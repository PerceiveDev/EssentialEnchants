package com.perceivedev.hydrusenchants.enchants.boots;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.perceivedev.hydrusenchants.ItemType;
import com.perceivedev.hydrusenchants.enchant.Rarity;
import com.perceivedev.hydrusenchants.enchant.types.Enchant;

public class EnchantFeatherWeight extends Enchant {

	public EnchantFeatherWeight() {
		super(EntityDamageEvent.class);

		registerEventHandler(EntityDamageEvent.class, e -> {
			EntityDamageEvent event = (EntityDamageEvent) e;
			if (event.getCause() != DamageCause.FALL || event.getEntityType() != EntityType.PLAYER) {
				return;
			}
			Player player = (Player) event.getEntity();
			if (isEnchanted(player.getInventory().getBoots())) {
				event.setCancelled(true);
			}
		});
	}

	@Override
	public String getIdentifier() {
		// TODO Auto-generated method stub
		return "FEATHER_WEIGHT";
	}

	@Override
	public String getDisplay() {
		// TODO Auto-generated method stub
		return "FeatherWeight";
	}

	@Override
	public int maxLevel() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Rarity getRarity() {
		// TODO Auto-generated method stub
		return Rarity.LEGENDARY;
	}

	@Override
	public List<ItemType> getApplicableItems() {
		// TODO Auto-generated method stub
		return Arrays.asList(ItemType.BOOTS);
	}

}
