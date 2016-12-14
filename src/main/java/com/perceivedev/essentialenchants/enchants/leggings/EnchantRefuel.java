package com.perceivedev.essentialenchants.enchants.leggings;

import java.util.Arrays;
import java.util.List;

import org.bukkit.potion.PotionEffectType;

import com.perceivedev.essentialenchants.ItemType;
import com.perceivedev.essentialenchants.enchant.ItemSlot;
import com.perceivedev.essentialenchants.enchant.Rarity;
import com.perceivedev.essentialenchants.enchant.types.PotionEnchant;

public class EnchantRefuel extends PotionEnchant {

    public EnchantRefuel() {
        super(new PotionEnchantData(ItemSlot.LEGGINGS, PotionEffectType.REGENERATION, lvl -> lvl - 1));
    }

    @Override
    public String getIdentifier() {
        // TODO Auto-generated method stub
        return "REFUEL";
    }

    @Override
    public String getDisplay() {
        // TODO Auto-generated method stub
        return "Refuel";
    }

    @Override
    public int maxLevel() {
        // TODO Auto-generated method stub
        return 3;
    }

    @Override
    public Rarity getRarity() {
        // TODO Auto-generated method stub
        return Rarity.LEGENDARY;
    }

    @Override
    public List<ItemType> getApplicableItems() {
        // TODO Auto-generated method stub
        return Arrays.asList(ItemType.LEGGINGS);
    }

}
