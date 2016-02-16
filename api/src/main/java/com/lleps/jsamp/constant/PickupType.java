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
public enum PickupType {

    EXISTS_ALWAYS(1),
    DISAPPEAR_30SECONDS(2),
    DISAPPEAR_AFTER_DEATH(3),
    DISAPPEAR_20SECONDS(4),
    DISAPPEAR_AFTER_PICKUP(8),
    BOMB_1(11),
    BOMB_2(12),
    VEHICLE_PICKUP(14);

    int nativeType;

    PickupType(int nativeType) {
        this.nativeType = nativeType;
    }

    public int getNativeType() {
        return nativeType;
    }
}
