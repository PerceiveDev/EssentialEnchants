package com.perceivedev.essentialenchants.util.gui;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import com.perceivedev.essentialenchants.util.TextUtils;

/**
 * @author Rayzr
 *
 */
public abstract class Gui implements InventoryHolder {

    private Map<Slot, Icon> slots = new LinkedHashMap<>();
    private Inventory inventory;

    public Gui(String title, int rows) {
        Validate.isTrue(rows > 0, "rows must be greater than 0!");
        Validate.isTrue(rows <= 6, "rows must be no greater than 6!");
        this.inventory = Bukkit.createInventory(this, rows * 9, TextUtils.colorize(title));
        init();
    }

    public final void open(Player player) {
        render();
        player.openInventory(inventory);
    }

    public void render() {
        inventory.clear();
        for (Entry<Slot, Icon> e : slots.entrySet()) {
            inventory.setItem(e.getKey().rawPosition(), e.getValue().getItem());
        }
    }

    /**
     * Called when the
     */
    protected abstract void init();

    /**
     * Sets an icon for a slot
     * 
     * @param slot the slot of the icon
     * @param icon the {@link Icon icon} itself
     * @return the icon, or {@code null} if something went wrong
     */
    public Icon setIcon(Slot slot, Icon icon) {
        Objects.requireNonNull(slot, "slot can not be null");
        Objects.requireNonNull(icon, "icon can not be null");
        Validate.isTrue(slot.fitsInside(inventory.getSize()), String.format("Slot %s is outside of the bounds of the inventory!", slot.toString()));
        slots.put(slot, icon);
        return icon;
    }

    /**
     * Sets an icon for a slot
     * 
     * @param x the x position of the icon
     * @param y the y position of the icon
     * @param icon the {@link Icon icon} itself
     * @return the icon, or {@code null} if something went wrong
     */
    public Icon setIcon(int x, int y, Icon icon) {
        return setIcon(new Slot(x, y), icon);
    }

    /**
     * Attempts to find an {@link Icon icon} at the given slot
     * 
     * @param slot the slot to check
     * @return The {@link Optional optional} {@link Icon icon}
     */
    public Optional<Icon> getIcon(Slot slot) {
        Objects.requireNonNull(slot, "slot can not be null");
        return Optional.ofNullable(slots.get(slot));
    }

    /**
     * Attempts to find an {@link Icon icon} at the given slot
     * 
     * @param x the x position of the icon
     * @param y the y position of the icon
     * @return The {@link Optional optional} {@link Icon icon}
     */
    public Optional<Icon> getIcon(int x, int y) {
        return getIcon(new Slot(x, y));
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * @param event the {@link InventoryClickEvent raw event}
     */
    public void handleClick(InventoryClickEvent event) {
        ClickEvent e = new ClickEvent(event);
        e.setCancelled(true);

        Optional<Icon> icon = getIcon(e.getSlot() % 9, e.getSlot() / 9);

        if (icon.isPresent() && icon.get().getHandler() != null) {
            e.setIcon(icon.get());
            icon.get().getHandler().onClick(e);
            if (e.shouldCloseOnClick()) {
                e.getPlayer().closeInventory();
            }
        }
    }

}
