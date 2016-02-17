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
 * @author spell
 */
public class PlayerSlot {
    private boolean connected;
    private boolean alive;
    private boolean legalSpawned;

    public void resetAll() {
        connected = true;
        alive = false;
        legalSpawned = false;
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

    public void setLegalSpawned(boolean legalSpawned) {
        this.legalSpawned = legalSpawned;
    }

    public boolean isLegalSpawned() {
        return legalSpawned;
    }
}