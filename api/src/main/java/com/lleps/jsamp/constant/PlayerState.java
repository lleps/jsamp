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
public enum PlayerState {
    PLAYER_STATE_NONE(0),
    PLAYER_STATE_ONFOOT(1),
    PLAYER_STATE_DRIVER(2),
    PLAYER_STATE_PASSENGER(3),
    PLAYER_STATE_EXIT_VEHICLE(4),
    PLAYER_STATE_ENTER_VEHICLE_DRIVER(5),
    PLAYER_STATE_ENTER_VEHICLE_PASSENGER(6),
    PLAYER_STATE_WASTED(7),
    PLAYER_STATE_SPAWNED(8),
    PLAYER_STATE_SPECTATING(9);

    public static PlayerState get(int value) {
        return values()[value];
    }

    private int value;

    PlayerState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
