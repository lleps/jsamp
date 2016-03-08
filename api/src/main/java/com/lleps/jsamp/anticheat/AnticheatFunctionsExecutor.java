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

import com.google.common.base.Preconditions;
import com.lleps.jsamp.FunctionAccess;
import com.lleps.jsamp.SAMPFunctions;
import com.lleps.jsamp.SAMPFunctionsExecutor;
import org.apache.commons.lang3.RandomUtils;

import static com.lleps.jsamp.SAMPConstants.*;

/**
 * @author spell
 */
public class AnticheatFunctionsExecutor implements SAMPFunctionsExecutor {
    private final Anticheat ac;
    private final ACPlayer[] players;
    private final ACVehicle[] vehicles;

    public AnticheatFunctionsExecutor(Anticheat ac) {
        this.ac = ac;
        this.players = ac.getPlayers();
        this.vehicles = ac.getVehicles();
    }

    @Override
    public boolean SetPlayerShopName(int playerid, String shopname) {
        throw new UnsupportedOperationException("SetPlayerShopName was disabled because it may produce client-side " +
                "effects such as health refill on pizza stacks or weapons in ammu-nation");
    }

    @Override
    public boolean SpawnPlayer(int playerid) {
        checkForValidPlayerId(playerid);

        players[playerid].setSpawnAllowed(true);
        players[playerid].setAlive(false); // Set to false in case player is already spawned and will be respawned.
        return SAMPFunctions.SpawnPlayer(playerid);
    }

    @Override
    public boolean SetPlayerHealth(int playerid, float health) {
        checkForValidPlayerId(playerid);
        Preconditions.checkArgument(health >= 0 && health <= 100, "invalid health: " + health + ". Valid values: [0-100]");

        players[playerid].getHealth().setShouldBe(health);
        players[playerid].getHealth().unsync();

        return SAMPFunctions.SetPlayerHealth(playerid, health);
    }

    @Override
    public float GetPlayerHealth(int playerid) {
        checkForValidPlayerId(playerid);
        return players[playerid].getHealth().getShouldBe();
    }

    @Override
    public boolean SetPlayerArmour(int playerid, float armour) {
        checkForValidPlayerId(playerid);
        Preconditions.checkArgument(armour >= 0 && armour <= 100, "invalid armour: " + armour + ". Valid values: [0-100]");

        if (armour > 99) armour = 99; // Impossible to get server-side 100 armour. If you get it, you're most likely cheating.

        if (armour > 0) {
            players[playerid].setArmourAllowed(true);
        }

        players[playerid].getArmour().setShouldBe(armour);
        players[playerid].getArmour().unsync();

        return SAMPFunctions.SetPlayerArmour(playerid, armour);
    }

    @Override
    public float GetPlayerArmour(int playerid) {
        checkForValidPlayerId(playerid);

        return players[playerid].getArmour().getShouldBe();
    }

    @Override
    public boolean SetPlayerSpecialAction(int playerid, int actionid) {
        checkForValidPlayerId(playerid);

        if (actionid == SPECIAL_ACTION_USEJETPACK) {
            players[playerid].setJetpackAllowed(true);
        }

        return SAMPFunctions.SetPlayerSpecialAction(playerid, actionid);
    }

    @Override
    public boolean PutPlayerInVehicle(int playerid, int vehicleid, int seatid) {
        checkForValidPlayerId(playerid);
        checkForValidVehicleId(vehicleid);

        boolean seatOccupied = false;
        for (int i = 0, j = SAMPFunctions.GetPlayerPoolSize(); i <= j; i++) {
            if (ac.isConnected(i) && players[i].getVehicleId().getShouldBe() == vehicleid) {
                if (SAMPFunctions.GetPlayerVehicleSeat(i) == seatid) {
                    seatOccupied = true;
                }
            }
        }

        if (!seatOccupied) {
            players[playerid].setEnteringVehicleId(vehicleid);
            players[playerid].getVehicleId().setShouldBe(vehicleid);
            players[playerid].getVehicleId().unsync();

            float[] vehiclePos = SAMPFunctions.GetVehiclePos(vehicleid);
            players[playerid].getPosition().setShouldBe(vehiclePos);
            players[playerid].getPosition().unsync();

            return SAMPFunctions.PutPlayerInVehicle(playerid, vehicleid, seatid);
        }
        return false;
    }

    @Override
    public boolean SetVehicleHealth(int vehicleid, float health) {
        checkForValidVehicleId(vehicleid);
        Preconditions.checkArgument(health >= 0 && health <= 1000f, "invalid health: " + health);

        SAMPFunctions.SetVehicleHealth(vehicleid, health);

        for (int i = 0, max = SAMPFunctions.GetPlayerPoolSize(); i <= max; i++) {
            if (ac.isConnected(i) && players[i].getVehicleId().getShouldBe() == vehicleid) {
                players[i].getVehicleHealth().setShouldBe(health);
                players[i].getVehicleHealth().unsync();
            }
        }
        return false;
    }

    @Override
    public boolean SetVehicleVelocity(int vehicleid, float X, float Y, float Z) {
        checkForValidVehicleId(vehicleid);

        float speed = ACUtils.get3DSpeed(X, Y, Z);
        float maxSpeed = ACUtils.getVehicleModelMaxSpeed(SAMPFunctions.GetVehicleModel(vehicleid));
        if (speed > maxSpeed) {
            X = maxSpeed * X / speed;
            Y = maxSpeed * Y / speed;
            Z = maxSpeed * Z / speed;
        }
        return SAMPFunctions.SetVehicleVelocity(vehicleid, X, Y, Z);
    }

    @Override
    public boolean SetVehiclePos(int vehicleid, float x, float y, float z) {
        checkForValidVehicleId(vehicleid);

        SAMPFunctions.SetVehiclePos(vehicleid, x, y, z);

        float[] positionArray = null;
        for (int i = 0, max = SAMPFunctions.GetPlayerPoolSize(); i <= max; i++) {
            if (ac.isConnected(i)) {
                if (players[i].getVehicleId().getShouldBe() == vehicleid) {
                    if (positionArray == null) positionArray = new float[]{x, y, z};
                    players[i].getPosition().setShouldBe(positionArray);
                    players[i].getPosition().unsync();
                } else if (SAMPFunctions.GetPlayerSurfingVehicleID(i) == vehicleid) {
                    float[] playerPos = SAMPFunctions.GetPlayerPos(i);
                    // Prevent players surfing from teleporting with vehicles.
                    FunctionAccess.SetPlayerPos(i, playerPos[0], playerPos[1], playerPos[2]);
                }
            }
        }
        return false;
    }

    @Override
    public int GetPlayerVehicleID(int playerid) {
        checkForValidPlayerId(playerid);
        return players[playerid].getVehicleId().getShouldBe();
    }

    @Override
    public int CreateVehicle(int vehicletype, float x, float y, float z, float rotation, int color1, int color2, int respawn_delay, boolean addsiren) {
        if (color1 == -1) color1 = RandomUtils.nextInt(0, 128);
        if (color2 == -1) color2 = RandomUtils.nextInt(0, 128);

        int id = SAMPFunctions.CreateVehicle(vehicletype, x, y, z, rotation, color1, color2, respawn_delay, addsiren);
        if (id != INVALID_VEHICLE_ID) {
            initVehicle(id, color1, color2);
        }
        return id;
    }

    @Override
    public int AddStaticVehicle(int modelid, float spawn_x, float spawn_y, float spawn_z, float z_angle, int color1, int color2) {
        if (color1 == -1) color1 = RandomUtils.nextInt(0, 128);
        if (color2 == -1) color2 = RandomUtils.nextInt(0, 128);

        int id = SAMPFunctions.AddStaticVehicle(modelid, spawn_x, spawn_y, spawn_z, z_angle, color1, color2);
        if (id != INVALID_VEHICLE_ID) {
            initVehicle(id, color1, color2);
        }
        return id;
    }

    @Override
    public int AddStaticVehicleEx(int modelid, float spawn_x, float spawn_y, float spawn_z, float z_angle, int color1, int color2, int respawn_delay, boolean addsiren) {
        if (color1 == -1) color1 = RandomUtils.nextInt(0, 128);
        if (color2 == -1) color2 = RandomUtils.nextInt(0, 128);

        int id = SAMPFunctions.AddStaticVehicleEx(modelid, spawn_x, spawn_y, spawn_z, z_angle, color1, color2, respawn_delay, addsiren);
        if (id != INVALID_VEHICLE_ID) {
            initVehicle(id, color1, color2);
        }
        return id;
    }

    private void initVehicle(int vehicleId, int color1, int color2) {
        vehicles[vehicleId] = new ACVehicle(vehicleId, color1, color2);
    }

    @Override
    public boolean ChangeVehicleColor(int vehicleid, int color1, int color2) {
        checkForValidVehicleId(vehicleid);
        vehicles[vehicleid].setColor1(color1);
        vehicles[vehicleid].setColor2(color2);
        SAMPFunctions.ChangeVehicleColor(vehicleid, color1, color2);
        return false;
    }

    @Override
    public boolean DestroyVehicle(int vehicleid) {
        checkForValidVehicleId(vehicleid);
        SAMPFunctions.DestroyVehicle(vehicleid);
        vehicles[vehicleid] = null;
        return false;
    }

    @Override
    public boolean SetVehicleParamsEx(int vehicleid, boolean engine, boolean lights, boolean alarm, boolean doors, boolean bonnet, boolean boot, boolean objective) {
        checkForValidVehicleId(vehicleid);
        vehicles[vehicleid].setDoorsLocked(doors);
        SAMPFunctions.SetVehicleParamsEx(vehicleid, engine, lights, alarm, doors, bonnet, boot, objective);
        return false;
    }

    @Override
    public boolean RemovePlayerFromVehicle(int playerid) {
        checkForValidPlayerId(playerid);

        players[playerid].getVehicleId().setShouldBe(0);
        players[playerid].getVehicleId().unsync();

        return SAMPFunctions.RemovePlayerFromVehicle(playerid);
    }

    @Override
    public boolean GivePlayerMoney(int playerid, int money) {
        checkForValidPlayerId(playerid);
        SAMPFunctions.GivePlayerMoney(playerid, money);
        players[playerid].setMoney(players[playerid].getMoney() + money);
        return false;
    }

    @Override
    public boolean ResetPlayerMoney(int playerid) {
        checkForValidPlayerId(playerid);
        SAMPFunctions.ResetPlayerMoney(playerid);
        players[playerid].setMoney(0);
        return false;
    }

    @Override
    public int GetPlayerMoney(int playerid) {
        checkForValidPlayerId(playerid);
        return players[playerid].getMoney();
    }

    @Override
    public boolean GivePlayerWeapon(int playerid, int weaponid, int ammo) {
        checkForValidPlayerId(playerid);
        Preconditions.checkArgument(weaponid >= 0 && weaponid < 47, "invalid weapon id: " + weaponid);

        int slot = ACUtils.getWeaponSlot(weaponid);
        players[playerid].getWeaponInSlot(slot).setShouldBe(weaponid);
        players[playerid].getWeaponInSlot(slot).unsync();

        int currentMaxAmmo = players[playerid].getWeaponSlotMaxAmmo(slot);
        int newMaxAmmo = currentMaxAmmo + ammo;

        if (ammo < 0 || newMaxAmmo > 32_000) {
            players[playerid].setInvalidAmmoPossible(slot, true);
        }

        if (ammo >= 0) {
            players[playerid].setWeaponSlotMaxAmmo(slot, newMaxAmmo);
        }

        return SAMPFunctions.GivePlayerWeapon(playerid, weaponid, ammo);
    }

    @Override
    public boolean Kick(int playerid) {
        checkForValidPlayerId(playerid);
        players[playerid].setKickedOrBanned(true);
        return SAMPFunctions.Kick(playerid);
    }

    @Override
    public boolean Ban(int playerid) {
        checkForValidPlayerId(playerid);
        players[playerid].setKickedOrBanned(true);
        return SAMPFunctions.Ban(playerid);
    }
    @Override
    public boolean BanEx(int playerid, String reason) {
        checkForValidPlayerId(playerid);
        players[playerid].setKickedOrBanned(true);
        return SAMPFunctions.BanEx(playerid, reason);
    }

    @Override
    public float[] GetPlayerPos(int playerid) {
        checkForValidPlayerId(playerid);
        if (players[playerid].isKickedOrBanned()) {
            // In TP hacks, if a cheater gets kicked and come back they'll be on cheated pos
            return players[playerid].getPosition().getShouldBe();
        }
        return SAMPFunctions.GetPlayerPos(playerid);
    }

    @Override
    public boolean SetPlayerAmmo(int playerid, int weaponid, int ammo) {
        checkForValidPlayerId(playerid);
        Preconditions.checkArgument(weaponid >= 0 && weaponid < 47, "invalid weaponid: " + weaponid);
        int slot = ACUtils.getWeaponSlot(weaponid);

        if (players[playerid].getWeaponInSlot(slot).getShouldBe() == weaponid) {

            if (ammo < 0) {
                players[playerid].setInvalidAmmoPossible(slot, true);
            }

            int currentMaxAmmo = players[playerid].getWeaponSlotMaxAmmo(slot);
            if (ammo >= currentMaxAmmo) {
                players[playerid].setWeaponSlotMaxAmmo(slot, ammo);
            }

            SAMPFunctions.SetPlayerAmmo(playerid, weaponid, ammo);
        }
        return true;
    }

    @Override
    public int GetPlayerWeapon(int playerid) {
        checkForValidPlayerId(playerid);

        int currentWeapon = SAMPFunctions.GetPlayerWeapon(playerid);
        if (currentWeapon == 0) {
            return 0;
        }
        int currentWeaponSlot = ACUtils.getWeaponSlot(currentWeapon);
        return players[playerid].getWeaponInSlot(currentWeaponSlot).getShouldBe();
    }

    @Override
    public boolean ResetPlayerWeapons(int playerid) {
        checkForValidPlayerId(playerid);

        for (int slot = 0; slot < MAX_WEAPON_SLOTS; slot++) {
            players[playerid].getWeaponInSlot(slot).setShouldBe(0);
            players[playerid].getWeaponInSlot(slot).unsync();
        }
        return SAMPFunctions.ResetPlayerWeapons(playerid);
    }

    @Override
    public int GetPlayerAmmo(int playerid) {
        checkForValidPlayerId(playerid);

        int weapon = SAMPFunctions.GetPlayerWeapon(playerid);
        if (weapon != 0) {
            int ammo = SAMPFunctions.GetPlayerAmmo(playerid);
            int slot = ACUtils.getWeaponSlot(weapon);
            // note that ammo cannot be higher than AMMO_TO_INVALIDATE!
            if (ACUtils.weaponSlotHoldsAmmo(slot) && ammo > players[playerid].getWeaponSlotMaxAmmo(slot)) {
                return players[playerid].getWeaponSlotMaxAmmo(slot);
            }
            return ammo;
        }
        return 0;
    }

    @Override
    public int GetPlayerAmmoSlot(int playerid, int slot) {
        checkForValidPlayerId(playerid);
        Preconditions.checkArgument(slot >= 0 && slot <= 12, "invalid slot: " + slot);
        int ammo = SAMPFunctions.GetPlayerAmmoSlot(playerid, slot);
        // note that ammo cannot be higher than AMMO_TO_INVALIDATE!
        if (ACUtils.weaponSlotHoldsAmmo(slot) && ammo > players[playerid].getWeaponSlotMaxAmmo(slot)) {
            return players[playerid].getWeaponSlotMaxAmmo(slot);
        }
        return ammo;
    }

    @Override
    public int GetPlayerWeaponSlot(int playerid, int slot) {
        checkForValidPlayerId(playerid);
        Preconditions.checkArgument(slot >= 0 && slot < MAX_WEAPON_SLOTS, "invalid slot: " + slot);

        return players[playerid].getWeaponInSlot(slot).getShouldBe();
    }

    @Override
    public boolean SetSpawnInfo(int playerid, int team, int skin, float x, float y, float z, float rotation, int weapon1, int weapon1_ammo, int weapon2, int weapon2_ammo, int weapon3, int weapon3_ammo) {
        checkForValidPlayerId(playerid);

        return SAMPFunctions.SetSpawnInfo(playerid, team, skin, x, y, z, rotation, 0, 0, 0, 0, 0, 0);
    }


    @Override
    public boolean SetPlayerPos(int playerid, float x, float y, float z) {
        checkForValidPlayerId(playerid);

        players[playerid].getPosition().setShouldBe(new float[] {x, y, z});
        players[playerid].getPosition().unsync();

        return SAMPFunctions.SetPlayerPos(playerid, x, y, z);
    }

    @Override
    public boolean SetPlayerPosFindZ(int playerid, float x, float y, float z) {
        // there is no way to know which z pos will be set to player, so we will use standard SetPlayerPos
        // it can be done by waiting x y for sync, then check z. However, it requires code that i don't want to write.
        return this.SetPlayerPos(playerid, x, y, z);
    }

    // These functions are disabled in JSAMP.
    @Override
    public int AddPlayerClass(int modelid, float spawn_x, float spawn_y, float spawn_z, float z_angle, int weapon1, int weapon1_ammo, int weapon2, int weapon2_ammo, int weapon3, int weapon3_ammo) {
        throw new UnsupportedOperationException("AddPlayerClass (and all built-in SA:MP class system) was disabled by anticheat," +
                " because they make checks harder.");
    }

    @Override
    public int AddPlayerClassEx(int teamid, int modelid, float spawn_x, float spawn_y, float spawn_z, float z_angle, int weapon1, int weapon1_ammo, int weapon2, int weapon2_ammo, int weapon3, int weapon3_ammo) {
        throw new UnsupportedOperationException("AddPlayerClass (and all built-in SA:MP class system) was disabled by anticheat," +
                " because they make checks harder.");
    }

    @Override
    public int CreatePickup(int model, int type, float x, float y, float z, int virtualworld) {
        Preconditions.checkArgument(type == 1 || type == 14, "Only types 1 and 14 are supported, because others " +
                " may have client-side effects that would cause false positives.");

        return SAMPFunctions.CreatePickup(model, type, x, y, z, virtualworld);
    }

    @Override
    public int AddStaticPickup(int model, int type, float x, float y, float z, int virtualworld) {
        this.CreatePickup(model, type, x, y, z, virtualworld);
        return 0;
    }

    @Override
    public boolean IsPlayerConnected(int playerid) {
        return ac.isConnected(playerid);
    }

    private void checkForValidPlayerId(int playerId) {
        Preconditions.checkArgument(ac.isConnected(playerId), "invalid or disconnected player id: " + playerId);
    }

    private void checkForValidVehicleId(int vehicleId) {
        Preconditions.checkArgument(ac.isValidVehicle(vehicleId), "invalid vehicle: " + vehicleId);
    }
}
