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
 * When a player is desynced.
 * @author spell
 */
public class UnsyncEvent extends AnticheatEvent {
    public enum Type {
        POSITION,
        HEALTH,
        ARMOUR,
        VEHICLE_ID,
        VEHICLE_HEALTH,
        WEAPON_ID
    }

    private final Type type;
    private final String description;

    public UnsyncEvent(Type type, String description) {
        this.type = type;
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    @Override
    public RecommendedAction getRecommendedAction() {
        return RecommendedAction.KICK;
    }

    @Override
    public String toString() {
        return description;
    }
}