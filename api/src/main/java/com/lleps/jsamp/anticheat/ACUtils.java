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
public class ACUtils {
    public static int getWeaponSlot(int id) {
        if (id == 0 || id == 1) return 0;
        if (id >= 2 && id <= 9) return 1;
        if (id >= 10 && id <= 15) return 10;
        if (id >= 16 && id <= 18 || id == 39) return 8;
        if (id >= 22 && id <= 24) return 2;
        if (id >= 25 && id <= 27) return 3;
        if (id == 28 || id == 29 || id == 32) return 4;
        if (id == 30 || id == 31) return 5;
        if (id == 33 || id == 34) return 6;
        if (id >= 35 && id <= 38) return 7;
        if (id == 40) return 12;
        if (id >= 41 && id <= 43) return 9;
        if (id >= 44 && id <= 46) return 11;
        return 0;
    }

    public static boolean isPlaneModel(int modelId) {
        int planeModels[] = { 592, 577, 511, 512, 520, 593, 553, 476, 519, 460, 513, 548, 425, 417, 487, 488, 497, 563, 447, 469 };
        for (int model : planeModels) {
            if (model == modelId) return true;
        }
        return false;
    }

    public static boolean weaponSlotHoldsAmmo(int slot) {
        return slot >= 2 && slot <= 8;
    }
}