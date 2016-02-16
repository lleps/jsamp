package com.lleps.jsamp.constant;

/**
 * @author Leandro on 10/2/2016.
 */
public enum VehicleSeat {
    DRIVER(0),
    PASSENGER(1),
    BACK_LEFT(2),
    BACK_RIGHT(3),
    EXTRA_1(4),
    EXTRA_2(5),
    EXTRA_3(6),
    EXTRA_4(7);

    public static VehicleSeat getById(int id) {
        if (id >= 0 && id < values().length) {
            return values()[id];
        }
        return null;
    }

    private int id;

    VehicleSeat(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
