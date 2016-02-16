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

import com.lleps.jsamp.constant.model.VehicleModel;

/**
 * @author spell
 */
public enum VehiclePart {
    // Cars
    DRIVER(0),
    PASSENGER(1),
    BACK_LEFT(2),
    BACK_RIGHT(3);

    private int id;

    public static VehiclePart get(int index) {
        return values()[index];
    }

    VehiclePart(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
