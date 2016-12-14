package com.perceivedev.essentialenchants.enchants.swords;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.perceivedev.essentialenchants.ItemType;
import com.perceivedev.essentialenchants.enchant.Rarity;
import com.perceivedev.essentialenchants.enchant.types.Enchant;

public class EnchantBurst extends Enchant {

    public EnchantBurst() {
        super(EntityDamageByEntityEvent.class);

        registerEventHandler(EntityDamageByEntityEvent.class, e -> {
            EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e;
            if (!(event.getEntity() instanceof Player && event.getDamager() instanceof Player)) {
                return;
            }
            Player player = (Player) event.getDamager();
            @SuppressWarnings("deprecation")
            int level = getEnchantLevel(player.getInventory().getItemInHand());
            if (level < 0) {
                return;
            }

            Player target = (Player) event.getEntity();

            switch (level) {
                case 1:
                    if (Math.random() < 0.45) {
                        target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 0));
                    }
                    if (Math.random() < 0.40) {
                        target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0));
                    }
                    break;
                case 2:
                    if (Math.random() < 0.55) {
                        target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 0));
                    }
                    if (Math.random() < 0.50) {
                        target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0));
                    }
                    break;
                case 3:
                    if (Math.random() < 0.50) {
                        target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 1));
                    }
                    if (Math.random() < 0.45) {
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
        return "BURST";
    }

    @Override
    public String getDisplay() {
        return "Burst";
    }

    @Override
    public int maxLevel() {
        return 3;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.ULTRA;
    }

    @Override
    public List<ItemType> getApplicableItems() {
        return Arrays.asList(ItemType.SWORD);
    }

}
