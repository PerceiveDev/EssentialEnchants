package com.perceivedev.hydrusenchants.util.gui;

import org.apache.commons.lang.Validate;

/**
 * @author Rayzr
 *
 */
public class Slot {

    private int x;
    private int y;

    public Slot(int x, int y) {
        Validate.isTrue(x > 0, "x can not be less than 0!");
        Validate.isTrue(y > 0, "y can not be less than 0!");
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    public int rawPosition() {
        return x + y * 9;
    }

    public boolean fitsInside(int width, int height) {
        return fitsInside(width + height * 9);
    }

    public boolean fitsInside(int size) {
        return rawPosition() < size;
    }

}
