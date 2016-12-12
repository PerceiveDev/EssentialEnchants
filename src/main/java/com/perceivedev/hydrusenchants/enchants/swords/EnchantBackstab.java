package com.perceivedev.hydrusenchants.enchants.swords;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.perceivedev.hydrusenchants.ItemType;
import com.perceivedev.hydrusenchants.enchant.Rarity;
import com.perceivedev.hydrusenchants.enchant.types.Enchant;

public class EnchantBackstab extends Enchant {

	public EnchantBackstab() {
		super(EntityDamageByEntityEvent.class);

		registerEventHandler(EntityDamageByEntityEvent.class, e -> {
			EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e;
			if (!(event.getEntity() instanceof Player && event.getDamager() instanceof Player)) {
				return;
			}
			Player player = (Player) event.getDamager();
			int level = getEnchantLevel(player.getInventory().getItemInMainHand());
			if (level < 0) {
				return;
			}

			Player target = (Player) event.getEntity();

			Vector towards = target.getEyeLocation().toVector().subtract(player.getEyeLocation().toVector())
					.normalize();
			Vector actual = target.getEyeLocation().getDirection();

			double distance = actual.subtract(towards).length();
			if (distance < 0.85) {
				switch (level) {
				case 1:
					if (Math.random() < 0.45) {
						target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0));
					}
					if (Math.random() < 0.50) {
						target.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 0));
					}
					break;
				case 2:
					if (Math.random() < 0.55) {
						target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0));
					}
					if (Math.random() < 0.60) {
						target.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 0));
					}
					break;
				case 3:
					if (Math.random() < 0.50) {
						target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 1));
					}
					if (Math.random() < 0.55) {
						target.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 1));
					}
					break;
				default:
					break;
				}
			}

		});
	}

	@Override
	public String getIdentifier() {
		return "BACKSTAB";
	}

	@Override
	public String getDisplay() {
		return "Backstab";
	}

	@Override
	public int maxLevel() {
		return 3;
	}

	@Override
	public Rarity getRarity() {
		return Rarity.EPIC;
	}

	@Override
	public List<ItemType> getApplicableItems() {
		return Arrays.asList(ItemType.SWORD);
	}

}
