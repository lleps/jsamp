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
package com.lleps.jsamp.gamemode;

/**
 * CallbackListener interface. You must return true in order to stop the cycle.
 *
 * @author spell
 */
public interface CallbackListener {
    default boolean OnPlayerConnect(int playerId ) {
        return false;
    }

    default boolean OnPlayerDisconnect(int playerId, int reason ) {
        return false;
    }

    default boolean OnPlayerSpawn(int playerId ) {
        return false;
    }

    default boolean OnPlayerDeath(int playerId, int killerId, int reason ) {
        return false;
    }

    default boolean OnVehicleSpawn(int vehicleId ) {
        return false;
    }

    default boolean OnVehicleDeath(int vehicleId, int killerId ) {
        return false;
    }

    default boolean OnPlayerText(int playerId, String text ) {
        return false;
    }

    default boolean OnPlayerCommandText(int playerId, String cmdtext ) {
        return false;
    }

    default boolean OnPlayerRequestClass(int playerId, int classId ) {
        return false;
    }

    default boolean OnPlayerEnterVehicle(int playerId, int vehicleId, boolean isPassenger ) {
        return false;
    }

    default boolean OnPlayerExitVehicle(int playerId, int vehicleId ) {
        return false;
    }

    default boolean OnPlayerStateChange(int playerId, int state, int oldState ) {
        return false;
    }

    default boolean OnPlayerEnterCheckpoint(int playerId ) {
        return false;
    }

    default boolean OnPlayerLeaveCheckpoint(int playerId ) {
        return false;
    }

    default boolean OnPlayerEnterRaceCheckpoint(int playerId ) {
        return false;
    }

    default boolean OnPlayerLeaveRaceCheckpoint(int playerId ) {
        return false;
    }

    default boolean OnRconCommand(String cmd ) {
        return false;
    }

    default boolean OnPlayerRequestSpawn(int playerId ) {
        return false;
    }

    default boolean OnObjectMoved(int objectId ) {
        return false;
    }

    default boolean OnPlayerObjectMoved(int playerId, int objectId ) {
        return false;
    }

    default boolean OnPlayerPickUpPickup(int playerId, int pickupId ) {
        return false;
    }

    default boolean OnVehicleMod(int playerId, int vehicleId, int componentId ) {
        return false;
    }

    default boolean OnEnterExitModShop(int playerId, int enterexit, int interiorId ) {
        return false;
    }

    default boolean OnVehiclePaintjob(int playerId, int vehicleId, int paintjobId ) {
        return false;
    }

    default boolean OnVehicleRespray(int playerId, int vehicleId, int color1, int color2 ) {
        return false;
    }

    default boolean OnVehicleDamageStatusUpdate(int vehicleId, int playerId ) {
        return false;
    }

    default boolean OnUnoccupiedVehicleUpdate(int vehicleId, int playerId, int passengerSeat, float newX, float newY, float newZ, float vel_x, float vel_y, float vel_z ) {
        return false;
    }

    default boolean OnPlayerSelectedMenuRow(int playerId, int row ) {
        return false;
    }

    default boolean OnPlayerExitedMenu(int playerId ) {
        return false;
    }

    default boolean OnPlayerInteriorChange(int playerId, int interiorId, int oldInteriorId ) {
        return false;
    }

    default boolean OnPlayerKeyStateChange(int playerId, int keys, int oldKeys ) {
        return false;
    }

    default boolean OnRconLoginAttempt(String ip, String password, boolean isSuccess ) {
        return false;
    }

    default boolean OnPlayerUpdate(int playerId ) {
        return false;
    }

    default boolean OnPlayerStreamIn(int playerId, int forPlayerId ) {
        return false;
    }

    default boolean OnPlayerStreamOut(int playerId, int forPlayerId ) {
        return false;
    }

    default boolean OnVehicleStreamIn(int vehicleId, int forPlayerId ) {
        return false;
    }

    default boolean OnVehicleStreamOut(int vehicleId, int forPlayerId ) {
        return false;
    }

    default boolean OnDialogResponse(int playerId, int dialogId, int response, int listitem, String inputtext ) {
        return false;
    }

    default boolean OnPlayerTakeDamage(int playerid, int issuerId, float amount, int weaponid, int bodypart ) {
        return false;
    }

    default boolean OnPlayerGiveDamage(int playerId, int damagedId, float amount, int weaponId, int bodypart ) {
        return false;
    }

    default boolean OnPlayerClickMap(int playerId, float x, float y, float z ) {
        return false;
    }

    default boolean OnPlayerClickTextDraw(int playerid, int clickedid ) {
        return false;
    }

    default boolean OnPlayerClickPlayerTextDraw(int playerid, int playertextid ) {
        return false;
    }

    default boolean OnPlayerClickPlayer(int playerId, int clickedPlayerId, int source ) {
        return false;
    }

    default boolean OnPlayerEditObject(int playerid, int playerobject, int objectid, int response, float fX, float fY, float fZ, float fRotX, float fRotY, float fRotZ ) {
        return false;
    }

    default boolean OnPlayerEditAttachedObject(int playerid, int response, int index, int modelid, int boneid, float fOffsetX, float fOffsetY, float fOffsetZ, float fRotX, float fRotY, float fRotZ, float fScaleX, float fScaleY, float fScaleZ ) {
        return false;
    }

    default boolean OnPlayerSelectObject(int playerid, int type, int objectid, int modelid, float fX, float fY, float fZ ) {
        return false;
    }

    default boolean OnPlayerWeaponShot(int playerid, int weaponid, int hittype, int hitid, float fX, float fY, float fZ ) {
        return false;
    }

    default boolean OnIncomingConnection(int playerid, String ipAddress, int port ) {
        return false;
    }

    default boolean OnTrailerUpdate(int playerid, int vehicleid ) {
        return false;
    }

    default boolean OnActorStreamIn(int actor, int playerid ) {
        return false;
    }

    default boolean OnActorStreamOut(int actor, int playerid ) {
        return false;
    }

    default boolean OnPlayerGiveDamageActor(int playerid, int actor, float amount, int weapon, int bodypart ) {
        return false;
    }

    default boolean OnVehicleSirenStateChange(int playerid, int vehicleid, boolean newstate ) {
        return false;
    }
}