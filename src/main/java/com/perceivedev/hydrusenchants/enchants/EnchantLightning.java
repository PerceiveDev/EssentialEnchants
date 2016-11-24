package com.perceivedev.hydrusenchants.enchants;

import java.util.Arrays;
import java.util.List;

import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import com.perceivedev.hydrusenchants.ItemType;
import com.perceivedev.hydrusenchants.enchant.Enchant;
import com.perceivedev.hydrusenchants.util.Markers;

/**
 * @author Rayzr
 *
 */
public class EnchantLightning extends Enchant {

    public EnchantLightning() {
        super(ProjectileLaunchEvent.class, ProjectileHitEvent.class);
        registerEventHandler(ProjectileLaunchEvent.class, e -> {
            ProjectileLaunchEvent event = (ProjectileLaunchEvent) e;
            Markers.set(event.getEntity(), getIdentifier());
        });
        registerEventHandler(ProjectileHitEvent.class, e -> {
            ProjectileHitEvent event = (ProjectileHitEvent) e;
            if (!Markers.get(event.getEntity(), getIdentifier())) {
                return;
            }
            event.getEntity().getWorld().strikeLightning(event.getEntity().getLocation());
        });
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.perceivedev.hydrusenchants.enchant.Enchant#getIdentifier()
     */
    @Override
    public String getIdentifier() {
        return "LIGHTNING";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.perceivedev.hydrusenchants.enchant.Enchant#getDisplay()
     */
    @Override
    public String getDisplay() {
        return "Lightning";
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
     * @see com.perceivedev.hydrusenchants.enchant.Enchant#getApplicableItems()
     */
    @Override
    public List<ItemType> getApplicableItems() {
        return Arrays.asList(ItemType.BOW);
    }

}
