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

import com.google.common.base.Preconditions;

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
        Preconditions.checkArgument(modelId >= 400 && modelId <= 611, "invalid model: " + modelId);
        return (int)vehicleModelData[modelId - 400][1/*max speed*/];
    }

    public static float get3DSpeed(float velocityX, float velocityY, float velocityZ) {
        return (float)Math.sqrt(velocityX*velocityX + velocityY*velocityY + velocityZ*velocityZ) * 160.2f; // Magic constant!
    }

    public static float get2DSpeed(float velocityX, float velocityY) {
        return (float)Math.sqrt(velocityX*velocityX + velocityY*velocityY) * 160.2f;
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


    private final static Object vehicleModelData[][] = {
        {"Landstalker", 140, 120, 10, 1},
        {"Bravura", 131, 60, 6, 1},
        {"Buffalo", 166, 65, 6, 1},
        {"Linerunner", 98, 150, 0, 1},
        {"Pereniel", 118, 60, 8, 1},
        {"Sentinel", 146, 60, 6, 1},
        {"Dumper", 98, 120, 0, 1},
        {"Firetruck", 132, 110, 0, 1},
        {"Trashmaster", 89, 120, 0, 1},
        {"Stretch", 140, 85, 6, 1},
        {"Manana", 115, 60, 4, 1},
        {"Infernus", 197, 80, 0, 1},
        {"Voodoo", 150, 75, 6, 1},
        {"Pony", 98, 90, 12, 1},
        {"Mule", 94, 90, 12, 2},
        {"Cheetah", 171, 70, 4, 1},
        {"Ambulancia", 137, 120, 10, 1},
        {"Leviathan", 399, 300, 12, 1},
        {"Moonbeam", 103, 95, 6, 1},
        {"Esperanto", 133, 70, 4, 1},
        {"Taxi", 129, 65, 6, 2},
        {"Washington", 137, 70, 6, 1},
        {"Bobcat", 124, 90, 10, 1},
        {"Mr Whoopee", 88, 90, 6, 1},
        {"BF Injection", 120, 50, 0, 1},
        {"Hunter", 399, 450, 0, 1},
        {"Premier", 154, 65, 6, 1},
        {"Enforcer", 147, 100, 10, 1},
        {"Securicar", 139, 100, 12, 1},
        {"Banshee", 179, 70, 0, 1},
        {"Predator", 399, 300, 0, 1},
        {"Bus", 116, 200, 0, 1},
        {"Rhino", 84, 500, 0, 1},
        {"Barracks", 98, 250, 20, 1},
        {"Hotknife", 148, 50, 4, 1},
        {"Trailer", 0, 9999, 0, 1},
        {"Previon", 133, 60, 4, 1},
        {"Coach", 140, 200, 0, 1},
        {"Cabbie", 127, 75, 6, 1},
        {"Stallion", 150, 60, 4, 1},
        {"Rumpo", 121, 90, 12, 2},
        {"RC Bandit", 67, 40, 0, 1},
        {"Romero", 124, 70, 10, 1},
        {"Packer", 112, 200, 0, 1},
        {"Monster Truck A", 98, 100, 0, 1},
        {"Admiral", 146, 65, 6, 1},
        {"Squalo", 399, 300, 0, 1},
        {"Seasparrow", 399, 200, 0, 1},
        {"Pizzaboy", 162, 50, 1, 1},
        {"Tram", 399, 100, 0, 1},
        {"Trailer", 399, 9999, 0, 1},
        {"Turismo", 172, 75, 0, 1},
        {"Speeder", 399, 300, 0, 1},
        {"Reefer", 399, 500, 0, 1},
        {"Tropic", 399, 500, 0, 1},
        {"Flatbed", 140, 200, 20, 1},
        {"Yankee", 94, 150, 17, 1},
        {"Caddy", 84, 40, 3, 1},
        {"Solair", 140, 70, 6, 1},
        {"Berkley's RC Van", 121, 85, 10, 1},
        {"Skimmer", 399, 100, 0, 1},
        {"PCJ-600", 180, 70, 0, 1},
        {"Faggio", 155, 50, 0, 1},
        {"Freeway", 180, 60, 0, 1},
        {"RC Baron", 399, 100, 0, 1},
        {"RC Raider", 399, 100, 0, 1},
        {"Glendale", 131, 70, 6, 1},
        {"Oceanic", 125, 70, 6, 1},
        {"Sanchez", 164, 50, 3, 1},
        {"Sparrow", 399, 150, 0, 1},
        {"Patriot", 139, 100, 8, 1},
        {"Quad", 98, 70, 0, 1},
        {"Coastguard", 399, 300, 17, 1},
        {"Dinghy", 399, 100, 0, 1},
        {"Hermes", 133, 70, 4, 1},
        {"Sabre", 154, 70, 8, 1},
        {"Rustler", 399, 120, 0, 1},
        {"ZR-350", 166, 60, 4, 1},
        {"Walton", 105, 80, 10, 1},
        {"Regina", 124, 70, 4, 1},
        {"Comet", 164, 60, 4, 1},
        {"BMX", 86, 9999, 0, 1},
        {"Burrito", 139, 85, 12, 2},
        {"Camper", 109, 85, 12, 1},
        {"Marquis", 399, 350, 7, 1},
        {"Baggage", 88, 60, 0, 1},
        {"Dozer", 56, 150, 0, 1},
        {"Maverick", 399, 200, 6, 1},
        {"Maverick (LSTV)", 399, 100, 0, 1},
        {"Rancher", 124, 80, 6, 1},
        {"FBI Rancher", 139, 90, 7, 1},
        {"Virgo", 132, 70, 4, 1},
        {"Greenwood", 125, 70, 6, 1},
        {"Jetmax", 399, 300, 0, 1},
        {"Hotring", 191, 90, 4, 1},
        {"Sandking", 157, 80, 8, 1},
        {"Blista Compact", 145, 60, 6, 1},
        {"Maverick (SAPD)", 399, 200, 6, 1},
        {"Boxville", 96, 120, 17, 1},
        {"Benson", 109, 150, 17, 2},
        {"Mesa", 125, 60, 5, 1},
        {"RC Goblin", 399, 100, 0, 1},
        {"Hotring Racer", 191, 90, 0, 1},
        {"Hotring Racer", 191, 90, 0, 1},
        {"Bloodring Banger", 154, 90, 6, 1},
        {"Rancher", 124, 80, 7, 1},
        {"Super GT", 159, 75, 6, 1},
        {"Elegant", 148, 75, 6, 1},
        {"Journey", 96, 90, 12, 1},
        {"Bicicleta", 93, 9999, 0, 1},
        {"Mountain Bike", 117, 9999, 0, 1},
        {"Beagle", 399, 150, 0, 1},
        {"Cropdust", 399, 150, 0, 1},
        {"Stunt", 399, 150, 0, 1},
        {"Tanker", 107, 200, 0, 1},
        {"RoadTrain", 126, 200, 0, 3},
        {"Nebula", 140, 70, 6, 1},
        {"Majestic", 140, 60, 4, 1},
        {"Buccaneer", 146, 70, 4, 1},
        {"Shamal", 399, 500, 0, 1},
        {"Hydra", 399, 750, 0, 1},
        {"FCR-900", 190, 70, 0, 1},
        {"NRG-500", 200, 90, 0, 1},
        {"HPV1000", 172, 80, 4, 1},
        {"Cement Truck", 116, 0, 0, 1},
        {"Tow Truck", 143, 100, 0, 1},
        {"Fortune", 140, 60, 4, 1},
        {"Cadrona", 133, 60, 4, 1},
        {"FBI Truck", 157, 80, 7, 1},
        {"Willard", 133, 70, 6, 1},
        {"Forklift", 54, 70, 0, 1},
        {"Tractor", 62, 80, 0, 1},
        {"Combine", 98, 100, 0, 1},
        {"Feltzer", 148, 70, 6, 1},
        {"Remington", 150, 70, 4, 1},
        {"Slamvan", 140, 75, 8, 1},
        {"Blade", 154, 70, 6, 1},
        {"Freight", 399, 1000, 0, 1},
        {"Streak", 399, 700, 0, 1},
        {"Vortex", 89, 100, 0, 1},
        {"Vincent", 136, 70, 6, 1},
        {"Bullet", 180, 75, 4, 1},
        {"Clover", 146, 65, 6, 1},
        {"Sadler", 134, 80, 10, 1},
        {"Firetruck", 132, 100, 0, 1},
        {"Hustler", 131, 70, 0, 1},
        {"Intruder", 133, 70, 6, 1},
        {"Primo", 127, 65, 6, 1},
        {"Cargobob", 399, 450, 17, 1},
        {"Tampa", 136, 65, 6, 1},
        {"Sunrise", 128, 70, 5, 1},
        {"Merit", 140, 70, 9, 1},
        {"Utility", 108, 90, 0, 1},
        {"Nevada", 399, 100, 0, 1},
        {"Yosemite", 128, 90, 12, 1},
        {"Windsor", 141, 60, 4, 1},
        {"Monster Truck B", 98, 90, 0, 1},
        {"Monster Truck C", 98, 90, 0, 1},
        {"Uranus", 139, 70, 4, 1},
        {"Jester", 158, 70, 6, 1},
        {"Sultan", 150, 85, 8, 1},
        {"Stratum", 137, 70, 6, 1},
        {"Elegy", 158, 70, 6, 1},
        {"Raindance", 399, 300, 17, 1},
        {"RC Tiger", 79, 300, 0, 1},
        {"Flash", 146, 70, 6, 1},
        {"Tahoma", 142, 70, 6, 1},
        {"Savanna", 154, 65, 4, 1},
        {"Bandito", 130, 60, 0, 1},
        {"Freight", 399, 100, 0, 1},
        {"Trailer", 399, 9999, 0, 1},
        {"Kart", 83, 40, 0, 1},
        {"Mower", 54, 90, 0, 1},
        {"Duneride", 98, 120, 20, 1},
        {"Sweeper", 53, 60, 0, 1},
        {"Broadway", 140, 70, 6, 1},
        {"Tornado", 140, 70, 8, 1},
        {"AT-400", 399, 1000, 0, 1},
        {"DFT-30", 116, 150, 20, 1},
        {"Huntley", 140, 90, 10, 1},
        {"Stafford", 136, 80, 6, 1},
        {"BF-400", 170, 70, 3, 1},
        {"Newsvan", 121, 100, 0, 1},
        {"Tug", 76, 100, 0, 1},
        {"Trailer", 399, 9999, 0, 1},
        {"Emperor", 136, 70, 6, 1},
        {"Wayfarer", 175, 75, 0, 1},
        {"Euros", 147, 70, 5, 1},
        {"Hotdog", 96, 80, 0, 1},
        {"Club", 145, 75, 6, 1},
        {"Trailer", 399, 9999, 0, 1},
        {"Trailer", 399, 9999, 0, 1},
        {"Andromada", 399, 2000, 0, 1},
        {"Dodo", 399, 500, 0, 1},
        {"RC Cam", 54, 10, 0, 1},
        {"Launch", 399, 90, 14, 1},
        {"Patrulla (LSPD)", 210, 90, 8, 1},
        {"Patrulla (SFPD)", 156, 90, 8, 1},
        {"Patrulla (LVPD)", 156, 90, 8, 1},
        {"Police Ranger", 140, 120, 8, 1},
        {"Picador", 134, 70, 10, 1},
        {"S.W.A.T. Van", 98, 200, 0, 1},
        {"Alpha", 150, 70, 4, 1},
        {"Phoenix", 152, 75, 4, 1},
        {"Glendale (Chocado)", 131, 40, 6, 1},
        {"Sadler (Chocada)", 134, 40, 10, 1},
        {"Luggage Trailer", 399, 50, 0, 1},
        {"Luggage Trailer", 399, 50, 0, 1},
        {"Stair Trailer", 399, 50, 0, 1},
        {"Boxville", 96, 120, 12, 1},
        {"Farm Plow", 399, 200, 0, 1},
        {"Utility Trailer", 399, 50, 0, 1}
    };

}