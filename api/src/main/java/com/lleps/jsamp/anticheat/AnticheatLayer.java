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
import com.lleps.jsamp.SAMPFunctionsExecutor;
import com.lleps.jsamp.anticheat.event.AccurateLevel;
import com.lleps.jsamp.anticheat.event.IllegalStateCheat;
import com.lleps.jsamp.gamemode.CallbackListener;
import com.lleps.jsamp.gamemode.GameMode;

import static com.lleps.jsamp.SAMPConstants.*;

/**
 * This class will perform anticheat checks. if a player is suspected to be cheater, an AnticheatEvent will
 * be generated and dispatched to GameMode's onAnticheatEvent method, so the action should be taken here.
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
 *
 *
 * Players using illegal stuff:
 * - Player moved from their original vehicle (vehicle teleport)
 * - Vehicle goes so fast (vehicle speed hack)
 * - Unoccupied vehicles moves too far (maybe unnocupied vehicle teleport)
 * - Player moved so far from their last position without calling any callback (airbreake, flying, or teleport hack)
 * - Player got a weapon without SetPlayerWeapong or entered an armed vehicle (weapon hack)
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
 * using invalid killerid on OPDeath. All parameters that can be changed by clients must be checked, for example
 * tunning, colours.
 *
 * For weapon hack, GetPlayerWeapon(Ammo)Slot should be hooked for avoid a client from adding itself a weapon without
 * carrying it.
 *
 * Every player has an array that is used for keep track of weapons that was sent to client by server.
 * There are some exceptions that may throw false positives:
 * - Planes gives you a parachute.
 * - Police cars gives you a
 *
 * This class is also responsible of disable SA:MP features that can make checks difficult.
 * Stuff disabled:
 * - Interior enter-exits. DisableInteriorEnterExits is called at initialization.
 * - SetPlayerShopName. Calls to this function will be ignored.
 *
 * -----------------
 *  ANTI WEAPON HACK
 *  ----------------
 * Stores in a variable which weapons were sent to a player. Basically, if player has a weapon but
 * the variable is false, is considered cheater.
 * There are some false-positives:
 * - When player enters a caddy, they'll get a golf club.
 * - When player enters a plane, they'll get a parachute.
 * - When player enters a police car, they'll get a shotgun.
 * - When player has a weapon and reconnect without exiting from the game (due to GMX or ip-ban), GetPlayerWeapon
 *   will return their last weapon, but with zero ammo.
 * - When SetPlayerSpawnInfo is used, player can get weapons too. This is fixed by ignoring weapon parameters
 *   in SetPlayerSpawnInfo (hooking the function).
 * - On Ammunations, player can get client-side weapons.
 * - Getting pickups players can get weapons too.
 *
 * There is a way to by-pass it: If you get a weapon one time, you'll be able to hack it.
 * Fix: Make a new anticheat using a flag "weapons synced" then syncing and unsyncing. Not needed yet!
 *
 * TODO: Players connecting as NPCS.
 *
 * TODO: Built-in support for creating interiors.
 *
 * You may note this code
 * @author spell
 */
public class AnticheatLayer implements CallbackListener, SAMPFunctionsExecutor {
    private PlayerSlot[] playerSlots = new PlayerSlot[SAMPConstants.MAX_PLAYERS];
    private GameMode gameMode;

    public AnticheatLayer(GameMode gameMode) {
        this.gameMode = gameMode;

        // Default interiors makes difficult to validate players, because of all client-side stuff like
        // ammunations or pizza stacks.
        DisableInteriorEnterExits();

        // Initialize playerSlot array.
        for (int i = 0; i < playerSlots.length; i++) {
            playerSlots[i] = new PlayerSlot();
            playerSlots[i].resetAll();
        }
    }

    @Override
    public boolean OnPlayerConnect(int playerId) {
        playerSlots[playerId].setConnected(true);
        return false;
    }

    @Override
    public boolean OnPlayerSpawn(int playerId) {
        if (!playerSlots[playerId].isLegalSpawned()) {
            // the only way of spawning is through SpawnPlayer (that will set this var to true)
            if (gameMode.onAnticheatEvent(playerId,
                    new IllegalStateCheat(AccurateLevel.HIGH, "Spawning without SpawnPlayer"))) {
                return true;
            }
        }
        // Player spawned when is already alive? SpawnPlayer sets alive to false..
        // A possible false-positive:
        // SpawnPlayer is called two times. The first message is received, then player spawns and alive sets to true.
        // The second SpawnPlayer is received, but player has alive = true, so the player gets kicked.
        if (playerSlots[playerId].isAlive()) {
            if (gameMode.onAnticheatEvent(playerId,
                    new IllegalStateCheat(AccurateLevel.MEDIUM, "Spawning when is alive."))) {
                return true;
            }
        }
        playerSlots[playerId].setAlive(true);
        return false;
    }

    @Override
    public boolean OnPlayerDeath(int playerId, int killerId, int reason) {
        if (!playerSlots[playerId].isAlive()) { // Player death, but is still death.
            // it can be due to fake-kill or some desync.
            if (gameMode.onAnticheatEvent(playerId, new IllegalStateCheat(AccurateLevel.MEDIUM,
                    "Player die when is not alive."))) {
                return true;
            }
        }
        playerSlots[playerId].setAlive(false);
        return false;
    }

    @Override
    public boolean OnPlayerDisconnect(int playerId, int reason) {
        playerSlots[playerId].resetAll();
        return false;
    }

    @Override
    public boolean OnPlayerUpdate(int playerId) {
        if (!playerSlots[playerId].isAlive()) {
            return true; // Discard update if player is not alive.
        }


        return false;
    }

    private boolean validPlayer(int id) {
        return id >= 0 && id < MAX_PLAYERS && playerSlots[id].isConnected();
    }

    @Override
    public boolean OnPlayerRequestSpawn(int playerId) {
        // This SA-MP spawn system is also disabled on JSAMP.
        return true;
    }

    @Override
    public boolean SetPlayerShopName(int playerid, String shopname) {
        // Disabled because makes checks so difficult. Same as DisableInteriorEnterExits
        return false;
    }

    @Override
    public boolean SpawnPlayer(int playerid) {
        if (validPlayer(playerid)) {
            playerSlots[playerid].setLegalSpawned(true);
            playerSlots[playerid].setAlive(false); // Set to false in case player is already spawned and will be respawned.
            return SAMPFunctionsExecutor.super.SpawnPlayer(playerid);
        }
        return false;
    }
}