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
package com.lleps.test.codegenerators;

import com.lleps.jsamp.SAMPFunctions;
import com.lleps.jsamp.anticheat.ACUtils;
import com.lleps.jsamp.constant.model.VehicleComponent;
import com.lleps.jsamp.player.Player;
import com.lleps.jsamp.server.SAMPServer;
import com.lleps.test.CommandListener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author spell
 */

public class VehicleComponentsEnumMaker implements CommandListener {

    @Override
    public boolean onCommand(Player player, String command, String[] args) {
        if (command.equals("/doEnum")) {
            doIt(StrStore.newComps.split("\n"));
        }
        return false;
    }

    static class EnumElement {
        static Map<Integer, EnumElement> elementsById = new HashMap<>();
        public static EnumElement getById(int id) {
            return elementsById.get(id);
        }

        String enumName;
        int modelId;
        String modelName;
        String slot;
        String name;
        String validModels;
    }

    // STEP 1: Read components and create EnumElement objects with defaults.
    // STEP 2: Now, we need to create missing elements. Add to elements list missing ones.
    // STEP 3: Now, we have all elements. Loop and fix their names!
    void doIt(String[] list) {
        int idx = 0;
        int lastId = 999;
        for (String line : list) {
            String[] nameAndParams = line.split("\\(", 2);
            String enumName = nameAndParams[0];
            String params = nameAndParams[1].replace("),", "");

            debugMSG("enumName: " + enumName + " params : " + params);

            String arrayString = params.substring(params.indexOf("new int[]"));
            String paramsWithoutArray = params.substring(0, params.indexOf("new int[]")).replace(" ", "");
            String[] id_model_slot_name = paramsWithoutArray.split(",");
            String id = id_model_slot_name[0];
            int idAsInt = Integer.parseInt(id);
            if ((idAsInt-1) != lastId) {
                int missingCount = (idAsInt - lastId) - 1;
                //System.out.println(missingCount + " missing.");
                for (int i = (lastId + 1); i < idAsInt; i++) {
                    int elemId = i + idAsInt - 1;
                    //printElement(i);
                }
            }
            lastId = idAsInt;

            String name = id_model_slot_name[1];
            String slot = id_model_slot_name[2];
            int price = ACUtils.getComponentPrice(idAsInt);

            System.out.println(enumName + "(" + id + ", " + name + ", " + slot + ", " + price + ", " + arrayString + "),");
        }
        System.out.println("list: " + list.length + " - componentTitles: " + ComponentTitles.length);
    }

    String getCompName(int compId) {
        return ComponentTitles[compId - 1000];
    }

    void printElement(int id) {
        String enumName = getShoebillComponentName(id);
        String name = getCompName(id);
        VehicleComponent.Slot slot = VehicleComponent.Slot.get(SAMPFunctions.GetVehicleComponentType(id));
        SAMPServer.printLine("\t" + enumName + "(" +
        id + ", " + "\"" + name + "\"" + ", " + "Slot." + slot + ", new int[] " + Arrays.toString(ACUtils.getCompatibleComponentModels(id)).replace("[", "{").replace("]", "}") + "),");
    }

    Map<Integer, String> shoebillCompNames = new HashMap<>();
    boolean compNamesLoaded = false;
    String getShoebillComponentName(int id) {
        if (!compNamesLoaded) {
            loadCompNames();
            compNamesLoaded = true;
        }
        return shoebillCompNames.get(id);
    }
    void loadCompNames() {
        for (String line : StrStore.shoebillComps.split("\n")) {
            String name = line.split("=")[0];
            int id = Integer.parseInt(line.split("=")[1]);
            shoebillCompNames.put(id, name);
        }
    }



    void debugMSG(String msg) {
        //System.out.println(msg);
    }

    String ComponentTitles[] =
            {
                    "Pro","Win","Drag","Alpha","Champ","Fury","Roof","Derecho","x5","x2","x10",
                    "Race","Worx","Round","Champ","Race","Worx","Izquierdo","Upswept","Twin","Large",
                    "Medium","Small","Fury","Cuadrada","OffRoad","Derecho Alien","Izquierdo Alien",
                    "Alien","X-Flow","Derecho X-Flow","Izquierdo X-Flow","Alien","X-Flow","Alien",
                    "X-Flow","Alien","X-Flow","Alien","X-Flow","Alien","X-Flow","Chrome","Slamin",
                    "Chrome","X-Flow","Alien","Alien","X-Flow","Alien","X-Flow","Alien","X-Flow",
                    "X-Flow","Alien","Alien","Alien","X-Flow","Alien","X-Flow","X-Flow","X-Flow",
                    "Alien","X-Flow","Alien","Alien","X-Flow","Alien","X-Flow","Alien","X-Flow",
                    "Alien","X-Flow","Shadow","Mega","Rimshine","Wires","Classic","Twist","Cutter",
                    "Switch","Grove","Import","Dollar","Trance","Atomic","Stereo","Hidráulica",
                    "Alien","X-Flow","Alien","X-Flow","Alien","X-Flow","Alien","X-Flow","Ahab",
                    "Virtual","Access","Chrome","Chrome","Chrome Flames","Chrome Strip","Covertible",
                    "Chrome","Slamin","Chrome Arches","Chrome Strip","Chrome Strip","Chrome","Slamin",
                    "Little Sign","Little Sign","Chrome","Slamin","Chrome","Slamin","Chrome","Chrome Trim",
                    "Wheelcovers","Chrome Trim","Wheelcovers","Chrome Flames","Chrome Bars","Chrome Arches",
                    "Chrome Lights","Chrome","Slamin","Vinyl","Chrome","Hardtop","Softtop","Slamin",
                    "Chrome Strip","Chrome Strip","Slamin","Chrome","Chrome Strip","Alien","X-Flow",
                    "X-Flow","Alien","Circular","Circular","Cuadrada","Cuadrada","X-Flow",
                    "Alien","X-Flow","Alien","Alien","X-Flow","X-Flow","Alien","Alien","Alien","X-Flow",
                    "X-Flow","X-Flow","Alien","Alien","X-Flow","Alien","X-Flow","Alien","X-Flow",
                    "Alien","X-Flow","Alien","Alien","X-Flow","Alien","X-Flow","X-Flow","Chrome",
                    "Slamin","Chrome","Slamin","Slamin","Chrome","Chrome","Slamin","Chrome","Slamin",
                    "Chrome","Slamin","Slamin","Chrome","Slamin","Chrome","Slamin","Chrome","Chrome","Slamin"
            };

    String src = "       SPOILER_PRO(1000, \"spl_b_mar_m\", VehicleComponentSlot.SPOILER, \"Pro\", new int[] {589, 492, 516, 404, 547, 489, 405, 421}),\n" +
            "        SPOILER_WIN(1001, \"spl_b_bab_m\", VehicleComponentSlot.SPOILER, \"Win\", new int[] {496, 401, 518, 527, 415, 585, 546, 410, 603, 426, 436, 405, 580, 439, 550, 549, 420, 540, 529}),\n" +
            "        SPOILER_DRAG(1002, \"spl_b_bar_m\", VehicleComponentSlot.SPOILER, \"Drag\", new int[] {496, 546, 517, 551, 418, 516, 404, 489}),\n" +
            "        SPOILER_ALPHA(1003, \"spl_b_mab_m\", VehicleComponentSlot.SPOILER, \"Alpha\", new int[] {496, 401, 518, 415, 585, 517, 410, 551, 426, 436, 547, 439, 550, 549, 420, 491, 529}),\n" +
            "        HOOD_CHAMP_SCOOP(1004, \"bnt_b_sc_m\", VehicleComponentSlot.HOOD, \"Champ Scoop\", new int[] {401, 589, 492, 546, 516, 600, 426, 489, 550, 420, 540, 478}),\n" +
            "        HOOD_FURY_SCOOP(1005, \"bnt_b_sc_l\", VehicleComponentSlot.HOOD, \"Fury Scoop\", new int[] {401, 518, 589, 492, 551, 600, 426, 489, 550, 420, 478}),\n" +
            "        ROOF_SCOOP(1006, \"rf_b_sc_r\", VehicleComponentSlot.ROOF, \"Roof Scoop\", new int[] {496, 401, 518, 589, 585, 492, 546, 551, 418, 603, 600, 426, 436, 489, 580, 550, 540, 529, 477}),\n" +
            "        SIDE_SKIRT_RIGHT(1007, \"wg_l_b_ssk\", VehicleComponentSlot.SIDE_SKIRT, \"Right Sideskirt\", new int[] {496, 422, 401, 518, 527, 415, 589, 585, 546, 517, 410, 516, 404, 603, 600, 436, 580, 439, 549, 540, 491, 529, 477}),\n" +
            "        NITRO_5_TIMES(1008, \"nto_b_l\", VehicleComponentSlot.NITRO, \"5 times\", new int[] {445, 602, 429, 496, 422, 401, 518, 541, 438, 527, 415, 542, 589, 480, 507, 585, 419, 587, 533, 526, 466, 492, 474, 579, 545, 411, 546, 400, 517, 410, 551, 500, 418, 516, 467, 404, 603, 600, 426, 436, 547, 489, 479, 442, 475, 405, 458, 580, 439, 550, 566, 549, 420, 451, 540, 491, 412, 478, 421, 529, 555, 477, 562, 565, 559, 561, 560, 558, 536, 575, 534, 567, 535, 576}),\n" +
            "        NITRO_2_TIMES(1009, \"nto_b_s\", VehicleComponentSlot.NITRO, \"2 times\", new int[] {445, 602, 429, 496, 422, 401, 518, 402, 402, 541, 438, 527, 415, 542, 589, 480, 507, 585, 419, 587, 533, 526, 466, 492, 474, 579, 545, 411, 546, 400, 517, 410, 551, 500, 418, 516, 467, 404, 603, 600, 426, 436, 547, 489, 479, 442, 475, 405, 458, 580, 439, 409, 550, 506, 566, 549, 420, 451, 540, 491, 412, 478, 421, 529, 555, 477, 562, 565, 559, 559, 561, 560, 558, 536, 575, 534, 567, 535, 576}),\n" +
            "        NITRO_10_TIMES(1010, \"nto_b_tw\", VehicleComponentSlot.NITRO, \"10 times\", new int[] {445, 602, 429, 496, 422, 401, 518, 402, 541, 438, 527, 415, 542, 589, 480, 507, 585, 419, 587, 533, 526, 466, 492, 474, 579, 545, 411, 546, 400, 517, 410, 551, 500, 418, 516, 467, 404, 603, 600, 426, 436, 547, 489, 479, 442, 475, 405, 458, 580, 439, 550, 566, 549, 420, 451, 540, 491, 412, 478, 421, 529, 555, 477, 562, 565, 559, 561, 560, 558, 536, 575, 534, 567, 535, 576}),\n" +
            "        HOOD_RACE_SCOOP(1011, \"bnt_b_sc_p_m\", VehicleComponentSlot.HOOD, \"Race Scoop\", new int[] {496, 549, 529}),\n" +
            "        HOOD_WORX_SCOOP(1012, \"bnt_b_sc_p_l\", VehicleComponentSlot.HOOD, \"Worx Scoop\", new int[] {549, 478, 529}),\n" +
            "        LAMPS_ROUND_FOG(1013, \"lgt_b_rspt\", VehicleComponentSlot.LAMPS, \"Round Fog\", new int[] {422, 401, 518, 589, 585, 400, 410, 500, 404, 600, 436, 489, 439, 478}),\n" +
            "        SPOILER_CHAMP(1014, \"spl_b_bar_l\", VehicleComponentSlot.SPOILER, \"Champ\", new int[] {527, 542, 405, 491, 421}),\n" +
            "        SPOILER_RACE(1015, \"spl_b_bbr_l\", VehicleComponentSlot.SPOILER, \"Race\", new int[] {527, 542, 516}),\n" +
            "        SPOILER_WORK(1016, \"spl_b_bbr_m\", VehicleComponentSlot.SPOILER, \"Worx\", new int[] {589, 492, 517, 551, 418, 516, 404, 547, 489, 421}),\n" +
            "        EXHAUST_UPSWEPT(1018, \"exh_b_ts\", VehicleComponentSlot.EXHAUST, \"Upswept\", new int[] {518, 527, 415, 542, 589, 585, 546, 400, 517, 551, 516, 603, 600, 547, 489, 405, 580, 550, 549, 540, 491, 421, 529, 477}),\n" +
            "        EXHAUST_TWIN(1019, \"exh_b_t\", VehicleComponentSlot.EXHAUST, \"Twin\", new int[] {496, 422, 401, 415, 542, 585, 546, 400, 517, 410, 551, 500, 516, 404, 603, 426, 436, 547, 489, 405, 550, 549, 420, 540, 491, 421, 529, 477}),\n" +
            "        EXHAUST_LARGE(1020, \"exh_b_l\", VehicleComponentSlot.EXHAUST, \"Large\", new int[] {496, 422, 401, 518, 527, 542, 589, 585, 400, 517, 410, 551, 500, 418, 516, 404, 603, 600, 436, 547, 489, 405, 580, 550, 549, 540, 491, 478, 421, 529, 477}),\n" +
            "        EXHAUST_MEDIUM(1021, \"exh_b_m\", VehicleComponentSlot.EXHAUST, \"Medium\", new int[] {422, 527, 542, 400, 410, 551, 500, 418, 516, 404, 426, 436, 547, 405, 420, 491, 478, 421, 477}),\n" +
            "        EXHAUST_SMALL(1022, \"exh_b_s\", VehicleComponentSlot.EXHAUST, \"Small\", new int[] {600, 436, 478}),\n" +
            "        SPOILER_FURY(1023, \"spl_b_bbb_m\", VehicleComponentSlot.SPOILER, \"Fury\", new int[] {496, 518, 415, 585, 546, 517, 410, 551, 603, 405, 580, 439, 550, 549, 540, 491, 421, 529}),\n" +
            "        LAMPS_SQUARE_FOG(1024, \"lgt_b_sspt\", VehicleComponentSlot.LAMPS, \"Square Fog\", new int[] {589, 546, 400, 410, 500, 603, 489, 540, 478}),\n" +
            "        SIDE_SKIRT_RIGHT_ALIEN_SULTAN(1026, \"wg_l_a_s\", VehicleComponentSlot.SIDE_SKIRT, \"Right Alien Sideskirt (Sultan)\", new int[] {560}),\n" +
            "        EXHAUST_ALIEN_SULTAN(1028, \"exh_a_s\", VehicleComponentSlot.EXHAUST, \"Alien (Sultan)\", new int[] {560}),\n" +
            "        EXHAUST_X_FLOW_SULTAN(1029, \"exh_c_s\", VehicleComponentSlot.EXHAUST, \"X-Flow (Sultan)\", new int[] {560}),\n" +
            "        SIDE_SKIRT_RIGHT_X_FLOW_SULTAN(1031, \"wg_l_c_s\", VehicleComponentSlot.SIDE_SKIRT, \"Right X-Flow Sideskirt (Sultan)\", new int[] {560}),\n" +
            "        ROOF_ALIEN_ROOF_VENT_SULTAN(1032, \"rf_a_s\", VehicleComponentSlot.ROOF, \"Alien Roof Vent (Sultan)\", new int[] {560}),\n" +
            "        ROOF_X_FLOW_ROOF_VENT_SULTAN(1033, \"rf_c_s\", VehicleComponentSlot.ROOF, \"X-Flow Roof Vent (Sultan)\", new int[] {560}),\n" +
            "        EXHAUST_ALIEN_ELEGY(1034, \"exh_a_l\", VehicleComponentSlot.EXHAUST, \"Alien (Elegy)\", new int[] {562}),\n" +
            "        ROOF_X_FLOW_ROOF_VENT_ELEGY(1035, \"rf_c_l\", VehicleComponentSlot.ROOF, \"X-Flow Roof Vent (Elegy)\", new int[] {562}),\n" +
            "        SIDE_SKIRT_RIGHT_ALIEN_ELEGY(1036, \"wg_l_a_l\", VehicleComponentSlot.SIDE_SKIRT, \"Right Alien Sideskirt (Elegy)\", new int[] {562}),\n" +
            "        EXHAUST_X_FLOW_ELEGY(1037, \"exh_c_l\", VehicleComponentSlot.EXHAUST, \"X-Flow (Elegy)\", new int[] {562}),\n" +
            "        ROOF_ALIEN_ROOF_VENT_ELEGY(1038, \"rf_a_l\", VehicleComponentSlot.ROOF, \"Alien Roof Vent (Elegy)\", new int[] {562}),\n" +
            "        SIDE_SKIRT_LEFT_X_FLOW_ELEGY(1039, \"wg_l_c_l\", VehicleComponentSlot.SIDE_SKIRT, \"Left X-Flow Sideskirt (Elegy)\", new int[] {562}),\n" +
            "        SIDE_SKIRT_RIGHT_CHROME_BROADWAY(1042, \"wg_l_lr_br1\", VehicleComponentSlot.SIDE_SKIRT, \"Right Chrome Sideskirt (Broadway)\", new int[] {575}),\n" +
            "        EXHAUST_SLAMIN_BROADWAY(1043, \"exh_lr_br2\", VehicleComponentSlot.EXHAUST, \"Slamin (Broadway)\", new int[] {575}),\n" +
            "        EXHAUST_CHROME_BROADWAY(1044, \"exh_lr_br1\", VehicleComponentSlot.EXHAUST, \"Chrome (Broadway)\", new int[] {575}),\n" +
            "        EXHAUST_X_FLOW_FLASH(1045, \"exh_c_f\", VehicleComponentSlot.EXHAUST, \"X-Flow (Flash)\", new int[] {565}),\n" +
            "        EXHAUST_ALIEN_FLASH(1046, \"exh_a_f\", VehicleComponentSlot.EXHAUST, \"Alien (Flash)\", new int[] {565}),\n" +
            "        SIDE_SKIRT_RIGHT_ALIEN_FLASH(1047, \"wg_l_a_f\", VehicleComponentSlot.SIDE_SKIRT, \"Right Alien Sideskirt (Flash)\", new int[] {565}),\n" +
            "        SIDE_SKIRT_RIGHT_X_FLOW_FLASH(1048, \"wg_l_c_f\", VehicleComponentSlot.SIDE_SKIRT, \"Right X-Flow Sideskirt (Flash)\", new int[] {565}),\n" +
            "        SPOILER_ALIEN_FLASH(1049, \"spl_a_f_r\", VehicleComponentSlot.SPOILER, \"Alien (Flash)\", new int[] {565}),\n" +
            "        SPOILER_X_FLOW_FLASH(1050, \"spl_c_f_r\", VehicleComponentSlot.SPOILER, \"X-Flow (Flash)\", new int[] {565}),\n" +
            "        ROOF_X_FLOW_FLASH(1053, \"rf_c_f\", VehicleComponentSlot.ROOF, \"X-Flow (Flash)\", new int[] {565}),\n" +
            "        ROOF_ALIEN_FLASH(1054, \"rf_a_f\", VehicleComponentSlot.ROOF, \"Alien (Flash)\", new int[] {565}),\n" +
            "        ROOF_ALIEN_STRATUM(1055, \"rf_a_st\", VehicleComponentSlot.ROOF, \"Alien (Stratum)\", new int[] {561}),\n" +
            "        SIDE_SKIRT_RIGHT_ALIEN_STRATUM(1056, \"wg_l_a_st\", VehicleComponentSlot.SIDE_SKIRT, \"Right Alien Sideskirt (Stratum)\", new int[] {561}),\n" +
            "        SIDE_SKIRT_X_FLOW_STRATUM(1057, \"wg_l_c_st\", VehicleComponentSlot.SIDE_SKIRT, \"Right X-Flow Sideskirt (Stratum)\", new int[] {561}),\n" +
            "        SPOILER_ALIEN_STRATUM(1058, \"spl_a_st_r\", VehicleComponentSlot.SPOILER, \"Alien (Stratum)\", new int[] {561}),\n" +
            "        EXHAUST_X_FLOW_STRATUM(1059, \"exh_c_st\", VehicleComponentSlot.EXHAUST, \"X-Flow (Stratum)\", new int[] {561}),\n" +
            "        SPOILER_X_FLOW_STRATUM(1060, \"spl_c_st_r\", VehicleComponentSlot.SPOILER, \"X-Flow (Stratum)\", new int[] {561}),\n" +
            "        ROOF_X_FLOW_STRATUM(1061, \"rf_c_st\", VehicleComponentSlot.ROOF, \"X-Flow (Stratum)\", new int[] {561}),\n" +
            "        EXHAUST_ALIEN_STRATUM(1064, \"exh_a_st\", VehicleComponentSlot.EXHAUST, \"Alien (Stratum)\", new int[] {561}),\n" +
            "        EXHAUST_ALIEN_JESTER(1065, \"exh_a_j\", VehicleComponentSlot.EXHAUST, \"Alien (Jester)\", new int[] {559}),\n" +
            "        EXHAUST_X_FLOW_JESTER(1066, \"exh_c_j\", VehicleComponentSlot.EXHAUST, \"X-Flow (Jester)\", new int[] {559}),\n" +
            "        ROOF_ALIEN_JESTER(1067, \"rf_a_j\", VehicleComponentSlot.ROOF, \"Alien (Jester)\", new int[] {559}),\n" +
            "        ROOF_X_FLOW_JESTER(1068, \"rf_c_j\", VehicleComponentSlot.ROOF, \"X-Flow (Jester)\", new int[] {559}),\n" +
            "        SIDE_SKIRT_RIGHT_ALIEN_JESTER(1069, \"wg_l_a_j\", VehicleComponentSlot.SIDE_SKIRT, \"Right Alien Sideskirt (Jester)\", new int[] {559}),\n" +
            "        SIDE_SKIRT_RIGHT_X_FLOW_JESTER(1070, \"wg_l_c_j\", VehicleComponentSlot.SIDE_SKIRT, \"Right X-Flow Sideskirt (Jester)\", new int[] {559}),\n" +
            "        ROOF_ALIEN(1088, \"rf_a_u\", VehicleComponentSlot.ROOF, \"Alien (Uranus)\", new int[] {558}),\n" +
            "        EXHAUST_X_FLOW_URANUS(1089, \"exh_c_u\", VehicleComponentSlot.EXHAUST, \"X-Flow (Uranus)\", new int[] {558}),\n" +
            "        SIDE_SKIRT_RIGHT_ALIEN_URANUS(1090, \"wg_l_a_u\", VehicleComponentSlot.SIDE_SKIRT, \"Right Alien Sideskirt (Uranus)\", new int[] {558}),\n" +
            "        ROOF_X_FLOW_URANUS(1091, \"rf_c_u\", VehicleComponentSlot.ROOF, \"X-Flow (Uranus)\", new int[] {558}),\n" +
            "        EXHAUST_ALIEN_URANUS(1092, \"exh_a_u\", VehicleComponentSlot.EXHAUST, \"Alien (Uranus)\", new int[] {558}),\n" +
            "        SIDE_SKIRT_RIGHT_X_FLOW_1_URANUS(1093, \"wg_l_c_u\", VehicleComponentSlot.SIDE_SKIRT, \"Right X-Flow Sideskirt (Uranus)\", new int[] {558}),\n" +
            "        BULLBARS_CHROME_GRILL(1100, \"misc_c_lr_rem1\", VehicleComponentSlot.FRONT_BUMPER, \"Chrome Grill (Remington)\", new int[] {534}),\n" +
            "        ROOF_COVERTIBLE_BLADE(1103, \"rf_lr_bl2\", VehicleComponentSlot.ROOF, \"Covertible (Blade)\", new int[] {536}),\n" +
            "        EXHAUST_CHROME_BLADE(1104, \"exh_lr_bl1\", VehicleComponentSlot.EXHAUST, \"Chrome (Blade)\", new int[] {536}),\n" +
            "        EXHAUST_SLAMIN_BLADE(1105, \"exh_lr_bl2\", VehicleComponentSlot.EXHAUST, \"Slamin (Blade)\", new int[] {536}),\n" +
            "        SIDE_SKIRT_RIGHT_CHROME_ARCHES_REMINGTON(1106, \"wg_l_lr_rem2\", VehicleComponentSlot.SIDE_SKIRT, \"Right `Chrome Arches` (Remington)\", new int[] {534}),\n" +
            "        SIDE_SKIRT_RIGHT_CHROME_STRIP_BLADE(1108, \"wg_l_lr_bl1\", VehicleComponentSlot.SIDE_SKIRT, \"Right `Chrome Strip` Sideskirt (Blade)\", new int[] {536}),\n" +
            "        REAR_BULLBARS_CHROME_SLAMVAN(1109, \"bbb_lr_slv1\", VehicleComponentSlot.REAR_BUMPER, \"Chrome (Slamvan)\", new int[] {535}),\n" +
            "        REAR_BULLBARS_SLAMIN_SLAMVAN(1110, \"bbb_lr_slv2\", VehicleComponentSlot.REAR_BUMPER, \"Slamin (Slamvan)\", new int[] {535}),\n" +
            "        EXHAUST_CHROME_SLAMVAN(1113, \"exh_lr_slv1\", VehicleComponentSlot.EXHAUST, \"Chrome (Slamvan)\", new int[] {535}),\n" +
            "        EXHAUST_SLAMIN_SLAMVAN(1114, \"exh_lr_slv2\", VehicleComponentSlot.EXHAUST, \"Slamin (Slamvan)\", new int[] {535}),\n" +
            "        FRONT_BULLBARS_CHROME_SLAMVAN(1115, \"fbb_lr_slv1\", VehicleComponentSlot.FRONT_BUMPER, \"Chrome (Slamvan)\", new int[] {535}),\n" +
            "        FRONT_BULLBARS_SLAMIN_SLAMVAN(1116, \"fbb_lr_slv2\", VehicleComponentSlot.FRONT_BUMPER, \"Slamin (Slamvan)\", new int[] {535}),\n" +
            "        FRONT_BUMPER_CHROME_SLAMVAN(1117, \"fbmp_lr_slv1\", VehicleComponentSlot.FRONT_BUMPER, \"Chrome (Slamvan)\", new int[] {535}),\n" +
            "        SIDE_SKIRT_RIGHT_CHROME_TRIM_SLAMVAN(1118, \"wg_l_lr_slv1\", VehicleComponentSlot.SIDE_SKIRT, \"Right `Chrome Trim` Sideskirt (Slamvan)\", new int[] {535}),\n" +
            "        SIDE_SKIRT_RIGHT_WHEELCOVERS_SLAMVAN(1119, \"wg_l_lr_slv2\", VehicleComponentSlot.SIDE_SKIRT, \"Right `Wheelcovers` Sideskirt (Slamvan)\", new int[] {535}),\n" +
            "        SIDE_SKIRT_RIGHT_CHROME_FLAMES_REMINGTON(1122, \"wg_l_lr_rem1\", VehicleComponentSlot.SIDE_SKIRT, \"Right `Chrome Flames` Sideskirt (Remington)\", new int[] {534}),\n" +
            "        BULLBARS_CHROME_BARS_REMINGTON(1123, \"misc_c_lr_rem2\", VehicleComponentSlot.FRONT_BUMPER, \"Bullbar Chrome Bars (Remington)\", new int[] {534}),\n" +
            "        BULLBARS_CHROME_LIGHTS_REMINGTON(1125, \"misc_c_lr_rem3\", VehicleComponentSlot.FRONT_BUMPER, \"Bullbar Chrome Lights (Remington)\", new int[] {534}),\n" +
            "        EXHAUST_CHROME_EXHAUST_REMINGTON(1126, \"exh_lr_rem1\", VehicleComponentSlot.EXHAUST, \"Chrome Exhaust (Remington)\", new int[] {534}),\n" +
            "        EXHAUST_SLAMIN_EXHAUST_REMINGTON(1127, \"exh_lr_rem2\", VehicleComponentSlot.EXHAUST, \"Slamin Exhaust (Remington)\", new int[] {534}),\n" +
            "        ROOF_VINYL_HARDTOP_BLADE(1128, \"rf_lr_bl1\", VehicleComponentSlot.ROOF, \"Vinyl Hardtop (Blade)\", new int[] {536}),\n" +
            "        EXHAUST_CHROME_SAVANNA(1129, \"exh_lr_sv1\", VehicleComponentSlot.EXHAUST, \"Chrome (Savanna)\", new int[] {567}),\n" +
            "        ROOF_HARDTOP_SAVANNA(1130, \"rf_lr_sv1\", VehicleComponentSlot.ROOF, \"Hardtop (Savanna)\", new int[] {567}),\n" +
            "        ROOF_SOFTTOP_SAVANNA(1131, \"rf_lr_sv2\", VehicleComponentSlot.ROOF, \"Softtop (Savanna)\", new int[] {567}),\n" +
            "        EXHAUST_SLAMIN_SAVANNA(1132, \"exh_lr_sv2\", VehicleComponentSlot.EXHAUST, \"Slamin (Savanna)\", new int[] {567}),\n" +
            "        SIDE_SKIRT_RIGHT_CHROME_STRIP_SAVANNA(1133, \"wg_l_lr_sv\", VehicleComponentSlot.SIDE_SKIRT, \"Right `Chrome Strip` Sideskirt (Savanna)\", new int[] {567}),\n" +
            "        SIDE_SKIRT_RIGHT_CHROME_STRIP_TORNADO(1134, \"wg_l_lr_t1\", VehicleComponentSlot.SIDE_SKIRT, \"Right `Chrome Strip` Sideskirt (Tornado)\", new int[] {576}),\n" +
            "        EXHAUST_SLAMIN_TORNADO(1135, \"exh_lr_t2\", VehicleComponentSlot.EXHAUST, \"Slamin (Tornado)\", new int[] {576}),\n" +
            "        EXHAUST_CHROME_TORNADO(1136, \"exh_lr_t1\", VehicleComponentSlot.EXHAUST, \"Chrome (Tornado)\", new int[] {576}),\n" +
            "        SPOILER_ALIEN_SULTAN(1138, \"spl_a_s_b\", VehicleComponentSlot.SPOILER, \"Alien (Sultan)\", new int[] {560}),\n" +
            "        SPOILER_X_FLOW_SULTAN(1139, \"spl_c_s_b\", VehicleComponentSlot.SPOILER, \"X-Flow (Sultan)\", new int[] {560}),\n" +
            "        REAR_BUMPER_X_FLOW_SULTAN(1140, \"rbmp_c_s\", VehicleComponentSlot.REAR_BUMPER, \"X-Flow (Sultan)\", new int[] {560}),\n" +
            "        REAR_BUMPER_ALIEN_SULTAN(1141, \"rbmp_a_s\", VehicleComponentSlot.REAR_BUMPER, \"Alien (Sultan)\", new int[] {560}),\n" +
            "        VENT_RIGHT_OVAL_VENTS(1143, \"bntl_b_ov\", VehicleComponentSlot.VENT_RIGHT, \"Right Oval Vents\", new int[] {496, 401, 518, 585, 546, 517, 603, 547, 439, 550, 549, 540, 491}),\n" +
            "        VENT_RIGHT_SQUARE_VENTS(1145, \"bntl_b_sq\", VehicleComponentSlot.VENT_RIGHT, \"Right Square Vents\", new int[] {401, 518, 542, 589, 585, 546, 517, 603, 439, 550, 549, 540, 491}),\n" +
            "        SPOILER_X_FLOW_ELEGY(1146, \"spl_c_l_b\", VehicleComponentSlot.SPOILER, \"X-Flow (Elegy)\", new int[] {562}),\n" +
            "        SPOILER_ALIEN_ELEGY(1147, \"spl_a_l_b\", VehicleComponentSlot.SPOILER, \"Alien (Elegy)\", new int[] {562}),\n" +
            "        REAR_BUMPER_X_FLOW_ELEGY(1148, \"rbmp_c_l\", VehicleComponentSlot.REAR_BUMPER, \"X-Flow (Elegy)\", new int[] {562}),\n" +
            "        REAR_BUMPER_ALIEN_ELEGY(1149, \"rbmp_a_l\", VehicleComponentSlot.REAR_BUMPER, \"Alien (Elegy)\", new int[] {562}),\n" +
            "        REAR_BUMPER_ALIEN_FLASH(1150, \"rbmp_a_f\", VehicleComponentSlot.REAR_BUMPER, \"Alien (Flash)\", new int[] {565}),\n" +
            "        REAR_BUMPER_X_FLOW_FLASH(1151, \"rbmp_c_f\", VehicleComponentSlot.REAR_BUMPER, \"X-Flow (Flash)\", new int[] {565}),\n" +
            "        FRONT_BUMPER_X_FLOW_FLASH(1152, \"fbmp_c_f\", VehicleComponentSlot.FRONT_BUMPER, \"X-Flow (Flash)\", new int[] {565}),\n" +
            "        FRONT_BUMPER_ALIEN_FLASH(1153, \"fbmp_a_f\", VehicleComponentSlot.FRONT_BUMPER, \"Alien (Flash)\", new int[] {565}),\n" +
            "        REAR_BUMPER_ALIEN_STRATUM(1154, \"rbmp_a_st\", VehicleComponentSlot.REAR_BUMPER, \"Alien (Stratum)\", new int[] {561}),\n" +
            "        FRONT_BUMPER_ALIEN_STRATUM(1155, \"fbmp_a_st\", VehicleComponentSlot.FRONT_BUMPER, \"Alien (Stratum)\", new int[] {561}),\n" +
            "        REAR_BUMPER_X_FLOW_STRATUM(1156, \"rbmp_c_st\", VehicleComponentSlot.REAR_BUMPER, \"X-Flow (Stratum)\", new int[] {561}),\n" +
            "        FRONT_BUMPER_X_FLOW_STRATUM(1157, \"fbmp_c_st\", VehicleComponentSlot.FRONT_BUMPER, \"X-Flow (Stratum)\", new int[] {561}),\n" +
            "        SPOILER_X_FLOW_JESTER(1158, \"spl_c_j_b\", VehicleComponentSlot.SPOILER, \"X-Flow (Jester)\", new int[] {559}),\n" +
            "        REAR_BUMPER_ALIEN_JESTER(1159, \"rbmp_a_j\", VehicleComponentSlot.REAR_BUMPER, \"Alien (Jester)\", new int[] {559}),\n" +
            "        FRONT_BUMPER_ALIEN_JESTER(1160, \"fbmp_a_j\", VehicleComponentSlot.FRONT_BUMPER, \"Alien (Jester)\", new int[] {559}),\n" +
            "        REAR_BUMPER_X_FLOW_JESTER(1161, \"rbmp_c_j\", VehicleComponentSlot.REAR_BUMPER, \"X-Flow (Jester)\", new int[] {559}),\n" +
            "        SPOILER_ALIEN_JESTER(1162, \"spl_a_j_b\", VehicleComponentSlot.SPOILER, \"Alien (Jester)\", new int[] {559}),\n" +
            "        SPOILER_X_FLOW_URANUS(1163, \"spl_c_u_b\", VehicleComponentSlot.SPOILER, \"X-Flow (Uranus)\", new int[] {558}),\n" +
            "        SPOILER_ALIEN_URANUS(1164, \"spl_a_u_b\", VehicleComponentSlot.SPOILER, \"Alien (Uranus)\", new int[] {558}),\n" +
            "        FRONT_BUMPER_X_FLOW_URANUS(1165, \"fbmp_c_u\", VehicleComponentSlot.FRONT_BUMPER, \"X-Flow (Uranus)\", new int[] {558}),\n" +
            "        FRONT_BUMPER_ALIEN_URANUS(1166, \"fbmp_a_u\", VehicleComponentSlot.FRONT_BUMPER, \"Alien (Uranus)\", new int[] {558}),\n" +
            "        REAR_BUMPER_X_FLOW_URANUS(1167, \"rbmp_c_u\", VehicleComponentSlot.REAR_BUMPER, \"X-Flow (Uranus)\", new int[] {558}),\n" +
            "        REAR_BUMPER_ALIEN_URANUS(1168, \"rbmp_a_u\", VehicleComponentSlot.REAR_BUMPER, \"Alien (Uranus)\", new int[] {558}),\n" +
            "        FRONT_BUMPER_ALIEN_SULTAN(1169, \"fbmp_a_s\", VehicleComponentSlot.FRONT_BUMPER, \"Alien (Sultan)\", new int[] {560}),\n" +
            "        FRONT_BUMPER_X_FLOW_SULTAN(1170, \"fbmp_c_s\", VehicleComponentSlot.FRONT_BUMPER, \"X-Flow (Sultan)\", new int[] {560}),\n" +
            "        FRONT_BUMPER_ALIEN_ELEGY(1171, \"fbmp_a_l\", VehicleComponentSlot.FRONT_BUMPER, \"Alien (Elegy)\", new int[] {562}),\n" +
            "        FRONT_BUMPER_X_FLOW_ELEGY(1172, \"fbmp_c_l\", VehicleComponentSlot.FRONT_BUMPER, \"X-Flow (Elegy)\", new int[] {562}),\n" +
            "        FRONT_BUMPER_X_FLOW_JESTER(1173, \"fbmp_c_j\", VehicleComponentSlot.FRONT_BUMPER, \"X-Flow (Jester)\", new int[] {559}),\n" +
            "        FRONT_BUMPER_CHROME_BROADWAY(1174, \"fbmp_lr_br1\", VehicleComponentSlot.FRONT_BUMPER, \"Chrome (Broadway)\", new int[] {575}),\n" +
            "        FRONT_BUMPER_SLAMIN_BROADWAY(1175, \"fbmp_lr_br2\", VehicleComponentSlot.FRONT_BUMPER, \"Slamin (Broadway)\", new int[] {575}),\n" +
            "        REAR_BUMPER_CHROME_BROADWAY(1176, \"rbmp_lr_br1\", VehicleComponentSlot.REAR_BUMPER, \"Chrome (Broadway)\", new int[] {575}),\n" +
            "        REAR_BUMPER_SLAMIN_BROADWAY(1177, \"rbmp_lr_br2\", VehicleComponentSlot.REAR_BUMPER, \"Slamin (Broadway)\", new int[] {575}),\n" +
            "        REAR_BUMPER_SLAMIN_REMINGTON(1178, \"rbmp_lr_rem2\", VehicleComponentSlot.REAR_BUMPER, \"Slamin (Remington)\", new int[] {534}),\n" +
            "        FRONT_BUMPER_CHROME_REMINGTON(1179, \"fbmp_lr_rem1\", VehicleComponentSlot.FRONT_BUMPER, \"Chrome (Remington)\", new int[] {534}),\n" +
            "        REAR_BUMPER_CHROME_REMINGTON(1180, \"rbmp_lr_rem1\", VehicleComponentSlot.REAR_BUMPER, \"Chrome (Remington)\", new int[] {534}),\n" +
            "        FRONT_BUMPER_SLAMIN_BLADE(1181, \"fbmp_lr_bl2\", VehicleComponentSlot.FRONT_BUMPER, \"Slamin (Blade)\", new int[] {536}),\n" +
            "        FRONT_BUMPER_CHROME_BLADE(1182, \"fbmp_lr_bl1\", VehicleComponentSlot.FRONT_BUMPER, \"Chrome (Blade)\", new int[] {536}),\n" +
            "        REAR_BUMPER_SLAMIN_BLADE(1183, \"rbmp_lr_bl2\", VehicleComponentSlot.REAR_BUMPER, \"Slamin (Blade)\", new int[] {536}),\n" +
            "        REAR_BUMPER_CHROME_BLADE(1184, \"rbmp_lr_bl1\", VehicleComponentSlot.REAR_BUMPER, \"Chrome (Blade)\", new int[] {536}),\n" +
            "        FRONT_BUMPER_SLAMIN_REMINGTON(1185, \"fbmp_lr_rem2\", VehicleComponentSlot.FRONT_BUMPER, \"Slamin (Remington)\", new int[] {534}),\n" +
            "        REAR_BUMPER_SLAMIN_SAVANNA(1186, \"rbmp_lr_sv2\", VehicleComponentSlot.REAR_BUMPER, \"Slamin (Savanna)\", new int[] {567}),\n" +
            "        REAR_BUMPER_CHROME_SAVANNA(1187, \"rbmp_lr_sv1\", VehicleComponentSlot.REAR_BUMPER, \"Chrome (Savanna)\", new int[] {567}),\n" +
            "        FRONT_BUMPER_SLAMIN_SAVANNA(1188, \"fbmp_lr_sv2\", VehicleComponentSlot.FRONT_BUMPER, \"Slamin (Savanna)\", new int[] {567}),\n" +
            "        FRONT_BUMPER_CHROME_SAVANNA(1189, \"fbmp_lr_sv1\", VehicleComponentSlot.FRONT_BUMPER, \"Chrome (Savanna)\", new int[] {567}),\n" +
            "        FRONT_BUMPER_SLAMIN_TORNADO(1190, \"fbmp_lr_t2\", VehicleComponentSlot.FRONT_BUMPER, \"Slamin (Tornado)\", new int[] {576}),\n" +
            "        FRONT_BUMPER_CHROME_TORNADO(1191, \"fbmp_lr_t1\", VehicleComponentSlot.FRONT_BUMPER, \"Chrome (Tornado)\", new int[] {576}),\n" +
            "        REAR_BUMPER_CHROME_TORNADO(1192, \"rbmp_lr_t1\", VehicleComponentSlot.REAR_BUMPER, \"Chrome (Tornado)\", new int[] {576}),\n" +
            "        REAR_BUMPER_SLAMIN_TORNADO(1193, \"rbmp_lr_t2\", VehicleComponentSlot.REAR_BUMPER, \"Slamin (Tornado)\", new int[] {576});\n";

}