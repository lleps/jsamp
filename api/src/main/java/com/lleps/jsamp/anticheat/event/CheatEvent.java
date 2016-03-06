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
package com.lleps.jsamp.anticheat.event;

/**
 * This class represents an event that occurs when a player has an illegal state.
 * For example, spawning without SpawnPlayer or dead when is already dead.
 * @author spell
 */
public class CheatEvent extends AnticheatEvent {
    public enum Type {
        CONNECT_AS_NPC,
        CONNECTION_FLOOD,
        STATECHANGE_FLOOD,
        ENTER_LOCKED_VEHICLE,
        ENTER_UNREQUESTED_VEHICLE,
        INVALID_SPAWN,
        SPAWN_WHEN_ALIVE,
        DEATH_WHEN_NOT_ALIVE,
        JETPACK,
        SPEED_HACK,
        VEHICLE_TELEPORT,
        WEAPON_HACK,
        AMMO_HACK,
        POSITION_HACK,
        HEALTH_HACK,
        ARMOUR_HACK,
        VEHICLE_HEALTH_HACK,
        INVALID_MODSHOP_ENTER,
        INVALID_MODSHOP_EXIT,
        TUNNING_HACK,
        PAINTJOB_HACK,
        RESPRAY_HACK,
    }

    private final AccurateLevel accurateLevel;
    private final Type type;
    private final String description;

    public CheatEvent(AccurateLevel accurateLevel, Type type, String description) {
        this.accurateLevel = accurateLevel;
        this.type = type;
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public AccurateLevel getAccurateLevel() {
        return accurateLevel;
    }

    @Override
    public RecommendedAction getRecommendedAction() {
        if (accurateLevel == AccurateLevel.HIGH) return RecommendedAction.BAN;
        else if (accurateLevel == AccurateLevel.MEDIUM) return RecommendedAction.KICK;
        else return RecommendedAction.WARN;
    }

    @Override
    public String toString() {
        return description;
    }
}
