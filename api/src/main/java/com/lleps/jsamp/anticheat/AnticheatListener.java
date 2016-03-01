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
import com.lleps.jsamp.anticheat.event.UnsyncEvent;
import com.lleps.jsamp.gamemode.CallbackListener;
import com.lleps.jsamp.gamemode.GameMode;

import static com.lleps.jsamp.SAMPConstants.*;

/**
 * This class will perform anticheat checks. if a player is suspected to be cheater, an AnticheatEvent will
 * be thrown and dispatched to GameMode's onAnticheatEvent method, so the action should be taken here.
 * The anticheat will continue performing the check if onAnticheatEvent returns false. If true, the check
 * will stop immediately.
 *
 * Tips taken from: http://forum.sa-mp.com/showthread.php?t=220089 by cessil.
 *
 * -------------
 * STATE CHECKS
 * ------------
 * Player will be synced only if alive. A player is considered alive when spawned and not dead.
 * State checks logic:
 * - A player cannot death if is still death (alive == false)
 * - A player cannot spawn if is still spawned (alive == true) - SpawnPlayer sets alive to false!
 * - A player cannot spawn if SpawnPlayer wasn't called. OnPlayerRequestSpawn ever returns 0.
 *
 * ---------------
 * NPC CONNECTIONS
 * ---------------
 * A player can connect simulating an NPC (IsPlayerNPC returns true).
 * Solution: If a player connects as NPC and their IP isn't a local one, cheater.
 * An ip is considered local if starts with "127.0.0".
 *
 * --------------
 * PLAYER SYNCING
 * --------------
 * Syncing is done by sending an RPC to a player, and checking when the update reachs the player.
 * Basically, this system can be applied to every player information: vehicles,weapons,health,armour,etc.
 * If the server sends a message to a player, and the player doesn't report the update in x time, the
 * server will try to resync him by sending the message again. If after a while, the client is still
 * unsynced, then will be reported as an unsync.
 * Abstraction of a synchronizable value is SynchronizableProperty.
 *
 * -------------------------
 * HEALTH AND ARMOUR SYNCING
 * -------------------------
 * Read forum samp post to understand how this works.
 * Soda machines and interiors will be removed to avoid client-side ways to get health.
 *
 * ------------
 * WEAPON HACKS
 * ------------
 * What anticheat should do:
 * 1. Report when a player gets a client-side weapon.
 * 2. Report when a player gets client-side ammo.
 * 3. Report when a player doesn't lost ammo.
 *
 * 1) Works using sync:
 * A slot can be synced or not. For example, givePlayerWeapon(m4 id) will desync m4 weapon slot and wait a player to
 * get that weapon.
 * If the slot is synced and the weapon doesn't match the player variable, report.
 * Sync is done through GetPlayerWeapon. The bad thing about GetPlayerWeaponData is that sometimes
 * returns wrong information: When you really don't have a weapon, this function may return that you
 * have that weapon with zero ammo.
 * Also, ammo can return -1.. so, the best way is validating the ammo value.
 *
 * Some false-positives:
 * * Player can get a golf stick when exits from caddy. Fix: Slot is synced when player exits a cady.
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
 * -------------------
 * AMMO HACK DETECTION
 * -------------------
 * There are 2 kind of weapons: Weapons that use bullets and weapons that don't use bullets.
 * Weapon that use bullets:
 * * We can keep track of player ammo, by subtracting -1 in OnPlayerWeaponShot and adding what given on GivePlayerWeapon.
 * Problems? Yes: drive by. Client dont sync drive-by bullets.. An approach would be to subtract bullets on OPU, by comparing
 * new value and previous value. The problem is that you'll be able to use infinite ammo.
 *
 * So, the system works as following:
 * - You have an ammo variable for each slot. When you shoot, the variable gets decreased by 1.
 * A check is performed every second to check your state. When your weapon has more ammo than the expected, maybe
 * the WeaponShot RPC reached the server before the update reporting the new ammo: a false positive happened.
 * Ammo changes happens lot of times, so it can make lot of false positives..
 * Maybe, ammo can be synced too.
 *
 * Ok. An invalid ammo would result in a . Invalid ammo is: (amo < -1) and (ammo > (max ammo that you can have))
 * max_ammo_that_you_can_have is obtained by keeping track of given ammo per slot.
 * Process must be different for weapons with bullets and weapons without bullets.
 *
 * TODO: Rewrite ammo and anticheat checks.
 *
 *  design
 * Okay. Player has a synchronizableVar for each weapon slot.
 * When GivePlayerWeapon is called, the slot var is unsynced and will start waiting for sync on the 1-sec update.
 * but.. if you have this slot synced and your weapon doesn't match, report.
 *
 * ---------------------
 * POSITION AND TELEPORT
 * ---------------------
 * Position is a synchronizable var, which is unsynced at SetPlayerPos(FindZ) and synced when player reach the point.
 * If distance between the last known position and new position is long, MAYBE player is cheating.
 * False positives:
 * * Player is surfing in a vehicle or object
 * * Player fell from the map
 * * Player was put in a vehicle that is far
 *
 * There is a problem:
 * * 1-second sync checks can cause problems, because maybe at this time,
 *
 * -------------------------------------------
 * VEHICLE TELEPORT/REMOTE VEHICLE CONTROLLING
 * -------------------------------------------
 * You need to be the driver to send vehicle updates.
 * Anti telejack works by syncing our vehicle id, then
 *
 * -------------
 * VEHICLE HACKS
 * -------------
 * In order to fuck vehicles, you need to be the driver. Some vulnerable vehicle vars:
 *  - Position
 *  - Speed (angular or lineal)
 *  - Health
 *  - Modding
 *  - Paintjob
 *  - Colours
 *
 * Most of vehicle hacks need to be driving the vehicle to send updates.
 * Best way is to check if player is in the same vehicle every time, or
 * teleport to other vehicles (maybe vehicle teleport).
 *
 * Players using illegal stuff:
 * - Player moved from their original vehicle (vehicle teleport)
 * - Vehicle goes so fast (vehicle speed hack)
 * - Unoccupied vehicles moved too far (maybe unnocupied vehicle teleport)
 * - Player moved so far from their last position without calling any callback (airbreake, flying, or teleport hack)
 * - Player got a weapon without SetPlayerWeapon or entered an armed vehicle (weapon hack)
 * - Player got armour without calling SetPlayerArmour (armour hack)
 * - Player repaired their vehicle.
 * - Player increased their ammo.
 * - Player increased their health or armour.
 * - Player filled their health or armour.
 *
 * Or sending fake info:
 * - Player called a callback but their state isn't normal. For example, called OnPlayerDeath when is already death
 * or OnPlayerSpawn without calling requestSpawn or beign manually spawned.
 * - Player sent invalid information to a callback. For example, sending fake weapons under OnPlayerWeaponShot or
 * using invalid killerid on OnPDeath. All parameters that can be changed by clients must be checked, for example
 * tunning, colours.
 *
 * For weapon hack, GetPlayerWeapon(Ammo)Slot should be hooked to avoid a client from adding itself a weapon without
 * carrying it.
 *
 * Every player has an array that is used to keep track of weapons that was sent to client by server.
 * There are some exceptions that may throw false positives:
 * - Planes gives you a parachute.
 * - Police cars gives you a
 *
 * This class is also responsible of disable SA:MP features that can make checks harder.
 * Stuff disabled:
 * - Interior enter-exits. DisableInteriorEnterExits is called at initialization.
 * - SetPlayerShopName. Calls to this function will be ignored.
 *
 * Some simple check detection:
 *  A jetpack without calling SetPlayerSpecialAction
 *  TODO: Server-side money.
 *
 * @author spell
 */
public class AnticheatListener implements CallbackListener {
    private final ACPlayer[] players;

    private final long[] lockFor1000ms = new long[SAMPConstants.MAX_PLAYERS];
    private final long[] lockFor500ms = new long[SAMPConstants.MAX_PLAYERS];
    private final long[] lockFor250ms = new long[SAMPConstants.MAX_PLAYERS];

    private final GameMode gm;
    private final Anticheat anticheat;

    private static final int SYNC_SECONDS_TO_RESYNC = 10;
    private static final int SYNC_SECONDS_TO_GIVEUP = 20;

    public AnticheatListener(Anticheat anticheat) {
        this.anticheat = anticheat;
        this.gm = anticheat.getGameMode();
        this.players = anticheat.getPlayers();

        SAMPFunctions.DisableInteriorEnterExits();
    }

    @Override
    public boolean OnPlayerConnect(int playerId) {
        if (players[playerId] != null) {
            return true;
        }

        if (SAMPFunctions.IsPlayerNPC(playerId)) {
            String ip = SAMPFunctions.GetPlayerIp(playerId);
            if (!ip.startsWith("127.0.0")) {
                if (reportCheat(playerId, AccurateLevel.HIGH,
                        "Connection as NPC from non-local IP " + ip)) {
                    return true;
                }
            }
        }

        ACPlayer player = new ACPlayer(playerId);
        player.setConnected(true);

        players[playerId] = player;
        return false;
    }

    @Override
    public boolean OnPlayerStateChange(int playerId, int newState, int oldState) {
        if (newState == PLAYER_STATE_DRIVER || newState == PLAYER_STATE_PASSENGER) {
            int vehicleId = SAMPFunctions.GetPlayerVehicleID(playerId);
            players[playerId].getVehicleId().setShouldBe(vehicleId);
            players[playerId].getVehicleId().sync();

            int model = SAMPFunctions.GetVehicleModel(vehicleId);
            if (ACUtils.isPlaneModel(model)) {
                players[playerId].setLegalWeapon(WEAPON_PARACHUTE, true);
            } else if (model == 457/*caddy*/) {
                players[playerId].setLegalWeapon(WEAPON_GOLFCLUB, true);
            } else if (model >= 596 && model <= 599 /*police cars*/) {
                players[playerId].setLegalWeapon(WEAPON_SHOTGUN, true);
            }
        } else {
            int lastVehicle = players[playerId].getVehicleId().getShouldBe();
            int lastVehicleModel = SAMPFunctions.GetVehicleModel(lastVehicle);

            players[playerId].getVehicleId().setShouldBe(0);
            players[playerId].getVehicleId().sync();

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
    public boolean OnPlayerSpawn(int playerId) {
        if (!players[playerId].isSpawnAllowed()) {
            // the only way of spawning is through SpawnPlayer (that will set this var to true)
            if (reportCheat(playerId, AccurateLevel.MEDIUM, "Spawning without SpawnPlayer")) {
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
            if (reportCheat(playerId, AccurateLevel.MEDIUM, "Spawning when is alive")) {
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

        // Sync vehicle id
        players[playerId].getVehicleId().setShouldBe(0);
        players[playerId].getVehicleId().sync();

        players[playerId].getPosition().setShouldBe(SAMPFunctions.GetPlayerPos(playerId));
        players[playerId].getPosition().sync();
        return false;
    }

    @Override
    public boolean OnPlayerDeath(int playerId, int killerId, int reason) {
        if (!players[playerId].isAlive()) {
            // it can be due to fake-kill or some desync.
            if (reportCheat(playerId, AccurateLevel.MEDIUM,
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
        int playerId = player.getId();
        int vehicleId = SAMPFunctions.GetPlayerVehicleID(playerId);
        SynchronizableProperty<Integer> vehicleVar = player.getVehicleId();
        if (!vehicleVar.isSynced()) {
            if (vehicleId == vehicleVar.getShouldBe()) {
                vehicleVar.sync();
            } else {
                // just desync them.
                return false;
            }
        } else {
            if (vehicleId != vehicleVar.getShouldBe()) {
                if (reportCheat(playerId, AccurateLevel.MEDIUM,
                        "Vehicle teleport")) {
                    return true;
                }
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

        for (int slot = 1; slot < 12; slot++) { // slots 0 and 12 are ignored
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

        if (checkOtherStuff(player)) {
            return false;
        }
        return false;
    }

    private boolean checkOtherStuff(ACPlayer player) {
        int playerId = player.getId();

        if (SAMPFunctions.GetPlayerSpecialAction(playerId) == SPECIAL_ACTION_USEJETPACK) {
            if (!player.isJetpackAllowed()) {
                if (reportCheat(playerId, AccurateLevel.HIGH, "Using a jetpack")) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean OnPlayerWeaponShot(int playerid, int weaponid, int hittype, int hitid, float fX, float fY, float fZ) {
        if (!players[playerid].isLegalWeapon(weaponid)) {
            if (reportCheat(playerid, AccurateLevel.HIGH,
                    "shooting with illegal weapon " + weaponid)) {
                return true;
            }
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
            return reportCheat(player, AccurateLevel.MEDIUM, "weapon is " + currentWeapon + " - should be " + weapon);
        }
        return false;
    }

    private boolean checkForWeaponSlotTimeout(ACPlayer player, int slot) {
        int unsyncSeconds = player.getWeaponInSlot(slot).increaseUnsyncedSecondsAndGet();
        if (unsyncSeconds == getUnsyncSecondsToTimeout()) {
            return reportUnsyncTimeout(player.getId(), "weapon slot unsynced");
        }
        return false;
    }

    private boolean checkWeapons(ACPlayer player) {
        int playerId = player.getId();
        for (int slot=0; slot < MAX_WEAPON_SLOTS; slot++) {
            if (slot == 0 || slot == 12) continue; // we don't care about the hand and explosives control
            // TODO: Check ammo only when the weapon uses ammo.
            // TODO: Perform checks only when needed. Study every case!

            SynchronizableProperty<Integer> weaponVar = player.getWeaponInSlot(slot);
            int weaponId = SAMPFunctions.GetPlayerWeaponSlot(playerId, slot);
            int ammo = SAMPFunctions.GetPlayerAmmoSlot(playerId, slot);
            int weaponShouldBe = weaponVar.getShouldBe();

            if (!weaponVar.isSynced()) {
                if (weaponId == weaponShouldBe) {
                    weaponVar.sync();
                } else {
                    int tries = weaponVar.increaseUnsyncedSecondsAndGet();
                    if (tries == SYNC_SECONDS_TO_GIVEUP) {
                        if (reportUnsyncTimeout(playerId, "weapon id is " + weaponId + " instead of " + weaponShouldBe)) {
                            return true;
                        }
                    }
                }
            } else {
                if (ammo != 0 && !players[playerId].isLegalWeapon(weaponId) && weaponId >= 22 && weaponId <= 38) {
                    if (reportCheat(playerId, AccurateLevel.HIGH,
                            "weapon hack: " + weaponId + " (ammo " + ammo + ")")) {
                        return true;
                    }
                }

                // no ammo!
                if (weaponShouldBe > 0 && (weaponId == 0 || ammo == 0)) {
                    weaponVar.setShouldBe(0);
                    player.setWeaponSlotAmmo(slot, 0);
                }

                int maximumAmmo = player.getWeaponSlotMaxAmmo(slot);

                // ammo values are 16-bit signed, so if the server gives the player more than ~32k bullets, the client
                // will return negative ammo values. This is why we check the maximum ammo don't exceed this.
                if (maximumAmmo < Anticheat.AMMO_TO_INVALIDATE_CHECKS && ACUtils.weaponSlotHoldsAmmo(slot)) {
                    if (ammo < -1 /*sometimes weapon ammo returns -1*/ || ammo > maximumAmmo) {
                        if (reportCheat(playerId,
                                AccurateLevel.HIGH,
                                "ammo hack (" + ammo + " but maximum " + maximumAmmo + " - w: " + weaponId + ")"
                        )) {
                            return true;
                        }
                    }
                }

                // player has a weapon
                if (weaponId > 0 && ammo != 0 && weaponShouldBe != weaponId) {
                    if (reportCheat(playerId, AccurateLevel.MEDIUM,
                            "Weapon hack: " + weaponId + " instead of " + weaponVar.getShouldBe())) {
                        return true;
                    }
                }

                if (System.currentTimeMillis() > players[playerId].getWeaponSlotAmmoLockTime(slot)) {
                    int ammoShouldBe = players[playerId].getWeaponSlotAmmo(slot);

                    if (weaponShouldBe != 0 && ammo > 0 && ammo > ammoShouldBe) {
                        reportCheat(playerId, AccurateLevel.LOW,
                                "Ammo is " + ammo + " instead of " + ammoShouldBe + ". Attempting to resync!");
                        SAMPFunctions.SetPlayerAmmo(playerId, weaponShouldBe, ammoShouldBe);
                    }

                    // if ammo is less than expected, player might used some ammo.
                    else if (ammo < ammoShouldBe) {
                        //scm(playerId, "you lost " + (ammoShouldBe - ammo) + " ammo! is " + ammo);
                        players[playerId].setWeaponSlotAmmo(slot, ammo);
                    }
                }
            }
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
            float toMtsPerSecond = ((float)msSinceLastCheck / 1000f);

            float distanceToWarn = state == PLAYER_STATE_DRIVER ? (toMtsPerSecond*30) : (toMtsPerSecond*10);
            float distanceToFlagAsTeleport = toMtsPerSecond * 300f;
            float distance = distanceBetweenPoints(position, positionShouldBe);

            if (distance > distanceToWarn) {
                float toSeconds = msSinceLastCheck / 1000f;

                AccurateLevel accurateLevel = distance > distanceToFlagAsTeleport ? AccurateLevel.MEDIUM : AccurateLevel.LOW;
                if (reportCheat(player.getId(), accurateLevel, "moved " + distance + " in " + toSeconds + " secs.")) {
                    return true;
                }
            }
        }

        player.getPosition().setShouldBe(position);
        return false;
    }

    private boolean checkForPositionTimeout(ACPlayer player) {
        int unsyncSeconds = player.getPosition().increaseUnsyncedSecondsAndGet();
        if (unsyncSeconds == getUnsyncSecondsToTimeout()) {
            return reportUnsyncTimeout(player.getId(), "position unsynced");
        } else if (unsyncSeconds == getUnsyncSecondsToResync()) {
            float[] posShouldBe = player.getPosition().getShouldBe();
            SAMPFunctions.SetPlayerPos(player.getId(), posShouldBe[0], posShouldBe[1], posShouldBe[2]);
        }
        return false;
    }
    
    private static float distanceBetweenPoints(float[] xyz1, float[] xyz2) {
        float dx = xyz1[0] - xyz2[0];
        float dy = xyz1[1] - xyz2[1];
        float dz = xyz1[2] - xyz2[2];
        return (float)Math.sqrt(dx*dx + dy*dy + dz*dz);
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
            if (reportCheat(player, AccurateLevel.HIGH, "Invalid health: " + currentHealth)) {
                return true;
            }
        }

        int healthShouldBe = player.getHealth().getShouldBe().intValue();
        if (currentHealth > healthShouldBe) {
            SAMPFunctions.SetPlayerHealth(player.getId(), healthShouldBe);
            reportCheat(player, AccurateLevel.LOW,
                    "Health is " + currentHealth + " - should be " + healthShouldBe);
        }
        return false;
    }

    private boolean checkForHealthTimeout(ACPlayer player) {
        int unsyncSeconds = player.getHealth().increaseUnsyncedSecondsAndGet();
        if (unsyncSeconds == getUnsyncSecondsToTimeout()) {
            return reportUnsyncTimeout(player.getId(), "health unsynced");
        } else if (unsyncSeconds == getUnsyncSecondsToResync()) {
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
            if (reportCheat(playerId, AccurateLevel.HIGH, "Armour hack (" + currentArmour + ")")) {
                return true;
            }
        }
        if (currentArmour < 0 || currentArmour > 100) {
            if (reportCheat(playerId, AccurateLevel.HIGH, "Invalid armour: " + currentArmour)) {
                return true;
            }
        }
        if (currentArmour > armourShouldBe) {
            SAMPFunctions.SetPlayerArmour(playerId, armourShouldBe);
            reportCheat(playerId, AccurateLevel.LOW,
                    "Armour increased from " + armourShouldBe + " to " + currentArmour);
        }
        return false;
    }

    private boolean checkForArmourTimeout(ACPlayer player) {
        int unsyncSecs = player.getArmour().increaseUnsyncedSecondsAndGet();
        if (unsyncSecs == getUnsyncSecondsToTimeout()) {
            return reportUnsyncTimeout(player.getId(), "armour unsynced");
        } else if (unsyncSecs == getUnsyncSecondsToResync()) {
            SAMPFunctions.SetPlayerArmour(player.getId(), player.getArmour().getShouldBe());
        }
        return false;
    }

    private void scm(int id, String msg) {
        SAMPFunctions.SendClientMessage(id, -1, msg);
    }

    @Override
    public boolean OnPlayerTakeDamage(int playerid, int issuerId, float damage, int weaponid, int bodypart) {
        // TODO: VALIDATE

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
    public boolean OnPlayerRequestSpawn(int playerId) {
        // This SA-MP spawn system is also disabled on JSAMP.
        return true;
    }

    private boolean reportCheat(ACPlayer player, AccurateLevel level, String description) {
        return gm.onAnticheatEvent(player.getId(), new CheatEvent(level, description));
    }

    private boolean reportCheat(int playerId, AccurateLevel level, String description) {
        return gm.onAnticheatEvent(playerId, new CheatEvent(level, description));
    }

    private boolean reportUnsyncTimeout(int playerId, String description) {
        return gm.onAnticheatEvent(playerId, new UnsyncEvent(description));
    }

    private int getUnsyncSecondsToTimeout() {
        return anticheat.getUnsyncSecondsToTimeout();
    }

    private int getUnsyncSecondsToResync() {
        return getUnsyncSecondsToTimeout() / 2;
    }
}