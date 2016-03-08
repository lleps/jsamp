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
package com.lleps.jsamp.constant.model;

import com.lleps.jsamp.data.Vector3D;

import java.util.HashMap;
import java.util.Map;

/**
 * @author spell
 */
public class BodyModel {
    private static final Map<Integer, BodyModel> instances = new HashMap<>();

    public static BodyModel getById(int id) {
        if (id < -1 || id > 20000) { // some arbitrary limit.
            throw new IllegalArgumentException("Invalid body model id (" + id + ")");
        }
        if (!instances.containsKey(id)) instances.put(id, new BodyModel(id));
        return instances.get(id);
    }

    private final int id;

    private BodyModel(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public Vector3D getSize() {
        throw new UnsupportedOperationException("object sizes not supported yet :/");
    }

    @Override
    public int hashCode() {
        return id;
    }
}