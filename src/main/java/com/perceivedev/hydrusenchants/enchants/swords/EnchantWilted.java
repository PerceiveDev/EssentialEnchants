package com.perceivedev.hydrusenchants.enchants.swords;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.perceivedev.hydrusenchants.ItemType;
import com.perceivedev.hydrusenchants.enchant.Rarity;
import com.perceivedev.hydrusenchants.enchant.types.Enchant;

public class EnchantWilted extends Enchant {

    public EnchantWilted() {
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

            switch (level) {
                case 1:
                    if (Math.random() < 0.45) {
                        target.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 0));
                        target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0));
                    }
                    break;
                case 2:
                    if (Math.random() < 0.65) {
                        target.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 0));
                        target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0));
                    }
                    break;
                case 3:
                    if (Math.random() < 0.55) {
                        target.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 1));
                        target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 1));
                    }
                    break;
                default:
                    break;
            }

        });
    }

    @Override
    public String getIdentifier() {
        return "WILTED";
    }

    @Override
    public String getDisplay() {
        return "Wilted";
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
