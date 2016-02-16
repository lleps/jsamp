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
public enum BodyPart {

    TORSO(3),

    GROIN(4),

    LEFT_ARM(5),

    RIGHT_ARM(6),

    LEFT_LEG(7),

    RIGHT_LEG(8),

    HEAD(9);

    private int value;

    public static BodyPart get(int value) {
        for (BodyPart bodyPart : values()) {
            if (bodyPart.getValue() == value) {
                return bodyPart;
            }
        }
        return null;
    }

    BodyPart(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
