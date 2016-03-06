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
package com.lleps.test.world;

import com.lleps.jsamp.FunctionAccess;
import com.lleps.jsamp.constant.Paintjob;
import com.lleps.jsamp.constant.model.VehicleComponent;
import com.lleps.jsamp.constant.model.VehicleModel;
import com.lleps.jsamp.data.Color;
import com.lleps.jsamp.data.Vector3D;
import com.lleps.jsamp.data.vehicle.DoorState;
import com.lleps.jsamp.data.vehicle.VehicleDamageState;
import com.lleps.jsamp.data.vehicle.WindowState;
import com.lleps.jsamp.player.Player;
import com.lleps.jsamp.world.entity.Vehicle;
import com.lleps.test.CommandListener;

import java.util.HashMap;
import java.util.Map;

/**
 * @author spell
 */
public class VehicleTest implements CommandListener {
    Map<String, Vehicle> vehiclesByKeys = new HashMap<>();

    @Override
    public boolean onCommand(Player player, String command, String[] args) {
        if (command.equals("/veh_new")) {
            try {
                String key = args[0];
                VehicleModel model = VehicleModel.getById(Integer.parseInt(args[1]));
                Color color1 = Color.ofVehicleColor(Integer.parseInt(args[2]));
                Color color2 = Color.ofVehicleColor(Integer.parseInt(args[3]));

                Vehicle vehicle = new Vehicle(model, (Vector3D)player.getPosition().plusX(2).plusY(2), 5f,
                        color1, color2);

                vehiclesByKeys.put(key, vehicle);
            } catch (Exception e) {
                player.sendMessage("/veh_crear key model col1 col2");
                player.sendMessage("Exception: " + e);
            }
            return true;
        }

        if (command.equals("/veh_create")) {
            try {
                String key = args[0];

                Vehicle vehicle = vehiclesByKeys.get(key);
                vehicle.create(player.getId(),
                        FunctionAccess.GetPlayerVirtualWorld(player.getId()),
                        FunctionAccess.GetPlayerInterior(player.getId()));
            } catch (Exception e) {
                player.sendMessage("exc " + e);
            }
            return true;
        }

        if (command.equals("/veh_destroy")) {
            try {
                String key = args[0];

                Vehicle vehicle = vehiclesByKeys.get(key);
                vehicle.destroy(player.getId());
            } catch (Exception e) {
                player.sendMessage("exc " + e);
            }
            return true;
        }

        //veh_health key health
        if (command.equals("/veh_health")) {
            try {
                String key = args[0];
                float health = Float.parseFloat(args[1]);
                Vehicle vehicle = vehiclesByKeys.get(key);
                vehicle.setHealth(health);
            } catch (Exception e) {
                player.sendMessage("exc " + e);
            }
            return true;
        }

        //veh_addComponent key component
        if (command.equals("/veh_addComponent")) {
            try {
                String key = args[0];
                int component = Integer.parseInt(args[1]);
                Vehicle vehicle = vehiclesByKeys.get(key);
                try {
                    vehicle.addComponent(VehicleComponent.getById(component));
                } catch (Vehicle.IllegalComponentException e) {
                    player.sendMessage("invalid.");
                }
            } catch (Exception e) {
                player.sendMessage("/veh_addComponent key comp");
            }
            return true;
        }

        //veh_removeComponent key component
        if (command.equals("/veh_removeComponent")) {
            try {
                String key = args[0];
                int component = Integer.parseInt(args[1]);
                Vehicle vehicle = vehiclesByKeys.get(key);
                        vehicle.removeComponent(VehicleComponent.getById(component));
            } catch (Exception e) {
            }
            return true;
        }

        //veh_openWindows key
        if (command.equals("/veh_openWindows")) {
            try {
                String key = args[0];
                Vehicle vehicle = vehiclesByKeys.get(key);
                    WindowState state = vehicle.getWindowState();
                    state.setAllOpened(true);
                    vehicle.setWindowState(state);

            } catch (Exception e) {
            }
            return true;
        }

        //veh_closeWindows key
        if (command.equals("/veh_closeWindows")) {
            try {
                String key = args[0];
                Vehicle vehicle = vehiclesByKeys.get(key);

                    WindowState state = vehicle.getWindowState();
                    state.setAllOpened(false);
                    vehicle.setWindowState(state);

            } catch (Exception e) {
            }
            return true;
        }

        //veh_openDoors key
        if (command.equals("/veh_openDoors")) {
            try {
                String key = args[0];
                Vehicle v = vehiclesByKeys.get(key);
                    DoorState state = v.getDoorState();
                    state.setAllOpened(true);
                    v.setDoorState(state);

            } catch (Exception e) {
            }
            return true;
        }

        //veh_closeDoors key
        if (command.equals("/veh_closeDoors")) {
            try {
                String key = args[0];
                Vehicle v = vehiclesByKeys.get(key);
                    DoorState state = v.getDoorState();
                    state.setAllOpened(false);
                    v.setDoorState(state);

            } catch (Exception e) {
            }
            return true;
        }

        //veh_damage key
        if (command.equals("/veh_damage")) {
            try {
                String key = args[0];
                Vehicle v = vehiclesByKeys.get(key);
                    VehicleDamageState state = v.getDamageState();
                    state.setTirePopped(VehicleDamageState.TIRE_PASSENGER, true);
                    state.setDoorState(VehicleDamageState.DOOR_BOOT, VehicleDamageState.DOORSTATE_DAMAGED);
                    state.setDoorState(VehicleDamageState.DOOR_PASSENGER, VehicleDamageState.DOORSTATE_OPENED);
                    state.setLightDestroyed(VehicleDamageState.LIGHT_DRIVER, true);
                    state.setPanelState(VehicleDamageState.PANEL_FRONT_BUMPER, VehicleDamageState.PANELSTATE_DAMAGED);
                    state.setAllTiresPopped(true);
                    v.setDamageState(state);
            } catch (Exception e) {
            }
            return true;
        }

        //veh_fix key
        if (command.equals("/veh_fix")) {
            try {
                String key = args[0];
                Vehicle v = vehiclesByKeys.get(key);
                    VehicleDamageState state = v.getDamageState();
                    state.fixAll();
                    v.setDamageState(state);

            } catch (Exception e) {
            }
            return true;
        }

        //veh_paintjob key
        if (command.equals("/veh_paintjob")) {
            try {
                String key = args[0];
                Paintjob paintjob = Paintjob.getById(Integer.parseInt(args[1]));
                Vehicle v = vehiclesByKeys.get(key);
                    v.setPaintjob(paintjob);
            } catch (Exception e) {
            }
            return true;
        }

        //veh_addSiren key
        if (command.equals("/veh_addSiren")) {
            try {
                String key = args[0];
                Vehicle v = vehiclesByKeys.get(key);
                    v.setHasSiren(true);
            } catch (Exception e) {
            }
            return true;
        }

        //veh_removeSiren key
        if (command.equals("/veh_removeSiren")) {
            try {
                String key = args[0];
                Vehicle v = vehiclesByKeys.get(key);
                    v.setHasSiren(false);
            } catch (Exception e) {
            }
            return true;
        }

        //veh_primaryColor key color
        if (command.equals("/veh_primaryColor")) {
            try {
                String key = args[0];
                Color color = Color.ofVehicleColor(Integer.parseInt(args[1]));
                Vehicle v = vehiclesByKeys.get(key);
                    v.setPrimaryColor(color);
            } catch (Exception e) {
            }
            return true;
        }

        //veh_secondaryColor key color
        if (command.equals("/veh_secondaryColor")) {
            String key = args[0];
            Color color = Color.ofVehicleColor(Integer.parseInt(args[1]));
            Vehicle v = vehiclesByKeys.get(key);
            v.setSecondaryColor(color);
            return true;
        }

        return false;
    }
}