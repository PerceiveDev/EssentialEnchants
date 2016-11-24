package com.perceivedev.hydrusenchants.enchant;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.perceivedev.hydrusenchants.HydrusEnchants;
import com.perceivedev.hydrusenchants.util.TextUtils;

/**
 * Represents a list of enchants and their levels that are stored on an item.
 * You can load it with {@link Enchants#load(List)}
 * 
 * @author Rayzr
 */
public class Enchants {

    private List<String>          lore;
    private Map<Enchant, Integer> enchants = new LinkedHashMap<>();

    /**
     * Saves the lore to this object and then calls {@link #loadEnchants()}
     * 
     * @param lore the lore
     */
    public Enchants(List<String> lore) {
        this.lore = lore;
        loadEnchants();
    }

    /**
     * Loads enchantments from hidden lore data
     * 
     * @param lore the lore
     * @return The loaded {@link Enchants} object
     */
    public static Enchants load(List<String> lore) {
        Objects.requireNonNull(lore);
        return new Enchants(lore);
    }

    /**
     * Loads the enchants from the lore supplied to the constructor. This method
     * is called by the constructor.
     */
    public void loadEnchants() {
        lore.stream().forEach(this::loadEnchant);
    }

    private void loadEnchant(String lore) {
        String[] split = lore.split("||");
        if (split.length < 1) {
            return;
        }
        String[] data = TextUtils.unhideText(split[0]).split(":");

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
