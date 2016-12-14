package com.perceivedev.essentialenchants.enchants.swords;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;

import com.perceivedev.essentialenchants.ItemType;
import com.perceivedev.essentialenchants.enchant.Rarity;
import com.perceivedev.essentialenchants.enchant.types.Enchant;

public class EnchantExperience extends Enchant {

    @SuppressWarnings("deprecation")
    public EnchantExperience() {
        super(EntityDeathEvent.class);

        registerEventHandler(EntityDeathEvent.class, e -> {
            EntityDeathEvent event = (EntityDeathEvent) e;
            Player player = event.getEntity().getKiller();
            if (player == null) {
                return;
            }

            if (!isEnchanted(player.getInventory().getItemInHand())) {
                return;
            }

            event.setDroppedExp(event.getDroppedExp() * 2);
        });
    }

    @Override
    public String getIdentifier() {
        return "EXPERIENCE";
    }

    @Override
    public String getDisplay() {
        return "Experience";
    }

    @Override
    public int maxLevel() {
        return 1;
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
