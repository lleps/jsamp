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

import com.lleps.jsamp.constant.model.VehicleModel;

/**
 * @author spell
 */
public enum Paintjob {
    DEFAULT(-1, "por defecto"),
    TYPE_1(0, "capa de pintura 1"),
    TYPE_2(1, "capa de pintura 2"),
    TYPE_3(2, "capa de pintura 3");

    private int id;
    private String name;

    Paintjob(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Paintjob getById(int id) {
        return values()[id + 1];
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isCompatibleWith(VehicleModel model) {
        return model.canUsePaintjobs();
    }

    @Override
    public String toString() {
        return name;
    }
}