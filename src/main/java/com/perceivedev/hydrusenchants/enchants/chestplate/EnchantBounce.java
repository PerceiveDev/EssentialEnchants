package com.perceivedev.hydrusenchants.enchants.chestplate;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

import com.perceivedev.hydrusenchants.ItemType;
import com.perceivedev.hydrusenchants.enchant.Rarity;
import com.perceivedev.hydrusenchants.enchant.types.Enchant;

/**
 * @author Rayzr
 *
 */
public class EnchantBounce extends Enchant {

    public EnchantBounce() {
        super(EntityDamageByEntityEvent.class);

        registerEventHandler(EntityDamageByEntityEvent.class, e -> {
            EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e;
            if (!(event.getEntity() instanceof Player && event.getDamager() instanceof Player)) {
                return;
            }
            Player player = (Player) event.getEntity();
            if (!isEnchanted(player.getInventory().getChestplate())) {
                return;
            }
            if (player.getHealth() > 5.0 || Math.random() >= 0.45) {
                return;
            }
            Vector velocity = event.getDamager().getLocation().toVector()
                    .subtract(player.getLocation().toVector())
                    .multiply(0.4).add(new Vector(0, 1.0, 0));
            event.getDamager().setVelocity(velocity);
        });
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.perceivedev.hydrusenchants.enchant.types.Enchant#getIdentifier()
     */
    @Override
    public String getIdentifier() {
        return "BOUNCE";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.perceivedev.hydrusenchants.enchant.types.Enchant#getDisplay()
     */
    @Override
    public String getDisplay() {
        return "Bounce";
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
        return Rarity.EPIC;
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
