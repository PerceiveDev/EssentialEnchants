package com.perceivedev.essentialenchants.util.gui;

import org.apache.commons.lang.Validate;

/**
 * @author Rayzr
 *
 */
public class Slot {

    private int x;
    private int y;

    public Slot(int x, int y) {
        Validate.isTrue(x >= 0, "x can not be less than 0!");
        Validate.isTrue(y >= 0, "y can not be less than 0!");
        Validate.isTrue(x < 9, "x must be less than 9!");
        Validate.isTrue(y < 6, "y must be less than 6!");
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return x + y * 9;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Slot) {
            Slot slot = (Slot) obj;
            return x == slot.getX() && y == slot.getY();
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("%d,%d", x, y);
    }

}
