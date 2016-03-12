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
package com.lleps.jsamp.data;

import com.lleps.jsamp.constant.PlayerKey;

/**
 * @author spell
 */
public class KeyState {
    int leftRight = 0;
    int upDown = 0;
    int keys;

    public KeyState(int keys) {
        this.keys = keys;
    }

    public KeyState(int[] state) {
        leftRight = state[0];
        upDown = state[1];
        keys = state[2];
    }

    public boolean isPressed(PlayerKey key) {
        return (keys & key.getValue()) != 0;
    }

    public boolean isOnlyPressed(PlayerKey key) {
        return (keys == key.getValue());
    }

    public boolean isNothingPressed() {
        return (keys == 0);
    }

    public boolean isArrowPressed(PlayerKey key) {
        if (key == PlayerKey.KEY_UP || key == PlayerKey.KEY_DOWN) {
            return (upDown & key.getValue()) != 0;
        }
        if (key == PlayerKey.KEY_LEFT || key == PlayerKey.KEY_RIGHT) {
            return (leftRight & key.getValue()) != 0;
        }
        return false;
    }

    public int getLeftRight() {
        return leftRight;
    }

    public int getUpDown() {
        return upDown;
    }

    public int getKeys() {
        return keys;
    }
}
