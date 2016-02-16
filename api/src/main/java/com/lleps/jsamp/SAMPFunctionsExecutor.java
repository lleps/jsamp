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
 * This interface is used to hook SA:MP functions. For example, an anticheat may use
 * it to keep track of player variables.
 *
 * Default function implementations call their native function.
 *
 * @author spell
 */
public interface SAMPFunctionsExecutor {
    /* misc */
    default boolean logprintf(String string) {
        return SAMPFunctions.logprintf(string);
    }
    default boolean crash() {
        return SAMPFunctions.crash();
    }
    default boolean SomeVeryLargeNameMethodOne() {
        return SAMPFunctions.SomeVeryLargeNameMethodOne();
    }

    /* a_players */
    default boolean SetSpawnInfo(int playerid, int team, int skin, float x, float y, float z, float rotation, int weapon1, int weapon1_ammo, int weapon2, int weapon2_ammo, int weapon3, int weapon3_ammo) {
        return SAMPFunctions.SetSpawnInfo(playerid, team, skin, x, y, z, rotation, weapon1, weapon1_ammo, weapon2, weapon2_ammo, weapon3, weapon3_ammo);
    }
    default boolean SpawnPlayer(int playerid) {
        return SAMPFunctions.SpawnPlayer(playerid);
    }
    default boolean SetPlayerPos(int playerid, float x, float y, float z) {
        return SAMPFunctions.SetPlayerPos(playerid, x, y, z);
    }
    default boolean SetPlayerPosFindZ(int playerid, float x, float y, float z) {
        return SAMPFunctions.SetPlayerPosFindZ(playerid, x, y, z);
    }
    default boolean SetPlayerFacingAngle(int playerid, float angle) {
        return SAMPFunctions.SetPlayerFacingAngle(playerid, angle);
    }
    default boolean IsPlayerInRangeOfPoint(int playerid, float range, float x, float y, float z) {
        return SAMPFunctions.IsPlayerInRangeOfPoint(playerid, range, x, y, z);
    }
    default float GetPlayerDistanceFromPoint(int playerid, float x, float y, float z) {
        return SAMPFunctions.GetPlayerDistanceFromPoint(playerid, x, y, z);
    }
    default boolean IsPlayerStreamedIn(int playerid, int forplayerid) {
        return SAMPFunctions.IsPlayerStreamedIn(playerid, forplayerid);
    }
    default boolean SetPlayerInterior(int playerid, int interiorid) {
        return SAMPFunctions.SetPlayerInterior(playerid, interiorid);
    }
    default int GetPlayerInterior(int playerid) {
        return SAMPFunctions.GetPlayerInterior(playerid);
    }
    default boolean SetPlayerHealth(int playerid, float health) {
        return SAMPFunctions.SetPlayerHealth(playerid, health);
    }
    default boolean SetPlayerArmour(int playerid, float armour) {
        return SAMPFunctions.SetPlayerArmour(playerid, armour);
    }
    default boolean SetPlayerAmmo(int playerid, int weaponid, int ammo) {
        return SAMPFunctions.SetPlayerAmmo(playerid, weaponid, ammo);
    }
    default int GetPlayerAmmo(int playerid) {
        return SAMPFunctions.GetPlayerAmmo(playerid);
    }
    default int GetPlayerWeaponState(int playerid) {
        return SAMPFunctions.GetPlayerWeaponState(playerid);
    }
    default int GetPlayerTargetPlayer(int playerid) {
        return SAMPFunctions.GetPlayerTargetPlayer(playerid);
    }
    default boolean SetPlayerTeam(int playerid, int teamid) {
        return SAMPFunctions.SetPlayerTeam(playerid, teamid);
    }
    default int GetPlayerTeam(int playerid) {
        return SAMPFunctions.GetPlayerTeam(playerid);
    }
    default boolean SetPlayerScore(int playerid, int score) {
        return SAMPFunctions.SetPlayerScore(playerid, score);
    }
    default int GetPlayerScore(int playerid) {
        return SAMPFunctions.GetPlayerScore(playerid);
    }
    default int GetPlayerDrunkLevel(int playerid) {
        return SAMPFunctions.GetPlayerDrunkLevel(playerid);
    }
    default boolean SetPlayerDrunkLevel(int playerid, int level) {
        return SAMPFunctions.SetPlayerDrunkLevel(playerid, level);
    }
    default boolean SetPlayerColor(int playerid, int color) {
        return SAMPFunctions.SetPlayerColor(playerid, color);
    }
    default int GetPlayerColor(int playerid) {
        return SAMPFunctions.GetPlayerColor(playerid);
    }
    default boolean SetPlayerSkin(int playerid, int skinid) {
        return SAMPFunctions.SetPlayerSkin(playerid, skinid);
    }
    default int GetPlayerSkin(int playerid) {
        return SAMPFunctions.GetPlayerSkin(playerid);
    }
    default boolean GivePlayerWeapon(int playerid, int weaponid, int ammo) {
        return SAMPFunctions.GivePlayerWeapon(playerid, weaponid, ammo);
    }
    default boolean ResetPlayerWeapons(int playerid) {
        return SAMPFunctions.ResetPlayerWeapons(playerid);
    }
    default boolean SetPlayerArmedWeapon(int playerid, int weaponid) {
        return SAMPFunctions.SetPlayerArmedWeapon(playerid, weaponid);
    }
    default boolean GivePlayerMoney(int playerid, int money) {
        return SAMPFunctions.GivePlayerMoney(playerid, money);
    }
    default boolean ResetPlayerMoney(int playerid) {
        return SAMPFunctions.ResetPlayerMoney(playerid);
    }
    default int SetPlayerName(int playerid, String name) {
        return SAMPFunctions.SetPlayerName(playerid, name);
    }
    default int GetPlayerMoney(int playerid) {
        return SAMPFunctions.GetPlayerMoney(playerid);
    }
    default int GetPlayerState(int playerid) {
        return SAMPFunctions.GetPlayerState(playerid);
    }
    default int GetPlayerPing(int playerid) {
        return SAMPFunctions.GetPlayerPing(playerid);
    }
    default int GetPlayerWeapon(int playerid) {
        return SAMPFunctions.GetPlayerWeapon(playerid);
    }
    default boolean SetPlayerTime(int playerid, int hour, int minute) {
        return SAMPFunctions.SetPlayerTime(playerid, hour, minute);
    }
    default boolean TogglePlayerClock(int playerid, boolean toggle) {
        return SAMPFunctions.TogglePlayerClock(playerid, toggle);
    }
    default boolean SetPlayerWeather(int playerid, int weather) {
        return SAMPFunctions.SetPlayerWeather(playerid, weather);
    }
    default boolean ForceClassSelection(int playerid) {
        return SAMPFunctions.ForceClassSelection(playerid);
    }
    default boolean SetPlayerWantedLevel(int playerid, int level) {
        return SAMPFunctions.SetPlayerWantedLevel(playerid, level);
    }
    default int GetPlayerWantedLevel(int playerid) {
        return SAMPFunctions.GetPlayerWantedLevel(playerid);
    }
    default boolean SetPlayerFightingStyle(int playerid, int style) {
        return SAMPFunctions.SetPlayerFightingStyle(playerid, style);
    }
    default int GetPlayerFightingStyle(int playerid) {
        return SAMPFunctions.GetPlayerFightingStyle(playerid);
    }
    default boolean SetPlayerVelocity(int playerid, float x, float y, float z) {
        return SAMPFunctions.SetPlayerVelocity(playerid, x, y, z);
    }
    default boolean PlayCrimeReportForPlayer(int playerid, int suspectid, int crime) {
        return SAMPFunctions.PlayCrimeReportForPlayer(playerid, suspectid, crime);
    }
    default boolean PlayAudioStreamForPlayer(int playerid, String url, float posX , float posY , float posZ , float distance , boolean usepos ) {
        return SAMPFunctions.PlayAudioStreamForPlayer(playerid, url, posX , posY , posZ , distance , usepos );
    }
    default boolean StopAudioStreamForPlayer(int playerid) {
        return SAMPFunctions.StopAudioStreamForPlayer(playerid);
    }
    default boolean SetPlayerShopName(int playerid, String shopname) {
        return SAMPFunctions.SetPlayerShopName(playerid, shopname);
    }
    default boolean SetPlayerSkillLevel(int playerid, int skill, int level) {
        return SAMPFunctions.SetPlayerSkillLevel(playerid, skill, level);
    }
    default int GetPlayerSurfingVehicleID(int playerid) {
        return SAMPFunctions.GetPlayerSurfingVehicleID(playerid);
    }
    default int GetPlayerSurfingObjectID(int playerid) {
        return SAMPFunctions.GetPlayerSurfingObjectID(playerid);
    }
    default boolean RemoveBuildingForPlayer(int playerid, int modelid, float fX, float fY, float fZ, float fRadius) {
        return SAMPFunctions.RemoveBuildingForPlayer(playerid, modelid, fX, fY, fZ, fRadius);
    }
    default boolean SetPlayerAttachedObject(int playerid, int index, int modelid, int bone, float fOffsetX , float fOffsetY , float fOffsetZ , float fRotX , float fRotY , float fRotZ , float fScaleX , float fScaleY , float fScaleZ , int materialcolor1 , int materialcolor2 ) {
        return SAMPFunctions.SetPlayerAttachedObject(playerid, index, modelid, bone, fOffsetX , fOffsetY , fOffsetZ , fRotX , fRotY , fRotZ , fScaleX , fScaleY , fScaleZ , materialcolor1 , materialcolor2 );
    }
    default boolean RemovePlayerAttachedObject(int playerid, int index) {
        return SAMPFunctions.RemovePlayerAttachedObject(playerid, index);
    }
    default boolean IsPlayerAttachedObjectSlotUsed(int playerid, int index) {
        return SAMPFunctions.IsPlayerAttachedObjectSlotUsed(playerid, index);
    }
    default boolean EditAttachedObject(int playerid, int index) {
        return SAMPFunctions.EditAttachedObject(playerid, index);
    }
    default int CreatePlayerTextDraw(int playerid, float x, float y, String text) {
        return SAMPFunctions.CreatePlayerTextDraw(playerid, x, y, text);
    }
    default boolean PlayerTextDrawDestroy(int playerid, int text) {
        return SAMPFunctions.PlayerTextDrawDestroy(playerid, text);
    }
    default boolean PlayerTextDrawLetterSize(int playerid, int text, float x, float y) {
        return SAMPFunctions.PlayerTextDrawLetterSize(playerid, text, x, y);
    }
    default boolean PlayerTextDrawTextSize(int playerid, int text, float x, float y) {
        return SAMPFunctions.PlayerTextDrawTextSize(playerid, text, x, y);
    }
    default boolean PlayerTextDrawAlignment(int playerid, int text, int alignment) {
        return SAMPFunctions.PlayerTextDrawAlignment(playerid, text, alignment);
    }
    default boolean PlayerTextDrawColor(int playerid, int text, int color) {
        return SAMPFunctions.PlayerTextDrawColor(playerid, text, color);
    }
    default boolean PlayerTextDrawUseBox(int playerid, int text, boolean use) {
        return SAMPFunctions.PlayerTextDrawUseBox(playerid, text, use);
    }
    default boolean PlayerTextDrawBoxColor(int playerid, int text, int color) {
        return SAMPFunctions.PlayerTextDrawBoxColor(playerid, text, color);
    }
    default boolean PlayerTextDrawSetShadow(int playerid, int text, int size) {
        return SAMPFunctions.PlayerTextDrawSetShadow(playerid, text, size);
    }
    default boolean PlayerTextDrawSetOutline(int playerid, int text, int size) {
        return SAMPFunctions.PlayerTextDrawSetOutline(playerid, text, size);
    }
    default boolean PlayerTextDrawBackgroundColor(int playerid, int text, int color) {
        return SAMPFunctions.PlayerTextDrawBackgroundColor(playerid, text, color);
    }
    default boolean PlayerTextDrawFont(int playerid, int text, int font) {
        return SAMPFunctions.PlayerTextDrawFont(playerid, text, font);
    }
    default boolean PlayerTextDrawSetProportional(int playerid, int text, boolean set) {
        return SAMPFunctions.PlayerTextDrawSetProportional(playerid, text, set);
    }
    default boolean PlayerTextDrawSetSelectable(int playerid, int text, boolean set) {
        return SAMPFunctions.PlayerTextDrawSetSelectable(playerid, text, set);
    }
    default boolean PlayerTextDrawShow(int playerid, int text) {
        return SAMPFunctions.PlayerTextDrawShow(playerid, text);
    }
    default boolean PlayerTextDrawHide(int playerid, int text) {
        return SAMPFunctions.PlayerTextDrawHide(playerid, text);
    }
    default boolean PlayerTextDrawSetString(int playerid, int text, String string) {
        return SAMPFunctions.PlayerTextDrawSetString(playerid, text, string);
    }
    default boolean PlayerTextDrawSetPreviewModel(int playerid, int text, int modelindex) {
        return SAMPFunctions.PlayerTextDrawSetPreviewModel(playerid, text, modelindex);
    }
    default boolean PlayerTextDrawSetPreviewRot(int playerid, int text, float fRotX, float fRotY, float fRotZ, float fZoom ) {
        return SAMPFunctions.PlayerTextDrawSetPreviewRot(playerid, text, fRotX, fRotY, fRotZ, fZoom );
    }
    default boolean PlayerTextDrawSetPreviewVehCol(int playerid, int text, int color1, int color2) {
        return SAMPFunctions.PlayerTextDrawSetPreviewVehCol(playerid, text, color1, color2);
    }
    default boolean SetPVarInt(int playerid, String varname, int value) {
        return SAMPFunctions.SetPVarInt(playerid, varname, value);
    }
    default int GetPVarInt(int playerid, String varname) {
        return SAMPFunctions.GetPVarInt(playerid, varname);
    }
    default boolean SetPVarString(int playerid, String varname, String value) {
        return SAMPFunctions.SetPVarString(playerid, varname, value);
    }
    default boolean SetPVarFloat(int playerid, String varname, float value) {
        return SAMPFunctions.SetPVarFloat(playerid, varname, value);
    }
    default float GetPVarFloat(int playerid, String varname) {
        return SAMPFunctions.GetPVarFloat(playerid, varname);
    }
    default boolean DeletePVar(int playerid, String varname) {
        return SAMPFunctions.DeletePVar(playerid, varname);
    }
    default int GetPVarsUpperIndex(int playerid) {
        return SAMPFunctions.GetPVarsUpperIndex(playerid);
    }
    default int GetPVarType(int playerid, String varname) {
        return SAMPFunctions.GetPVarType(playerid, varname);
    }
    default boolean SetPlayerChatBubble(int playerid, String text, int color, float drawdistance, int expiretime) {
        return SAMPFunctions.SetPlayerChatBubble(playerid, text, color, drawdistance, expiretime);
    }
    default boolean PutPlayerInVehicle(int playerid, int vehicleid, int seatid) {
        return SAMPFunctions.PutPlayerInVehicle(playerid, vehicleid, seatid);
    }
    default int GetPlayerVehicleID(int playerid) {
        return SAMPFunctions.GetPlayerVehicleID(playerid);
    }
    default int GetPlayerVehicleSeat(int playerid) {
        return SAMPFunctions.GetPlayerVehicleSeat(playerid);
    }
    default boolean RemovePlayerFromVehicle(int playerid) {
        return SAMPFunctions.RemovePlayerFromVehicle(playerid);
    }
    default boolean TogglePlayerControllable(int playerid, boolean toggle) {
        return SAMPFunctions.TogglePlayerControllable(playerid, toggle);
    }
    default boolean PlayerPlaySound(int playerid, int soundid, float x, float y, float z) {
        return SAMPFunctions.PlayerPlaySound(playerid, soundid, x, y, z);
    }
    default boolean ApplyAnimation(int playerid, String animlib, String animname, float fDelta, boolean loop, boolean lockx, boolean locky, boolean freeze, int time, boolean forcesync ) {
        return SAMPFunctions.ApplyAnimation(playerid, animlib, animname, fDelta, loop, lockx, locky, freeze, time, forcesync );
    }
    default boolean ClearAnimations(int playerid, boolean forcesync ) {
        return SAMPFunctions.ClearAnimations(playerid, forcesync );
    }
    default int GetPlayerAnimationIndex(int playerid) {
        return SAMPFunctions.GetPlayerAnimationIndex(playerid);
    }
    default int GetPlayerSpecialAction(int playerid) {
        return SAMPFunctions.GetPlayerSpecialAction(playerid);
    }
    default boolean SetPlayerSpecialAction(int playerid, int actionid) {
        return SAMPFunctions.SetPlayerSpecialAction(playerid, actionid);
    }
    default boolean SetPlayerCheckpoint(int playerid, float x, float y, float z, float size) {
        return SAMPFunctions.SetPlayerCheckpoint(playerid, x, y, z, size);
    }
    default boolean DisablePlayerCheckpoint(int playerid) {
        return SAMPFunctions.DisablePlayerCheckpoint(playerid);
    }
    default boolean SetPlayerRaceCheckpoint(int playerid, int type, float x, float y, float z, float nextx, float nexty, float nextz, float size) {
        return SAMPFunctions.SetPlayerRaceCheckpoint(playerid, type, x, y, z, nextx, nexty, nextz, size);
    }
    default boolean DisablePlayerRaceCheckpoint(int playerid) {
        return SAMPFunctions.DisablePlayerRaceCheckpoint(playerid);
    }
    default boolean SetPlayerWorldBounds(int playerid, float x_max, float x_min, float y_max, float y_min) {
        return SAMPFunctions.SetPlayerWorldBounds(playerid, x_max, x_min, y_max, y_min);
    }
    default boolean SetPlayerMarkerForPlayer(int playerid, int showplayerid, int color) {
        return SAMPFunctions.SetPlayerMarkerForPlayer(playerid, showplayerid, color);
    }
    default boolean ShowPlayerNameTagForPlayer(int playerid, int showplayerid, boolean show) {
        return SAMPFunctions.ShowPlayerNameTagForPlayer(playerid, showplayerid, show);
    }
    default boolean SetPlayerMapIcon(int playerid, int iconid, float x, float y, float z, int markertype, int color, int style ) {
        return SAMPFunctions.SetPlayerMapIcon(playerid, iconid, x, y, z, markertype, color, style );
    }
    default boolean RemovePlayerMapIcon(int playerid, int iconid) {
        return SAMPFunctions.RemovePlayerMapIcon(playerid, iconid);
    }
    default boolean AllowPlayerTeleport(int playerid, boolean allow) {
        return SAMPFunctions.AllowPlayerTeleport(playerid, allow);
    }
    default boolean SetPlayerCameraPos(int playerid, float x, float y, float z) {
        return SAMPFunctions.SetPlayerCameraPos(playerid, x, y, z);
    }
    default boolean SetPlayerCameraLookAt(int playerid, float x, float y, float z, int cut ) {
        return SAMPFunctions.SetPlayerCameraLookAt(playerid, x, y, z, cut );
    }
    default boolean SetCameraBehindPlayer(int playerid) {
        return SAMPFunctions.SetCameraBehindPlayer(playerid);
    }
    default int GetPlayerCameraMode(int playerid) {
        return SAMPFunctions.GetPlayerCameraMode(playerid);
    }
    default float GetPlayerCameraAspectRatio(int playerid) {
        return SAMPFunctions.GetPlayerCameraAspectRatio(playerid);
    }
    default float GetPlayerCameraZoom(int playerid) {
        return SAMPFunctions.GetPlayerCameraZoom(playerid);
    }
    default boolean AttachCameraToObject(int playerid, int objectid) {
        return SAMPFunctions.AttachCameraToObject(playerid, objectid);
    }
    default boolean AttachCameraToPlayerObject(int playerid, int playerobjectid) {
        return SAMPFunctions.AttachCameraToPlayerObject(playerid, playerobjectid);
    }
    default boolean InterpolateCameraPos(int playerid, float FromX, float FromY, float FromZ, float ToX, float ToY, float ToZ, int time, int cut ) {
        return SAMPFunctions.InterpolateCameraPos(playerid, FromX, FromY, FromZ, ToX, ToY, ToZ, time, cut );
    }
    default boolean InterpolateCameraLookAt(int playerid, float FromX, float FromY, float FromZ, float ToX, float ToY, float ToZ, int time, int cut ) {
        return SAMPFunctions.InterpolateCameraLookAt(playerid, FromX, FromY, FromZ, ToX, ToY, ToZ, time, cut );
    }
    default boolean IsPlayerConnected(int playerid) {
        return SAMPFunctions.IsPlayerConnected(playerid);
    }
    default boolean IsPlayerInVehicle(int playerid, int vehicleid) {
        return SAMPFunctions.IsPlayerInVehicle(playerid, vehicleid);
    }
    default boolean IsPlayerInAnyVehicle(int playerid) {
        return SAMPFunctions.IsPlayerInAnyVehicle(playerid);
    }
    default boolean IsPlayerInCheckpoint(int playerid) {
        return SAMPFunctions.IsPlayerInCheckpoint(playerid);
    }
    default boolean IsPlayerInRaceCheckpoint(int playerid) {
        return SAMPFunctions.IsPlayerInRaceCheckpoint(playerid);
    }
    default boolean SetPlayerVirtualWorld(int playerid, int worldid) {
        return SAMPFunctions.SetPlayerVirtualWorld(playerid, worldid);
    }
    default int GetPlayerVirtualWorld(int playerid) {
        return SAMPFunctions.GetPlayerVirtualWorld(playerid);
    }
    default boolean EnableStuntBonusForPlayer(int playerid, boolean enable) {
        return SAMPFunctions.EnableStuntBonusForPlayer(playerid, enable);
    }
    default boolean EnableStuntBonusForAll(boolean enable) {
        return SAMPFunctions.EnableStuntBonusForAll(enable);
    }
    default boolean TogglePlayerSpectating(int playerid, boolean toggle) {
        return SAMPFunctions.TogglePlayerSpectating(playerid, toggle);
    }
    default boolean PlayerSpectatePlayer(int playerid, int targetplayerid, int mode ) {
        return SAMPFunctions.PlayerSpectatePlayer(playerid, targetplayerid, mode );
    }
    default boolean PlayerSpectateVehicle(int playerid, int targetvehicleid, int mode ) {
        return SAMPFunctions.PlayerSpectateVehicle(playerid, targetvehicleid, mode );
    }
    default boolean StartRecordingPlayerData(int playerid, int recordtype, String recordname) {
        return SAMPFunctions.StartRecordingPlayerData(playerid, recordtype, recordname);
    }
    default boolean StopRecordingPlayerData(int playerid) {
        return SAMPFunctions.StopRecordingPlayerData(playerid);
    }
    default boolean CreateExplosionForPlayer(int playerid, float X, float Y, float Z, int type, float Radius) {
        return SAMPFunctions.CreateExplosionForPlayer(playerid, X, Y, Z, type, Radius);
    }
    default float[] GetPlayerPos(int playerid) {
        return SAMPFunctions.GetPlayerPos(playerid);
    }
    default float GetPlayerFacingAngle(int playerid) {
        return SAMPFunctions.GetPlayerFacingAngle(playerid);
    }
    default float GetPlayerHealth(int playerid) {
        return SAMPFunctions.GetPlayerHealth(playerid);
    }
    default float GetPlayerArmour(int playerid) {
        return SAMPFunctions.GetPlayerArmour(playerid);
    }
    default int GetPlayerWeaponSlot(int playerid, int slot) {
        return SAMPFunctions.GetPlayerWeaponSlot(playerid, slot);
    }
    default int GetPlayerAmmoSlot(int playerid, int slot) {
        return SAMPFunctions.GetPlayerAmmoSlot(playerid, slot);
    }
    default String GetPlayerIp(int playerid) {
        return SAMPFunctions.GetPlayerIp(playerid);
    }
    default int[] GetPlayerKeys(int playerid) {
        return SAMPFunctions.GetPlayerKeys(playerid);
    }
    default String GetPlayerName(int playerid) {
        return SAMPFunctions.GetPlayerName(playerid);
    }
    default int GetPlayerHour(int playerid) {
        return SAMPFunctions.GetPlayerHour(playerid);
    }
    default int GetPlayerTime(int playerid) {
        return SAMPFunctions.GetPlayerTime(playerid);
    }
    default float[] GetPlayerVelocity(int playerid) {
        return SAMPFunctions.GetPlayerVelocity(playerid);
    }
    default float[] GetPlayerLastShotVectors(int playerid) {
        return SAMPFunctions.GetPlayerLastShotVectors(playerid);
    }
    default String GetPVarString(int playerid, String varname) {
        return SAMPFunctions.GetPVarString(playerid, varname);
    }
    default String GetPVarNameAtIndex(int playerid, int index) {
        return SAMPFunctions.GetPVarNameAtIndex(playerid, index);
    }
    default String GetAnimationLibrary(int index) {
        return SAMPFunctions.GetAnimationLibrary(index);
    }
    default String GetAnimationName(int index) {
        return SAMPFunctions.GetAnimationName(index);
    }
    default float[] GetPlayerCameraPos(int playerid) {
        return SAMPFunctions.GetPlayerCameraPos(playerid);
    }
    default float[] GetPlayerCameraFrontVector(int playerid) {
        return SAMPFunctions.GetPlayerCameraFrontVector(playerid);
    }

    /* a_objects */
    default int CreateObject(int modelid, float x, float y, float z, float rX, float rY, float rZ, float DrawDistance ) {
        return SAMPFunctions.CreateObject(modelid, x, y, z, rX, rY, rZ, DrawDistance );
    }
    default boolean AttachObjectToVehicle(int objectid, int vehicleid, float fOffsetX, float fOffsetY, float fOffsetZ, float fRotX, float fRotY, float fRotZ) {
        return SAMPFunctions.AttachObjectToVehicle(objectid, vehicleid, fOffsetX, fOffsetY, fOffsetZ, fRotX, fRotY, fRotZ);
    }
    default boolean AttachObjectToObject(int objectid, int attachtoid, float fOffsetX, float fOffsetY, float fOffsetZ, float fRotX, float fRotY, float fRotZ, boolean SyncRotation ) {
        return SAMPFunctions.AttachObjectToObject(objectid, attachtoid, fOffsetX, fOffsetY, fOffsetZ, fRotX, fRotY, fRotZ, SyncRotation );
    }
    default boolean AttachObjectToPlayer(int objectid, int playerid, float fOffsetX, float fOffsetY, float fOffsetZ, float fRotX, float fRotY, float fRotZ) {
        return SAMPFunctions.AttachObjectToPlayer(objectid, playerid, fOffsetX, fOffsetY, fOffsetZ, fRotX, fRotY, fRotZ);
    }
    default boolean SetObjectPos(int objectid, float x, float y, float z) {
        return SAMPFunctions.SetObjectPos(objectid, x, y, z);
    }
    default boolean SetObjectRot(int objectid, float rotX, float rotY, float rotZ) {
        return SAMPFunctions.SetObjectRot(objectid, rotX, rotY, rotZ);
    }
    default boolean IsValidObject(int objectid) {
        return SAMPFunctions.IsValidObject(objectid);
    }
    default boolean DestroyObject(int objectid) {
        return SAMPFunctions.DestroyObject(objectid);
    }
    default int MoveObject(int objectid, float X, float Y, float Z, float Speed, float RotX , float RotY , float RotZ ) {
        return SAMPFunctions.MoveObject(objectid, X, Y, Z, Speed, RotX , RotY , RotZ );
    }
    default boolean StopObject(int objectid) {
        return SAMPFunctions.StopObject(objectid);
    }
    default boolean IsObjectMoving(int objectid) {
        return SAMPFunctions.IsObjectMoving(objectid);
    }
    default boolean EditObject(int playerid, int objectid) {
        return SAMPFunctions.EditObject(playerid, objectid);
    }
    default boolean EditPlayerObject(int playerid, int objectid) {
        return SAMPFunctions.EditPlayerObject(playerid, objectid);
    }
    default boolean SelectObject(int playerid) {
        return SAMPFunctions.SelectObject(playerid);
    }
    default boolean CancelEdit(int playerid) {
        return SAMPFunctions.CancelEdit(playerid);
    }
    default int CreatePlayerObject(int playerid, int modelid, float x, float y, float z, float rX, float rY, float rZ, float DrawDistance ) {
        return SAMPFunctions.CreatePlayerObject(playerid, modelid, x, y, z, rX, rY, rZ, DrawDistance );
    }
    default boolean AttachPlayerObjectToPlayer(int objectplayer, int objectid, int attachplayer, float OffsetX, float OffsetY, float OffsetZ, float rX, float rY, float rZ) {
        return SAMPFunctions.AttachPlayerObjectToPlayer(objectplayer, objectid, attachplayer, OffsetX, OffsetY, OffsetZ, rX, rY, rZ);
    }
    default boolean AttachPlayerObjectToVehicle(int playerid, int objectid, int vehicleid, float fOffsetX, float fOffsetY, float fOffsetZ, float fRotX, float fRotY, float RotZ) {
        return SAMPFunctions.AttachPlayerObjectToVehicle(playerid, objectid, vehicleid, fOffsetX, fOffsetY, fOffsetZ, fRotX, fRotY, RotZ);
    }
    default boolean SetPlayerObjectPos(int playerid, int objectid, float x, float y, float z) {
        return SAMPFunctions.SetPlayerObjectPos(playerid, objectid, x, y, z);
    }
    default boolean SetPlayerObjectRot(int playerid, int objectid, float rotX, float rotY, float rotZ) {
        return SAMPFunctions.SetPlayerObjectRot(playerid, objectid, rotX, rotY, rotZ);
    }
    default boolean IsValidPlayerObject(int playerid, int objectid) {
        return SAMPFunctions.IsValidPlayerObject(playerid, objectid);
    }
    default boolean DestroyPlayerObject(int playerid, int objectid) {
        return SAMPFunctions.DestroyPlayerObject(playerid, objectid);
    }
    default int MovePlayerObject(int playerid, int objectid, float x, float y, float z, float Speed, float RotX , float RotY , float RotZ ) {
        return SAMPFunctions.MovePlayerObject(playerid, objectid, x, y, z, Speed, RotX , RotY , RotZ );
    }
    default boolean StopPlayerObject(int playerid, int objectid) {
        return SAMPFunctions.StopPlayerObject(playerid, objectid);
    }
    default boolean IsPlayerObjectMoving(int playerid, int objectid) {
        return SAMPFunctions.IsPlayerObjectMoving(playerid, objectid);
    }
    default boolean SetObjectMaterial(int objectid, int materialindex, int modelid, String txdname, String texturename, int materialcolor ) {
        return SAMPFunctions.SetObjectMaterial(objectid, materialindex, modelid, txdname, texturename, materialcolor );
    }
    default boolean SetPlayerObjectMaterial(int playerid, int objectid, int materialindex, int modelid, String txdname, String texturename, int materialcolor ) {
        return SAMPFunctions.SetPlayerObjectMaterial(playerid, objectid, materialindex, modelid, txdname, texturename, materialcolor );
    }
    default boolean SetObjectMaterialText(int objectid, String text, int materialindex , int materialsize , String fontface , int fontsize , boolean bold , int fontcolor , int backcolor , int textalignment ) {
        return SAMPFunctions.SetObjectMaterialText(objectid, text, materialindex , materialsize , fontface , fontsize , bold , fontcolor , backcolor , textalignment );
    }
    default boolean SetPlayerObjectMaterialText(int playerid, int objectid, String text, int materialindex , int materialsize , String fontface , int fontsize , boolean bold , int fontcolor , int backcolor , int textalignment ) {
        return SAMPFunctions.SetPlayerObjectMaterialText(playerid, objectid, text, materialindex , materialsize , fontface , fontsize , bold , fontcolor , backcolor , textalignment );
    }
    default float[] GetObjectPos(int objectid) {
        return SAMPFunctions.GetObjectPos(objectid);
    }
    default float[] GetObjectRot(int objectid) {
        return SAMPFunctions.GetObjectRot(objectid);
    }
    default float[] GetPlayerObjectPos(int playerid, int objectid) {
        return SAMPFunctions.GetPlayerObjectPos(playerid, objectid);
    }
    default float[] GetPlayerObjectRot(int playerid, int objectid) {
        return SAMPFunctions.GetPlayerObjectRot(playerid, objectid);
    }

    /* a_vehicles */
    default boolean IsValidVehicle(int vehicleid) {
        return SAMPFunctions.IsValidVehicle(vehicleid);
    }
    default float GetVehicleDistanceFromPoint(int vehicleid, float x, float y, float z) {
        return SAMPFunctions.GetVehicleDistanceFromPoint(vehicleid, x, y, z);
    }
    default int CreateVehicle(int vehicletype, float x, float y, float z, float rotation, int color1, int color2, int respawn_delay, boolean addsiren) {
        return SAMPFunctions.CreateVehicle(vehicletype, x, y, z, rotation, color1, color2, respawn_delay, addsiren);
    }
    default boolean DestroyVehicle(int vehicleid) {
        return SAMPFunctions.DestroyVehicle(vehicleid);
    }
    default boolean IsVehicleStreamedIn(int vehicleid, int forplayerid) {
        return SAMPFunctions.IsVehicleStreamedIn(vehicleid, forplayerid);
    }
    default boolean SetVehiclePos(int vehicleid, float x, float y, float z) {
        return SAMPFunctions.SetVehiclePos(vehicleid, x, y, z);
    }
    default boolean SetVehicleZAngle(int vehicleid, float z_angle) {
        return SAMPFunctions.SetVehicleZAngle(vehicleid, z_angle);
    }
    default boolean SetVehicleParamsForPlayer(int vehicleid, int playerid, boolean objective, boolean doorslocked) {
        return SAMPFunctions.SetVehicleParamsForPlayer(vehicleid, playerid, objective, doorslocked);
    }
    default boolean ManualVehicleEngineAndLights() {
        return SAMPFunctions.ManualVehicleEngineAndLights();
    }
    default boolean SetVehicleParamsEx(int vehicleid, boolean engine, boolean lights, boolean alarm, boolean doors, boolean bonnet, boolean boot, boolean objective) {
        return SAMPFunctions.SetVehicleParamsEx(vehicleid, engine, lights, alarm, doors, bonnet, boot, objective);
    }
    default boolean SetVehicleToRespawn(int vehicleid) {
        return SAMPFunctions.SetVehicleToRespawn(vehicleid);
    }
    default boolean LinkVehicleToInterior(int vehicleid, int interiorid) {
        return SAMPFunctions.LinkVehicleToInterior(vehicleid, interiorid);
    }
    default boolean AddVehicleComponent(int vehicleid, int componentid) {
        return SAMPFunctions.AddVehicleComponent(vehicleid, componentid);
    }
    default boolean RemoveVehicleComponent(int vehicleid, int componentid) {
        return SAMPFunctions.RemoveVehicleComponent(vehicleid, componentid);
    }
    default boolean ChangeVehicleColor(int vehicleid, int color1, int color2) {
        return SAMPFunctions.ChangeVehicleColor(vehicleid, color1, color2);
    }
    default boolean ChangeVehiclePaintjob(int vehicleid, int paintjobid) {
        return SAMPFunctions.ChangeVehiclePaintjob(vehicleid, paintjobid);
    }
    default boolean SetVehicleHealth(int vehicleid, float health) {
        return SAMPFunctions.SetVehicleHealth(vehicleid, health);
    }
    default boolean AttachTrailerToVehicle(int trailerid, int vehicleid) {
        return SAMPFunctions.AttachTrailerToVehicle(trailerid, vehicleid);
    }
    default boolean DetachTrailerFromVehicle(int vehicleid) {
        return SAMPFunctions.DetachTrailerFromVehicle(vehicleid);
    }
    default boolean IsTrailerAttachedToVehicle(int vehicleid) {
        return SAMPFunctions.IsTrailerAttachedToVehicle(vehicleid);
    }
    default int GetVehicleTrailer(int vehicleid) {
        return SAMPFunctions.GetVehicleTrailer(vehicleid);
    }
    default boolean SetVehicleNumberPlate(int vehicleid, String numberplate) {
        return SAMPFunctions.SetVehicleNumberPlate(vehicleid, numberplate);
    }
    default int GetVehicleModel(int vehicleid) {
        return SAMPFunctions.GetVehicleModel(vehicleid);
    }
    default int GetVehicleComponentInSlot(int vehicleid, int slot) {
        return SAMPFunctions.GetVehicleComponentInSlot(vehicleid, slot);
    }
    default int GetVehicleComponentType(int component) {
        return SAMPFunctions.GetVehicleComponentType(component);
    }
    default boolean RepairVehicle(int vehicleid) {
        return SAMPFunctions.RepairVehicle(vehicleid);
    }
    default boolean SetVehicleVelocity(int vehicleid, float X, float Y, float Z) {
        return SAMPFunctions.SetVehicleVelocity(vehicleid, X, Y, Z);
    }
    default boolean SetVehicleAngularVelocity(int vehicleid, float X, float Y, float Z) {
        return SAMPFunctions.SetVehicleAngularVelocity(vehicleid, X, Y, Z);
    }
    default boolean UpdateVehicleDamageStatus(int vehicleid, int panels, int doors, int lights, int tires) {
        return SAMPFunctions.UpdateVehicleDamageStatus(vehicleid, panels, doors, lights, tires);
    }
    default boolean SetVehicleVirtualWorld(int vehicleid, int worldid) {
        return SAMPFunctions.SetVehicleVirtualWorld(vehicleid, worldid);
    }
    default int GetVehicleVirtualWorld(int vehicleid) {
        return SAMPFunctions.GetVehicleVirtualWorld(vehicleid);
    }
    default float[] GetVehiclePos(int vehicleid) {
        return SAMPFunctions.GetVehiclePos(vehicleid);
    }
    default float GetVehicleZAngle(int vehicleid) {
        return SAMPFunctions.GetVehicleZAngle(vehicleid);
    }
    default float[] GetVehicleRotationQuat(int vehicleid) {
        return SAMPFunctions.GetVehicleRotationQuat(vehicleid);
    }
    default int[] GetVehicleParamsEx(int vehicleid) {
        return SAMPFunctions.GetVehicleParamsEx(vehicleid);
    }
    default float GetVehicleHealth(int vehicleid) {
        return SAMPFunctions.GetVehicleHealth(vehicleid);
    }
    default float[] GetVehicleVelocity(int vehicleid) {
        return SAMPFunctions.GetVehicleVelocity(vehicleid);
    }
    default int[] GetVehicleDamageStatus(int vehicleid) {
        return SAMPFunctions.GetVehicleDamageStatus(vehicleid);
    }
    default float[] GetVehicleModelInfo(int vehicleid, int infotype) {
        return SAMPFunctions.GetVehicleModelInfo(vehicleid, infotype);
    }

    /* a_samp */
    default boolean SendClientMessage(int playerid, int color, String message) {
        return SAMPFunctions.SendClientMessage(playerid, color, message);
    }
    default boolean SendClientMessageToAll(int color, String message) {
        return SAMPFunctions.SendClientMessageToAll(color, message);
    }
    default boolean SendPlayerMessageToPlayer(int playerid, int senderid, String message) {
        return SAMPFunctions.SendPlayerMessageToPlayer(playerid, senderid, message);
    }
    default boolean SendPlayerMessageToAll(int senderid, String message) {
        return SAMPFunctions.SendPlayerMessageToAll(senderid, message);
    }
    default boolean SendDeathMessage(int killer, int killee, int weapon) {
        return SAMPFunctions.SendDeathMessage(killer, killee, weapon);
    }
    default boolean SendDeathMessageToPlayer(int playerid, int killer, int killee, int weapon) {
        return SAMPFunctions.SendDeathMessageToPlayer(playerid, killer, killee, weapon);
    }
    default boolean GameTextForAll(String text, int time, int style) {
        return SAMPFunctions.GameTextForAll(text, time, style);
    }
    default boolean GameTextForPlayer(int playerid, String text, int time, int style) {
        return SAMPFunctions.GameTextForPlayer(playerid, text, time, style);
    }
    default int GetTickCount() {
        return SAMPFunctions.GetTickCount();
    }
    default int GetMaxPlayers() {
        return SAMPFunctions.GetMaxPlayers();
    }
    default float VectorSize(float x, float y, float z) {
        return SAMPFunctions.VectorSize(x, y, z);
    }
    default boolean SetGameModeText(String text) {
        return SAMPFunctions.SetGameModeText(text);
    }
    default boolean SetTeamCount(int count) {
        return SAMPFunctions.SetTeamCount(count);
    }
    default int AddPlayerClass(int modelid, float spawn_x, float spawn_y, float spawn_z, float z_angle, int weapon1, int weapon1_ammo, int weapon2, int weapon2_ammo, int weapon3, int weapon3_ammo) {
        return SAMPFunctions.AddPlayerClass(modelid, spawn_x, spawn_y, spawn_z, z_angle, weapon1, weapon1_ammo, weapon2, weapon2_ammo, weapon3, weapon3_ammo);
    }
    default int AddPlayerClassEx(int teamid, int modelid, float spawn_x, float spawn_y, float spawn_z, float z_angle, int weapon1, int weapon1_ammo, int weapon2, int weapon2_ammo, int weapon3, int weapon3_ammo) {
        return SAMPFunctions.AddPlayerClassEx(teamid, modelid, spawn_x, spawn_y, spawn_z, z_angle, weapon1, weapon1_ammo, weapon2, weapon2_ammo, weapon3, weapon3_ammo);
    }
    default int AddStaticVehicle(int modelid, float spawn_x, float spawn_y, float spawn_z, float z_angle, int color1, int color2) {
        return SAMPFunctions.AddStaticVehicle(modelid, spawn_x, spawn_y, spawn_z, z_angle, color1, color2);
    }
    default int AddStaticVehicleEx(int modelid, float spawn_x, float spawn_y, float spawn_z, float z_angle, int color1, int color2, int respawn_delay, boolean addsiren) {
        return SAMPFunctions.AddStaticVehicleEx(modelid, spawn_x, spawn_y, spawn_z, z_angle, color1, color2, respawn_delay, addsiren);
    }
    default int AddStaticPickup(int model, int type, float x, float y, float z, int virtualworld ) {
        return SAMPFunctions.AddStaticPickup(model, type, x, y, z, virtualworld );
    }
    default int CreatePickup(int model, int type, float x, float y, float z, int virtualworld ) {
        return SAMPFunctions.CreatePickup(model, type, x, y, z, virtualworld );
    }
    default boolean DestroyPickup(int pickup) {
        return SAMPFunctions.DestroyPickup(pickup);
    }
    default boolean ShowNameTags(boolean show) {
        return SAMPFunctions.ShowNameTags(show);
    }
    default boolean ShowPlayerMarkers(int mode) {
        return SAMPFunctions.ShowPlayerMarkers(mode);
    }
    default boolean GameModeExit() {
        return SAMPFunctions.GameModeExit();
    }
    default boolean SetWorldTime(int hour) {
        return SAMPFunctions.SetWorldTime(hour);
    }
    default boolean EnableTirePopping(boolean enable) {
        return SAMPFunctions.EnableTirePopping(enable);
    }
    default boolean EnableVehicleFriendlyFire() {
        return SAMPFunctions.EnableVehicleFriendlyFire();
    }
    default boolean AllowInteriorWeapons(boolean allow) {
        return SAMPFunctions.AllowInteriorWeapons(allow);
    }
    default boolean SetWeather(int weatherid) {
        return SAMPFunctions.SetWeather(weatherid);
    }
    default boolean SetGravity(float gravity) {
        return SAMPFunctions.SetGravity(gravity);
    }
    default boolean AllowAdminTeleport(boolean allow) {
        return SAMPFunctions.AllowAdminTeleport(allow);
    }
    default boolean SetDeathDropAmount(int amount) {
        return SAMPFunctions.SetDeathDropAmount(amount);
    }
    default boolean CreateExplosion(float x, float y, float z, int type, float radius) {
        return SAMPFunctions.CreateExplosion(x, y, z, type, radius);
    }
    default boolean EnableZoneNames(boolean enable) {
        return SAMPFunctions.EnableZoneNames(enable);
    }
    default boolean UsePlayerPedAnims() {
        return SAMPFunctions.UsePlayerPedAnims();
    }
    default boolean DisableInteriorEnterExits() {
        return SAMPFunctions.DisableInteriorEnterExits();
    }
    default boolean SetNameTagDrawDistance(float distance) {
        return SAMPFunctions.SetNameTagDrawDistance(distance);
    }
    default boolean DisableNameTagLOS() {
        return SAMPFunctions.DisableNameTagLOS();
    }
    default boolean LimitGlobalChatRadius(float chat_radius) {
        return SAMPFunctions.LimitGlobalChatRadius(chat_radius);
    }
    default boolean LimitPlayerMarkerRadius(float marker_radius) {
        return SAMPFunctions.LimitPlayerMarkerRadius(marker_radius);
    }
    default boolean ConnectNPC(String name, String script) {
        return SAMPFunctions.ConnectNPC(name, script);
    }
    default boolean IsPlayerNPC(int playerid) {
        return SAMPFunctions.IsPlayerNPC(playerid);
    }
    default boolean IsPlayerAdmin(int playerid) {
        return SAMPFunctions.IsPlayerAdmin(playerid);
    }
    default boolean Kick(int playerid) {
        return SAMPFunctions.Kick(playerid);
    }
    default boolean Ban(int playerid) {
        return SAMPFunctions.Ban(playerid);
    }
    default boolean BanEx(int playerid, String reason) {
        return SAMPFunctions.BanEx(playerid, reason);
    }
    default boolean SendRconCommand(String command) {
        return SAMPFunctions.SendRconCommand(command);
    }
    default int GetServerVarAsInt(String varname) {
        return SAMPFunctions.GetServerVarAsInt(varname);
    }
    default boolean GetServerVarAsBool(String varname) {
        return SAMPFunctions.GetServerVarAsBool(varname);
    }
    default boolean BlockIpAddress(String ip_address, int timems) {
        return SAMPFunctions.BlockIpAddress(ip_address, timems);
    }
    default boolean UnBlockIpAddress(String ip_address) {
        return SAMPFunctions.UnBlockIpAddress(ip_address);
    }
    default int GetServerTickRate() {
        return SAMPFunctions.GetServerTickRate();
    }
    default int NetStats_GetConnectedTime(int playerid) {
        return SAMPFunctions.NetStats_GetConnectedTime(playerid);
    }
    default int NetStats_MessagesReceived(int playerid) {
        return SAMPFunctions.NetStats_MessagesReceived(playerid);
    }
    default int NetStats_BytesReceived(int playerid) {
        return SAMPFunctions.NetStats_BytesReceived(playerid);
    }
    default int NetStats_MessagesSent(int playerid) {
        return SAMPFunctions.NetStats_MessagesSent(playerid);
    }
    default int NetStats_BytesSent(int playerid) {
        return SAMPFunctions.NetStats_BytesSent(playerid);
    }
    default int NetStats_MessagesRecvPerSecond(int playerid) {
        return SAMPFunctions.NetStats_MessagesRecvPerSecond(playerid);
    }
    default float NetStats_PacketLossPercent(int playerid) {
        return SAMPFunctions.NetStats_PacketLossPercent(playerid);
    }
    default int NetStats_ConnectionStatus(int playerid) {
        return SAMPFunctions.NetStats_ConnectionStatus(playerid);
    }
    default int CreateMenu(String title, int columns, float x, float y, float col1width, float col2width ) {
        return SAMPFunctions.CreateMenu(title, columns, x, y, col1width, col2width );
    }
    default boolean DestroyMenu(int menuid) {
        return SAMPFunctions.DestroyMenu(menuid);
    }
    default int AddMenuItem(int menuid, int column, String menutext) {
        return SAMPFunctions.AddMenuItem(menuid, column, menutext);
    }
    default boolean SetMenuColumnHeader(int menuid, int column, String columnheader) {
        return SAMPFunctions.SetMenuColumnHeader(menuid, column, columnheader);
    }
    default boolean ShowMenuForPlayer(int menuid, int playerid) {
        return SAMPFunctions.ShowMenuForPlayer(menuid, playerid);
    }
    default boolean HideMenuForPlayer(int menuid, int playerid) {
        return SAMPFunctions.HideMenuForPlayer(menuid, playerid);
    }
    default boolean IsValidMenu(int menuid) {
        return SAMPFunctions.IsValidMenu(menuid);
    }
    default boolean DisableMenu(int menuid) {
        return SAMPFunctions.DisableMenu(menuid);
    }
    default boolean DisableMenuRow(int menuid, int row) {
        return SAMPFunctions.DisableMenuRow(menuid, row);
    }
    default int GetPlayerMenu(int playerid) {
        return SAMPFunctions.GetPlayerMenu(playerid);
    }
    default int TextDrawCreate(float x, float y, String text) {
        return SAMPFunctions.TextDrawCreate(x, y, text);
    }
    default boolean TextDrawDestroy(int text) {
        return SAMPFunctions.TextDrawDestroy(text);
    }
    default boolean TextDrawLetterSize(int text, float x, float y) {
        return SAMPFunctions.TextDrawLetterSize(text, x, y);
    }
    default boolean TextDrawTextSize(int text, float x, float y) {
        return SAMPFunctions.TextDrawTextSize(text, x, y);
    }
    default boolean TextDrawAlignment(int text, int alignment) {
        return SAMPFunctions.TextDrawAlignment(text, alignment);
    }
    default boolean TextDrawColor(int text, int color) {
        return SAMPFunctions.TextDrawColor(text, color);
    }
    default boolean TextDrawUseBox(int text, boolean use) {
        return SAMPFunctions.TextDrawUseBox(text, use);
    }
    default boolean TextDrawBoxColor(int text, int color) {
        return SAMPFunctions.TextDrawBoxColor(text, color);
    }
    default boolean TextDrawSetShadow(int text, int size) {
        return SAMPFunctions.TextDrawSetShadow(text, size);
    }
    default boolean TextDrawSetOutline(int text, int size) {
        return SAMPFunctions.TextDrawSetOutline(text, size);
    }
    default boolean TextDrawBackgroundColor(int text, int color) {
        return SAMPFunctions.TextDrawBackgroundColor(text, color);
    }
    default boolean TextDrawFont(int text, int font) {
        return SAMPFunctions.TextDrawFont(text, font);
    }
    default boolean TextDrawSetProportional(int text, boolean set) {
        return SAMPFunctions.TextDrawSetProportional(text, set);
    }
    default boolean TextDrawSetSelectable(int text, boolean set) {
        return SAMPFunctions.TextDrawSetSelectable(text, set);
    }
    default boolean TextDrawShowForPlayer(int playerid, int text) {
        return SAMPFunctions.TextDrawShowForPlayer(playerid, text);
    }
    default boolean TextDrawHideForPlayer(int playerid, int text) {
        return SAMPFunctions.TextDrawHideForPlayer(playerid, text);
    }
    default boolean TextDrawShowForAll(int text) {
        return SAMPFunctions.TextDrawShowForAll(text);
    }
    default boolean TextDrawHideForAll(int text) {
        return SAMPFunctions.TextDrawHideForAll(text);
    }
    default boolean TextDrawSetString(int text, String string) {
        return SAMPFunctions.TextDrawSetString(text, string);
    }
    default boolean TextDrawSetPreviewModel(int text, int modelindex) {
        return SAMPFunctions.TextDrawSetPreviewModel(text, modelindex);
    }
    default boolean TextDrawSetPreviewRot(int text, float fRotX, float fRotY, float fRotZ, float fZoom ) {
        return SAMPFunctions.TextDrawSetPreviewRot(text, fRotX, fRotY, fRotZ, fZoom );
    }
    default boolean TextDrawSetPreviewVehCol(int text, int color1, int color2) {
        return SAMPFunctions.TextDrawSetPreviewVehCol(text, color1, color2);
    }
    default boolean SelectTextDraw(int playerid, int hovercolor) {
        return SAMPFunctions.SelectTextDraw(playerid, hovercolor);
    }
    default boolean CancelSelectTextDraw(int playerid) {
        return SAMPFunctions.CancelSelectTextDraw(playerid);
    }
    default int GangZoneCreate(float minx, float miny, float maxx, float maxy) {
        return SAMPFunctions.GangZoneCreate(minx, miny, maxx, maxy);
    }
    default boolean GangZoneDestroy(int zone) {
        return SAMPFunctions.GangZoneDestroy(zone);
    }
    default boolean GangZoneShowForPlayer(int playerid, int zone, int color) {
        return SAMPFunctions.GangZoneShowForPlayer(playerid, zone, color);
    }
    default boolean GangZoneShowForAll(int zone, int color) {
        return SAMPFunctions.GangZoneShowForAll(zone, color);
    }
    default boolean GangZoneHideForPlayer(int playerid, int zone) {
        return SAMPFunctions.GangZoneHideForPlayer(playerid, zone);
    }
    default boolean GangZoneHideForAll(int zone) {
        return SAMPFunctions.GangZoneHideForAll(zone);
    }
    default boolean GangZoneFlashForPlayer(int playerid, int zone, int flashcolor) {
        return SAMPFunctions.GangZoneFlashForPlayer(playerid, zone, flashcolor);
    }
    default boolean GangZoneFlashForAll(int zone, int flashcolor) {
        return SAMPFunctions.GangZoneFlashForAll(zone, flashcolor);
    }
    default boolean GangZoneStopFlashForPlayer(int playerid, int zone) {
        return SAMPFunctions.GangZoneStopFlashForPlayer(playerid, zone);
    }
    default boolean GangZoneStopFlashForAll(int zone) {
        return SAMPFunctions.GangZoneStopFlashForAll(zone);
    }
    default int Create3DTextLabel(String text, int color, float x, float y, float z, float DrawDistance, int virtualworld, boolean testLOS ) {
        return SAMPFunctions.Create3DTextLabel(text, color, x, y, z, DrawDistance, virtualworld, testLOS );
    }
    default boolean Delete3DTextLabel(int id) {
        return SAMPFunctions.Delete3DTextLabel(id);
    }
    default boolean Attach3DTextLabelToPlayer(int id, int playerid, float OffsetX, float OffsetY, float OffsetZ) {
        return SAMPFunctions.Attach3DTextLabelToPlayer(id, playerid, OffsetX, OffsetY, OffsetZ);
    }
    default boolean Attach3DTextLabelToVehicle(int id, int vehicleid, float OffsetX, float OffsetY, float OffsetZ) {
        return SAMPFunctions.Attach3DTextLabelToVehicle(id, vehicleid, OffsetX, OffsetY, OffsetZ);
    }
    default boolean Update3DTextLabelText(int id, int color, String text) {
        return SAMPFunctions.Update3DTextLabelText(id, color, text);
    }
    default int CreatePlayer3DTextLabel(int playerid, String text, int color, float x, float y, float z, float DrawDistance, int attachedplayer , int attachedvehicle , boolean testLOS ) {
        return SAMPFunctions.CreatePlayer3DTextLabel(playerid, text, color, x, y, z, DrawDistance, attachedplayer , attachedvehicle , testLOS );
    }
    default boolean DeletePlayer3DTextLabel(int playerid, int id) {
        return SAMPFunctions.DeletePlayer3DTextLabel(playerid, id);
    }
    default boolean UpdatePlayer3DTextLabelText(int playerid, int id, int color, String text) {
        return SAMPFunctions.UpdatePlayer3DTextLabelText(playerid, id, color, text);
    }
    default boolean ShowPlayerDialog(int playerid, int dialogid, int style, String caption, String info, String button1, String button2) {
        return SAMPFunctions.ShowPlayerDialog(playerid, dialogid, style, caption, info, button1, button2);
    }
    default boolean KillTimer(int timerid) {
        return SAMPFunctions.KillTimer(timerid);
    }
    default String GetWeaponName(int weaponid) {
        return SAMPFunctions.GetWeaponName(weaponid);
    }
    default String GetServerVarAsString(String varname) {
        return SAMPFunctions.GetServerVarAsString(varname);
    }
    default String GetPlayerNetworkStats(int playerid) {
        return SAMPFunctions.GetPlayerNetworkStats(playerid);
    }
    default String GetNetworkStats() {
        return SAMPFunctions.GetNetworkStats();
    }
    default String GetPlayerVersion(int playerid) {
        return SAMPFunctions.GetPlayerVersion(playerid);
    }
    default String NetStats_GetIpPort(int playerid) {
        return SAMPFunctions.NetStats_GetIpPort(playerid);
    }
    default String gpci(int playerid) {
        return SAMPFunctions.gpci(playerid);
    }

    /* a_actors */
    default int CreateActor(int modelid,float x,float y,float z,float rotation) {
        return SAMPFunctions.CreateActor(modelid,x,y,z,rotation);
    }
    default boolean DestroyActor(int actorid) {
        return SAMPFunctions.DestroyActor(actorid);
    }
    default boolean IsActorStreamedIn(int actorid,int forplayerid) {
        return SAMPFunctions.IsActorStreamedIn(actorid,forplayerid);
    }
    default boolean SetActorVirtualWorld(int actorid,int vworld) {
        return SAMPFunctions.SetActorVirtualWorld(actorid,vworld);
    }
    default int GetActorVirtualWorld(int actorid) {
        return SAMPFunctions.GetActorVirtualWorld(actorid);
    }
    default boolean ApplyActorAnimation(int actorid,String animlib,String animname,float fDelta,boolean loop,boolean lockx,boolean locky,boolean freeze,int time) {
        return SAMPFunctions.ApplyActorAnimation(actorid,animlib,animname,fDelta,loop,lockx,locky,freeze,time);
    }
    default boolean ClearActorAnimations(int actorid) {
        return SAMPFunctions.ClearActorAnimations(actorid);
    }
    default boolean SetActorPos(int actorid,float x,float y,float z) {
        return SAMPFunctions.SetActorPos(actorid,x,y,z);
    }
    default boolean SetActorFacingAngle(int actorid,float angle) {
        return SAMPFunctions.SetActorFacingAngle(actorid,angle);
    }
    default boolean SetActorHealth(int actorid,float health) {
        return SAMPFunctions.SetActorHealth(actorid,health);
    }
    default boolean SetActorInvulnerable(int actorid,boolean invulnerable) {
        return SAMPFunctions.SetActorInvulnerable(actorid,invulnerable);
    }
    default boolean IsActorInvulnerable(int actorid) {
        return SAMPFunctions.IsActorInvulnerable(actorid);
    }
    default boolean IsValidActor(int actorid) {
        return SAMPFunctions.IsValidActor(actorid);
    }
    default float[] GetActorPos(int actorid) {
        return SAMPFunctions.GetActorPos(actorid);
    }
    default float GetActorFacingAngle(int actorid) {
        return SAMPFunctions.GetActorFacingAngle(actorid);
    }
    default float GetActorHealth(int actorid) {
        return SAMPFunctions.GetActorHealth(actorid);
    }

    /* Vehicle collision */
    default boolean DisableRemoteVehicleCollisions(int playerid, boolean disable) {
        return SAMPFunctions.DisableRemoteVehicleCollisions(playerid, disable);
    }

    /* Camera target functions */
    default boolean EnablePlayerCameraTarget(int playerid, boolean enable) {
        return SAMPFunctions.EnablePlayerCameraTarget(playerid, enable);
    }
    default int GetPlayerCameraTargetObject(int playerid) {
        return SAMPFunctions.GetPlayerCameraTargetObject(playerid);
    }
    default int GetPlayerCameraTargetPlayer(int playerid) {
        return SAMPFunctions.GetPlayerCameraTargetPlayer(playerid);
    }
    default int GetPlayerCameraTargetActor(int playerid) {
        return SAMPFunctions.GetPlayerCameraTargetActor(playerid);
    }
    default int GetPlayerCameraTargetVehicle(int playerid) {
        return SAMPFunctions.GetPlayerCameraTargetVehicle(playerid);
    }

    /* Pool */
    default int GetPlayerPoolSize() {
        return SAMPFunctions.GetPlayerPoolSize();
    }
    default int GetVehiclePoolSize() {
        return SAMPFunctions.GetVehiclePoolSize();
    }

    /* Car doors and windows */
    default boolean[] GetVehicleParamsCarDoors(int vehicleid) {
        return SAMPFunctions.GetVehicleParamsCarDoors(vehicleid);
    }
    default boolean[] GetVehicleParamsCarWindows(int vehicleid) {
        return SAMPFunctions.GetVehicleParamsCarWindows(vehicleid);
    }
    default boolean SetVehicleParamsCarDoors(int vehicleid, boolean driver, boolean passenger, boolean backleft, boolean backright) {
        return SAMPFunctions.SetVehicleParamsCarDoors(vehicleid, driver, passenger, backleft, backright);
    }
    default boolean SetVehicleParamsCarWindows(int vehicleid, boolean driver, boolean passenger, boolean backleft, boolean backright) {
        return SAMPFunctions.SetVehicleParamsCarWindows(vehicleid, driver, passenger, backleft, backright);
    }

    /* Camera col */
    default boolean SetObjectNoCameraCol(int objectid) {
        return SAMPFunctions.SetObjectNoCameraCol(objectid);
    }
    default boolean SetObjectsDefaultCameraCol(boolean disable) {
        return SAMPFunctions.SetObjectsDefaultCameraCol(disable);
    }
    default boolean SetPlayerObjectNoCameraCol(int playerid, int objectid) {
        return SAMPFunctions.SetPlayerObjectNoCameraCol(playerid, objectid);
    }

    default boolean SomeVeryLargeNameMethodTwo() {
        return SAMPFunctions.SomeVeryLargeNameMethodTwo();
    }
}