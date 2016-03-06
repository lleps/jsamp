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

import com.google.common.collect.Sets;
import com.lleps.jsamp.player.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Base class for all per-player entity implementations.
 * Note that per-player entities don't have virtual worlds.
 *
 * @author spell
 */
public abstract class PerPlayerEntity extends WorldEntity {
    /**
     * This map contains references player id -> entity id.
     */
    protected final Map<Integer, Integer> idsByPlayerIds = new HashMap<>();

    protected PerPlayerEntity() {
    }

    @Override
    public final boolean create(int playerId, int worldId, int interiorId) {
        if (idsByPlayerIds.containsKey(playerId)) {
            return true;
        }

        int entityId = createNatively(playerId);
        if (entityId == getInvalidId()) {
            return false;
        }

        idsByPlayerIds.put(playerId, entityId);

        getArrayNativeIDS()[playerId][entityId] = this;

        applyState(playerId, entityId);
        return true;
    }

    @Override
    public final void destroy(int playerId) {
        Integer entityId = idsByPlayerIds.get(playerId);
        if (entityId != null) {
            saveState(playerId, entityId);
            destroyNatively(playerId, entityId);

            idsByPlayerIds.remove(playerId);

            getArrayNativeIDS()[playerId][entityId] = null;
        }
    }

    @Override
    public final boolean isCreated(int playerId) {
        return idsByPlayerIds.containsKey(playerId);
    }

    /**
     * In case this entity is created, this method will destroy it and create it again. It is used for
     * cases when there isn't a native method to change a property. For example, if you want to change
     * a vehicle model you must recreate the vehicle to make the change visible.
     */
    public void recreate() {
        // Copied to avoid ConcurrentModificationException
        for (int playerId : Sets.newHashSet(idsByPlayerIds.keySet())) {
            destroy(playerId);
            create(playerId, 0, 0); // world and interior arent used for per-player entities.
        }
    }

    /**
     * Create this entity for the given player.
     *
     * @param playerId player id.
     * @return created entity id.
     */
    protected abstract int createNatively(int playerId);

    /**
     * Get entity invalid id. Used for check if creation was sucessfully or not.
     *
     * @return entity native invalid id.
     */
    protected abstract int getInvalidId();

    /**
     * Destroy this entity natively.
     *
     * @param playerId player to destroy entity.
     * @param entityId entity id.
     */
    protected abstract void destroyNatively(int playerId, int entityId);

    /**
     * Used to apply this entity state based on implementation variables.
     * Called after a successfully creation.
     *
     * @param playerId player id.
     * @param entityId entity id.
     */
    protected void applyState(int playerId, int entityId) { }

    /**
     * Save this entity state, in case is need.
     *
     * @param playerId player id.
     * @param entityId entity id.
     */
    protected void saveState(int playerId, int entityId) { }

    /**
     * Get this entity native storage array.
     *
     * @return an array.
     */
    protected abstract Object[][] getArrayNativeIDS();
}
