package com.perceivedev.essentialenchants.enchants.axes;

import java.util.Arrays;
import java.util.List;

import org.bukkit.potion.PotionEffectType;

import com.perceivedev.essentialenchants.ItemType;
import com.perceivedev.essentialenchants.enchant.ItemSlot;
import com.perceivedev.essentialenchants.enchant.Rarity;
import com.perceivedev.essentialenchants.enchant.types.PotionEnchant;

public class EnchantQuickened extends PotionEnchant {

    public EnchantQuickened() {
        super(new PotionEnchantData(ItemSlot.HAND, PotionEffectType.FAST_DIGGING, lvl -> 1));
    }

    @Override
    public String getIdentifier() {
        // TODO Auto-generated method stub
        return "QUICKENED";
    }

    @Override
    public String getDisplay() {
        // TODO Auto-generated method stub
        return "Quickened";
    }

    @Override
    public int maxLevel() {
        // TODO Auto-generated method stub
        return 1;
    }

    @Override
    public Rarity getRarity() {
        // TODO Auto-generated method stub
        return Rarity.EPIC;
    }

    @Override
    public List<ItemType> getApplicableItems() {
        // TODO Auto-generated method stub
        return Arrays.asList(ItemType.AXE, ItemType.PICKAXE, ItemType.SWORD);
    }

}
