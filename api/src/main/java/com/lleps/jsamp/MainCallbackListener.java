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

import com.lleps.jsamp.gamemode.CallbackListener;
import com.lleps.jsamp.gamemode.GameMode;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Entry point for JSAMP. Static "On..." methods will be called by JSAMP plugin.
 *
 * This static class will do two things:
 *
 * 1. Initialize GameMode instance.
 * 2. Report events to all added listeners. To add or remove listeners, use
 * {@link #addCallbackListener(CallbackListener, ListenerPriority)} and
 * {@link #removeCallbackListener(CallbackListener)}
 *
 * Important note: On every callback call, this class will loop through all listeners
 * and report the corresponding event. If you want to stop the cycle, you need to
 * return true on the listener method.
 * If the given callback handle returns, will return the corresponding value (for example,
 * OnPlayerText will return 0, but OnPlayerCommandText will return 1).
 *
 * @author spell
 */
public class MainCallbackListener {
    private static List<CallbackListener> listeners = new ArrayList<>();
    private static GameMode gameMode;

    public enum ListenerPriority {
        /**
         * Will be added at the begin of the list.
         */
        HIGH,
        /**
         * Will be added at the end of the list.
         */
        LOW};

    private final static String JSAMP_PROPERTIES_PATH = "./jsamp/jsamp.properties";
    private final static String PROPERTY_MAINCLASS = "main.class";

    /**
     * @param callbackListener which listener to add.
     * @param priority which priority. See priority documentation for more details.
     */
    public static void addCallbackListener(CallbackListener callbackListener, ListenerPriority priority) {
        if (priority == ListenerPriority.HIGH) {
            listeners.add(0, callbackListener);
        } else if (priority == ListenerPriority.LOW) {
            listeners.add(callbackListener);
        }
    }

    /**
     * @param listener listener to remove.
     */
    public static void removeCallbackListener(CallbackListener listener) {
       listeners.remove(listener);
    }

    public static void ProcessTick() {
        gameMode.onTick();
    }

    public static void OnGameModeInit() {
        // Initialize GameMode instance using reflection, based on JSAMP_PROPERTIES_PATH PROPERTY_MAINCLASS line.
        try (InputStream inputStream = new FileInputStream(new File(JSAMP_PROPERTIES_PATH))) {
            Properties properties = new Properties();
            properties.load(inputStream);

            String mainClassName = properties.getProperty(PROPERTY_MAINCLASS);
            Class mainClass = Class.forName(mainClassName);
            if (!GameMode.class.isAssignableFrom(mainClass)) {
                throw new Exception(PROPERTY_MAINCLASS + " must be a subclass of " + GameMode.class.toString());
            }

            gameMode = (GameMode) mainClass.newInstance();
            gameMode.onInit();
        } catch (Exception e) {
            FunctionAccess.logprintf("Error: Cannot initialize GameMode instance:");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            SAMPFunctions.logprintf(sw.toString());
            SAMPFunctions.logprintf("Shutting down server by JSAMP..");
            SAMPFunctions.SendRconCommand("exit");
        }
    }

    public static void OnGameModeExit() {
        if (gameMode != null) {
            gameMode.onExit();
        }
    }

    public static void OnExceptionOccurred(Throwable throwable) {
        gameMode.onExceptionOccurred(throwable);
    }

    public static int OnPlayerConnect(int playerId ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerConnect(playerId ))
                break;
        }
        return 1;
    }

    public static int OnPlayerDisconnect(int playerId, int reason ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerDisconnect(playerId, reason ))
                break;
        }
        return 1;
    }

    public static int OnPlayerSpawn(int playerId ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerSpawn(playerId ))
                break;
        }
        return 1;
    }

    public static int OnPlayerDeath(int playerId, int killerId, int reason ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerDeath(playerId, killerId, reason ))
                break;
        }
        return 1;
    }

    public static int OnVehicleSpawn(int vehicleId ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnVehicleSpawn(vehicleId ))
                break;
        }
        return 1;
    }

    public static int OnVehicleDeath(int vehicleId, int killerId ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnVehicleDeath(vehicleId, killerId ))
                break;
        }
        return 1;
    }

    public static int OnPlayerText(int playerId, String text ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerText(playerId, text ))
                return 0;
        }
        return 1;
    }

    public static int OnPlayerCommandText(int playerId, String cmdtext ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerCommandText(playerId, cmdtext ))
                return 1;
        }
        return 0;
    }

    public static int OnPlayerRequestClass(int playerId, int classId ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerRequestClass(playerId, classId ))
                return 0;
        }
        return 1;
    }

    public static int OnPlayerEnterVehicle(int playerId, int vehicleId, boolean isPassenger ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerEnterVehicle(playerId, vehicleId, isPassenger ))
                break;
        }
        return 1;
    }

    public static int OnPlayerExitVehicle(int playerId, int vehicleId ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerExitVehicle(playerId, vehicleId ))
                break;
        }
        return 1;
    }

    public static int OnPlayerStateChange(int playerId, int state, int oldState ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerStateChange(playerId, state, oldState ))
                break;
        }
        return 1;
    }

    public static int OnPlayerEnterCheckpoint(int playerId ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerEnterCheckpoint(playerId ))
                break;
        }
        return 1;
    }

    public static int OnPlayerLeaveCheckpoint(int playerId ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerLeaveCheckpoint(playerId ))
                break;
        }
        return 1;
    }

    public static int OnPlayerEnterRaceCheckpoint(int playerId ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerEnterRaceCheckpoint(playerId ))
                break;
        }
        return 1;
    }

    public static int OnPlayerLeaveRaceCheckpoint(int playerId ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerLeaveRaceCheckpoint(playerId ))
                break;
        }
        return 1;
    }

    public static int OnRconCommand(String cmd ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnRconCommand(cmd ))
                break;
        }
        return 1;
    }

    public static int OnPlayerRequestSpawn(int playerId ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerRequestSpawn(playerId ))
                return 0;
        }
        return 1;
    }

    public static int OnObjectMoved(int objectId ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnObjectMoved(objectId ))
                break;
        }
        return 1;
    }

    public static int OnPlayerObjectMoved(int playerId, int objectId ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerObjectMoved(playerId, objectId ))
                break;
        }
        return 1;
    }

    public static int OnPlayerPickUpPickup(int playerId, int pickupId ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerPickUpPickup(playerId, pickupId ))
                break;
        }
        return 1;
    }

    public static int OnVehicleMod(int playerId, int vehicleId, int componentId ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnVehicleMod(playerId, vehicleId, componentId ))
                break;
        }
        return 1;
    }

    public static int OnEnterExitModShop(int playerId, int enterexit, int interiorId ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnEnterExitModShop(playerId, enterexit, interiorId ))
                break;
        }
        return 1;
    }

    public static int OnVehiclePaintjob(int playerId, int vehicleId, int paintjobId ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnVehiclePaintjob(playerId, vehicleId, paintjobId ))
                break;
        }
        return 1;
    }

    public static int OnVehicleRespray(int playerId, int vehicleId, int color1, int color2 ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnVehicleRespray(playerId, vehicleId, color1, color2 ))
                break;
        }
        return 1;
    }

    public static int OnVehicleDamageStatusUpdate(int vehicleId, int playerId ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnVehicleDamageStatusUpdate(vehicleId, playerId ))
                break;
        }
        return 1;
    }

    public static int OnUnoccupiedVehicleUpdate(int vehicleId, int playerId, int passengerSeat, float newX, float newY, float newZ, float vel_x, float vel_y, float vel_z ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnUnoccupiedVehicleUpdate(vehicleId, playerId, passengerSeat, newX, newY, newZ, vel_x, vel_y, vel_z ))
                break;
        }
        return 1;
    }

    public static int OnPlayerSelectedMenuRow(int playerId, int row ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerSelectedMenuRow(playerId, row ))
                break;
        }
        return 1;
    }

    public static int OnPlayerExitedMenu(int playerId ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerExitedMenu(playerId ))
                break;
        }
        return 1;
    }

    public static int OnPlayerInteriorChange(int playerId, int interiorId, int oldInteriorId ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerInteriorChange(playerId, interiorId, oldInteriorId ))
                break;
        }
        return 1;
    }

    public static int OnPlayerKeyStateChange(int playerId, int keys, int oldKeys ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerKeyStateChange(playerId, keys, oldKeys ))
                break;
        }
        return 1;
    }

    public static int OnRconLoginAttempt(String ip, String password, boolean isSuccess ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnRconLoginAttempt(ip, password, isSuccess ))
                break;
        }
        return 1;
    }

    private static long minOPUTime, maxOPUTime;
    private static long secCounterLock;
    private static long nsAccumulator, updateCounter;

    public static int OnPlayerUpdate(int playerId ) {
        boolean shouldReturnZero = false;

        /*
        long secCounter = System.currentTimeMillis();
        if (secCounter > secCounterLock) {
            secCounterLock = secCounter + 1_000;
            double min = nsToMS(minOPUTime);
            double max = nsToMS(maxOPUTime);
            double average = (min + max) / 2.0;
            SAMPFunctions.SendClientMessage(playerId, -1, "min: " + min + " | max: " + max + " | average: " + average);
            minOPUTime = Long.MAX_VALUE;
            maxOPUTime = 0;

            double average2 = nsToMS(nsAccumulator / updateCounter);
            SAMPFunctions.SendClientMessage(playerId, 0xFF0000FF,
                    "the ultimate result! {ffffff}average time: " + average2
                            + " for total " + nsToMS(updateCounter) + " (updates: " + updateCounter + ")");

            updateCounter = 0;
            nsAccumulator = 0;
        }


        long clockStart = System.nanoTime();*/
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerUpdate(playerId ))
                shouldReturnZero = true;
                break; // TODO: CHANGE IT TO 'return 0' IF YOU ARE NOT BENCHMARKING
        }
        /*long timeSpentUpdating = System.nanoTime() - clockStart;
        nsAccumulator += timeSpentUpdating;
        updateCounter++;*/
        return shouldReturnZero ? 0 : 1;
    }

    static double nsToMS(long nano) {
        return nano / 1_000_000.0;
    }

    public static int OnPlayerStreamIn(int playerId, int forPlayerId ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerStreamIn(playerId, forPlayerId ))
                break;
        }
        return 1;
    }

    public static int OnPlayerStreamOut(int playerId, int forPlayerId ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerStreamOut(playerId, forPlayerId ))
                break;
        }
        return 1;
    }

    public static int OnVehicleStreamIn(int vehicleId, int forPlayerId ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnVehicleStreamIn(vehicleId, forPlayerId ))
                break;
        }
        return 1;
    }

    public static int OnVehicleStreamOut(int vehicleId, int forPlayerId ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnVehicleStreamOut(vehicleId, forPlayerId ))
                break;
        }
        return 1;
    }

    public static int OnDialogResponse(int playerId, int dialogId, int response, int listitem, String inputtext ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnDialogResponse(playerId, dialogId, response, listitem, inputtext ))
                return 1;
        }
        return 0;
    }

    public static int OnPlayerTakeDamage(int playerid, int issuerId, float amount, int weaponid, int bodypart ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerTakeDamage(playerid, issuerId, amount, weaponid, bodypart ))
                return 0;
        }
        return 1;
    }

    public static int OnPlayerGiveDamage(int playerId, int damagedId, float amount, int weaponId, int bodypart ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerGiveDamage(playerId, damagedId, amount, weaponId, bodypart ))
                break;
        }
        return 1;
    }

    public static int OnPlayerClickMap(int playerId, float x, float y, float z ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerClickMap(playerId, x, y, z ))
                break;
        }
        return 1;
    }

    public static int OnPlayerClickTextDraw(int playerid, int clickedid ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerClickTextDraw(playerid, clickedid ))
                return 1;
        }
        return 0;
    }

    public static int OnPlayerClickPlayerTextDraw(int playerid, int playertextid ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerClickPlayerTextDraw(playerid, playertextid ))
                return 1;
        }
        return 0;
    }

    public static int OnPlayerClickPlayer(int playerId, int clickedPlayerId, int source ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerClickPlayer(playerId, clickedPlayerId, source ))
                break;
        }
        return 1;
    }

    public static int OnPlayerEditObject(int playerid, int playerobject, int objectid, int response, float fX, float fY, float fZ, float fRotX, float fRotY, float fRotZ ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerEditObject(playerid, playerobject, objectid, response, fX, fY, fZ, fRotX, fRotY, fRotZ ))
                break;
        }
        return 1;
    }

    public static int OnPlayerEditAttachedObject(int playerid, int response, int index, int modelid, int boneid, float fOffsetX, float fOffsetY, float fOffsetZ, float fRotX, float fRotY, float fRotZ, float fScaleX, float fScaleY, float fScaleZ ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerEditAttachedObject(playerid, response, index, modelid, boneid, fOffsetX, fOffsetY, fOffsetZ, fRotX, fRotY, fRotZ, fScaleX, fScaleY, fScaleZ ))
                break;
        }
        return 1;
    }

    public static int OnPlayerSelectObject(int playerid, int type, int objectid, int modelid, float fX, float fY, float fZ ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerSelectObject(playerid, type, objectid, modelid, fX, fY, fZ ))
                break;
        }
        return 1;
    }

    public static int OnPlayerWeaponShot(int playerid, int weaponid, int hittype, int hitid, float fX, float fY, float fZ ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerWeaponShot(playerid, weaponid, hittype, hitid, fX, fY, fZ ))
                return 0;
        }
        return 1;
    }

    public static int OnIncomingConnection(int playerid, String ipAddress, int port ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnIncomingConnection(playerid, ipAddress, port ))
                break;
        }
        return 1;
    }

    public static int OnTrailerUpdate(int playerid, int vehicleid ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnTrailerUpdate(playerid, vehicleid ))
                return 0;
        }
        return 1;
    }

    public static int OnActorStreamIn(int actor, int playerid ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnActorStreamIn(actor, playerid ))
                break;
        }
        return 1;
    }

    public static int OnActorStreamOut(int actor, int playerid ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnActorStreamOut(actor, playerid ))
                break;
        }
        return 1;
    }

    public static int OnPlayerGiveDamageActor(int playerid, int actor, float amount, int weapon, int bodypart ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnPlayerGiveDamageActor(playerid, actor, amount, weapon, bodypart ))
                break;
        }
        return 1;
    }

    public static int OnVehicleSirenStateChange(int playerid, int vehicleid, boolean newstate ) {
        for (CallbackListener listener : listeners) {
            if (listener.OnVehicleSirenStateChange(playerid, vehicleid, newstate ))
                break;
        }
        return 1;
    }


}