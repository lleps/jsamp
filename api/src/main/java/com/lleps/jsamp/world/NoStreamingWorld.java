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

import com.google.common.base.Preconditions;
import com.lleps.jsamp.FunctionAccess;
import com.lleps.jsamp.constant.Interior;
import com.lleps.jsamp.server.SAMPServer;
import com.lleps.jsamp.player.Player;
import com.lleps.jsamp.constant.Weather;
import com.lleps.jsamp.world.entity.WorldEntity;
import org.apache.commons.lang3.RandomUtils;

import java.time.LocalTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * This world implementation don't stream nothing. For obviously reasons, is the fastest.
 * Useful when you know the world wont have too many entities.
 *
 * @author spell
 */
public class NoStreamingWorld implements World {

    private Set<WorldEntity> entities;
    private Set<Player> players;
    private final int worldId;

    private Weather weather;
    private LocalTime time;

    private Interior interior;

    public NoStreamingWorld(Interior interior) {
        this.interior = Preconditions.checkNotNull(interior);
        this.worldId = RandomUtils.nextInt(0, Integer.MAX_VALUE);
        entities = new HashSet<>();
        players = new HashSet<>();
    }

    @Override
    public Interior getInterior() {
        return interior;
    }

    @Override
    public void onPlayerEnter(Player player) {
        int playerId = player.getId();
        int interiorId = interior.getId();
        int worldId = this.worldId;

        for (WorldEntity entity : entities) {
            if (!entity.isCreated(playerId)) entity.create(playerId, worldId, interiorId);
        }
        FunctionAccess.SetPlayerVirtualWorld(player.getId(), this.worldId);
        players.add(player);
        player.onParentTimeChange(getTime());
        player.onParentWeatherChange(getWeather());
    }

    @Override
    public void onPlayerExit(Player player) {
        int playerId = player.getId();
        for (WorldEntity entity : entities) {
            entity.destroy(playerId);
        }
        players.remove(player);
    }

    @Override
    public void onParentWeatherChange(Weather weather) {
        if (this.weather == null) {
            players.forEach(p -> p.onParentWeatherChange(weather));
        }
    }

    @Override
    public void onParentTimeChange(LocalTime time) {
        if (this.time == null) {
            players.forEach(p -> p.onParentTimeChange(time));
        }
    }

    @Override
    public void setWeather(Weather weather) {
        this.weather = weather;
        players.forEach(p -> p.onParentWeatherChange(weather));
    }

    @Override
    public Weather getWeather() {
        return weather != null ? weather : SAMPServer.getWeather();
    }

    @Override
    public void setTime(LocalTime time) {
        this.time = time;
        players.forEach(p -> p.onParentTimeChange(time));
    }

    @Override
    public LocalTime getTime() {
        return time != null ? time : SAMPServer.getTime();
    }

    @Override
    public void addEntity(WorldEntity entity) {
        for (Player player : players) {
            entity.create(player.getId(), worldId, interior.getId());
        }
        entities.add(entity);
    }

    @Override
    public void removeEntity(WorldEntity entity) {
        for (Player player : players) {
            entity.destroy(player.getId());
        }
        entities.remove(entity);
    }

    @Override
    public Collection<Player> getPlayers() {
        return players;
    }

    @Override
    public int getId() {
        return worldId;
    }
}