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
 */package com.lleps.jsamp.world.entity;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.lleps.jsamp.SAMPFunctions;
import com.lleps.jsamp.player.Player;
import com.lleps.jsamp.server.EventDispatcher;
import com.lleps.jsamp.server.SAMPServer;
import com.lleps.jsamp.world.World;

import java.util.HashSet;
import java.util.Set;

/**
 * @author spell
 */
public abstract class GlobalEntity extends WorldEntity implements EventDispatcher.OnPlayerIdDisconnectListener {
    /**
     * This set will store all player ids with this entity streamed.
     * No matters if the entity creation was successfully or not, the
     * player will be in the list if you call create(player) and will
     * be removed if you call destroy.
     */
    protected final Set<Integer> playerIds = new HashSet<>();
    protected int id = getInvalidId();

    protected int worldId;
    protected int interiorId;

    protected GlobalEntity() {
    }

    @Override
    public boolean create(int playerId, int worldId, int interiorId) {
        playerIds.add(playerId);
        SAMPServer.getEventDispatcher().addOnPlayerIdDisconnectListener(playerId, this);

        if (isCreated()) return true;

        id = createNatively(worldId);
        if (isCreated()) {

            this.worldId = worldId;
            this.interiorId = interiorId;

            getIDSArray()[id] = this;
            applyState(id, worldId, interiorId);
            return true;
        }

        return false;
    }

    @Override
    public void destroy(int playerId) {
        playerIds.remove(playerId);
        SAMPServer.getEventDispatcher().removeOnPlayerIdDisconnectListener(playerId, this);

        checkForDestroyAndSave();
    }

    @Override
    public boolean isCreated(int playerId) {
        return isCreated();
    }

    @Override
    public World getWorld() {
        return world;
    }

    /**
     * Get this entity invalid id. Will be used to determine if entity creation
     * was successfully or not, also to check if an entity is created, making a
     * comparison between entity id and invalid id.
     *
     * @return entity id.
     */
    protected abstract int getInvalidId();

    /**
     * Create this entity natively. Only creation should be performed here.
     *
     * @param worldId virtual world id in case is needed (pickups need it).
     * @return created id.
     */
    protected abstract int createNatively(int worldId);

    /**
     * Used to "load" an entity state after creation. For example, when a vehicle
     * is created, applyState should set vehicle tunning, paintjob, virtual world, etc.
     *
     * @param id entity id.
     * @param worldId virtual world id.
     * @param interiorId interior id.
     */
    protected void applyState(int id, int worldId, int interiorId) { }

    /**
     * Destroy this entity natively.
     *
     * @param id entity id.
     */
    protected abstract void destroyNatively(int id);

    /**
     * Ask the object for their array reference, to set it to "this" when created (the ObjectNativeIDS will
     * know our object) and set to null when destroyed.
     * @return
     */
    protected abstract Object[] getIDSArray();

    /**
     * Used to save this object state when is destroyed (for example, to store a vehicle position).
     *
     * @param id entity id.
     */
    protected void saveState(int id) { }

    /**
     * Returns true if this entity is created.
     *
     * @return true if created, false otherwise.
     */
    protected boolean isCreated() {
        return id != getInvalidId();
    }

    /**
     * In case this entity is created, this method will destroy it and create it again. It is used for
     * cases when there isn't a native method to change a property. For example, if you want to change
     * a vehicle model you must recreate the vehicle to make the change visible.
     */
    public void recreate() {
        if (isCreated()) {
            for (int playerId : Sets.newHashSet(playerIds)) {
                destroy(playerId);
                create(playerId, worldId, interiorId);
            }
        }
    }

    /**
     * Get this entity id. Can be invalid.
     *
     * @return this entity id.
     */
    public int getId() {
        return id;
    }

    @Override
    public void onPlayerIdDisconnect(int playerId, int reason) {
        playerIds.remove(playerId);
        checkForDestroyAndSave();
    }

    private void checkForDestroyAndSave() {
        if (isCreated() && playerIds.isEmpty()) {
            saveState(id);
            destroyNatively(id);
            getIDSArray()[id] = null;
            id = getInvalidId();
        }
    }
}