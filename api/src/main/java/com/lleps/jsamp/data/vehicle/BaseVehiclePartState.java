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

import com.lleps.jsamp.constant.VehiclePart;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author spell
 */
public abstract class BaseVehiclePartState implements VehicleProperty, Cloneable {
    protected boolean[] state;

    protected BaseVehiclePartState() {
        this(new boolean[] {true, true, true, true});
    }

    protected BaseVehiclePartState(boolean[] state) {
        this.state = state;
    }

    public void setOpened(VehiclePart part, boolean opened) {
        state[part.getId()] = opened;
    }

    public boolean isOpened(VehiclePart part) {
        return state[part.getId()];
    }

    public boolean isAllClosed() {
        for (boolean b : state) {
            if (!b) return false;
        }
        return true;
    }

    public void setAllOpened(boolean opened) {
        Arrays.fill(state, opened);
    }

    @Override
    public BaseVehiclePartState clone() {
        try {
            return (BaseVehiclePartState) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "BaseVehiclePartState{" +
                "state=" + Arrays.toString(state) +
                '}';
    }
}
