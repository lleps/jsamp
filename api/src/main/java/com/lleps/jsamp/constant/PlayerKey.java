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

import com.lleps.jsamp.SAMPConstants;

/**
 * @author spell
 */
public enum PlayerKey {

    KEY_ACTION(SAMPConstants.KEY_ACTION, "~k~~PED_ANSWER_PHONE~", "~k~~VEHICLE_FIREWEAPON_ALT~", "TAB", "Alt gr / NUM0"),

    KEY_CROUCH(SAMPConstants.KEY_CROUCH, "~k~~PED_DUCK~", "~k~~VEHICLE_HORN~", "C", "H / BlockMayus"),

    KEY_FIRE(SAMPConstants.KEY_FIRE, "~k~~PED_FIREWEAPON~", "~k~~VEHICLE_FIREWEAPON~", "Click / Ctrl izquierdo", "Alt izquierdo"),

    KEY_SPRINT(SAMPConstants.KEY_SPRINT, "~k~~PED_SPRINT~", "~k~~VEHICLE_ACCELERATE~", "Espacio", "W"),

    KEY_SECONDARY_ATTACK(SAMPConstants.KEY_SECONDARY_ATTACK, "~k~~VEHICLE_ENTER_EXIT~", "~k~~VEHICLE_ENTER_EXIT~", "Enter", "Enter"),

    KEY_JUMP(SAMPConstants.KEY_JUMP, "~k~~PED_JUMPING~", "~k~~VEHICLE_BRAKE~", "Shift izquierdo", "S"),

    KEY_LOOK_RIGHT(SAMPConstants.KEY_LOOK_RIGHT, "", "~k~~VEHICLE_LOOKRIGHT~", "", "E"),

    KEY_HANDBRAKE(SAMPConstants.KEY_HANDBRAKE, "~k~~PED_LOCK_TARGET~", "~k~~VEHICLE_HANDBRAKE~", "Segundo click", "Espacio"),

    KEY_LOOK_LEFT(SAMPConstants.KEY_LOOK_LEFT, "", "~k~~VEHICLE_LOOKLEFT~	", "", "Q"),

    KEY_LOOK_BEHIND(SAMPConstants.KEY_LOOK_BEHIND, "~k~~PED_LOOKBEHIND~", "~k~~VEHICLE_LOOKBEHIND~", "NUM1 / Rueda del mouse", "2"),

    KEY_SUBMISSION(SAMPConstants.KEY_SUBMISSION, "", "~k~~TOGGLE_SUBMISSIONS~", "NUM1 / Rueda del mouse", "2 / NUMPAD"),

    KEY_WALK(SAMPConstants.KEY_WALK, "~k~~SNEAK_ABOUT~", "", "Alt izquierdo", ""),

    KEY_ANALOG_UP(SAMPConstants.KEY_ANALOG_UP, "", "~k~~VEHICLE_TURRETUP~", "NUM8", "NUM8"),

    KEY_ANALOG_DOWN(SAMPConstants.KEY_ANALOG_DOWN, "", "~k~~VEHICLE_TURRETDOWN~", "NUM2", "NUM2"),

    KEY_ANALOG_LEFT(SAMPConstants.KEY_ANALOG_LEFT, "~k~~VEHICLE_LOOKLEFT~", "~k~~VEHICLE_TURRETLEFT~", "NUM4", "NUM4"),

    KEY_ANALOG_RIGHT(SAMPConstants.KEY_ANALOG_RIGHT, "~k~~VEHICLE_LOOKRIGHT~", "~k~~VEHICLE_TURRETRIGHT~", "NUM6", "NUM6"),

    KEY_YES(SAMPConstants.KEY_YES, "~k~~CONVERSATION_YES~", "~k~~CONVERSATION_YES~", "Y", "Y"),

    KEY_NO(SAMPConstants.KEY_NO, "~k~~CONVERSATION_NO~", "~k~~CONVERSATION_NO~", "N", "N"),

    KEY_CTRL_BACK(SAMPConstants.KEY_CTRL_BACK, "~k~~GROUP_CONTROL_BWD~", "~k~~GROUP_CONTROL_BWD~", "H", "H"),

    KEY_UP(SAMPConstants.KEY_UP, "~k~~GO_FORWARD~", "~k~~VEHICLE_STEERUP~", "W", "W"),

    KEY_DOWN(SAMPConstants.KEY_DOWN, "~k~~GO_BACK~", "~k~~VEHICLE_STEERDOWN~", "S", "S"),

    KEY_LEFT(SAMPConstants.KEY_LEFT, "~k~~GO_LEFT~", "~k~~VEHICLE_STEERLEFT~", "A", "A"),

    KEY_RIGHT(SAMPConstants.KEY_RIGHT, "~k~~GO_RIGHT~", "~k~~VEHICLE_STEERRIGHT~", "D", "D");

    private int value;
    private String onFootCode;
    private String inVehicleCode;
    private String defaultOnFoot;
    private String defaultInVehicle;

    PlayerKey(int value, String onFootCode, String inVehicleCode, String defaultOnFoot, String defaultInVehicle) {
        this.value = value;
        this.onFootCode = onFootCode;
        this.inVehicleCode = inVehicleCode;
        this.defaultOnFoot = defaultOnFoot;
        this.defaultInVehicle = defaultInVehicle;
    }

    public int getValue() {
        return value;
    }

    public String getOnFootCode() {
        return onFootCode;
    }

    public String getInVehicleCode() {
        return inVehicleCode;
    }

    public String getDefaultOnFoot() {
        return defaultOnFoot;
    }

    public String getDefaultInVehicle() {
        return defaultInVehicle;
    }
}
