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
package com.lleps.jsamp.player;

import com.lleps.jsamp.SAMPConstants;
import com.lleps.jsamp.FunctionAccess;
import com.lleps.jsamp.constant.Weather;
import com.lleps.jsamp.data.Color;
import com.lleps.jsamp.data.Vector3D;
import com.lleps.jsamp.dialog.Dialog;
import com.lleps.jsamp.gamemode.ObjectNativeIDS;
import com.lleps.jsamp.world.World;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author spell
 */
public class Player {
    public static Optional<Player> getById(int id) {
        return Optional.ofNullable(ObjectNativeIDS.get(ObjectNativeIDS.getInstance().players, id));
    }

    private int id;
    private World world;
    private Dialog dialog;
    private Weather weather;
    private LocalTime time;

    public Player(int id) {
        this.id = id;
        ObjectNativeIDS.getInstance().players[id] = this;
    }

    /**
     * Set player's world.
     *
     * @param world new world.
     */
    public void setWorld(World world) {
        if (this.world != null) {
            this.world.onPlayerExit(this);
        }

        this.world = world;
        world.onPlayerEnter(this);
    }

    /**
     * Get player current world, or null if player isn't in a world.
     *
     * @return player world.
     */
    public World getWorld() {
        return world;
    }

    /**
     * Called when player's world weather changes.
     *
     * @param weather new weather.
     */
    public void onParentWeatherChange(Weather weather) {
        if (this.weather == null) { // only set if player dont have a custom weather.
            FunctionAccess.SetPlayerWeather(id, weather.getId());
        }
    }

    /**
     * Called when player's world time changes.
     *
     * @param time new time.
     */
    public void onParentTimeChange(LocalTime time) {
        if (this.time == null) { // only set if player dont have a custom time.
            FunctionAccess.SetPlayerTime(id, time.getHour(), time.getMinute());
        }
    }

    /**
     * Set player custom weather. By default, is inherited from their world.
     *
     * @param weather weather.
     */
    public void setWeather(Weather weather) {
        this.weather = weather;
        FunctionAccess.SetPlayerWeather(id, weather.getId());
    }

    /**
     * Get player weather, or player's world weather if null.
     *
     * @return weather.
     */
    public Weather getWeather() {
        return weather != null ? weather : world.getWeather();
    }

    /**
     * Set player custom time. By default, is inherited from their world.
     *
     * @param time time.
     */
    public void setTime(LocalTime time) {
        this.time = time;
        FunctionAccess.SetPlayerTime(id, time.getHour(), time.getMinute());
    }

    /**
     * Get player time, or player's world time if null.
     *
     * @return time.
     */
    public LocalTime getTime() {
        return time != null ? time : world.getTime();
    }

    /**
     * Get player id.
     * @return player id.
     */
    public int getId() {
        return id;
    }

    /**
     * Get this player position.
     *
     * @return player position.
     */
    public Vector3D getPosition() {
        return Vector3D.of(FunctionAccess.GetPlayerPos(id));
    }

    /**
     * Show dialog to a player. Dialog cant be null or a NPE will be thrown.
     *
     * @param dialog dialog.
     */
    public void showDialog(Dialog dialog) {
        this.dialog = dialog;
        FunctionAccess.ShowPlayerDialog(id, dialog.getId(), dialog.getStyle(), dialog.getCaption(), dialog.getInfo(),
                dialog.getButton1(), dialog.getButton2());
    }

    /**
     * Hide current dialog.
     */
    public void hideDialog() {
        FunctionAccess.ShowPlayerDialog(id, -1, 0, "-", "-", "-", "-");
    }

    /**
     * Get player current dialog.
     *
     * @return dialog.
     */
    public Optional<Dialog> getDialog() {
        return Optional.ofNullable(dialog);
    }

    /**
     * Called when this player disconnects from this server. Used internally.
     */
    public void onDisconnect() {

        if (world != null) {
            world.onPlayerExit(this);
        }

        // do all stuff BEFORE setting player reference to null, because there are objects that
        // do some work when player disconnects using this reference.
        // this line must be THE LAST line called on a player disconnect event.
        ObjectNativeIDS.getInstance().players[id] = null;
        id = SAMPConstants.INVALID_PLAYER_ID;
    }

    /**
     * Send a message to a player.
     *
     * @param color message color.
     * @param message message to send.
     */
    public void sendMessage(Color color, String message) {
        FunctionAccess.SendClientMessage(id, color.getRGBA(), message);
    }

    /**
     * Send a white message to a player.
     *
     * @param message message to send.
     */
    public void sendMessage(String message) {
        sendMessage(Color.WHITE, message);
    }
}