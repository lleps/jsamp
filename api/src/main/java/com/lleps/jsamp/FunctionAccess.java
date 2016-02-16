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
 * This class is used by all JSAMP abstractions to access samp to native functions.
 *
 * An executor is a class that is responsible of executing native functions. So, when calling
 * a static function on this class, you're calling the executor implementation of the function.
 *
 * This encapsulation is used to provide extra functionality to native functions, because some
 * classes -such as AnticheatLayer- need to keep track of called functions.
 *
 * @author spell
 */
public class FunctionAccess {
    private static SAMPFunctionsExecutor executor;

    static {
        // Set default func executor
        executor = new SAMPFunctionsExecutor() {};
    }

    public static void setExecutor(SAMPFunctionsExecutor executor) {
        FunctionAccess.executor = executor;
    }

    /* misc */
    public static boolean logprintf(String string) {
        return executor.logprintf(string);
    }
    public static boolean crash() {
        return executor.crash();
    }
    public static boolean SomeVeryLargeNameMethodOne() {
        return executor.SomeVeryLargeNameMethodOne();
    }

    /* a_players */
    public static boolean SetSpawnInfo(int playerid, int team, int skin, float x, float y, float z, float rotation, int weapon1, int weapon1_ammo, int weapon2, int weapon2_ammo, int weapon3, int weapon3_ammo) {
        return executor.SetSpawnInfo(playerid, team, skin, x, y, z, rotation, weapon1, weapon1_ammo, weapon2, weapon2_ammo, weapon3, weapon3_ammo);
    }
    public static boolean SpawnPlayer(int playerid) {
        return executor.SpawnPlayer(playerid);
    }
    public static boolean SetPlayerPos(int playerid, float x, float y, float z) {
        return executor.SetPlayerPos(playerid, x, y, z);
    }
    public static boolean SetPlayerPosFindZ(int playerid, float x, float y, float z) {
        return executor.SetPlayerPosFindZ(playerid, x, y, z);
    }
    public static boolean SetPlayerFacingAngle(int playerid, float angle) {
        return executor.SetPlayerFacingAngle(playerid, angle);
    }
    public static boolean IsPlayerInRangeOfPoint(int playerid, float range, float x, float y, float z) {
        return executor.IsPlayerInRangeOfPoint(playerid, range, x, y, z);
    }
    public static float GetPlayerDistanceFromPoint(int playerid, float x, float y, float z) {
        return executor.GetPlayerDistanceFromPoint(playerid, x, y, z);
    }
    public static boolean IsPlayerStreamedIn(int playerid, int forplayerid) {
        return executor.IsPlayerStreamedIn(playerid, forplayerid);
    }
    public static boolean SetPlayerInterior(int playerid, int interiorid) {
        return executor.SetPlayerInterior(playerid, interiorid);
    }
    public static int GetPlayerInterior(int playerid) {
        return executor.GetPlayerInterior(playerid);
    }
    public static boolean SetPlayerHealth(int playerid, float health) {
        return executor.SetPlayerHealth(playerid, health);
    }
    public static boolean SetPlayerArmour(int playerid, float armour) {
        return executor.SetPlayerArmour(playerid, armour);
    }
    public static boolean SetPlayerAmmo(int playerid, int weaponid, int ammo) {
        return executor.SetPlayerAmmo(playerid, weaponid, ammo);
    }
    public static int GetPlayerAmmo(int playerid) {
        return executor.GetPlayerAmmo(playerid);
    }
    public static int GetPlayerWeaponState(int playerid) {
        return executor.GetPlayerWeaponState(playerid);
    }
    public static int GetPlayerTargetPlayer(int playerid) {
        return executor.GetPlayerTargetPlayer(playerid);
    }
    public static boolean SetPlayerTeam(int playerid, int teamid) {
        return executor.SetPlayerTeam(playerid, teamid);
    }
    public static int GetPlayerTeam(int playerid) {
        return executor.GetPlayerTeam(playerid);
    }
    public static boolean SetPlayerScore(int playerid, int score) {
        return executor.SetPlayerScore(playerid, score);
    }
    public static int GetPlayerScore(int playerid) {
        return executor.GetPlayerScore(playerid);
    }
    public static int GetPlayerDrunkLevel(int playerid) {
        return executor.GetPlayerDrunkLevel(playerid);
    }
    public static boolean SetPlayerDrunkLevel(int playerid, int level) {
        return executor.SetPlayerDrunkLevel(playerid, level);
    }
    public static boolean SetPlayerColor(int playerid, int color) {
        return executor.SetPlayerColor(playerid, color);
    }
    public static int GetPlayerColor(int playerid) {
        return executor.GetPlayerColor(playerid);
    }
    public static boolean SetPlayerSkin(int playerid, int skinid) {
        return executor.SetPlayerSkin(playerid, skinid);
    }
    public static int GetPlayerSkin(int playerid) {
        return executor.GetPlayerSkin(playerid);
    }
    public static boolean GivePlayerWeapon(int playerid, int weaponid, int ammo) {
        return executor.GivePlayerWeapon(playerid, weaponid, ammo);
    }
    public static boolean ResetPlayerWeapons(int playerid) {
        return executor.ResetPlayerWeapons(playerid);
    }
    public static boolean SetPlayerArmedWeapon(int playerid, int weaponid) {
        return executor.SetPlayerArmedWeapon(playerid, weaponid);
    }
    public static boolean GivePlayerMoney(int playerid, int money) {
        return executor.GivePlayerMoney(playerid, money);
    }
    public static boolean ResetPlayerMoney(int playerid) {
        return executor.ResetPlayerMoney(playerid);
    }
    public static int SetPlayerName(int playerid, String name) {
        return executor.SetPlayerName(playerid, name);
    }
    public static int GetPlayerMoney(int playerid) {
        return executor.GetPlayerMoney(playerid);
    }
    public static int GetPlayerState(int playerid) {
        return executor.GetPlayerState(playerid);
    }
    public static int GetPlayerPing(int playerid) {
        return executor.GetPlayerPing(playerid);
    }
    public static int GetPlayerWeapon(int playerid) {
        return executor.GetPlayerWeapon(playerid);
    }
    public static boolean SetPlayerTime(int playerid, int hour, int minute) {
        return executor.SetPlayerTime(playerid, hour, minute);
    }
    public static boolean TogglePlayerClock(int playerid, boolean toggle) {
        return executor.TogglePlayerClock(playerid, toggle);
    }
    public static boolean SetPlayerWeather(int playerid, int weather) {
        return executor.SetPlayerWeather(playerid, weather);
    }
    public static boolean ForceClassSelection(int playerid) {
        return executor.ForceClassSelection(playerid);
    }
    public static boolean SetPlayerWantedLevel(int playerid, int level) {
        return executor.SetPlayerWantedLevel(playerid, level);
    }
    public static int GetPlayerWantedLevel(int playerid) {
        return executor.GetPlayerWantedLevel(playerid);
    }
    public static boolean SetPlayerFightingStyle(int playerid, int style) {
        return executor.SetPlayerFightingStyle(playerid, style);
    }
    public static int GetPlayerFightingStyle(int playerid) {
        return executor.GetPlayerFightingStyle(playerid);
    }
    public static boolean SetPlayerVelocity(int playerid, float x, float y, float z) {
        return executor.SetPlayerVelocity(playerid, x, y, z);
    }
    public static boolean PlayCrimeReportForPlayer(int playerid, int suspectid, int crime) {
        return executor.PlayCrimeReportForPlayer(playerid, suspectid, crime);
    }
    public static boolean PlayAudioStreamForPlayer(int playerid, String url, float posX , float posY , float posZ , float distance , boolean usepos ) {
        return executor.PlayAudioStreamForPlayer(playerid, url, posX , posY , posZ , distance , usepos );
    }
    public static boolean StopAudioStreamForPlayer(int playerid) {
        return executor.StopAudioStreamForPlayer(playerid);
    }
    public static boolean SetPlayerShopName(int playerid, String shopname) {
        return executor.SetPlayerShopName(playerid, shopname);
    }
    public static boolean SetPlayerSkillLevel(int playerid, int skill, int level) {
        return executor.SetPlayerSkillLevel(playerid, skill, level);
    }
    public static int GetPlayerSurfingVehicleID(int playerid) {
        return executor.GetPlayerSurfingVehicleID(playerid);
    }
    public static int GetPlayerSurfingObjectID(int playerid) {
        return executor.GetPlayerSurfingObjectID(playerid);
    }
    public static boolean RemoveBuildingForPlayer(int playerid, int modelid, float fX, float fY, float fZ, float fRadius) {
        return executor.RemoveBuildingForPlayer(playerid, modelid, fX, fY, fZ, fRadius);
    }
    public static boolean SetPlayerAttachedObject(int playerid, int index, int modelid, int bone, float fOffsetX , float fOffsetY , float fOffsetZ , float fRotX , float fRotY , float fRotZ , float fScaleX , float fScaleY , float fScaleZ , int materialcolor1 , int materialcolor2 ) {
        return executor.SetPlayerAttachedObject(playerid, index, modelid, bone, fOffsetX , fOffsetY , fOffsetZ , fRotX , fRotY , fRotZ , fScaleX , fScaleY , fScaleZ , materialcolor1 , materialcolor2 );
    }
    public static boolean RemovePlayerAttachedObject(int playerid, int index) {
        return executor.RemovePlayerAttachedObject(playerid, index);
    }
    public static boolean IsPlayerAttachedObjectSlotUsed(int playerid, int index) {
        return executor.IsPlayerAttachedObjectSlotUsed(playerid, index);
    }
    public static boolean EditAttachedObject(int playerid, int index) {
        return executor.EditAttachedObject(playerid, index);
    }
    public static int CreatePlayerTextDraw(int playerid, float x, float y, String text) {
        return executor.CreatePlayerTextDraw(playerid, x, y, text);
    }
    public static boolean PlayerTextDrawDestroy(int playerid, int text) {
        return executor.PlayerTextDrawDestroy(playerid, text);
    }
    public static boolean PlayerTextDrawLetterSize(int playerid, int text, float x, float y) {
        return executor.PlayerTextDrawLetterSize(playerid, text, x, y);
    }
    public static boolean PlayerTextDrawTextSize(int playerid, int text, float x, float y) {
        return executor.PlayerTextDrawTextSize(playerid, text, x, y);
    }
    public static boolean PlayerTextDrawAlignment(int playerid, int text, int alignment) {
        return executor.PlayerTextDrawAlignment(playerid, text, alignment);
    }
    public static boolean PlayerTextDrawColor(int playerid, int text, int color) {
        return executor.PlayerTextDrawColor(playerid, text, color);
    }
    public static boolean PlayerTextDrawUseBox(int playerid, int text, boolean use) {
        return executor.PlayerTextDrawUseBox(playerid, text, use);
    }
    public static boolean PlayerTextDrawBoxColor(int playerid, int text, int color) {
        return executor.PlayerTextDrawBoxColor(playerid, text, color);
    }
    public static boolean PlayerTextDrawSetShadow(int playerid, int text, int size) {
        return executor.PlayerTextDrawSetShadow(playerid, text, size);
    }
    public static boolean PlayerTextDrawSetOutline(int playerid, int text, int size) {
        return executor.PlayerTextDrawSetOutline(playerid, text, size);
    }
    public static boolean PlayerTextDrawBackgroundColor(int playerid, int text, int color) {
        return executor.PlayerTextDrawBackgroundColor(playerid, text, color);
    }
    public static boolean PlayerTextDrawFont(int playerid, int text, int font) {
        return executor.PlayerTextDrawFont(playerid, text, font);
    }
    public static boolean PlayerTextDrawSetProportional(int playerid, int text, boolean set) {
        return executor.PlayerTextDrawSetProportional(playerid, text, set);
    }
    public static boolean PlayerTextDrawSetSelectable(int playerid, int text, boolean set) {
        return executor.PlayerTextDrawSetSelectable(playerid, text, set);
    }
    public static boolean PlayerTextDrawShow(int playerid, int text) {
        return executor.PlayerTextDrawShow(playerid, text);
    }
    public static boolean PlayerTextDrawHide(int playerid, int text) {
        return executor.PlayerTextDrawHide(playerid, text);
    }
    public static boolean PlayerTextDrawSetString(int playerid, int text, String string) {
        return executor.PlayerTextDrawSetString(playerid, text, string);
    }
    public static boolean PlayerTextDrawSetPreviewModel(int playerid, int text, int modelindex) {
        return executor.PlayerTextDrawSetPreviewModel(playerid, text, modelindex);
    }
    public static boolean PlayerTextDrawSetPreviewRot(int playerid, int text, float fRotX, float fRotY, float fRotZ, float fZoom ) {
        return executor.PlayerTextDrawSetPreviewRot(playerid, text, fRotX, fRotY, fRotZ, fZoom );
    }
    public static boolean PlayerTextDrawSetPreviewVehCol(int playerid, int text, int color1, int color2) {
        return executor.PlayerTextDrawSetPreviewVehCol(playerid, text, color1, color2);
    }
    public static boolean SetPVarInt(int playerid, String varname, int value) {
        return executor.SetPVarInt(playerid, varname, value);
    }
    public static int GetPVarInt(int playerid, String varname) {
        return executor.GetPVarInt(playerid, varname);
    }
    public static boolean SetPVarString(int playerid, String varname, String value) {
        return executor.SetPVarString(playerid, varname, value);
    }
    public static boolean SetPVarFloat(int playerid, String varname, float value) {
        return executor.SetPVarFloat(playerid, varname, value);
    }
    public static float GetPVarFloat(int playerid, String varname) {
        return executor.GetPVarFloat(playerid, varname);
    }
    public static boolean DeletePVar(int playerid, String varname) {
        return executor.DeletePVar(playerid, varname);
    }
    public static int GetPVarsUpperIndex(int playerid) {
        return executor.GetPVarsUpperIndex(playerid);
    }
    public static int GetPVarType(int playerid, String varname) {
        return executor.GetPVarType(playerid, varname);
    }
    public static boolean SetPlayerChatBubble(int playerid, String text, int color, float drawdistance, int expiretime) {
        return executor.SetPlayerChatBubble(playerid, text, color, drawdistance, expiretime);
    }
    public static boolean PutPlayerInVehicle(int playerid, int vehicleid, int seatid) {
        return executor.PutPlayerInVehicle(playerid, vehicleid, seatid);
    }
    public static int GetPlayerVehicleID(int playerid) {
        return executor.GetPlayerVehicleID(playerid);
    }
    public static int GetPlayerVehicleSeat(int playerid) {
        return executor.GetPlayerVehicleSeat(playerid);
    }
    public static boolean RemovePlayerFromVehicle(int playerid) {
        return executor.RemovePlayerFromVehicle(playerid);
    }
    public static boolean TogglePlayerControllable(int playerid, boolean toggle) {
        return executor.TogglePlayerControllable(playerid, toggle);
    }
    public static boolean PlayerPlaySound(int playerid, int soundid, float x, float y, float z) {
        return executor.PlayerPlaySound(playerid, soundid, x, y, z);
    }
    public static boolean ApplyAnimation(int playerid, String animlib, String animname, float fDelta, boolean loop, boolean lockx, boolean locky, boolean freeze, int time, boolean forcesync ) {
        return executor.ApplyAnimation(playerid, animlib, animname, fDelta, loop, lockx, locky, freeze, time, forcesync );
    }
    public static boolean ClearAnimations(int playerid, boolean forcesync ) {
        return executor.ClearAnimations(playerid, forcesync );
    }
    public static int GetPlayerAnimationIndex(int playerid) {
        return executor.GetPlayerAnimationIndex(playerid);
    }
    public static int GetPlayerSpecialAction(int playerid) {
        return executor.GetPlayerSpecialAction(playerid);
    }
    public static boolean SetPlayerSpecialAction(int playerid, int actionid) {
        return executor.SetPlayerSpecialAction(playerid, actionid);
    }
    public static boolean SetPlayerCheckpoint(int playerid, float x, float y, float z, float size) {
        return executor.SetPlayerCheckpoint(playerid, x, y, z, size);
    }
    public static boolean DisablePlayerCheckpoint(int playerid) {
        return executor.DisablePlayerCheckpoint(playerid);
    }
    public static boolean SetPlayerRaceCheckpoint(int playerid, int type, float x, float y, float z, float nextx, float nexty, float nextz, float size) {
        return executor.SetPlayerRaceCheckpoint(playerid, type, x, y, z, nextx, nexty, nextz, size);
    }
    public static boolean DisablePlayerRaceCheckpoint(int playerid) {
        return executor.DisablePlayerRaceCheckpoint(playerid);
    }
    public static boolean SetPlayerWorldBounds(int playerid, float x_max, float x_min, float y_max, float y_min) {
        return executor.SetPlayerWorldBounds(playerid, x_max, x_min, y_max, y_min);
    }
    public static boolean SetPlayerMarkerForPlayer(int playerid, int showplayerid, int color) {
        return executor.SetPlayerMarkerForPlayer(playerid, showplayerid, color);
    }
    public static boolean ShowPlayerNameTagForPlayer(int playerid, int showplayerid, boolean show) {
        return executor.ShowPlayerNameTagForPlayer(playerid, showplayerid, show);
    }
    public static boolean SetPlayerMapIcon(int playerid, int iconid, float x, float y, float z, int markertype, int color, int style ) {
        return executor.SetPlayerMapIcon(playerid, iconid, x, y, z, markertype, color, style );
    }
    public static boolean RemovePlayerMapIcon(int playerid, int iconid) {
        return executor.RemovePlayerMapIcon(playerid, iconid);
    }
    public static boolean AllowPlayerTeleport(int playerid, boolean allow) {
        return executor.AllowPlayerTeleport(playerid, allow);
    }
    public static boolean SetPlayerCameraPos(int playerid, float x, float y, float z) {
        return executor.SetPlayerCameraPos(playerid, x, y, z);
    }
    public static boolean SetPlayerCameraLookAt(int playerid, float x, float y, float z, int cut ) {
        return executor.SetPlayerCameraLookAt(playerid, x, y, z, cut );
    }
    public static boolean SetCameraBehindPlayer(int playerid) {
        return executor.SetCameraBehindPlayer(playerid);
    }
    public static int GetPlayerCameraMode(int playerid) {
        return executor.GetPlayerCameraMode(playerid);
    }
    public static float GetPlayerCameraAspectRatio(int playerid) {
        return executor.GetPlayerCameraAspectRatio(playerid);
    }
    public static float GetPlayerCameraZoom(int playerid) {
        return executor.GetPlayerCameraZoom(playerid);
    }
    public static boolean AttachCameraToObject(int playerid, int objectid) {
        return executor.AttachCameraToObject(playerid, objectid);
    }
    public static boolean AttachCameraToPlayerObject(int playerid, int playerobjectid) {
        return executor.AttachCameraToPlayerObject(playerid, playerobjectid);
    }
    public static boolean InterpolateCameraPos(int playerid, float FromX, float FromY, float FromZ, float ToX, float ToY, float ToZ, int time, int cut ) {
        return executor.InterpolateCameraPos(playerid, FromX, FromY, FromZ, ToX, ToY, ToZ, time, cut );
    }
    public static boolean InterpolateCameraLookAt(int playerid, float FromX, float FromY, float FromZ, float ToX, float ToY, float ToZ, int time, int cut ) {
        return executor.InterpolateCameraLookAt(playerid, FromX, FromY, FromZ, ToX, ToY, ToZ, time, cut );
    }
    public static boolean IsPlayerConnected(int playerid) {
        return executor.IsPlayerConnected(playerid);
    }
    public static boolean IsPlayerInVehicle(int playerid, int vehicleid) {
        return executor.IsPlayerInVehicle(playerid, vehicleid);
    }
    public static boolean IsPlayerInAnyVehicle(int playerid) {
        return executor.IsPlayerInAnyVehicle(playerid);
    }
    public static boolean IsPlayerInCheckpoint(int playerid) {
        return executor.IsPlayerInCheckpoint(playerid);
    }
    public static boolean IsPlayerInRaceCheckpoint(int playerid) {
        return executor.IsPlayerInRaceCheckpoint(playerid);
    }
    public static boolean SetPlayerVirtualWorld(int playerid, int worldid) {
        return executor.SetPlayerVirtualWorld(playerid, worldid);
    }
    public static int GetPlayerVirtualWorld(int playerid) {
        return executor.GetPlayerVirtualWorld(playerid);
    }
    public static boolean EnableStuntBonusForPlayer(int playerid, boolean enable) {
        return executor.EnableStuntBonusForPlayer(playerid, enable);
    }
    public static boolean EnableStuntBonusForAll(boolean enable) {
        return executor.EnableStuntBonusForAll(enable);
    }
    public static boolean TogglePlayerSpectating(int playerid, boolean toggle) {
        return executor.TogglePlayerSpectating(playerid, toggle);
    }
    public static boolean PlayerSpectatePlayer(int playerid, int targetplayerid, int mode ) {
        return executor.PlayerSpectatePlayer(playerid, targetplayerid, mode );
    }
    public static boolean PlayerSpectateVehicle(int playerid, int targetvehicleid, int mode ) {
        return executor.PlayerSpectateVehicle(playerid, targetvehicleid, mode );
    }
    public static boolean StartRecordingPlayerData(int playerid, int recordtype, String recordname) {
        return executor.StartRecordingPlayerData(playerid, recordtype, recordname);
    }
    public static boolean StopRecordingPlayerData(int playerid) {
        return executor.StopRecordingPlayerData(playerid);
    }
    public static boolean CreateExplosionForPlayer(int playerid, float X, float Y, float Z, int type, float Radius) {
        return executor.CreateExplosionForPlayer(playerid, X, Y, Z, type, Radius);
    }
    public static float[] GetPlayerPos(int playerid) {
        return executor.GetPlayerPos(playerid);
    }
    public static float GetPlayerFacingAngle(int playerid) {
        return executor.GetPlayerFacingAngle(playerid);
    }
    public static float GetPlayerHealth(int playerid) {
        return executor.GetPlayerHealth(playerid);
    }
    public static float GetPlayerArmour(int playerid) {
        return executor.GetPlayerArmour(playerid);
    }
    public static int GetPlayerWeaponSlot(int playerid, int slot) {
        return executor.GetPlayerWeaponSlot(playerid, slot);
    }
    public static int GetPlayerAmmoSlot(int playerid, int slot) {
        return executor.GetPlayerAmmoSlot(playerid, slot);
    }
    public static String GetPlayerIp(int playerid) {
        return executor.GetPlayerIp(playerid);
    }
    public static int[] GetPlayerKeys(int playerid) {
        return executor.GetPlayerKeys(playerid);
    }
    public static String GetPlayerName(int playerid) {
        return executor.GetPlayerName(playerid);
    }
    public static int GetPlayerHour(int playerid) {
        return executor.GetPlayerHour(playerid);
    }
    public static int GetPlayerTime(int playerid) {
        return executor.GetPlayerTime(playerid);
    }
    public static float[] GetPlayerVelocity(int playerid) {
        return executor.GetPlayerVelocity(playerid);
    }
    public static float[] GetPlayerLastShotVectors(int playerid) {
        return executor.GetPlayerLastShotVectors(playerid);
    }
    public static String GetPVarString(int playerid, String varname) {
        return executor.GetPVarString(playerid, varname);
    }
    public static String GetPVarNameAtIndex(int playerid, int index) {
        return executor.GetPVarNameAtIndex(playerid, index);
    }
    public static String GetAnimationLibrary(int index) {
        return executor.GetAnimationLibrary(index);
    }
    public static String GetAnimationName(int index) {
        return executor.GetAnimationName(index);
    }
    public static float[] GetPlayerCameraPos(int playerid) {
        return executor.GetPlayerCameraPos(playerid);
    }
    public static float[] GetPlayerCameraFrontVector(int playerid) {
        return executor.GetPlayerCameraFrontVector(playerid);
    }

    /* a_objects */
    public static int CreateObject(int modelid, float x, float y, float z, float rX, float rY, float rZ, float DrawDistance ) {
        return executor.CreateObject(modelid, x, y, z, rX, rY, rZ, DrawDistance );
    }
    public static boolean AttachObjectToVehicle(int objectid, int vehicleid, float fOffsetX, float fOffsetY, float fOffsetZ, float fRotX, float fRotY, float fRotZ) {
        return executor.AttachObjectToVehicle(objectid, vehicleid, fOffsetX, fOffsetY, fOffsetZ, fRotX, fRotY, fRotZ);
    }
    public static boolean AttachObjectToObject(int objectid, int attachtoid, float fOffsetX, float fOffsetY, float fOffsetZ, float fRotX, float fRotY, float fRotZ, boolean SyncRotation ) {
        return executor.AttachObjectToObject(objectid, attachtoid, fOffsetX, fOffsetY, fOffsetZ, fRotX, fRotY, fRotZ, SyncRotation );
    }
    public static boolean AttachObjectToPlayer(int objectid, int playerid, float fOffsetX, float fOffsetY, float fOffsetZ, float fRotX, float fRotY, float fRotZ) {
        return executor.AttachObjectToPlayer(objectid, playerid, fOffsetX, fOffsetY, fOffsetZ, fRotX, fRotY, fRotZ);
    }
    public static boolean SetObjectPos(int objectid, float x, float y, float z) {
        return executor.SetObjectPos(objectid, x, y, z);
    }
    public static boolean SetObjectRot(int objectid, float rotX, float rotY, float rotZ) {
        return executor.SetObjectRot(objectid, rotX, rotY, rotZ);
    }
    public static boolean IsValidObject(int objectid) {
        return executor.IsValidObject(objectid);
    }
    public static boolean DestroyObject(int objectid) {
        return executor.DestroyObject(objectid);
    }
    public static int MoveObject(int objectid, float X, float Y, float Z, float Speed, float RotX , float RotY , float RotZ ) {
        return executor.MoveObject(objectid, X, Y, Z, Speed, RotX , RotY , RotZ );
    }
    public static boolean StopObject(int objectid) {
        return executor.StopObject(objectid);
    }
    public static boolean IsObjectMoving(int objectid) {
        return executor.IsObjectMoving(objectid);
    }
    public static boolean EditObject(int playerid, int objectid) {
        return executor.EditObject(playerid, objectid);
    }
    public static boolean EditPlayerObject(int playerid, int objectid) {
        return executor.EditPlayerObject(playerid, objectid);
    }
    public static boolean SelectObject(int playerid) {
        return executor.SelectObject(playerid);
    }
    public static boolean CancelEdit(int playerid) {
        return executor.CancelEdit(playerid);
    }
    public static int CreatePlayerObject(int playerid, int modelid, float x, float y, float z, float rX, float rY, float rZ, float DrawDistance ) {
        return executor.CreatePlayerObject(playerid, modelid, x, y, z, rX, rY, rZ, DrawDistance );
    }
    public static boolean AttachPlayerObjectToPlayer(int objectplayer, int objectid, int attachplayer, float OffsetX, float OffsetY, float OffsetZ, float rX, float rY, float rZ) {
        return executor.AttachPlayerObjectToPlayer(objectplayer, objectid, attachplayer, OffsetX, OffsetY, OffsetZ, rX, rY, rZ);
    }
    public static boolean AttachPlayerObjectToVehicle(int playerid, int objectid, int vehicleid, float fOffsetX, float fOffsetY, float fOffsetZ, float fRotX, float fRotY, float RotZ) {
        return executor.AttachPlayerObjectToVehicle(playerid, objectid, vehicleid, fOffsetX, fOffsetY, fOffsetZ, fRotX, fRotY, RotZ);
    }
    public static boolean SetPlayerObjectPos(int playerid, int objectid, float x, float y, float z) {
        return executor.SetPlayerObjectPos(playerid, objectid, x, y, z);
    }
    public static boolean SetPlayerObjectRot(int playerid, int objectid, float rotX, float rotY, float rotZ) {
        return executor.SetPlayerObjectRot(playerid, objectid, rotX, rotY, rotZ);
    }
    public static boolean IsValidPlayerObject(int playerid, int objectid) {
        return executor.IsValidPlayerObject(playerid, objectid);
    }
    public static boolean DestroyPlayerObject(int playerid, int objectid) {
        return executor.DestroyPlayerObject(playerid, objectid);
    }
    public static int MovePlayerObject(int playerid, int objectid, float x, float y, float z, float Speed, float RotX , float RotY , float RotZ ) {
        return executor.MovePlayerObject(playerid, objectid, x, y, z, Speed, RotX , RotY , RotZ );
    }
    public static boolean StopPlayerObject(int playerid, int objectid) {
        return executor.StopPlayerObject(playerid, objectid);
    }
    public static boolean IsPlayerObjectMoving(int playerid, int objectid) {
        return executor.IsPlayerObjectMoving(playerid, objectid);
    }
    public static boolean SetObjectMaterial(int objectid, int materialindex, int modelid, String txdname, String texturename, int materialcolor ) {
        return executor.SetObjectMaterial(objectid, materialindex, modelid, txdname, texturename, materialcolor );
    }
    public static boolean SetPlayerObjectMaterial(int playerid, int objectid, int materialindex, int modelid, String txdname, String texturename, int materialcolor ) {
        return executor.SetPlayerObjectMaterial(playerid, objectid, materialindex, modelid, txdname, texturename, materialcolor );
    }
    public static boolean SetObjectMaterialText(int objectid, String text, int materialindex , int materialsize , String fontface , int fontsize , boolean bold , int fontcolor , int backcolor , int textalignment ) {
        return executor.SetObjectMaterialText(objectid, text, materialindex , materialsize , fontface , fontsize , bold , fontcolor , backcolor , textalignment );
    }
    public static boolean SetPlayerObjectMaterialText(int playerid, int objectid, String text, int materialindex , int materialsize , String fontface , int fontsize , boolean bold , int fontcolor , int backcolor , int textalignment ) {
        return executor.SetPlayerObjectMaterialText(playerid, objectid, text, materialindex , materialsize , fontface , fontsize , bold , fontcolor , backcolor , textalignment );
    }
    public static float[] GetObjectPos(int objectid) {
        return executor.GetObjectPos(objectid);
    }
    public static float[] GetObjectRot(int objectid) {
        return executor.GetObjectRot(objectid);
    }
    public static float[] GetPlayerObjectPos(int playerid, int objectid) {
        return executor.GetPlayerObjectPos(playerid, objectid);
    }
    public static float[] GetPlayerObjectRot(int playerid, int objectid) {
        return executor.GetPlayerObjectRot(playerid, objectid);
    }

    /* a_vehicles */
    public static boolean IsValidVehicle(int vehicleid) {
        return executor.IsValidVehicle(vehicleid);
    }
    public static float GetVehicleDistanceFromPoint(int vehicleid, float x, float y, float z) {
        return executor.GetVehicleDistanceFromPoint(vehicleid, x, y, z);
    }
    public static int CreateVehicle(int vehicletype, float x, float y, float z, float rotation, int color1, int color2, int respawn_delay, boolean addsiren) {
        return executor.CreateVehicle(vehicletype, x, y, z, rotation, color1, color2, respawn_delay, addsiren);
    }
    public static boolean DestroyVehicle(int vehicleid) {
        return executor.DestroyVehicle(vehicleid);
    }
    public static boolean IsVehicleStreamedIn(int vehicleid, int forplayerid) {
        return executor.IsVehicleStreamedIn(vehicleid, forplayerid);
    }
    public static boolean SetVehiclePos(int vehicleid, float x, float y, float z) {
        return executor.SetVehiclePos(vehicleid, x, y, z);
    }
    public static boolean SetVehicleZAngle(int vehicleid, float z_angle) {
        return executor.SetVehicleZAngle(vehicleid, z_angle);
    }
    public static boolean SetVehicleParamsForPlayer(int vehicleid, int playerid, boolean objective, boolean doorslocked) {
        return executor.SetVehicleParamsForPlayer(vehicleid, playerid, objective, doorslocked);
    }
    public static boolean ManualVehicleEngineAndLights() {
        return executor.ManualVehicleEngineAndLights();
    }
    public static boolean SetVehicleParamsEx(int vehicleid, boolean engine, boolean lights, boolean alarm, boolean doors, boolean bonnet, boolean boot, boolean objective) {
        return executor.SetVehicleParamsEx(vehicleid, engine, lights, alarm, doors, bonnet, boot, objective);
    }
    public static boolean SetVehicleToRespawn(int vehicleid) {
        return executor.SetVehicleToRespawn(vehicleid);
    }
    public static boolean LinkVehicleToInterior(int vehicleid, int interiorid) {
        return executor.LinkVehicleToInterior(vehicleid, interiorid);
    }
    public static boolean AddVehicleComponent(int vehicleid, int componentid) {
        return executor.AddVehicleComponent(vehicleid, componentid);
    }
    public static boolean RemoveVehicleComponent(int vehicleid, int componentid) {
        return executor.RemoveVehicleComponent(vehicleid, componentid);
    }
    public static boolean ChangeVehicleColor(int vehicleid, int color1, int color2) {
        return executor.ChangeVehicleColor(vehicleid, color1, color2);
    }
    public static boolean ChangeVehiclePaintjob(int vehicleid, int paintjobid) {
        return executor.ChangeVehiclePaintjob(vehicleid, paintjobid);
    }
    public static boolean SetVehicleHealth(int vehicleid, float health) {
        return executor.SetVehicleHealth(vehicleid, health);
    }
    public static boolean AttachTrailerToVehicle(int trailerid, int vehicleid) {
        return executor.AttachTrailerToVehicle(trailerid, vehicleid);
    }
    public static boolean DetachTrailerFromVehicle(int vehicleid) {
        return executor.DetachTrailerFromVehicle(vehicleid);
    }
    public static boolean IsTrailerAttachedToVehicle(int vehicleid) {
        return executor.IsTrailerAttachedToVehicle(vehicleid);
    }
    public static int GetVehicleTrailer(int vehicleid) {
        return executor.GetVehicleTrailer(vehicleid);
    }
    public static boolean SetVehicleNumberPlate(int vehicleid, String numberplate) {
        return executor.SetVehicleNumberPlate(vehicleid, numberplate);
    }
    public static int GetVehicleModel(int vehicleid) {
        return executor.GetVehicleModel(vehicleid);
    }
    public static int GetVehicleComponentInSlot(int vehicleid, int slot) {
        return executor.GetVehicleComponentInSlot(vehicleid, slot);
    }
    public static int GetVehicleComponentType(int component) {
        return executor.GetVehicleComponentType(component);
    }
    public static boolean RepairVehicle(int vehicleid) {
        return executor.RepairVehicle(vehicleid);
    }
    public static boolean SetVehicleVelocity(int vehicleid, float X, float Y, float Z) {
        return executor.SetVehicleVelocity(vehicleid, X, Y, Z);
    }
    public static boolean SetVehicleAngularVelocity(int vehicleid, float X, float Y, float Z) {
        return executor.SetVehicleAngularVelocity(vehicleid, X, Y, Z);
    }
    public static boolean UpdateVehicleDamageStatus(int vehicleid, int panels, int doors, int lights, int tires) {
        return executor.UpdateVehicleDamageStatus(vehicleid, panels, doors, lights, tires);
    }
    public static boolean SetVehicleVirtualWorld(int vehicleid, int worldid) {
        return executor.SetVehicleVirtualWorld(vehicleid, worldid);
    }
    public static int GetVehicleVirtualWorld(int vehicleid) {
        return executor.GetVehicleVirtualWorld(vehicleid);
    }
    public static float[] GetVehiclePos(int vehicleid) {
        return executor.GetVehiclePos(vehicleid);
    }
    public static float GetVehicleZAngle(int vehicleid) {
        return executor.GetVehicleZAngle(vehicleid);
    }
    public static float[] GetVehicleRotationQuat(int vehicleid) {
        return executor.GetVehicleRotationQuat(vehicleid);
    }
    public static int[] GetVehicleParamsEx(int vehicleid) {
        return executor.GetVehicleParamsEx(vehicleid);
    }
    public static float GetVehicleHealth(int vehicleid) {
        return executor.GetVehicleHealth(vehicleid);
    }
    public static float[] GetVehicleVelocity(int vehicleid) {
        return executor.GetVehicleVelocity(vehicleid);
    }
    public static int[] GetVehicleDamageStatus(int vehicleid) {
        return executor.GetVehicleDamageStatus(vehicleid);
    }
    public static float[] GetVehicleModelInfo(int vehicleid, int infotype) {
        return executor.GetVehicleModelInfo(vehicleid, infotype);
    }

    /* a_samp */
    public static boolean SendClientMessage(int playerid, int color, String message) {
        return executor.SendClientMessage(playerid, color, message);
    }
    public static boolean SendClientMessageToAll(int color, String message) {
        return executor.SendClientMessageToAll(color, message);
    }
    public static boolean SendPlayerMessageToPlayer(int playerid, int senderid, String message) {
        return executor.SendPlayerMessageToPlayer(playerid, senderid, message);
    }
    public static boolean SendPlayerMessageToAll(int senderid, String message) {
        return executor.SendPlayerMessageToAll(senderid, message);
    }
    public static boolean SendDeathMessage(int killer, int killee, int weapon) {
        return executor.SendDeathMessage(killer, killee, weapon);
    }
    public static boolean SendDeathMessageToPlayer(int playerid, int killer, int killee, int weapon) {
        return executor.SendDeathMessageToPlayer(playerid, killer, killee, weapon);
    }
    public static boolean GameTextForAll(String text, int time, int style) {
        return executor.GameTextForAll(text, time, style);
    }
    public static boolean GameTextForPlayer(int playerid, String text, int time, int style) {
        return executor.GameTextForPlayer(playerid, text, time, style);
    }
    public static int GetTickCount() {
        return executor.GetTickCount();
    }
    public static int GetMaxPlayers() {
        return executor.GetMaxPlayers();
    }
    public static float VectorSize(float x, float y, float z) {
        return executor.VectorSize(x, y, z);
    }
    public static boolean SetGameModeText(String text) {
        return executor.SetGameModeText(text);
    }
    public static boolean SetTeamCount(int count) {
        return executor.SetTeamCount(count);
    }
    public static int AddPlayerClass(int modelid, float spawn_x, float spawn_y, float spawn_z, float z_angle, int weapon1, int weapon1_ammo, int weapon2, int weapon2_ammo, int weapon3, int weapon3_ammo) {
        return executor.AddPlayerClass(modelid, spawn_x, spawn_y, spawn_z, z_angle, weapon1, weapon1_ammo, weapon2, weapon2_ammo, weapon3, weapon3_ammo);
    }
    public static int AddPlayerClassEx(int teamid, int modelid, float spawn_x, float spawn_y, float spawn_z, float z_angle, int weapon1, int weapon1_ammo, int weapon2, int weapon2_ammo, int weapon3, int weapon3_ammo) {
        return executor.AddPlayerClassEx(teamid, modelid, spawn_x, spawn_y, spawn_z, z_angle, weapon1, weapon1_ammo, weapon2, weapon2_ammo, weapon3, weapon3_ammo);
    }
    public static int AddStaticVehicle(int modelid, float spawn_x, float spawn_y, float spawn_z, float z_angle, int color1, int color2) {
        return executor.AddStaticVehicle(modelid, spawn_x, spawn_y, spawn_z, z_angle, color1, color2);
    }
    public static int AddStaticVehicleEx(int modelid, float spawn_x, float spawn_y, float spawn_z, float z_angle, int color1, int color2, int respawn_delay, boolean addsiren) {
        return executor.AddStaticVehicleEx(modelid, spawn_x, spawn_y, spawn_z, z_angle, color1, color2, respawn_delay, addsiren);
    }
    public static int AddStaticPickup(int model, int type, float x, float y, float z, int virtualworld ) {
        return executor.AddStaticPickup(model, type, x, y, z, virtualworld );
    }
    public static int CreatePickup(int model, int type, float x, float y, float z, int virtualworld ) {
        return executor.CreatePickup(model, type, x, y, z, virtualworld );
    }
    public static boolean DestroyPickup(int pickup) {
        return executor.DestroyPickup(pickup);
    }
    public static boolean ShowNameTags(boolean show) {
        return executor.ShowNameTags(show);
    }
    public static boolean ShowPlayerMarkers(int mode) {
        return executor.ShowPlayerMarkers(mode);
    }
    public static boolean GameModeExit() {
        return executor.GameModeExit();
    }
    public static boolean SetWorldTime(int hour) {
        return executor.SetWorldTime(hour);
    }
    public static boolean EnableTirePopping(boolean enable) {
        return executor.EnableTirePopping(enable);
    }
    public static boolean EnableVehicleFriendlyFire() {
        return executor.EnableVehicleFriendlyFire();
    }
    public static boolean AllowInteriorWeapons(boolean allow) {
        return executor.AllowInteriorWeapons(allow);
    }
    public static boolean SetWeather(int weatherid) {
        return executor.SetWeather(weatherid);
    }
    public static boolean SetGravity(float gravity) {
        return executor.SetGravity(gravity);
    }
    public static boolean AllowAdminTeleport(boolean allow) {
        return executor.AllowAdminTeleport(allow);
    }
    public static boolean SetDeathDropAmount(int amount) {
        return executor.SetDeathDropAmount(amount);
    }
    public static boolean CreateExplosion(float x, float y, float z, int type, float radius) {
        return executor.CreateExplosion(x, y, z, type, radius);
    }
    public static boolean EnableZoneNames(boolean enable) {
        return executor.EnableZoneNames(enable);
    }
    public static boolean UsePlayerPedAnims() {
        return executor.UsePlayerPedAnims();
    }
    public static boolean DisableInteriorEnterExits() {
        return executor.DisableInteriorEnterExits();
    }
    public static boolean SetNameTagDrawDistance(float distance) {
        return executor.SetNameTagDrawDistance(distance);
    }
    public static boolean DisableNameTagLOS() {
        return executor.DisableNameTagLOS();
    }
    public static boolean LimitGlobalChatRadius(float chat_radius) {
        return executor.LimitGlobalChatRadius(chat_radius);
    }
    public static boolean LimitPlayerMarkerRadius(float marker_radius) {
        return executor.LimitPlayerMarkerRadius(marker_radius);
    }
    public static boolean ConnectNPC(String name, String script) {
        return executor.ConnectNPC(name, script);
    }
    public static boolean IsPlayerNPC(int playerid) {
        return executor.IsPlayerNPC(playerid);
    }
    public static boolean IsPlayerAdmin(int playerid) {
        return executor.IsPlayerAdmin(playerid);
    }
    public static boolean Kick(int playerid) {
        return executor.Kick(playerid);
    }
    public static boolean Ban(int playerid) {
        return executor.Ban(playerid);
    }
    public static boolean BanEx(int playerid, String reason) {
        return executor.BanEx(playerid, reason);
    }
    public static boolean SendRconCommand(String command) {
        return executor.SendRconCommand(command);
    }
    public static int GetServerVarAsInt(String varname) {
        return executor.GetServerVarAsInt(varname);
    }
    public static boolean GetServerVarAsBool(String varname) {
        return executor.GetServerVarAsBool(varname);
    }
    public static boolean BlockIpAddress(String ip_address, int timems) {
        return executor.BlockIpAddress(ip_address, timems);
    }
    public static boolean UnBlockIpAddress(String ip_address) {
        return executor.UnBlockIpAddress(ip_address);
    }
    public static int GetServerTickRate() {
        return executor.GetServerTickRate();
    }
    public static int NetStats_GetConnectedTime(int playerid) {
        return executor.NetStats_GetConnectedTime(playerid);
    }
    public static int NetStats_MessagesReceived(int playerid) {
        return executor.NetStats_MessagesReceived(playerid);
    }
    public static int NetStats_BytesReceived(int playerid) {
        return executor.NetStats_BytesReceived(playerid);
    }
    public static int NetStats_MessagesSent(int playerid) {
        return executor.NetStats_MessagesSent(playerid);
    }
    public static int NetStats_BytesSent(int playerid) {
        return executor.NetStats_BytesSent(playerid);
    }
    public static int NetStats_MessagesRecvPerSecond(int playerid) {
        return executor.NetStats_MessagesRecvPerSecond(playerid);
    }
    public static float NetStats_PacketLossPercent(int playerid) {
        return executor.NetStats_PacketLossPercent(playerid);
    }
    public static int NetStats_ConnectionStatus(int playerid) {
        return executor.NetStats_ConnectionStatus(playerid);
    }
    public static int CreateMenu(String title, int columns, float x, float y, float col1width, float col2width ) {
        return executor.CreateMenu(title, columns, x, y, col1width, col2width );
    }
    public static boolean DestroyMenu(int menuid) {
        return executor.DestroyMenu(menuid);
    }
    public static int AddMenuItem(int menuid, int column, String menutext) {
        return executor.AddMenuItem(menuid, column, menutext);
    }
    public static boolean SetMenuColumnHeader(int menuid, int column, String columnheader) {
        return executor.SetMenuColumnHeader(menuid, column, columnheader);
    }
    public static boolean ShowMenuForPlayer(int menuid, int playerid) {
        return executor.ShowMenuForPlayer(menuid, playerid);
    }
    public static boolean HideMenuForPlayer(int menuid, int playerid) {
        return executor.HideMenuForPlayer(menuid, playerid);
    }
    public static boolean IsValidMenu(int menuid) {
        return executor.IsValidMenu(menuid);
    }
    public static boolean DisableMenu(int menuid) {
        return executor.DisableMenu(menuid);
    }
    public static boolean DisableMenuRow(int menuid, int row) {
        return executor.DisableMenuRow(menuid, row);
    }
    public static int GetPlayerMenu(int playerid) {
        return executor.GetPlayerMenu(playerid);
    }
    public static int TextDrawCreate(float x, float y, String text) {
        return executor.TextDrawCreate(x, y, text);
    }
    public static boolean TextDrawDestroy(int text) {
        return executor.TextDrawDestroy(text);
    }
    public static boolean TextDrawLetterSize(int text, float x, float y) {
        return executor.TextDrawLetterSize(text, x, y);
    }
    public static boolean TextDrawTextSize(int text, float x, float y) {
        return executor.TextDrawTextSize(text, x, y);
    }
    public static boolean TextDrawAlignment(int text, int alignment) {
        return executor.TextDrawAlignment(text, alignment);
    }
    public static boolean TextDrawColor(int text, int color) {
        return executor.TextDrawColor(text, color);
    }
    public static boolean TextDrawUseBox(int text, boolean use) {
        return executor.TextDrawUseBox(text, use);
    }
    public static boolean TextDrawBoxColor(int text, int color) {
        return executor.TextDrawBoxColor(text, color);
    }
    public static boolean TextDrawSetShadow(int text, int size) {
        return executor.TextDrawSetShadow(text, size);
    }
    public static boolean TextDrawSetOutline(int text, int size) {
        return executor.TextDrawSetOutline(text, size);
    }
    public static boolean TextDrawBackgroundColor(int text, int color) {
        return executor.TextDrawBackgroundColor(text, color);
    }
    public static boolean TextDrawFont(int text, int font) {
        return executor.TextDrawFont(text, font);
    }
    public static boolean TextDrawSetProportional(int text, boolean set) {
        return executor.TextDrawSetProportional(text, set);
    }
    public static boolean TextDrawSetSelectable(int text, boolean set) {
        return executor.TextDrawSetSelectable(text, set);
    }
    public static boolean TextDrawShowForPlayer(int playerid, int text) {
        return executor.TextDrawShowForPlayer(playerid, text);
    }
    public static boolean TextDrawHideForPlayer(int playerid, int text) {
        return executor.TextDrawHideForPlayer(playerid, text);
    }
    public static boolean TextDrawShowForAll(int text) {
        return executor.TextDrawShowForAll(text);
    }
    public static boolean TextDrawHideForAll(int text) {
        return executor.TextDrawHideForAll(text);
    }
    public static boolean TextDrawSetString(int text, String string) {
        return executor.TextDrawSetString(text, string);
    }
    public static boolean TextDrawSetPreviewModel(int text, int modelindex) {
        return executor.TextDrawSetPreviewModel(text, modelindex);
    }
    public static boolean TextDrawSetPreviewRot(int text, float fRotX, float fRotY, float fRotZ, float fZoom ) {
        return executor.TextDrawSetPreviewRot(text, fRotX, fRotY, fRotZ, fZoom );
    }
    public static boolean TextDrawSetPreviewVehCol(int text, int color1, int color2) {
        return executor.TextDrawSetPreviewVehCol(text, color1, color2);
    }
    public static boolean SelectTextDraw(int playerid, int hovercolor) {
        return executor.SelectTextDraw(playerid, hovercolor);
    }
    public static boolean CancelSelectTextDraw(int playerid) {
        return executor.CancelSelectTextDraw(playerid);
    }
    public static int GangZoneCreate(float minx, float miny, float maxx, float maxy) {
        return executor.GangZoneCreate(minx, miny, maxx, maxy);
    }
    public static boolean GangZoneDestroy(int zone) {
        return executor.GangZoneDestroy(zone);
    }
    public static boolean GangZoneShowForPlayer(int playerid, int zone, int color) {
        return executor.GangZoneShowForPlayer(playerid, zone, color);
    }
    public static boolean GangZoneShowForAll(int zone, int color) {
        return executor.GangZoneShowForAll(zone, color);
    }
    public static boolean GangZoneHideForPlayer(int playerid, int zone) {
        return executor.GangZoneHideForPlayer(playerid, zone);
    }
    public static boolean GangZoneHideForAll(int zone) {
        return executor.GangZoneHideForAll(zone);
    }
    public static boolean GangZoneFlashForPlayer(int playerid, int zone, int flashcolor) {
        return executor.GangZoneFlashForPlayer(playerid, zone, flashcolor);
    }
    public static boolean GangZoneFlashForAll(int zone, int flashcolor) {
        return executor.GangZoneFlashForAll(zone, flashcolor);
    }
    public static boolean GangZoneStopFlashForPlayer(int playerid, int zone) {
        return executor.GangZoneStopFlashForPlayer(playerid, zone);
    }
    public static boolean GangZoneStopFlashForAll(int zone) {
        return executor.GangZoneStopFlashForAll(zone);
    }
    public static int Create3DTextLabel(String text, int color, float x, float y, float z, float DrawDistance, int virtualworld, boolean testLOS ) {
        return executor.Create3DTextLabel(text, color, x, y, z, DrawDistance, virtualworld, testLOS );
    }
    public static boolean Delete3DTextLabel(int id) {
        return executor.Delete3DTextLabel(id);
    }
    public static boolean Attach3DTextLabelToPlayer(int id, int playerid, float OffsetX, float OffsetY, float OffsetZ) {
        return executor.Attach3DTextLabelToPlayer(id, playerid, OffsetX, OffsetY, OffsetZ);
    }
    public static boolean Attach3DTextLabelToVehicle(int id, int vehicleid, float OffsetX, float OffsetY, float OffsetZ) {
        return executor.Attach3DTextLabelToVehicle(id, vehicleid, OffsetX, OffsetY, OffsetZ);
    }
    public static boolean Update3DTextLabelText(int id, int color, String text) {
        return executor.Update3DTextLabelText(id, color, text);
    }
    public static int CreatePlayer3DTextLabel(int playerid, String text, int color, float x, float y, float z, float DrawDistance, int attachedplayer , int attachedvehicle , boolean testLOS ) {
        return executor.CreatePlayer3DTextLabel(playerid, text, color, x, y, z, DrawDistance, attachedplayer , attachedvehicle , testLOS );
    }
    public static boolean DeletePlayer3DTextLabel(int playerid, int id) {
        return executor.DeletePlayer3DTextLabel(playerid, id);
    }
    public static boolean UpdatePlayer3DTextLabelText(int playerid, int id, int color, String text) {
        return executor.UpdatePlayer3DTextLabelText(playerid, id, color, text);
    }
    public static boolean ShowPlayerDialog(int playerid, int dialogid, int style, String caption, String info, String button1, String button2) {
        return executor.ShowPlayerDialog(playerid, dialogid, style, caption, info, button1, button2);
    }
    public static boolean KillTimer(int timerid) {
        return executor.KillTimer(timerid);
    }
    public static String GetWeaponName(int weaponid) {
        return executor.GetWeaponName(weaponid);
    }
    public static String GetServerVarAsString(String varname) {
        return executor.GetServerVarAsString(varname);
    }
    public static String GetPlayerNetworkStats(int playerid) {
        return executor.GetPlayerNetworkStats(playerid);
    }
    public static String GetNetworkStats() {
        return executor.GetNetworkStats();
    }
    public static String GetPlayerVersion(int playerid) {
        return executor.GetPlayerVersion(playerid);
    }
    public static String NetStats_GetIpPort(int playerid) {
        return executor.NetStats_GetIpPort(playerid);
    }
    public static String gpci(int playerid) {
        return executor.gpci(playerid);
    }

    /* a_actors */
    public static int CreateActor(int modelid,float x,float y,float z,float rotation) {
        return executor.CreateActor(modelid,x,y,z,rotation);
    }
    public static boolean DestroyActor(int actorid) {
        return executor.DestroyActor(actorid);
    }
    public static boolean IsActorStreamedIn(int actorid,int forplayerid) {
        return executor.IsActorStreamedIn(actorid,forplayerid);
    }
    public static boolean SetActorVirtualWorld(int actorid,int vworld) {
        return executor.SetActorVirtualWorld(actorid,vworld);
    }
    public static int GetActorVirtualWorld(int actorid) {
        return executor.GetActorVirtualWorld(actorid);
    }
    public static boolean ApplyActorAnimation(int actorid,String animlib,String animname,float fDelta,boolean loop,boolean lockx,boolean locky,boolean freeze,int time) {
        return executor.ApplyActorAnimation(actorid,animlib,animname,fDelta,loop,lockx,locky,freeze,time);
    }
    public static boolean ClearActorAnimations(int actorid) {
        return executor.ClearActorAnimations(actorid);
    }
    public static boolean SetActorPos(int actorid,float x,float y,float z) {
        return executor.SetActorPos(actorid,x,y,z);
    }
    public static boolean SetActorFacingAngle(int actorid,float angle) {
        return executor.SetActorFacingAngle(actorid,angle);
    }
    public static boolean SetActorHealth(int actorid,float health) {
        return executor.SetActorHealth(actorid,health);
    }
    public static boolean SetActorInvulnerable(int actorid,boolean invulnerable) {
        return executor.SetActorInvulnerable(actorid,invulnerable);
    }
    public static boolean IsActorInvulnerable(int actorid) {
        return executor.IsActorInvulnerable(actorid);
    }
    public static boolean IsValidActor(int actorid) {
        return executor.IsValidActor(actorid);
    }
    public static float[] GetActorPos(int actorid) {
        return executor.GetActorPos(actorid);
    }
    public static float GetActorFacingAngle(int actorid) {
        return executor.GetActorFacingAngle(actorid);
    }
    public static float GetActorHealth(int actorid) {
        return executor.GetActorHealth(actorid);
    }

    /* Vehicle collision */
    public static boolean DisableRemoteVehicleCollisions(int playerid, boolean disable) {
        return executor.DisableRemoteVehicleCollisions(playerid, disable);
    }

    /* Camera target functions */
    public static boolean EnablePlayerCameraTarget(int playerid, boolean enable) {
        return executor.EnablePlayerCameraTarget(playerid, enable);
    }
    public static int GetPlayerCameraTargetObject(int playerid) {
        return executor.GetPlayerCameraTargetObject(playerid);
    }
    public static int GetPlayerCameraTargetPlayer(int playerid) {
        return executor.GetPlayerCameraTargetPlayer(playerid);
    }
    public static int GetPlayerCameraTargetActor(int playerid) {
        return executor.GetPlayerCameraTargetActor(playerid);
    }
    public static int GetPlayerCameraTargetVehicle(int playerid) {
        return executor.GetPlayerCameraTargetVehicle(playerid);
    }

    /* Pool */
    public static int GetPlayerPoolSize() {
        return executor.GetPlayerPoolSize();
    }
    public static int GetVehiclePoolSize() {
        return executor.GetVehiclePoolSize();
    }

    /* Car doors and windows */
    public static boolean[] GetVehicleParamsCarDoors(int vehicleid) {
        return executor.GetVehicleParamsCarDoors(vehicleid);
    }
    public static boolean[] GetVehicleParamsCarWindows(int vehicleid) {
        return executor.GetVehicleParamsCarWindows(vehicleid);
    }
    public static boolean SetVehicleParamsCarDoors(int vehicleid, boolean driver, boolean passenger, boolean backleft, boolean backright) {
        return executor.SetVehicleParamsCarDoors(vehicleid, driver, passenger, backleft, backright);
    }
    public static boolean SetVehicleParamsCarWindows(int vehicleid, boolean driver, boolean passenger, boolean backleft, boolean backright) {
        return executor.SetVehicleParamsCarWindows(vehicleid, driver, passenger, backleft, backright);
    }

    /* Camera col */
    public static boolean SetObjectNoCameraCol(int objectid) {
        return executor.SetObjectNoCameraCol(objectid);
    }
    public static boolean SetObjectsDefaultCameraCol(boolean disable) {
        return executor.SetObjectsDefaultCameraCol(disable);
    }
    public static boolean SetPlayerObjectNoCameraCol(int playerid, int objectid) {
        return executor.SetPlayerObjectNoCameraCol(playerid, objectid);
    }

    public static boolean SomeVeryLargeNameMethodTwo() {
        return executor.SomeVeryLargeNameMethodTwo();
    }

}
