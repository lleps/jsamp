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

import com.lleps.jsamp.data.Vector;
import com.lleps.jsamp.data.Vector3D;
import com.lleps.jsamp.world.World;
import com.lleps.jsamp.world.entity.WorldEntity;

import java.util.Collection;
import java.util.LinkedList;

/**
 * World entity abstraction (Labels, Vehicles, etc).
 *
 * @author spell
 */
public abstract class WorldEntity {
    /**
     * This interface is a listener used for report position changes.
     */
    public interface OnPositionChangeListener {
        void onPositionChange(WorldEntity entity, Vector3D oldPosition, Vector3D newPosition);
    }

    private final Collection<OnPositionChangeListener> onPositionChangeListeners = new LinkedList<>();;
    protected World world;

    protected WorldEntity() {
    }

    /**
     * Add a listener to notify when position changes.
     *
     * @param listener the listener to add.
     */
    public void addOnPositionChangeListener(OnPositionChangeListener listener) {
        onPositionChangeListeners.add(listener);
    }

    /**
     * Removes a position change listener.
     *
     * @param listener listener to remove.
     */
    public void removeOnPositionChangeListener(OnPositionChangeListener listener) {
        onPositionChangeListeners.remove(listener);
    }

    protected void dispatchPositionChange(Vector3D oldPosition, Vector3D newPosition) {
        onPositionChangeListeners.forEach(l -> l.onPositionChange(this, oldPosition, newPosition));
    }

    /**
     * @param world set world reference (only reference - it doesn't change the world or create the entity).
     */
    public void setWorldReference(World world) {
        this.world = world;
    }

    /**
     * @return get world reference.
     */
    public World getWorld() {
        return world;
    }

    /**
     * @return entity world position.
     */
    public abstract Vector3D getPosition();

    /**
     * Create this entity for a player.
     *
     * @param playerId player.
     * @param worldId virtual world.
     * @return true if this entity was successfully created, false otherwise.
     */
    public abstract boolean create(int playerId, int worldId, int interiorId);

    /**
     * Check if this entity is created for a player.
     *
     * @param playerId the player.
     * @return true if created, false otherwise.
     */
    public abstract boolean isCreated(int playerId);

    /**
     * Destroy this entity for a player.
     *
     * @param playerId the player.
     */
    public abstract void destroy(int playerId);
}