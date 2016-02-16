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
public class VehicleDamageState implements VehicleProperty, Cloneable {
    // TODO: Fix this class.
    public static final int TIRE_DRIVER = 0;
    public static final int TIRE_PASSENGER = 1;
    public static final int TIRE_BEHIND_DRIVER = 2;
    public static final int TIRE_BEHIND_PASSENGER = 3;

    public static final int LIGHT_DRIVER = 0;
    public static final int LIGHT_PASSENGER = 1;
    public static final int LIGHT_BEHIND_DRIVER = 2;
    public static final int LIGHT_BEHIND_PASSENGER = 3;

    public static final int DOOR_BONNET = 0;
    public static final int DOOR_BOOT = 1;
    public static final int DOOR_DRIVER = 2;
    public static final int DOOR_PASSENGER = 2;

    public static final int DOORSTATE_FIXED =   0b000;
    public static final int DOORSTATE_OPENED =  0b001;
    public static final int DOORSTATE_DAMAGED = 0b010;
    public static final int DOORSTATE_REMOVED = 0b100;

    public static final int PANEL_FLP = 0;
    public static final int PANEL_FRP = 1;
    public static final int PANEL_RLP = 2;
    public static final int PANEL_RRP = 3;
    public static final int PANEL_WINDSHIELD = 4;
    public static final int PANEL_FRONT_BUMPER = 5;
    public static final int PANEL_REAR_BUMPER = 6;

    public static final int PANELSTATE_FIXED =    0b0000;
    public static final int PANELSTATE_DAMAGED =  0b0001;
    public static final int PANELSTATE_DETACHED = 0b0010;


    private int panels;
    private int doors;
    private int lights;
    private int tires;

    public VehicleDamageState() {
    }

    public VehicleDamageState(int[] data) {
        this(data[0], data[1], data[2], data[3]);
    }

    public VehicleDamageState(int panels, int doors, int lights, int tires) {
        this.panels = panels;
        this.doors = doors;
        this.lights = lights;
        this.tires = tires;
    }

    /**
     * Set popping for a tire.
     *
     * @param tire which tire. Must be one of "TIRE_" class constants.
     * @param popped true for make the tire popped, false otherwise.
     */
    public void setTirePopped(int tire, boolean popped) {
        switch (tire) {
            case 0:
                setTires(popped, isTirePopped(1), isTirePopped(2), isTirePopped(3));
                break;
            case 1:
                setTires(isTirePopped(0), popped, isTirePopped(2), isTirePopped(3));
                break;
            case 2:
                setTires(isTirePopped(0), isTirePopped(1), popped, isTirePopped(3));
                break;
            case 3:
                setTires(isTirePopped(0), isTirePopped(1), isTirePopped(2), popped);
                break;
        }
    }

    /**
     * Check if a tire is popped.
     *
     * @param tire which tire. Must be one of "TIRE_" constants from this class.
     * @return true if popped, false otherwise.
     */
    public boolean isTirePopped(int tire) {
        return ((tires >> tire) & 1) != 0;
    }

    public int getTires() {
        return tires;
    }

    public void setTires(int tires) {
        this.tires = tires;
    }

    public void setAllTiresPopped(boolean popped) {
        tires = popped ? 0b1111 : 0b0000;
    }

    /**
     * Set a light state. True means the light is destroyed.
     *
     * @param light an integer, must be one of "LIGHT_" constants from this class.
     * @param destroyed true for make a light destroyed, false otherwise.
     */
    public void setLightDestroyed(int light, boolean destroyed) {
        switch (light) {
            case 0:
                setLights(destroyed, isTirePopped(1), isTirePopped(2), isTirePopped(3));
                break;
            case 1:
                setLights(isTirePopped(0), destroyed, isTirePopped(2), isTirePopped(3));
                break;
            case 2:
                setLights(isTirePopped(0), isTirePopped(1), destroyed, isTirePopped(3));
                break;
            case 3:
                setLights(isTirePopped(0), isTirePopped(1), isTirePopped(2), destroyed);
                break;
        }
    }

    /**
     * Check a light state. True means destroyed, false means the light is ok.
     *
     * @param light which light. Must be one of "LIGHT_" constants from this clas..
     * @return true if light is destroyed, false otherwise.
     */
    public boolean isLightDestroyed(int light) {
        return ((lights >> light) & 1) != 0;
    }

    public void setAllLights(boolean destroyed) {
        lights = destroyed ? 0b1111 : 0b0000;
    }

    public void setLights(int lights) {
        this.lights = lights;
    }

    public int getLights() {
        return lights;
    }

    /**
     * Set a door state. Note that boot and bonnet are also doors.
     *
     * @param door which door you want to change. Use one of "DOOR_" class constants.
     * @param state door state, from "DOORSTATE_" class constants. You can
     *              use bitmasking for make the door have more than one state. For example,
     *              if you want to make a driver's door damaged and opened, you should do:
     *
     *              setDoorState(DOOR_DRIVER, DOORSTATE_DAMAGED | DOORSTATE_OPENED);
     */
    public void setDoorState(int door, int state) {
        switch (doors) {
            case 0:
                setDoors(state, getDoorState(1), getDoorState(2), getDoorState(3));
                break;
            case 1:
                setDoors(getDoorState(0), state, getDoorState(2), getDoorState(3));
                break;
            case 2:
                setDoors(getDoorState(0), getDoorState(1), state, getDoorState(3));
                break;
            case 3:
                setDoors(getDoorState(0), getDoorState(1), getDoorState(2), state);
                break;
        }
    }

    /**
     * Get a door state.
     *
     * @param door which door to get the state.
     * @return door state bits. You can use bitmasking for check door states, for example:
     *
     * if (getDoorState(DOOR_DRIVER) & (DOORSTATE_DAMAGED | DOORSTATE_OPENED)) {
     *     // vehicle door is damaged and opened.
     * }
     *
     * or, if you want to check for a single state:
     *
     * if (getDoorState(DOOR_BOOT) & DOOR_DETACHED) {
     *     // looks like vehicle lost his boot.
     * }
     *
     * Its recommended that you import all VehicleDamageState constants statically for make a better syntax.
     */
    public int getDoorState(int door) {
        return (doors >>> (8 * door));
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public int getDoors() {
        return doors;
    }

    /**
     * Set the same state for all doors.
     *
     * @param state state (can use bitmasks)
     * @see #setDoorState(int, int)
     */
    public void setAllDoorsState(int state) {
        setDoors(state, state, state, state);
    }

    /**
     * Sets a panel state.
     *
     * @param panel which panel. Must be one of "PANEL_" constants. Note that the only
     *              panels that seems to works is PANEL_REAR_BUMPER and PANEL_FRONT_BUMPER.
     *              PANEL_WINDSHIELD can be obtained but cannot be changed.
     * @param state panel state. You can use bitmasking for make panels have more than one state.
     *              All states can be accessed using "PANELSTATE_" constants.
     *
     *              Example of making a panel damaged and detached using bitmasks:
     *
     *              setPanelState(PANEL_REAR_BUMPER, PANELSTATE_DAMAGED | PANELSTATE_DETACHED)
     *
     *              Its recommended to import all constants statically, for a better syntax.
     */
    public void setPanelState(int panel, int state) {
        switch (panel) {
            case 0:
                setPanels(state, getPanelState(1), getPanelState(2), getPanelState(3), getPanelState(4),
                        getPanelState(5), getPanelState(6));
                break;
            case 1:
                setPanels(getPanelState(0), state, getPanelState(2), getPanelState(3), getPanelState(4),
                        getPanelState(5), getPanelState(6));
                break;
            case 2:
                setPanels(getPanelState(0), getPanelState(1), state, getPanelState(3), getPanelState(4),
                        getPanelState(5), getPanelState(6));
                break;
            case 3:
                setPanels(getPanelState(0), getPanelState(1), getPanelState(2), state, getPanelState(4),
                        getPanelState(5), getPanelState(6));
                break;
            case 4:
                setPanels(getPanelState(0), getPanelState(1), getPanelState(2), getPanelState(3), state,
                        getPanelState(5), getPanelState(6));
                break;
            case 5:
                setPanels(getPanelState(0), getPanelState(1), getPanelState(2), getPanelState(3), getPanelState(4),
                        state, getPanelState(6));
                break;
            case 6:
                setPanels(getPanelState(0), getPanelState(1), getPanelState(2), getPanelState(3), getPanelState(4),
                        getPanelState(5), state);
                break;
        }
    }

    /**
     * Get a panel state.
     *
     * @param panel which panel to get the state.
     * @return panel state bits. You can use bitmasking for check panel states, for example:
     *
     * if (getPanelState(PANEL_REAR_BUMPER) & (PANELSTATE_DAMAGED)) {
     *     // panel is damaged
     * }
     *
     * Its recommended that you import all VehicleDamageState constants statically for make a better syntax.
     */
    public int getPanelState(int panel) {
        return panels >>> (4 * panel);
    }

    public void setAllPanelsState(int state) {
        setPanels(state, state, state, state, state, state, state);
    }

    public void setPanels(int panels) {
        this.panels = panels;
    }

    public int getPanels() {
        return panels;
    }

    public void setTires(boolean tire1, boolean tire2, boolean tire3, boolean tire4) {
        lights = booleanToInt(tire1) | (booleanToInt(tire2) << 1) | (booleanToInt(tire3) << 2) | (booleanToInt(tire4) << 3);
    }

    public void setPanels(int flp, int frp, int rlp, int rrp, int windshield, int front_bumper, int rear_bumper) {
        panels = flp | (frp << 4) | (rlp << 8) | (rrp << 12) | (windshield << 16) | (front_bumper << 20) | (rear_bumper << 24);
    }

    public void setDoors(int bonnet, int boot, int driver_door, int passenger_door) {
        doors = bonnet | (boot << 8) | (driver_door << 16) | (passenger_door << 24);
    }

    public void setLights(boolean light1, boolean light2, boolean light3, boolean light4) {
        lights = booleanToInt(light1) | (booleanToInt(light2) << 1) | (booleanToInt(light3) << 2) | (booleanToInt(light4) << 3);
    }

    public boolean isAllFixed() {
        return panels == 0 && doors == 0 && lights == 0 && tires == 0;
    }

    public void fixAll() {
        panels = doors = lights = tires = 0;
    }

    @Override
    public VehicleDamageState clone() {
         try {
            return (VehicleDamageState)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    private static int booleanToInt(boolean value) {
        return value ? 1 : 0;
    }

    public int[] asArray() {
        return new int[] {panels, doors, lights, tires};
    }

    @Override
    public void apply(int vehicleId) {
        FunctionAccess.UpdateVehicleDamageStatus(vehicleId, panels, doors, lights, tires);
    }
}