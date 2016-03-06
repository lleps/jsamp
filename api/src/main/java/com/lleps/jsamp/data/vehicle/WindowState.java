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
public class WindowState implements VehicleProperty, Cloneable {
    private boolean[] windowOpened = new boolean[4];

    @Override
    public void apply(int vehicleId) {
        // Revert state, since for SA-MP, true is "closed" and false is "opened".
        FunctionAccess.SetVehicleParamsCarWindows(vehicleId, !windowOpened[0], !windowOpened[1], !windowOpened[2], !windowOpened[3]);
    }

    public void setWindowOpened(VehiclePart window, boolean opened) {
        windowOpened[window.getId()] = opened;
    }

    public boolean isWindowOpened(VehiclePart window) {
        return windowOpened[window.getId()];
    }

    public void setAllOpened(boolean opened) {
        Arrays.fill(windowOpened, opened);
    }

    @Override
    public WindowState clone() {
        try {
            return (WindowState)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * @return true if all windows are closed, false otherwise.
     */
    public boolean isDefaultState() {
        for (boolean windowOpened : this.windowOpened) {
            if (windowOpened) return false;
        }
        return true;
    }
}