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
package com.lleps.jsamp.gamemode;

import com.lleps.jsamp.player.Player;
import com.lleps.jsamp.SAMPConstants;
import com.lleps.jsamp.constant.model.WeaponModel;
import com.lleps.jsamp.data.Vector3D;
import com.lleps.jsamp.dialog.Dialog;
import com.lleps.jsamp.world.Body;
import com.lleps.jsamp.world.Pickup;

/**
 * @author spell
 */
public class EventDispatcher implements CallbackListener {
    private GameMode gameMode;
    private ObjectNativeIDS arrays = ObjectNativeIDS.getInstance();

    public EventDispatcher(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    @Override
    public boolean OnPlayerConnect(int playerId) {
        Player newPlayer = new Player(playerId);
        gameMode.onPlayerConnect(newPlayer);
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
        }
        return false;
    }

    private <T> T getEntity(T[] array, int entityId) {
        return ObjectNativeIDS.get(array, entityId);
    }

    private <T> T getEntity(T[][] array, int playerId, int entityId) {
        return ObjectNativeIDS.get(array, playerId, entityId);
    }
}