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
 * When a player calls a callback but with wrong values (example, OnVehicleDeath with an invalid vehicle id).
 * @author spell
 */
public class InvalidCallEvent extends AnticheatEvent {
    private String callbackName;

    public InvalidCallEvent(String callbackName) {
        this.callbackName = callbackName;
    }

    @Override
    public RecommendedAction getRecommendedAction() {
        return RecommendedAction.WARN;
    }

    @Override
    public String toString() {
        return "invalid call to " + callbackName;
    }
}