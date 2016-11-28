package com.perceivedev.hydrusenchants.util.gui;

import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * @author Rayzr
 *
 */
public interface ClickHandler {

    /**
     * Called when an {@link InventoryClickEvent} is accepted by an
     * {@link GuiListener}.
     * 
     * @param event the event
     */
    void onClick(ClickEvent event);

}
