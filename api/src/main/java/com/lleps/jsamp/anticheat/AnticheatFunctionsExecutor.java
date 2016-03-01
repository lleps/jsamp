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
import com.lleps.jsamp.SAMPFunctions;
import com.lleps.jsamp.SAMPFunctionsExecutor;

import static com.lleps.jsamp.SAMPConstants.*;

/**
 * @author spell
 */
public class AnticheatFunctionsExecutor implements SAMPFunctionsExecutor {
    private final Anticheat ac;
    private final ACPlayer[] players;

    public AnticheatFunctionsExecutor(Anticheat ac) {
        this.ac = ac;
        this.players = ac.getPlayers();
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

        players[playerid].getVehicleId().setShouldBe(vehicleid);
        players[playerid].getVehicleId().unsync();

        return SAMPFunctions.PutPlayerInVehicle(playerid, vehicleid, seatid);
    }

    @Override
    public boolean GivePlayerWeapon(int playerid, int weaponid, int ammo) {
        checkForValidPlayerId(playerid);
        Preconditions.checkArgument(weaponid >= 0 && weaponid < 47, "invalid weapon id: " + weaponid);

        int slot = ACUtils.getWeaponSlot(weaponid);
        players[playerid].getWeaponInSlot(slot).setShouldBe(weaponid);
        players[playerid].getWeaponInSlot(slot).unsync();

        players[playerid].setLegalWeapon(weaponid, true);
        if (weaponid == WEAPON_SATCHEL) {
            // legalize detonator
            players[playerid].setLegalWeapon(WEAPON_BOMB, true);
        }

        // Block bullet check times to wait player to sync
        players[playerid].setWeaponSlotAmmoLockTime(slot, System.currentTimeMillis() + 5_000);

        // Set ammo to what it should be. Note that this value may be desynced.
        players[playerid].setWeaponSlotAmmo(slot, players[playerid].getWeaponSlotAmmo(slot) + ammo);

        // This var holds maximum ammo given by server
        if (ammo >= 0) {
            players[playerid].setWeaponSlotMaxAmmo(slot,
                    players[playerid].getWeaponSlotMaxAmmo(slot) + ammo);
        } else {
            // negative ammo values invalidate "invalid ammo" checks, because players can get negative ammo.
            // so we will set maximum ammo to a very high value to avoid ammo checks.
            players[playerid].setWeaponSlotMaxAmmo(slot, Anticheat.AMMO_TO_INVALIDATE_CHECKS);
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
            // In TP hacks, if a cheater gets a kick and come back they'll be on cheated pos
            return players[playerid].getPosition().getShouldBe();
        }
        return SAMPFunctions.GetPlayerPos(playerid);
    }

    @Override
    public boolean SetPlayerAmmo(int playerid, int weaponid, int ammo) {
        checkForValidPlayerId(playerid);

        int slot = ACUtils.getWeaponSlot(weaponid);
        SynchronizableProperty<Integer> weaponAtThisSlot = players[playerid].getWeaponInSlot(slot);
        if (weaponAtThisSlot.isSynced() && weaponAtThisSlot.getShouldBe() == weaponid) {
            players[playerid].setWeaponSlotAmmoLockTime(slot, System.currentTimeMillis() + 5_000);
            players[playerid].setWeaponSlotAmmo(slot, ammo);

            if (ammo > players[playerid].getWeaponSlotMaxAmmo(slot)) {
                players[playerid].setWeaponSlotMaxAmmo(slot, ammo);
            }

            if (ammo < 0) {
                // negative ammo values invalidate "invalid ammo" checks, because players can get negative ammo.
                players[playerid].setWeaponSlotMaxAmmo(slot, Anticheat.AMMO_TO_INVALIDATE_CHECKS);
            }
            return SAMPFunctions.SetPlayerAmmo(playerid, weaponid, ammo);
        }
        return false;
    }

    @Override
    public int GetPlayerWeaponSlot(int playerid, int slot) {
        checkForValidPlayerId(playerid);
        Preconditions.checkArgument(slot >= 0 && slot < MAX_WEAPON_SLOTS, "invalid slot: " + slot);

        return players[playerid].getWeaponInSlot(slot).getShouldBe();
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

    private void updateAmmoVariable(int slot, int playerId) {
        int ammo = SAMPFunctions.GetPlayerWeaponSlot(playerId, slot);
        int shouldBe = players[playerId].getWeaponSlotAmmo(slot);
        if (ammo < shouldBe) {
            shouldBe = ammo;
        }
        players[playerId].setWeaponSlotAmmo(slot, shouldBe);
    }

    @Override
    public int GetPlayerAmmoSlot(int playerid, int slot) {
        return 0;
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
}
