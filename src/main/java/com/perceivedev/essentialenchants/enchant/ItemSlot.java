package com.perceivedev.essentialenchants.enchant;

import java.util.function.Function;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/**
 * @author Rayzr
 *
 */
public enum ItemSlot {
    HELMET(p -> p.getHelmet()),
    CHESTPLATE(p -> p.getChestplate()),
    LEGGINGS(p -> p.getLeggings()),
    BOOTS(p -> p.getBoots()),
    @SuppressWarnings("deprecation")
    HAND(p -> p.getItemInHand());

    private final Function<PlayerInventory, ItemStack> itemGetter;

    private ItemSlot(Function<PlayerInventory, ItemStack> itemGetter) {
        this.itemGetter = itemGetter;
    }

    /**
     * @param player the player to get the item of
     * @return The item, or null if none was found (or null if {@code player} is
     *         null)
     */
    public ItemStack getItem(Player player) {
        return player == null ? null : itemGetter.apply(player.getInventory());
    }
}
