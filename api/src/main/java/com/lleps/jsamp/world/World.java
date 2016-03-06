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
package com.lleps.jsamp.world;

import com.lleps.jsamp.constant.Interior;
import com.lleps.jsamp.player.Player;
import com.lleps.jsamp.constant.Weather;
import com.lleps.jsamp.world.entity.WorldEntity;

import java.time.LocalTime;
import java.util.Collection;

/**
 * World is an abstraction of SA:MP virtualWorld, linked to a worldId.
 *
 * @author spell
 */
public interface World {
    /**
     * Called when SAMPServer's weather changes.
     *
     * @param weather new weather.
     */
    void onParentWeatherChange(Weather weather);

    /**
     * Called when SAMPServer's time changes.
     *
     * @param time new time.
     */
    void onParentTimeChange(LocalTime time);

    /**
     * Called when a player enters this world.
     *
     * @param player the player who entered.
     */
    void onPlayerEnter(Player player);

    /**
     * Called when a player leaves this world, also called when a player
     * in this world disconnects from the server.
     *
     * @param player the player who exited.
     */
    void onPlayerExit(Player player);

    /**
     * Get players in this world.
     *
     * @return A collection of players.
     */
    Collection<Player> getPlayers();

    /**
     * Adds an entity to this world.
     *
     * @param entity the entity to add.
     */
    void addEntity(WorldEntity entity);

    /**
     * Removes an entity from this world.
     *
     * @param entity the entity to remove.
     */
    void removeEntity(WorldEntity entity);

    /**
     * Set world weather.
     *
     * @param weather weather.
     */
    void setWeather(Weather weather);

    /**
     * Get world weather, or SAMPServer's weather if null.
     *
     * @return This world's weather
     */
    Weather getWeather();

    /**
     * Set world time.
     *
     * @param time the time.
     */
    void setTime(LocalTime time);

    /**
     * Get world time, or SAMPServer's time if null.
     *
     * @return time.
     */
    LocalTime getTime();

    /**
     * Get this world's SA:MP id.
     *
     * @return this world id.
     */
    int getId();

    /**
     * Get this world's interior.
     *
     * @return world interior.
     */
    Interior getInterior();
}