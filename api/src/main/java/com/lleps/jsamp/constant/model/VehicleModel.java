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

import com.lleps.jsamp.SAMPConstants;
import com.lleps.jsamp.FunctionAccess;
import com.lleps.jsamp.constant.VehicleSeat;
import com.lleps.jsamp.data.Vector3D;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

/**
 * @author spell
 */
public enum VehicleModel {
    // TODO: Add max speed
    LANDSTALKER(400, "Landstalker", Type.CAR, 4),
    BRAVURA(401, "Bravura", Type.CAR, 2),
    BUFFALO(402, "Buffalo", Type.CAR, 2),
    LINERUNNER(403, "Linerunner", Type.CAR, 2),
    PERENNIAL(404, "Perennial", Type.CAR, 4),
    SENTINEL(405, "Sentinel", Type.CAR, 4),
    DUMPER(406, "Dumper", Type.CAR, 1),
    FIRETRUCK1(407, "Firetruck", Type.CAR, 2),
    TRASHMASTER(408, "Trashmaster", Type.CAR, 2),
    STRETCH(409, "Stretch", Type.CAR, 4),
    MANANA(410, "Manana", Type.CAR, 2),
    INFERNUS(411, "Infernus", Type.CAR, 2),
    VOODOO(412, "Voodoo", Type.CAR, 2),
    PONY(413, "Pony", Type.CAR, 2),
    MULE(414, "Mule", Type.CAR, 2),
    CHEETAH(415, "Cheetah", Type.CAR, 2),
    AMBULANCE(416, "Ambulance", Type.CAR, 4),
    LEVIATHAN(417, "Leviathan", Type.HELICOPTER, 2),
    MOONBEAM(418, "Moonbeam", Type.CAR, 4),
    ESPERANTO(419, "Esperanto", Type.CAR, 2),
    TAXI(420, "Taxi", Type.CAR, 4),
    WASHINGTON(421, "Washington", Type.CAR, 4),
    BOBCAT(422, "Bobcat", Type.CAR, 2),
    MR_WHOOPEE(423, "Mr Whoopee", Type.CAR, 2),
    BF_INJECTION(424, "BF Injection", Type.CAR, 2),
    HUNTER(425, "Hunter", Type.HELICOPTER, 1),
    PREMIER(426, "Premier", Type.CAR, 4),
    ENFORCER(427, "Enforcer", Type.CAR, 4),
    SECURICAR(428, "Securicar", Type.CAR, 4),
    BANSHEE(429, "Banshee", Type.CAR, 2),
    PREDATOR(430, "Predator", Type.BOAT, 0),
    BUS(431, "Bus", Type.CAR, 8),
    RHINO(432, "Rhino", Type.TANK, 1),
    BARRACKS(433, "Barracks", Type.CAR, 2),
    HOTKNIFE(434, "Hotknife", Type.CAR, 2),
    TRAILER1(435, "Trailer", Type.TRAILER, 0),
    PREVION(436, "Previon", Type.CAR, 2),
    COACH(437, "Coach", Type.CAR, 8),
    CABBIE(438, "Cabbie", Type.CAR, 4),
    STALLION(439, "Stallion", Type.CAR, 2),
    RUMPO(440, "Rumpo", Type.CAR, 4),
    RC_BANDIT(441, "RC Bandit", Type.REMOTE_CONTROL, 1),
    ROMERO(442, "Romero", Type.CAR, 2),
    PACKER(443, "Packer", Type.CAR, 2),
    MONSTER1(444, "Monster", Type.CAR, 2),
    ADMIRAL(445, "Admiral", Type.CAR, 4),
    SQUALO(446, "Squalo", Type.BOAT, 0),
    SEASPARROW(447, "Seasparrow", Type.HELICOPTER, 2),
    PIZZABOY(448, "Pizzaboy", Type.MOTORBIKE, 1),
    TRAM(449, "Tram", Type.TRAIN, 4),
    TRAILER2(450, "Trailer", Type.TRAILER, 0),
    TURISMO(451, "Turismo", Type.CAR, 2),
    SPEEDER(452, "Speeder", Type.BOAT, 0),
    REEFER(453, "Reefer", Type.BOAT, 0),
    TROPIC(454, "Tropic", Type.BOAT, 0),
    FLATBED(455, "Flatbed", Type.CAR, 2),
    YANKEE(456, "Yankee", Type.CAR, 2),
    CADDY(457, "Caddy", Type.CAR, 2),
    SOLAIR(458, "Solair", Type.CAR, 4),
    BERKLEYS_RC_VAN(459, "Berkleys RC Van", Type.CAR, 4),
    SKIMMER(460, "Skimmer", Type.AIRCRAFT, 2),
    PCJ_600(461, "PCJ-600", Type.MOTORBIKE, 2),
    FAGGIO(462, "Faggio", Type.MOTORBIKE, 2),
    FREEWAY(463, "Freeway", Type.MOTORBIKE, 0),
    RC_BARON(464, "RC Baron", Type.REMOTE_CONTROL, 1),
    RC_RAIDER(465, "RC Raider", Type.REMOTE_CONTROL, 1),
    GLENDALE1(466, "Glendale", Type.CAR, 4),
    OCEANIC(467, "Oceanic", Type.CAR, 4),
    SANCHEZ(468, "Sanchez", Type.MOTORBIKE, 2),
    SPARROW(469, "Sparrow", Type.HELICOPTER, 2),
    PATRIOT(470, "Patriot", Type.CAR, 4),
    QUAD(471, "Quad", Type.MOTORBIKE, 2),
    COASTGUARD(472, "Coastguard", Type.BOAT, 0),
    DINGHY(473, "Dinghy", Type.BOAT, 0),
    HERMES(474, "Hermes", Type.CAR, 2),
    SABRE(475, "Sabre", Type.CAR, 2),
    RUSTLER(476, "Rustler", Type.AIRCRAFT, 1),
    ZR3_50(477, "ZR3 50", Type.CAR, 2),
    WALTON(478, "Walton", Type.CAR, 2),
    REGINA(479, "Regina", Type.CAR, 4),
    COMET(480, "Comet", Type.CAR, 2),
    BMX(481, "BMX", Type.BICYCLE, 1),
    BURRITO(482, "Burrito", Type.CAR, 4),
    CAMPER(483, "Camper", Type.CAR, 3),
    MARQUIS(484, "Marquis", Type.BOAT, 0),
    BAGGAGE(485, "Baggage", Type.CAR, 1),
    DOZER(486, "Dozer", Type.CAR, 1),
    MAVERICK(487, "Maverick", Type.HELICOPTER, 4),
    NEWS_CHOPPER(488, "News Chopper", Type.HELICOPTER, 2),
    RANCHER1(489, "Rancher", Type.CAR, 2),
    FBI_RANCHER(490, "FBI Rancher", Type.CAR, 4),
    VIRGO(491, "Virgo", Type.CAR, 2),
    GREENWOOD(492, "Greenwood", Type.CAR, 2),
    JETMAX(493, "Jetmax", Type.BOAT, 0),
    HOTRING(494, "Hotring", Type.CAR, 2),
    SANDKING(495, "Sandking", Type.CAR, 2),
    BLISTA_COMPACT(496, "Blista Compact", Type.CAR, 2),
    POLICE_MAVERICK(497, "Police Maverick", Type.HELICOPTER, 4),
    BOXVILLE1(498, "Boxville", Type.CAR, 4),
    BENSON(499, "Benson", Type.CAR, 2),
    MESA(500, "Mesa", Type.CAR, 2),
    RC_GOBLIN(501, "RC Goblin", Type.REMOTE_CONTROL, 1),
    HOTRING_RACER1(502, "Hotring Racer", Type.CAR, 2),
    HOTRING_RACER2(503, "Hotring Racer", Type.CAR, 2),
    BLOODRING_BANGER(504, "Bloodring Banger", Type.CAR, 2),
    RANCHER2(505, "Rancher", Type.CAR, 2),
    SUPER_GT(506, "Super GT", Type.CAR, 2),
    ELEGANT(507, "Elegant", Type.CAR, 4),
    JOURNEY(508, "Journey", Type.CAR, 2),
    BIKE(509, "Bike", Type.BICYCLE, 1),
    MOUNTAIN_BIKE(510, "Mountain Bike", Type.BICYCLE, 1),
    BEAGLE(511, "Beagle", Type.AIRCRAFT, 2),
    CROPDUST(512, "Cropdust", Type.AIRCRAFT, 1),
    STUNT(513, "Stunt", Type.AIRCRAFT, 1),
    TANKER(514, "Tanker", Type.CAR, 2),
    ROADTRAIN(515, "RoadTrain", Type.CAR, 2),
    NEBULA(516, "Nebula", Type.CAR, 4),
    MAJESTIC(517, "Majestic", Type.CAR, 2),
    BUCCANEER(518, "Buccaneer", Type.CAR, 2),
    SHAMAL(519, "Shamal", Type.AIRCRAFT, 1),
    HYDRA(520, "Hydra", Type.AIRCRAFT, 1),
    FCR_900(521, "FCR-900", Type.MOTORBIKE, 2),
    NRG_500(522, "NRG-500", Type.MOTORBIKE, 2),
    HPV1000(523, "HPV1000", Type.MOTORBIKE, 2),
    CEMENT_TRUCK(524, "Cement Truck", Type.CAR, 2),
    TOW_TRUCK(525, "Tow Truck", Type.CAR, 2),
    FORTUNE(526, "Fortune", Type.CAR, 2),
    CADRONA(527, "Cadrona", Type.CAR, 2),
    FBI_TRUCK(528, "FBI Truck", Type.CAR, 2),
    WILLARD(529, "Willard", Type.CAR, 4),
    FORKLIFT(530, "Forklift", Type.CAR, 1),
    TRACTOR(531, "Tractor", Type.CAR, 1),
    COMBINE(532, "Combine", Type.CAR, 1),
    FELTZER(533, "Feltzer", Type.CAR, 2),
    REMINGTON(534, "Remington", Type.CAR, 2),
    SLAMVAN(535, "Slamvan", Type.CAR, 2),
    BLADE(536, "Blade", Type.CAR, 2),
    FREIGHT1(537, "Freight", Type.TRAIN, 2),
    STREAK(538, "Streak", Type.TRAIN, 2),
    VORTEX(539, "Vortex", Type.BOAT, 0),
    VINCENT(540, "Vincent", Type.CAR, 4),
    BULLET(541, "Bullet", Type.CAR, 2),
    CLOVER(542, "Clover", Type.CAR, 2),
    SADLER1(543, "Sadler", Type.CAR, 2),
    FIRETRUCK2(544, "Firetruck", Type.CAR, 2),
    HUSTLER(545, "Hustler", Type.CAR, 2),
    INTRUDER(546, "Intruder", Type.CAR, 4),
    PRIMO(547, "Primo", Type.CAR, 4),
    CARGOBOB(548, "Cargobob", Type.HELICOPTER, 2),
    TAMPA(549, "Tampa", Type.CAR, 2),
    SUNRISE(550, "Sunrise", Type.CAR, 4),
    MERIT(551, "Merit", Type.CAR, 4),
    UTILITY(552, "Utility", Type.CAR, 2),
    NEVADA(553, "Nevada", Type.AIRCRAFT, 1),
    YOSEMITE(554, "Yosemite", Type.CAR, 2),
    WINDSOR(555, "Windsor", Type.CAR, 2),
    MONSTER2(556, "Monster", Type.CAR, 2),
    MONSTER3(557, "Monster", Type.CAR, 2),
    URANUS(558, "Uranus", Type.CAR, 2),
    JESTER(559, "Jester", Type.CAR, 2),
    SULTAN(560, "Sultan", Type.CAR, 4),
    STRATUM(561, "Stratum", Type.CAR, 4),
    ELEGY(562, "Elegy", Type.CAR, 2),
    RAINDANCE(563, "Raindance", Type.HELICOPTER, 2),
    RC_TIGER(564, "RC Tiger", Type.REMOTE_CONTROL, 1),
    FLASH(565, "Flash", Type.CAR, 2),
    TAHOMA(566, "Tahoma", Type.CAR, 4),
    SAVANNA(567, "Savanna", Type.CAR, 4),
    BANDITO(568, "Bandito", Type.CAR, 1),
    FREIGHT2(569, "Freight", Type.TRAIN, 0),
    TRAILER3(570, "Trailer", Type.TRAIN, 0),
    KART(571, "Kart", Type.CAR, 1),
    MOWER(572, "Mower", Type.CAR, 1),
    DUNERIDE(573, "Duneride", Type.CAR, 2),
    SWEEPER(574, "Sweeper", Type.CAR, 1),
    BROADWAY(575, "Broadway", Type.CAR, 2),
    TORNADO(576, "Tornado", Type.CAR, 2),
    AT_400(577, "AT-400", Type.AIRCRAFT, 1),
    DFT_30(578, "DFT-30", Type.CAR, 2),
    HUNTLEY(579, "Huntley", Type.CAR, 4),
    STAFFORD(580, "Stafford", Type.CAR, 4),
    BF_400(581, "BF-400", Type.MOTORBIKE, 2),
    NEWSVAN(582, "Newsvan", Type.CAR, 2),
    TUG(583, "Tug", Type.CAR, 1),
    TRAILER4(584, "Trailer", Type.TRAILER, 0),
    EMPEROR(585, "Emperor", Type.CAR, 4),
    WAYFARER(586, "Wayfarer", Type.MOTORBIKE, 2),
    EUROS(587, "Euros", Type.CAR, 2),
    HOTDOG(588, "Hotdog", Type.CAR, 2),
    CLUB(589, "Club", Type.CAR, 2),
    TRAILER5(590, "Trailer", Type.TRAILER, 0),
    TRAILER6(591, "Trailer", Type.TRAILER, 0),
    ANDROMADA(592, "Andromada", Type.AIRCRAFT, 2),
    DODO(593, "Dodo", Type.AIRCRAFT, 2),
    RC_CAM(594, "RC Cam", Type.REMOTE_CONTROL, 1),
    LAUNCH(595, "Launch", Type.BOAT, 0),
    POLICE_CAR_LSPD(596, "Police Car LSPD", Type.CAR, 4),
    POLICE_CAR_SFPD(597, "Police Car SFPD", Type.CAR, 4),
    POLICE_CAR_LVPD(598, "Police Car LVPD", Type.CAR, 4),
    POLICE_RANGER(599, "Police Ranger", Type.CAR, 2),
    PICADOR(600, "Picador", Type.CAR, 2),
    SWAT_VAN(601, "SWAT Van", Type.CAR, 2),
    ALPHA(602, "Alpha", Type.CAR, 2),
    PHOENIX(603, "Phoenix", Type.CAR, 2),
    GLENDALE2(604, "Glendale", Type.CAR, 4),
    SADLER2(605, "Sadler", Type.CAR, 2),
    LUGGAGE_TRAILER1(606, "Luggage Trailer", Type.TRAILER, 0),
    LUGGAGE_TRAILER2(607, "Luggage Trailer", Type.TRAILER, 0),
    STAIR_TRAILER(608, "Stair Trailer", Type.TRAILER, 0),
    BOXVILLE2(609, "Boxville", Type.CAR, 4),
    FARM_PLOW(610, "Farm Plow", Type.TRAILER, 0),
    UTILITY_TRAILER(611, "Utility Trailer", Type.TRAILER, 0);

    final static HashMap<Integer, VehicleModel> VALUES_BY_ID = new HashMap<Integer, VehicleModel>();

    static {
        for (VehicleModel model : values()) {
            VALUES_BY_ID.put(model.modelId, model);

            // Copy model info to model enum
            model.size = Vector3D.of(FunctionAccess.GetVehicleModelInfo(model.modelId, SAMPConstants.VEHICLE_MODEL_INFO_SIZE));
            model.frontSeat = Vector3D.of(FunctionAccess.GetVehicleModelInfo(model.modelId, SAMPConstants.VEHICLE_MODEL_INFO_FRONTSEAT));
            model.rearSeat = Vector3D.of(FunctionAccess.GetVehicleModelInfo(model.modelId, SAMPConstants.VEHICLE_MODEL_INFO_REARSEAT));
            model.petrolCap = Vector3D.of(FunctionAccess.GetVehicleModelInfo(model.modelId, SAMPConstants.VEHICLE_MODEL_INFO_PETROLCAP));
            model.wheelsFront = Vector3D.of(FunctionAccess.GetVehicleModelInfo(model.modelId, SAMPConstants.VEHICLE_MODEL_INFO_WHEELSFRONT));
            model.wheelsRear = Vector3D.of(FunctionAccess.GetVehicleModelInfo(model.modelId, SAMPConstants.VEHICLE_MODEL_INFO_WHEELSREAR));
            model.wheelsMID = Vector3D.of(FunctionAccess.GetVehicleModelInfo(model.modelId, SAMPConstants.VEHICLE_MODEL_INFO_WHEELSMID));
            model.frontBumperZHeight = Vector3D.of(FunctionAccess.GetVehicleModelInfo(model.modelId, SAMPConstants.VEHICLE_MODEL_INFO_FRONT_BUMPER_Z));
            model.rearBumperZHeight = Vector3D.of(FunctionAccess.GetVehicleModelInfo(model.modelId, SAMPConstants.VEHICLE_MODEL_INFO_REAR_BUMPER_Z));
        }
    }

    public static VehicleModel getById(int modelId) {
        return VALUES_BY_ID.get(modelId);
    }

    public static Optional<VehicleModel> getByExactName(String name, boolean ignoreCase) {
        if (ignoreCase) {
            return VALUES_BY_ID.values().stream().filter(v -> v.name.equalsIgnoreCase(name)).findFirst();
        } else {
            return VALUES_BY_ID.values().stream().filter(v -> v.name.equals(name)).findFirst();
        }
    }

    public static Optional<VehicleModel> getByPartOfName(String name, boolean ignoreCase) {
        if (ignoreCase) {
            return VALUES_BY_ID.values().stream().filter(v -> StringUtils.containsIgnoreCase(v.name, name)).findFirst();
        } else {
            return VALUES_BY_ID.values().stream().filter(v -> v.name.contains(name)).findFirst();
        }
    }

    final static int[] PAINTJOB_MODELS = new int[] {483, 562, 562, 565, 559, 561, 560, 575, 534, 567, 536, 535, 576, 558};

    private int modelId;
    private String name;
    private Type type;
    private int seatCount;

    // Vehicle model info
    private Vector3D size;
    private Vector3D frontSeat;
    private Vector3D rearSeat;
    private Vector3D petrolCap;
    private Vector3D wheelsFront;
    private Vector3D wheelsRear;
    private Vector3D wheelsMID;
    private Vector3D frontBumperZHeight;
    private Vector3D rearBumperZHeight;

    private Collection<VehicleSeat> seats = new ArrayList<>();

    VehicleModel(int modelId, String name, Type type, int seatCount) {
        this.modelId = modelId;
        this.name = name;
        this.type = type;
        this.seatCount = seatCount;

        for (int i = 0; i < seatCount; i++) {
            seats.add(VehicleSeat.getById(i));
        }
    }

    public Collection<VehicleSeat> getSeats() {
        return seats;
    }

    public int getModelId() {
        return modelId;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public boolean canUsePaintjobs() {
        for (int i : PAINTJOB_MODELS) {
            if (modelId == i) return true;
        }
        return false;
    }

    public Vector3D getFrontSeat() {
        return frontSeat;
    }

    public Vector3D getRearSeat() {
        return rearSeat;
    }

    public Vector3D getPetrolCap() {
        return petrolCap;
    }

    public Vector3D getWheelsFront() {
        return wheelsFront;
    }

    public Vector3D getWheelsRear() {
        return wheelsRear;
    }

    public Vector3D getWheelsMID() {
        return wheelsMID;
    }

    public Vector3D getFrontBumperZHeight() {
        return frontBumperZHeight;
    }

    public Vector3D getRearBumperZHeight() {
        return rearBumperZHeight;
    }

    public enum Type {
        UNKNOWN,
        BICYCLE,
        MOTORBIKE,
        CAR,
        TRAILER,
        REMOTE_CONTROL,
        TRAIN,
        BOAT,
        AIRCRAFT,
        HELICOPTER,
        TANK,
    }
}