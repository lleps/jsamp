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

import java.util.HashMap;

/**
 * @author spell
 */
public final class Vector4D implements Vector {
    private static HashMap<Integer, Vector4D> instances = new HashMap<>();

    public static Vector4D empty() {
        return of(0, 0, 0, 0);
    }

    public static Vector4D of(float[] xyz) {
        if (xyz.length != 4) throw new IllegalArgumentException("array length must be 4");
        return of(xyz[0], xyz[1], xyz[2], xyz[3]);
    }

    public static Vector4D of(float x, float y, float z, float w) {
        return new Vector4D(x, y, z, w);
    }

    private final float x, y, z, w;

    private Vector4D(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public Vector4D plusX(float amount) {
        return of(x+amount, y, z, w);
    }

    @Override
    public Vector4D minusX(float amount) {
        return of(x-amount, y, z, w);
    }

    @Override
    public Vector4D withX(float value) {
        return of(value, y, z, w);
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public Vector4D plusY(float amount) {
        return of(x, y+amount, z, w);
    }

    @Override
    public Vector4D minusY(float amount) {
        return of(x, y-amount, z, w);
    }

    @Override
    public Vector4D withY(float value) {
        return of(x, value, z, w);
    }

    @Override
    public float getZ() {
        return z;
    }

    @Override
    public Vector4D plusZ(float amount) {
        return of(x, y, z+amount, w);
    }

    @Override
    public Vector4D minusZ(float amount) {
        return of(x, y, z-amount, w);
    }

    @Override
    public Vector4D withZ(float value) {
        return of(x, y, value, w);
    }

    @Override
    public float getW() {
        return w;
    }

    @Override
    public Vector4D plusW(float amount) {
        return of(x, y, z, w+amount);
    }

    @Override
    public Vector4D minusW(float amount) {
        return of(x, y, z, w-amount);
    }

    @Override
    public Vector4D withW(float value) {
        return of(x, y, z, value);
    }

    @Override
    public boolean isNull() {
        return x == 0 && y == 0 && z == 0;
    }

    @Override
    public float length() {
        return (float) Math.sqrt(x*x + y*y + z*z);
    }

    @Override
    public float distanceTo(Vector target) {
        float x1 = target.getX() - x;
        float y1 = target.getY() - y;
        float z1 = target.getZ() - z;
        return (float) Math.sqrt((x1 * x1) + (y1 * y1) + (z1 * z1));
    }

    @Override
    public Vector4D plus(Vector other) {
        return of(x + other.getX(), y + other.getY(), z + other.getZ(), w + other.getW());
    }

    @Override
    public Vector4D minus(Vector other) {
        return of(x - other.getX(), y - other.getY(), z - other.getZ(), w + other.getW());
    }

    public Vector3D asVector3D() {
        return Vector3D.of(x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector4D vector4D = (Vector4D) o;

        return Float.compare(vector4D.x, x) == 0 && Float.compare(vector4D.y, y) == 0
                && Float.compare(vector4D.z, z) == 0
                && Float.compare(vector4D.w, w) == 0;
    }

    @Override
    public int hashCode() {
        return generateHashCode(x, y, z, w);
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", w=" + w +
                '}';
    }

    private static int generateHashCode(float x, float y, float z, float w) {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        result = 31 * result + (z != +0.0f ? Float.floatToIntBits(z) : 0);
        result = 31 * result + (w != +0.0f ? Float.floatToIntBits(w) : 0);
        return result;
    }
}