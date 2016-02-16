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
package com.lleps.jsamp.world;

import com.lleps.jsamp.data.Vector3D;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Base class for common tasks to all entities.
 *
 * @author spell
 */
public abstract class BaseEntity implements WorldEntity {
    private Collection<OnPositionChangeListener> onPositionChangeListeners;

    protected BaseEntity() {
        onPositionChangeListeners = new LinkedList<>();
    }

    @Override
    public void addOnPositionChangeListener(OnPositionChangeListener listener) {
        onPositionChangeListeners.add(listener);
    }

    @Override
    public void removeOnPositionChangeListener(OnPositionChangeListener listener) {
        onPositionChangeListeners.remove(listener);
    }

    protected void dispatchPositionChange(Vector3D oldPosition, Vector3D newPosition) {
        onPositionChangeListeners.forEach(l -> l.onPositionChange(this, oldPosition, newPosition));
    }
}