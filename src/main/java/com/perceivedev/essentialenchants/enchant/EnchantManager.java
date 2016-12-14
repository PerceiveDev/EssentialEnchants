package com.perceivedev.essentialenchants.enchant;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.bukkit.configuration.file.YamlConfiguration;

import com.perceivedev.essentialenchants.EssentialEnchants;
import com.perceivedev.essentialenchants.enchant.types.Enchant;

/**
 * @author Rayzr
 *
 */
public class EnchantManager {

    private Map<String, Enchant> enchants = new LinkedHashMap<>();

    private EssentialEnchants plugin;

    public EnchantManager(EssentialEnchants plugin) {
        this.plugin = plugin;

    }

    /**
     * Loads the data from the configuration file
     */
    public void load() {
        YamlConfiguration config = plugin.getConfig("enchants.yml");
        for (String key : config.getKeys(false)) {
            Enchant enchant = enchants.get(key.toUpperCase().replace('-', '_'));
            if (enchant == null) {
                continue;
            }
            if (!config.isString(key + ".description")) {
                continue;
            }
            enchant.setDescription(config.getString(key + ".description"));
        }
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

    /**
     * @return all the enchants in this {@link EnchantManager}
     */
    public Collection<Enchant> getEnchants() {
        return enchants.values();
    }
}
