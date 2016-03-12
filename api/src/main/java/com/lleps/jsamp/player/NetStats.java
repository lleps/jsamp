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

import com.lleps.jsamp.FunctionAccess;

import java.time.Duration;

/**
 * @author spell
 */
public class NetStats {
    public enum ConnectionStatus {
        NO_ACTION,
        DISCONNECT_ASAP,
        DISCONNECT_ASAP_SILENTLY,
        DISCONNECT_ON_NO_ACK,
        REQUESTED_CONNECTION,
        HANDLING_CONNECTION_REQUEST,
        UNVERIFIED_SENDER,
        SET_ENCRYPTION_ON_MULTIPLE_16_BYTE_PACKET,
        CONNECTED
    }

    private final int playerId;

    NetStats(int playerId) {
        this.playerId = playerId;
    }

    public int getBytesReceived() {
        return FunctionAccess.NetStats_BytesReceived(playerId);
    }

    public int getBytesSent() {
        return FunctionAccess.NetStats_BytesSent(playerId);
    }

    public Duration getConnectedTime() {
        return Duration.ofMillis(FunctionAccess.NetStats_GetConnectedTime(playerId));
    }

    public float getPacketLossPercent() {
        return FunctionAccess.NetStats_PacketLossPercent(playerId);
    }

    public int getMessagesReceivedPerSecond() {
        return FunctionAccess.NetStats_MessagesRecvPerSecond(playerId);
    }

    public int getMessagesSent() {
        return FunctionAccess.NetStats_MessagesSent(playerId);
    }

    public int getMessagesReceived() {
        return FunctionAccess.NetStats_MessagesReceived(playerId);
    }

    public String getIpAndPort() {
        return FunctionAccess.NetStats_GetIpPort(playerId);
    }

    public ConnectionStatus getConnectionStatus() {
        return ConnectionStatus.values()[FunctionAccess.NetStats_ConnectionStatus(playerId)];
    }

    @Override
    public String toString() {
        return FunctionAccess.GetPlayerNetworkStats(playerId);
    }
}
