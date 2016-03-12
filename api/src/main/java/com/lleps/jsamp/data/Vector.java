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

/**
 * @author spell
 */
public interface Vector {
    boolean isNull();
    float length();
    float distanceTo(Vector target);

    Vector plus(Vector other);
    Vector minus(Vector other);

    float getX();
    Vector plusX(float amount);
    Vector minusX(float amount);
    Vector withX(float value);

    float getY();
    Vector plusY(float amount);
    Vector minusY(float amount);
    Vector withY(float value);

    float getZ();
    Vector plusZ(float amount);
    Vector minusZ(float amount);
    Vector withZ(float value);

    float getW();
    Vector plusW(float amount);
    Vector minusW(float amount);
    Vector withW(float value);
}
