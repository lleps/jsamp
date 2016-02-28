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

/**
 * An abstraction of a value that can be synced. See http://forum.sa-mp.com/showthread.php?t=220089
 * @author spell
 */
public class SynchronizableVar<T> {
    private boolean synced;
    private T shouldBe;
    private int syncTries;

    public SynchronizableVar(T initialValue) {
        shouldBe = initialValue;
    }

    /**
     * Sets synced to true and syncTries to 0
     */
    public void sync() {
        synced = true;
        syncTries = 0;
    }

    /**
     * Sets synced to false and syncTries to 0
     */
    public void unsync() {
        synced = false;
        syncTries = 0;
    }

    public void setSyncTries(int syncTries) {
        this.syncTries = syncTries;
    }

    public int getSyncTries() {
        return syncTries;
    }

    /**
     * @return Adds 1 to syncTries and return the increased value.
     */
    public int increaseSyncTries() {
        return ++syncTries;
    }

    public boolean isSynced() {
        return synced;
    }

    public T getShouldBe() {
        return shouldBe;
    }

    public void setShouldBe(T shouldBe) {
        this.shouldBe = shouldBe;
    }
}