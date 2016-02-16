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
package com.lleps.jsamp.constant;

import com.lleps.jsamp.data.Vector3D;

/**
 * Abstraction of SA:MP interior id, with some optional useful information like a spawn point.
 * This class is immutable.
 *
 * @author spell
 */
public final class Interior {
    private final int id;
    private final Vector3D spawnPosition;
    private final Float spawnAngle;

    public Interior(int id) {
        this(id, null, null);
    }

    public Interior(int id, Vector3D spawnPosition, Float spawnAngle) {
        this.id = id;
        this.spawnPosition = spawnPosition;
        this.spawnAngle = spawnAngle;
    }

    public int getId() {
        return id;
    }

    public Float getSpawnAngle() {
        return spawnAngle;
    }

    public Vector3D getSpawnPosition() {
        return spawnPosition;
    }
}
