package com.perceivedev.essentialenchants.enchants.helmet;

import java.util.Arrays;
import java.util.List;

import org.bukkit.potion.PotionEffectType;

import com.perceivedev.essentialenchants.ItemType;
import com.perceivedev.essentialenchants.enchant.ItemSlot;
import com.perceivedev.essentialenchants.enchant.Rarity;
import com.perceivedev.essentialenchants.enchant.types.PotionEnchant;

/**
 * @author Rayzr
 *
 */
public class EnchantRage extends PotionEnchant {

    public EnchantRage() {
        super(new PotionEnchantData(ItemSlot.HELMET, PotionEffectType.INCREASE_DAMAGE, lvl -> 1),
                new PotionEnchantData(ItemSlot.CHESTPLATE, PotionEffectType.INCREASE_DAMAGE, lvl -> 1),
                new PotionEnchantData(ItemSlot.LEGGINGS, PotionEffectType.INCREASE_DAMAGE, lvl -> 1),
                new PotionEnchantData(ItemSlot.BOOTS, PotionEffectType.INCREASE_DAMAGE, lvl -> 1));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.perceivedev.hydrusenchants.enchant.types.Enchant#getIdentifier()
     */
    @Override
    public String getIdentifier() {
        return "RAGE";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.perceivedev.hydrusenchants.enchant.types.Enchant#getDisplay()
     */
    @Override
    public String getDisplay() {
        return "Rage";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.perceivedev.hydrusenchants.enchant.types.Enchant#maxLevel()
     */
    @Override
    public int maxLevel() {
        return 1;
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
        return Arrays.asList(ItemType.ARMOR);
    }

}
