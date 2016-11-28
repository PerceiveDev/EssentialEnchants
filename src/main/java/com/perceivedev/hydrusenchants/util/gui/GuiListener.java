package com.perceivedev.hydrusenchants.util.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;

/**
 * @author Rayzr
 *
 */
public class GuiListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onClick(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof Gui)) {
            return;
        }

        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }

        if (e.getSlotType() != SlotType.CONTAINER) {
            return;
        }

        Gui gui = (Gui) e.getInventory().getHolder();

        gui.handleClick(e);
    }

}
