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

import com.lleps.jsamp.server.ObjectNativeIDS;
import com.lleps.jsamp.player.Player;
import com.lleps.jsamp.FunctionAccess;
import com.lleps.jsamp.constant.model.BodyModel;
import com.lleps.jsamp.data.Vector;
import com.lleps.jsamp.data.Vector3D;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author spell
 */
public class Pickup extends GlobalEntity {
    public Optional<Pickup> getById(int id) {
        return Optional.ofNullable(ObjectNativeIDS.get(ObjectNativeIDS.getInstance().pickups, id));
    }

    public interface OnPickupListener {
        void onPickup(Pickup pickup, Player player);
    }

    public enum Type {
        EXISTS_ALWAYS(1),
        DISAPPEAR_30SECONDS(2),
        DISAPPEAR_AFTER_DEATH(3),
        DISAPPEAR_20SECONDS(4),
        DISAPPEAR_AFTER_PICKUP(8),
        BOMB_1(11),
        BOMB_2(12),
        VEHICLE_PICKUP(14);

        int nativeType;

        Type(int nativeType) {
            this.nativeType = nativeType;
        }
    }

    private Type type;
    private BodyModel model;
    private Vector3D position;

    private Set<WorldEntity> attachedEntities = new HashSet<>();

    private OnPickupListener onPickupListener;

    public Pickup(Type type, BodyModel model, Vector3D position) {
        this.type = type;
        this.model = model;
        this.position = position;
    }

    public void onPickup(Player player) {
        if (onPickupListener != null) onPickupListener.onPickup(this, player);
    }

    public void setOnPickupListener(OnPickupListener onPickupListener) {
        this.onPickupListener = onPickupListener;
    }

    public void setPosition(Vector3D position) {
        dispatchPositionChange(this.position, position);
        this.position = position;
    }

    @Override
    public Vector3D getPosition() {
        return position;
    }

    public BodyModel getModel() {
        return model;
    }

    public void setModel(BodyModel model) {
        this.model = model;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


    public void attachLabel(Label label, Vector3D offSets) {
        AttachedData<Pickup> attachedData = new AttachedData<>(this, offSets);
        label.setAttachedPickup(attachedData);
        attachedEntities.add(label);

        playerIds.forEach((playerId) -> label.create(playerId, 0, 0));
    }

    public void detachLabel(Label label) {
        if (attachedEntities.remove(label)) {
            label.setAttachedPickup(null);
            if (isCreated()) playerIds.forEach(label::destroy);
        }
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
    protected int createNatively(int worldId) {
        return FunctionAccess.CreatePickup(model.getId(), type.nativeType, position.getX(),
                position.getY(), position.getZ(), worldId);
    }

    @Override
    protected void destroyNatively(int id) {
        FunctionAccess.DestroyPickup(id);
    }

    @Override
    protected int getInvalidId() {
        return -1;
    }

    @Override
    protected Object[] getIDSArray() {
        return ObjectNativeIDS.getInstance().pickups;
    }
}
