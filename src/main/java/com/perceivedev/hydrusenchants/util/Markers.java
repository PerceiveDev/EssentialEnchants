package com.perceivedev.hydrusenchants.util;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.metadata.FixedMetadataValue;

import com.perceivedev.hydrusenchants.HydrusEnchants;

/**
 * @author Rayzr
 *
 */
public class Markers {

    public static void set(Entity entity, String id) {
        id = id.trim().toUpperCase();
        entity.setMetadata("HydrusEnchants:" + id, new FixedMetadataValue(HydrusEnchants.getInstance(), true));
    }

    public static boolean get(Entity entity, String id) {
        id = id.trim().toUpperCase();
        // Should be fool proof enough, right?
        return entity.hasMetadata("HydrusEnchants:" + id);
    }

    /**
     * @param entity the entity to remove the marker from
     * @param id the marker's identifier
     */
    public static boolean remove(Projectile entity, String id) {
        if (entity.hasMetadata("HydrusEnchants:" + id)) {
            entity.removeMetadata("HydrusEnchants:" + id, HydrusEnchants.getInstance());
            return true;
        }
        return false;
    }

}
