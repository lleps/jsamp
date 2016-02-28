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
import com.lleps.jsamp.constant.Interior;
import com.lleps.jsamp.constant.Paintjob;
import com.lleps.jsamp.constant.model.VehicleComponent;
import com.lleps.jsamp.constant.model.VehicleModel;
import com.lleps.jsamp.data.Color;
import com.lleps.jsamp.data.Vector3D;
import com.lleps.jsamp.data.vehicle.DoorState;
import com.lleps.jsamp.data.vehicle.VehicleDamageState;
import com.lleps.jsamp.data.vehicle.WindowState;
import com.lleps.jsamp.player.Player;
import com.lleps.jsamp.world.NoStreamingWorld;
import com.lleps.jsamp.world.Vehicle;
import com.lleps.jsamp.world.World;

import java.util.HashMap;
import java.util.Map;

/**
 * @author spell
 */
public class AnticheatWeaponsTest implements CommandListener {
    World world;

    Map<String, Vehicle> vehiclesByKeys = new HashMap<>();

    @Override
    public boolean onCommand(Player player, String command, String[] args) {
        if (command.equals("/giveweapon")) {
            FunctionAccess.GivePlayerWeapon(player.getId(), Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            return true;
        }
        if (command.equals("/resetweapons")) {
            FunctionAccess.ResetPlayerWeapons(player.getId());
            return true;
        }
        if (command.equals("/setammo")) {
            FunctionAccess.SetPlayerAmmo(player.getId(), Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            return true;
        }
        return false;
    }
}