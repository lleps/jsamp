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
import com.lleps.jsamp.FunctionAccess;
import com.lleps.jsamp.SAMPFunctions;
import com.lleps.jsamp.constant.*;
import com.lleps.jsamp.data.*;
import com.lleps.jsamp.dialog.Dialog;
import com.lleps.jsamp.exception.InvalidNameException;
import com.lleps.jsamp.server.ObjectNativeIDS;
import com.lleps.jsamp.server.SAMPServer;
import com.lleps.jsamp.world.World;
import com.lleps.jsamp.world.entity.Body;
import com.lleps.jsamp.world.entity.Vehicle;
import com.lleps.jsamp.world.entity.WorldEntity;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

/**
 * @author spell
 */
public class Player {
    // TODO: Set freezed to false when player spawns.

    public static Optional<Player> getById(int id) {
        return Optional.ofNullable(ObjectNativeIDS.get(ObjectNativeIDS.getInstance().players, id));
    }

    private int id;
    private String name;
    private World world;
    private Dialog dialog;
    private Weather weather;
    private LocalTime time;
    private Map<String, Object> properties = new HashMap<>();
    private NetStats netStats;
    private boolean freezed;

    public Player(int id) {
        this.id = id;
        this.name = FunctionAccess.GetPlayerName(id);
        ObjectNativeIDS.getInstance().players[id] = this;

        netStats = new NetStats(id);
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
     * Set a player property. Properties are like PVars, but a lot faster, since are java HashMap.
     * Note that properties cannot shared through AMX scripts.
     * @param property property name.
     * @param value property value.
     */
    public void setProperty(String property, Object value) {
        properties.put(property, value);
    }

    /**
     * Removes a property from player properties map.
     * @param property the property.
     */
    public void removeProperty(String property) {
        properties.remove(property);
    }

    /**
     * Check if the player contains the given property.
     * @param property which property.
     * @return true if properties HashMap contains the property, false otherwise.
     */
    public boolean containsProperty(String property) {
        return properties.containsKey(property);
    }

    /**
     * Return the value of a property.
     * @param property property name.
     * @param <T> generic to avoid casting.
     * @return the property value, or null if none.
     */
    @SuppressWarnings("unchecked")
    public <T> T getProperty(String property) {
        return (T) properties.get(property);
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

    public void setPosition(Vector3D position) {
        FunctionAccess.SetPlayerPos(id, position.getX(), position.getY(), position.getZ());
    }

    /**
     * Get this player position.
     *
     * @return player position.
     */
    public Vector3D getPosition() {
        return Vector3D.of(FunctionAccess.GetPlayerPos(id));
    }

    public void setFacingAngle(float angle) {
        FunctionAccess.SetPlayerFacingAngle(id, angle);
    }

    public float getFacingAngle() {
        return FunctionAccess.GetPlayerFacingAngle(id);
    }

    public void setSkin(SkinModel skin) {
        FunctionAccess.SetPlayerSkin(id, skin.getId());
    }

    public SkinModel getSkin() {
        return SkinModel.getById(FunctionAccess.GetPlayerSkin(id));
    }

    public void setFreezed(boolean freezed) {
        FunctionAccess.TogglePlayerControllable(id, freezed);
        this.freezed = freezed;
    }

    public boolean isFreezed() {
        return freezed;
    }

    public void setScore(int score) {
        FunctionAccess.SetPlayerScore(id, score);
    }

    public int getScore() {
        return FunctionAccess.GetPlayerScore(id);
    }

    public Optional<Vehicle> getVehicle() {
        return Vehicle.getById(FunctionAccess.GetPlayerVehicleID(id));
    }

    public VehicleSeat getSeat() {
        return VehicleSeat.getById(FunctionAccess.GetPlayerVehicleSeat(id));
    }

    public boolean isInAnyVehicle() {
        return FunctionAccess.IsPlayerInAnyVehicle(id);
    }

    public void putInVehicle(Vehicle vehicle, VehicleSeat seat) {
        FunctionAccess.PutPlayerInVehicle(id, vehicle.getId(), seat.getId());
    }

    public void setWorldBounds(Area area) {
        FunctionAccess.SetPlayerWorldBounds(id, area.getMax().getX(), area.getMin().getX(), area.getMax().getY(), area.getMin().getY());
    }

    public void removeWorldBounds() {
        FunctionAccess.SetPlayerWorldBounds(id, 20000.0000f, -20000.0000f, 20000.0000f, -20000.0000f);
    }

    public void applyAnimation(Animation animation, AnimationRunner runner) {
        applyAnimation(animation, runner, false);
    }

    public void applyAnimation(Animation animation, AnimationRunner runner, boolean forceSync) {
        runner.applyToPlayer(animation, id, forceSync);
    }

    public Animation getAnimation() {
        return Animation.of(FunctionAccess.GetPlayerAnimationIndex(id));
    }

    public void clearAnimations() {
        clearAnimations(false);
    }

    public void clearAnimations(boolean forceSync) {
        FunctionAccess.ClearAnimations(id, forceSync);
    }

    public void setSpecialAction(SpecialAction action) {
        FunctionAccess.SetPlayerSpecialAction(id, action.getValue());
    }

    public SpecialAction getSpecialAction() {
        return SpecialAction.get(FunctionAccess.GetPlayerSpecialAction(id));
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

    public KeyState getKeyState() {
        return new KeyState(FunctionAccess.GetPlayerKeys(id));
    }

    public void setCameraBehind() {
        FunctionAccess.SetCameraBehindPlayer(id);
    }

    public void setCameraPosition(Vector3D position) {
        FunctionAccess.SetPlayerCameraPos(id, position.getX(), position.getY(), position.getZ());
    }

    public Vector3D getCameraPosition() {
        return Vector3D.of(FunctionAccess.GetPlayerCameraPos(id));
    }

    public void setCameraLookAt(Vector3D whereToLook) {
        FunctionAccess.SetPlayerCameraLookAt(id, whereToLook.getX(), whereToLook.getY(), whereToLook.getZ(), SAMPConstants.CAMERA_CUT);
    }

    public Vector3D getCameraFrontVector() {
        return Vector3D.of(FunctionAccess.GetPlayerCameraFrontVector(id));
    }

    public Vector3D getCameraLookAt() {
        Vector3D frontCoordinates = getCameraFrontVector();
        Vector3D cameraPos = getCameraPosition();
        return cameraPos.plus(frontCoordinates);
    }

    public float getCameraZoom() {
        return FunctionAccess.GetPlayerCameraZoom(id);
    }

    public float getCameraAspectRatio() {
        return FunctionAccess.GetPlayerCameraAspectRatio(id);
    }

    public int getCameraMode() {
        return FunctionAccess.GetPlayerCameraMode(id);
    }

    public void setVarInt(String varname, int value) {
        FunctionAccess.SetPVarInt(id, varname, value);
    }

    public void setVarFloat(String varname, float value) {
        FunctionAccess.SetPVarFloat(id, varname, value);
    }

    public void setVarString(String varname, String value) {
        FunctionAccess.SetPVarString(id, varname, value);
    }

    public int getVarInt(String varname) {
        return FunctionAccess.GetPVarInt(id, varname);
    }

    public float getVarFloat(String varname) {
        return FunctionAccess.GetPVarFloat(id, varname);
    }

    public String getVarString(String varname) {
        return FunctionAccess.GetPVarString(id, varname);
    }

    public void deleteVar(String varname) {
        FunctionAccess.DeletePVar(id, varname);
    }

    public int getVarsUpperIndex() {
        return FunctionAccess.GetPVarsUpperIndex(id);
    }

    public String getVarNameAtIndex(int index) {
        return FunctionAccess.GetPVarNameAtIndex(id, index);
    }

    public VarType getVarType(String varname) {
        return VarType.get(FunctionAccess.GetPVarType(id, varname));
    }

    public PlayerState getState() {
        return PlayerState.get(FunctionAccess.GetPlayerState(id));
    }

    public void setHealth(float health) {
        FunctionAccess.SetPlayerHealth(id, health);
    }

    public float getHealth() {
        return FunctionAccess.GetPlayerHealth(id);
    }

    public void setArmour(float armour) {
        FunctionAccess.SetPlayerArmour(id, armour);
    }

    public float getArmour() {
        return FunctionAccess.GetPlayerArmour(id);
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

    public void gameText(GameTextStyle style, String message, Duration duration) {
        FunctionAccess.GameTextForPlayer(id, message, (int)duration.toMillis(), style.getValue());
    }

    public void setFightStyle(FightStyle fightStyle) {
        FunctionAccess.SetPlayerFightingStyle(id, fightStyle.getValue());
    }

    public FightStyle getFightStyle() {
        return FightStyle.get(FunctionAccess.GetPlayerFightingStyle(id));
    }

    public boolean isOnline() {
        return FunctionAccess.IsPlayerConnected(id);
    }

    public void setDrunkLevel(int level) {
        FunctionAccess.SetPlayerDrunkLevel(id, level);
    }

    public void giveMoney(int money) {
        FunctionAccess.GivePlayerMoney(id, money);
    }

    public void setMoney(int money) {
        FunctionAccess.ResetPlayerMoney(id);
        FunctionAccess.GivePlayerMoney(id, money);
    }

    public int getMoney(int money) {
        return FunctionAccess.GetPlayerMoney(id);
    }

    public int getPing() {
        return FunctionAccess.GetPlayerPing(id);
    }
    
    public int getDrunkLevel() {
        return FunctionAccess.GetPlayerDrunkLevel(id);
    }

    public String getIp() {
        return FunctionAccess.GetPlayerIp(id);
    }

    public String getName() {
        return name;
    }

    public void setWantedLevel(int level) {
        FunctionAccess.SetPlayerWantedLevel(id, level);
    }

    public int getWantedLevel() {
        return FunctionAccess.GetPlayerWantedLevel(id);
    }

    public void setChatBubble(String text, Color color, float drawDistance, Duration duration) {
        FunctionAccess.SetPlayerChatBubble(id, text, color.getRGBA(), drawDistance, (int)duration.toMillis());
    }

    public void setColor(Color color) {
        FunctionAccess.SetPlayerColor(id, color.getRGBA());
    }

    public Color getColor() {
        return Color.ofRGBA(FunctionAccess.GetPlayerColor(id));
    }

    public void setName(String name) throws InvalidNameException {
        int nameChangeResult = FunctionAccess.SetPlayerName(id, name);
        if (nameChangeResult == -1) throw new InvalidNameException(name);
        if (nameChangeResult == 0) {
            FunctionAccess.SetPlayerName(id, "changing..");
            FunctionAccess.SetPlayerName(id, name);
        }
        this.name = name;
    }

    public void kick(String reason) {
        SAMPServer.printLine(this.toString() + " has been kicked for: " + reason);
        FunctionAccess.Kick(id);
    }

    public void ban(String reason) {
        SAMPServer.printLine(this.toString() + " has been banned for: " + reason);
        FunctionAccess.BanEx(id, reason);
    }

    public float getDistanceToPoint(Vector3D pointCoordinates) {
        return FunctionAccess.GetPlayerDistanceFromPoint(id, pointCoordinates.getX(), pointCoordinates.getY(), pointCoordinates.getZ());
    }

    public boolean isInRangeOfPoint(Vector3D pointCoordinates, float toleranceDistance) {
        return FunctionAccess.IsPlayerInRangeOfPoint(id, toleranceDistance, pointCoordinates.getX(), pointCoordinates.getY(),
                pointCoordinates.getZ());
    }

    public Optional<Vehicle> getSurfingVehicle() {
        return Vehicle.getById(FunctionAccess.GetPlayerSurfingVehicleID(id));
    }

    public NetStats getNetStats() {
        return netStats;
    }

    public String getClientVersion() {
        return FunctionAccess.GetPlayerVersion(id);
    }

    /**
     * @return a string formatted like: "name (id)"
     */
    @Override
    public String toString() {
        return name + " (" + id + ")";
    }
}