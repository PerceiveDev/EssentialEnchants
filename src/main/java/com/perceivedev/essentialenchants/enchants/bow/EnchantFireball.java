package com.perceivedev.essentialenchants.enchants.bow;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.util.Vector;

import com.perceivedev.essentialenchants.ItemType;
import com.perceivedev.essentialenchants.enchant.Rarity;
import com.perceivedev.essentialenchants.enchant.types.Enchant;

/**
 * @author Rayzr
 *
 */
public class EnchantFireball extends Enchant {

    @SuppressWarnings("deprecation")
    public EnchantFireball() {
        super(ProjectileLaunchEvent.class);

        registerEventHandler(ProjectileLaunchEvent.class, e -> {
            ProjectileLaunchEvent event = (ProjectileLaunchEvent) e;
            if (!(event.getEntity().getShooter() instanceof Player)) {
                return;
            }
            Player p = (Player) event.getEntity().getShooter();
            if (!isEnchanted(p.getItemInHand())) {
                return;
            }
            Location loc = event.getEntity().getLocation().clone();
            Vector velocity = event.getEntity().getVelocity();

            event.getEntity().remove();

            Fireball fb = (Fireball) loc.getWorld().spawnEntity(loc, EntityType.FIREBALL);
            fb.setShooter(p);
            fb.setDirection(velocity.normalize());
            fb.setYield(0.0f);
            fb.setIsIncendiary(true);
        });
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.perceivedev.hydrusenchants.enchant.Enchant#getIdentifier()
     */
    @Override
    public String getIdentifier() {
        return "FIREBALL";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.perceivedev.hydrusenchants.enchant.Enchant#getDisplay()
     */
    @Override
    public String getDisplay() {
        return "Fireball";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.perceivedev.hydrusenchants.enchant.Enchant#maxLevel()
     */
    @Override
    public int maxLevel() {
        return 1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.perceivedev.hydrusenchants.enchant.Enchant#getRarity()
     */
    @Override
    public Rarity getRarity() {
        return Rarity.ULTRA;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.perceivedev.hydrusenchants.enchant.Enchant#getApplicableItems()
     */
    @Override
    public List<ItemType> getApplicableItems() {
        return Arrays.asList(ItemType.BOW);
    }

}
