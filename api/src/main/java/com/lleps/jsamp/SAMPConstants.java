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
package com.lleps.jsamp;

/**
 * @author spell
 */
public class SAMPConstants {

    // Useful constants (not default)
    public static final int MAX_SKINS = 311;
    public static final int DOOR_DRIVER = 0;
    public static final int DOOR_PASSENGER = 1;
    public static final int DOOR_BACKLEFT = 2;
    public static final int DOOR_BACKRIGHT = 3;

    // Limits and internal constants
    public static final int MAX_PLAYER_NAME = (24);
    public static final int MAX_PLAYERS = (1000);
    public static final int MAX_VEHICLES = (2000);
    public static final int INVALID_PLAYER_ID = (0xFFFF);
    public static final int INVALID_ACTOR_ID = (0xFFFF);
    public static final int INVALID_VEHICLE_ID = (0xFFFF);
    public static final int NO_TEAM = (255);
    public static final int MAX_OBJECTS = (1000);
    public static final int INVALID_OBJECT_ID = (0xFFFF);
    public static final int MAX_GANG_ZONES = (1024);
    public static final int MAX_TEXT_DRAWS = (2048);
    public static final int MAX_PLAYER_TEXT_DRAWS = (256);
    public static final int MAX_MENUS = (128);
    public static final int MAX_3DTEXT_GLOBAL = (1024);
    public static final int MAX_3DTEXT_PLAYER = (1024);
    public static final int MAX_PICKUPS = (4096);
    public static final int MAX_ACTORS = (1000);
    public static final int INVALID_MENU = (0xFF);
    public static final int INVALID_TEXT_DRAW = (0xFFFF);
    public static final int INVALID_GANG_ZONE = (-1);
    public static final int INVALID_3DTEXT_ID = (0xFFFF);


    // Text Draw
    public static final int TEXT_DRAW_FONT_SPRITE_DRAW =  4;
    public static final int TEXT_DRAW_FONT_MODEL_PREVIEW = 5;

    // Player GUI Dialog
    public static final int DIALOG_STYLE_MSGBOX = 0;
    public static final int DIALOG_STYLE_INPUT = 1;
    public static final int DIALOG_STYLE_LIST = 2;
    public static final int DIALOG_STYLE_PASSWORD = 3;
    public static final int DIALOG_STYLE_TABLIST = 4;
    public static final int DIALOG_STYLE_TABLIST_HEADERS = 5;

    // States
    public static final int PLAYER_STATE_NONE = (0);
    public static final int PLAYER_STATE_ONFOOT = (1);
    public static final int PLAYER_STATE_DRIVER = (2);
    public static final int PLAYER_STATE_PASSENGER = (3);
    public static final int PLAYER_STATE_EXIT_VEHICLE = (4); // (used internally)
    public static final int PLAYER_STATE_ENTER_VEHICLE_DRIVER = (5); // (used internally)
    public static final int PLAYER_STATE_ENTER_VEHICLE_PASSENGER = (6); // (used internally)
    public static final int PLAYER_STATE_WASTED = (7);
    public static final int PLAYER_STATE_SPAWNED = (8);
    public static final int PLAYER_STATE_SPECTATING = (9);

    // Marker modes used by ShowPlayerMarkers()
    public static final int PLAYER_MARKERS_MODE_OFF = (0);
    public static final int PLAYER_MARKERS_MODE_GLOBAL = (1);
    public static final int PLAYER_MARKERS_MODE_STREAMED = (2);

    // Weapons
    public static final int WEAPON_BRASSKNUCKLE = (1);
    public static final int WEAPON_GOLFCLUB = (2);
    public static final int WEAPON_NITESTICK = (3);
    public static final int WEAPON_KNIFE = (4);
    public static final int WEAPON_BAT = (5);
    public static final int WEAPON_SHOVEL = (6);
    public static final int WEAPON_POOLSTICK = (7);
    public static final int WEAPON_KATANA = (8);
    public static final int WEAPON_CHAINSAW = (9);
    public static final int WEAPON_DILDO = (10);
    public static final int WEAPON_DILDO2 = (11);
    public static final int WEAPON_VIBRATOR = (12);
    public static final int WEAPON_VIBRATOR2 = (13);
    public static final int WEAPON_FLOWER = (14);
    public static final int WEAPON_CANE = (15);
    public static final int WEAPON_GRENADE = (16);
    public static final int WEAPON_TEARGAS = (17);
    public static final int WEAPON_MOLTOV = (18);
    public static final int WEAPON_COLT45 = (22);
    public static final int WEAPON_SILENCED = (23);
    public static final int WEAPON_DEAGLE = (24);
    public static final int WEAPON_SHOTGUN = (25);
    public static final int WEAPON_SAWEDOFF = (26);
    public static final int WEAPON_SHOTGSPA = (27);
    public static final int WEAPON_UZI = (28);
    public static final int WEAPON_MP5 = (29);
    public static final int WEAPON_AK47 = (30);
    public static final int WEAPON_M4 = (31);
    public static final int WEAPON_TEC9 = (32);
    public static final int WEAPON_RIFLE = (33);
    public static final int WEAPON_SNIPER = (34);
    public static final int WEAPON_ROCKETLAUNCHER = (35);
    public static final int WEAPON_HEATSEEKER = (36);
    public static final int WEAPON_FLAMETHROWER = (37);
    public static final int WEAPON_MINIGUN = (38);
    public static final int WEAPON_SATCHEL = (39);
    public static final int WEAPON_BOMB = (40);
    public static final int WEAPON_SPRAYCAN = (41);
    public static final int WEAPON_FIREEXTINGUISHER = (42);
    public static final int WEAPON_CAMERA = (43);
    public static final int WEAPON_PARACHUTE = (46);
    public static final int WEAPON_VEHICLE = (49);
    public static final int WEAPON_DROWN = (53);
    public static final int WEAPON_COLLISION = (54);

    // Keys
    public static final int KEY_ACTION = (1);
    public static final int KEY_CROUCH = (2);
    public static final int KEY_FIRE = (4);
    public static final int KEY_SPRINT = (8);
    public static final int KEY_SECONDARY_ATTACK = (16);
    public static final int KEY_JUMP = (32);
    public static final int KEY_LOOK_RIGHT = (64);
    public static final int KEY_HANDBRAKE = (128);
    public static final int KEY_LOOK_LEFT = (256);
    public static final int KEY_SUBMISSION = (512);
    public static final int KEY_LOOK_BEHIND = (512);
    public static final int KEY_WALK = (1024);
    public static final int KEY_ANALOG_UP = (2048);
    public static final int KEY_ANALOG_DOWN = (4096);
    public static final int KEY_ANALOG_LEFT = (8192);
    public static final int KEY_ANALOG_RIGHT = (16384);
    public static final int KEY_YES = (65536);
    public static final int KEY_NO = (131072);
    public static final int KEY_CTRL_BACK = (262144);

    public static final int KEY_UP = (-128);
    public static final int KEY_DOWN = (128);
    public static final int KEY_LEFT = (-128);
    public static final int KEY_RIGHT = (128);

    public static final int CLICK_SOURCE_SCOREBOARD = 0;

    public static final int EDIT_RESPONSE_CANCEL = 0;
    public static final int EDIT_RESPONSE_FINAL = 1;
    public static final int EDIT_RESPONSE_UPDATE = 2;

    public static final int SELECT_OBJECT_GLOBAL_OBJECT = 1;
    public static final int SELECT_OBJECT_PLAYER_OBJECT = 2;

    public static final int BULLET_HIT_TYPE_NONE = 0;
    public static final int BULLET_HIT_TYPE_PLAYER = 1;
    public static final int BULLET_HIT_TYPE_VEHICLE = 2;
    public static final int BULLET_HIT_TYPE_OBJECT = 3;
    public static final int BULLET_HIT_TYPE_PLAYER_OBJECT = 4;

    public static final int SPECIAL_ACTION_NONE = 0;
    public static final int SPECIAL_ACTION_DUCK = 1;
    public static final int SPECIAL_ACTION_USEJETPACK = 2;
    public static final int SPECIAL_ACTION_ENTER_VEHICLE = 3;
    public static final int SPECIAL_ACTION_EXIT_VEHICLE = 4;
    public static final int SPECIAL_ACTION_DANCE1 = 5;
    public static final int SPECIAL_ACTION_DANCE2 = 6;
    public static final int SPECIAL_ACTION_DANCE3 = 7;
    public static final int SPECIAL_ACTION_DANCE4 = 8;
    public static final int SPECIAL_ACTION_HANDSUP = 10;
    public static final int SPECIAL_ACTION_USECELLPHONE = 11;
    public static final int SPECIAL_ACTION_SITTING = 12;
    public static final int SPECIAL_ACTION_STOPUSECELLPHONE = 13;
    public static final int SPECIAL_ACTION_DRINK_BEER = 20;
    public static final int SPECIAL_ACTION_SMOKE_CIGGY = 21;
    public static final int SPECIAL_ACTION_DRINK_WINE = 22;
    public static final int SPECIAL_ACTION_DRINK_SPRUNK = 23;
    public static final int SPECIAL_ACTION_CUFFED = 24;
    public static final int SPECIAL_ACTION_CARRY = 25;

    public static final int FIGHT_STYLE_NORMAL = 4;
    public static final int FIGHT_STYLE_BOXING = 5;
    public static final int FIGHT_STYLE_KUNGFU = 6;
    public static final int FIGHT_STYLE_KNEEHEAD = 7;
    public static final int FIGHT_STYLE_GRABKICK = 15;
    public static final int FIGHT_STYLE_ELBOW = 16;

    public static final int WEAPONSKILL_PISTOL  = 0;
    public static final int WEAPONSKILL_PISTOL_SILENCED = 1;
    public static final int WEAPONSKILL_DESERT_EAGLE = 2;
    public static final int WEAPONSKILL_SHOTGUN = 3;
    public static final int WEAPONSKILL_SAWNOFF_SHOTGUN = 4;
    public static final int WEAPONSKILL_SPAS12_SHOTGUN = 5;
    public static final int WEAPONSKILL_MICRO_UZI = 6;
    public static final int WEAPONSKILL_MP5 = 7;
    public static final int WEAPONSKILL_AK47 = 8;
    public static final int WEAPONSKILL_M4 = 9;
    public static final int WEAPONSKILL_SNIPERRIFLE = 10;

    public static final int WEAPONSTATE_UNKNOWN = -1;
    public static final int WEAPONSTATE_NO_BULLETS = 0;
    public static final int WEAPONSTATE_LAST_BULLET = 1;
    public static final int WEAPONSTATE_MORE_BULLETS = 2;
    public static final int WEAPONSTATE_RELOADING = 3;

    public static final int MAX_PLAYER_ATTACHED_OBJECTS = 10;

    // PVar enumeration
    public static final int PLAYER_VARTYPE_NONE	= 0;
    public static final int PLAYER_VARTYPE_INT	= 1;
    public static final int PLAYER_VARTYPE_STRING = 2;
    public static final int PLAYER_VARTYPE_FLOAT = 3;

    public static final int MAPICON_LOCAL = 0; // displays in the player's local are
    public static final int MAPICON_GLOBAL = 1; // displays always
    public static final int MAPICON_LOCAL_CHECKPOINT = 2; // displays in the player's local area and has a checkpoint marker
    public static final int MAPICON_GLOBAL_CHECKPOINT = 3; // displays always and has a checkpoint marker

    public static final int CAMERA_CUT = 2;
    public static final int CAMERA_MOVE = 1;

    // Spectating
    public static final int SPECTATE_MODE_NORMAL = 1;
    public static final int SPECTATE_MODE_FIXED	= 2;
    public static final int SPECTATE_MODE_SIDE = 3;

    // Recording for NPC playback
    public static final int PLAYER_RECORDING_TYPE_NONE = 0;
    public static final int PLAYER_RECORDING_TYPE_DRIVER = 1;
    public static final int PLAYER_RECORDING_TYPE_ONFOOT = 2;

    public static final int CARMODTYPE_SPOILER = 0;
    public static final int CARMODTYPE_HOOD = 1;
    public static final int CARMODTYPE_ROOF = 2;
    public static final int CARMODTYPE_SIDESKIRT = 3;
    public static final int CARMODTYPE_LAMPS = 4;
    public static final int CARMODTYPE_NITRO = 5;
    public static final int CARMODTYPE_EXHAUST = 6;
    public static final int CARMODTYPE_WHEELS = 7;
    public static final int CARMODTYPE_STEREO = 8;
    public static final int CARMODTYPE_HYDRAULICS = 9;
    public static final int CARMODTYPE_FRONT_BUMPER = 10;
    public static final int CARMODTYPE_REAR_BUMPER = 11;
    public static final int CARMODTYPE_VENT_RIGHT = 12;
    public static final int CARMODTYPE_VENT_LEFT = 13;

    public static final int VEHICLE_PARAMS_UNSET = -1;
    public static final int VEHICLE_PARAMS_OFF = 0;
    public static final int VEHICLE_PARAMS_ON = 1;

    public static final int VEHICLE_MODEL_INFO_SIZE = 1;
    public static final int VEHICLE_MODEL_INFO_FRONTSEAT = 2;
    public static final int VEHICLE_MODEL_INFO_REARSEAT	= 3;
    public static final int VEHICLE_MODEL_INFO_PETROLCAP = 4;
    public static final int VEHICLE_MODEL_INFO_WHEELSFRONT = 5;
    public static final int VEHICLE_MODEL_INFO_WHEELSREAR = 6;
    public static final int VEHICLE_MODEL_INFO_WHEELSMID = 7;
    public static final int VEHICLE_MODEL_INFO_FRONT_BUMPER_Z = 8;
    public static final int VEHICLE_MODEL_INFO_REAR_BUMPER_Z = 9;

    public static final int OBJECT_MATERIAL_SIZE_32x32 = 10;
    public static final int OBJECT_MATERIAL_SIZE_64x32 = 20;
    public static final int OBJECT_MATERIAL_SIZE_64x64 = 30;
    public static final int OBJECT_MATERIAL_SIZE_128x32	= 40;
    public static final int OBJECT_MATERIAL_SIZE_128x64 = 50;
    public static final int OBJECT_MATERIAL_SIZE_128x128 = 60;
    public static final int OBJECT_MATERIAL_SIZE_256x32 = 70;
    public static final int OBJECT_MATERIAL_SIZE_256x64 = 80;
    public static final int OBJECT_MATERIAL_SIZE_256x128 = 90;
    public static final int OBJECT_MATERIAL_SIZE_256x256 = 100;
    public static final int OBJECT_MATERIAL_SIZE_512x64 = 110;
    public static final int OBJECT_MATERIAL_SIZE_512x128 = 120;
    public static final int OBJECT_MATERIAL_SIZE_512x256 = 130;
    public static final int OBJECT_MATERIAL_SIZE_512x512 = 140;

    public static final int OBJECT_MATERIAL_TEXT_ALIGN_LEFT = 0;
    public static final int OBJECT_MATERIAL_TEXT_ALIGN_CENTER = 1;
    public static final int OBJECT_MATERIAL_TEXT_ALIGN_RIGHT = 2;
}
