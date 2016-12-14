package com.perceivedev.essentialenchants.enchants.chestplate;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.perceivedev.essentialenchants.ItemType;
import com.perceivedev.essentialenchants.enchant.Rarity;
import com.perceivedev.essentialenchants.enchant.types.Enchant;

/**
 * @author Rayzr
 *
 */
public class EnchantRevival extends Enchant {

    public EnchantRevival() {
        super(PlayerDeathEvent.class);

        registerEventHandler(PlayerDeathEvent.class, e -> {
            PlayerDeathEvent event = (PlayerDeathEvent) e;
            if (isEnchanted(event.getEntity().getInventory().getChestplate())) {
                Location loc = event.getEntity().getLocation();
                for (int i = 0; i < 2; i++) {
                    Creeper creeper = (Creeper) loc.getWorld().spawnEntity(loc, EntityType.CREEPER);
                    creeper.setPowered(true);
                }
            }
        });
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.perceivedev.hydrusenchants.enchant.types.Enchant#getIdentifier()
     */
    @Override
    public String getIdentifier() {
        return "REVIVAL";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.perceivedev.hydrusenchants.enchant.types.Enchant#getDisplay()
     */
    @Override
    public String getDisplay() {
        return "Revival";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.perceivedev.hydrusenchants.enchant.types.Enchant#maxLevel()
     */
    @Override
    public int maxLevel() {
        return 1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.perceivedev.hydrusenchants.enchant.types.Enchant#getRarity()
     */
    @Override
    public Rarity getRarity() {
        return Rarity.ULTRA;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.perceivedev.hydrusenchants.enchant.types.Enchant#getApplicableItems()
     */
    @Override
    public List<ItemType> getApplicableItems() {
        return Arrays.asList(ItemType.CHESTPLATE);
    }

}
