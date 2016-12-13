package com.perceivedev.hydrusenchants.enchants.axes;

import java.util.Arrays;
import java.util.List;

import org.bukkit.potion.PotionEffectType;

import com.perceivedev.hydrusenchants.ItemType;
import com.perceivedev.hydrusenchants.enchant.ItemSlot;
import com.perceivedev.hydrusenchants.enchant.Rarity;
import com.perceivedev.hydrusenchants.enchant.types.PotionEnchant;

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
		return Arrays.asList(ItemType.AXE, ItemType.PICKAXE);
	}

}
