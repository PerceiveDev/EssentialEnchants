package com.perceivedev.hydrusenchants.util.gui;

import org.bukkit.inventory.ItemStack;

import com.perceivedev.hydrusenchants.util.ItemFactory;

/**
 * @author Rayzr
 *
 */
public class Icon {

    private ItemFactory  item;
    private ClickHandler handler;

    public Icon(ItemFactory item, ClickHandler handler) {
        this.item = item;
        this.handler = handler;
    }

    /**
     * @return the item
     */
    public ItemStack getItem() {
        return item.build();
    }

    /**
     * @return the {@link ClickHandler click handler}
     */
    public ClickHandler getHandler() {
        return handler;
    }

}
