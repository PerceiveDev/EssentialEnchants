package com.perceivedev.hydrusenchants.enchant;

import java.util.LinkedHashMap;
import java.util.Map;

import com.perceivedev.hydrusenchants.HydrusEnchants;

/**
 * @author Rayzr
 *
 */
public class EnchantManager {
    private Map<String, Enchant> enchants = new LinkedHashMap<>();

    @SuppressWarnings("unused")
    private HydrusEnchants       plugin;

    public EnchantManager(HydrusEnchants plugin) {
        this.plugin = plugin;
    }

    /**
     * Registers an {@link Enchant enchant} based on it's
     * {@link Enchant#getIdentifier() identifier}.
     * 
     * @param enchant the enchant to register
     */
    public void registerEnchant(Enchant enchant) {
        enchants.put(enchant.getIdentifier(), enchant);
    }
}
