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
 * Cheat Detection accutare level. Should be used to take decisions.
 *
 * For example, a "LOW" accurate level may report the event to admins only.
 *
 * @author spell
 */
public enum  AccurateLevel {
    LOW, MEDIUM, HIGH
}