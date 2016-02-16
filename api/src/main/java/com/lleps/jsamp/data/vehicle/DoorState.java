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
package com.lleps.jsamp.data.vehicle;

import com.lleps.jsamp.FunctionAccess;

/**
 * @author spell
 */
public class DoorState extends BaseVehiclePartState {

    public DoorState() {
    }

    public DoorState(boolean[] opened) {
        super(opened);
    }

    @Override
    public void apply(int vehicleId) {
        FunctionAccess.SetVehicleParamsCarDoors(vehicleId, state[0], state[1], state[2], state[3]);
    }

    @Override
    public DoorState clone() {
        return (DoorState) super.clone();
    }
}