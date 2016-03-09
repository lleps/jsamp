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
package com.lleps.jsamp.world.entity;

import com.lleps.jsamp.*;
import com.lleps.jsamp.constant.model.BodyModel;
import com.lleps.jsamp.constant.model.WeaponModel;
import com.lleps.jsamp.data.Vector;
import com.lleps.jsamp.data.Vector3D;
import com.lleps.jsamp.data.bodymaterial.BodyMaterial;
import com.lleps.jsamp.server.ObjectNativeIDS;
import com.lleps.jsamp.player.Player;
import com.lleps.jsamp.world.World;

import java.util.*;

/**
 * @author spell
 */
public class Body extends PerPlayerEntity {
    public interface OnShootedListener {
        void onShooted(Body body, Player player, Vector3D offSets, WeaponModel weapon);
    }

    public static final int MAX_MATERIAL_INDEX = 15;

    private BodyModel model;
    private Vector3D position;
    private Vector3D rotation;
    private float drawDistance;
    private Map<Integer, BodyMaterial> materials = new HashMap<>();

    private AttachedData<Vehicle> attachedVehicle;

    private Set<WorldEntity> attachedEntities = new HashSet<>();

    private OnShootedListener onShootedListener;

    public Body(BodyModel model, Vector3D position, Vector3D rotation) {
        this.model = model;
        this.position = position;
        this.rotation = rotation;
    }

    public void onPlayerShot(Player player, Vector3D offSets, WeaponModel weapon) {
        if (onShootedListener != null) onShootedListener.onShooted(this, player, offSets, weapon);
    }

    public void setOnShootedListener(OnShootedListener onShootedListener) {
        this.onShootedListener = onShootedListener;
    }

    public void setModel(BodyModel model) {
        this.model = model;
    }

    public BodyModel getModel() {
        return model;
    }

    @Override
    public Vector3D getPosition() {
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

    public void attachLabel(Label label, Vector3D offSets) {
        AttachedData<Body> attachedData = new AttachedData<>(this, offSets);
        label.setAttachedBody(attachedData);
        attachedEntities.add(label);

        idsByPlayerIds.forEach((playerId, objectId) -> label.create(playerId, 0, 0));
    }

    public void detachLabel(Label label) {
        if (attachedEntities.remove(label)) {
            label.setAttachedBody(null);
            idsByPlayerIds.forEach((playerId, objectId) -> label.destroy(playerId));
        }
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

    public int move(Vector3D position, Vector3D rotation, float speed) {
        int moveTimeMS = 0;
        for (Map.Entry<Integer, Integer> entry : idsByPlayerIds.entrySet()) {
            int playerId = entry.getKey();
            int objectId = entry.getValue();
            moveTimeMS = FunctionAccess.MovePlayerObject(playerId, objectId,
                    position.getX(), position.getY(), position.getZ(), speed, rotation.getX(), rotation.getY(), rotation.getZ());
        }

        this.position = position;
        this.rotation = rotation;

        return moveTimeMS;
    }

    public void setAttachedVehicle(AttachedData<Vehicle> attachedVehicle) {
        this.attachedVehicle = attachedVehicle;
    }

    @Override
    public boolean create(int playerId, int worldId, int interiorId) {
        boolean created = super.create(playerId, worldId, interiorId);
        if (created) {
            attachedEntities.forEach(e -> e.create(playerId, worldId, interiorId));
        }
        return created;
    }

    @Override
    public void destroy(int playerId) {
        super.destroy(playerId);
        attachedEntities.forEach(e -> e.destroy(playerId));
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

        if (attachedVehicle != null) {
            SAMPFunctions.AttachPlayerObjectToVehicle(playerId, entityId, attachedVehicle.getSource().getId(),
                    attachedVehicle.getOffSets().getX(), attachedVehicle.getOffSets().getY(),
                    attachedVehicle.getOffSets().getZ(),
                    attachedVehicle.getRotation().getX(), attachedVehicle.getRotation().getY(),
                    attachedVehicle.getRotation().getZ());
        }
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