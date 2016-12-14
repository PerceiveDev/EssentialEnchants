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
    PICKAXE("Pickaxes", i -> i.getType().name().endsWith("_PICKAXE")),
    /**
     * Matches any type of sword
     */
    SWORD("Swords", i -> i.getType().name().endsWith("_SWORD")),
    /**
     * Matches any type of shovel
     */
    SHOVEL("Shovels", i -> i.getType().name().endsWith("_SPADE")),
    /**
     * Matches any type of axe
     */
    AXE("Axes", i -> i.getType().name().endsWith("_AXE")),
    /**
     * Matches bows
     */
    BOW("Bows", i -> i.getType() == Material.BOW),
    /**
     * Matches any type of tool / weapon (excluding fishing rods)
     */
    TOOLS("All tools", i -> PICKAXE.isValid(i) || SWORD.isValid(i) || SHOVEL.isValid(i) || AXE.isValid(i) || BOW.isValid(i)),
    /**
     * Matches fishing rods
     */
    FISHING_ROD("Fishing rods", i -> i.getType() == Material.FISHING_ROD),
    /**
     * Matches any type of helmet
     */
    HELMET("Helmets", i -> i.getType().name().endsWith("_HELMET")),
    /**
     * Matches any type of chestplate
     */
    CHESTPLATE("Chestplates", i -> i.getType().name().endsWith("_CHESTPLATE")),
    /**
     * Matches any type of leggings
     */
    LEGGINGS("Leggings", i -> i.getType().name().endsWith("_LEGGINGS")),
    /**
     * Matches any type of boots
     */
    BOOTS("Boots", i -> i.getType().name().endsWith("_BOOTS")),
    /**
     * Matches any type of armor
     */
    ARMOR("All armor", i -> HELMET.isValid(i) || CHESTPLATE.isValid(i) || LEGGINGS.isValid(i) || BOOTS.isValid(i)),
    /**
     * Matches any of the item types listed
     */
    ALL("All", i -> {
        for (ItemType type : ItemType.values()) {
            if (!type.name().equals("ALL") && type.isValid(i)) {
                return true;
            }
        }
        return false;
    });

    private final String display;
    private final Predicate<ItemStack> validator;

    private ItemType(String display, Predicate<ItemStack> validator) {
        this.display = display;
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

    /**
     * Returns the display name of this {@link ItemType item type}
     * 
     * @return The display name
     */
    public String getDisplay() {
        return display;
    }
}
