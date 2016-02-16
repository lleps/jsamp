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
public class VehicleParams implements Cloneable, VehicleProperty {
    private boolean engineOn;
    private boolean lightsOn;
    private boolean alarmOn;
    private boolean doorsLocked;
    private boolean bonnetOpened;
    private boolean bootOpened;
    private boolean objectiveOn;

    public VehicleParams() {
    }

    public VehicleParams(boolean[] array) {
        this.engineOn = array[0];
        this.lightsOn = array[1];
        this.alarmOn = array[2];
        this.doorsLocked = array[3];
        this.bonnetOpened = array[4];
        this.bootOpened = array[5];
        this.objectiveOn = array[6];
    }

    public VehicleParams(boolean engineOn, boolean lightsOn, boolean alarmOn, boolean doorsLocked, boolean bonnetOpened, boolean bootOpened, boolean objectiveOn) {
        this.engineOn = engineOn;
        this.lightsOn = lightsOn;
        this.alarmOn = alarmOn;
        this.doorsLocked = doorsLocked;
        this.bonnetOpened = bonnetOpened;
        this.bootOpened = bootOpened;
        this.objectiveOn = objectiveOn;
    }

    public boolean isEngineOn() {
        return engineOn;
    }

    public VehicleParams setEngineOn(boolean engineOn) {
        this.engineOn = engineOn;
        return this;
    }


    public boolean isLightsOn() {
        return lightsOn;
    }

    public VehicleParams setLightsOn(boolean lightsOn) {
        this.lightsOn = lightsOn;
        return this;
    }

    public boolean isAlarmOn() {
        return alarmOn;
    }

    public VehicleParams setAlarmOn(boolean alarmOn) {
        this.alarmOn = alarmOn;
        return this;
    }

    public boolean isDoorsLocked() {
        return doorsLocked;
    }

    public VehicleParams setDoorsLocked(boolean doorsLocked) {
        this.doorsLocked = doorsLocked;
        return this;
    }

    public boolean isBonnetOpened() {
        return bonnetOpened;
    }

    public VehicleParams setBonnetOpened(boolean bonnetOpened) {
        this.bonnetOpened = bonnetOpened;
        return this;
    }

    public boolean isBootOpened() {
        return bootOpened;
    }

    public VehicleParams setBootOpened(boolean bootOpened) {
        this.bootOpened = bootOpened;
        return this;
    }

    public boolean isObjectiveOn() {
        return objectiveOn;
    }

    public VehicleParams setObjectiveOn(boolean objectiveOn) {
        this.objectiveOn = objectiveOn;
        return this;
    }

    public boolean isAllOff() {
        return !engineOn && !lightsOn && !alarmOn && !doorsLocked
                && !bonnetOpened && !objectiveOn;
    }

    public boolean[] asArray() {
        return new boolean[] {engineOn, lightsOn, alarmOn, doorsLocked, bonnetOpened, bootOpened, objectiveOn};
    }

    @Override
    public String toString() {
        return "{" +
                "engineOn=" + engineOn +
                ", lightsOn=" + lightsOn +
                ", alarmOn=" + alarmOn +
                ", doorsLocked=" + doorsLocked +
                ", bonnetOpened=" + bonnetOpened +
                ", bootOpened=" + bootOpened +
                ", objectiveOn=" + objectiveOn +
                '}';
    }

    @Override
    public VehicleParams clone()  {
        try {
            return (VehicleParams) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public void apply(int vehicleId) {
        FunctionAccess.SetVehicleParamsEx(vehicleId, engineOn, lightsOn, alarmOn, doorsLocked, bonnetOpened,
                bootOpened, objectiveOn);
    }
}