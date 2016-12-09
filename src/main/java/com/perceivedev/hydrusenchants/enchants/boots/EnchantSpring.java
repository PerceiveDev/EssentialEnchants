package com.perceivedev.hydrusenchants.enchants.boots;

import java.util.Arrays;
import java.util.List;

import org.bukkit.potion.PotionEffectType;

import com.perceivedev.hydrusenchants.ItemType;
import com.perceivedev.hydrusenchants.enchant.ItemSlot;
import com.perceivedev.hydrusenchants.enchant.Rarity;
import com.perceivedev.hydrusenchants.enchant.types.PotionEnchant;

public class EnchantSpring extends PotionEnchant {

	public EnchantSpring() {
		super(new PotionEnchantData(ItemSlot.BOOTS, PotionEffectType.JUMP, lvl -> lvl - 1));
	}

	@Override
	public String getIdentifier() {
		// TODO Auto-generated method stub
		return "SPRING";
	}

	@Override
	public String getDisplay() {
		// TODO Auto-generated method stub
		return "Spring";
	}

	@Override
	public int maxLevel() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public Rarity getRarity() {
		// TODO Auto-generated method stub
		return Rarity.EPIC;
	}

	@Override
	public List<ItemType> getApplicableItems() {
		// TODO Auto-generated method stub
		return Arrays.asList(ItemType.BOOTS);
	}

}
