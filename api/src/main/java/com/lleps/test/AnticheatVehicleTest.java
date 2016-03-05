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
import com.lleps.jsamp.constant.model.VehicleModel;
import com.lleps.jsamp.data.Color;
import com.lleps.jsamp.data.Vector3D;
import com.lleps.jsamp.player.Player;
import com.lleps.jsamp.world.Vehicle;
import com.lleps.jsamp.world.World;

import java.util.HashMap;
import java.util.Map;

/**
 * @author spell
 */
public class AnticheatVehicleTest implements CommandListener {

    @Override
    public boolean onCommand(Player player, String command, String[] args) {
        if (command.equals("/createvehicle")) {
            try {
                VehicleModel model = VehicleModel.getById(Integer.parseInt(args[0]));
                Color color1 = Color.ofVehicleColor(Integer.parseInt(args[1]));
                Color color2 = Color.ofVehicleColor(Integer.parseInt(args[2]));
                float[] xyz = FunctionAccess.GetPlayerPos(player.getId());
                int id = FunctionAccess.CreateVehicle(model.getModelId(), xyz[0], xyz[1], xyz[2], 0, color1.getVehicleColorID(),
                        color2.getVehicleColorID(), -1, false);
                FunctionAccess.SetVehicleVirtualWorld(id, FunctionAccess.GetPlayerVirtualWorld(player.getId()));
            } catch (Exception e) {
                player.sendMessage("/createvehicle model col1 col2");
                player.sendMessage("Exception: " + e);
            }
            return true;
        }
        if (command.equals("/putinvehicle")) {
            FunctionAccess.PutPlayerInVehicle(player.getId(), Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            return true;
        }
        if (command.equals("/removefromvehicle")) {
            FunctionAccess.RemovePlayerFromVehicle(player.getId());
            return true;
        }
        if (command.equals("/setvehiclepos")) {
            FunctionAccess.SetVehiclePos(Integer.parseInt(args[0]), Float.parseFloat(args[1]), Float.parseFloat(args[2]), Float.parseFloat(args[3]));
            return true;
        }
        if (command.equals("/setvehiclehealth")) {
            FunctionAccess.SetVehicleHealth(Integer.parseInt(args[0]), Float.parseFloat(args[1]));
            return true;
        }
        if (command.equals("/setvehiclevelocity")) {
            FunctionAccess.SetVehicleVelocity(Integer.parseInt(args[0]), Float.parseFloat(args[1]),
                    Float.parseFloat(args[2]), Float.parseFloat(args[3]));
            return true;
        }
        if (command.equals("/setvehiclecolor")) {
            FunctionAccess.ChangeVehicleColor(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
            return true;
        }
        if (command.equals("/setvehiclepaintjob")) {
            FunctionAccess.ChangeVehiclePaintjob(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            return true;
        }
        if (command.equals("/setvehicleparamsex")) {
            FunctionAccess.SetVehicleParamsEx(Integer.parseInt(args[0]), Integer.parseInt(args[1]) == 1,
                    Integer.parseInt(args[2]) == 1,
                    Integer.parseInt(args[3]) == 1,
                    Integer.parseInt(args[4]) == 1,
                    Integer.parseInt(args[5]) == 1,
                    Integer.parseInt(args[6]) == 1,
                    Integer.parseInt(args[7]) == 1);
            return true;
        }
        return false;
    }
}