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
package com.lleps.test;

import com.lleps.jsamp.FunctionAccess;
import com.lleps.jsamp.SAMPFunctions;
import com.lleps.jsamp.player.Player;

/**
 * This test proves that all get functions (that are hooked to make them server-side) works property.
 * Commands are equal to function to check, but all letters are lowercase.
 * All commands will get the sender as the player id, functions that need extra params should be passed as
 * normal command params.
 * @author spell
 */
public class AnticheatGetTests implements CommandListener {
    @Override
    public boolean onCommand(Player player, String command, String[] args) {
        if (command.equals("/getplayerhealth")) {
            player.sendMessage("health: " + FunctionAccess.GetPlayerHealth(player.getId()));
            return true;
        }
        if (command.equals("/getplayerarmour")) {
            player.sendMessage("armour: " + FunctionAccess.GetPlayerArmour(player.getId()));
            return true;
        }
        if (command.equals("/getplayerweaponslot")) {
            int slot = Integer.parseInt(args[0]);
            player.sendMessage("getplayerweaponslot("+slot+": " + FunctionAccess.GetPlayerWeaponSlot(player.getId(), slot));
            return true;
        }
        if (command.equals("/getplayerweapon")) {
            player.sendMessage("getplayerweapon: " + FunctionAccess.GetPlayerWeapon(player.getId()));
            return true;
        }
        return false;
    }
}
