/**
 * 
 */
package com.perceivedev.essentialenchants.util;

import java.util.Objects;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * @author Rayzr
 *
 */
public class OneDotEightUtils {

    public static PotionEffect getPotionEffect(Player self, PotionEffectType type) {
        Objects.requireNonNull(self, "self cannot be null!");
        Objects.requireNonNull(type, "type cannot be null!");
        return self.getActivePotionEffects().stream().filter(eff -> eff.getType() == type).findFirst().orElse(null);
    }

}
