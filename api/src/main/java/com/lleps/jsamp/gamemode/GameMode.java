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

import com.lleps.jsamp.MainCallbackListener;
import com.lleps.jsamp.SAMPFunctions;
import com.lleps.jsamp.anticheat.AnticheatLayer;
import com.lleps.jsamp.player.Player;
import com.lleps.jsamp.FunctionAccess;
import com.lleps.jsamp.constant.Weather;
import com.lleps.jsamp.world.World;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Main class. Will be initialized by MainCallbackListener which is the entry point. Will handle GameMode lifecycle.
 * User main class must extends GameMode, for example:
 *
 *      public class RolePlayGameMode extends GameMode {
 *          public void onInit() {
 *              super.onInit(); // Always call super methods when overriding, to do internal work correctly.
 *              printLine("Role play initialized");
 *          }
 *      }
 *
 * @author spell
 */
public abstract class GameMode {
    /**
     * THIS means a static reference to this object, which is needed to make all GameMode methods static.
     */
    private static GameMode THIS;

    private final Map<Runnable, Long> tasksToRunLater = new ConcurrentHashMap<>();

    private Weather weather;
    private LocalTime time;

    /**
     * World references are needed to report weather and time updates.
     */
    private Collection<World> worlds;

    /**
     * Called by MainCallbackListener, when GameMode instance is sucessfully created.
     * @throws Exception if something wrong happens at initialization.
     */
    public void onInit() throws Exception {
        THIS = this;

        worlds = new ArrayList<>();

        setWeather(Weather.get(0));
        setTime(LocalTime.of(14, 30));

        AnticheatLayer anticheat = new AnticheatLayer();
        MainCallbackListener.addCallbackListener(anticheat, MainCallbackListener.ListenerPriority.LOW);
        FunctionAccess.setExecutor(anticheat);

        MainCallbackListener.addCallbackListener(new EventDispatcher(this), MainCallbackListener.ListenerPriority.LOW);
    }

    /**
     * Called for every server tick, by MainCallbackListener.
     */
    public void onTick() {
        Iterator<Map.Entry<Runnable, Long>> iterator = tasksToRunLater.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Runnable, Long> entry = iterator.next();
            Runnable task = entry.getKey();
            long whenToRun = entry.getValue();
            if (System.currentTimeMillis() >= whenToRun) {
                task.run();
                iterator.remove();
            }
        }
    }

    /**
     * Called when the server goes down by MainCallbackListener. Clean up should be done here.
     */
    public void onExit() {

    }

    /**
     * Called when a player connects to the server.
     * @param player player.
     */
    public void onPlayerConnect(Player player) {
    }

    /**
     * Called when a unexpected exception occurs on JSAMP.
     * @param throwable exception.
     */
    public void onExceptionOccurred(Throwable throwable) {
        FunctionAccess.logprintf("-- Unexpected exception on JSAMP --");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);

        // output to server_log.txt
        FunctionAccess.logprintf(sw.toString());

        // output to jsamp_exceptions.txt
        final String JSAMP_EXCEPTIONS_FILE = "jsamp-exceptions.txt";
        try {
            Files.write(Paths.get(JSAMP_EXCEPTIONS_FILE), sw.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            FunctionAccess.logprintf("[warning] Cannot log exception to file " + JSAMP_EXCEPTIONS_FILE);
            FunctionAccess.logprintf("[warning] Cause: " + e);
        }
    }

    /**
     * @return all connected players.
     */
    public static Collection<Player> getPlayers() {
        return ObjectNativeIDS.getNotNullElements(ObjectNativeIDS.getInstance().players);
    }

    /**
     * Run a task on SA:MP thread. Will be executed on next server tick.
     * @param runnable task.
     */
    public static void runLater(Runnable runnable) {
        runLater(Duration.ZERO, runnable);
    }

    /**
     * Run a task later on SA:MP thread.
     * @param delay delay since this function call.
     * @param runnable task to run.
     */
    public static void runLater(Duration delay, Runnable runnable) {
        THIS.tasksToRunLater.put(runnable, System.currentTimeMillis() + delay.toMillis());
    }

    /**
     * Set GameMode weather. Will affect all worlds that inherits weather.
     * @param weather weather to set.
     */
    public static void setWeather(Weather weather) {
        THIS.weather = weather;
        THIS.worlds.forEach(w -> w.onParentWeatherChange(weather));
    }

    public static Weather getWeather() {
        return THIS.weather;
    }

    /**
     * Set GameMode time. Will affect all worlds that inherits time.
     * @param time time to set.
     */
    public static void setTime(LocalTime time) {
        THIS.time = time;
        FunctionAccess.SendRconCommand("worldtime " + time.toString());
        THIS.worlds.forEach(w -> w.onParentTimeChange(time));
    }

    public static LocalTime getTime() {
        return THIS.time;
    }

    /**
     * To make a world receive time and weather updates from GameMode.
     * @param world world.
     */
    public static void addWorld(World world) {
        THIS.worlds.add(world);
        world.onParentWeatherChange(THIS.weather);
    }

    /**
     * Removes a world.
     * @param world world.
     */
    public static void removeWorld(World world) {
        THIS.worlds.remove(world);
    }

    /**
     * Equivalent to printf. Will write to console and to server_log.txt
     * @param string string.
     */
    public static void printLine(String string) {
        FunctionAccess.logprintf(string);
    }

    /**
     * Equivalent to SA:MP SendRconCommand.
     * @param command command.
     */
    public static void sendRConCommand(String command) {
        FunctionAccess.SendRconCommand(command);
    }
}