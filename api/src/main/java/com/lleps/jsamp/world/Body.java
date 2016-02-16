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
package com.lleps.jsamp.world;

import com.lleps.jsamp.*;
import com.lleps.jsamp.constant.model.BodyModel;
import com.lleps.jsamp.constant.model.WeaponModel;
import com.lleps.jsamp.data.Vector;
import com.lleps.jsamp.data.Vector3D;
import com.lleps.jsamp.data.bodymaterial.BodyMaterial;
import com.lleps.jsamp.gamemode.ObjectNativeIDS;
import com.lleps.jsamp.player.Player;

import java.util.*;

/**
 * @author spell
 */
public class Body extends PerPlayerEntity {
    public interface OnPlayerShotListener {
        void onPlayerShot(Body body, Player player, Vector3D offSets, WeaponModel weapon);
    }

    public static final int MAX_MATERIAL_INDEX = 15;

    private World world;
    private BodyModel model;
    private Vector3D position;
    private Vector3D rotation;
    private float drawDistance;
    private Map<Integer, BodyMaterial> materials;

    private OnPlayerShotListener onPlayerShotListener;

    public Body() {
        materials = new HashMap<>();
    }

    public Body(BodyModel model, Vector3D position, Vector3D rotation) {
        this();
        this.model = model;
        this.position = position;
        this.rotation = rotation;
    }

    public void onPlayerShot(Player player, Vector3D offSets, WeaponModel weapon) {
        if (onPlayerShotListener != null) onPlayerShotListener.onPlayerShot(this, player, offSets, weapon);
    }

    public void setOnPlayerShotListener(OnPlayerShotListener onPlayerShotListener) {
        this.onPlayerShotListener = onPlayerShotListener;
    }

    public void setModel(BodyModel model) {
        this.model = model;
    }

    public BodyModel getModel() {
        return model;
    }

    @Override
    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector3D position) {
        this.position = position;
        idsByPlayerIds.forEach((playerId, objectId) -> FunctionAccess.SetPlayerObjectPos(playerId, objectId,
                    position.getX(), position.getY(), position.getZ()));
    }

    public Vector3D getRotation() {
        return rotation;
    }

    public void setRotation(Vector3D rotation) {
        this.rotation = rotation;
        idsByPlayerIds.forEach((playerId, objectId) -> FunctionAccess.SetPlayerObjectRot(playerId, objectId,
                rotation.getX(), rotation.getY(), rotation.getZ()));
    }

    public void setDrawDistance(float drawDistance) {
        this.drawDistance = drawDistance;
    }

    public float getDrawDistance() {
        return drawDistance;
    }

    public void setMaterial(int index, BodyMaterial material) {
        materials.put(index, material);
        idsByPlayerIds.forEach((playerId, objectId) -> material.apply(playerId, objectId, index));
    }

    public Optional<BodyMaterial> getMaterial(int index) {
        return Optional.ofNullable(materials.get(index));
    }

    public void removeMaterial(int index) {
        materials.remove(index);
    }

    @Override
    protected int createNatively(int playerId) {
        return FunctionAccess.CreatePlayerObject(playerId, model.getId(),
                position.getX(), position.getY(), position.getZ(),
                rotation.getX(), rotation.getY(), rotation.getZ(), drawDistance);
    }

    @Override
    protected void applyState(int playerId, int entityId) {
        super.applyState(playerId, entityId);

        materials.forEach((index, material) -> material.apply(playerId, entityId, index));
    }

    @Override
    protected void destroyNatively(int playerId, int entityId) {
        FunctionAccess.DestroyPlayerObject(playerId, entityId);
    }

    @Override
    protected int getInvalidId() {
        return SAMPConstants.INVALID_OBJECT_ID;
    }

    @Override
    protected Object[][] getArrayNativeIDS() {
        return ObjectNativeIDS.getInstance().playerObjects;
    }
}