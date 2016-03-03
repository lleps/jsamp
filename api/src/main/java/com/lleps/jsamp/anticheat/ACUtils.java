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

    public static int getComponentPrice(int componentId) {
        switch(componentId) {
            // exhaust
            case 1018: return 350;
            case 1019: return 300;
            case 1020: return 250;
            case 1021: return 200;
            case 1022: return 150;
            case 1028: return 770;
            case 1029: return 680;
            case 1034: return 790;
            case 1037: return 690;
            case 1043: return 500;
            case 1044: return 500;
            case 1045: return 510;
            case 1046: return 710;
            case 1059: return 720;
            case 1064: return 830;
            case 1065: return 850;
            case 1066: return 750;
            case 1089: return 650;
            case 1092: return 750;
            case 1104: return 1610;
            case 1105: return 1540;
            case 1113: return 3340;
            case 1114: return 3250;
            case 1126: return 3340;
            case 1127: return 3250;
            case 1129: return 1650;
            case 1132: return 1590;
            case 1135: return 1500;
            case 1136: return 1000;
            // hood
            case 1004: return 100;
            case 1005: return 150;
            case 1011: return 220;
            case 1012: return 250;
            // front_bumper
            case 1117: return 2130;
            case 1152: return 910;
            case 1153: return 1200;
            case 1155: return 1030;
            case 1157: return 930;
            case 1160: return 1050;
            case 1165: return 850;
            case 1166: return 950;
            case 1169: return 970;
            case 1170: return 880;
            case 1171: return 990;
            case 1172: return 900;
            case 1173: return 950;
            case 1174: return 1000;
            case 1175: return 900;
            case 1179: return 2150;
            case 1181: return 2050;
            case 1182: return 2130;
            case 1185: return 2040;
            case 1188: return 2080;
            case 1189: return 2200;
            case 1190: return 1200;
            case 1191: return 1040;
            // rear_bumper
            case 1140: return 870;
            case 1141: return 980;
            case 1148: return 890;
            case 1149: return 1000;
            case 1150: return 1090;
            case 1151: return 840;
            case 1154: return 1030;
            case 1156: return 920;
            case 1159: return 1050;
            case 1161: return 950;
            case 1167: return 850;
            case 1168: return 950;
            case 1176: return 1000;
            case 1177: return 900;
            case 1178: return 2050;
            case 1180: return 2130;
            case 1183: return 2040;
            case 1184: return 2150;
            case 1186: return 2095;
            case 1187: return 2175;
            case 1192: return 940;
            case 1193: return 1100;
            // Roof
            case 1006: return 80;
            case 1128: return 3340;
            case 1130: return 3380;
            case 1131: return 3290;
            case 1103: return 3250;
            case 1032: return 170;
            case 1033: return 120;
            case 1035: return 150;
            case 1038: return 190;
            case 1053: return 130;
            case 1054: return 210;
            case 1055: return 230;
            case 1061: return 180;
            case 1067: return 250;
            case 1068: return 200;
            case 1088: return 150;
            case 1091: return 100;
            // spoilers
            case 1000: return 400;
            case 1001: return 550;
            case 1002: return 200;
            case 1003: return 250;
            case 1014: return 400;
            case 1015: return 500;
            case 1016: return 200;
            case 1023: return 350;
            case 1049: return 810;
            case 1050: return 620;
            case 1058: return 620;
            case 1060: return 530;
            case 1138: return 580;
            case 1139: return 470;
            case 1146: return 490;
            case 1147: return 600;
            case 1158: return 550;
            case 1162: return 650;
            case 1163: return 550;
            case 1164: return 450;
            // sideskirts
            case 1007: return 500;
            case 1017: return 500;
            case 1026: return 480;
            case 1027: return 480;
            case 1030: return 37;
            case 1031: return 370;
            case 1036: return 500;
            case 1039: return 390;
            case 1040: return 500;
            case 1041: return 390;
            case 1042: return 1000;
            case 1047: return 670;
            case 1048: return 530;
            case 1051: return 670;
            case 1052: return 530;
            case 1056: return 520;
            case 1057: return 430;
            case 1062: return 250;
            case 1063: return 430;
            case 1069: return 550;
            case 1070: return 450;
            case 1071: return 550;
            case 1072: return 450;
            case 1090: return 450;
            case 1093: return 350;
            case 1094: return 450;
            case 1095: return 350;
            case 1099: return 1000;
            case 1101: return 780;
            case 1102: return 830;
            case 1106: return 780;
            case 1107: return 780;
            case 1108: return 780;
            case 1118: return 780;
            case 1119: return 940;
            case 1120: return 780;
            case 1121: return 940;
            case 1122: return 780;
            case 1124: return 780;
            case 1133: return 830;
            case 1134: return 800;
            case 1137: return 800;
            // lamps
            case 1013: return 100;
            case 1024: return 50;
            // vents
            case 1142: return 150;
            case 1143: return 150;
            case 1144: return 100;
            case 1145: return 100;
            // wheels
            case 1025: return 1000;
            case 1073: return 1100;
            case 1074: return 1030;
            case 1075: return 980;
            case 1076: return 1560;
            case 1077: return 1620;
            case 1078: return 1200;
            case 1079: return 1030;
            case 1080: return 900;
            case 1081: return 1230;
            case 1082: return 820;
            case 1083: return 1560;
            case 1084: return 1350;
            case 1085: return 770;
            case 1096: return 1000;
            case 1097: return 620;
            case 1098: return 1140;
            // nitro
            case 1008: return 200;
            case 1009: return 500;
            case 1010: return 1000;
            // stereo & hydraulics
            case 1086: return 100;
            case 1087: return 1500;
            // misc
            case 1100: return 940;
            case 1123: return 860;
            case 1125: return 1120;
            case 1109: return 1610;
            case 1110: return 1540;
            case 1115: return 2130;
            case 1116: return 2050;
        }
        return 0;
    }

    public static float distanceBetweenPoints(float[] xyz1, float[] xyz2) {
        float dx = xyz1[0] - xyz2[0];
        float dy = xyz1[1] - xyz2[1];
        float dz = xyz1[2] - xyz2[2];
        return (float)Math.sqrt(dx*dx + dy*dy + dz*dz);
    }

    public static boolean isPlaneModel(int modelId) {
        final int planeModels[] = { 592, 577, 511, 512, 520, 593, 553, 476, 519, 460, 513, 548, 425, 417, 487, 488, 497, 563, 447, 469 };
        for (int model : planeModels) {
            if (model == modelId) return true;
        }
        return false;
    }

    public static boolean weaponSlotHoldsAmmo(int slot) {
        return slot >= 2 && slot <= 8;
    }

    private static boolean isOrdinaryVehicleModel(int modelid) {
        final int nonOrdinaryVehicleModels[] = { 581, 523, 462, 521, 463, 522, 461, 448, 468, 586, 509, 481, 510, 472, 473, 493, 595, 484, 430, 453, 452, 446, 454, 590, 569, 537, 538, 570, 449 };
        for (int nonOrdinaryVehicleModel : nonOrdinaryVehicleModels) {
            if (modelid == nonOrdinaryVehicleModel) {
                return false;
            }
        }
        return true;
    }

    private static boolean isWheelComponent(int componentid) {
        final int wheelComponents[] = {1025,1073,1074,1075,1076,1077,1078,1079,1080,1081,1082,1083,1084,1085,1096,1097,1098};
        for (int wheelComponent : wheelComponents) {
            if (componentid == wheelComponent) {
                return true;
            }
        }
        return false;
    }

    private static boolean isOrdinaryComponent(int componentid) {
        return isWheelComponent(componentid) || componentid == 1086 || componentid == 1087 || (componentid >= 1008 && componentid <= 1010);
    }

    public static boolean isValidComponent(int modelid, int componentid) {
        if (isOrdinaryComponent(componentid)) {
            return isOrdinaryVehicleModel(modelid);
        }

        final int rows = ACArraysStore.validModelComponents.length;
        final int columns = ACArraysStore.validModelComponents[0].length;
        for (int i = 0; i < rows; i++) {
            if (ACArraysStore.validModelComponents[i][0] == modelid) {
                for (int j = 1; j < columns; j++) {
                    if (ACArraysStore.validModelComponents[i][j] == componentid) {
                        return true;
                    }
                }
                break;
            }
        }
        return false;
    }

    public static int getVehicleModelMaxSpeed(int modelId) {
        Preconditions.checkArgument(modelId >= 400 && modelId <= 611, "invalid model: " + modelId);
        return (int)ACArraysStore.vehicleModelData[modelId - 400][1/*max speed*/];
    }

    public static float get3DSpeed(float velocityX, float velocityY, float velocityZ) {
        return (float)Math.sqrt(velocityX*velocityX + velocityY*velocityY + velocityZ*velocityZ) * 160.2f; // Magic constant!
    }

    public static float get2DSpeed(float velocityX, float velocityY) {
        return (float)Math.sqrt(velocityX*velocityX + velocityY*velocityY) * 160.2f;
    }

    public static boolean isNearModshopInterior(float[] xyz, float ratio) {
        for (float[] modshopXYZ : ACArraysStore.modshopInteriors) {
            if (distanceBetweenPoints(modshopXYZ, xyz) < ratio) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNearPayNSpray(float[] xyz, float ratio) {
        for (float[] pointXYZ : ACArraysStore.payNSprays) {
            if (distanceBetweenPoints(xyz, pointXYZ) < ratio) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNearModshopExterior(float[] xyz, float ratio) {
        for (float[] modshopXYZ : ACArraysStore.modshopExteriors) {
            if (distanceBetweenPoints(modshopXYZ, xyz) < ratio) {
                return true;
            }
        }
        return false;
    }
}