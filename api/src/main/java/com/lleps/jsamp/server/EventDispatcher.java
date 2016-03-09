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
package com.lleps.jsamp.server;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Multiset;
import com.lleps.jsamp.SAMPFunctions;
import com.lleps.jsamp.constant.BodyPart;
import com.lleps.jsamp.constant.Modshop;
import com.lleps.jsamp.constant.Paintjob;
import com.lleps.jsamp.constant.VehicleSeat;
import com.lleps.jsamp.constant.model.VehicleComponent;
import com.lleps.jsamp.data.Color;
import com.lleps.jsamp.player.Player;
import com.lleps.jsamp.SAMPConstants;
import com.lleps.jsamp.constant.model.WeaponModel;
import com.lleps.jsamp.data.Vector3D;
import com.lleps.jsamp.dialog.Dialog;
import com.lleps.jsamp.world.entity.*;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @author spell
 */
public class EventDispatcher implements CallbackListener {
    public interface OnPlayerIdDisconnectListener {
        void onPlayerIdDisconnect(int playerId, int reason);
    }

    private final SAMPServer server;
    private final ObjectNativeIDS arrays = ObjectNativeIDS.getInstance();

    private final Multimap<Integer, OnPlayerIdDisconnectListener> disconnectListeners = ArrayListMultimap.create();

    public EventDispatcher(SAMPServer server) {
        this.server = server;
    }

    @Override
    public boolean OnPlayerConnect(int playerId) {
        Player newPlayer = new Player(playerId);
        server.onPlayerConnect(newPlayer);
        return false;
    }

    @Override
    public boolean OnDialogResponse(int playerId, int dialogId, int response, int listItem, String inputText) {
        Player player = getEntity(arrays.players, playerId);
        Dialog dialog = player.getDialog().orElse(null);
        if (dialog != null) {
            if (dialog.onDialogResponse(player, dialogId, response != 0, inputText, listItem)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean OnPlayerPickUpPickup(int playerId, int pickupId) {
        Pickup pickup = getEntity(arrays.pickups, pickupId);
        if (pickup != null) {
            pickup.onPickup(getEntity(arrays.players, playerId));
        }
        return false;
    }

    @Override
    public boolean OnPlayerWeaponShot(int playerId, int weaponId, int hitType, int hitId, float x, float y, float z) {
        Player player = getEntity(arrays.players, playerId);
        WeaponModel weaponModel = WeaponModel.getById(weaponId);
        Vector3D offSets = Vector3D.of(x, y, z);
        if (hitType == SAMPConstants.BULLET_HIT_TYPE_PLAYER_OBJECT) {
            Body body = getEntity(arrays.playerObjects, playerId, hitId);
            if (body != null) body.onPlayerShot(player, offSets, weaponModel);
        } else if (hitType == SAMPConstants.BULLET_HIT_TYPE_VEHICLE) {
            Vehicle vehicle = arrays.vehicles[hitId];
            vehicle.onPlayerShootVehicle(player, weaponModel, offSets);
        }
        return false;
    }

    @Override
    public boolean OnPlayerGiveDamageActor(int playerid, int actor_id, float amount, int weapon, int bodypart) {
        Player player = arrays.players[playerid];
        Actor actor = arrays.actors[actor_id];

        if (!actor.onDamaged(player, amount, WeaponModel.getById(weapon), BodyPart.get(bodypart))) {
            float newActorHealth = actor.getHealth() - amount;

            if (newActorHealth < 0) newActorHealth = 0;

            actor.setHealth(newActorHealth);

            if (newActorHealth == 0) {
                actor.onDeath(player);
            }
        }
        return false;
    }

    @Override
    public boolean OnVehicleSpawn(int vehicleId) {
        Vehicle vehicle = arrays.vehicles[vehicleId];
        vehicle.onSpawn();
        return false;
    }

    @Override
    public boolean OnVehicleMod(int playerId, int vehicleId, int componentId) {
        Vehicle vehicle = arrays.vehicles[vehicleId];
        Player player = arrays.players[playerId];
        Modshop modshop = player.getProperty("EventDispatcher.modshop");

        vehicle.onModdedInModshop(player, modshop, VehicleComponent.getById(componentId));
        return false;
    }

    @Override
    public boolean OnVehicleRespray(int playerId, int vehicleId, int color1, int color2) {
        Vehicle vehicle = arrays.vehicles[vehicleId];
        Player player = arrays.players[playerId];
        Modshop modshop = player.getProperty("EventDispatcher.modshop");

        Color newPrimaryColor = Color.ofVehicleColor(color1);
        Color newSecondaryColor = Color.ofVehicleColor(color2);

        if (vehicle.getPrimaryColor() != newPrimaryColor || vehicle.getSecondaryColor() != newSecondaryColor) {
            vehicle.onColorChangeInModshop(player, modshop, newPrimaryColor, newSecondaryColor);
        }
        return false;
    }

    @Override
    public boolean OnVehiclePaintjob(int playerId, int vehicleId, int paintjobId) {
        Vehicle vehicle = arrays.vehicles[vehicleId];
        Player player = arrays.players[playerId];
        Modshop modshop = player.getProperty("EventDispatcher.modshop");
        Paintjob paintjob = Paintjob.getById(paintjobId);
        if (paintjob != vehicle.getPaintjob()) {
            vehicle.onPaintjobChangeInModshop(player, modshop, paintjob);
        }
        return false;
    }

    @Override
    public boolean OnEnterExitModShop(int playerId, int enterexit, int interiorId) {
        Player player = arrays.players[playerId];
        Vehicle vehicle = arrays.vehicles[SAMPFunctions.GetPlayerVehicleID(playerId)];
        if (enterexit == 1) {
            Modshop modshop = Modshop.getByExteriorPosition(player.getPosition(), 30);
            player.setProperty("EventDispatcher.modshop", modshop);
            vehicle.onEnterModshop(modshop);
        } else if (enterexit == 0){
            Modshop modshop = player.getProperty("EventDispatcher.modshop");
            // We cant remove this property since sometimes OnVehicleRespray is called when you exit from modshop.
            //player.removeProperty("EventDispatcher.modshop");
            vehicle.onExitModshop(modshop);
        }
        return false;
    }

    @Override
    public boolean OnPlayerEnterVehicle(int playerId, int vehicleId, boolean isPassenger) {
        Player player = arrays.players[playerId];
        Vehicle vehicle = arrays.vehicles[vehicleId];
        vehicle.onPlayerEnterAnim(player, isPassenger);
        return false;
    }

    @Override
    public boolean OnPlayerExitVehicle(int playerId, int vehicleId) {
        Player player = arrays.players[playerId];
        Vehicle vehicle = arrays.vehicles[vehicleId];
        vehicle.onPlayerExitAnim(player);
        return false;
    }

    @Override
    public boolean OnPlayerStateChange(int playerId, int state, int oldState) {
        Player player = arrays.players[playerId];
        if (state == SAMPConstants.PLAYER_STATE_DRIVER || state == SAMPConstants.PLAYER_STATE_PASSENGER) {
            int vehicleId = SAMPFunctions.GetPlayerVehicleID(playerId);
            Vehicle vehicle = arrays.vehicles[vehicleId];
            VehicleSeat seat = VehicleSeat.getById(SAMPFunctions.GetPlayerVehicleSeat(playerId));
            vehicle.onPlayerEntered(player, seat);
            player.setProperty("EventDispatcher.vehicleId", vehicleId);
        } else if (state == SAMPConstants.PLAYER_STATE_ONFOOT) {
            Integer vehicleId = player.getProperty("EventDispatcher.vehicleId");
            if (vehicleId != null) {
                Vehicle vehicle = arrays.vehicles[vehicleId];

                // if vehicle got destroyed when player is driving it, player state will be ONFOOT, but vehicle array will point to null!
                if (vehicle != null) {
                    vehicle.onPlayerExited(player);
                }

                player.removeProperty("EventDispatcher.vehicleId");
            }
        }
        return false;
    }

    @Override
    public boolean OnVehicleDeath(int vehicleId, int killerId) {
        Vehicle vehicle = arrays.vehicles[vehicleId];
        Optional<Player> killer = Optional.ofNullable(getEntity(arrays.players, killerId));
        vehicle.onDeath(killer);
        return false;
    }

    @Override
    public boolean OnVehicleSirenStateChange(int playerid, int vehicleid, boolean newstate) {
        Player player = arrays.players[playerid];
        Vehicle vehicle = arrays.vehicles[vehicleid];
        vehicle.onSirenStateChange(player, newstate);
        return false;
    }

    @Override
    public boolean OnPlayerDisconnect(int playerId, int reason) {
        for (OnPlayerIdDisconnectListener listener : disconnectListeners.get(playerId)) {
            listener.onPlayerIdDisconnect(playerId, reason);
        }
        disconnectListeners.removeAll(playerId);
        return false;
    }

    /**
     * Listen when a player id disconnects. All per-player listeners are automatically removed after the player disconnects.
     * @param playerId player id
     * @param listener listener
     */
    public void addOnPlayerIdDisconnectListener(int playerId, OnPlayerIdDisconnectListener listener) {
        disconnectListeners.put(playerId, listener);
    }

    /**
     * Removes a player disconnection listener. You don't need to remove player listeners on player disconnect, since all listeners
     * are automatically removed when player leaves the server.
     * @param playerId player id.
     * @param listener listener id.
     * @exception java.util.ConcurrentModificationException if you try to remove a listener when a player is disconnecting.
     */
    public void removeOnPlayerIdDisconnectListener(int playerId, OnPlayerIdDisconnectListener listener) {
        disconnectListeners.remove(playerId, listener);
    }

    private <T> T getEntity(T[] array, int entityId) {
        return ObjectNativeIDS.get(array, entityId);
    }

    private <T> T getEntity(T[][] array, int playerId, int entityId) {
        return ObjectNativeIDS.get(array, playerId, entityId);
    }
}