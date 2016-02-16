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
package com.lleps.jsamp.constant.model;

import java.util.*;

/**
 * @author spell
 */
public enum  VehicleComponent {

    SPOILER_PRO(1000, "Pro", Slot.SPOILER, new int[] {589, 492, 516, 404, 547, 489, 405, 421}),
    SPOILER_WIN(1001, "Win", Slot.SPOILER, new int[] {496, 401, 518, 527, 415, 585, 546, 410, 603, 426, 436, 405, 580, 439, 550, 549, 420, 540, 529}),
    SPOILER_DRAG(1002, "Drag", Slot.SPOILER, new int[] {496, 546, 517, 551, 418, 516, 404, 489}),
    SPOILER_ALPHA(1003, "Alpha", Slot.SPOILER, new int[] {496, 401, 518, 415, 585, 517, 410, 551, 426, 436, 547, 439, 550, 549, 420, 491, 529}),
    HOOD_CHAMP_SCOOP(1004, "Champ", Slot.HOOD, new int[] {401, 589, 492, 546, 516, 600, 426, 489, 550, 420, 540, 478}),
    HOOD_FURY_SCOOP(1005, "Fury", Slot.HOOD, new int[] {401, 518, 589, 492, 551, 600, 426, 489, 550, 420, 478}),
    ROOF_SCOOP(1006, "Roof", Slot.ROOF, new int[] {496, 401, 518, 589, 585, 492, 546, 551, 418, 603, 600, 426, 436, 489, 580, 550, 540, 529, 477}),
    SIDE_SKIRT_RIGHT(1007, "Derecho", Slot.SIDE_SKIRT, new int[] {496, 422, 401, 518, 527, 415, 589, 585, 546, 517, 410, 516, 404, 603, 600, 436, 580, 439, 549, 540, 491, 529, 477}),
    NITRO_5_TIMES(1008, "x5", Slot.NITRO, new int[] {445, 602, 429, 496, 422, 401, 518, 541, 438, 527, 415, 542, 589, 480, 507, 585, 419, 587, 533, 526, 466, 492, 474, 579, 545, 411, 546, 400, 517, 410, 551, 500, 418, 516, 467, 404, 603, 600, 426, 436, 547, 489, 479, 442, 475, 405, 458, 580, 439, 550, 566, 549, 420, 451, 540, 491, 412, 478, 421, 529, 555, 477, 562, 565, 559, 561, 560, 558, 536, 575, 534, 567, 535, 576}),
    NITRO_2_TIMES(1009, "x2", Slot.NITRO, new int[] {445, 602, 429, 496, 422, 401, 518, 402, 402, 541, 438, 527, 415, 542, 589, 480, 507, 585, 419, 587, 533, 526, 466, 492, 474, 579, 545, 411, 546, 400, 517, 410, 551, 500, 418, 516, 467, 404, 603, 600, 426, 436, 547, 489, 479, 442, 475, 405, 458, 580, 439, 409, 550, 506, 566, 549, 420, 451, 540, 491, 412, 478, 421, 529, 555, 477, 562, 565, 559, 559, 561, 560, 558, 536, 575, 534, 567, 535, 576}),
    NITRO_10_TIMES(1010, "x10", Slot.NITRO, new int[] {445, 602, 429, 496, 422, 401, 518, 402, 541, 438, 527, 415, 542, 589, 480, 507, 585, 419, 587, 533, 526, 466, 492, 474, 579, 545, 411, 546, 400, 517, 410, 551, 500, 418, 516, 467, 404, 603, 600, 426, 436, 547, 489, 479, 442, 475, 405, 458, 580, 439, 550, 566, 549, 420, 451, 540, 491, 412, 478, 421, 529, 555, 477, 562, 565, 559, 561, 560, 558, 536, 575, 534, 567, 535, 576}),
    HOOD_RACE_SCOOP(1011, "Race", Slot.HOOD, new int[] {496, 549, 529}),
    HOOD_WORX_SCOOP(1012, "Worx", Slot.HOOD, new int[] {549, 478, 529}),
    LAMPS_ROUND_FOG(1013, "Round", Slot.LAMPS, new int[] {422, 401, 518, 589, 585, 400, 410, 500, 404, 600, 436, 489, 439, 478}),
    SPOILER_CHAMP(1014, "Champ", Slot.SPOILER, new int[] {527, 542, 405, 491, 421}),
    SPOILER_RACE(1015, "Race", Slot.SPOILER, new int[] {527, 542, 516}),
    SPOILER_WORK(1016, "Worx", Slot.SPOILER, new int[] {589, 492, 517, 551, 418, 516, 404, 547, 489, 421}),
    EXHAUST_UPSWEPT(1018, "Izquierdo", Slot.EXHAUST, new int[] {518, 527, 415, 542, 589, 585, 546, 400, 517, 551, 516, 603, 600, 547, 489, 405, 580, 550, 549, 540, 491, 421, 529, 477}),
    EXHAUST_TWIN(1019, "Upswept", Slot.EXHAUST, new int[] {496, 422, 401, 415, 542, 585, 546, 400, 517, 410, 551, 500, 516, 404, 603, 426, 436, 547, 489, 405, 550, 549, 420, 540, 491, 421, 529, 477}),
    EXHAUST_LARGE(1020, "Twin", Slot.EXHAUST, new int[] {496, 422, 401, 518, 527, 542, 589, 585, 400, 517, 410, 551, 500, 418, 516, 404, 603, 600, 436, 547, 489, 405, 580, 550, 549, 540, 491, 478, 421, 529, 477}),
    EXHAUST_MEDIUM(1021, "Large", Slot.EXHAUST, new int[] {422, 527, 542, 400, 410, 551, 500, 418, 516, 404, 426, 436, 547, 405, 420, 491, 478, 421, 477}),
    EXHAUST_SMALL(1022, "Medium", Slot.EXHAUST, new int[] {600, 436, 478}),
    SPOILER_FURY(1023, "Small", Slot.SPOILER, new int[] {496, 518, 415, 585, 546, 517, 410, 551, 603, 405, 580, 439, 550, 549, 540, 491, 421, 529}),
    LAMPS_SQUARE_FOG(1024, "Fury", Slot.LAMPS, new int[] {589, 546, 400, 410, 500, 603, 489, 540, 478}),
    SIDE_SKIRT_RIGHT_ALIEN_SULTAN(1026, "Cuadrada", Slot.SIDE_SKIRT, new int[] {560}),
    EXHAUST_ALIEN_SULTAN(1028, "OffRoad", Slot.EXHAUST, new int[] {560}),
    EXHAUST_X_FLOW_SULTAN(1029, "Derecho Alien", Slot.EXHAUST, new int[] {560}),
    SIDE_SKIRT_RIGHT_X_FLOW_SULTAN(1031, "Izquierdo Alien", Slot.SIDE_SKIRT, new int[] {560}),
    ROOF_ALIEN_ROOF_VENT_SULTAN(1032, "Alien", Slot.ROOF, new int[] {560}),
    ROOF_X_FLOW_ROOF_VENT_SULTAN(1033, "X-Flow", Slot.ROOF, new int[] {560}),
    EXHAUST_ALIEN_ELEGY(1034, "Derecho X-Flow", Slot.EXHAUST, new int[] {562}),
    ROOF_X_FLOW_ROOF_VENT_ELEGY(1035, "Izquierdo X-Flow", Slot.ROOF, new int[] {562}),
    SIDE_SKIRT_RIGHT_ALIEN_ELEGY(1036, "Alien", Slot.SIDE_SKIRT, new int[] {562}),
    EXHAUST_X_FLOW_ELEGY(1037, "X-Flow", Slot.EXHAUST, new int[] {562}),
    ROOF_ALIEN_ROOF_VENT_ELEGY(1038, "Alien", Slot.ROOF, new int[] {562}),
    SIDE_SKIRT_LEFT_X_FLOW_ELEGY(1039, "X-Flow", Slot.SIDE_SKIRT, new int[] {562}),
    SIDE_SKIRT_RIGHT_CHROME_BROADWAY(1042, "Alien", Slot.SIDE_SKIRT, new int[] {575}),
    EXHAUST_SLAMIN_BROADWAY(1043, "X-Flow", Slot.EXHAUST, new int[] {575}),
    EXHAUST_CHROME_BROADWAY(1044, "Alien", Slot.EXHAUST, new int[] {575}),
    EXHAUST_X_FLOW_FLASH(1045, "X-Flow", Slot.EXHAUST, new int[] {565}),
    EXHAUST_ALIEN_FLASH(1046, "Alien", Slot.EXHAUST, new int[] {565}),
    SIDE_SKIRT_RIGHT_ALIEN_FLASH(1047, "X-Flow", Slot.SIDE_SKIRT, new int[] {565}),
    SIDE_SKIRT_RIGHT_X_FLOW_FLASH(1048, "Chrome", Slot.SIDE_SKIRT, new int[] {565}),
    SPOILER_ALIEN_FLASH(1049, "Slamin", Slot.SPOILER, new int[] {565}),
    SPOILER_X_FLOW_FLASH(1050, "Chrome", Slot.SPOILER, new int[] {565}),
    ROOF_X_FLOW_FLASH(1053, "X-Flow", Slot.ROOF, new int[] {565}),
    ROOF_ALIEN_FLASH(1054, "Alien", Slot.ROOF, new int[] {565}),
    ROOF_ALIEN_STRATUM(1055, "Alien", Slot.ROOF, new int[] {561}),
    SIDE_SKIRT_RIGHT_ALIEN_STRATUM(1056, "X-Flow", Slot.SIDE_SKIRT, new int[] {561}),
    SIDE_SKIRT_X_FLOW_STRATUM(1057, "Alien", Slot.SIDE_SKIRT, new int[] {561}),
    SPOILER_ALIEN_STRATUM(1058, "X-Flow", Slot.SPOILER, new int[] {561}),
    EXHAUST_X_FLOW_STRATUM(1059, "Alien", Slot.EXHAUST, new int[] {561}),
    SPOILER_X_FLOW_STRATUM(1060, "X-Flow", Slot.SPOILER, new int[] {561}),
    ROOF_X_FLOW_STRATUM(1061, "X-Flow", Slot.ROOF, new int[] {561}),
    EXHAUST_ALIEN_STRATUM(1064, "Alien", Slot.EXHAUST, new int[] {561}),
    EXHAUST_ALIEN_JESTER(1065, "Alien", Slot.EXHAUST, new int[] {559}),
    EXHAUST_X_FLOW_JESTER(1066, "Alien", Slot.EXHAUST, new int[] {559}),
    ROOF_ALIEN_JESTER(1067, "X-Flow", Slot.ROOF, new int[] {559}),
    ROOF_X_FLOW_JESTER(1068, "Alien", Slot.ROOF, new int[] {559}),
    SIDE_SKIRT_RIGHT_ALIEN_JESTER(1069, "X-Flow", Slot.SIDE_SKIRT, new int[] {559}),
    SIDE_SKIRT_RIGHT_X_FLOW_JESTER(1070, "X-Flow", Slot.SIDE_SKIRT, new int[] {559}),
    ROOF_ALIEN(1088, "X-Flow", Slot.ROOF, new int[] {558}),
    EXHAUST_X_FLOW_URANUS(1089, "Alien", Slot.EXHAUST, new int[] {558}),
    SIDE_SKIRT_RIGHT_ALIEN_URANUS(1090, "X-Flow", Slot.SIDE_SKIRT, new int[] {558}),
    ROOF_X_FLOW_URANUS(1091, "Alien", Slot.ROOF, new int[] {558}),
    EXHAUST_ALIEN_URANUS(1092, "Alien", Slot.EXHAUST, new int[] {558}),
    SIDE_SKIRT_RIGHT_X_FLOW_1_URANUS(1093, "X-Flow", Slot.SIDE_SKIRT, new int[] {558}),
    BULLBARS_CHROME_GRILL(1100, "Alien", Slot.FRONT_BUMPER, new int[] {534}),
    ROOF_COVERTIBLE_BLADE(1103, "X-Flow", Slot.ROOF, new int[] {536}),
    EXHAUST_CHROME_BLADE(1104, "Alien", Slot.EXHAUST, new int[] {536}),
    EXHAUST_SLAMIN_BLADE(1105, "X-Flow", Slot.EXHAUST, new int[] {536}),
    SIDE_SKIRT_RIGHT_CHROME_ARCHES_REMINGTON(1106, "Alien", Slot.SIDE_SKIRT, new int[] {534}),
    SIDE_SKIRT_RIGHT_CHROME_STRIP_BLADE(1108, "X-Flow", Slot.SIDE_SKIRT, new int[] {536}),
    REAR_BULLBARS_CHROME_SLAMVAN(1109, "Shadow", Slot.REAR_BUMPER, new int[] {535}),
    REAR_BULLBARS_SLAMIN_SLAMVAN(1110, "Mega", Slot.REAR_BUMPER, new int[] {535}),
    EXHAUST_CHROME_SLAMVAN(1113, "Rimshine", Slot.EXHAUST, new int[] {535}),
    EXHAUST_SLAMIN_SLAMVAN(1114, "Wires", Slot.EXHAUST, new int[] {535}),
    FRONT_BULLBARS_CHROME_SLAMVAN(1115, "Classic", Slot.FRONT_BUMPER, new int[] {535}),
    FRONT_BULLBARS_SLAMIN_SLAMVAN(1116, "Twist", Slot.FRONT_BUMPER, new int[] {535}),
    FRONT_BUMPER_CHROME_SLAMVAN(1117, "Cutter", Slot.FRONT_BUMPER, new int[] {535}),
    SIDE_SKIRT_RIGHT_CHROME_TRIM_SLAMVAN(1118, "Switch", Slot.SIDE_SKIRT, new int[] {535}),
    SIDE_SKIRT_RIGHT_WHEELCOVERS_SLAMVAN(1119, "Grove", Slot.SIDE_SKIRT, new int[] {535}),
    SIDE_SKIRT_RIGHT_CHROME_FLAMES_REMINGTON(1122, "Import", Slot.SIDE_SKIRT, new int[] {534}),
    BULLBARS_CHROME_BARS_REMINGTON(1123, "Dollar", Slot.FRONT_BUMPER, new int[] {534}),
    BULLBARS_CHROME_LIGHTS_REMINGTON(1125, "Trance", Slot.FRONT_BUMPER, new int[] {534}),
    EXHAUST_CHROME_EXHAUST_REMINGTON(1126, "Atomic", Slot.EXHAUST, new int[] {534}),
    EXHAUST_SLAMIN_EXHAUST_REMINGTON(1127, "Stereo", Slot.EXHAUST, new int[] {534}),
    ROOF_VINYL_HARDTOP_BLADE(1128, "Hidráulica", Slot.ROOF, new int[] {536}),
    EXHAUST_CHROME_SAVANNA(1129, "Alien", Slot.EXHAUST, new int[] {567}),
    ROOF_HARDTOP_SAVANNA(1130, "X-Flow", Slot.ROOF, new int[] {567}),
    ROOF_SOFTTOP_SAVANNA(1131, "Alien", Slot.ROOF, new int[] {567}),
    EXHAUST_SLAMIN_SAVANNA(1132, "X-Flow", Slot.EXHAUST, new int[] {567}),
    SIDE_SKIRT_RIGHT_CHROME_STRIP_SAVANNA(1133, "Alien", Slot.SIDE_SKIRT, new int[] {567}),
    SIDE_SKIRT_RIGHT_CHROME_STRIP_TORNADO(1134, "X-Flow", Slot.SIDE_SKIRT, new int[] {576}),
    EXHAUST_SLAMIN_TORNADO(1135, "Alien", Slot.EXHAUST, new int[] {576}),
    EXHAUST_CHROME_TORNADO(1136, "X-Flow", Slot.EXHAUST, new int[] {576}),
    SPOILER_ALIEN_SULTAN(1138, "Ahab", Slot.SPOILER, new int[] {560}),
    SPOILER_X_FLOW_SULTAN(1139, "Virtual", Slot.SPOILER, new int[] {560}),
    REAR_BUMPER_X_FLOW_SULTAN(1140, "Access", Slot.REAR_BUMPER, new int[] {560}),
    REAR_BUMPER_ALIEN_SULTAN(1141, "Chrome", Slot.REAR_BUMPER, new int[] {560}),
    VENT_RIGHT_OVAL_VENTS(1143, "Chrome", Slot.VENT_RIGHT, new int[] {496, 401, 518, 585, 546, 517, 603, 547, 439, 550, 549, 540, 491}),
    VENT_RIGHT_SQUARE_VENTS(1145, "Chrome Flames", Slot.VENT_RIGHT, new int[] {401, 518, 542, 589, 585, 546, 517, 603, 439, 550, 549, 540, 491}),
    SPOILER_X_FLOW_ELEGY(1146, "Chrome Strip", Slot.SPOILER, new int[] {562}),
    SPOILER_ALIEN_ELEGY(1147, "Covertible", Slot.SPOILER, new int[] {562}),
    REAR_BUMPER_X_FLOW_ELEGY(1148, "Chrome", Slot.REAR_BUMPER, new int[] {562}),
    REAR_BUMPER_ALIEN_ELEGY(1149, "Slamin", Slot.REAR_BUMPER, new int[] {562}),
    REAR_BUMPER_ALIEN_FLASH(1150, "Chrome Arches", Slot.REAR_BUMPER, new int[] {565}),
    REAR_BUMPER_X_FLOW_FLASH(1151, "Chrome Strip", Slot.REAR_BUMPER, new int[] {565}),
    FRONT_BUMPER_X_FLOW_FLASH(1152, "Chrome Strip", Slot.FRONT_BUMPER, new int[] {565}),
    FRONT_BUMPER_ALIEN_FLASH(1153, "Chrome", Slot.FRONT_BUMPER, new int[] {565}),
    REAR_BUMPER_ALIEN_STRATUM(1154, "Slamin", Slot.REAR_BUMPER, new int[] {561}),
    FRONT_BUMPER_ALIEN_STRATUM(1155, "Little Sign", Slot.FRONT_BUMPER, new int[] {561}),
    REAR_BUMPER_X_FLOW_STRATUM(1156, "Little Sign", Slot.REAR_BUMPER, new int[] {561}),
    FRONT_BUMPER_X_FLOW_STRATUM(1157, "Chrome", Slot.FRONT_BUMPER, new int[] {561}),
    SPOILER_X_FLOW_JESTER(1158, "Slamin", Slot.SPOILER, new int[] {559}),
    REAR_BUMPER_ALIEN_JESTER(1159, "Chrome", Slot.REAR_BUMPER, new int[] {559}),
    FRONT_BUMPER_ALIEN_JESTER(1160, "Slamin", Slot.FRONT_BUMPER, new int[] {559}),
    REAR_BUMPER_X_FLOW_JESTER(1161, "Chrome", Slot.REAR_BUMPER, new int[] {559}),
    SPOILER_ALIEN_JESTER(1162, "Chrome Trim", Slot.SPOILER, new int[] {559}),
    SPOILER_X_FLOW_URANUS(1163, "Wheelcovers", Slot.SPOILER, new int[] {558}),
    SPOILER_ALIEN_URANUS(1164, "Chrome Trim", Slot.SPOILER, new int[] {558}),
    FRONT_BUMPER_X_FLOW_URANUS(1165, "Wheelcovers", Slot.FRONT_BUMPER, new int[] {558}),
    FRONT_BUMPER_ALIEN_URANUS(1166, "Chrome Flames", Slot.FRONT_BUMPER, new int[] {558}),
    REAR_BUMPER_X_FLOW_URANUS(1167, "Chrome Bars", Slot.REAR_BUMPER, new int[] {558}),
    REAR_BUMPER_ALIEN_URANUS(1168, "Chrome Arches", Slot.REAR_BUMPER, new int[] {558}),
    FRONT_BUMPER_ALIEN_SULTAN(1169, "Chrome Lights", Slot.FRONT_BUMPER, new int[] {560}),
    FRONT_BUMPER_X_FLOW_SULTAN(1170, "Chrome", Slot.FRONT_BUMPER, new int[] {560}),
    FRONT_BUMPER_ALIEN_ELEGY(1171, "Slamin", Slot.FRONT_BUMPER, new int[] {562}),
    FRONT_BUMPER_X_FLOW_ELEGY(1172, "Vinyl", Slot.FRONT_BUMPER, new int[] {562}),
    FRONT_BUMPER_X_FLOW_JESTER(1173, "Chrome", Slot.FRONT_BUMPER, new int[] {559}),
    FRONT_BUMPER_CHROME_BROADWAY(1174, "Hardtop", Slot.FRONT_BUMPER, new int[] {575}),
    FRONT_BUMPER_SLAMIN_BROADWAY(1175, "Softtop", Slot.FRONT_BUMPER, new int[] {575}),
    REAR_BUMPER_CHROME_BROADWAY(1176, "Slamin", Slot.REAR_BUMPER, new int[] {575}),
    REAR_BUMPER_SLAMIN_BROADWAY(1177, "Chrome Strip", Slot.REAR_BUMPER, new int[] {575}),
    REAR_BUMPER_SLAMIN_REMINGTON(1178, "Chrome Strip", Slot.REAR_BUMPER, new int[] {534}),
    FRONT_BUMPER_CHROME_REMINGTON(1179, "Slamin", Slot.FRONT_BUMPER, new int[] {534}),
    REAR_BUMPER_CHROME_REMINGTON(1180, "Chrome", Slot.REAR_BUMPER, new int[] {534}),
    FRONT_BUMPER_SLAMIN_BLADE(1181, "Chrome Strip", Slot.FRONT_BUMPER, new int[] {536}),
    FRONT_BUMPER_CHROME_BLADE(1182, "Alien", Slot.FRONT_BUMPER, new int[] {536}),
    REAR_BUMPER_SLAMIN_BLADE(1183, "X-Flow", Slot.REAR_BUMPER, new int[] {536}),
    REAR_BUMPER_CHROME_BLADE(1184, "X-Flow", Slot.REAR_BUMPER, new int[] {536}),
    FRONT_BUMPER_SLAMIN_REMINGTON(1185, "Alien", Slot.FRONT_BUMPER, new int[] {534}),
    REAR_BUMPER_SLAMIN_SAVANNA(1186, "Circular", Slot.REAR_BUMPER, new int[] {567}),
    REAR_BUMPER_CHROME_SAVANNA(1187, "Circular", Slot.REAR_BUMPER, new int[] {567}),
    FRONT_BUMPER_SLAMIN_SAVANNA(1188, "Cuadrada", Slot.FRONT_BUMPER, new int[] {567}),
    FRONT_BUMPER_CHROME_SAVANNA(1189, "Cuadrada", Slot.FRONT_BUMPER, new int[] {567}),
    FRONT_BUMPER_SLAMIN_TORNADO(1190, "X-Flow", Slot.FRONT_BUMPER, new int[] {576}),
    FRONT_BUMPER_CHROME_TORNADO(1191, "Alien", Slot.FRONT_BUMPER, new int[] {576}),
    REAR_BUMPER_CHROME_TORNADO(1192, "X-Flow", Slot.REAR_BUMPER, new int[] {576}),
    REAR_BUMPER_SLAMIN_TORNADO(1193, "Alien", Slot.REAR_BUMPER, new int[] {576});

    public enum Slot {
        SPOILER				(0, "alerón"),
        HOOD				(1, "campanas"),
        ROOF				(2, "techo"),
        SIDE_SKIRT			(3, "embellecedores"),
        LAMPS				(4, "lámparas"),
        NITRO				(5, "nitro"),
        EXHAUST				(6, "escape"),
        WHEELS				(7, "ruedas"),
        STEREO				(8, "stereo"),
        HYDRAULICS			(9, "suspensión"),
        FRONT_BUMPER		(10, "paragolpes delantero"),
        REAR_BUMPER			(11, "paragolpes trasero"),
        VENT_RIGHT			(12, "ventilación derecha"),
        VENT_LEFT			(13, "ventilación trasera");

        private int id;
        private String name;

        Slot(int id, String name) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public static Slot get(int value)
        {
            return values() [value];
        }

        @Override
        public String toString() {
            return name + " (" + id + ")";
        }
    }

    private int componentId;
    private String name;
    private Slot slot;
    private int[] compatibleModelIds;

    private Collection<VehicleModel> compatibleModels;

    VehicleComponent(int componentId, String name, Slot slot, int[] compatibleModelIds) {
        this.componentId = componentId;
        this.name = name;
        this.slot = slot;
        this.compatibleModelIds = compatibleModelIds;
    }

    public int getComponentId() {
        return componentId;
    }

    public String getName() {
        return name;
    }

    public Slot getSlot() {
        return slot;
    }

    public Collection<VehicleModel> getCompatibleVehicleModels() {
        if (compatibleModels == null) {
            compatibleModels = new LinkedList<>();
            for (int vehicleId : compatibleModelIds) {
                compatibleModels.add(VehicleModel.getById(vehicleId));
            }
        }
        return compatibleModels;
    }

    public boolean isCompatibleWith(VehicleModel model) {
        return getCompatibleVehicleModels().contains(model);
    }

    public BodyModel toBodyModel() {
        return BodyModel.getById(componentId);
    }

    public static Collection<VehicleComponent> getBySlot(Slot slot) {
        List<VehicleComponent> result = new LinkedList<>();
        for (VehicleComponent component : values()) {
            if (component.getSlot() == slot) result.add(component);
        }
        return result;
    }

    private final static Map<Integer, VehicleComponent> VALUES = new HashMap<>();
    static {
        for (VehicleComponent component : values()) VALUES.put(component.componentId, component);
    }

    public static VehicleComponent getById(int id) {
        return VALUES.get(id);
    }
}