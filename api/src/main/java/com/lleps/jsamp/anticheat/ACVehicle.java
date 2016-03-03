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
package com.lleps.jsamp.anticheat;

/**
 * @author spell
 */
public class ACVehicle {
    private final int id;

    private int color1;
    private int color2;

    private boolean doorsLocked;

    public ACVehicle(int id, int color1, int color2) {
        this.id = id;
        this.color1 = color1;
        this.color2 = color2;
    }

    public int getId() {
        return id;
    }

    public void setDoorsLocked(boolean doorsLocked) {
        this.doorsLocked = doorsLocked;
    }

    public boolean isDoorsLocked() {
        return doorsLocked;
    }

    public void setColor1(int color1) {
        this.color1 = color1;
    }

    public int getColor1() {
        return color1;
    }

    public void setColor2(int color2) {
        this.color2 = color2;
    }

    public int getColor2() {
        return color2;
    }
}