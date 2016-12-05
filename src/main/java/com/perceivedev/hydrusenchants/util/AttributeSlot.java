package com.perceivedev.hydrusenchants.util;

/**
 * @author Rayzr
 *
 */
public enum AttributeSlot {

    MAINHAND("mainhand"),
    OFFHAND("offhand"),
    HEAD("head"),
    CHEST("chest"),
    LEGS("legs"),
    FEET("feet");

    private final String internal;

    AttributeSlot(String internal) {
        this.internal = internal;
    }

    /**
     * @return the internal slot string
     */
    public String getInternal() {
        return internal;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return getInternal();
    }

}
