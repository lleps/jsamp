/*
* JSAMP - Java implementation for SA:MP.
* Project started 13/4/15.
* Writted by spell <leandro.barbero122@gmail.com>
*/

#pragma warning(disable:4800)

#include <cstring>
#include <jni.h>
#include <stdio.h>
#include "sampgdk/sampgdk.h"
#include "EncodingUtils.h"

#define BYTES_PER_CHARACTER 2

inline void GetJVMString(JNIEnv *env, jstring string, char *dest, int size) {
    const unsigned short* wmsg = env->GetStringChars(string, NULL);
	int len = env->GetStringLength(string);
	wcs2mbs(1252, wmsg, len, dest, size);
	env->ReleaseStringChars(string, wmsg);
}

static jboolean Java_logprintf(JNIEnv* env, jobject obj, jstring string) {
	char c_str_string[1024 * BYTES_PER_CHARACTER];
	GetJVMString(env, string, c_str_string, sizeof(c_str_string));
	sampgdk::logprintf(c_str_string);
	return true;
}

static jboolean Java_SetSpawnInfo(JNIEnv* env, jobject obj, jint playerid, jint team, jint skin, jfloat x, jfloat y, jfloat z, jfloat rotation, jint weapon1, jint weapon1_ammo, jint weapon2, jint weapon2_ammo, jint weapon3, jint weapon3_ammo) {
	bool ret = SetSpawnInfo(playerid, team, skin, x, y, z, rotation, weapon1, weapon1_ammo, weapon2, weapon2_ammo, weapon3, weapon3_ammo);
	return ret;
}

static jboolean Java_SpawnPlayer(JNIEnv* env, jobject obj, jint playerid) {
	bool ret = SpawnPlayer(playerid);
	return ret;
}

static jboolean Java_SetPlayerPos(JNIEnv* env, jobject obj, jint playerid, jfloat x, jfloat y, jfloat z) {
	bool ret = SetPlayerPos(playerid, x, y, z);
	return ret;
}

static jboolean Java_SetPlayerPosFindZ(JNIEnv* env, jobject obj, jint playerid, jfloat x, jfloat y, jfloat z) {
	bool ret = SetPlayerPosFindZ(playerid, x, y, z);
	return ret;
}

static jboolean Java_SetPlayerFacingAngle(JNIEnv* env, jobject obj, jint playerid, jfloat angle) {
	bool ret = SetPlayerFacingAngle(playerid, angle);
	return ret;
}

static jboolean Java_IsPlayerInRangeOfPoint(JNIEnv* env, jobject obj, jint playerid, jfloat range, jfloat x, jfloat y, jfloat z) {
	bool ret = IsPlayerInRangeOfPoint(playerid, range, x, y, z);
	return ret;
}

static jfloat Java_GetPlayerDistanceFromPoint(JNIEnv* env, jobject obj, jint playerid, jfloat x, jfloat y, jfloat z) {
	jfloat ret = GetPlayerDistanceFromPoint(playerid, x, y, z);
	return ret;
}

static jboolean Java_IsPlayerStreamedIn(JNIEnv* env, jobject obj, jint playerid, jint forplayerid) {
	bool ret = IsPlayerStreamedIn(playerid, forplayerid);
	return ret;
}

static jboolean Java_SetPlayerInterior(JNIEnv* env, jobject obj, jint playerid, jint jinteriorid) {
	bool ret = SetPlayerInterior(playerid, jinteriorid);
	return ret;
}

static jint Java_GetPlayerInterior(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = GetPlayerInterior(playerid);
	return ret;
}

static jboolean Java_SetPlayerHealth(JNIEnv* env, jobject obj, jint playerid, jfloat health) {
	bool ret = SetPlayerHealth(playerid, health);
	return ret;
}

static jboolean Java_SetPlayerArmour(JNIEnv* env, jobject obj, jint playerid, jfloat armour) {
	bool ret = SetPlayerArmour(playerid, armour);
	return ret;
}

static jboolean Java_SetPlayerAmmo(JNIEnv* env, jobject obj, jint playerid, jint weaponid, jint ammo) {
	bool ret = SetPlayerAmmo(playerid, weaponid, ammo);
	return ret;
}

static jint Java_GetPlayerAmmo(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = GetPlayerAmmo(playerid);
	return ret;
}

static jint Java_GetPlayerWeaponState(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = GetPlayerWeaponState(playerid);
	return ret;
}

static jint Java_GetPlayerTargetPlayer(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = GetPlayerTargetPlayer(playerid);
	return ret;
}

static jboolean Java_SetPlayerTeam(JNIEnv* env, jobject obj, jint playerid, jint teamid) {
	bool ret = SetPlayerTeam(playerid, teamid);
	return ret;
}

static jint Java_GetPlayerTeam(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = GetPlayerTeam(playerid);
	return ret;
}

static jboolean Java_SetPlayerScore(JNIEnv* env, jobject obj, jint playerid, jint score) {
	bool ret = SetPlayerScore(playerid, score);
	return ret;
}

static jint Java_GetPlayerScore(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = GetPlayerScore(playerid);
	return ret;
}

static jint Java_GetPlayerDrunkLevel(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = GetPlayerDrunkLevel(playerid);
	return ret;
}

static jboolean Java_SetPlayerDrunkLevel(JNIEnv* env, jobject obj, jint playerid, jint level) {
	bool ret = SetPlayerDrunkLevel(playerid, level);
	return ret;
}

static jboolean Java_SetPlayerColor(JNIEnv* env, jobject obj, jint playerid, jint color) {
	bool ret = SetPlayerColor(playerid, color);
	return ret;
}

static jint Java_GetPlayerColor(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = GetPlayerColor(playerid);
	return ret;
}

static jboolean Java_SetPlayerSkin(JNIEnv* env, jobject obj, jint playerid, jint skinid) {
	bool ret = SetPlayerSkin(playerid, skinid);
	return ret;
}

static jint Java_GetPlayerSkin(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = GetPlayerSkin(playerid);
	return ret;
}

static jboolean Java_GivePlayerWeapon(JNIEnv* env, jobject obj, jint playerid, jint weaponid, jint ammo) {
	bool ret = GivePlayerWeapon(playerid, weaponid, ammo);
	return ret;
}

static jboolean Java_ResetPlayerWeapons(JNIEnv* env, jobject obj, jint playerid) {
	bool ret = ResetPlayerWeapons(playerid);
	return ret;
}

static jboolean Java_SetPlayerArmedWeapon(JNIEnv* env, jobject obj, jint playerid, jint weaponid) {
	bool ret = SetPlayerArmedWeapon(playerid, weaponid);
	return ret;
}

static jboolean Java_GivePlayerMoney(JNIEnv* env, jobject obj, jint playerid, jint money) {
	bool ret = GivePlayerMoney(playerid, money);
	return ret;
}

static jboolean Java_ResetPlayerMoney(JNIEnv* env, jobject obj, jint playerid) {
	bool ret = ResetPlayerMoney(playerid);
	return ret;
}

static jint Java_SetPlayerName(JNIEnv* env, jobject obj, jint playerid, jstring name) {
    char c_str_name[24 * BYTES_PER_CHARACTER];
    GetJVMString(env, name, c_str_name, sizeof(c_str_name));
	return SetPlayerName(playerid, c_str_name);
}

static jint Java_GetPlayerMoney(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = GetPlayerMoney(playerid);
	return ret;
}

static jint Java_GetPlayerState(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = GetPlayerState(playerid);
	return ret;
}

static jint Java_GetPlayerPing(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = GetPlayerPing(playerid);
	return ret;
}

static jint Java_GetPlayerWeapon(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = GetPlayerWeapon(playerid);
	return ret;
}

static jboolean Java_SetPlayerTime(JNIEnv* env, jobject obj, jint playerid, jint hour, jint minute) {
	bool ret = SetPlayerTime(playerid, hour, minute);
	return ret;
}

static jboolean Java_TogglePlayerClock(JNIEnv* env, jobject obj, jint playerid, jboolean toggle) {
	bool ret = TogglePlayerClock(playerid, toggle);
	return ret;
}

static jboolean Java_SetPlayerWeather(JNIEnv* env, jobject obj, jint playerid, jint weather) {
	bool ret = SetPlayerWeather(playerid, weather);
	return ret;
}

static jboolean Java_ForceClassSelection(JNIEnv* env, jobject obj, jint playerid) {
	bool ret = ForceClassSelection(playerid);
	return ret;
}

static jboolean Java_SetPlayerWantedLevel(JNIEnv* env, jobject obj, jint playerid, jint level) {
	bool ret = SetPlayerWantedLevel(playerid, level);
	return ret;
}

static jint Java_GetPlayerWantedLevel(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = GetPlayerWantedLevel(playerid);
	return ret;
}

static jboolean Java_SetPlayerFightingStyle(JNIEnv* env, jobject obj, jint playerid, jint style) {
	bool ret = SetPlayerFightingStyle(playerid, style);
	return ret;
}

static jint Java_GetPlayerFightingStyle(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = GetPlayerFightingStyle(playerid);
	return ret;
}

static jboolean Java_SetPlayerVelocity(JNIEnv* env, jobject obj, jint playerid, jfloat x, jfloat y, jfloat z) {
	bool ret = SetPlayerVelocity(playerid, x, y, z);
	return ret;
}

static jboolean Java_PlayCrimeReportForPlayer(JNIEnv* env, jobject obj, jint playerid, jint suspectid, jint crime) {
	bool ret = PlayCrimeReportForPlayer(playerid, suspectid, crime);
	return ret;
}

static jboolean Java_PlayAudioStreamForPlayer(JNIEnv* env, jobject obj, jint playerid, jstring url, jfloat posX = 0.0, jfloat posY = 0.0, jfloat posZ = 0.0, jfloat distance = 50.0, jboolean usepos = false) {
    char c_str_url[256 * BYTES_PER_CHARACTER];
    GetJVMString(env, url, c_str_url, sizeof(c_str_url));
    return PlayAudioStreamForPlayer(playerid, c_str_url, posX, posY, posZ, distance, usepos);
}

static jboolean Java_StopAudioStreamForPlayer(JNIEnv* env, jobject obj, jint playerid) {
	bool ret = StopAudioStreamForPlayer(playerid);
	return ret;
}

static jboolean Java_SetPlayerShopName(JNIEnv* env, jobject obj, jint playerid, jstring shopname) {
    char c_str_shopname[24 * BYTES_PER_CHARACTER];
    GetJVMString(env, shopname, c_str_shopname, sizeof(c_str_shopname));
	return SetPlayerShopName(playerid, c_str_shopname);
}

static jboolean Java_SetPlayerSkillLevel(JNIEnv* env, jobject obj, jint playerid, jint skill, jint level) {
	bool ret = SetPlayerSkillLevel(playerid, skill, level);
	return ret;
}

static jint Java_GetPlayerSurfingVehicleID(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = GetPlayerSurfingVehicleID(playerid);
	return ret;
}

static jint Java_GetPlayerSurfingObjectID(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = GetPlayerSurfingObjectID(playerid);
	return ret;
}

static jboolean Java_RemoveBuildingForPlayer(JNIEnv* env, jobject obj, jint playerid, jint modelid, jfloat fX, jfloat fY, jfloat fZ, jfloat fRadius) {
	bool ret = RemoveBuildingForPlayer(playerid, modelid, fX, fY, fZ, fRadius);
	return ret;
}

static jboolean Java_SetPlayerAttachedObject(JNIEnv* env, jobject obj, jint playerid, jint index, jint modelid, jint bone, jfloat fOffsetX = 0.0, jfloat fOffsetY = 0.0, jfloat fOffsetZ = 0.0, jfloat fRotX = 0.0, jfloat fRotY = 0.0, jfloat fRotZ = 0.0, jfloat fScaleX = 1.0, jfloat fScaleY = 1.0, jfloat fScaleZ = 1.0, jint materialcolor1 = 0, jint materialcolor2 = 0) {
	bool ret = SetPlayerAttachedObject(playerid, index, modelid, bone, fOffsetX, fOffsetY, fOffsetZ, fRotX, fRotY, fRotZ, fScaleX, fScaleY, fScaleZ, materialcolor1, materialcolor2);
	return ret;
}

static jboolean Java_RemovePlayerAttachedObject(JNIEnv* env, jobject obj, jint playerid, jint index) {
	bool ret = RemovePlayerAttachedObject(playerid, index);
	return ret;
}

static jboolean Java_IsPlayerAttachedObjectSlotUsed(JNIEnv* env, jobject obj, jint playerid, jint index) {
	bool ret = IsPlayerAttachedObjectSlotUsed(playerid, index);
	return ret;
}

static jboolean Java_EditAttachedObject(JNIEnv* env, jobject obj, jint playerid, jint index) {
	bool ret = EditAttachedObject(playerid, index);
	return ret;
}

static jint Java_CreatePlayerTextDraw(JNIEnv* env, jobject obj, jint playerid, jfloat x, jfloat y, jstring text) {
	char c_str_text[1024 * BYTES_PER_CHARACTER];
	GetJVMString(env, text, c_str_text, sizeof(c_str_text));
	FixTextDrawStringAccents(c_str_text, sizeof(c_str_text));
	jint ret = CreatePlayerTextDraw(playerid, x, y, c_str_text);
	return ret;
}

static jboolean Java_PlayerTextDrawDestroy(JNIEnv* env, jobject obj, jint playerid, jint text) {
	bool ret = PlayerTextDrawDestroy(playerid, text);
	return ret;
}

static jboolean Java_PlayerTextDrawLetterSize(JNIEnv* env, jobject obj, jint playerid, jint text, jfloat x, jfloat y) {
	bool ret = PlayerTextDrawLetterSize(playerid, text, x, y);
	return ret;
}

static jboolean Java_PlayerTextDrawTextSize(JNIEnv* env, jobject obj, jint playerid, jint text, jfloat x, jfloat y) {
	bool ret = PlayerTextDrawTextSize(playerid, text, x, y);
	return ret;
}

static jboolean Java_PlayerTextDrawAlignment(JNIEnv* env, jobject obj, jint playerid, jint text, jint alignment) {
	bool ret = PlayerTextDrawAlignment(playerid, text, alignment);
	return ret;
}

static jboolean Java_PlayerTextDrawColor(JNIEnv* env, jobject obj, jint playerid, jint text, jint color) {
	bool ret = PlayerTextDrawColor(playerid, text, color);
	return ret;
}

static jboolean Java_PlayerTextDrawUseBox(JNIEnv* env, jobject obj, jint playerid, jint text, jboolean use) {
	bool ret = PlayerTextDrawUseBox(playerid, text, use);
	return ret;
}

static jboolean Java_PlayerTextDrawBoxColor(JNIEnv* env, jobject obj, jint playerid, jint text, jint color) {
	bool ret = PlayerTextDrawBoxColor(playerid, text, color);
	return ret;
}

static jboolean Java_PlayerTextDrawSetShadow(JNIEnv* env, jobject obj, jint playerid, jint text, jint size) {
	bool ret = PlayerTextDrawSetShadow(playerid, text, size);
	return ret;
}

static jboolean Java_PlayerTextDrawSetOutline(JNIEnv* env, jobject obj, jint playerid, jint text, jint size) {
	bool ret = PlayerTextDrawSetOutline(playerid, text, size);
	return ret;
}

static jboolean Java_PlayerTextDrawBackgroundColor(JNIEnv* env, jobject obj, jint playerid, jint text, jint color) {
	bool ret = PlayerTextDrawBackgroundColor(playerid, text, color);
	return ret;
}

static jboolean Java_PlayerTextDrawFont(JNIEnv* env, jobject obj, jint playerid, jint text, jint font) {
	bool ret = PlayerTextDrawFont(playerid, text, font);
	return ret;
}

static jboolean Java_PlayerTextDrawSetProportional(JNIEnv* env, jobject obj, jint playerid, jint text, jboolean set) {
	bool ret = PlayerTextDrawSetProportional(playerid, text, set);
	return ret;
}

static jboolean Java_PlayerTextDrawSetSelectable(JNIEnv* env, jobject obj, jint playerid, jint text, jboolean set) {
	bool ret = PlayerTextDrawSetSelectable(playerid, text, set);
	return ret;
}

static jboolean Java_PlayerTextDrawShow(JNIEnv* env, jobject obj, jint playerid, jint text) {
	bool ret = PlayerTextDrawShow(playerid, text);
	return ret;
}

static jboolean Java_PlayerTextDrawHide(JNIEnv* env, jobject obj, jint playerid, jint text) {
	bool ret = PlayerTextDrawHide(playerid, text);
	return ret;
}

static jboolean Java_PlayerTextDrawSetString(JNIEnv* env, jobject obj, jint playerid, jint text, jstring string) {
	char c_str_text[1024 * BYTES_PER_CHARACTER];
	GetJVMString(env, string, c_str_text, sizeof(c_str_text));
	FixTextDrawStringAccents(c_str_text, sizeof(c_str_text));
	bool ret = PlayerTextDrawSetString(playerid, text, c_str_text);
	return ret;
}

static jboolean Java_PlayerTextDrawSetPreviewModel(JNIEnv* env, jobject obj, jint playerid, jint text, jint modelindex) {
	bool ret = PlayerTextDrawSetPreviewModel(playerid, text, modelindex);
	return ret;
}

static jboolean Java_PlayerTextDrawSetPreviewRot(JNIEnv* env, jobject obj, jint playerid, jint text, jfloat fRotX, jfloat fRotY, jfloat fRotZ, jfloat fZoom = 1.0) {
	bool ret = PlayerTextDrawSetPreviewRot(playerid, text, fRotX, fRotY, fRotZ, fZoom);
	return ret;
}

static jboolean Java_PlayerTextDrawSetPreviewVehCol(JNIEnv* env, jobject obj, jint playerid, jint text, jint color1, jint color2) {
	bool ret = PlayerTextDrawSetPreviewVehCol(playerid, text, color1, color2);
	return ret;
}

static jboolean Java_SetPVarInt(JNIEnv* env, jobject obj, jint playerid, jstring varname, jint value) {
    char c_str_varname[64 * BYTES_PER_CHARACTER];
    GetJVMString(env, varname, c_str_varname, sizeof(c_str_varname));
	return SetPVarInt(playerid, c_str_varname, value);
}

static jint Java_GetPVarInt(JNIEnv* env, jobject obj, jint playerid, jstring varname) {
    char c_str_varname[64 * BYTES_PER_CHARACTER];
    GetJVMString(env, varname, c_str_varname, sizeof(c_str_varname));
	jint ret = GetPVarInt(playerid, c_str_varname);
	return ret;
}

static jboolean Java_SetPVarString(JNIEnv* env, jobject obj, jint playerid, jstring varname, jstring value) {
    char c_str_varname[64 * BYTES_PER_CHARACTER];
    GetJVMString(env, varname, c_str_varname, sizeof(c_str_varname));
    char c_str_value[512 * BYTES_PER_CHARACTER];
    GetJVMString(env, value, c_str_value, sizeof(c_str_value));
	bool ret = SetPVarString(playerid, c_str_varname, c_str_value);
	return ret;
}

static jboolean Java_SetPVarFloat(JNIEnv* env, jobject obj, jint playerid, jstring varname, jfloat value) {
    char c_str_varname[64 * BYTES_PER_CHARACTER];
    GetJVMString(env, varname, c_str_varname, sizeof(c_str_varname));
	bool ret = SetPVarFloat(playerid, c_str_varname, value);
	return ret;
}

static jfloat Java_GetPVarFloat(JNIEnv* env, jobject obj, jint playerid, jstring varname) {
    char c_str_varname[64 * BYTES_PER_CHARACTER];
    GetJVMString(env, varname, c_str_varname, sizeof(c_str_varname));
	jfloat ret = GetPVarFloat(playerid, c_str_varname);
	return ret;
}

static jboolean Java_DeletePVar(JNIEnv* env, jobject obj, jint playerid, jstring varname) {
    char c_str_varname[64 * BYTES_PER_CHARACTER];
    GetJVMString(env, varname, c_str_varname, sizeof(c_str_varname));
	bool ret = DeletePVar(playerid, c_str_varname);
	return ret;
}

static jint Java_GetPVarsUpperIndex(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = GetPVarsUpperIndex(playerid);
	return ret;
}

static jint Java_GetPVarType(JNIEnv* env, jobject obj, jint playerid, jstring varname) {
    char c_str_varname[64 * BYTES_PER_CHARACTER];
    GetJVMString(env, varname, c_str_varname, sizeof(c_str_varname));
	jint ret = GetPVarType(playerid, c_str_varname);
	return ret;
}

static jboolean Java_SetPlayerChatBubble(JNIEnv* env, jobject obj, jint playerid, jstring text, jint color, jfloat drawdistance, jint expiretime) {
	char c_str_text[144 * BYTES_PER_CHARACTER];
	GetJVMString(env, text, c_str_text, sizeof(c_str_text));
	bool ret = SetPlayerChatBubble(playerid, c_str_text, color, drawdistance, expiretime);
	return ret;
}

static jboolean Java_PutPlayerInVehicle(JNIEnv* env, jobject obj, jint playerid, jint vehicleid, jint seatid) {
	bool ret = PutPlayerInVehicle(playerid, vehicleid, seatid);
	return ret;
}

static jint Java_GetPlayerVehicleID(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = GetPlayerVehicleID(playerid);
	return ret;
}

static jint Java_GetPlayerVehicleSeat(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = GetPlayerVehicleSeat(playerid);
	return ret;
}

static jboolean Java_RemovePlayerFromVehicle(JNIEnv* env, jobject obj, jint playerid) {
	bool ret = RemovePlayerFromVehicle(playerid);
	return ret;
}

static jboolean Java_TogglePlayerControllable(JNIEnv* env, jobject obj, jint playerid, jboolean toggle) {
	bool ret = TogglePlayerControllable(playerid, toggle);
	return ret;
}

static jboolean Java_PlayerPlaySound(JNIEnv* env, jobject obj, jint playerid, jint soundid, jfloat x, jfloat y, jfloat z) {
	bool ret = PlayerPlaySound(playerid, soundid, x, y, z);
	return ret;
}

static jboolean Java_ApplyAnimation(JNIEnv* env, jobject obj, jint playerid, jstring animlib, jstring animname, jfloat fDelta, jboolean loop, jboolean lockx, jboolean locky, jboolean freeze, jint time, jboolean forcesync = false) {
    char c_str_animlib[32 * BYTES_PER_CHARACTER];
    GetJVMString(env, animlib, c_str_animlib, sizeof(c_str_animlib));
    char c_str_animname[32 * BYTES_PER_CHARACTER];
    GetJVMString(env, animname, c_str_animname, sizeof(c_str_animname));
	bool ret = ApplyAnimation(playerid, c_str_animlib, c_str_animname, fDelta, loop, lockx, locky, freeze, time, forcesync);
	return ret;
}

static jboolean Java_ClearAnimations(JNIEnv* env, jobject obj, jint playerid, jboolean forcesync = false) {
	bool ret = ClearAnimations(playerid, forcesync);
	return ret;
}

static jint Java_GetPlayerAnimationIndex(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = GetPlayerAnimationIndex(playerid);
	return ret;
}

static jint Java_GetPlayerSpecialAction(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = GetPlayerSpecialAction(playerid);
	return ret;
}

static jboolean Java_SetPlayerSpecialAction(JNIEnv* env, jobject obj, jint playerid, jint actionid) {
	bool ret = SetPlayerSpecialAction(playerid, actionid);
	return ret;
}

static jboolean Java_SetPlayerCheckpoint(JNIEnv* env, jobject obj, jint playerid, jfloat x, jfloat y, jfloat z, jfloat size) {
	bool ret = SetPlayerCheckpoint(playerid, x, y, z, size);
	return ret;
}

static jboolean Java_DisablePlayerCheckpoint(JNIEnv* env, jobject obj, jint playerid) {
	bool ret = DisablePlayerCheckpoint(playerid);
	return ret;
}

static jboolean Java_SetPlayerRaceCheckpoint(JNIEnv* env, jobject obj, jint playerid, jint type, jfloat x, jfloat y, jfloat z, jfloat nextx, jfloat nexty, jfloat nextz, jfloat size) {
	bool ret = SetPlayerRaceCheckpoint(playerid, type, x, y, z, nextx, nexty, nextz, size);
	return ret;
}

static jboolean Java_DisablePlayerRaceCheckpoint(JNIEnv* env, jobject obj, jint playerid) {
	bool ret = DisablePlayerRaceCheckpoint(playerid);
	return ret;
}

static jboolean Java_SetPlayerWorldBounds(JNIEnv* env, jobject obj, jint playerid, jfloat x_max, jfloat x_min, jfloat y_max, jfloat y_min) {
	bool ret = SetPlayerWorldBounds(playerid, x_max, x_min, y_max, y_min);
	return ret;
}

static jboolean Java_SetPlayerMarkerForPlayer(JNIEnv* env, jobject obj, jint playerid, jint showplayerid, jint color) {
	bool ret = SetPlayerMarkerForPlayer(playerid, showplayerid, color);
	return ret;
}

static jboolean Java_ShowPlayerNameTagForPlayer(JNIEnv* env, jobject obj, jint playerid, jint showplayerid, jboolean show) {
	bool ret = ShowPlayerNameTagForPlayer(playerid, showplayerid, show);
	return ret;
}

static jboolean Java_SetPlayerMapIcon(JNIEnv* env, jobject obj, jint playerid, jint iconid, jfloat x, jfloat y, jfloat z, jint markertype, jint color, jint style = MAPICON_LOCAL) {
	bool ret = SetPlayerMapIcon(playerid, iconid, x, y, z, markertype, color, style);
	return ret;
}

static jboolean Java_RemovePlayerMapIcon(JNIEnv* env, jobject obj, jint playerid, jint iconid) {
	bool ret = RemovePlayerMapIcon(playerid, iconid);
	return ret;
}

static jboolean Java_AllowPlayerTeleport(JNIEnv* env, jobject obj, jint playerid, jboolean allow) {
	bool ret = AllowPlayerTeleport(playerid, allow);
	return ret;
}
//
static jboolean Java_SetPlayerCameraPos(JNIEnv* env, jobject obj, jint playerid, jfloat x, jfloat y, jfloat z) {
	bool ret = SetPlayerCameraPos(playerid, x, y, z);
	return ret;
}

static jboolean Java_SetPlayerCameraLookAt(JNIEnv* env, jobject obj, jint playerid, jfloat x, jfloat y, jfloat z, jint cut = CAMERA_CUT) {
	bool ret = SetPlayerCameraLookAt(playerid, x, y, z, cut);
	return ret;
}

static jboolean Java_SetCameraBehindPlayer(JNIEnv* env, jobject obj, jint playerid) {
	bool ret = SetCameraBehindPlayer(playerid);
	return ret;
}

static jint Java_GetPlayerCameraMode(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = GetPlayerCameraMode(playerid);
	return ret;
}

static jfloat Java_GetPlayerCameraAspectRatio(JNIEnv* env, jobject obj, jint playerid) {
	jfloat ret = GetPlayerCameraAspectRatio(playerid);
	return ret;
}

static jfloat Java_GetPlayerCameraZoom(JNIEnv* env, jobject obj, jint playerid) {
	jfloat ret = GetPlayerCameraZoom(playerid);
	return ret;
}

static jboolean Java_AttachCameraToObject(JNIEnv* env, jobject obj, jint playerid, jint objectid) {
	bool ret = AttachCameraToObject(playerid, objectid);
	return ret;
}

static jboolean Java_AttachCameraToPlayerObject(JNIEnv* env, jobject obj, jint playerid, jint playerobjectid) {
	bool ret = AttachCameraToPlayerObject(playerid, playerobjectid);
	return ret;
}

static jboolean Java_InterpolateCameraPos(JNIEnv* env, jobject obj, jint playerid, jfloat FromX, jfloat FromY, jfloat FromZ, jfloat ToX, jfloat ToY, jfloat ToZ, jint time, jint cut = CAMERA_CUT) {
	bool ret = InterpolateCameraPos(playerid, FromX, FromY, FromZ, ToX, ToY, ToZ, time, cut);
	return ret;
}

static jboolean Java_InterpolateCameraLookAt(JNIEnv* env, jobject obj, jint playerid, jfloat FromX, jfloat FromY, jfloat FromZ, jfloat ToX, jfloat ToY, jfloat ToZ, jint time, jint cut = CAMERA_CUT) {
	bool ret = InterpolateCameraLookAt(playerid, FromX, FromY, FromZ, ToX, ToY, ToZ, time, cut);
	return ret;
}

static jboolean Java_IsPlayerConnected(JNIEnv* env, jobject obj, jint playerid) {
	bool ret = IsPlayerConnected(playerid);
	return ret;
}

static jboolean Java_IsPlayerInVehicle(JNIEnv* env, jobject obj, jint playerid, jint vehicleid) {
	bool ret = IsPlayerInVehicle(playerid, vehicleid);
	return ret;
}

static jboolean Java_IsPlayerInAnyVehicle(JNIEnv* env, jobject obj, jint playerid) {
	bool ret = IsPlayerInAnyVehicle(playerid);
	return ret;
}

static jboolean Java_IsPlayerInCheckpoint(JNIEnv* env, jobject obj, jint playerid) {
	bool ret = IsPlayerInCheckpoint(playerid);
	return ret;
}

static jboolean Java_IsPlayerInRaceCheckpoint(JNIEnv* env, jobject obj, jint playerid) {
	bool ret = IsPlayerInRaceCheckpoint(playerid);
	return ret;
}

static jboolean Java_SetPlayerVirtualWorld(JNIEnv* env, jobject obj, jint playerid, jint worldid) {
	bool ret = SetPlayerVirtualWorld(playerid, worldid);
	return ret;
}

static jint Java_GetPlayerVirtualWorld(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = GetPlayerVirtualWorld(playerid);
	return ret;
}

static jboolean Java_EnableStuntBonusForPlayer(JNIEnv* env, jobject obj, jint playerid, jboolean enable) {
	bool ret = EnableStuntBonusForPlayer(playerid, enable);
	return ret;
}

static jboolean Java_EnableStuntBonusForAll(JNIEnv* env, jobject obj, jboolean enable) {
	bool ret = EnableStuntBonusForAll(enable);
	return ret;
}

static jboolean Java_TogglePlayerSpectating(JNIEnv* env, jobject obj, jint playerid, jboolean toggle) {
	bool ret = TogglePlayerSpectating(playerid, toggle);
	return ret;
}

static jboolean Java_PlayerSpectatePlayer(JNIEnv* env, jobject obj, jint playerid, jint targetplayerid, jint mode = SPECTATE_MODE_NORMAL) {
	bool ret = PlayerSpectatePlayer(playerid, targetplayerid, mode);
	return ret;
}

static jboolean Java_PlayerSpectateVehicle(JNIEnv* env, jobject obj, jint playerid, jint targetvehicleid, jint mode = SPECTATE_MODE_NORMAL) {
	bool ret = PlayerSpectateVehicle(playerid, targetvehicleid, mode);
	return ret;
}

static jboolean Java_StartRecordingPlayerData(JNIEnv* env, jobject obj, jint playerid, jint recordtype, jstring recordname) {
    char c_str_recordname[64 * BYTES_PER_CHARACTER];
    GetJVMString(env, recordname, c_str_recordname, sizeof(c_str_recordname));
	bool ret = StartRecordingPlayerData(playerid, recordtype, c_str_recordname);
	return ret;
}

static jboolean Java_StopRecordingPlayerData(JNIEnv* env, jobject obj, jint playerid) {
	bool ret = StopRecordingPlayerData(playerid);
	return ret;
}

static jboolean Java_CreateExplosionForPlayer(JNIEnv* env, jobject obj, jint playerid, jfloat X, jfloat Y, jfloat Z, jint type, jfloat Radius) {
	bool ret = CreateExplosionForPlayer(playerid, X, Y, Z, type, Radius);
	return ret;
}

static jfloatArray Java_GetPlayerPos(JNIEnv* env, jobject obj, jint playerid) {
	jfloatArray result;
	result = env->NewFloatArray(3);
	if (result == NULL) {
		return NULL;
	}
	float x, y, z;
	GetPlayerPos(playerid, &x, &y, &z);
	jfloat pos[3] = { x, y, z };
	env->SetFloatArrayRegion(result, 0, 3, pos);
	return result;
}

static jfloat Java_GetPlayerFacingAngle(JNIEnv* env, jobject obj, jint playerid) {
	float angle;
	GetPlayerFacingAngle(playerid, &angle);
	return angle;
}

static jfloat Java_GetPlayerHealth(JNIEnv* env, jobject obj, jint playerid) {
	float health;
	GetPlayerHealth(playerid, &health);
	return health;
}

static jfloat Java_GetPlayerArmour(JNIEnv* env, jobject obj, jint playerid) {
	float armour;
	GetPlayerArmour(playerid, &armour);
	return armour;
}

static jint Java_GetPlayerWeaponSlot(JNIEnv* env, jobject obj, jint playerid, jint slot) {
	int weapon, ammo;
	GetPlayerWeaponData(playerid, slot, &weapon, &ammo);
	return weapon;
}

static jint Java_GetPlayerAmmoSlot(JNIEnv* env, jobject obj, jint playerid, jint slot) {
	int weapon, ammo;
	GetPlayerWeaponData(playerid, slot, &weapon, &ammo);
	return ammo;
}

static jstring Java_GetPlayerIp(JNIEnv* env, jobject obj, jint playerid) {
	char ip[16]; // Only IPV4 please
	GetPlayerIp(playerid, ip, 16);
	return env->NewStringUTF(ip);
}

static jintArray Java_GetPlayerKeys(JNIEnv* env, jobject obj, jint playerid) {
	int keys, updown, leftright;
	GetPlayerKeys(playerid, &keys, &updown, &leftright);
	jint res[3] = { keys, updown, leftright };
	jintArray result = env->NewIntArray(3);
	env->SetIntArrayRegion(result, 0, 3, res);
	return result;
}

static jstring Java_GetPlayerName(JNIEnv* env, jobject obj, jint playerid) {
	char name[24];
	GetPlayerName(playerid, name, 24);
	return env->NewStringUTF(name);
}

static jint Java_GetPlayerHour(JNIEnv* env, jobject obj, jint playerid) {
	int hour, minute;
	GetPlayerTime(playerid, &hour, &minute);
	return hour;
}

static jint Java_GetPlayerTime(JNIEnv* env, jobject obj, jint playerid) {
	int hour, minute;
	GetPlayerTime(playerid, &hour, &minute);
	return minute;
}

static jfloatArray Java_GetPlayerVelocity(JNIEnv* env, jobject obj, jint playerid) {
	jfloatArray result;
	result = env->NewFloatArray(3);
	if (result == NULL) {
		return NULL;
	}
	float x, y, z;
	GetPlayerVelocity(playerid, &x, &y, &z);
	jfloat pos[3] = { x, y, z };
	env->SetFloatArrayRegion(result, 0, 3, pos);
	return result;
}

static jfloatArray Java_GetPlayerLastShotVectors(JNIEnv* env, jobject obj, jint playerid) {
	jfloatArray result;
	result = env->NewFloatArray(6);
	if (result == NULL) {
		return NULL;
	}
	float ox, oy, oz, hx, hy, hz;
	GetPlayerLastShotVectors(playerid, &ox, &oy, &oz, &hx, &hy, &hz);
	jfloat res[6] = { ox, oy, oz, hx, hy, hz };
	env->SetFloatArrayRegion(result, 0, 6, res);
	return result;
}

static jstring Java_GetPVarString(JNIEnv* env, jobject obj, jint playerid, jstring varname) {
	char value[512];
    char c_str_varname[64 * BYTES_PER_CHARACTER];
    GetJVMString(env, varname, c_str_varname, sizeof(c_str_varname));
	GetPVarString(playerid, c_str_varname, value, sizeof(value));
	return env->NewStringUTF(value);
}

static jstring Java_GetPVarNameAtIndex(JNIEnv* env, jobject obj, jint playerid, jint index) {
	char value[64];
	GetPVarNameAtIndex(playerid, index, value, 64);
	return env->NewStringUTF(value);
}

static jstring Java_GetAnimationLibrary(JNIEnv* env, jobject obj, jint index) {
	char animlib[64], animname[64];
	GetAnimationName(index, animlib, 64, animname, 64);
	return env->NewStringUTF(animlib);
}

static jstring Java_GetAnimationName(JNIEnv* env, jobject obj, jint index) {
	char animlib[64], animname[64];
	GetAnimationName(index, animlib, 64, animname, 64);
	return env->NewStringUTF(animname);
}

static jfloatArray Java_GetPlayerCameraPos(JNIEnv* env, jobject obj, jint playerid) {
	jfloatArray result;
	result = env->NewFloatArray(3);
	if (result == NULL) {
		return NULL;
	}
	float x, y, z;
	GetPlayerCameraPos(playerid, &x, &y, &z);
	jfloat pos[3] = { x, y, z };
	env->SetFloatArrayRegion(result, 0, 3, pos);
	return result;
}

static jfloatArray Java_GetPlayerCameraFrontVector(JNIEnv* env, jobject obj, jint playerid) {
	jfloatArray result;
	result = env->NewFloatArray(3);
	if (result == NULL) {
		return NULL;
	}
	float x, y, z;
	GetPlayerCameraFrontVector(playerid, &x, &y, &z);
	jfloat pos[3] = { x, y, z };
	env->SetFloatArrayRegion(result, 0, 3, pos);
	return result;
}

//a_objects
static jint Java_CreateObject(JNIEnv* env, jobject obj, jint modelid, jfloat x, jfloat y, jfloat z, jfloat rX, jfloat rY, jfloat rZ, jfloat DrawDistance = 0.0) {
	jint ret = CreateObject(modelid, x, y, z, rX, rY, rZ, DrawDistance);
	return ret;
}

static jboolean Java_AttachObjectToVehicle(JNIEnv* env, jobject obj, jint objectid, jint vehicleid, jfloat fOffsetX, jfloat fOffsetY, jfloat fOffsetZ, jfloat fRotX, jfloat fRotY, jfloat fRotZ) {
	bool ret = AttachObjectToVehicle(objectid, vehicleid, fOffsetX, fOffsetY, fOffsetZ, fRotX, fRotY, fRotZ);
	return ret;
}

static jboolean Java_AttachObjectToObject(JNIEnv* env, jobject obj, jint objectid, jint attachtoid, jfloat fOffsetX, jfloat fOffsetY, jfloat fOffsetZ, jfloat fRotX, jfloat fRotY, jfloat fRotZ, jboolean SyncRotation = false) {
	bool ret = AttachObjectToObject(objectid, attachtoid, fOffsetX, fOffsetY, fOffsetZ, fRotX, fRotY, fRotZ, SyncRotation);
	return ret;
}

static jboolean Java_AttachObjectToPlayer(JNIEnv* env, jobject obj, jint objectid, jint playerid, jfloat fOffsetX, jfloat fOffsetY, jfloat fOffsetZ, jfloat fRotX, jfloat fRotY, jfloat fRotZ) {
	bool ret = AttachObjectToPlayer(objectid, playerid, fOffsetX, fOffsetY, fOffsetZ, fRotX, fRotY, fRotZ);
	return ret;
}

static jboolean Java_SetObjectPos(JNIEnv* env, jobject obj, jint objectid, jfloat x, jfloat y, jfloat z) {
	bool ret = SetObjectPos(objectid, x, y, z);
	return ret;
}

static jboolean Java_SetObjectRot(JNIEnv* env, jobject obj, jint objectid, jfloat rotX, jfloat rotY, jfloat rotZ) {
	bool ret = SetObjectRot(objectid, rotX, rotY, rotZ);
	return ret;
}

static jboolean Java_IsValidObject(JNIEnv* env, jobject obj, jint objectid) {
	bool ret = IsValidObject(objectid);
	return ret;
}

static jboolean Java_DestroyObject(JNIEnv* env, jobject obj, jint objectid) {
	bool ret = DestroyObject(objectid);
	return ret;
}

static jint Java_MoveObject(JNIEnv* env, jobject obj, jint objectid, jfloat X, jfloat Y, jfloat Z, jfloat Speed, jfloat RotX = -1000.0, jfloat RotY = -1000.0, jfloat RotZ = -1000.0) {
	jint ret = MoveObject(objectid, X, Y, Z, Speed, RotX, RotY, RotZ);
	return ret;
}

static jboolean Java_StopObject(JNIEnv* env, jobject obj, jint objectid) {
	bool ret = StopObject(objectid);
	return ret;
}

static jboolean Java_IsObjectMoving(JNIEnv* env, jobject obj, jint objectid) {
	bool ret = IsObjectMoving(objectid);
	return ret;
}

static jboolean Java_EditObject(JNIEnv* env, jobject obj, jint playerid, jint objectid) {
	bool ret = EditObject(playerid, objectid);
	return ret;
}

static jboolean Java_EditPlayerObject(JNIEnv* env, jobject obj, jint playerid, jint objectid) {
	bool ret = EditPlayerObject(playerid, objectid);
	return ret;
}

static jboolean Java_SelectObject(JNIEnv* env, jobject obj, jint playerid) {
	bool ret = SelectObject(playerid);
	return ret;
}

static jboolean Java_CancelEdit(JNIEnv* env, jobject obj, jint playerid) {
	bool ret = CancelEdit(playerid);
	return ret;
}

static jint Java_CreatePlayerObject(JNIEnv* env, jobject obj, jint playerid, jint modelid, jfloat x, jfloat y, jfloat z, jfloat rX, jfloat rY, jfloat rZ, jfloat DrawDistance = 0.0) {
	jint ret = CreatePlayerObject(playerid, modelid, x, y, z, rX, rY, rZ, DrawDistance);
	return ret;
}

static jboolean Java_AttachPlayerObjectToPlayer(JNIEnv* env, jobject obj, jint objectplayer, jint objectid, jint attachplayer, jfloat OffsetX, jfloat OffsetY, jfloat OffsetZ, jfloat rX, jfloat rY, jfloat rZ) {
	bool ret = AttachPlayerObjectToPlayer(objectplayer, objectid, attachplayer, OffsetX, OffsetY, OffsetZ, rX, rY, rZ);
	return ret;
}

static jboolean Java_AttachPlayerObjectToVehicle(JNIEnv* env, jobject obj, jint playerid, jint objectid, jint vehicleid, jfloat fOffsetX, jfloat fOffsetY, jfloat fOffsetZ, jfloat fRotX, jfloat fRotY, jfloat RotZ) {
	bool ret = AttachPlayerObjectToVehicle(playerid, objectid, vehicleid, fOffsetX, fOffsetY, fOffsetZ, fRotX, fRotY, RotZ);
	return ret;
}

static jboolean Java_SetPlayerObjectPos(JNIEnv* env, jobject obj, jint playerid, jint objectid, jfloat x, jfloat y, jfloat z) {
	bool ret = SetPlayerObjectPos(playerid, objectid, x, y, z);
	return ret;
}

static jboolean Java_SetPlayerObjectRot(JNIEnv* env, jobject obj, jint playerid, jint objectid, jfloat rotX, jfloat rotY, jfloat rotZ) {
	bool ret = SetPlayerObjectRot(playerid, objectid, rotX, rotY, rotZ);
	return ret;
}

static jboolean Java_IsValidPlayerObject(JNIEnv* env, jobject obj, jint playerid, jint objectid) {
	bool ret = IsValidPlayerObject(playerid, objectid);
	return ret;
}

static jboolean Java_DestroyPlayerObject(JNIEnv* env, jobject obj, jint playerid, jint objectid) {
	bool ret = DestroyPlayerObject(playerid, objectid);
	return ret;
}

static jint Java_MovePlayerObject(JNIEnv* env, jobject obj, jint playerid, jint objectid, jfloat x, jfloat y, jfloat z, jfloat Speed, jfloat RotX = -1000.0, jfloat RotY = -1000.0, jfloat RotZ = -1000.0) {
	jint ret = MovePlayerObject(playerid, objectid, x, y, z, Speed, RotX, RotY, RotZ);
	return ret;
}

static jboolean Java_StopPlayerObject(JNIEnv* env, jobject obj, jint playerid, jint objectid) {
	bool ret = StopPlayerObject(playerid, objectid);
	return ret;
}

static jboolean Java_IsPlayerObjectMoving(JNIEnv* env, jobject obj, jint playerid, jint objectid) {
	bool ret = IsPlayerObjectMoving(playerid, objectid);
	return ret;
}

static jboolean Java_SetObjectMaterial(JNIEnv* env, jobject obj, jint objectid, jint materialindex, jint modelid, jstring txdname, jstring texturename, jint materialcolor = 0) {
    char c_str_txdname[64 * BYTES_PER_CHARACTER];
    GetJVMString(env, txdname, c_str_txdname, sizeof(c_str_txdname));
    char c_str_texturename[64 * BYTES_PER_CHARACTER];
    GetJVMString(env, texturename, c_str_texturename, sizeof(c_str_texturename));
	bool ret = SetObjectMaterial(objectid, materialindex, modelid, c_str_txdname, c_str_texturename, materialcolor);
	return ret;
}

static jboolean Java_SetPlayerObjectMaterial(JNIEnv* env, jobject obj, jint playerid, jint objectid, jint materialindex, jint modelid, jstring txdname, jstring texturename, jint materialcolor = 0) {
    char c_str_txdname[64 * BYTES_PER_CHARACTER];
    GetJVMString(env, txdname, c_str_txdname, sizeof(c_str_txdname));
    char c_str_texturename[64 * BYTES_PER_CHARACTER];
    GetJVMString(env, texturename, c_str_texturename, sizeof(c_str_texturename));
	bool ret = SetPlayerObjectMaterial(playerid, objectid, materialindex, modelid, c_str_txdname, c_str_texturename, materialcolor);
	return ret;
}

static jboolean Java_SetObjectMaterialText(JNIEnv* env, jobject obj, jint objectid, jstring text, jint materialindex = 0, jint materialsize = OBJECT_MATERIAL_SIZE_256x128, jstring fontface = NULL, jint fontsize = 24, jboolean bold = true, jint fontcolor = 0xFFFFFFFF, jint backcolor = 0, jint textalignment = 0) {
    char c_str_text[256 * BYTES_PER_CHARACTER];
    GetJVMString(env, text, c_str_text, sizeof(c_str_text));
    char c_str_fontface[32 * BYTES_PER_CHARACTER];
    GetJVMString(env, fontface, c_str_fontface, sizeof(c_str_fontface));
	bool ret = SetObjectMaterialText(objectid, c_str_text, materialindex, materialsize, c_str_fontface, fontsize, bold, fontcolor, backcolor, textalignment);
	return ret;
}

static jboolean Java_SetPlayerObjectMaterialText(JNIEnv* env, jobject obj, jint playerid, jint objectid, jstring text, jint materialindex = 0, jint materialsize = OBJECT_MATERIAL_SIZE_256x128, jstring fontface = NULL, jint fontsize = 24, jboolean bold = true, jint fontcolor = 0xFFFFFFFF, jint backcolor = 0, jint textalignment = 0) {
    char c_str_text[256 * BYTES_PER_CHARACTER];
    GetJVMString(env, text, c_str_text, sizeof(c_str_text));
    char c_str_fontface[32 * BYTES_PER_CHARACTER];
    GetJVMString(env, fontface, c_str_fontface, sizeof(c_str_fontface));
	bool ret = SetPlayerObjectMaterialText(playerid, objectid, c_str_text, materialindex, materialsize, c_str_fontface, fontsize, bold, fontcolor, backcolor, textalignment);
	return ret;
}


static jfloatArray Java_GetObjectPos(JNIEnv* env, jobject obj, jint objectid) {
	jfloatArray result;
	result = env->NewFloatArray(3);
	if (result == NULL) {
		return NULL;
	}
	float x, y, z;
	GetObjectPos(objectid, &x, &y, &z);
	jfloat pos[3] = { x, y, z };
	env->SetFloatArrayRegion(result, 0, 3, pos);
	return result;
}

static jfloatArray Java_GetObjectRot(JNIEnv* env, jobject obj, jint objectid) {
	jfloatArray result;
	result = env->NewFloatArray(3);
	if (result == NULL) {
		return NULL;
	}
	float x, y, z;
	GetObjectRot(objectid, &x, &y, &z);
	jfloat pos[3] = { x, y, z };
	env->SetFloatArrayRegion(result, 0, 3, pos);
	return result;
}

static jfloatArray Java_GetPlayerObjectPos(JNIEnv* env, jobject obj, jint playerid, jint objectid) {
	jfloatArray result;
	result = env->NewFloatArray(3);
	if (result == NULL) {
		return NULL;
	}
	float x, y, z;
	GetPlayerObjectPos(playerid, objectid, &x, &y, &z);
	jfloat pos[3] = { x, y, z };
	env->SetFloatArrayRegion(result, 0, 3, pos);
	return result;
}

static jfloatArray Java_GetPlayerObjectRot(JNIEnv* env, jobject obj, jint playerid, jint objectid) {
	jfloatArray result;
	result = env->NewFloatArray(3);
	if (result == NULL) {
		return NULL;
	}
	float x, y, z;
	GetPlayerObjectRot(playerid, objectid, &x, &y, &z);
	jfloat pos[3] = { x, y, z };
	env->SetFloatArrayRegion(result, 0, 3, pos);
	return result;
}

//a_vehicles
static jboolean Java_IsValidVehicle(JNIEnv* env, jobject obj, jint vehicleid) {
	bool ret = IsValidVehicle(vehicleid);
	return ret;
}

static jfloat Java_GetVehicleDistanceFromPoint(JNIEnv* env, jobject obj, jint vehicleid, jfloat x, jfloat y, jfloat z) {
	jfloat ret = GetVehicleDistanceFromPoint(vehicleid, x, y, z);
	return ret;
}

static jint Java_CreateVehicle(JNIEnv* env, jobject obj, jint vehicletype, jfloat x, jfloat y, jfloat z, jfloat rotation, jint color1, jint color2, jint respawn_delay, jboolean addsiren) {
	jint ret = CreateVehicle(vehicletype, x, y, z, rotation, color1, color2, respawn_delay, addsiren);
	return ret;
}

static jboolean Java_DestroyVehicle(JNIEnv* env, jobject obj, jint vehicleid) {
	bool ret = DestroyVehicle(vehicleid);
	return ret;
}

static jboolean Java_IsVehicleStreamedIn(JNIEnv* env, jobject obj, jint vehicleid, jint forplayerid) {
	bool ret = IsVehicleStreamedIn(vehicleid, forplayerid);
	return ret;
}

static jboolean Java_SetVehiclePos(JNIEnv* env, jobject obj, jint vehicleid, jfloat x, jfloat y, jfloat z) {
	bool ret = SetVehiclePos(vehicleid, x, y, z);
	return ret;
}

static jboolean Java_SetVehicleZAngle(JNIEnv* env, jobject obj, jint vehicleid, jfloat z_angle) {
	bool ret = SetVehicleZAngle(vehicleid, z_angle);
	return ret;
}

static jboolean Java_SetVehicleParamsForPlayer(JNIEnv* env, jobject obj, jint vehicleid, jint playerid, jboolean objective, jboolean doorslocked) {
	bool ret = SetVehicleParamsForPlayer(vehicleid, playerid, objective, doorslocked);
	return ret;
}

static jboolean Java_ManualVehicleEngineAndLights(JNIEnv* env, jobject obj) {
	bool ret = ManualVehicleEngineAndLights();
	return ret;
}

static jboolean Java_SetVehicleParamsEx(JNIEnv* env, jobject obj, jint vehicleid, jboolean engine, jboolean lights, jboolean alarm, jboolean doors, jboolean bonnet, jboolean boot, jboolean objective) {
	bool ret = SetVehicleParamsEx(vehicleid, engine, lights, alarm, doors, bonnet, boot, objective);
	return ret;
}

static jboolean Java_SetVehicleToRespawn(JNIEnv* env, jobject obj, jint vehicleid) {
	bool ret = SetVehicleToRespawn(vehicleid);
	return ret;
}

static jboolean Java_LinkVehicleToInterior(JNIEnv* env, jobject obj, jint vehicleid, jint jinteriorid) {
	bool ret = LinkVehicleToInterior(vehicleid, jinteriorid);
	return ret;
}

static jboolean Java_AddVehicleComponent(JNIEnv* env, jobject obj, jint vehicleid, jint componentid) {
	bool ret = AddVehicleComponent(vehicleid, componentid);
	return ret;
}

static jboolean Java_RemoveVehicleComponent(JNIEnv* env, jobject obj, jint vehicleid, jint componentid) {
	bool ret = RemoveVehicleComponent(vehicleid, componentid);
	return ret;
}

static jboolean Java_ChangeVehicleColor(JNIEnv* env, jobject obj, jint vehicleid, jint color1, jint color2) {
	bool ret = ChangeVehicleColor(vehicleid, color1, color2);
	return ret;
}

static jboolean Java_ChangeVehiclePaintjob(JNIEnv* env, jobject obj, jint vehicleid, jint pajintjobid) {
	bool ret = ChangeVehiclePaintjob(vehicleid, pajintjobid);
	return ret;
}

static jboolean Java_SetVehicleHealth(JNIEnv* env, jobject obj, jint vehicleid, jfloat health) {
	bool ret = SetVehicleHealth(vehicleid, health);
	return ret;
}

static jboolean Java_AttachTrailerToVehicle(JNIEnv* env, jobject obj, jint trailerid, jint vehicleid) {
	bool ret = AttachTrailerToVehicle(trailerid, vehicleid);
	return ret;
}

static jboolean Java_DetachTrailerFromVehicle(JNIEnv* env, jobject obj, jint vehicleid) {
	bool ret = DetachTrailerFromVehicle(vehicleid);
	return ret;
}

static jboolean Java_IsTrailerAttachedToVehicle(JNIEnv* env, jobject obj, jint vehicleid) {
	bool ret = IsTrailerAttachedToVehicle(vehicleid);
	return ret;
}

static jint Java_GetVehicleTrailer(JNIEnv* env, jobject obj, jint vehicleid) {
	jint ret = GetVehicleTrailer(vehicleid);
	return ret;
}

static jboolean Java_SetVehicleNumberPlate(JNIEnv* env, jobject obj, jint vehicleid, jstring numberplate) {
	char c_str_numberplate[32 * BYTES_PER_CHARACTER];
	GetJVMString(env, numberplate, c_str_numberplate, sizeof(c_str_numberplate));
	bool ret = SetVehicleNumberPlate(vehicleid, c_str_numberplate);
	return ret;
}

static jint Java_GetVehicleModel(JNIEnv* env, jobject obj, jint vehicleid) {
	jint ret = GetVehicleModel(vehicleid);
	return ret;
}

static jint Java_GetVehicleComponentInSlot(JNIEnv* env, jobject obj, jint vehicleid, jint slot) {
	jint ret = GetVehicleComponentInSlot(vehicleid, slot);
	return ret;
}

static jint Java_GetVehicleComponentType(JNIEnv* env, jobject obj, jint component) {
	jint ret = GetVehicleComponentType(component);
	return ret;
}

static jboolean Java_RepairVehicle(JNIEnv* env, jobject obj, jint vehicleid) {
	bool ret = RepairVehicle(vehicleid);
	return ret;
}

static jboolean Java_SetVehicleVelocity(JNIEnv* env, jobject obj, jint vehicleid, jfloat X, jfloat Y, jfloat Z) {
	bool ret = SetVehicleVelocity(vehicleid, X, Y, Z);
	return ret;
}

static jboolean Java_SetVehicleAngularVelocity(JNIEnv* env, jobject obj, jint vehicleid, jfloat X, jfloat Y, jfloat Z) {
	bool ret = SetVehicleAngularVelocity(vehicleid, X, Y, Z);
	return ret;
}

static jboolean Java_UpdateVehicleDamageStatus(JNIEnv* env, jobject obj, jint vehicleid, jint panels, jint doors, jint lights, jint tires) {
	bool ret = UpdateVehicleDamageStatus(vehicleid, panels, doors, lights, tires);
	return ret;
}

static jboolean Java_SetVehicleVirtualWorld(JNIEnv* env, jobject obj, jint vehicleid, jint worldid) {
	bool ret = SetVehicleVirtualWorld(vehicleid, worldid);
	return ret;
}

static jint Java_GetVehicleVirtualWorld(JNIEnv* env, jobject obj, jint vehicleid) {
	jint ret = GetVehicleVirtualWorld(vehicleid);
	return ret;
}


static jfloatArray Java_GetVehiclePos(JNIEnv* env, jobject obj, jint vehicleid) {
	jfloatArray result;
	result = env->NewFloatArray(3);
	if (result == NULL) {
		return NULL;
	}
	float x, y, z;
	GetVehiclePos(vehicleid, &x, &y, &z);
	jfloat pos[3] = { x, y, z };
	env->SetFloatArrayRegion(result, 0, 3, pos);
	return result;
}

static jfloat Java_GetVehicleZAngle(JNIEnv* env, jobject obj, jint vehicleid) {
	float angle;
	GetVehicleZAngle(vehicleid, &angle);
	return angle;
}

static jfloatArray Java_GetVehicleRotationQuat(JNIEnv* env, jobject obj, jint vehicleid) {
	jfloatArray result;
	result = env->NewFloatArray(4);
	if (result == NULL) {
		return NULL;
	}
	float w, x, y, z;
	GetVehicleRotationQuat(vehicleid, &w, &x, &y, &z);
	jfloat pos[4] = { w, x, y, z };
	env->SetFloatArrayRegion(result, 0, 4, pos);
	return result;
}

static jintArray Java_GetVehicleParamsEx(JNIEnv* env, jobject obj, jint vehicleid) {
	jintArray result;
	result = env->NewIntArray(7);
	if (result == NULL) {
		return NULL;
	}
	int engine, lights, alarm, doors, bonnet, boot, objective;
	GetVehicleParamsEx(vehicleid, &engine, &lights, &alarm, &doors, &bonnet, &boot, &objective);
	jint args[7] = { engine, lights, alarm, doors, bonnet, boot, objective };
	env->SetIntArrayRegion(result, 0, 7, args);
	return result;
}

static jfloat Java_GetVehicleHealth(JNIEnv* env, jobject obj, jint vehicleid) {
	float health;
	GetVehicleHealth(vehicleid, &health);
	return health;
}

static jfloatArray Java_GetVehicleVelocity(JNIEnv* env, jobject obj, jint vehicleid) {
	jfloatArray result;
	result = env->NewFloatArray(3);
	if (result == NULL) {
		return NULL;
	}
	float x, y, z;
	GetVehicleVelocity(vehicleid, &x, &y, &z);
	jfloat pos[3] = { x, y, z };
	env->SetFloatArrayRegion(result, 0, 3, pos);
	return result;
}

static jintArray Java_GetVehicleDamageStatus(JNIEnv* env, jobject obj, jint vehicleid) {
	jintArray result;
	result = env->NewIntArray(4);
	if (result == NULL) {
		return NULL;
	}
	int panels, doors, lights, tires;
	GetVehicleDamageStatus(vehicleid, &panels, &doors, &lights, &tires);
	jint pos[4] = { panels, doors, lights, tires };
	env->SetIntArrayRegion(result, 0, 4, pos);
	return result;
}

static jfloatArray Java_GetVehicleModelInfo(JNIEnv* env, jobject obj, jint vehicleid, jint infotype) {
	jfloatArray result;
	result = env->NewFloatArray(3);
	if (result == NULL) {
		return NULL;
	}
	float x, y, z;
	GetVehicleModelInfo(vehicleid, infotype, &x, &y, &z);
	jfloat pos[3] = { x, y, z };
	env->SetFloatArrayRegion(result, 0, 3, pos);
	return result;
}

static jboolean Java_crash(JNIEnv* env, jobject obj) {
	int *ptr = NULL;
	*ptr = 0;
	return true;
}

//a_samp
static jboolean Java_SendClientMessage(JNIEnv* env, jobject obj, jint playerid, jint color, jstring message) {
	char c_str_message[144 * BYTES_PER_CHARACTER];
	GetJVMString(env, message, c_str_message, sizeof(c_str_message));
	bool ret = SendClientMessage(playerid, color, c_str_message);
	return ret;
}

static jboolean Java_SendClientMessageToAll(JNIEnv* env, jobject obj, jint color, jstring message) {
	char c_str_message[144 * BYTES_PER_CHARACTER];
	GetJVMString(env, message, c_str_message, sizeof(c_str_message));
	bool ret = SendClientMessageToAll(color, c_str_message);
	return ret;
}

static jboolean Java_SendPlayerMessageToPlayer(JNIEnv* env, jobject obj, jint playerid, jint senderid, jstring message) {
	/* DEPRECATED */
	return true;
}

static jboolean Java_SendPlayerMessageToAll(JNIEnv* env, jobject obj, jint senderid, jstring message) {
	/* DEPRECATED */
	return true;
}

static jboolean Java_SendDeathMessage(JNIEnv* env, jobject obj, jint killer, jint killee, jint weapon) {
	bool ret = SendDeathMessage(killer, killee, weapon);
	return ret;
}

static jboolean Java_SendDeathMessageToPlayer(JNIEnv* env, jobject obj, jint playerid, jint killer, jint killee, jint weapon) {
	bool ret = SendDeathMessageToPlayer(playerid, killer, killee, weapon);
	return ret;
}

static jboolean Java_GameTextForAll(JNIEnv* env, jobject obj, jstring text, jint time, jint style) {
	char c_str_text[512 * BYTES_PER_CHARACTER];
	GetJVMString(env, text, c_str_text, sizeof(c_str_text));
	FixTextDrawStringAccents(c_str_text, sizeof(c_str_text));
	bool ret = GameTextForAll(c_str_text, time, style);
	return ret;
}

static jboolean Java_GameTextForPlayer(JNIEnv* env, jobject obj, jint playerid, jstring text, jint time, jint style) {
	char c_str_text[512 * BYTES_PER_CHARACTER];
	GetJVMString(env, text, c_str_text, sizeof(c_str_text));
	FixTextDrawStringAccents(c_str_text, sizeof(c_str_text));
	bool ret = GameTextForPlayer(playerid, c_str_text, time, style);
	return ret;
}

static jint Java_GetMaxPlayers(JNIEnv* env, jobject obj) {
	jint ret = GetMaxPlayers();
	return ret;
}

static jfloat Java_VectorSize(JNIEnv* env, jobject obj, jfloat x, jfloat y, jfloat z) {
	jfloat ret = VectorSize(x, y, z);
	return ret;
}

static jboolean Java_SetGameModeText(JNIEnv* env, jobject obj, jstring text) {
	char c_str_text[64 * BYTES_PER_CHARACTER];
	GetJVMString(env, text, c_str_text, sizeof(c_str_text));
	bool ret = SetGameModeText(c_str_text);
	return ret;
}

static jboolean Java_SetTeamCount(JNIEnv* env, jobject obj, jint count) {
	bool ret = SetTeamCount(count);
	return ret;
}

static jint Java_AddPlayerClass(JNIEnv* env, jobject obj, jint modelid, jfloat spawn_x, jfloat spawn_y, jfloat spawn_z, jfloat z_angle, jint weapon1, jint weapon1_ammo, jint weapon2, jint weapon2_ammo, jint weapon3, jint weapon3_ammo) {
	jint ret = AddPlayerClass(modelid, spawn_x, spawn_y, spawn_z, z_angle, weapon1, weapon1_ammo, weapon2, weapon2_ammo, weapon3, weapon3_ammo);
	return ret;
}

static jint Java_AddPlayerClassEx(JNIEnv* env, jobject obj, jint teamid, jint modelid, jfloat spawn_x, jfloat spawn_y, jfloat spawn_z, jfloat z_angle, jint weapon1, jint weapon1_ammo, jint weapon2, jint weapon2_ammo, jint weapon3, jint weapon3_ammo) {
	jint ret = AddPlayerClassEx(teamid, modelid, spawn_x, spawn_y, spawn_z, z_angle, weapon1, weapon1_ammo, weapon2, weapon2_ammo, weapon3, weapon3_ammo);
	return ret;
}

static jint Java_AddStaticVehicle(JNIEnv* env, jobject obj, jint modelid, jfloat spawn_x, jfloat spawn_y, jfloat spawn_z, jfloat z_angle, jint color1, jint color2) {
	jint ret = AddStaticVehicle(modelid, spawn_x, spawn_y, spawn_z, z_angle, color1, color2);
	return ret;
}

static jint Java_AddStaticVehicleEx(JNIEnv* env, jobject obj, jint modelid, jfloat spawn_x, jfloat spawn_y, jfloat spawn_z, jfloat z_angle, jint color1, jint color2, jint respawn_delay, jboolean addsiren) {
	jint ret = AddStaticVehicleEx(modelid, spawn_x, spawn_y, spawn_z, z_angle, color1, color2, respawn_delay, addsiren);
	return ret;
}

static jint Java_AddStaticPickup(JNIEnv* env, jobject obj, jint model, jint type, jfloat x, jfloat y, jfloat z, jint virtualworld = 0) {
	jint ret = AddStaticPickup(model, type, x, y, z, virtualworld);
	return ret;
}

static jint Java_CreatePickup(JNIEnv* env, jobject obj, jint model, jint type, jfloat x, jfloat y, jfloat z, jint virtualworld = 0) {
	jint ret = CreatePickup(model, type, x, y, z, virtualworld);
	return ret;
}

static jboolean Java_DestroyPickup(JNIEnv* env, jobject obj, jint pickup) {
	bool ret = DestroyPickup(pickup);
	return ret;
}

static jboolean Java_ShowNameTags(JNIEnv* env, jobject obj, jboolean show) {
	bool ret = ShowNameTags(show);
	return ret;
}

static jboolean Java_ShowPlayerMarkers(JNIEnv* env, jobject obj, jint mode) {
	bool ret = ShowPlayerMarkers(mode);
	return ret;
}

static jboolean Java_GameModeExit(JNIEnv* env, jobject obj) {
	bool ret = GameModeExit();
	return ret;
}

static jboolean Java_SetWorldTime(JNIEnv* env, jobject obj, jint hour) {
	bool ret = SetWorldTime(hour);
	return ret;
}

static jboolean Java_EnableTirePopping(JNIEnv* env, jobject obj, jboolean enable) {
	bool ret = EnableTirePopping(enable);
	return ret;
}

static jboolean Java_EnableVehicleFriendlyFire(JNIEnv* env, jobject obj) {
	bool ret = EnableVehicleFriendlyFire();
	return ret;
}

static jboolean Java_AllowInteriorWeapons(JNIEnv* env, jobject obj, jboolean allow) {
	bool ret = AllowInteriorWeapons(allow);
	return ret;
}

static jboolean Java_SetWeather(JNIEnv* env, jobject obj, jint weatherid) {
	bool ret = SetWeather(weatherid);
	return ret;
}

static jboolean Java_SetGravity(JNIEnv* env, jobject obj, jfloat gravity) {
	bool ret = SetGravity(gravity);
	return ret;
}

static jboolean Java_AllowAdminTeleport(JNIEnv* env, jobject obj, jboolean allow) {
	bool ret = AllowAdminTeleport(allow);
	return ret;
}

static jboolean Java_SetDeathDropAmount(JNIEnv* env, jobject obj, jint amount) {
	bool ret = SetDeathDropAmount(amount);
	return ret;
}

static jboolean Java_CreateExplosion(JNIEnv* env, jobject obj, jfloat x, jfloat y, jfloat z, jint type, jfloat radius) {
	bool ret = CreateExplosion(x, y, z, type, radius);
	return ret;
}

static jboolean Java_EnableZoneNames(JNIEnv* env, jobject obj, jboolean enable) {
	bool ret = EnableZoneNames(enable);
	return ret;
}

static jboolean Java_UsePlayerPedAnims(JNIEnv* env, jobject obj) {
	bool ret = UsePlayerPedAnims();
	return ret;
}

static jboolean Java_DisableInteriorEnterExits(JNIEnv* env, jobject obj) {
	bool ret = DisableInteriorEnterExits();
	return ret;
}

static jboolean Java_SetNameTagDrawDistance(JNIEnv* env, jobject obj, jfloat distance) {
	bool ret = SetNameTagDrawDistance(distance);
	return ret;
}

static jboolean Java_DisableNameTagLOS(JNIEnv* env, jobject obj) {
	bool ret = DisableNameTagLOS();
	return ret;
}

static jboolean Java_LimitGlobalChatRadius(JNIEnv* env, jobject obj, jfloat chat_radius) {
	bool ret = LimitGlobalChatRadius(chat_radius);
	return ret;
}

static jboolean Java_LimitPlayerMarkerRadius(JNIEnv* env, jobject obj, jfloat marker_radius) {
	bool ret = LimitPlayerMarkerRadius(marker_radius);
	return ret;
}

static jboolean Java_ConnectNPC(JNIEnv* env, jobject obj, jstring name, jstring script) {
    char c_str_name[24 * BYTES_PER_CHARACTER];
    GetJVMString(env, name, c_str_name, sizeof(c_str_name));
    char c_str_script[64 * BYTES_PER_CHARACTER];
    GetJVMString(env, script, c_str_script, sizeof(c_str_script));
	bool ret = ConnectNPC(c_str_name, c_str_script);
	return ret;
}

static jboolean Java_IsPlayerNPC(JNIEnv* env, jobject obj, jint playerid) {
	bool ret = IsPlayerNPC(playerid);
	return ret;
}

static jboolean Java_IsPlayerAdmin(JNIEnv* env, jobject obj, jint playerid) {
	bool ret = IsPlayerAdmin(playerid);
	return ret;
}

static jboolean Java_Kick(JNIEnv* env, jobject obj, jint playerid) {
	bool ret = Kick(playerid);
	return ret;
}

static jboolean Java_Ban(JNIEnv* env, jobject obj, jint playerid) {
	bool ret = Ban(playerid);
	return ret;
}

static jboolean Java_BanEx(JNIEnv* env, jobject obj, jint playerid, jstring reason) {
	char c_str_reason[64 * BYTES_PER_CHARACTER];
	GetJVMString(env, reason, c_str_reason, sizeof(c_str_reason));
	bool ret = BanEx(playerid, c_str_reason);
	return ret;
}

static jboolean Java_SendRconCommand(JNIEnv* env, jobject obj, jstring command) {
	char c_str_command[512];
	GetJVMString(env, command, c_str_command, sizeof(c_str_command));
	bool ret = SendRconCommand(c_str_command);
	return ret;
}

static jint Java_GetServerVarAsInt(JNIEnv* env, jobject obj, jstring varname) {
    char c_str_varname[64 * BYTES_PER_CHARACTER];
    GetJVMString(env, varname, c_str_varname, sizeof(c_str_varname));
	jint ret = GetServerVarAsInt(c_str_varname);
	return ret;
}

static jboolean Java_GetServerVarAsBool(JNIEnv* env, jobject obj, jstring varname) {
    char c_str_varname[64 * BYTES_PER_CHARACTER];
    GetJVMString(env, varname, c_str_varname, sizeof(c_str_varname));
	bool ret = GetServerVarAsBool(c_str_varname);
	return ret;
}

static jboolean Java_BlockIpAddress(JNIEnv* env, jobject obj, jstring ip_address, jint timems) {
    char c_str_ip_address[32 * BYTES_PER_CHARACTER];
    GetJVMString(env, ip_address, c_str_ip_address, sizeof(c_str_ip_address));
	bool ret = BlockIpAddress(c_str_ip_address, timems);
	return ret;
}

static jboolean Java_UnBlockIpAddress(JNIEnv* env, jobject obj, jstring ip_address) {
    char c_str_ip_address[32 * BYTES_PER_CHARACTER];
    GetJVMString(env, ip_address, c_str_ip_address, sizeof(c_str_ip_address));
	bool ret = UnBlockIpAddress(c_str_ip_address);
	return ret;
}

static jint Java_GetServerTickRate(JNIEnv* env, jobject obj) {
	jint ret = GetServerTickRate();
	return ret;
}

static jint Java_NetStats_GetConnectedTime(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = NetStats_GetConnectedTime(playerid);
	return ret;
}

static jint Java_NetStats_MessagesReceived(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = NetStats_MessagesReceived(playerid);
	return ret;
}

static jint Java_NetStats_BytesReceived(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = NetStats_BytesReceived(playerid);
	return ret;
}

static jint Java_NetStats_MessagesSent(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = NetStats_MessagesSent(playerid);
	return ret;
}

static jint Java_NetStats_BytesSent(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = NetStats_BytesSent(playerid);
	return ret;
}

static jint Java_NetStats_MessagesRecvPerSecond(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = NetStats_MessagesRecvPerSecond(playerid);
	return ret;
}

static jfloat Java_NetStats_PacketLossPercent(JNIEnv* env, jobject obj, jint playerid) {
	jfloat ret = NetStats_PacketLossPercent(playerid);
	return ret;
}

static jint Java_NetStats_ConnectionStatus(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = NetStats_ConnectionStatus(playerid);
	return ret;
}

static jint Java_CreateMenu(JNIEnv* env, jobject obj, jstring title, jint columns, jfloat x, jfloat y, jfloat col1width, jfloat col2width = 0.0) {
	char c_str_title[32 * BYTES_PER_CHARACTER];
	GetJVMString(env, title, c_str_title, sizeof(c_str_title));
	jint ret = CreateMenu(c_str_title, columns, x, y, col1width, col2width);
	return ret;
}

static jboolean Java_DestroyMenu(JNIEnv* env, jobject obj, jint menuid) {
	bool ret = DestroyMenu(menuid);
	return ret;
}

static jint Java_AddMenuItem(JNIEnv* env, jobject obj, jint menuid, jint column, jstring menutext) {
	char c_str_menutext[30 * BYTES_PER_CHARACTER];
	GetJVMString(env, menutext, c_str_menutext, sizeof(c_str_menutext));
	jint ret = AddMenuItem(menuid, column, c_str_menutext);
	return ret;
}

static jboolean Java_SetMenuColumnHeader(JNIEnv* env, jobject obj, jint menuid, jint column, jstring columnheader) {
	char c_str_columnheader[32 * BYTES_PER_CHARACTER];
	GetJVMString(env, columnheader, c_str_columnheader, sizeof(c_str_columnheader));
	bool ret = SetMenuColumnHeader(menuid, column, c_str_columnheader);
	return ret;
}

static jboolean Java_ShowMenuForPlayer(JNIEnv* env, jobject obj, jint menuid, jint playerid) {
	bool ret = ShowMenuForPlayer(menuid, playerid);
	return ret;
}

static jboolean Java_HideMenuForPlayer(JNIEnv* env, jobject obj, jint menuid, jint playerid) {
	bool ret = HideMenuForPlayer(menuid, playerid);
	return ret;
}

static jboolean Java_IsValidMenu(JNIEnv* env, jobject obj, jint menuid) {
	bool ret = IsValidMenu(menuid);
	return ret;
}

static jboolean Java_DisableMenu(JNIEnv* env, jobject obj, jint menuid) {
	bool ret = DisableMenu(menuid);
	return ret;
}

static jboolean Java_DisableMenuRow(JNIEnv* env, jobject obj, jint menuid, jint row) {
	bool ret = DisableMenuRow(menuid, row);
	return ret;
}

static jint Java_GetPlayerMenu(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = GetPlayerMenu(playerid);
	return ret;
}

static jint Java_TextDrawCreate(JNIEnv* env, jobject obj, jfloat x, jfloat y, jstring text) {
	char c_str_text[1024 * BYTES_PER_CHARACTER];
	GetJVMString(env, text, c_str_text, sizeof(c_str_text));
	FixTextDrawStringAccents(c_str_text, sizeof(c_str_text));
	jint ret = TextDrawCreate(x, y, c_str_text);
	return ret;
}

static jboolean Java_TextDrawDestroy(JNIEnv* env, jobject obj, jint text) {
	bool ret = TextDrawDestroy(text);
	return ret;
}

static jboolean Java_TextDrawLetterSize(JNIEnv* env, jobject obj, jint text, jfloat x, jfloat y) {
	bool ret = TextDrawLetterSize(text, x, y);
	return ret;
}

static jboolean Java_TextDrawTextSize(JNIEnv* env, jobject obj, jint text, jfloat x, jfloat y) {
	bool ret = TextDrawTextSize(text, x, y);
	return ret;
}

static jboolean Java_TextDrawAlignment(JNIEnv* env, jobject obj, jint text, jint alignment) {
	bool ret = TextDrawAlignment(text, alignment);
	return ret;
}

static jboolean Java_TextDrawColor(JNIEnv* env, jobject obj, jint text, jint color) {
	bool ret = TextDrawColor(text, color);
	return ret;
}

static jboolean Java_TextDrawUseBox(JNIEnv* env, jobject obj, jint text, jboolean use) {
	bool ret = TextDrawUseBox(text, use);
	return ret;
}

static jboolean Java_TextDrawBoxColor(JNIEnv* env, jobject obj, jint text, jint color) {
	bool ret = TextDrawBoxColor(text, color);
	return ret;
}

static jboolean Java_TextDrawSetShadow(JNIEnv* env, jobject obj, jint text, jint size) {
	bool ret = TextDrawSetShadow(text, size);
	return ret;
}

static jboolean Java_TextDrawSetOutline(JNIEnv* env, jobject obj, jint text, jint size) {
	bool ret = TextDrawSetOutline(text, size);
	return ret;
}

static jboolean Java_TextDrawBackgroundColor(JNIEnv* env, jobject obj, jint text, jint color) {
	bool ret = TextDrawBackgroundColor(text, color);
	return ret;
}

static jboolean Java_TextDrawFont(JNIEnv* env, jobject obj, jint text, jint font) {
	bool ret = TextDrawFont(text, font);
	return ret;
}

static jboolean Java_TextDrawSetProportional(JNIEnv* env, jobject obj, jint text, jboolean set) {
	bool ret = TextDrawSetProportional(text, set);
	return ret;
}

static jboolean Java_TextDrawSetSelectable(JNIEnv* env, jobject obj, jint text, jboolean set) {
	bool ret = TextDrawSetSelectable(text, set);
	return ret;
}

static jboolean Java_TextDrawShowForPlayer(JNIEnv* env, jobject obj, jint playerid, jint text) {
	bool ret = TextDrawShowForPlayer(playerid, text);
	return ret;
}

static jboolean Java_TextDrawHideForPlayer(JNIEnv* env, jobject obj, jint playerid, jint text) {
	bool ret = TextDrawHideForPlayer(playerid, text);
	return ret;
}

static jboolean Java_TextDrawShowForAll(JNIEnv* env, jobject obj, jint text) {
	bool ret = TextDrawShowForAll(text);
	return ret;
}

static jboolean Java_TextDrawHideForAll(JNIEnv* env, jobject obj, jint text) {
	bool ret = TextDrawHideForAll(text);
	return ret;
}

static jboolean Java_TextDrawSetString(JNIEnv* env, jobject obj, jint text, jstring string) {
	char c_str_string[1024 * BYTES_PER_CHARACTER];
	GetJVMString(env, string, c_str_string, sizeof(c_str_string));
	FixTextDrawStringAccents(c_str_string, sizeof(c_str_string));
	bool ret = TextDrawSetString(text, c_str_string);
	return ret;
}

static jboolean Java_TextDrawSetPreviewModel(JNIEnv* env, jobject obj, jint text, jint modelindex) {
	bool ret = TextDrawSetPreviewModel(text, modelindex);
	return ret;
}

static jboolean Java_TextDrawSetPreviewRot(JNIEnv* env, jobject obj, jint text, jfloat fRotX, jfloat fRotY, jfloat fRotZ, jfloat fZoom = 1.0) {
	bool ret = TextDrawSetPreviewRot(text, fRotX, fRotY, fRotZ, fZoom);
	return ret;
}

static jboolean Java_TextDrawSetPreviewVehCol(JNIEnv* env, jobject obj, jint text, jint color1, jint color2) {
	bool ret = TextDrawSetPreviewVehCol(text, color1, color2);
	return ret;
}

static jboolean Java_SelectTextDraw(JNIEnv* env, jobject obj, jint playerid, jint hovercolor) {
	bool ret = SelectTextDraw(playerid, hovercolor);
	return ret;
}

static jboolean Java_CancelSelectTextDraw(JNIEnv* env, jobject obj, jint playerid) {
	bool ret = CancelSelectTextDraw(playerid);
	return ret;
}

static jint Java_GangZoneCreate(JNIEnv* env, jobject obj, jfloat minx, jfloat miny, jfloat maxx, jfloat maxy) {
	jint ret = GangZoneCreate(minx, miny, maxx, maxy);
	return ret;
}

static jboolean Java_GangZoneDestroy(JNIEnv* env, jobject obj, jint zone) {
	bool ret = GangZoneDestroy(zone);
	return ret;
}

static jboolean Java_GangZoneShowForPlayer(JNIEnv* env, jobject obj, jint playerid, jint zone, jint color) {
	bool ret = GangZoneShowForPlayer(playerid, zone, color);
	return ret;
}

static jboolean Java_GangZoneShowForAll(JNIEnv* env, jobject obj, jint zone, jint color) {
	bool ret = GangZoneShowForAll(zone, color);
	return ret;
}

static jboolean Java_GangZoneHideForPlayer(JNIEnv* env, jobject obj, jint playerid, jint zone) {
	bool ret = GangZoneHideForPlayer(playerid, zone);
	return ret;
}

static jboolean Java_GangZoneHideForAll(JNIEnv* env, jobject obj, jint zone) {
	bool ret = GangZoneHideForAll(zone);
	return ret;
}

static jboolean Java_GangZoneFlashForPlayer(JNIEnv* env, jobject obj, jint playerid, jint zone, jint flashcolor) {
	bool ret = GangZoneFlashForPlayer(playerid, zone, flashcolor);
	return ret;
}

static jboolean Java_GangZoneFlashForAll(JNIEnv* env, jobject obj, jint zone, jint flashcolor) {
	bool ret = GangZoneFlashForAll(zone, flashcolor);
	return ret;
}

static jboolean Java_GangZoneStopFlashForPlayer(JNIEnv* env, jobject obj, jint playerid, jint zone) {
	bool ret = GangZoneStopFlashForPlayer(playerid, zone);
	return ret;
}

static jboolean Java_GangZoneStopFlashForAll(JNIEnv* env, jobject obj, jint zone) {
	bool ret = GangZoneStopFlashForAll(zone);
	return ret;
}

static jint Java_Create3DTextLabel(JNIEnv* env, jobject obj, jstring text, jint color, jfloat x, jfloat y, jfloat z, jfloat DrawDistance, jint virtualworld, jboolean testLOS = false) {
	char c_str_text[1024 * BYTES_PER_CHARACTER];
	GetJVMString(env, text, c_str_text, sizeof(c_str_text));
	jint ret = Create3DTextLabel(c_str_text, color, x, y, z, DrawDistance, virtualworld, testLOS);
	return ret;
}

static jboolean Java_Delete3DTextLabel(JNIEnv* env, jobject obj, jint id) {
	bool ret = Delete3DTextLabel(id);
	return ret;
}

static jboolean Java_Attach3DTextLabelToPlayer(JNIEnv* env, jobject obj, jint id, jint playerid, jfloat OffsetX, jfloat OffsetY, jfloat OffsetZ) {
	bool ret = Attach3DTextLabelToPlayer(id, playerid, OffsetX, OffsetY, OffsetZ);
	return ret;
}

static jboolean Java_Attach3DTextLabelToVehicle(JNIEnv* env, jobject obj, jint id, jint vehicleid, jfloat OffsetX, jfloat OffsetY, jfloat OffsetZ) {
	bool ret = Attach3DTextLabelToVehicle(id, vehicleid, OffsetX, OffsetY, OffsetZ);
	return ret;
}

static jboolean Java_Update3DTextLabelText(JNIEnv* env, jobject obj, jint id, jint color, jstring text) {
	char c_str_text[1024 * BYTES_PER_CHARACTER];
	GetJVMString(env, text, c_str_text, sizeof(c_str_text));
	bool ret = Update3DTextLabelText(id, color, c_str_text);
	return ret;
}

static jint Java_CreatePlayer3DTextLabel(JNIEnv* env, jobject obj, jint playerid, jstring text, jint color, jfloat x, jfloat y, jfloat z, jfloat DrawDistance, jint attachedplayer = INVALID_PLAYER_ID, jint attachedvehicle = INVALID_VEHICLE_ID, jboolean testLOS = false) {
	char c_str_text[1024 * BYTES_PER_CHARACTER];
	GetJVMString(env, text, c_str_text, sizeof(c_str_text));
	jint ret = CreatePlayer3DTextLabel(playerid, c_str_text, color, x, y, z, DrawDistance, attachedplayer, attachedvehicle, testLOS);
	return ret;
}

static jboolean Java_DeletePlayer3DTextLabel(JNIEnv* env, jobject obj, jint playerid, jint id) {
	bool ret = DeletePlayer3DTextLabel(playerid, id);
	return ret;
}

static jboolean Java_UpdatePlayer3DTextLabelText(JNIEnv* env, jobject obj, jint playerid, jint id, jint color, jstring text) {
	char c_str_text[1024 * BYTES_PER_CHARACTER];
	GetJVMString(env, text, c_str_text, sizeof(c_str_text));
	bool ret = UpdatePlayer3DTextLabelText(playerid, id, color, c_str_text);
	return ret;
}

static jboolean Java_ShowPlayerDialog(JNIEnv* env, jobject obj, jint playerid, jint dialogid, jint style, jstring caption, jstring info, jstring button1, jstring button2) {
	char c_str_caption[64 * BYTES_PER_CHARACTER];
	char c_str_info[4096 * BYTES_PER_CHARACTER];
	char c_str_button1[32 * BYTES_PER_CHARACTER];
	char c_str_button2[32 * BYTES_PER_CHARACTER];
	GetJVMString(env, caption, c_str_caption, sizeof(c_str_caption));
	GetJVMString(env, info, c_str_info, sizeof(c_str_info));
	GetJVMString(env, button1, c_str_button1, sizeof(c_str_button1));
	GetJVMString(env, button2, c_str_button2, sizeof(c_str_button2));
	bool ret = ShowPlayerDialog(playerid, dialogid, style, c_str_caption, c_str_info, c_str_button1, c_str_button2);
	return ret;
}

static jboolean Java_KillTimer(JNIEnv* env, jobject obj, jint timerid) {
	bool ret = KillTimer(timerid);
	return ret;
}

static jstring Java_GetWeaponName(JNIEnv* env, jobject obj, jint weaponid) {
	char name[34];
	GetWeaponName(weaponid, name, 34);
    return env->NewStringUTF(name);
}

static jstring Java_GetServerVarAsString(JNIEnv* env, jobject obj, jstring varname) {
	char value[256];
    char c_str_varname[64 * BYTES_PER_CHARACTER];
    GetJVMString(env, varname, c_str_varname, sizeof(c_str_varname));
	GetServerVarAsString(c_str_varname, value, 256);
	return env->NewStringUTF(value);
}

static jstring Java_GetPlayerNetworkStats(JNIEnv* env, jobject obj, jint playerid) {
	char retstr[1024];
	GetPlayerNetworkStats(playerid, retstr, 1024);
	return env->NewStringUTF(retstr);
}

static jstring Java_GetNetworkStats(JNIEnv* env, jobject obj) {
	char retstr[1024];
	GetNetworkStats(retstr, 1024);
	return env->NewStringUTF(retstr);
}

static jstring Java_GetPlayerVersion(JNIEnv* env, jobject obj, jint playerid) {
	char version[64];
	GetPlayerVersion(playerid, version, 64);
	return env->NewStringUTF(version);
}

static jstring Java_NetStats_GetIpPort(JNIEnv* env, jobject obj, jint playerid) {
	char ip_port[64];
	NetStats_GetIpPort(playerid, ip_port, 64);
	return env->NewStringUTF(ip_port);
}

static jstring Java_gpci(JNIEnv* env, jobject obj, jint playerid) {
	char buffer[256];
	gpci(playerid, buffer, 256);
	return env->NewStringUTF(buffer);
}

static jint Java_CreateActor(JNIEnv* env, jobject obj, jint modelid, jfloat x, jfloat y, jfloat z, jfloat rotation) {
	jint ret = CreateActor(modelid, x, y, z, rotation);
	return ret;
}

static jboolean Java_DestroyActor(JNIEnv* env, jobject obj, jint actorid) {
	jboolean ret = DestroyActor(actorid);
	return ret;
}

static jboolean Java_IsActorStreamedIn(JNIEnv* env, jobject obj, jint actorid, jint forplayerid) {
	jboolean ret = IsActorStreamedIn(actorid, forplayerid);
	return ret;
}

static jboolean Java_SetActorVirtualWorld(JNIEnv* env, jobject obj, jint actorid, jint vworld) {
	jboolean ret = SetActorVirtualWorld(actorid, vworld);
	return ret;
}

static jint Java_GetActorVirtualWorld(JNIEnv* env, jobject obj, jint actorid) {
	jint ret = GetActorVirtualWorld(actorid);
	return ret;
}

static jboolean Java_ApplyActorAnimation(JNIEnv* env, jobject obj, jint actorid, jstring animlib, jstring animname, jfloat fDelta, jboolean loop, jboolean lockx, jboolean locky, jboolean freeze, jint time) {
	const char* c_str_animlib = env->GetStringUTFChars(animlib, NULL);
	const char* c_str_animname = env->GetStringUTFChars(animname, NULL);
	jboolean ret = ApplyActorAnimation(actorid, c_str_animlib, c_str_animname, fDelta, loop, lockx, locky, freeze, time);
	env->ReleaseStringUTFChars(animlib, c_str_animlib);
	env->ReleaseStringUTFChars(animname, c_str_animname);
	return ret;
}

static jboolean Java_ClearActorAnimations(JNIEnv* env, jobject obj, jint actorid) {
	jboolean ret = ClearActorAnimations(actorid);
	return ret;
}

static jboolean Java_SetActorPos(JNIEnv* env, jobject obj, jint actorid, jfloat x, jfloat y, jfloat z) {
	jboolean ret = SetActorPos(actorid, x, y, z);
	return ret;
}

static jboolean Java_SetActorFacingAngle(JNIEnv* env, jobject obj, jint actorid, jfloat angle) {
	jboolean ret = SetActorFacingAngle(actorid, angle);
	return ret;
}

static jboolean Java_SetActorHealth(JNIEnv* env, jobject obj, jint actorid, jfloat health) {
	jboolean ret = SetActorHealth(actorid, health);
	return ret;
}

static jboolean Java_SetActorInvulnerable(JNIEnv* env, jobject obj, jint actorid, jboolean invulnerable = true) {
	jboolean ret = SetActorInvulnerable(actorid, invulnerable);
	return ret;
}

static jboolean Java_IsActorInvulnerable(JNIEnv* env, jobject obj, jint actorid) {
	jboolean ret = IsActorInvulnerable(actorid);
	return ret;
}

static jboolean Java_IsValidActor(JNIEnv* env, jobject obj, jint actorid) {
	jboolean ret = IsValidActor(actorid);
	return ret;
}

static jfloatArray Java_GetActorPos(JNIEnv* env, jobject obj, int actorid) {
	jfloatArray result;
	result = env->NewFloatArray(3);
	if (result == NULL) {
		return NULL;
	}
	float x, y, z;
	GetActorPos(actorid, &x, &y, &z);
	jfloat pos[3] = { x, y, z };
	env->SetFloatArrayRegion(result, 0, 3, pos);
	return result;
}

static jfloat Java_GetActorFacingAngle(JNIEnv* env, jobject obj, int actorid) {
	float angle;
	GetActorFacingAngle(actorid, &angle);
	return angle;
}

static jfloat Java_GetActorHealth(JNIEnv* env, jobject obj, int actorid) {
	float health;
	GetActorHealth(actorid, &health);
	return health;
}

static jboolean Java_DisableRemoteVehicleCollisions(JNIEnv* env, jobject obj, jint playerid, jboolean disable) {
	jboolean ret = DisableRemoteVehicleCollisions(playerid, disable);
	return ret;
}

static jboolean Java_EnablePlayerCameraTarget(JNIEnv* env, jobject obj, jint playerid, jboolean enable) {
	jboolean ret = EnablePlayerCameraTarget(playerid, enable);
	return ret;
}

static jint Java_GetPlayerCameraTargetActor(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = GetPlayerCameraTargetActor(playerid);
	return ret;
}

static jint Java_GetPlayerCameraTargetPlayer(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = GetPlayerCameraTargetPlayer(playerid);
	return ret;
}

static jint Java_GetPlayerCameraTargetVehicle(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = GetPlayerCameraTargetVehicle(playerid);
	return ret;
}

static jint Java_GetPlayerCameraTargetObject(JNIEnv* env, jobject obj, jint playerid) {
	jint ret = GetPlayerCameraTargetObject(playerid);
	return ret;
}

static jint Java_GetPlayerPoolSize(JNIEnv* env, jobject obj) {
	jint ret = GetPlayerPoolSize();
	return ret;
}

static jint Java_GetVehiclePoolSize(JNIEnv* env, jobject obj) {
	jint ret = GetVehiclePoolSize();
	return ret;
}

static jbooleanArray Java_GetVehicleParamsCarDoors(JNIEnv* env, jobject obj, jint vehicleid) {
	jbooleanArray result;
	result = env->NewBooleanArray(4);
	if (result == NULL) {
		return NULL;
	}
	int driver, passenger, backleft, backright;
	GetVehicleParamsCarDoors(vehicleid, &driver, &passenger, &backleft, &backright);
	jboolean args[7] = { driver == 1, passenger == 1, backleft == 1, backright == 1 };
	env->SetBooleanArrayRegion(result, 0, 4, args);
	return result;
}

static jbooleanArray Java_GetVehicleParamsCarWindows(JNIEnv* env, jobject obj, jint vehicleid) {
	jbooleanArray result;
	result = env->NewBooleanArray(4);
	if (result == NULL) {
		return NULL;
	}
	int driver, passenger, backleft, backright;
	GetVehicleParamsCarWindows(vehicleid, &driver, &passenger, &backleft, &backright);
	jboolean args[7] = { driver == 1, passenger == 1, backleft == 1, backright == 1 };
	env->SetBooleanArrayRegion(result, 0, 4, args);
	return result;
}

static jboolean Java_SetVehicleParamsCarDoors(JNIEnv* env, jobject obj, jint vehicleid, jboolean driver, jboolean passenger, jboolean backleft, jboolean backright) {
	jboolean ret = SetVehicleParamsCarDoors(vehicleid, driver, passenger, backleft, backright);
	return ret;
}

static jboolean Java_SetVehicleParamsCarWindows(JNIEnv* env, jobject obj, jint vehicleid, jboolean driver, jboolean passenger, jboolean backleft, jboolean backright) {
	jboolean ret = SetVehicleParamsCarWindows(vehicleid, driver, passenger, backleft, backright);
	return ret;
}

static jboolean Java_SetObjectNoCameraCol(JNIEnv* env, jobject obj, jint objectid) {
	jboolean ret = SetObjectNoCameraCol(objectid);
	return ret;
}

static jboolean Java_SetObjectsDefaultCameraCol(JNIEnv* env, jobject obj, jboolean disable) {
	jboolean ret = SetObjectsDefaultCameraCol(disable);
	return ret;
}

static jboolean Java_SetPlayerObjectNoCameraCol(JNIEnv* env, jobject obj, jint playerid, jint objectid) {
	jboolean ret = SetPlayerObjectNoCameraCol(playerid, objectid);
	return ret;
}

static jboolean Java_SomeVeryLargeNameMethodOne(JNIEnv* env, jobject obj) {
	return true;
}

static jboolean Java_SomeVeryLargeNameMethodTwo(JNIEnv* env, jobject obj) {
	return true;
}

static JNINativeMethod methods[] = {
	{ "crash", "()Z", (void *)Java_crash }, /* METHOD FOR CRASH AHAHAHAHA AHAHHAHAHAHAHAHAHAHHAHAH*/
	{ "logprintf", "(Ljava/lang/String;)Z", (void *)Java_logprintf },
	{ "SomeVeryLargeNameMethodOne", "()Z", (void *)Java_SomeVeryLargeNameMethodOne },
	{ "SetSpawnInfo", "(IIIFFFFIIIIII)Z", (void *)Java_SetSpawnInfo },
	{ "SpawnPlayer", "(I)Z", (void *)Java_SpawnPlayer },
	{ "SetPlayerPos", "(IFFF)Z", (void *)Java_SetPlayerPos },
	{ "SetPlayerPosFindZ", "(IFFF)Z", (void *)Java_SetPlayerPosFindZ },
	{ "SetPlayerFacingAngle", "(IF)Z", (void *)Java_SetPlayerFacingAngle },
	{ "IsPlayerInRangeOfPoint", "(IFFFF)Z", (void *)Java_IsPlayerInRangeOfPoint },
	{ "GetPlayerDistanceFromPoint", "(IFFF)F", (void *)Java_GetPlayerDistanceFromPoint },
	{ "IsPlayerStreamedIn", "(II)Z", (void *)Java_IsPlayerStreamedIn },
	{ "SetPlayerInterior", "(II)Z", (void *)Java_SetPlayerInterior },
	{ "GetPlayerInterior", "(I)I", (void *)Java_GetPlayerInterior },
	{ "SetPlayerHealth", "(IF)Z", (void *)Java_SetPlayerHealth },
	{ "SetPlayerArmour", "(IF)Z", (void *)Java_SetPlayerArmour },
	{ "SetPlayerAmmo", "(III)Z", (void *)Java_SetPlayerAmmo },
	{ "GetPlayerAmmo", "(I)I", (void *)Java_GetPlayerAmmo },
	{ "GetPlayerWeaponState", "(I)I", (void *)Java_GetPlayerWeaponState },
	{ "GetPlayerTargetPlayer", "(I)I", (void *)Java_GetPlayerTargetPlayer },
	{ "SetPlayerTeam", "(II)Z", (void *)Java_SetPlayerTeam },
	{ "GetPlayerTeam", "(I)I", (void *)Java_GetPlayerTeam },
	{ "SetPlayerScore", "(II)Z", (void *)Java_SetPlayerScore },
	{ "GetPlayerScore", "(I)I", (void *)Java_GetPlayerScore },
	{ "GetPlayerDrunkLevel", "(I)I", (void *)Java_GetPlayerDrunkLevel },
	{ "SetPlayerDrunkLevel", "(II)Z", (void *)Java_SetPlayerDrunkLevel },
	{ "SetPlayerColor", "(II)Z", (void *)Java_SetPlayerColor },
	{ "GetPlayerColor", "(I)I", (void *)Java_GetPlayerColor },
	{ "SetPlayerSkin", "(II)Z", (void *)Java_SetPlayerSkin },
	{ "GetPlayerSkin", "(I)I", (void *)Java_GetPlayerSkin },
	{ "GivePlayerWeapon", "(III)Z", (void *)Java_GivePlayerWeapon },
	{ "ResetPlayerWeapons", "(I)Z", (void *)Java_ResetPlayerWeapons },
	{ "SetPlayerArmedWeapon", "(II)Z", (void *)Java_SetPlayerArmedWeapon },
	{ "GivePlayerMoney", "(II)Z", (void *)Java_GivePlayerMoney },
	{ "ResetPlayerMoney", "(I)Z", (void *)Java_ResetPlayerMoney },
	{ "SetPlayerName", "(ILjava/lang/String;)I", (void *)Java_SetPlayerName },
	{ "GetPlayerMoney", "(I)I", (void *)Java_GetPlayerMoney },
	{ "GetPlayerState", "(I)I", (void *)Java_GetPlayerState },
	{ "GetPlayerPing", "(I)I", (void *)Java_GetPlayerPing },
	{ "GetPlayerWeapon", "(I)I", (void *)Java_GetPlayerWeapon },
	{ "SetPlayerTime", "(III)Z", (void *)Java_SetPlayerTime },
	{ "TogglePlayerClock", "(IZ)Z", (void *)Java_TogglePlayerClock },
	{ "SetPlayerWeather", "(II)Z", (void *)Java_SetPlayerWeather },
	{ "ForceClassSelection", "(I)Z", (void *)Java_ForceClassSelection },
	{ "SetPlayerWantedLevel", "(II)Z", (void *)Java_SetPlayerWantedLevel },
	{ "GetPlayerWantedLevel", "(I)I", (void *)Java_GetPlayerWantedLevel },
	{ "SetPlayerFightingStyle", "(II)Z", (void *)Java_SetPlayerFightingStyle },
	{ "GetPlayerFightingStyle", "(I)I", (void *)Java_GetPlayerFightingStyle },
	{ "SetPlayerVelocity", "(IFFF)Z", (void *)Java_SetPlayerVelocity },
	{ "PlayCrimeReportForPlayer", "(III)Z", (void *)Java_PlayCrimeReportForPlayer },
	{ "PlayAudioStreamForPlayer", "(ILjava/lang/String;FFFFZ)Z", (void *)Java_PlayAudioStreamForPlayer },
	{ "StopAudioStreamForPlayer", "(I)Z", (void *)Java_StopAudioStreamForPlayer },
	{ "SetPlayerShopName", "(ILjava/lang/String;)Z", (void *)Java_SetPlayerShopName },
	{ "SetPlayerSkillLevel", "(III)Z", (void *)Java_SetPlayerSkillLevel },
	{ "GetPlayerSurfingVehicleID", "(I)I", (void *)Java_GetPlayerSurfingVehicleID },
	{ "GetPlayerSurfingObjectID", "(I)I", (void *)Java_GetPlayerSurfingObjectID },
	{ "RemoveBuildingForPlayer", "(IIFFFF)Z", (void *)Java_RemoveBuildingForPlayer },
	{ "SetPlayerAttachedObject", "(IIIIFFFFFFFFFII)Z", (void *)Java_SetPlayerAttachedObject },
	{ "RemovePlayerAttachedObject", "(II)Z", (void *)Java_RemovePlayerAttachedObject },
	{ "IsPlayerAttachedObjectSlotUsed", "(II)Z", (void *)Java_IsPlayerAttachedObjectSlotUsed },
	{ "EditAttachedObject", "(II)Z", (void *)Java_EditAttachedObject },
	{ "CreatePlayerTextDraw", "(IFFLjava/lang/String;)I", (void *)Java_CreatePlayerTextDraw },
	{ "PlayerTextDrawDestroy", "(II)Z", (void *)Java_PlayerTextDrawDestroy },
	{ "PlayerTextDrawLetterSize", "(IIFF)Z", (void *)Java_PlayerTextDrawLetterSize },
	{ "PlayerTextDrawTextSize", "(IIFF)Z", (void *)Java_PlayerTextDrawTextSize },
	{ "PlayerTextDrawAlignment", "(III)Z", (void *)Java_PlayerTextDrawAlignment },
	{ "PlayerTextDrawColor", "(III)Z", (void *)Java_PlayerTextDrawColor },
	{ "PlayerTextDrawUseBox", "(IIZ)Z", (void *)Java_PlayerTextDrawUseBox },
	{ "PlayerTextDrawBoxColor", "(III)Z", (void *)Java_PlayerTextDrawBoxColor },
	{ "PlayerTextDrawSetShadow", "(III)Z", (void *)Java_PlayerTextDrawSetShadow },
	{ "PlayerTextDrawSetOutline", "(III)Z", (void *)Java_PlayerTextDrawSetOutline },
	{ "PlayerTextDrawBackgroundColor", "(III)Z", (void *)Java_PlayerTextDrawBackgroundColor },
	{ "PlayerTextDrawFont", "(III)Z", (void *)Java_PlayerTextDrawFont },
	{ "PlayerTextDrawSetProportional", "(IIZ)Z", (void *)Java_PlayerTextDrawSetProportional },
	{ "PlayerTextDrawSetSelectable", "(IIZ)Z", (void *)Java_PlayerTextDrawSetSelectable },
	{ "PlayerTextDrawShow", "(II)Z", (void *)Java_PlayerTextDrawShow },
	{ "PlayerTextDrawHide", "(II)Z", (void *)Java_PlayerTextDrawHide },
	{ "PlayerTextDrawSetString", "(IILjava/lang/String;)Z", (void *)Java_PlayerTextDrawSetString },
	{ "PlayerTextDrawSetPreviewModel", "(III)Z", (void *)Java_PlayerTextDrawSetPreviewModel },
	{ "PlayerTextDrawSetPreviewRot", "(IIFFFF)Z", (void *)Java_PlayerTextDrawSetPreviewRot },
	{ "PlayerTextDrawSetPreviewVehCol", "(IIII)Z", (void *)Java_PlayerTextDrawSetPreviewVehCol },
	{ "SetPVarInt", "(ILjava/lang/String;I)Z", (void *)Java_SetPVarInt },
	{ "GetPVarInt", "(ILjava/lang/String;)I", (void *)Java_GetPVarInt },
	{ "SetPVarString", "(ILjava/lang/String;Ljava/lang/String;)Z", (void *)Java_SetPVarString },
	{ "SetPVarFloat", "(ILjava/lang/String;F)Z", (void *)Java_SetPVarFloat },
	{ "GetPVarFloat", "(ILjava/lang/String;)F", (void *)Java_GetPVarFloat },
	{ "DeletePVar", "(ILjava/lang/String;)Z", (void *)Java_DeletePVar },
	{ "GetPVarsUpperIndex", "(I)I", (void *)Java_GetPVarsUpperIndex },
	{ "GetPVarType", "(ILjava/lang/String;)I", (void *)Java_GetPVarType },
	{ "SetPlayerChatBubble", "(ILjava/lang/String;IFI)Z", (void *)Java_SetPlayerChatBubble },
	{ "PutPlayerInVehicle", "(III)Z", (void *)Java_PutPlayerInVehicle },
	{ "GetPlayerVehicleID", "(I)I", (void *)Java_GetPlayerVehicleID },
	{ "GetPlayerVehicleSeat", "(I)I", (void *)Java_GetPlayerVehicleSeat },
	{ "RemovePlayerFromVehicle", "(I)Z", (void *)Java_RemovePlayerFromVehicle },
	{ "TogglePlayerControllable", "(IZ)Z", (void *)Java_TogglePlayerControllable },
	{ "PlayerPlaySound", "(IIFFF)Z", (void *)Java_PlayerPlaySound },
	{ "ApplyAnimation", "(ILjava/lang/String;Ljava/lang/String;FZZZZIZ)Z", (void *)Java_ApplyAnimation },
	{ "ClearAnimations", "(IZ)Z", (void *)Java_ClearAnimations },
	{ "GetPlayerAnimationIndex", "(I)I", (void *)Java_GetPlayerAnimationIndex },
	{ "GetPlayerSpecialAction", "(I)I", (void *)Java_GetPlayerSpecialAction },
	{ "SetPlayerSpecialAction", "(II)Z", (void *)Java_SetPlayerSpecialAction },
	{ "SetPlayerCheckpoint", "(IFFFF)Z", (void *)Java_SetPlayerCheckpoint },
	{ "DisablePlayerCheckpoint", "(I)Z", (void *)Java_DisablePlayerCheckpoint },
	{ "SetPlayerRaceCheckpoint", "(IIFFFFFFF)Z", (void *)Java_SetPlayerRaceCheckpoint },
	{ "DisablePlayerRaceCheckpoint", "(I)Z", (void *)Java_DisablePlayerRaceCheckpoint },
	{ "SetPlayerWorldBounds", "(IFFFF)Z", (void *)Java_SetPlayerWorldBounds },
	{ "SetPlayerMarkerForPlayer", "(III)Z", (void *)Java_SetPlayerMarkerForPlayer },
	{ "ShowPlayerNameTagForPlayer", "(IIZ)Z", (void *)Java_ShowPlayerNameTagForPlayer },
	{ "SetPlayerMapIcon", "(IIFFFIII)Z", (void *)Java_SetPlayerMapIcon },
	{ "RemovePlayerMapIcon", "(II)Z", (void *)Java_RemovePlayerMapIcon },
	{ "AllowPlayerTeleport", "(IZ)Z", (void *)Java_AllowPlayerTeleport },
	{ "SetPlayerCameraPos", "(IFFF)Z", (void *)Java_SetPlayerCameraPos },
	{ "SetPlayerCameraLookAt", "(IFFFI)Z", (void *)Java_SetPlayerCameraLookAt },
	{ "SetCameraBehindPlayer", "(I)Z", (void *)Java_SetCameraBehindPlayer },
	{ "GetPlayerCameraMode", "(I)I", (void *)Java_GetPlayerCameraMode },
	{ "GetPlayerCameraAspectRatio", "(I)F", (void *)Java_GetPlayerCameraAspectRatio },
	{ "GetPlayerCameraZoom", "(I)F", (void *)Java_GetPlayerCameraZoom },
	{ "AttachCameraToObject", "(II)Z", (void *)Java_AttachCameraToObject },
	{ "AttachCameraToPlayerObject", "(II)Z", (void *)Java_AttachCameraToPlayerObject },
	{ "InterpolateCameraPos", "(IFFFFFFII)Z", (void *)Java_InterpolateCameraPos },
	{ "InterpolateCameraLookAt", "(IFFFFFFII)Z", (void *)Java_InterpolateCameraLookAt },
	{ "IsPlayerConnected", "(I)Z", (void *)Java_IsPlayerConnected },
	{ "IsPlayerInVehicle", "(II)Z", (void *)Java_IsPlayerInVehicle },
	{ "IsPlayerInAnyVehicle", "(I)Z", (void *)Java_IsPlayerInAnyVehicle },
	{ "IsPlayerInCheckpoint", "(I)Z", (void *)Java_IsPlayerInCheckpoint },
	{ "IsPlayerInRaceCheckpoint", "(I)Z", (void *)Java_IsPlayerInRaceCheckpoint },
	{ "SetPlayerVirtualWorld", "(II)Z", (void *)Java_SetPlayerVirtualWorld },
	{ "GetPlayerVirtualWorld", "(I)I", (void *)Java_GetPlayerVirtualWorld },
	{ "EnableStuntBonusForPlayer", "(IZ)Z", (void *)Java_EnableStuntBonusForPlayer },
	{ "EnableStuntBonusForAll", "(Z)Z", (void *)Java_EnableStuntBonusForAll },
	{ "TogglePlayerSpectating", "(IZ)Z", (void *)Java_TogglePlayerSpectating },
	{ "PlayerSpectatePlayer", "(III)Z", (void *)Java_PlayerSpectatePlayer },
	{ "PlayerSpectateVehicle", "(III)Z", (void *)Java_PlayerSpectateVehicle },
	{ "StartRecordingPlayerData", "(IILjava/lang/String;)Z", (void *)Java_StartRecordingPlayerData },
	{ "StopRecordingPlayerData", "(I)Z", (void *)Java_StopRecordingPlayerData },
	{ "CreateExplosionForPlayer", "(IFFFIF)Z", (void *)Java_CreateExplosionForPlayer },
	{ "GetPlayerPos", "(I)[F", (void *)Java_GetPlayerPos },
	{ "GetPlayerFacingAngle", "(I)F", (void *)Java_GetPlayerFacingAngle },
	{ "GetPlayerHealth", "(I)F", (void *)Java_GetPlayerHealth },
	{ "GetPlayerArmour", "(I)F", (void *)Java_GetPlayerArmour },
	{ "GetPlayerWeaponSlot", "(II)I", (void *)Java_GetPlayerWeaponSlot },
	{ "GetPlayerAmmoSlot", "(II)I", (void *)Java_GetPlayerAmmoSlot },
	{ "GetPlayerIp", "(I)Ljava/lang/String;", (void *)Java_GetPlayerIp },
	{ "GetPlayerKeys", "(I)[I", (void *)Java_GetPlayerKeys },
	{ "GetPlayerName", "(I)Ljava/lang/String;", (void *)Java_GetPlayerName },
	{ "GetPlayerHour", "(I)I", (void *)Java_GetPlayerHour },
	{ "GetPlayerTime", "(I)I", (void *)Java_GetPlayerTime },
	{ "GetPlayerVelocity", "(I)[F", (void *)Java_GetPlayerVelocity },
	{ "GetPlayerLastShotVectors", "(I)[F", (void *)Java_GetPlayerLastShotVectors },
	{ "GetPVarString", "(ILjava/lang/String;)Ljava/lang/String;", (void *)Java_GetPVarString },
	{ "GetPVarNameAtIndex", "(II)Ljava/lang/String;", (void *)Java_GetPVarNameAtIndex },
	{ "GetAnimationLibrary", "(I)Ljava/lang/String;", (void *)Java_GetAnimationLibrary },
	{ "GetAnimationName", "(I)Ljava/lang/String;", (void *)Java_GetAnimationName },
	{ "GetPlayerCameraPos", "(I)[F", (void *)Java_GetPlayerCameraPos },
	{ "GetPlayerCameraFrontVector", "(I)[F", (void *)Java_GetPlayerCameraFrontVector },
	{ "CreateObject", "(IFFFFFFF)I", (void *)Java_CreateObject },
	{ "AttachObjectToVehicle", "(IIFFFFFF)Z", (void *)Java_AttachObjectToVehicle },
	{ "AttachObjectToObject", "(IIFFFFFFZ)Z", (void *)Java_AttachObjectToObject },
	{ "AttachObjectToPlayer", "(IIFFFFFF)Z", (void *)Java_AttachObjectToPlayer },
	{ "SetObjectPos", "(IFFF)Z", (void *)Java_SetObjectPos },
	{ "SetObjectRot", "(IFFF)Z", (void *)Java_SetObjectRot },
	{ "IsValidObject", "(I)Z", (void *)Java_IsValidObject },
	{ "DestroyObject", "(I)Z", (void *)Java_DestroyObject },
	{ "MoveObject", "(IFFFFFFF)I", (void *)Java_MoveObject },
	{ "StopObject", "(I)Z", (void *)Java_StopObject },
	{ "IsObjectMoving", "(I)Z", (void *)Java_IsObjectMoving },
	{ "EditObject", "(II)Z", (void *)Java_EditObject },
	{ "EditPlayerObject", "(II)Z", (void *)Java_EditPlayerObject },
	{ "SelectObject", "(I)Z", (void *)Java_SelectObject },
	{ "CancelEdit", "(I)Z", (void *)Java_CancelEdit },
	{ "CreatePlayerObject", "(IIFFFFFFF)I", (void *)Java_CreatePlayerObject },
	{ "AttachPlayerObjectToPlayer", "(IIIFFFFFF)Z", (void *)Java_AttachPlayerObjectToPlayer },
	{ "AttachPlayerObjectToVehicle", "(IIIFFFFFF)Z", (void *)Java_AttachPlayerObjectToVehicle },
	{ "SetPlayerObjectPos", "(IIFFF)Z", (void *)Java_SetPlayerObjectPos },
	{ "SetPlayerObjectRot", "(IIFFF)Z", (void *)Java_SetPlayerObjectRot },
	{ "IsValidPlayerObject", "(II)Z", (void *)Java_IsValidPlayerObject },
	{ "DestroyPlayerObject", "(II)Z", (void *)Java_DestroyPlayerObject },
	{ "MovePlayerObject", "(IIFFFFFFF)I", (void *)Java_MovePlayerObject },
	{ "StopPlayerObject", "(II)Z", (void *)Java_StopPlayerObject },
	{ "IsPlayerObjectMoving", "(II)Z", (void *)Java_IsPlayerObjectMoving },
	{ "SetObjectMaterial", "(IIILjava/lang/String;Ljava/lang/String;I)Z", (void *)Java_SetObjectMaterial },
	{ "SetPlayerObjectMaterial", "(IIIILjava/lang/String;Ljava/lang/String;I)Z", (void *)Java_SetPlayerObjectMaterial },
	{ "SetObjectMaterialText", "(ILjava/lang/String;IILjava/lang/String;IZIII)Z", (void *)Java_SetObjectMaterialText },
	{ "SetPlayerObjectMaterialText", "(IILjava/lang/String;IILjava/lang/String;IZIII)Z", (void *)Java_SetPlayerObjectMaterialText },
	{ "GetObjectPos", "(I)[F", (void *)Java_GetObjectPos },
	{ "GetObjectRot", "(I)[F", (void *)Java_GetObjectRot },
	{ "GetPlayerObjectPos", "(II)[F", (void *)Java_GetPlayerObjectPos },
	{ "GetPlayerObjectRot", "(II)[F", (void *)Java_GetPlayerObjectRot },
	{ "IsValidVehicle", "(I)Z", (void *)Java_IsValidVehicle },
	{ "GetVehicleDistanceFromPoint", "(IFFF)F", (void *)Java_GetVehicleDistanceFromPoint },
	{ "CreateVehicle", "(IFFFFIIIZ)I", (void *)Java_CreateVehicle },
	{ "DestroyVehicle", "(I)Z", (void *)Java_DestroyVehicle },
	{ "IsVehicleStreamedIn", "(II)Z", (void *)Java_IsVehicleStreamedIn },
	{ "SetVehiclePos", "(IFFF)Z", (void *)Java_SetVehiclePos },
	{ "SetVehicleZAngle", "(IF)Z", (void *)Java_SetVehicleZAngle },
	{ "SetVehicleParamsForPlayer", "(IIZZ)Z", (void *)Java_SetVehicleParamsForPlayer },
	{ "ManualVehicleEngineAndLights", "()Z", (void *)Java_ManualVehicleEngineAndLights },
	{ "SetVehicleParamsEx", "(IZZZZZZZ)Z", (void *)Java_SetVehicleParamsEx },
	{ "SetVehicleToRespawn", "(I)Z", (void *)Java_SetVehicleToRespawn },
	{ "LinkVehicleToInterior", "(II)Z", (void *)Java_LinkVehicleToInterior },
	{ "AddVehicleComponent", "(II)Z", (void *)Java_AddVehicleComponent },
	{ "RemoveVehicleComponent", "(II)Z", (void *)Java_RemoveVehicleComponent },
	{ "ChangeVehicleColor", "(III)Z", (void *)Java_ChangeVehicleColor },
	{ "ChangeVehiclePaintjob", "(II)Z", (void *)Java_ChangeVehiclePaintjob },
	{ "SetVehicleHealth", "(IF)Z", (void *)Java_SetVehicleHealth },
	{ "AttachTrailerToVehicle", "(II)Z", (void *)Java_AttachTrailerToVehicle },
	{ "DetachTrailerFromVehicle", "(I)Z", (void *)Java_DetachTrailerFromVehicle },
	{ "IsTrailerAttachedToVehicle", "(I)Z", (void *)Java_IsTrailerAttachedToVehicle },
	{ "GetVehicleTrailer", "(I)I", (void *)Java_GetVehicleTrailer },
	{ "SetVehicleNumberPlate", "(ILjava/lang/String;)Z", (void *)Java_SetVehicleNumberPlate },
	{ "GetVehicleModel", "(I)I", (void *)Java_GetVehicleModel },
	{ "GetVehicleComponentInSlot", "(II)I", (void *)Java_GetVehicleComponentInSlot },
	{ "GetVehicleComponentType", "(I)I", (void *)Java_GetVehicleComponentType },
	{ "RepairVehicle", "(I)Z", (void *)Java_RepairVehicle },
	{ "SetVehicleVelocity", "(IFFF)Z", (void *)Java_SetVehicleVelocity },
	{ "SetVehicleAngularVelocity", "(IFFF)Z", (void *)Java_SetVehicleAngularVelocity },
	{ "UpdateVehicleDamageStatus", "(IIIII)Z", (void *)Java_UpdateVehicleDamageStatus },
	{ "SetVehicleVirtualWorld", "(II)Z", (void *)Java_SetVehicleVirtualWorld },
	{ "GetVehicleVirtualWorld", "(I)I", (void *)Java_GetVehicleVirtualWorld },
	{ "GetVehiclePos", "(I)[F", (void *)Java_GetVehiclePos },
	{ "GetVehicleZAngle", "(I)F", (void *)Java_GetVehicleZAngle },
	{ "GetVehicleRotationQuat", "(I)[F", (void *)Java_GetVehicleRotationQuat },
	{ "GetVehicleParamsEx", "(I)[I", (void *)Java_GetVehicleParamsEx },
	{ "GetVehicleHealth", "(I)F", (void *)Java_GetVehicleHealth },
	{ "GetVehicleVelocity", "(I)[F", (void *)Java_GetVehicleVelocity },
	{ "GetVehicleDamageStatus", "(I)[I", (void *)Java_GetVehicleDamageStatus },
	{ "GetVehicleModelInfo", "(II)[F", (void *)Java_GetVehicleModelInfo },
	{ "SendClientMessage", "(IILjava/lang/String;)Z", (void *)Java_SendClientMessage },
	{ "SendClientMessageToAll", "(ILjava/lang/String;)Z", (void *)Java_SendClientMessageToAll },
	{ "SendPlayerMessageToPlayer", "(IILjava/lang/String;)Z", (void *)Java_SendPlayerMessageToPlayer },
	{ "SendPlayerMessageToAll", "(ILjava/lang/String;)Z", (void *)Java_SendPlayerMessageToAll },
	{ "SendDeathMessage", "(III)Z", (void *)Java_SendDeathMessage },
	{ "SendDeathMessageToPlayer", "(IIII)Z", (void *)Java_SendDeathMessageToPlayer },
	{ "GameTextForAll", "(Ljava/lang/String;II)Z", (void *)Java_GameTextForAll },
	{ "GameTextForPlayer", "(ILjava/lang/String;II)Z", (void *)Java_GameTextForPlayer },
	{ "GetMaxPlayers", "()I", (void *)Java_GetMaxPlayers },
	{ "VectorSize", "(FFF)F", (void *)Java_VectorSize },
	{ "SetGameModeText", "(Ljava/lang/String;)Z", (void *)Java_SetGameModeText },
	{ "SetTeamCount", "(I)Z", (void *)Java_SetTeamCount },
	{ "AddPlayerClass", "(IFFFFIIIIII)I", (void *)Java_AddPlayerClass },
	{ "AddPlayerClassEx", "(IIFFFFIIIIII)I", (void *)Java_AddPlayerClassEx },
	{ "AddStaticVehicle", "(IFFFFII)I", (void *)Java_AddStaticVehicle },
	{ "AddStaticVehicleEx", "(IFFFFIIIZ)I", (void *)Java_AddStaticVehicleEx },
	{ "AddStaticPickup", "(IIFFFI)I", (void *)Java_AddStaticPickup },
	{ "CreatePickup", "(IIFFFI)I", (void *)Java_CreatePickup },
	{ "DestroyPickup", "(I)Z", (void *)Java_DestroyPickup },
	{ "ShowNameTags", "(Z)Z", (void *)Java_ShowNameTags },
	{ "ShowPlayerMarkers", "(I)Z", (void *)Java_ShowPlayerMarkers },
	{ "GameModeExit", "()Z", (void *)Java_GameModeExit },
	{ "SetWorldTime", "(I)Z", (void *)Java_SetWorldTime },
	{ "EnableTirePopping", "(Z)Z", (void *)Java_EnableTirePopping },
	{ "EnableVehicleFriendlyFire", "()Z", (void *)Java_EnableVehicleFriendlyFire },
	{ "AllowInteriorWeapons", "(Z)Z", (void *)Java_AllowInteriorWeapons },
	{ "SetWeather", "(I)Z", (void *)Java_SetWeather },
	{ "SetGravity", "(F)Z", (void *)Java_SetGravity },
	{ "AllowAdminTeleport", "(Z)Z", (void *)Java_AllowAdminTeleport },
	{ "SetDeathDropAmount", "(I)Z", (void *)Java_SetDeathDropAmount },
	{ "CreateExplosion", "(FFFIF)Z", (void *)Java_CreateExplosion },
	{ "EnableZoneNames", "(Z)Z", (void *)Java_EnableZoneNames },
	{ "UsePlayerPedAnims", "()Z", (void *)Java_UsePlayerPedAnims },
	{ "DisableInteriorEnterExits", "()Z", (void *)Java_DisableInteriorEnterExits },
	{ "SetNameTagDrawDistance", "(F)Z", (void *)Java_SetNameTagDrawDistance },
	{ "DisableNameTagLOS", "()Z", (void *)Java_DisableNameTagLOS },
	{ "LimitGlobalChatRadius", "(F)Z", (void *)Java_LimitGlobalChatRadius },
	{ "LimitPlayerMarkerRadius", "(F)Z", (void *)Java_LimitPlayerMarkerRadius },
	{ "ConnectNPC", "(Ljava/lang/String;Ljava/lang/String;)Z", (void *)Java_ConnectNPC },
	{ "IsPlayerNPC", "(I)Z", (void *)Java_IsPlayerNPC },
	{ "IsPlayerAdmin", "(I)Z", (void *)Java_IsPlayerAdmin },
	{ "Kick", "(I)Z", (void *)Java_Kick },
	{ "Ban", "(I)Z", (void *)Java_Ban },
	{ "BanEx", "(ILjava/lang/String;)Z", (void *)Java_BanEx },
	{ "SendRconCommand", "(Ljava/lang/String;)Z", (void *)Java_SendRconCommand },
	{ "GetServerVarAsInt", "(Ljava/lang/String;)I", (void *)Java_GetServerVarAsInt },
	{ "GetServerVarAsBool", "(Ljava/lang/String;)Z", (void *)Java_GetServerVarAsBool },
	{ "BlockIpAddress", "(Ljava/lang/String;I)Z", (void *)Java_BlockIpAddress },
	{ "UnBlockIpAddress", "(Ljava/lang/String;)Z", (void *)Java_UnBlockIpAddress },
	{ "GetServerTickRate", "()I", (void *)Java_GetServerTickRate },
	{ "NetStats_GetConnectedTime", "(I)I", (void *)Java_NetStats_GetConnectedTime },
	{ "NetStats_MessagesReceived", "(I)I", (void *)Java_NetStats_MessagesReceived },
	{ "NetStats_BytesReceived", "(I)I", (void *)Java_NetStats_BytesReceived },
	{ "NetStats_MessagesSent", "(I)I", (void *)Java_NetStats_MessagesSent },
	{ "NetStats_BytesSent", "(I)I", (void *)Java_NetStats_BytesSent },
	{ "NetStats_MessagesRecvPerSecond", "(I)I", (void *)Java_NetStats_MessagesRecvPerSecond },
	{ "NetStats_PacketLossPercent", "(I)F", (void *)Java_NetStats_PacketLossPercent },
	{ "NetStats_ConnectionStatus", "(I)I", (void *)Java_NetStats_ConnectionStatus },
	{ "CreateMenu", "(Ljava/lang/String;IFFFF)I", (void *)Java_CreateMenu },
	{ "DestroyMenu", "(I)Z", (void *)Java_DestroyMenu },
	{ "AddMenuItem", "(IILjava/lang/String;)I", (void *)Java_AddMenuItem },
	{ "SetMenuColumnHeader", "(IILjava/lang/String;)Z", (void *)Java_SetMenuColumnHeader },
	{ "ShowMenuForPlayer", "(II)Z", (void *)Java_ShowMenuForPlayer },
	{ "HideMenuForPlayer", "(II)Z", (void *)Java_HideMenuForPlayer },
	{ "IsValidMenu", "(I)Z", (void *)Java_IsValidMenu },
	{ "DisableMenu", "(I)Z", (void *)Java_DisableMenu },
	{ "DisableMenuRow", "(II)Z", (void *)Java_DisableMenuRow },
	{ "GetPlayerMenu", "(I)I", (void *)Java_GetPlayerMenu },
	{ "TextDrawCreate", "(FFLjava/lang/String;)I", (void *)Java_TextDrawCreate },
	{ "TextDrawDestroy", "(I)Z", (void *)Java_TextDrawDestroy },
	{ "TextDrawLetterSize", "(IFF)Z", (void *)Java_TextDrawLetterSize },
	{ "TextDrawTextSize", "(IFF)Z", (void *)Java_TextDrawTextSize },
	{ "TextDrawAlignment", "(II)Z", (void *)Java_TextDrawAlignment },
	{ "TextDrawColor", "(II)Z", (void *)Java_TextDrawColor },
	{ "TextDrawUseBox", "(IZ)Z", (void *)Java_TextDrawUseBox },
	{ "TextDrawBoxColor", "(II)Z", (void *)Java_TextDrawBoxColor },
	{ "TextDrawSetShadow", "(II)Z", (void *)Java_TextDrawSetShadow },
	{ "TextDrawSetOutline", "(II)Z", (void *)Java_TextDrawSetOutline },
	{ "TextDrawBackgroundColor", "(II)Z", (void *)Java_TextDrawBackgroundColor },
	{ "TextDrawFont", "(II)Z", (void *)Java_TextDrawFont },
	{ "TextDrawSetProportional", "(IZ)Z", (void *)Java_TextDrawSetProportional },
	{ "TextDrawSetSelectable", "(IZ)Z", (void *)Java_TextDrawSetSelectable },
	{ "TextDrawShowForPlayer", "(II)Z", (void *)Java_TextDrawShowForPlayer },
	{ "TextDrawHideForPlayer", "(II)Z", (void *)Java_TextDrawHideForPlayer },
	{ "TextDrawShowForAll", "(I)Z", (void *)Java_TextDrawShowForAll },
	{ "TextDrawHideForAll", "(I)Z", (void *)Java_TextDrawHideForAll },
	{ "TextDrawSetString", "(ILjava/lang/String;)Z", (void *)Java_TextDrawSetString },
	{ "TextDrawSetPreviewModel", "(II)Z", (void *)Java_TextDrawSetPreviewModel },
	{ "TextDrawSetPreviewRot", "(IFFFF)Z", (void *)Java_TextDrawSetPreviewRot },
	{ "TextDrawSetPreviewVehCol", "(III)Z", (void *)Java_TextDrawSetPreviewVehCol },
	{ "SelectTextDraw", "(II)Z", (void *)Java_SelectTextDraw },
	{ "CancelSelectTextDraw", "(I)Z", (void *)Java_CancelSelectTextDraw },
	{ "GangZoneCreate", "(FFFF)I", (void *)Java_GangZoneCreate },
	{ "GangZoneDestroy", "(I)Z", (void *)Java_GangZoneDestroy },
	{ "GangZoneShowForPlayer", "(III)Z", (void *)Java_GangZoneShowForPlayer },
	{ "GangZoneShowForAll", "(II)Z", (void *)Java_GangZoneShowForAll },
	{ "GangZoneHideForPlayer", "(II)Z", (void *)Java_GangZoneHideForPlayer },
	{ "GangZoneHideForAll", "(I)Z", (void *)Java_GangZoneHideForAll },
	{ "GangZoneFlashForPlayer", "(III)Z", (void *)Java_GangZoneFlashForPlayer },
	{ "GangZoneFlashForAll", "(II)Z", (void *)Java_GangZoneFlashForAll },
	{ "GangZoneStopFlashForPlayer", "(II)Z", (void *)Java_GangZoneStopFlashForPlayer },
	{ "GangZoneStopFlashForAll", "(I)Z", (void *)Java_GangZoneStopFlashForAll },
	{ "Create3DTextLabel", "(Ljava/lang/String;IFFFFIZ)I", (void *)Java_Create3DTextLabel },
	{ "Delete3DTextLabel", "(I)Z", (void *)Java_Delete3DTextLabel },
	{ "Attach3DTextLabelToPlayer", "(IIFFF)Z", (void *)Java_Attach3DTextLabelToPlayer },
	{ "Attach3DTextLabelToVehicle", "(IIFFF)Z", (void *)Java_Attach3DTextLabelToVehicle },
	{ "Update3DTextLabelText", "(IILjava/lang/String;)Z", (void *)Java_Update3DTextLabelText },
	{ "CreatePlayer3DTextLabel", "(ILjava/lang/String;IFFFFIIZ)I", (void *)Java_CreatePlayer3DTextLabel },
	{ "DeletePlayer3DTextLabel", "(II)Z", (void *)Java_DeletePlayer3DTextLabel },
	{ "UpdatePlayer3DTextLabelText", "(IIILjava/lang/String;)Z", (void *)Java_UpdatePlayer3DTextLabelText },
	{ "ShowPlayerDialog", "(IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z", (void *)Java_ShowPlayerDialog },
	{ "KillTimer", "(I)Z", (void *)Java_KillTimer },
	{ "GetWeaponName", "(I)Ljava/lang/String;", (void *)Java_GetWeaponName },
	{ "GetServerVarAsString", "(Ljava/lang/String;)Ljava/lang/String;", (void *)Java_GetServerVarAsString },
	{ "GetPlayerNetworkStats", "(I)Ljava/lang/String;", (void *)Java_GetPlayerNetworkStats },
	{ "GetNetworkStats", "()Ljava/lang/String;", (void *)Java_GetNetworkStats },
	{ "GetPlayerVersion", "(I)Ljava/lang/String;", (void *)Java_GetPlayerVersion },
	{ "NetStats_GetIpPort", "(I)Ljava/lang/String;", (void *)Java_NetStats_GetIpPort },
	{ "gpci", "(I)Ljava/lang/String;", (void *)Java_gpci },

	/* a_actor.inc */
	{ "CreateActor", "(IFFFF)I", (void *)Java_CreateActor },
	{ "DestroyActor", "(I)Z", (void *)Java_DestroyActor },
	{ "IsActorStreamedIn", "(II)Z", (void *)Java_IsActorStreamedIn },
	{ "SetActorVirtualWorld", "(II)Z", (void *)Java_SetActorVirtualWorld },
	{ "GetActorVirtualWorld", "(I)I", (void *)Java_GetActorVirtualWorld },
	{ "ApplyActorAnimation", "(ILjava/lang/String;Ljava/lang/String;FZZZZI)Z", (void *)Java_ApplyActorAnimation },
	{ "ClearActorAnimations", "(I)Z", (void *)Java_ClearActorAnimations },
	{ "SetActorPos", "(IFFF)Z", (void *)Java_SetActorPos },
	{ "SetActorFacingAngle", "(IF)Z", (void *)Java_SetActorFacingAngle },
	{ "SetActorHealth", "(IF)Z", (void *)Java_SetActorHealth },
	{ "SetActorInvulnerable", "(IZ)Z", (void *)Java_SetActorInvulnerable },
	{ "IsActorInvulnerable", "(I)Z", (void *)Java_IsActorInvulnerable },
	{ "IsValidActor", "(I)Z", (void *)Java_IsValidActor },
	{ "GetActorPos", "(I)[F", (void *)Java_GetActorPos },
	{ "GetActorFacingAngle", "(I)F", (void *)Java_GetActorFacingAngle },
	{ "GetActorHealth", "(I)F", (void *)Java_GetActorHealth },

	/* Vehicle collision */
	{ "DisableRemoteVehicleCollisions", "(IZ)Z", (void *)Java_DisableRemoteVehicleCollisions },

	/* Camera target functions */
	{ "EnablePlayerCameraTarget", "(IZ)Z", (void *)Java_EnablePlayerCameraTarget },
	{ "GetPlayerCameraTargetObject", "(I)I", (void *)Java_GetPlayerCameraTargetObject },
	{ "GetPlayerCameraTargetPlayer", "(I)I", (void *)Java_GetPlayerCameraTargetPlayer },
	{ "GetPlayerCameraTargetActor", "(I)I", (void *)Java_GetPlayerCameraTargetActor },
	{ "GetPlayerCameraTargetVehicle", "(I)I", (void *)Java_GetPlayerCameraTargetVehicle },

	/* Pools */
	{ "GetPlayerPoolSize", "()I", (void *)Java_GetPlayerPoolSize },
	{ "GetVehiclePoolSize", "()I", (void *)Java_GetVehiclePoolSize },

	/* Car doors and windows */
	{ "GetVehicleParamsCarDoors", "(I)[Z", (void *)Java_GetVehicleParamsCarDoors },
	{ "GetVehicleParamsCarWindows", "(I)[Z", (void *)Java_GetVehicleParamsCarWindows },
	{ "SetVehicleParamsCarDoors", "(IZZZZ)Z", (void *)Java_SetVehicleParamsCarDoors },
	{ "SetVehicleParamsCarWindows", "(IZZZZ)Z", (void *)Java_SetVehicleParamsCarWindows },

	/* Camera col*/
	{ "SetObjectNoCameraCol", "(I)Z", (void *)Java_SetObjectNoCameraCol },
	{ "SetObjectsDefaultCameraCol", "(Z)Z", (void *)Java_SetObjectsDefaultCameraCol },
	{ "SetPlayerObjectNoCameraCol", "(II)Z", (void *)Java_SetPlayerObjectNoCameraCol },

	{ "SomeVeryLargeNameMethodTwo", "()Z", (void *)Java_SomeVeryLargeNameMethodTwo },

};

namespace JNISampFunctions {
	int RegisterNativeMethods(JNIEnv* env, jclass clazz) {
		static int count = sizeof(methods) / sizeof(methods[0]);
		return env->RegisterNatives(clazz, methods, count);
	}
}
