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
package com.lleps.jsamp.constant;

// Created by marvin on 28.12.14 in project shoebill-api.
// Copyright (c) 2014 Marvin Haschker. All rights reserved.
public enum GameTextStyle {
    NORMALE_MIDDLE (0),
    NORMAL_BOTTOM_RIGHT (1),
    GHETTO_MIDDLE (2),
    THIN_MIDDLE (3),
    THIN_MIDDLE_TOP (4),
    WHITE_THIN_CENTER (5),
    THICK_TOP (6);

    public static GameTextStyle get(int value)
    {
        return values() [value];
    }

    private final int value;

    private GameTextStyle(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }
}