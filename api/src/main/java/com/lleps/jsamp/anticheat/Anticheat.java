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
package com.lleps.jsamp.anticheat;

import com.lleps.jsamp.server.SAMPServer;

import static com.lleps.jsamp.SAMPConstants.MAX_PLAYERS;
import static com.lleps.jsamp.SAMPConstants.MAX_VEHICLES;

/**
 * @author spell
 */
public class Anticheat {
    private final ACPlayer[] players = new ACPlayer[MAX_PLAYERS];
    private final ACVehicle[] vehicles = new ACVehicle[MAX_VEHICLES];

    private final SAMPServer server;
    private int unsyncSecondsToTimeout = 12;

    public Anticheat(SAMPServer server) {
        this.server = server;
    }

    public ACPlayer[] getPlayers() {
        return players;
    }

    public ACVehicle[] getVehicles() {
        return vehicles;
    }

    public SAMPServer getServer() {
        return server;
    }

    public void setUnsyncSecondsToTimeout(int unsyncSecondsToTimeout) {
        this.unsyncSecondsToTimeout = unsyncSecondsToTimeout;
    }

    public int getUnsyncSecondsToTimeout() {
        return unsyncSecondsToTimeout;
    }

    public boolean isConnected(int id) {
        return id >= 0 && id < MAX_PLAYERS && players[id] != null && players[id].isConnected();
    }

    public boolean isValidVehicle(int id) {
        return id >= 0 && id < MAX_VEHICLES && vehicles[id] != null;
    }
}