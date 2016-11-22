package com.perceivedev.hydrusenchants;

import java.util.function.Predicate;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * @author Rayzr
 *
 */
public enum ItemType {
    PICKAXE(i -> i.getType().name().endsWith("_PICKAXE")),
    SWORD(i -> i.getType().name().endsWith("_SWORD")),
    SHOVEL(i -> i.getType().name().endsWith("_SPADE")),
    AXE(i -> i.getType().name().endsWith("_AXE")),
    TOOLS(i -> PICKAXE.isValid(i) || SWORD.isValid(i) || SHOVEL.isValid(i) || AXE.isValid(i)),
    FISHING_ROD(i -> i.getType() == Material.FISHING_ROD),
    HELMET(i -> i.getType().name().endsWith("_HELMET")),
    CHESTPLATE(i -> i.getType().name().endsWith("_CHESTPLATE")),
    LEGGINGS(i -> i.getType().name().endsWith("_LEGGINGS")),
    BOOTS(i -> i.getType().name().endsWith("_BOOTS")),
    ARMOR(i -> HELMET.isValid(i) || CHESTPLATE.isValid(i) || LEGGINGS.isValid(i) || BOOTS.isValid(i)),
    ALL(i -> {
        for (ItemType type : ItemType.values()) {
            if (type.isValid(i)) {
                return true;
            }
        }
        return false;
    });

    private final Predicate<ItemStack> validator;

    private ItemType(Predicate<ItemStack> validator) {
        this.validator = validator;
    }

    public boolean isValid(ItemStack item) {
        return validator.test(item);
    }
}
