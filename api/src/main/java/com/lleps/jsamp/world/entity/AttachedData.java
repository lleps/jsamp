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
package com.lleps.jsamp.world.entity;

import com.lleps.jsamp.data.Vector3D;

/**
 * @author spell
 */
public class AttachedData<T extends WorldEntity> {
    private T source;
    private Vector3D offSets;
    private Vector3D rotation;

    public AttachedData(T source, Vector3D offSets) {
        this(source, offSets, Vector3D.empty());
    }

    public AttachedData(T source, Vector3D offSets, Vector3D rotation) {
        this.source = source;
        this.offSets = offSets;
        this.rotation = rotation;
    }

    public T getSource() {
        return source;
    }

    public Vector3D getOffSets() {
        return offSets;
    }

    public Vector3D getRotation() {
        return rotation;
    }
}