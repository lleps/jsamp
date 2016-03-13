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
public final class Vector3D implements Vector {

    public static Vector3D empty() {
        return of(0, 0, 0);
    }

    public static Vector3D of(float[] xyz) {
        if (xyz.length != 3) throw new IllegalArgumentException("array length must be 3");
        return of(xyz[0], xyz[1], xyz[2]);
    }

    public static Vector3D of(float x, float y, float z) {
        return new Vector3D(x, y, z);
    }

    private final float x, y, z;

    private Vector3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public Vector3D plusX(float amount) {
        return of(x+amount, y, z);
    }

    @Override
    public Vector3D minusX(float amount) {
        return of(x-amount, y, z);
    }

    @Override
    public Vector3D withX(float value) {
        return of(value, y, z);
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public Vector3D plusY(float amount) {
        return of(x, y+amount, z);
    }

    @Override
    public Vector3D minusY(float amount) {
        return of(x, y-amount, z);
    }

    @Override
    public Vector3D withY(float value) {
        return of(x, value, z);
    }

    @Override
    public float getZ() {
        return z;
    }

    @Override
    public Vector3D plusZ(float amount) {
        return of(x, y, z+amount);
    }

    @Override
    public Vector3D minusZ(float amount) {
        return of(x, y, z-amount);
    }

    @Override
    public Vector3D withZ(float value) {
        return of(x, y, value);
    }

    @Override
    public float getW() {
        return 0;
    }

    @Override
    public Vector3D plusW(float amount) {
        return this;
    }

    @Override
    public Vector3D minusW(float amount) {
        return this;
    }

    @Override
    public Vector3D withW(float value) {
        return this;
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
    public Vector3D plus(Vector other) {
        return of(x + other.getX(), y + other.getY(), z + other.getZ());
    }

    @Override
    public Vector3D minus(Vector other) {
        return of(x - other.getX(), y - other.getY(), z - other.getZ());
    }

    public Vector2D asVector2D() {
        return Vector2D.of(x, y);
    }

    public Vector4D asVector4D(float w) {
        return Vector4D.of(x, y, z, w);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector3D vector3D = (Vector3D) o;

        return Float.compare(vector3D.x, x) == 0 && Float.compare(vector3D.y, y) == 0 && Float.compare(vector3D.z, z) == 0;

    }

    @Override
    public int hashCode() {
        return generateHashCode(x, y, z);
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    private static int generateHashCode(float x, float y, float z) {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        result = 31 * result + (z != +0.0f ? Float.floatToIntBits(z) : 0);
        return result;
    }
}