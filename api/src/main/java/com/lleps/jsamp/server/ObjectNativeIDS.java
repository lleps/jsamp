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
package com.lleps.jsamp.server;

import com.lleps.jsamp.player.Player;
import com.lleps.jsamp.SAMPConstants;
import com.lleps.jsamp.world.entity.*;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Singleton class that holds relationship between SA:MP object ids and their JSAMP abstraction.
 * Arrays are public because GLobalEntity and PerPlayerEntity need access to it for set or
 * get the references.
 *
 * @author spell
 */
public class ObjectNativeIDS {
    private final static ObjectNativeIDS instance = new ObjectNativeIDS();

    public static ObjectNativeIDS getInstance() {
        return instance;
    }

    public final Player[] players = new Player[SAMPConstants.MAX_PLAYERS];
    public final Body[][] playerObjects = new Body[SAMPConstants.MAX_PLAYERS][SAMPConstants.MAX_OBJECTS];
    public final Pickup[] pickups = new Pickup[SAMPConstants.MAX_PICKUPS];
    public final Vehicle[] vehicles = new Vehicle[SAMPConstants.MAX_VEHICLES];
    public final Label[][] playerLabels = new Label[SAMPConstants.MAX_PLAYERS][SAMPConstants.MAX_3DTEXT_PLAYER];
    public final Actor[] actors = new Actor[SAMPConstants.MAX_ACTORS];

    public static <T> T get(T[] array, int entityId) {
        if (entityId >= 0 && entityId < array.length) {
            return array[entityId];
        }
        return null;
    }

    public static <T> T get(T[][] array, int playerId, int entityId) {
        if ((entityId >= 0 && entityId < array.length) && (entityId >= 0 && entityId < array[0].length)) {
            return array[playerId][entityId];
        }
        return null;
    }

    public static <T> Collection<T> getNotNullElements(T[] array) {
        Collection<T> collection = new LinkedList<>();
        for (T element : array) if (element != null) collection.add(element);
        return collection;
    }
}