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
package com.lleps.jsamp.data;

/**
 * @author spell
 */
public final class Area {
    private final Vector2D min;
    private final Vector2D max;

    public Area(Vector2D min, Vector2D max) {
        this.min = min;
        this.max = max;
    }

    public Vector2D getMax() {
        return max;
    }

    public Area withMax(Vector2D max) {
        return new Area(min, max);
    }

    public Vector2D getMin() {
        return min;
    }

    public Area withMin(Vector2D min) {
        return new Area(min, max);
    }

    public boolean isPointInside(Vector2D point) {
        return point.getX() > min.getX() && point.getX() < max.getX()
                && point.getY() > min.getY() && point.getY() < max.getY();
    }
}
