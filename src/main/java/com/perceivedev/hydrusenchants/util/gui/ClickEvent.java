package com.perceivedev.hydrusenchants.util.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author Rayzr
 *
 */
public class ClickEvent {

    private InventoryClickEvent raw;
    private Icon                icon;
    /**
     * By default this is {@code false}. If set to {@code true}, the GUI will
     * close.
     */
    private boolean             closeOnClick = false;

    public ClickEvent(InventoryClickEvent event) {
        raw = event;
    }

    public InventoryClickEvent raw() {
        return raw;
    }

    public Player getPlayer() {
        return (Player) raw.getWhoClicked();
    }

    public ClickType getType() {
        return raw.getClick();
    }

    public int getSlot() {
        return raw.getSlot();
    }

    /**
     * @return
     * @see org.bukkit.event.inventory.InventoryInteractEvent#isCancelled()
     */
    public boolean isCancelled() {
        return raw.isCancelled();
    }

    /**
     * @param toCancel
     * @see org.bukkit.event.inventory.InventoryInteractEvent#setCancelled(boolean)
     */
    public void setCancelled(boolean toCancel) {
        raw.setCancelled(toCancel);
    }

    /**
     * @return
     * @see org.bukkit.event.inventory.InventoryClickEvent#getCursor()
     */
    public ItemStack getCursor() {
        return raw.getCursor();
    }

    /**
     * @return
     * @see org.bukkit.event.inventory.InventoryClickEvent#getCurrentItem()
     */
    public ItemStack getCurrentItem() {
        return raw.getCurrentItem();
    }

    /**
     * @return
     * @see org.bukkit.event.inventory.InventoryClickEvent#isRightClick()
     */
    public boolean isRightClick() {
        return raw.isRightClick();
    }

    /**
     * @return
     * @see org.bukkit.event.inventory.InventoryClickEvent#isLeftClick()
     */
    public boolean isLeftClick() {
        return raw.isLeftClick();
    }

    /**
     * @return
     * @see org.bukkit.event.inventory.InventoryClickEvent#isShiftClick()
     */
    public boolean isShiftClick() {
        return raw.isShiftClick();
    }

    /**
     * @param stack
     * @see org.bukkit.event.inventory.InventoryClickEvent#setCurrentItem(org.bukkit.inventory.ItemStack)
     */
    public void setCurrentItem(ItemStack stack) {
        raw.setCurrentItem(stack);
    }

    /**
     * @return the icon that was clicked
     */
    public Icon getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    void setIcon(Icon icon) {
        this.icon = icon;
    }

    /**
     * @return whether or not the GUI should {@link #closeOnClick close}.
     *         Defaults to {@code false}.
     */
    public boolean shouldCloseOnClick() {
        return closeOnClick;
    }

    /**
     * @param closeOnClick the value to set for {@link #closeOnClick}. Defaults
     *            to {@code false}.
     */
    public void setCloseOnClick(boolean closeOnClick) {
        this.closeOnClick = closeOnClick;
    }

}
