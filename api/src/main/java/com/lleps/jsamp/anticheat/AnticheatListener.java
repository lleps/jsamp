/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lleps.jsamp.anticheat;

import com.lleps.jsamp.SAMPConstants;
import com.lleps.jsamp.SAMPFunctions;
import com.lleps.jsamp.anticheat.event.AccurateLevel;
import com.lleps.jsamp.anticheat.event.CheatEvent;
import com.lleps.jsamp.anticheat.event.InvalidCallEvent;
import com.lleps.jsamp.anticheat.event.UnsyncEvent;
import com.lleps.jsamp.server.CallbackListener;
import com.lleps.jsamp.server.SAMPServer;

import java.util.Arrays;
import java.util.Map;
import java.util.WeakHashMap;

import static com.lleps.jsamp.SAMPConstants.*;

/**
 *
 * This class will perform anticheat checks. if a player is suspected to be cheater, an AnticheatEvent will
 * be thrown and dispatched to SAMPServer's onAnticheatEvent method, so the action should be taken here.
 * The anticheat will continue performing the check if onAnticheatEvent returns false. If true, the check
 * will stop immediately.
 *
 * Tips taken from: http://forum.sa-mp.com/showthread.php?t=220089 by cessil.
 *
 *
 * TO-DO?
 * To improve performance, reformat checks by only one function, example: checkArmour(bool 250msPassed, bool 500msPassed, bool 1000msPassed)
 *
 * Some non-detected cheats:
 * * Infinite ammo
 * * Special actions
 * * When you teleport an unoccupied vehicle near you and enter them, the vehicle will be "teleported" to other players
 *
 * An observation:
 * - Most cheaters can be detecte and by writing checks for special tools, such as sobeit. This anticheat is just a "generic"
 * version.
 *
 *  -- STATE CHECKS
 * Player will be synced only if alive. A player is considered alive when spawned and not dead.
 * State checks logic:
 * - A player cannot death if is still death (alive == false)
 * - A player cannot spawn if is still spawned (alive == true) - SpawnPlayer sets alive to false!
 * - A player cannot spawn if SpawnPlayer wasn't called. OnPlayerRequestSpawn ever returns 0.
 *
 * -- NPC CONNECTIONS
 * A player can connect simulating an NPC (IsPlayerNPC returns true).
 * Solution: If a player connects as NPC and their IP isn't a local one, cheater.
 * An ip is considered local if starts with "127.0.0".
 *
 * -- PLAYER SYNCING
 * Syncing is done by sending an RPC to a player, and checking when the update reaches the player.
 * Basically, this system can be applied to every player information: vehicles,weapons,health,armour,etc.
 * If the server sends a message to a player, and the player doesn't report the update in x time, the
 * server will try to resync him by sending the message again. If after a while, the client is still
 * unsynced, then will be reported as an unsync.
 * Abstraction of a synchronizable value is SynchronizableProperty.
 *
 * -- HEALTH AND ARMOUR SYNCING
 * Read forum samp post to understand how this works.
 * Soda machines and interiors are removed to avoid client-side ways to get health.
 *
 * -- WEAPON HACKS
 * Works using per-slot synchronizable properties.
 * Some false-positives:
 * * Player can get a golf stick when exits from caddy. Fix: Slot is synced when player exits a caddy.
 * * Player can get a parachute when jumping from aircrafts. Fix: Same as above.
 * * Using SetSpawnInfo weapon params. Fix: Hook to ignore weapon params.
 * * Using AddPlayerClass. Fix: Disable this function with the entire sa-mp class system.
 * * Using pickups.
 * GetPlayerWeapon(Slot) need to be hooked to return right values. For example, if a cheater gets kicked because
 * they spawned a Minigun, and user code saves player weapons per user, when the cheater re-join will get a
 * server-side minigun.
 *
 * Note that most of the time cheaters will spawn a non-given-by-server weapon, so another check will be added to
 * keep track of which weapons were sent to a player and which not.
 *
 * -- AMMO
 * Every slot has a maximum ammo value. When you have more than the maximum, or negative ammo, an event will be thrown.
 * GivePlayerWeapon will add the given ammo to the maximum. SetPlayerAmmo will set the maximum ammo (in case the new ammo
 * exceeds the previous one). Any kind of server-side negative ammo will completely invalidate ammo checks.
 * Note that you can get cheated ammo if the server wants it.
 *
 * -- POSITION AND TELEPORT
 * Position is a synchronizable property, which is unsynced at SetPlayerPos(FindZ) and synced when player reach the point.
 * If distance between the last known position and new position is long, the player may be a cheater.
 * False positives on-foot:
 * * Player is surfing in a vehicle or object
 * * Player fell from the map
 * * Player was put in a vehicle that is far
 * in-vehicle (checks are performed for drivers only):
 * * You entered or exited from a modshop.
 * * Fell from map
 *
 * -- MONEY CHECKS
 * Money is server-sided. If player's money doesn't match server money, server will send to player real money.
 * Money is also decreased when player repair's their vehicle on a PaiNSpray and when bought a component in a modshop.
 *
 * Anticheat is also responsible of disable SA:MP features, to prevent false positives.
 * Stuff disabled:
 * - Interior enter-exits. DisableInteriorEnterExits is called at initialization.
 * - SetPlayerShopName. Calls to this function will be ignored.
 * - SAMP spawn and class selection system.
 *
 * Other checks:
 * * A jetpack without calling SetPlayerSpecialAction
 *
 * @author spell
 */
public class AnticheatListener implements CallbackListener {
    private final ACPlayer[] players;
    private final ACVehicle[] vehicles;

    private final long[] lockFor1000ms = new long[SAMPConstants.MAX_PLAYERS];
    private final long[] lockFor500ms = new long[SAMPConstants.MAX_PLAYERS];
    private final long[] lockFor250ms = new long[SAMPConstants.MAX_PLAYERS];

    private final SAMPServer sv;
    private final Anticheat anticheat;

    private Map<String, Long> ipConnectionTimes = new WeakHashMap<>();

    private final static int MIN_IP_CONNECTION_TIME_MS = 10000;

    private final static int POS_INDEX_X = 0, POS_INDEX_Y = 1, POS_INDEX_Z = 2;

    public AnticheatListener(Anticheat anticheat) {
        this.anticheat = anticheat;
        this.sv = anticheat.getServer();
        this.players = anticheat.getPlayers();
        this.vehicles = anticheat.getVehicles();

        SAMPFunctions.EnableStuntBonusForAll(false);
        SAMPFunctions.DisableInteriorEnterExits();
    }

    @Override
    public boolean OnPlayerConnect(int playerId) {
        String ip = SAMPFunctions.GetPlayerIp(playerId);

        if (SAMPFunctions.IsPlayerNPC(playerId)) {
            if (!isLocalIp(ip)) {
                if (reportCheat(playerId, AccurateLevel.HIGH, CheatEvent.Type.CONNECT_AS_NPC,
                        "Connection as NPC from non-local IP " + ip)) {
                    return true;
                }
            }
        }

        long now = System.currentTimeMillis();
        long lastIpConnection = ipConnectionTimes.getOrDefault(ip, 0L);
        if ((now - lastIpConnection) < MIN_IP_CONNECTION_TIME_MS) {
            if (reportCheat(playerId, AccurateLevel.MEDIUM, CheatEvent.Type.CONNECTION_FLOOD,
                    "time since " + ip + " last connection is "
                            + (now - lastIpConnection) + "ms, of min " + MIN_IP_CONNECTION_TIME_MS + "ms")) {
                return true;
            }
        }

        ipConnectionTimes.put(ip, now);

        ACPlayer player = new ACPlayer(playerId);
        player.setConnected(true);

        players[playerId] = player;
        return false;
    }

    @Override
    public boolean OnPlayerStateChange(int playerId, int newState, int oldState) {
        ACPlayer player = players[playerId];

        if (newState == PLAYER_STATE_DRIVER || newState == PLAYER_STATE_PASSENGER || newState == PLAYER_STATE_ONFOOT) {
            long lastStateChange = player.getLastStateChange();
            long currentMillis = System.currentTimeMillis();

            if ((currentMillis - lastStateChange) < 1000) {
                player.setStateChangeWarns(player.getStateChangeWarns() + 1);
                if (player.getStateChangeWarns() >= 3) {
                    if (reportCheat(player, AccurateLevel.MEDIUM, CheatEvent.Type.STATECHANGE_FLOOD, "state change flood")) {
                        return true;
                    }
                }
            } else {
                player.setStateChangeWarns(0);
            }

            player.setLastStateChange(currentMillis);
        }

        if (newState == PLAYER_STATE_DRIVER || newState == PLAYER_STATE_PASSENGER) {
            int vehicleId = SAMPFunctions.GetPlayerVehicleID(playerId);

            if (vehicles[vehicleId].isDoorsLocked()) {
                if (reportCheat(playerId, AccurateLevel.MEDIUM, CheatEvent.Type.ENTER_LOCKED_VEHICLE,
                        "entering vehicle with doors locked")) {
                    return true;
                }
            }

            if (player.getEnteringVehicleId() != vehicleId) {
                if (reportCheat(playerId, AccurateLevel.MEDIUM, CheatEvent.Type.ENTER_UNREQUESTED_VEHICLE,
                        "entering " + player.getEnteringVehicleId() + ", but entered " + vehicleId)) {
                    return true;
                }
            }
            player.setEnteringVehicleId(0);

            player.getVehicleId().setShouldBe(vehicleId);
            player.getVehicleId().sync();

            float vehicleHealth = SAMPFunctions.GetVehicleHealth(vehicleId);
            player.getVehicleHealth().setShouldBe(vehicleHealth);
            player.getVehicleHealth().sync();
        } else {
            int lastVehicle = player.getVehicleId().getShouldBe();
            int lastVehicleModel = SAMPFunctions.GetVehicleModel(lastVehicle);

            boolean dieInVehicle = newState == PLAYER_STATE_WASTED &&
                    (oldState == PLAYER_STATE_DRIVER || oldState == PLAYER_STATE_PASSENGER);

            if (!dieInVehicle) { // when player die in veh, vehicle shouldn't be 0. Anyway, checks are performed when alive only.
                player.getVehicleId().setShouldBe(0);
                player.getVehicleId().sync();

                player.getVehicleHealth().setShouldBe(0f);
                player.getVehicleHealth().sync();
            }

            // check caddy weaps
            if (oldState == PLAYER_STATE_DRIVER && newState == PLAYER_STATE_ONFOOT) {
                final int SLOT_GOLFSTICK = ACUtils.getWeaponSlot(WEAPON_GOLFCLUB);
                // Sync a golf stick when exiting a caddy (in case slot is empty)
                if (lastVehicleModel == 457/*caddy*/
                        && players[playerId].getWeaponInSlot(SLOT_GOLFSTICK).getShouldBe() == 0) {

                    players[playerId].getWeaponInSlot(SLOT_GOLFSTICK).setShouldBe(WEAPON_GOLFCLUB);
                    players[playerId].getWeaponInSlot(SLOT_GOLFSTICK).sync();
                }
            }

            // check parachute
            if ((oldState == PLAYER_STATE_DRIVER || oldState == PLAYER_STATE_PASSENGER)
                    && newState == PLAYER_STATE_ONFOOT) {
                final int SLOT_PARACHUTE = ACUtils.getWeaponSlot(WEAPON_PARACHUTE);

                if (ACUtils.isPlaneModel(lastVehicleModel)
                        && players[playerId].getWeaponInSlot(SLOT_PARACHUTE).getShouldBe() == 0) {
                    // player exited from a plane - sync a parachute.
                    players[playerId].getWeaponInSlot(SLOT_PARACHUTE).setShouldBe(WEAPON_PARACHUTE);
                    players[playerId].getWeaponInSlot(SLOT_PARACHUTE).sync();
                }
            }
        }
        return false;
    }

    @Override
    public boolean OnPlayerEnterVehicle(int playerId, int vehicleId, boolean isPassenger) {
        if (!SAMPFunctions.IsValidVehicle(vehicleId)) {
            if (reportInvalidCall(playerId,
                    "OnPlayerEnterVehicle(pid=%d,vid=%d,passenger=%b)", playerId, vehicleId, isPassenger)) {
                return true;
            }
        }

        if (isPassenger && vehicles[vehicleId].isDoorsLocked()) {
            int modelId = SAMPFunctions.GetVehicleModel(vehicleId);
            if (ACUtils.isBikeModel(modelId)) {
                // You can enter bikes as pssenget even if locked, so we'll prevent players from entering
                // bikes as passengers, to prevent false-positives.
                float[] pos = SAMPFunctions.GetPlayerPos(playerId);
                SAMPFunctions.SetPlayerPos(playerId, pos[0], pos[1], pos[2]);
            }
        }
        players[playerId].setEnteringVehicleId(vehicleId);
        return false;
    }

    @Override
    public boolean OnPlayerSpawn(int playerId) {
        if (!players[playerId].isSpawnAllowed()) {
            // the only way of spawning is through SpawnPlayer (that will set this var to true)
            if (reportCheat(playerId, AccurateLevel.MEDIUM, CheatEvent.Type.INVALID_SPAWN, "Spawning without SpawnPlayer")) {
                return true;
            }
        }

        if (!players[playerId].isAlreadySpawned()) { // first time spawning
            SAMPFunctions.RemoveBuildingForPlayer(playerId, 1302, 0, 0, 0, 6000);
            SAMPFunctions.RemoveBuildingForPlayer(playerId, 1209, 0, 0, 0, 6000);
            SAMPFunctions.RemoveBuildingForPlayer(playerId, 955, 0, 0, 0, 6000);
            SAMPFunctions.RemoveBuildingForPlayer(playerId, 956, 0, 0, 0, 6000);
            SAMPFunctions.RemoveBuildingForPlayer(playerId, 1775, 0, 0, 0, 6000);
            SAMPFunctions.RemoveBuildingForPlayer(playerId, 1776, 0, 0, 0, 6000);
            SAMPFunctions.RemoveBuildingForPlayer(playerId, 1977, 0, 0, 0, 6000);

            players[playerId].setAlreadySpawned(true);
        }

        // Player spawned when is already alive? SpawnPlayer sets alive to false..
        // A possible false-positive:
        // SpawnPlayer is called two times. The first message is received, then player spawns and alive sets to true.
        // The second SpawnPlayer is received, but player has alive = true, so the player gets kicked.
        if (players[playerId].isAlive()) {
            if (reportCheat(playerId, AccurateLevel.MEDIUM, CheatEvent.Type.SPAWN_WHEN_ALIVE, "Spawning when is alive")) {
                return true;
            }
        }
        players[playerId].setAlive(true);

        // Health is 100 when player spawn.
        players[playerId].getHealth().setShouldBe(100f);
        players[playerId].getHealth().sync();

        // Sync the armour - should be 0 and IS 0.
        players[playerId].getArmour().setShouldBe(0f);
        players[playerId].getArmour().sync();

        // Sync vehicle
        players[playerId].getVehicleId().setShouldBe(0);
        players[playerId].getVehicleId().sync();
        players[playerId].getVehicleHealth().setShouldBe(0f);
        players[playerId].getVehicleHealth().sync();

        players[playerId].getPosition().setShouldBe(SAMPFunctions.GetPlayerPos(playerId));
        players[playerId].getPosition().sync();

        for (int slot = 0; slot < MAX_WEAPON_SLOTS; slot++) {
            players[playerId].getWeaponInSlot(slot).setShouldBe(0);
            players[playerId].getWeaponInSlot(slot).sync();
        }
        return false;
    }

    @Override
    public boolean OnPlayerDeath(int playerId, int killerId, int reason) {
        if (!players[playerId].isAlive()) {
            // it can be due to fake-kill or some desync.
            if (reportCheat(playerId, AccurateLevel.MEDIUM, CheatEvent.Type.DEATH_WHEN_NOT_ALIVE,
                    "Player die when is not alive")) {
                return true;
            }
        }
        players[playerId].setAlive(false);

        players[playerId].getHealth().setShouldBe(0f);
        players[playerId].getHealth().sync();
        return false;
    }

    @Override
    public boolean OnPlayerDisconnect(int playerId, int reason) {
        return false;
    }

    @Override
    public boolean OnPlayerUpdate(int playerId) {
        ACPlayer player = players[playerId];

        if (!player.isAlive()) {
            return true; // Discard update if player is not alive.
        }


        long currentMillis = System.currentTimeMillis();

        if (currentMillis > lockFor250ms[playerId]) {
            lockFor250ms[playerId] = currentMillis + 250;
            if (checkEvery250ms(player)) return true;
        }
        if (currentMillis > lockFor500ms[playerId]) {
            lockFor500ms[playerId] = currentMillis + 500;
            if (checkEvery500ms(player)) return true;
        }
        if (currentMillis > lockFor1000ms[playerId]) {
            lockFor1000ms[playerId] = currentMillis + 1000;
            if (checkEvery1000ms(player)) return true;
        }

        return checkEveryUpdate(player);
    }

    private boolean checkEveryUpdate(ACPlayer player) {
        if (player.getVehicleId().isSynced()) {
            if (checkVehicleId(player)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkEvery250ms(ACPlayer player) {
        if (!player.getPosition().isSynced()) {
            tryToSyncPosition(player);
        }
        return false;
    }

    private boolean checkEvery500ms(ACPlayer player) {

        if (player.getPosition().isSynced()) {
            if (checkPosition(player)) {
                return true;
            }
        }

        if (player.getVehicleHealth().isSynced()) {
            if (checkVehicleHealth(player)) {
                return true;
            }
        }

        if (checkSpeedHack(player)) {
            return true;
        }

        return false;
    }

    private boolean checkEvery1000ms(ACPlayer player) {
        if (player.getHealth().isSynced()) {
            if (checkHealth(player)) {
                return true;
            }
        } else {
            if (!tryToSyncHealth(player)) {
                if (checkForHealthTimeout(player)) {
                    return true;
                }
            }
        }

        if (player.getArmour().isSynced()) {
            if (checkArmour(player)) {
                return true;
            }
        } else {
            if (!tryToSyncArmour(player)) {
                if (checkForArmourTimeout(player)) {
                    return true;
                }
            }
        }

        if (!player.getVehicleId().isSynced()) {
            if (!tryToSyncVehicleId(player)) {
                if (checkForVehicleIdTimeout(player)) {
                    return true;
                }
            }
        }

        if (!player.getVehicleHealth().isSynced()) {
            if (!tryToSyncVehicleHealth(player)) {
                if (checkForVehicleHealthTimeout(player)) {
                    return true;
                }
            }
        }

        for (int slot = 0; slot < 12; slot++) { // slot 12 is ignored.
            if (player.getWeaponInSlot(slot).isSynced()) {
                if (checkWeaponSlot(player, slot)) {
                    return true;
                }
            } else {
                if (!tryToSyncWeaponSlot(player, slot)) {
                    if (checkForWeaponSlotTimeout(player, slot)) {
                        return true;
                    }
                }
            }
        }

        if (!player.getPosition().isSynced()) {
            if (checkForPositionTimeout(player)) {
                return true;
            }
        }

        if (checkOtherStuff(player)) {
            return true;
        }
        return false;
    }

    private boolean checkOtherStuff(ACPlayer player) {
        int playerId = player.getId();

        if (SAMPFunctions.GetPlayerSpecialAction(playerId) == SPECIAL_ACTION_USEJETPACK) {
            if (!player.isJetpackAllowed()) {
                if (reportCheat(playerId, AccurateLevel.HIGH, CheatEvent.Type.JETPACK, "Using a jetpack")) {
                    return true;
                }
            }
        }

        if (SAMPFunctions.GetPlayerMoney(playerId) != player.getMoney()) {
            SAMPFunctions.ResetPlayerMoney(playerId);
            SAMPFunctions.GivePlayerMoney(playerId, player.getMoney());
        }
        return false;
    }

    private boolean checkSpeedHack(ACPlayer player) {
        if (SAMPFunctions.GetPlayerState(player.getId()) == PLAYER_STATE_DRIVER) {
            int playerId = player.getId();
            int vehicleId = SAMPFunctions.GetPlayerVehicleID(playerId);
            float[] velocity = SAMPFunctions.GetVehicleVelocity(vehicleId);
            float speed2d = ACUtils.get2DSpeed(velocity[0], velocity[1]);
            int modelId = SAMPFunctions.GetVehicleModel(vehicleId);
            float maxSpeed = ACUtils.getVehicleModelMaxSpeed(modelId);
            if (speed2d > (maxSpeed + 5)) {
                return reportCheat(playerId, AccurateLevel.MEDIUM, CheatEvent.Type.SPEED_HACK, "speed hack: " + speed2d + "/" + maxSpeed);
            }
        }
        return false;
    }

    @Override
    public boolean OnPlayerWeaponShot(int playerid, int weaponid, int hittype, int hitid, float fX, float fY, float fZ) {
        if (weaponid < 22 || weaponid > 38) {
            if (reportInvalidCall(playerid,
                    "OnPlayerWeaponShot(pid=%d,weap=%d,hittype=%d,hitid=%d,x=%f,y=%f,z=%f)", playerid, weaponid,
                    hittype, hitid, fX, fY, fZ)) {

                return true;
            }
        }

        int weaponAtThisSlot = ACUtils.getWeaponSlot(weaponid);
        if (players[playerid].getWeaponInSlot(weaponAtThisSlot).getShouldBe() != weaponid) {
            return true;
        }

        float[] playerPos = SAMPFunctions.GetPlayerPos(playerid);
        //Desync shots with Z pos out of bounds
        if (playerPos[POS_INDEX_Z] < -20_000 || playerPos[POS_INDEX_Z] > 20_000) {
            return true;
        }
        return false;
    }

    private boolean tryToSyncVehicleId(ACPlayer player) {
        if (SAMPFunctions.GetPlayerVehicleID(player.getId()) == player.getVehicleId().getShouldBe()) {
            player.getVehicleId().sync();
            return true;
        }
        return false;
    }

    private boolean checkVehicleId(ACPlayer player) {
        int vehicleId = SAMPFunctions.GetPlayerVehicleID(player.getId());
        if (vehicleId > 0 && vehicleId != player.getVehicleId().getShouldBe()) {
            return reportCheat(player, AccurateLevel.MEDIUM, CheatEvent.Type.VEHICLE_TELEPORT,
                    "Invalid vehicle: " + vehicleId + "/" + player.getVehicleId().getShouldBe());
        }
        return false;
    }

    private boolean checkForVehicleIdTimeout(ACPlayer player) {
        int unsyncSeconds = player.getVehicleId().increaseUnsyncedSecondsAndGet();
        if (shouldTimeout(unsyncSeconds)) {
            return reportUnsyncTimeout(player.getId(), UnsyncEvent.Type.VEHICLE_ID, "vehicle id");
        }
        return false;
    }

    private boolean tryToSyncWeaponSlot(ACPlayer player, int slot) {
        int currentWeapon = SAMPFunctions.GetPlayerWeaponSlot(player.getId(), slot);
        if (currentWeapon == player.getWeaponInSlot(slot).getShouldBe()) {
            player.getWeaponInSlot(slot).sync();
            return true;
        }
        return false;
    }

    private boolean checkWeaponSlot(ACPlayer player, int slot) {
        int currentWeapon = SAMPFunctions.GetPlayerWeaponSlot(player.getId(), slot);
        SynchronizableProperty<Integer> weapon = player.getWeaponInSlot(slot);
        if (currentWeapon != weapon.getShouldBe()) {
            return reportCheat(player, AccurateLevel.MEDIUM, CheatEvent.Type.WEAPON_HACK,
                    "weapon is " + currentWeapon + " - should be " + weapon.getShouldBe());
        }

        if (ACUtils.weaponSlotHoldsAmmo(slot)) {
            int ammo = SAMPFunctions.GetPlayerAmmoSlot(player.getId(), slot);
            if (!player.isInvalidAmmoPossible(slot)) {
                if (ammo < -1 || ammo > player.getWeaponSlotMaxAmmo(slot)) {
                    return reportCheat(player, AccurateLevel.HIGH, CheatEvent.Type.AMMO_HACK,
                            "ammo hack: " + ammo + "/" + player.getWeaponSlotMaxAmmo(slot));
                }
            }
        }
        return false;
    }

    private boolean checkForWeaponSlotTimeout(ACPlayer player, int slot) {
        int unsyncSeconds = player.getWeaponInSlot(slot).increaseUnsyncedSecondsAndGet();
        if (shouldTimeout(unsyncSeconds)) {
            return reportUnsyncTimeout(player.getId(), UnsyncEvent.Type.WEAPON_ID, "weapon slot unsynced");
        }
        return false;
    }

    private boolean tryToSyncPosition(ACPlayer player) {
        float[] posShouldBe = player.getPosition().getShouldBe();
        if (SAMPFunctions.IsPlayerInRangeOfPoint(player.getId(), 3, posShouldBe[0], posShouldBe[1], posShouldBe[2])) {
            player.getPosition().sync();
            return true;
        }
        return false;
    }

    private boolean checkPosition(ACPlayer player) {
        long msSinceLastCheck = System.currentTimeMillis() - player.getLastPositionCheck();

        int state = SAMPFunctions.GetPlayerState(player.getId());

        float[] position = SAMPFunctions.GetPlayerPos(player.getId());
        float[] positionShouldBe = player.getPosition().getShouldBe();

        if (state == PLAYER_STATE_DRIVER || state == PLAYER_STATE_ONFOOT) {
            float toMtsPerSecond = (float)msSinceLastCheck / 1000f;

            float toleranceDistToWarn = toMtsPerSecond * 20;
            float toleranceDistToTeleport = toMtsPerSecond * 300f;
            float distance = ACUtils.distanceBetweenPoints(position, positionShouldBe);

            if (state == PLAYER_STATE_ONFOOT) {
                float[] velocity = SAMPFunctions.GetPlayerVelocity(player.getId());

                if (velocity[POS_INDEX_Z] < -0.3f/*falling*/) {
                    toleranceDistToWarn += toMtsPerSecond * 60;
                }

                if (positionShouldBe[POS_INDEX_Z] < -20 && position[POS_INDEX_Z] > 0) { //  fell from map
                    toleranceDistToWarn += 200; // 200 mts but may be even more.
                }

                if (SAMPFunctions.GetPlayerSurfingVehicleID(player.getId()) != INVALID_VEHICLE_ID
                        || SAMPFunctions.GetPlayerSurfingObjectID(player.getId()) != INVALID_OBJECT_ID) {
                    toleranceDistToWarn += toMtsPerSecond * 40;
                }
            } else {// PLAYER_STATE_DRIVER
                int vehicleId = SAMPFunctions.GetPlayerVehicleID(player.getId());
                float[] velocity = SAMPFunctions.GetVehicleVelocity(vehicleId);
                float speedInMetersPerSecond = (ACUtils.get3DSpeed(velocity[0], velocity[1], velocity[2]) * 1000) / 3600;

                toleranceDistToWarn += speedInMetersPerSecond * toMtsPerSecond;

                if (positionShouldBe[POS_INDEX_Z] < -20 && position[POS_INDEX_Z] > 0) {
                    toleranceDistToWarn += 200;
                }
            }

            if (distance > toleranceDistToWarn) {
                boolean falsePositive = false;

                final float ratioForModshopCheck = 30;
                if (
                        (ACUtils.isNearModshopExterior(positionShouldBe, ratioForModshopCheck)
                                && ACUtils.isNearModshopInterior(position, ratioForModshopCheck))
                        || (ACUtils.isNearModshopExterior(position, ratioForModshopCheck)
                                && ACUtils.isNearModshopInterior(positionShouldBe, ratioForModshopCheck))) {
                    falsePositive = true;
                }

                if (!falsePositive) {
                    float toSeconds = msSinceLastCheck / 1000f;

                    AccurateLevel accurateLevel = distance > toleranceDistToTeleport ? AccurateLevel.MEDIUM : AccurateLevel.LOW;
                    if (reportCheat(player.getId(), accurateLevel, CheatEvent.Type.POSITION_HACK,
                            "moved " + distance + " in " + toSeconds + " secs.")) {
                        return true;
                    }
                }
            }
        }

        player.getPosition().setShouldBe(position);
        player.setLastPositionCheck(System.currentTimeMillis());
        return false;
    }

    private boolean checkForPositionTimeout(ACPlayer player) {
        int unsyncSeconds = player.getPosition().increaseUnsyncedSecondsAndGet();
        if (shouldTimeout(unsyncSeconds)) {
            return reportUnsyncTimeout(player.getId(), UnsyncEvent.Type.POSITION, "position unsynced");
        } else if (shouldResync(unsyncSeconds)) {
            float[] posShouldBe = player.getPosition().getShouldBe();
            SAMPFunctions.SetPlayerPos(player.getId(), posShouldBe[0], posShouldBe[1], posShouldBe[2]);
        }
        return false;
    }

    private boolean tryToSyncHealth(ACPlayer player) {
        int playerId = player.getId();
        int currentHealth = (int) SAMPFunctions.GetPlayerHealth(playerId);
        if (player.getHealth().getShouldBe().intValue() == currentHealth) {
            player.getHealth().sync();
            return true;
        }
        return false;
    }

    private boolean checkHealth(ACPlayer player) {
        int currentHealth = (int)SAMPFunctions.GetPlayerHealth(player.getId());
        if (currentHealth < 0 || currentHealth > 100) {
            if (reportCheat(player, AccurateLevel.HIGH, CheatEvent.Type.HEALTH_HACK, "Invalid health: " + currentHealth)) {
                return true;
            }
        }

        int healthShouldBe = player.getHealth().getShouldBe().intValue();
        if (currentHealth > healthShouldBe) {
            SAMPFunctions.SetPlayerHealth(player.getId(), healthShouldBe);
            reportCheat(player, AccurateLevel.LOW, CheatEvent.Type.HEALTH_HACK,
                    "Health is " + currentHealth + " - should be " + healthShouldBe);
        }
        return false;
    }

    private boolean checkForHealthTimeout(ACPlayer player) {
        int unsyncSeconds = player.getHealth().increaseUnsyncedSecondsAndGet();
        if (shouldTimeout(unsyncSeconds)) {
            return reportUnsyncTimeout(player.getId(), UnsyncEvent.Type.HEALTH, "health unsynced");
        } else if (shouldResync(unsyncSeconds)) {
            SAMPFunctions.SetPlayerHealth(player.getId(), player.getHealth().getShouldBe());
        }
        return false;
    }

    private boolean tryToSyncArmour(ACPlayer player) {
        int currentArmour = (int) SAMPFunctions.GetPlayerArmour(player.getId());
        if (currentArmour == player.getArmour().getShouldBe().intValue()) {
            player.getArmour().sync();
            return true;
        }
        return false;
    }

    private boolean checkArmour(ACPlayer player) {
        int playerId = player.getId();
        int armourShouldBe = player.getArmour().getShouldBe().intValue();
        int currentArmour = (int) SAMPFunctions.GetPlayerArmour(player.getId());

        if ((currentArmour > 0 && !player.isArmourAllowed()) || currentArmour == 100) {
            if (reportCheat(playerId, AccurateLevel.HIGH, CheatEvent.Type.ARMOUR_HACK,
                    "Armour hack (" + currentArmour + ")")) {
                return true;
            }
        }
        if (currentArmour < 0 || currentArmour > 100) {
            if (reportCheat(playerId, AccurateLevel.HIGH, CheatEvent.Type.ARMOUR_HACK,
                    "Invalid armour: " + currentArmour)) {
                return true;
            }
        }
        if (currentArmour > armourShouldBe) {
            SAMPFunctions.SetPlayerArmour(playerId, armourShouldBe);
            reportCheat(playerId, AccurateLevel.LOW, CheatEvent.Type.ARMOUR_HACK,
                    "Armour increased from " + armourShouldBe + " to " + currentArmour);
        }
        return false;
    }

    private boolean checkForArmourTimeout(ACPlayer player) {
        int unsyncSecs = player.getArmour().increaseUnsyncedSecondsAndGet();
        if (shouldTimeout(unsyncSecs)) {
            return reportUnsyncTimeout(player.getId(), UnsyncEvent.Type.ARMOUR, "armour unsynced");
        } else if (shouldResync(unsyncSecs)) {
            SAMPFunctions.SetPlayerArmour(player.getId(), player.getArmour().getShouldBe());
        }
        return false;
    }

    private boolean tryToSyncVehicleHealth(ACPlayer player) {
        int health = (int)SAMPFunctions.GetVehicleHealth(SAMPFunctions.GetPlayerVehicleID(player.getId()));
        if (health == player.getVehicleHealth().getShouldBe().intValue()) {
            player.getVehicleHealth().sync();
            return true;
        }
        return false;
    }

    private boolean checkVehicleHealth(ACPlayer player) {
        if (SAMPFunctions.GetPlayerState(player.getId()) == PLAYER_STATE_DRIVER) {
            int vehicleId = SAMPFunctions.GetPlayerVehicleID(player.getId());
            float health = SAMPFunctions.GetVehicleHealth(vehicleId);
            float healthShouldBe = player.getVehicleHealth().getShouldBe();

            if (health > healthShouldBe) {
                boolean repairedInPayNSprayOrModshop = false;
                if (health == 1000) {
                    if (ACUtils.isNearPayNSpray(SAMPFunctions.GetVehiclePos(vehicleId), 20)) {
                        if (hasEnoughMoneyToPay(player, 100)) {
                            player.setMoney(player.getMoney() - 100);
                            repairedInPayNSprayOrModshop = true;
                        }
                    } else if (ACUtils.isNearModshopInterior(SAMPFunctions.GetVehiclePos(vehicleId), 20)) {
                        if (hasEnoughMoneyToPay(player, 150)) {
                            player.setMoney(player.getMoney() - 150);
                            repairedInPayNSprayOrModshop = true;
                        }
                    }
                }
                if (!repairedInPayNSprayOrModshop && reportCheat(player.getId(), AccurateLevel.MEDIUM,
                        CheatEvent.Type.VEHICLE_HEALTH_HACK, "invalid vehicle health: " + health + "/" + healthShouldBe)) {
                    SAMPFunctions.SetVehicleHealth(vehicleId, healthShouldBe);
                    return true;
                } else {
                    player.getVehicleHealth().setShouldBe(health);
                }
            } else if (health < healthShouldBe) {
                player.getVehicleHealth().setShouldBe(health);
            }
        }
        return false;
    }

    private boolean hasEnoughMoneyToPay(ACPlayer player, int moneyToPay) {
        return (player.getMoney() - moneyToPay) >= 0;
    }

    private boolean checkForVehicleHealthTimeout(ACPlayer player) {
        int seconds = player.getVehicleHealth().increaseUnsyncedSecondsAndGet();
        if (shouldTimeout(seconds)) {
            return reportUnsyncTimeout(player.getId(), UnsyncEvent.Type.VEHICLE_HEALTH, "vehicle health");
        } else if (shouldResync(seconds)) {
            if (SAMPFunctions.GetPlayerState(player.getId()) == PLAYER_STATE_DRIVER) {
                SAMPFunctions.SetVehicleHealth(
                        SAMPFunctions.GetPlayerVehicleID(player.getId()),
                        player.getVehicleHealth().getShouldBe());
            }
        }
        return false;
    }

    @Override
    public boolean OnPlayerTakeDamage(int playerid, int issuerId, float damage, int weaponid, int bodypart) {
        if (damage < 0 || (issuerId != INVALID_PLAYER_ID && !anticheat.isConnected(issuerId))) {
            if (reportInvalidCall(playerid, "OnPlayerTakeDamage(pid=%d,issuer=%d,damage=%f,weap=%d,bodypart=%d",
                    playerid, issuerId, damage, weaponid, bodypart)) {
                return true;
            }
        }

        ACPlayer player = players[playerid];
        float healthShouldBe = player.getHealth().getShouldBe();
        float armourShouldBe = player.getArmour().getShouldBe();

        if (issuerId == INVALID_PLAYER_ID) {
            healthShouldBe -= damage;
        } else {
            healthShouldBe = armourShouldBe + (healthShouldBe - damage);
            armourShouldBe -= damage;
        }

        if (healthShouldBe < 0) healthShouldBe = 0;
        if (armourShouldBe < 0) armourShouldBe = 0;

        player.getHealth().setShouldBe(healthShouldBe);
        player.getArmour().setShouldBe(armourShouldBe);
        return false;
    }

    @Override
    public boolean OnEnterExitModShop(int playerId, int enterexit, int interiorId) {
        if (SAMPFunctions.GetPlayerState(playerId) == PLAYER_STATE_DRIVER) {
            int vehicleId = SAMPFunctions.GetPlayerVehicleID(playerId);
            float[] vehiclePos = SAMPFunctions.GetVehiclePos(vehicleId);
            if (enterexit == 1) {
                if (!ACUtils.isNearModshopExterior(vehiclePos, 30)) {
                    if (reportCheat(playerId, AccurateLevel.MEDIUM, CheatEvent.Type.INVALID_MODSHOP_ENTER,
                            "entering modshop from unknown position: " + Arrays.toString(vehiclePos))) {
                        return true;
                    }
                }
                players[playerId].setInModshop(true);
            } else if (enterexit == 0){
                if (!ACUtils.isNearModshopInterior(vehiclePos, 30)) {
                    if (reportCheat(playerId, AccurateLevel.MEDIUM, CheatEvent.Type.INVALID_MODSHOP_EXIT,
                            "exiting modshop from unknown position: " + Arrays.toString(vehiclePos))) {
                        return true;
                    }
                }
                players[playerId].setInModshop(false);
            }
        }
        return false;
    }

    @Override
    public boolean OnVehicleMod(int playerId, int vehicleId, int componentId) {
        if (!anticheat.isValidVehicle(vehicleId) && reportInvalidCall(playerId, "OnVehicleMod(pid=%d,veh=%d,comp=%d)",
                playerId, vehicleId, componentId)) {
            return true;
        }

        ACPlayer player = players[playerId];

        if (!player.isInModshop()) {
            if (reportCheat(player, AccurateLevel.HIGH, CheatEvent.Type.TUNNING_HACK,
                    "tunning without beign on a modshop: " + componentId)) {
                return true;
            }
        }
        int modelId = SAMPFunctions.GetVehicleModel(vehicleId);
        boolean compatibleComponent = ACUtils.isValidComponent(modelId, componentId);
        if (!compatibleComponent) {
            if (reportCheat(player, AccurateLevel.HIGH, CheatEvent.Type.TUNNING_HACK,
                    "adding to vehicle model "+modelId+" invalid component " + componentId)) {
                return true;
            }
        }

        int componentPrice = ACUtils.getComponentPrice(componentId);
        if (hasEnoughMoneyToPay(player, componentPrice)) {
            player.setMoney(player.getMoney() - componentPrice);
            return false;
        } else {
            if (reportCheat(player, AccurateLevel.MEDIUM, CheatEvent.Type.TUNNING_HACK,
                    "bought component "+componentId+" without enough money: $" + componentPrice)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean OnVehiclePaintjob(int playerId, int vehicleId, int paintjobId) {
        if (!anticheat.isValidVehicle(vehicleId) && reportInvalidCall(playerId, "OnVehiclePaintjob(p=%d,v=%d,paintjob=%d)",
                playerId, vehicleId, paintjobId)) {
            return true;
        }

        if (!players[playerId].isInModshop()) {
            if (reportCheat(playerId, AccurateLevel.MEDIUM, CheatEvent.Type.PAINTJOB_HACK, "Paintjob without beign on modshop")) {
                if (paintjobId >= 0 && paintjobId <= 3) SAMPFunctions.ChangeVehiclePaintjob(vehicleId, 255);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean OnUnoccupiedVehicleUpdate(int vehicleId, int playerId, int passengerSeat, float newX, float newY, float newZ, float vel_x, float vel_y, float vel_z) {
        if (
                ACUtils.get3DSpeed(vel_x, vel_y, vel_z) < 20
                && SAMPFunctions.GetVehicleDistanceFromPoint(vehicleId, newX, newY, newZ) < 2
                && SAMPFunctions.IsPlayerInRangeOfPoint(playerId, 5, newX, newY, newZ)
                ) {
            return false;
        }
        return true;
    }

    @Override
    public boolean OnVehicleRespray(int playerId, int vehicleId, int color1, int color2) {
        if (!anticheat.isValidVehicle(vehicleId) && reportInvalidCall(playerId, "OnVehicleRespray(p=%d,v=%d,col1=%d,col2=%d",
                playerId, vehicleId, color1, color2)) {
            return true;
        }

        ACPlayer player = players[playerId];

        if (!player.isInModshop() && !ACUtils.isNearModshopExterior(SAMPFunctions.GetPlayerPos(playerId), 30)) {
            if (reportCheat(playerId, AccurateLevel.HIGH, CheatEvent.Type.RESPRAY_HACK, "vehicle colour hacks (colours " + color1 + ", " + color2 + ")")) {
                return true;
            }
        }
        // Money is not set here, see vehicle health checks.
        ACVehicle vehicle = vehicles[vehicleId];
        vehicle.setColor1(color1);
        vehicle.setColor2(color2);
        return false;
    }

    @Override
    public boolean OnPlayerRequestSpawn(int playerId) {
        // This SA-MP spawn system is also disabled on JSAMP.
        return true;
    }

    private boolean reportCheat(ACPlayer player, AccurateLevel level, CheatEvent.Type type, String description) {
        return sv.onAnticheatEvent(player.getId(), new CheatEvent(level, type, description));
    }

    private boolean reportCheat(int playerId, AccurateLevel level, CheatEvent.Type type, String description) {
        return sv.onAnticheatEvent(playerId, new CheatEvent(level, type, description));
    }

    private boolean reportUnsyncTimeout(int playerId, UnsyncEvent.Type type, String description) {
        return sv.onAnticheatEvent(playerId, new UnsyncEvent(type, description));
    }

    private boolean reportInvalidCall(int playerId, String callbackNameFormatted, Object... arguments) {
        return sv.onAnticheatEvent(playerId, new InvalidCallEvent(String.format(callbackNameFormatted, arguments)));
    }

    private boolean shouldResync(int seconds) {
        return seconds == 7 || seconds == 17;
    }

    private boolean shouldTimeout(int seconds) {
        return seconds >= anticheat.getUnsyncSecondsToTimeout();
    }

    private boolean isLocalIp(String ip) {
        return ip.startsWith("127.0.0") || ip.startsWith("10.0.0");
    }
}