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
public enum Bone {
    SPINE(1, "Columna"),
    HEAD(2, "Cabeza"),
    LEFT_UPPER_ARM(3, "Brazo izquierdo"),
    RIGHT_UPPER_ARM(4, "Brazo derecho"),
    LEFT_HAND(5, "Mano izquierda"),
    RIGHT_HAND(6, "Mano derecha"),
    LEFT_THIGH(7, "Muslo izquierdo"),
    RIGHT_THIGH(8, "Muslo derecho"),
    LEFT_FOOT(9, "Pie izquierdo"),
    RIGHT_FOOT(10, "Pie derecho"),
    RIGHT_CALF(11, "Pantorrilla derecha"),
    LEFT_CALF(12, "Pantorrilla izquierda"),
    LEFT_FOREARM(13, "Antebrazo izquierdo"),
    RIGHT_FOREARM(14, "Antebrazo derecho"),
    LEFT_CLAVICLE_SHOULDER(15, "Hombro izquierdo"),
    RIGHT_CLAVICLE_SHOULDER(16, "Hombro derecha"),
    NECK(17, "Cuello"),
    JAW(18, "Mandibula");

    private int value;
    private String description;

    public Bone getByValue(int value) {
        for (Bone bone : values()) {
            if (bone.value == value) {
                return bone;
            }
        }
        return null;
    }

    Bone(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}