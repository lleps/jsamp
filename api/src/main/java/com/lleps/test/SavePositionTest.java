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
import com.lleps.jsamp.MainCallbackListener;
import com.lleps.jsamp.SAMPFunctions;
import com.lleps.jsamp.player.Player;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author spell
 */
public class SavePositionTest implements CommandListener {
    @Override
    public boolean onCommand(Player player, String command, String[] args) {
        int id = player.getId();
        if (command.equalsIgnoreCase("/saveposition")) {
            float[] pos = SAMPFunctions.GetPlayerPos(id);
            int interior = SAMPFunctions.GetPlayerInterior(id);
            String textToWrite = "interior: " + interior + " position: " + pos[0] + ", " + pos[1] + ", " + pos[2] + " - " + args[0];
            try (FileWriter f = new FileWriter("scriptfiles/savedpositions.txt")) {
                f.write(textToWrite + "\n");
            } catch (IOException e) {
                SAMPFunctions.SendClientMessage(id, -1, "failed: " + e);
            }
            SAMPFunctions.SendClientMessage(id, -1, textToWrite);
            return true;
        }
        return false;
    }
}