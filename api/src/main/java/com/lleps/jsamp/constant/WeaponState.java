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

/**
 * @author spell
 */
public enum WeaponState {
    UNKNOWN(-1),
    NO_BULLET(0),
    LAST_BULLET(1),
    MORE_BULLETS(2),
    RELOADING(3);

    int id;

    WeaponState(int id) {
        this.id = id;
    }

    WeaponState getById(int id) {
        for (WeaponState state : values()) {
            if (state.id == id) return state;
        }
        return UNKNOWN;
    }

    public int getId() {
        return id;
    }
}