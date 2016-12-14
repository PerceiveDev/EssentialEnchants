package com.perceivedev.essentialenchants.enchants.swords;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.perceivedev.essentialenchants.ItemType;
import com.perceivedev.essentialenchants.enchant.Rarity;
import com.perceivedev.essentialenchants.enchant.types.Enchant;

public class EnchantVampiric extends Enchant {

    public EnchantVampiric() {
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

            switch (level) {
                case 1:
                    if (Math.random() < 0.45) {
                        addHealth(player, Math.min(event.getDamage(), 0.5));
                    }
                    break;
                case 2:
                    if (Math.random() < 0.55) {
                        addHealth(player, Math.min(event.getDamage(), 1.0));
                    }
                    break;
                case 3:
                    if (Math.random() < 0.65) {
                        addHealth(player, Math.min(event.getDamage(), 1.5));
                    }
                    break;
                default:
                    break;
            }

        });
    }

    private void addHealth(Player player, double health) {
        player.setHealth(Math.min(player.getMaxHealth(), health));
    }

    @Override
    public String getIdentifier() {
        return "VAMPIRIC";
    }

    @Override
    public String getDisplay() {
        return "Vampiric";
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
