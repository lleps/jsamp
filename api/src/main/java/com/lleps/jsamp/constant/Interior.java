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

import com.lleps.jsamp.data.Vector3D;

/**
 * Abstraction of SA:MP interior id, with some optional useful information like a spawn point.
 * This class is immutable.
 *
 * @author spell
 */
public final class Interior {
    public static final Interior NONE = new Interior(0);
    public static final Interior INTERIOR_1 = new Interior(1);
    public static final Interior INTERIOR_2 = new Interior(2);
    public static final Interior INTERIOR_3 = new Interior(3);

    public static Interior ofId(int id) {
        return new Interior(id);
    }

    private final int id;

    private Interior(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Interior interior = (Interior) o;

        return id == interior.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
