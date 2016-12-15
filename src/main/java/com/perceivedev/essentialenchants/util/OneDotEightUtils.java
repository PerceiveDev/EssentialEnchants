/**
 * 
 */
package com.perceivedev.essentialenchants.util;

import java.util.Objects;

import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.perceivedev.essentialenchants.enchant.types.Enchant;

/**
 * @author Rayzr
 *
 */
public class OneDotEightUtils {

    private static boolean OFF_HAND = false;

    static {
        // Hacky way to check if this server supports the off-hand slot
        try {
            EquipmentSlot.OFF_HAND.name();
            OFF_HAND = true;
        } catch (EnumConstantNotPresentException e) {
            // Ignore
        }
    }

    /**
     * For some reason, this method is not preset in the 1.8 API for players. I
     * had to manually create it.
     * 
     * @param self the player to check
     * @param type the {@link PotionEffectType} to check for
     * @return The {@link PotionEffect}, or <code>null</code> if it wasn't found
     *         (just like the newer API)
     */
    public static PotionEffect getPotionEffect(Player self, PotionEffectType type) {
        Objects.requireNonNull(self, "self cannot be null!");
        Objects.requireNonNull(type, "type cannot be null!");
        return self.getActivePotionEffects().stream().filter(eff -> eff.getType() == type).findFirst().orElse(null);
    }

    /**
     * A cross-compatibility method for checking the off-hand only when the
     * server is running a version with off-hand support (1.9+)
     * 
     * @param self the player to check (checks main-hand, then off-hand)
     * @param enchant the enchantment to check for
     * @return The level of the enchant, or -1 if it is not present in either
     *         main-hand or off-hand
     */
    public static int getHandLevel(Player self, Enchant enchant) {
        PlayerInventory inv = self.getInventory();
        @SuppressWarnings("deprecation")
        int lvl = enchant.getEnchantLevel(inv.getItemInHand());
        if (lvl > -1) {
            return lvl;
        }
        if (OFF_HAND) {
            lvl = enchant.getEnchantLevel(inv.getItemInOffHand());
            if (lvl > -1) {
                return lvl;
            }
        }
        return -1;
    }

    /**
     * A cross-compatibility method for checking the off-hand only when the
     * server is running a version with off-hand support (1.9+)
     * <br>
     * <br>
     * This is just a simple alias for doing the following:
     * 
     * <pre>
     * OneDotEightUtils.getHandLevel(self, enchant) != -1
     * </pre>
     * 
     * @param self the player to check (checks main-hand, then off-hand)
     * @param enchant the enchantment to check for
     * @return Whether or not the player is holding an item with the given
     *         enchant in either main-hand or off-hand
     */
    public static boolean isHandEnchanted(Player self, Enchant enchant) {
        return getHandLevel(self, enchant) != -1;
    }

}
