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
package com.lleps.jsamp.data;

import com.lleps.jsamp.world.World;

import java.util.HashMap;
import java.util.Map;

/**
 * @author spell
 */
public final class Location {
    private final static Map<Integer, Location> instances = new HashMap<>();

    public static Location of(World world, Vector position) {
        int hash = generateHashCode(world, position);
        Location location = instances.get(hash);
        if (location == null) {
            location = new Location(world, position);
            instances.put(hash, location);
        }
        return location;
    }

    private World world;
    private Vector position;

    private Location(World world, Vector position) {
        this.world = world;
        this.position = position;
    }

    public World getWorld() {
        return world;
    }

    public Vector getPosition() {
        return position;
    }

    public Location atPosition(Vector position) {
        return of(world, position);
    }

    public Location atWorld(World world) {
        return of(world, position);
    }

    @Override
    public int hashCode() {
        return generateHashCode(world, position);
    }

    private static int generateHashCode(World world, Vector position) {
        int result = world.hashCode();
        result = 31 * result + position.hashCode();
        return result;
    }
}