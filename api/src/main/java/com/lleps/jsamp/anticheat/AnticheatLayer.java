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

import com.lleps.jsamp.FunctionAccess;
import com.lleps.jsamp.SAMPConstants;
import com.lleps.jsamp.SAMPFunctionsExecutor;
import com.lleps.jsamp.gamemode.CallbackListener;
import static com.lleps.jsamp.SAMPConstants.*;

/**
 * TODO: Document this.
 * TODO: Define which checks will be included.
 *
 * This class should be the first processing of a server event. Will discard false information sent by a client,
 * for example, invalid weapon id at OnPlayerWeaponShot.
 *
 * When something goes wrong (for example, cheat detection, invalid information sent by a client, or simply
 * a player desync), this class will need to report events to GameMode class.
 *
 * This class will not ban or kick any player, will simply report an event to GameMode, when an action should
 * be taken.
 *
 * However, some actions may require a response to determine if the player should be unsynced or not (for
 * example, an event detected in OnPlayerUpdate may desync the update if player was kicked or banned. But
 * others checks will simply do nothing.
 *
 * Things goes better when you try to do anticheat checks that checks if a client is not acting as a normal one.
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
 *
 * Weapon hack:
 * Every player has an array that is used for keep track of weapons that was sent to client by server.
 * There are some exceptions that may throw false positives:
 * - Planes gives you a parachute.
 * - Police cars gives you a 
 * @author spell
 */
public class AnticheatLayer implements CallbackListener, SAMPFunctionsExecutor {
    class PlayerData {
        boolean hasWeapons[] = new boolean[47];
    }

    PlayerData[] playerData = new PlayerData[SAMPConstants.MAX_PLAYERS];

    @Override
    public boolean OnPlayerConnect(int playerId) {
        playerData[playerId] = new PlayerData();
        return false;
    }

    @Override
    public boolean OnPlayerUpdate(int playerId) {

        return false;
    }
}