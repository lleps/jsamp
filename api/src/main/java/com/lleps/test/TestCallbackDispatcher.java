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

import com.lleps.jsamp.gamemode.CallbackListener;
import com.lleps.jsamp.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author spell
 */
public class TestCallbackDispatcher implements CallbackListener {
    private List<CommandListener> commandListeners = new ArrayList<>();

    public void addCommandListener(CommandListener commandListener) {
        commandListeners.add(commandListener);
    }

    public void removeCommandListener(CommandListener commandListener) {
        commandListeners.remove(commandListener);
    }

    @Override
    public boolean onPlayerCommandText(int playerId, String command) {
        Player player = Player.getById(playerId).get();
        String[] commandAndParams = command.split(" ", 2);
        String commandString = commandAndParams[0];
        String commandParams = "";
        String args[];
        if (commandAndParams.length > 1) {
            args = commandAndParams[1].split(" ");
        } else {
            args = new String[] {};
        }
        player.sendMessage("command: " + commandString + " args: " + Arrays.toString(args));

        for (CommandListener commandListener : commandListeners) {
            if (commandListener.onCommand(player, commandString, args)) {
                return true;
            }
        }
        return false;
    }
}