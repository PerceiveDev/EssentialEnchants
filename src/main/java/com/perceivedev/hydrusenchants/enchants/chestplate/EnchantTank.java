package com.perceivedev.hydrusenchants.enchants.chestplate;

import java.util.Arrays;
import java.util.List;

import org.bukkit.potion.PotionEffectType;

import com.perceivedev.hydrusenchants.ItemType;
import com.perceivedev.hydrusenchants.enchant.ItemSlot;
import com.perceivedev.hydrusenchants.enchant.Rarity;
import com.perceivedev.hydrusenchants.enchant.types.PotionEnchant;

/**
 * @author Rayzr
 *
 */
public class EnchantTank extends PotionEnchant {

    public EnchantTank() {
        super(new PotionEnchantData(ItemSlot.CHESTPLATE, PotionEffectType.DAMAGE_RESISTANCE, lvl -> lvl),
                new PotionEnchantData(ItemSlot.LEGGINGS, PotionEffectType.DAMAGE_RESISTANCE, lvl -> lvl));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.perceivedev.hydrusenchants.enchant.types.Enchant#getIdentifier()
     */
    @Override
    public String getIdentifier() {
        return "TANK";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.perceivedev.hydrusenchants.enchant.types.Enchant#getDisplay()
     */
    @Override
    public String getDisplay() {
        return "Tank";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.perceivedev.hydrusenchants.enchant.types.Enchant#maxLevel()
     */
    @Override
    public int maxLevel() {
        return 3;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.perceivedev.hydrusenchants.enchant.types.Enchant#getRarity()
     */
    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.perceivedev.hydrusenchants.enchant.types.Enchant#getApplicableItems()
     */
    @Override
    public List<ItemType> getApplicableItems() {
        return Arrays.asList(ItemType.CHESTPLATE, ItemType.LEGGINGS);
    }

}
