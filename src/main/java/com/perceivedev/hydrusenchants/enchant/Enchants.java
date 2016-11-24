package com.perceivedev.hydrusenchants.enchant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.perceivedev.hydrusenchants.HydrusEnchants;
import com.perceivedev.hydrusenchants.util.TextUtils;

/**
 * Represents a list of enchants and their levels that are stored on an item.
 * You can load it with {@link Enchants#load(List)}
 * 
 * @author Rayzr
 */
public class Enchants {

    public static final String    IDENTIFIER     = "data-enchant:";
    public static final String    ENCHANT_FORMAT = IDENTIFIER + "%s:%d;;";

    private List<String>          lore;
    private Map<Enchant, Integer> enchants       = new LinkedHashMap<>();

    /**
     * Saves the lore to this object and then calls {@link #loadEnchants()}
     * 
     * @param lore the lore
     */
    private Enchants(List<String> lore) {
        this.lore = lore == null ? new ArrayList<>() : lore;
        loadEnchants();
    }

    /**
     * Loads enchantments from hidden lore data
     * 
     * @param lore the lore
     * @return The loaded {@link Enchants} object
     */
    public static Enchants load(List<String> lore) {
        return new Enchants(lore);
    }

    public List<String> getLore() {
        return enchants.entrySet().stream().map(e -> String.format(ENCHANT_FORMAT, e.getKey().getIdentifier(), e.getValue()) +
                ChatColor.GRAY + e.getKey().getDisplay() + " " + TextUtils.numeral(e.getValue())).collect(Collectors.toList());
    }

    public void apply(ItemStack item) {
        clearEnchantData(item);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(getLore());
        item.setItemMeta(meta);
    }

    public static void clearEnchantData(ItemStack item) {
        if (item == null || item.getType() == Material.AIR || !item.getItemMeta().hasLore()) {
            return;
        }

        List<String> lore = item.getItemMeta().getLore();
        Iterator<String> it = lore.iterator();
        it.forEachRemaining(raw -> {
            String s = TextUtils.unhideText(raw);
            if (!s.matches(Pattern.quote(IDENTIFIER) + "[a-z_-]+:\\d+;;")) {
                lore.remove(raw);
            }
        });

        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);
        item.setItemMeta(meta);

    }

    /**
     * Loads the enchants from the lore supplied to the constructor. This method
     * is called by the constructor.
     */
    public void loadEnchants() {
        lore.stream().forEach(this::parseEnchant);
    }

    private void parseEnchant(String lore) {
        lore = TextUtils.unhideText(lore);
        if (!lore.startsWith(IDENTIFIER)) {
            return;
        }
        lore = lore.substring(IDENTIFIER.length());
        String[] split = lore.split(";;");
        if (split.length < 1) {
            return;
        }
        String[] data = split[0].split(":");

        if (data.length < 2) {
            return;
        }
        if (!data[1].matches("\\d+")) {
            return;
        }
        Enchant enchant = HydrusEnchants.getInstance().getEnchantManager().getEnchant(data[0]);
        if (enchant == null) {
            return;
        }

        enchants.put(enchant, Integer.parseInt(data[1]));
    }

    /**
     * @param enchant the enchant to check
     * @return The level of the enchant, or {@code -1} if the enchant wasn't
     *         found
     */
    public int get(Enchant enchant) {
        return enchants.getOrDefault(enchant, -1);
    }

    /**
     * @param identifier the identifier of the enchant to check
     * @return The level of the enchant, or {@code -1} if the enchant doesn't
     *         exist or if it wasn't found in this list of enchants
     */
    public int get(String identifier) {
        Enchant enchant = HydrusEnchants.getInstance().getEnchantManager().getEnchant(identifier);
        return enchant == null ? -1 : enchants.getOrDefault(enchant, -1);
    }

}
