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
package com.lleps.test.anticheat;

import com.lleps.jsamp.FunctionAccess;
import com.lleps.jsamp.MainCallbackListener;
import com.lleps.jsamp.player.Player;
import com.lleps.test.CommandListener;

/**
 * @author spell
 */
public class AnticheatMoneyTest implements CommandListener {
    @Override
    public boolean onCommand(Player player, String command, String[] args) {
        int id = player.getId();
        if (command.equals("/givemoney")) {
            FunctionAccess.GivePlayerMoney(id, Integer.parseInt(args[0]));
            return true;
        }
        if (command.equals("/getmoney")) {
            FunctionAccess.SendClientMessage(id, -1, "money: " + FunctionAccess.GetPlayerMoney(id));
            return true;
        }
        if (command.equals("/resetmoney")) {
            FunctionAccess.ResetPlayerMoney(player.getId());
            return true;
        }
        return false;
    }
}