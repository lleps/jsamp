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
public enum SpeedUnit {
    NATIVE(1),
    KM_PER_HOUR(160.2f),
    MILES_PER_HOUR(100.125f);

    private float constant;

    SpeedUnit(float constant) {
        this.constant = constant;
    }

    public float getConstant() {
        return constant;
    }

    public float forNativeSpeed(float value) {
        return value * constant;
    }
}