package com.lleps.jsamp.constant;

/**
 * To be used with TextDraw and PlayerTextDraw.
 *
 * @author MK124
 */
public enum TextDrawAlign
{
    LEFT		(1),
    CENTER		(2),
    RIGHT		(3);


    public static TextDrawAlign get(int value)
    {
        return values() [value-1];
    }


    private final int value;


    private TextDrawAlign(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }
}