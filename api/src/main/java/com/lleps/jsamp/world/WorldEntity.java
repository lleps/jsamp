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

import com.lleps.jsamp.data.Vector;
import com.lleps.jsamp.data.Vector3D;
import com.lleps.jsamp.player.Player;

/**
 * World entity abstraction (Labels, Vehicles, etc..).
 *
 * @author spell
 */
public interface WorldEntity {
    /**
     * This interface is a listener used for report position changes.
     */
    interface OnPositionChangeListener {
        void onPositionChange(WorldEntity entity, Vector3D oldPosition, Vector3D newPosition);
    }

    /**
     * Add a listener to notify when position changes.
     *
     * @param listener the listener.
     */
    void addOnPositionChangeListener(OnPositionChangeListener listener);

    /**
     * Removes a position change listener.
     *
     * @param listener listener to remove.
     */
    void removeOnPositionChangeListener(OnPositionChangeListener listener);

    /**
     * Get the world position of this entity. Used by world implementations for streaming.
     *
     * @return entity position.
     */
    Vector getPosition();

    /**
     * Create this entity. Note that you should check if this entity isn't created
     * yet, to avoid creating two times the same object.
     *
     * @param player player to create the entity.
     * @param world world to create entity.
     * @return true if this entity was successfully created, false otherwise.
     */
    boolean create(Player player, World world);

    /**
     * Check if this entity is created for a player.
     *
     * @param player the player.
     * @return true if created, false otherwise.
     */
    boolean isCreated(Player player);

    /**
     * Destroy this entity for a player. No need of check if is created or not, because
     * when getting entity id, the implementation already checks if is created or not.
     *
     * @param player the player.
     */
    void destroy(Player player);

    /**
     * Get this entity world. Null if none.
     *
     * @return entity world.
     */
    World getWorld();
}