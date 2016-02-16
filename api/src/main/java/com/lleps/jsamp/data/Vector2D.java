package com.lleps.jsamp.data;

import java.util.HashMap;

/**
 * Created by Leandro on 29/11/2015.
 */
public final class Vector2D implements Vector {

    private static HashMap<Integer, Vector2D> instances = new HashMap<>();

    public static Vector2D empty() {
        return of(0, 0);
    }

    public static Vector2D of(float[] xy) {
        if (xy.length != 2) throw new IllegalArgumentException("array length must be 2");
        return of(xy[0], xy[1]);
    }

    public static Vector2D of(float x, float y) {
        int key = generateHashCode(x, y);
        Vector2D result = instances.get(key);
        if (result == null) {
            result = new Vector2D(x, y);
            instances.put(key, result);
        }
        return result;
    }

    private final float x, y;

    private Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public Vector plusX(float amount) {
        return of(x+amount, y);
    }

    @Override
    public Vector minusX(float amount) {
        return of(x-amount, y);
    }

    @Override
    public Vector withX(float value) {
        return of(value, y);
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public Vector plusY(float amount) {
        return of(x, y+amount);
    }

    @Override
    public Vector minusY(float amount) {
        return of(x, y-amount);
    }

    @Override
    public Vector withY(float value) {
        return of(x, value);
    }

    @Override
    public float getZ() {
        return 0;
    }

    @Override
    public Vector plusZ(float amount) {
        return this;
    }

    @Override
    public Vector minusZ(float amount) {
        return this;
    }

    @Override
    public Vector withZ(float value) {
        return this;
    }

    @Override
    public float getW() {
        return 0;
    }

    @Override
    public Vector plusW(float amount) {
        return this;
    }

    @Override
    public Vector minusW(float amount) {
        return this;
    }

    @Override
    public Vector withW(float value) {
        return this;
    }

    @Override
    public boolean isNull() {
        return x == 0 && y == 0;
    }

    @Override
    public float length() {
        return (float) Math.sqrt(x*x + y*y);
    }

    public Vector3D asVector3D(float z) {
        return Vector3D.of(x, y, z);
    }

    @Override
    public float distanceTo(Vector target) {
        float x1 = target.getX() - x;
        float y1 = target.getY() - y;
        return (float) Math.sqrt((x1 * x1) + (y1 * y1));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector2D vector2D = (Vector2D) o;

        if (Float.compare(vector2D.x, x) != 0) return false;
        return Float.compare(vector2D.y, y) == 0;

    }

    @Override
    public int hashCode() {
        return generateHashCode(x, y);
    }

    private static int generateHashCode(float x, float y) {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        return result;
    }
}