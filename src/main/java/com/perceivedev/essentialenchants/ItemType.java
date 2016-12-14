package com.perceivedev.essentialenchants;

import java.util.function.Predicate;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Represents a type of item, like {@link #PICKAXE pickaxes} for example.
 * 
 * @author Rayzr
 */
public enum ItemType {
    /**
     * Matches any type of pickaxe
     */
    PICKAXE(i -> i.getType().name().endsWith("_PICKAXE")),
    /**
     * Matches any type of sword
     */
    SWORD(i -> i.getType().name().endsWith("_SWORD")),
    /**
     * Matches any type of shovel
     */
    SHOVEL(i -> i.getType().name().endsWith("_SPADE")),
    /**
     * Matches any type of axe
     */
    AXE(i -> i.getType().name().endsWith("_AXE")),
    /**
     * Matches bows
     */
    BOW(i -> i.getType() == Material.BOW),
    /**
     * Matches any type of tool / weapon (excluding fishing rods)
     */
    TOOLS(i -> PICKAXE.isValid(i) || SWORD.isValid(i) || SHOVEL.isValid(i) || AXE.isValid(i) || BOW.isValid(i)),
    /**
     * Matches fishing rods
     */
    FISHING_ROD(i -> i.getType() == Material.FISHING_ROD),
    /**
     * Matches any type of helmet
     */
    HELMET(i -> i.getType().name().endsWith("_HELMET")),
    /**
     * Matches any type of chestplate
     */
    CHESTPLATE(i -> i.getType().name().endsWith("_CHESTPLATE")),
    /**
     * Matches any type of leggings
     */
    LEGGINGS(i -> i.getType().name().endsWith("_LEGGINGS")),
    /**
     * Matches any type of boots
     */
    BOOTS(i -> i.getType().name().endsWith("_BOOTS")),
    /**
     * Matches any type of armor
     */
    ARMOR(i -> HELMET.isValid(i) || CHESTPLATE.isValid(i) || LEGGINGS.isValid(i) || BOOTS.isValid(i)),
    /**
     * Matches any of the item types listed
     */
    ALL(i -> {
        for (ItemType type : ItemType.values()) {
            if (!type.name().equals("ALL") && type.isValid(i)) {
                return true;
            }
        }
        return false;
    });

    private final Predicate<ItemStack> validator;

    private ItemType(Predicate<ItemStack> validator) {
        this.validator = validator;
    }

    /**
     * Returns if an item is a valid item for this {@link ItemType item type}
     * 
     * @param item the item to validate
     * @return If the item is a valid item for this {@link ItemType item type}
     */
    public boolean isValid(ItemStack item) {
        return validator.test(item);
    }
}
