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
import com.lleps.jsamp.SAMPFunctions;
import com.lleps.jsamp.constant.Paintjob;
import com.lleps.jsamp.constant.VehicleSeat;
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
            String key = args[0];
            VehicleModel model = VehicleModel.getById(Integer.parseInt(args[1]));
            Color color1 = Color.ofVehicleColor(Integer.parseInt(args[2]));
            Color color2 = Color.ofVehicleColor(Integer.parseInt(args[3]));

            Vehicle vehicle = new Vehicle(model, (Vector3D)player.getPosition().plusX(2).plusY(2), 5f,
                    color1, color2);

            vehiclesByKeys.put(key, vehicle);
            return true;
        }

        if (command.equals("/veh_create")) {
            String key = args[0];

            Vehicle vehicle = vehiclesByKeys.get(key);
            vehicle.create(player.getId(),
                    FunctionAccess.GetPlayerVirtualWorld(player.getId()),
                    FunctionAccess.GetPlayerInterior(player.getId()));
            return true;
        }

        if (command.equals("/veh_recreate")) {
            String key = args[0];
            Vehicle vehicle = vehiclesByKeys.get(key);
            long start = System.nanoTime();
            vehicle.recreate();
            long end = System.nanoTime() - start;
            SAMPFunctions.SendClientMessage(player.getId(), -1, "took " + end + "ns - " + (end/1_000_000.0) + "ms");
            return true;
        }

        if (command.equals("/veh_setModel")) {
            String key = args[0];
            Vehicle vehicle = vehiclesByKeys.get(key);
            vehicle.setModel(VehicleModel.getById(Integer.parseInt(args[1])));
            return true;
        }

        if (command.equals("/veh_destroy")) {
            String key = args[0];

            Vehicle vehicle = vehiclesByKeys.get(key);
            vehicle.destroy(player.getId());
            return true;
        }

        //veh_health key health
        if (command.equals("/veh_health")) {
            String key = args[0];
            float health = Float.parseFloat(args[1]);
            Vehicle vehicle = vehiclesByKeys.get(key);
            vehicle.setHealth(health);
            return true;
        }

        //veh_addComponent key component
        if (command.equals("/veh_addComponent")) {
            String key = args[0];
            int component = Integer.parseInt(args[1]);
            Vehicle vehicle = vehiclesByKeys.get(key);
            try {
                vehicle.addComponent(VehicleComponent.getById(component));
            } catch (Vehicle.IllegalComponentException e) {
                player.sendMessage("invalid.");
            }
            return true;
        }

        //veh_removeComponent key component
        if (command.equals("/veh_removeComponent")) {
            String key = args[0];
            int component = Integer.parseInt(args[1]);
            Vehicle vehicle = vehiclesByKeys.get(key);
            vehicle.removeComponent(VehicleComponent.getById(component));
            return true;
        }

        //veh_openWindows key
        if (command.equals("/veh_openWindows")) {
            String key = args[0];
            Vehicle vehicle = vehiclesByKeys.get(key);
            WindowState state = vehicle.getWindowState();
            state.setAllOpened(true);
            vehicle.setWindowState(state);
            return true;
        }

        //veh_closeWindows key
        if (command.equals("/veh_closeWindows")) {
            String key = args[0];
            Vehicle vehicle = vehiclesByKeys.get(key);

            WindowState state = vehicle.getWindowState();
            state.setAllOpened(false);
            vehicle.setWindowState(state);
            return true;
        }

        //veh_openDoors key
        if (command.equals("/veh_openDoors")) {
            String key = args[0];
            Vehicle v = vehiclesByKeys.get(key);
            DoorState state = v.getDoorState();
            state.setAllOpened(true);
            v.setDoorState(state);
            return true;
        }

        //veh_closeDoors key
        if (command.equals("/veh_closeDoors")) {
            String key = args[0];
            Vehicle v = vehiclesByKeys.get(key);
            DoorState state = v.getDoorState();
            state.setAllOpened(false);
            v.setDoorState(state);
            return true;
        }

        //veh_damage key
        if (command.equals("/veh_damage")) {
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
            return true;
        }

        //veh_fix key
        if (command.equals("/veh_fix")) {
            String key = args[0];
            Vehicle v = vehiclesByKeys.get(key);
            VehicleDamageState state = v.getDamageState();
            state.fixAll();
            v.setDamageState(state);

            return true;
        }

        //veh_paintjob key
        if (command.equals("/veh_paintjob")) {
            String key = args[0];
            Paintjob paintjob = Paintjob.getById(Integer.parseInt(args[1]));
            Vehicle v = vehiclesByKeys.get(key);
            v.setPaintjob(paintjob);
            return true;
        }

        //veh_addSiren key
        if (command.equals("/veh_addSiren")) {
            String key = args[0];
            Vehicle v = vehiclesByKeys.get(key);
            v.setHasSiren(true);
            return true;
        }

        //veh_removeSiren key
        if (command.equals("/veh_removeSiren")) {
            String key = args[0];
            Vehicle v = vehiclesByKeys.get(key);
            v.setHasSiren(false);
            return true;
        }

        //veh_primaryColor key color
        if (command.equals("/veh_primaryColor")) {
            String key = args[0];
            Color color = Color.ofVehicleColor(Integer.parseInt(args[1]));
            Vehicle v = vehiclesByKeys.get(key);
            v.setPrimaryColor(color);
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

        if (command.equals("/veh_respawn")) {
            String key = args[0];
            Vehicle v = vehiclesByKeys.get(key);
            v.respawn();
            return true;
        }

        if (command.equals("/veh_onspawnlistener")) {
            String key = args[0];
            Vehicle v = vehiclesByKeys.get(key);
            v.setOnSpawnListener((veh) -> player.sendMessage("vehicle " + veh + " spawned."));
            return true;
        }

        if (command.equals("/veh_spawnposition")) {
            String key = args[0];
            Vehicle v = vehiclesByKeys.get(key);
            v.setSpawnPosition(player.getPosition());
            return true;
        }

        if (command.equals("/veh_onmoddedinmodshop")) {
            String key = args[0];
            Vehicle v = vehiclesByKeys.get(key);
            v.setOnModdedInModshopListener((veh, driver, modshop, component) -> {
                driver.sendMessage("in " + modshop + ", you modded " + component + " for $" + component.getPrice());
            });
            return true;
        }


        if (command.equals("/veh_oncolorchangeinmodshop")) {
            String key = args[0];
            Vehicle v = vehiclesByKeys.get(key);
            v.setOnColorChangeInModshopListener((veh, driver, modshop, c1, c2) -> {
                player.sendMessage("you painted in " + modshop + " for colors ");
                driver.sendMessage("you painted in " + modshop + " for colors " + c1 + "this " + Color.WHITE + " and " + c2 + "this.");
            });
            return true;
        }

        if (command.equals("/veh_onpaintjobchangeinmodshop")) {
            String key = args[0];
            Vehicle v = vehiclesByKeys.get(key);
            v.setOnPaintjobChangeInModshopListener((veh, driver, modshop, paintjob) -> {
                player.sendMessage("you <what>paintjobed</what> in ");
                driver.sendMessage("you <what>paintjobed</what> in " + modshop + " for " + paintjob);
            });
            return true;
        }

        if (command.equals("/veh_onentermodshop")) {
            String key = args[0];
            Vehicle v = vehiclesByKeys.get(key);
            v.setOnEnterModshopListener((veh, modshop) -> {
                player.sendMessage("you entered to modshop " + modshop);
            });
            return true;
        }

        if (command.equals("/veh_onexitmodshop")) {
            String key = args[0];
            Vehicle v = vehiclesByKeys.get(key);
            v.setOnExitModshopListener((veh, modshop) -> {
                player.sendMessage("you exited from modshop " + modshop);
            });
            return true;
        }

        if (command.equals("/veh_onenteranim")) {
            String key = args[0];
            Vehicle v = vehiclesByKeys.get(key);
            v.setOnPlayerEnterAnimListener((vehicle, player1, passengerSeat) ->
            player1.sendMessage("entering to seat " + passengerSeat));
            return true;
        }

        if (command.equals("/veh_onexitanim")) {
            String key = args[0];
            Vehicle v = vehiclesByKeys.get(key);
            v.setOnPlayerExitAnimListener(((vehicle, player1) -> player1.sendMessage("exiting from vehicle")));
            return true;
        }
        if (command.equals("/veh_onentered")) {
            String key = args[0];
            Vehicle v = vehiclesByKeys.get(key);
            v.setOnPlayerEnteredListener((vehicle, player1, seat) -> player1.sendMessage("entered on seat " + seat));
            return true;
        }

        if (command.equals("/veh_onexited")) {
            String key = args[0];
            Vehicle v = vehiclesByKeys.get(key);
            v.setOnPlayerExitedListener((veh, player1) -> player1.sendMessage("exited"));
            return true;
        }

        if (command.equals("/veh_onsirenstatechange")) {
            String key = args[0];
            Vehicle v = vehiclesByKeys.get(key);
            v.setOnSirenStateChangeListener((veh, player1, newState) -> player1.sendMessage("now siren is " + newState));
            return true;
        }


        if (command.equals("/veh_ondeath")) {
            String key = args[0];
            Vehicle v = vehiclesByKeys.get(key);
            v.setOnDeathListener((vehicle, killer) -> player.sendMessage("vehicle killed by " + killer + "!"));
            return true;
        }

        if (command.equals("/veh_onshooted")) {
            String key = args[0];
            Vehicle v = vehiclesByKeys.get(key);
            v.setOnPlayerShootVehicleListener((vehicle, shooter, weap, offSets) ->  {
                player.sendMessage("shooted by " + shooter + " with a " + weap + " in " + offSets);
            });
            return true;
        }


        if (command.equals("/veh_driver")) {
            String key = args[0];
            Vehicle v = vehiclesByKeys.get(key);
            player.sendMessage("driver: " + v.getDriver());
            return true;
        }

        if (command.equals("/veh_playerinseat")) {
            String key = args[0];
            Vehicle v = vehiclesByKeys.get(key);
            player.sendMessage("player in this seat: " + v.getPlayerInSeat(VehicleSeat.getById(Integer.parseInt(args[1]))));
            return true;
        }


        if (command.equals("/veh_playersin")) {
            String key = args[0];
            Vehicle v = vehiclesByKeys.get(key);
            player.sendMessage("players in: " + v.getPlayersIn());
            return true;
        }

        return false;
    }
}