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
package com.lleps.jsamp.anticheat;

import com.lleps.jsamp.SAMPConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author spell
 */
public class ACPlayer {
    private final int id;

    private boolean connected;
    private boolean alive;

    private boolean spawnAllowed;
    private boolean alreadySpawned;
    private boolean jetpackAllowed;
    private boolean armourAllowed;

    private final Map<Integer, Boolean> weaponIdsAllowed = new HashMap<>();

    private final SynchronizableVar<Float> health = new SynchronizableVar<>(0f);
    private final SynchronizableVar<Float> armour = new SynchronizableVar<>(0f);
    private final SynchronizableVar<Integer> vehicleId = new SynchronizableVar<>(0);
    private final SynchronizableVar<float[]> position = new SynchronizableVar<>(new float[] {0, 0, 0});

    private final Map<Integer, SynchronizableVar<Integer>> weaponIds = new HashMap<>();

    private int weaponSlotsAmmo[] = new int[SAMPConstants.MAX_WEAPON_SLOTS];
    private int weaponSlotsMaxAmmo[] = new int[SAMPConstants.MAX_WEAPON_SLOTS];
    private long weaponSlotsAmmoLockTime[] = new long[SAMPConstants.MAX_WEAPON_SLOTS];

    public ACPlayer(int id) {
        this.id = id;

        for (int weaponSlot = 0; weaponSlot < SAMPConstants.MAX_WEAPON_SLOTS; weaponSlot++) {
            weaponIds.put(weaponSlot, new SynchronizableVar<>(0));
        }
    }

    public int getId() {
        return id;
    }

    public SynchronizableVar<float[]> getPosition() {
        return position;
    }

    public void setWeaponSlotAmmo(int slot, int ammo) {
        weaponSlotsAmmo[slot] = ammo;
    }

    public int getWeaponSlotAmmo(int slot) {
        return weaponSlotsAmmo[slot];
    }

    public void setWeaponSlotMaxAmmo(int slot, int ammo) {
        weaponSlotsMaxAmmo[slot] = ammo;
    }

    public int getWeaponSlotMaxAmmo(int slot) {
        return weaponSlotsMaxAmmo[slot];
    }

    public void setWeaponSlotAmmoLockTime(int slot, long time) {
        weaponSlotsAmmoLockTime[slot] = time;
    }

    public long getWeaponSlotAmmoLockTime(int slot) {
        return weaponSlotsAmmoLockTime[slot];
    }

    public void setLegalWeapon(int weaponId, boolean legal) {
        weaponIdsAllowed.put(weaponId, legal);
    }

    public boolean isLegalWeapon(int weaponId) {
        return weaponIdsAllowed.get(weaponId);
    }

    public SynchronizableVar<Integer> getWeaponInSlot(int slot) {
        return weaponIds.get(slot);
    }

    public void setArmourAllowed(boolean legalArmour) {
        this.armourAllowed = legalArmour;
    }

    public boolean isArmourAllowed() {
        return armourAllowed;
    }

    public void setJetpackAllowed(boolean jetpackAllowed) {
        this.jetpackAllowed = jetpackAllowed;
    }

    public boolean isJetpackAllowed() {
        return jetpackAllowed;
    }

    public SynchronizableVar<Float> getHealth() {
        return health;
    }

    public SynchronizableVar<Float> getArmour() {
        return armour;
    }

    public SynchronizableVar<Integer> getVehicleId() {
        return vehicleId;
    }

    public void setAlreadySpawned(boolean alreadySpawned) {
        this.alreadySpawned = alreadySpawned;
    }

    public boolean isAlreadySpawned() {
        return alreadySpawned;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setSpawnAllowed(boolean spawnAllowed) {
        this.spawnAllowed = spawnAllowed;
    }

    public boolean isSpawnAllowed() {
        return spawnAllowed;
    }
}