package com.perceivedev.hydrusenchants.enchants.helmet;

import java.util.Arrays;
import java.util.List;

import org.bukkit.inventory.ItemStack;

import com.perceivedev.hydrusenchants.ItemType;
import com.perceivedev.hydrusenchants.enchant.Rarity;
import com.perceivedev.hydrusenchants.enchant.types.Enchant;
import com.perceivedev.hydrusenchants.util.AttributeSlot;
import com.perceivedev.hydrusenchants.util.Attributes;

/**
 * @author Rayzr
 *
 */
public class EnchantFitness extends Enchant {

    /*
     * (non-Javadoc)
     * 
     * @see com.perceivedev.hydrusenchants.enchant.Enchant#getIdentifier()
     */
    @Override
    public String getIdentifier() {
        return "FITNESS";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.perceivedev.hydrusenchants.enchant.Enchant#getDisplay()
     */
    @Override
    public String getDisplay() {
        return "Fitness";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.perceivedev.hydrusenchants.enchant.Enchant#maxLevel()
     */
    @Override
    public int maxLevel() {
        return 3;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.perceivedev.hydrusenchants.enchant.Enchant#getRarity()
     */
    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.perceivedev.hydrusenchants.enchant.Enchant#getApplicableItems()
     */
    @Override
    public List<ItemType> getApplicableItems() {
        return Arrays.asList(ItemType.HELMET);
    }

    @Override
    public ItemStack apply(ItemStack item, int level) {
        return Attributes.addAttribute(
                Attributes.removeAttribute(item, "generic.maxHealth", AttributeSlot.HEAD),
                "generic.maxHealth", 0, Math.round(level * 6.66f), AttributeSlot.HEAD);
    }

}
