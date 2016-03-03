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

    public static float distanceBetweenPoints(float[] xyz1, float[] xyz2) {
        float dx = xyz1[0] - xyz2[0];
        float dy = xyz1[1] - xyz2[1];
        float dz = xyz1[2] - xyz2[2];
        return (float)Math.sqrt(dx*dx + dy*dy + dz*dz);
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

    public static int getVehicleModelMaxSpeed(int modelId) {
        return 1;
    }

    public static float get3DSpeed(float velocityX, float velocityY, float velocityZ) {
        return (float)Math.sqrt(velocityX*velocityX + velocityY*velocityY + velocityZ*velocityZ) * 160.2f; // Magic constant!
    }

    private static final float modshopInteriors[][] = {
            {617, -2, 1000}, //transfender
            {615, -124, 997}, // wheelarchs
            {616, -74, 997} // lowrider
    };

    private static final float modshopExteriors[][] = {
            {-1936, 244, 34}, //transfender sf
            {1041, -1021, 31}, //transfender ls
            {2386, 1051, 10}, //transfender lv
            {-2719, 216, 4}, //wheelarchs
            {2644, -2043, 13} //lowrider
    };

    private static final float payNSprays[][] = {
            {719.8571f, -455.8376f, 16.0450f},
            {-1420.4935f, 2584.8137f, 55.5545f},
            {-99.8769f, 1116.7948f, 19.4567f},
            {2065.9587f, -1831.2217f, 13.2568f},
            {-2425.9490f, 1021.5446f, 50.1055f},
            {1975.1755f, 2162.3755f, 10.7835f},
            {487.7060f, -1739.6212f, 10.8601f},
            {1025.0927f, -1023.5479f, 31.8119f},
            {2393.4456f, 1491.5537f, 10.5616f},
            {-1904.5793f, 283.9675f, 40.7533f}
    };

    public static boolean isNearModshopInterior(float[] xyz, float ratio) {
        for (float[] modshopXYZ : modshopInteriors) {
            if (distanceBetweenPoints(modshopXYZ, xyz) < ratio) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNearPayNSpray(float[] xyz, float ratio) {
        for (float[] pointXYZ : payNSprays) {
            if (distanceBetweenPoints(xyz, pointXYZ) < ratio) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNearModshopExterior(float[] xyz, float ratio) {
        for (float[] modshopXYZ : modshopExteriors) {
            if (distanceBetweenPoints(modshopXYZ, xyz) < ratio) {
                return true;
            }
        }
        return false;
    }
}