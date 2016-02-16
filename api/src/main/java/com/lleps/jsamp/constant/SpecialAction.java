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
public enum SpecialAction {
    SPECIAL_ACTION_NONE(0),
    SPECIAL_ACTION_DUCK(1),
    SPECIAL_ACTION_USEJETPACK(2),
    SPECIAL_ACTION_ENTER_VEHICLE(3),
    SPECIAL_ACTION_EXIT_VEHICLE(4),
    SPECIAL_ACTION_DANCE1(5),
    SPECIAL_ACTION_DANCE2(6),
    SPECIAL_ACTION_DANCE3(7),
    SPECIAL_ACTION_DANCE4(8),
    SPECIAL_ACTION_HANDSUP(10),
    SPECIAL_ACTION_USECELLPHONE(11),
    SPECIAL_ACTION_SITTING(12),
    SPECIAL_ACTION_STOPUSECELLPHONE(13),
    SPECIAL_ACTION_DRINK_BEER(20),
    SPECIAL_ACTION_SMOKE_CIGGY(21),
    SPECIAL_ACTION_DRINK_WINE(22),
    SPECIAL_ACTION_DRINK_SPRUNK(23),
    SPECIAL_ACTION_PISSING(68);

    public static SpecialAction get(int value) {
        for (SpecialAction specialAction : values()) {
            if (specialAction.value == value) {
                return specialAction;
            }
        }
        return null;
    }

    private int value;

    SpecialAction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
