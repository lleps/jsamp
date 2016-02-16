/**
 * Copyright (C) 2011 MK124
 *
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

import com.lleps.jsamp.constant.WeaponSkill;
import com.lleps.jsamp.constant.WeaponSlot;

import java.util.Optional;

public enum WeaponModel {
    NONE					(0,		WeaponSlot.getById(0),		0,		"Unarmed"),
    BRASSKNUCKLE			(1,		WeaponSlot.getById(0),		331,	"Brass Knuckles"),
    GOLFCLUB				(2,		WeaponSlot.getById(1),		333,	"Golf Club"),
    NITESTICK				(3,		WeaponSlot.getById(1),		334,	"Nite Stick"),
    KNIFE					(4,		WeaponSlot.getById(1),		335,	"Knife"),
    BAT						(5,		WeaponSlot.getById(1),		336,	"Baseball Bat"),
    SHOVEL					(6,		WeaponSlot.getById(1),		337,	"Shovel"),
    POOLSTICK				(7,		WeaponSlot.getById(1),		338,	"Pool Stick"),
    KATANA					(8,		WeaponSlot.getById(1),		339,	"Katana"),
    CHAINSAW				(9,		WeaponSlot.getById(1),		341,	"Chainsaw"),
    DILDO					(10,	WeaponSlot.getById(10),		321,	"Purple Dildo"),
    DILDO2					(11,	WeaponSlot.getById(10),		322,	"Small White Vibrator"),
    VIBRATOR				(12,	WeaponSlot.getById(10),		323,	"Large White Vibrator"),
    VIBRATOR2				(13,	WeaponSlot.getById(10),		324,	"Silver Vibrator"),
    FLOWER					(14,	WeaponSlot.getById(10),		325,	"Flowers"),
    CANE					(15,	WeaponSlot.getById(10),		326,	"Cane"),
    GRENADE					(16,	WeaponSlot.getById(8),		342,	"Grenade"),
    TEARGAS					(17,	WeaponSlot.getById(8),		343,	"Tear Gas"),
    MOLTOV					(18,	WeaponSlot.getById(8),		344,	"Molotov Cocktail"),
    COLT45					(22,	WeaponSlot.getById(2),		346,	"Colt 45", WeaponSkill.PISTOL),
    SILENCED_COLT45			(23,	WeaponSlot.getById(2),		347,	"Silenced Pistol", WeaponSkill.PISTOL_SILENCED),
    DEAGLE					(24,	WeaponSlot.getById(2),		348,	"Desert Eagle", WeaponSkill.DESERT_EAGLE),
    SHOTGUN					(25,	WeaponSlot.getById(3),		349,	"Shotgun", WeaponSkill.SHOTGUN),
    SAWEDOFF				(26,	WeaponSlot.getById(3),		350,	"Sawn-off Shotgun", WeaponSkill.SAWNOFF_SHOTGUN),
    SHOTGSPA				(27,	WeaponSlot.getById(3),		351,	"Combat Shotgun", WeaponSkill.SAWNOFF_SHOTGUN),
    UZI						(28,	WeaponSlot.getById(4),		352,	"Micro UZI", WeaponSkill.MICRO_UZI),
    MP5						(29,	WeaponSlot.getById(4),		353,	"MP5", WeaponSkill.MP5),
    AK47					(30,	WeaponSlot.getById(5),		355,	"AK-47", WeaponSkill.AK47),
    M4						(31,	WeaponSlot.getById(5),		356,	"M4", WeaponSkill.M4),
    TEC9					(32,	WeaponSlot.getById(4),		372,	"TEC-9", WeaponSkill.MICRO_UZI),
    RIFLE					(33,	WeaponSlot.getById(6),		357,	"Rifle"),
    SNIPER					(34,	WeaponSlot.getById(6),		358,	"Sniper Rifle", WeaponSkill.SNIPERRIFLE),
    ROCKETLAUNCHER			(35,	WeaponSlot.getById(7),		359,	"Rocket Launcher"),
    HEATSEEKER				(36,	WeaponSlot.getById(7),		360,	"HS Rocket Launcher"),
    FLAMETHROWER			(37,	WeaponSlot.getById(7),		361,	"Flamethrower"),
    MINIGUN					(38,	WeaponSlot.getById(7),		362,	"Minigun"),
    SATCHEL					(39,	WeaponSlot.getById(8),		363,	"Remote Explosives"),
    BOMB					(40,	WeaponSlot.getById(12),		364,	"Bomb"),
    SPRAYCAN				(41,	WeaponSlot.getById(9),		365,	"Spray Can"),
    FIREEXTINGUISHER		(42,	WeaponSlot.getById(9),		366,	"Fire Extinguisher"),
    CAMERA					(43,	WeaponSlot.getById(9),		367,	"Camera"),
    NIGHTVISION				(44,	WeaponSlot.getById(11),		368,	"NV Goggles"),
    INFRARED				(45,	WeaponSlot.getById(11),		369,	"IR Goggles"),
    PARACHUTE				(46,	WeaponSlot.getById(11),		371,	"Parachute"),
    FAKEPISTOL				(47,	WeaponSlot.getById(-1),		0,		"Fake Pistol"),
    VEHICLE					(49,	WeaponSlot.getById(-1),		0,		"Vehicle"),
    HELICOPTERBLADES		(50,	WeaponSlot.getById(-1),		0,		"Helicopter Blades"),
    EXPLOSION				(51,	WeaponSlot.getById(-1),		0,		"Explosion"),
    DROWN					(53,	WeaponSlot.getById(-1),		0,		"Drowned"),
    COLLISION				(54,	WeaponSlot.getById(-1),		0,		"Splat"),
    CONNECT					(200,	WeaponSlot.getById(-1),		0,		"Connect"),
    DISCONNECT				(201,	WeaponSlot.getById(-1),		0,		"Disconnect"),

    /**
     * Unknown (only usable in sendDeathMessage)
     */
    UNKNOWN					(255,	WeaponSlot.getById(-1),		0,		"Unknown");

    private final int id;
    private final WeaponSlot slot;
    private final int objectId;
    private final String name;
    private final WeaponSkill skill;

    public static WeaponModel getById(int id) {
        for (WeaponModel weaponModel : values()) {
            if (weaponModel.getId() == id) {
                return weaponModel;
            }
        }
        return null;
    }

    WeaponModel(int id, WeaponSlot slot, int objectId, String name) {
        this(id, slot, objectId, name, null);
    }

    WeaponModel(int id, WeaponSlot slot, int objectId, String name, WeaponSkill skill) {
        this.id = id;
        this.slot = slot;
        this.objectId = objectId;
        this.name = name;
        this.skill = skill;
    }

    public int getId() {
        return id;
    }

    public BodyModel toBodyModel() {
        return BodyModel.getById(objectId);
    }

    public String getName() {
        return name;
    }

    public WeaponSlot getSlot() {
        return slot;
    }

    public Optional<WeaponSkill> getSkill() {
        return Optional.ofNullable(skill);
    }

    @Override
    public String toString() {
        return name;
    }
}