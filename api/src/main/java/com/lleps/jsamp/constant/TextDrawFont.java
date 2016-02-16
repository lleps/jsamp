package com.lleps.jsamp.constant;

/**
 * To be used with TextDraw and PlayerTextDraw.
 *
 * @author MK124
 */
public enum TextDrawFont
{
    DIPLOMA(0),
    FONT2(1),
    BANK_GOTHIC(2),
    PRICEDOWN(3),
    SPRITE_DRAW(4),
    MODEL_PREVIEW(5);


    public static TextDrawFont get(int value)
    {
        return values() [value];
    }


    private final int value;


    private TextDrawFont(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }
}