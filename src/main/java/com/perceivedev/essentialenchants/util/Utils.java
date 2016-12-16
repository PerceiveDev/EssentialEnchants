/**
 * 
 */
package com.perceivedev.essentialenchants.util;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.perceivedev.essentialenchants.enchant.types.Enchant;

/**
 * @author Rayzr
 *
 */
public class Utils {

    private static boolean OFF_HAND = false;
    private static boolean TIPPED_ARROWS = false;

    static {
        // Hacky way to check if this server supports the off-hand slot
        try {
            EquipmentSlot.OFF_HAND.name();
            OFF_HAND = true;
        } catch (NoSuchFieldError e) {
            // Ignore
        }

        // Hacky way to check if this server supports tipped arrows
        try {
            Material.TIPPED_ARROW.name();
            TIPPED_ARROWS = true;
        } catch (NoSuchFieldError e) {
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

    /**
     * A utility method for consuming the first
     * 
     * @param self
     * @param predicate
     * @return
     */
    public static boolean consumeFirst(Player self, Predicate<ItemStack> predicate) {
        Objects.requireNonNull(self, "self cannot be null!");
        Objects.requireNonNull(predicate, "predicate cannot be null!");
        Optional<ItemStack> i = Arrays.stream(self.getInventory().getStorageContents()).filter(predicate).findFirst();
        if (!i.isPresent()) {
            return false;
        }
        ItemStack consumable = i.get();
        int slot = self.getInventory().first(consumable);
        int amount = consumable.getAmount() - 1;
        if (amount < 1) {
            self.getInventory().setItem(slot, null);
        }
        consumable.setAmount(amount);
        self.getInventory().setItem(slot, consumable);
        return true;
    }

    /**
     * Attempts to consume an item from the player's inventory.
     * 
     * @param self the player to consume the item from
     * @param item the item to consume
     * @return Whether or not the item was consumed
     */
    public static boolean consume(Player self, ItemStack item) {
        Objects.requireNonNull(item, "item cannot be null!");
        Validate.isTrue(item.getType() != Material.AIR, "item cannot be AIR!");
        return consumeFirst(self, it -> item.isSimilar(it));
    }

    /**
     * Attempts to consume an item from the player's inventory.
     * 
     * @param self the player to consume the item from
     * @param type the type of item to consume
     * @return Whether or not the item was consumed
     */
    public static boolean consume(Player self, Material type) {
        return consume(self, new ItemStack(type));
    }

    /**
     * Consumes an arrow from the player, including a cross-compatibility check
     * for newer servers that have tipped arrows
     * 
     * @param self the player to consume the arrow from
     * @return Whether or not any kind of error was consumed
     */
    public static boolean consumeArrow(Player self) {
        return consumeFirst(self, it -> it != null && (it.getType() == Material.ARROW || (TIPPED_ARROWS && it.getType() == Material.TIPPED_ARROW)));
    }

    /**
     * Fixes odd durabilities of {@link Short#MAX_VALUE} on items in recipes 
     * @param item the {@link ItemStack} to fix the durability of
     * @return The normalized item
     */
    public static ItemStack fixDurability(ItemStack item) {
        if (item.getDurability() != Short.MAX_VALUE) {
            return item.clone();
        }
        ItemStack fixed = item.clone();
        fixed.setDurability((short) 0);
        return fixed;
    }

}
