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
import com.lleps.jsamp.constant.VehiclePart;

import java.util.Arrays;

/**
 * @author spell
 */
public class DoorState implements VehicleProperty, Cloneable {
    private boolean[] doorOpened = new boolean[4];

    @Override
    public void apply(int vehicleId) {
        FunctionAccess.SetVehicleParamsCarDoors(vehicleId, doorOpened[0], doorOpened[1], doorOpened[2], doorOpened[3]);
    }

    public void setDoorOpened(VehiclePart door, boolean opened) {
        doorOpened[door.getId()] = opened;
    }

    public boolean isDoorOpened(VehiclePart door) {
        return doorOpened[door.getId()];
    }

    public void setAllOpened(boolean opened) {
        Arrays.fill(doorOpened, opened);
    }

    /**
     * @return true if all doors are closed, false otherwise.
     */
    public boolean isDefaultState() {
        for (boolean doorOpened : this.doorOpened) {
            if (doorOpened) return false;
        }
        return true;
    }

    @Override
    public DoorState clone() {
        try {
            return (DoorState) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}