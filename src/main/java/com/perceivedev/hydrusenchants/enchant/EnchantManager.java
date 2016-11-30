package com.perceivedev.hydrusenchants.enchant;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import com.perceivedev.hydrusenchants.HydrusEnchants;
import com.perceivedev.hydrusenchants.enchant.types.Enchant;

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

    /**
     * @param identifier the {@link Enchant#getIdentifier() identifier} of the
     *            {@link Enchant enchant}.
     * @return
     */
    public Enchant getEnchant(String identifier) {
        return enchants.get(identifier);
    }

    public Stream<Enchant> stream() {
        return enchants.values().stream();
    }
}
