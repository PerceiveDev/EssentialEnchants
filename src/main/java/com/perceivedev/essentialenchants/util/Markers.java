package com.perceivedev.essentialenchants.util;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.metadata.FixedMetadataValue;

import com.perceivedev.essentialenchants.EssentialEnchants;

/**
 * @author Rayzr
 *
 */
public class Markers {

    public static void set(Entity entity, String id) {
        id = id.trim().toUpperCase();
        entity.setMetadata("EssentialEnchants:" + id, new FixedMetadataValue(EssentialEnchants.getInstance(), true));
    }

    public static boolean get(Entity entity, String id) {
        id = id.trim().toUpperCase();
        // Should be fool proof enough, right?
        return entity.hasMetadata("EssentialEnchants:" + id);
    }

    /**
     * @param entity the entity to remove the marker from
     * @param id the marker's identifier
     */
    public static boolean remove(Projectile entity, String id) {
        if (entity.hasMetadata("EssentialEnchants:" + id)) {
            entity.removeMetadata("EssentialEnchants:" + id, EssentialEnchants.getInstance());
            return true;
        }
        return false;
    }

}
