package com.perceivedev.hydrusenchants.enchant;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import com.perceivedev.hydrusenchants.ItemType;

/**
 * @author Rayzr
 *
 */
public abstract class Enchant implements Listener {
    public abstract String getIdentifier();

    public abstract String getDisplay();

    public abstract int maxLevel();

    public abstract List<ItemType> getApplicableItems();

    public boolean isEnchanted(ItemStack item) {
        if (item == null || item.getType() == Material.AIR) {
            return false;
        }

        if (!item.getItemMeta().hasLore()) {
            return false;
        }

        Enchants enchants = Enchants.load(item.getItemMeta().getLore());

        // TODO: Check if the item has this enchant
        return true;
    }
}
