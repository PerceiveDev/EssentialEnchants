package com.perceivedev.essentialenchants.enchant;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.apache.commons.lang.Validate;
import org.bukkit.configuration.file.YamlConfiguration;

import com.perceivedev.essentialenchants.EssentialEnchants;
import com.perceivedev.essentialenchants.enchant.types.Enchant;

/**
 * @author Rayzr
 *
 */
public class EnchantManager {
    private static final Pattern IDENTIFIER_CHARS = Pattern.compile("[A-Z0-9_]+");

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
                enchant.setDescription("&cNo description provided.");
            } else {
                enchant.setDescription(config.getString(key + ".description"));
            }
        }
    }

    /**
     * Registers an {@link Enchant enchant} based on it's
     * {@link Enchant#getIdentifier() identifier}.
     * 
     * @param enchant the enchant to register
     * @throws IllegalArgumentException if the enchant identifier contains
     *             illegal characters. See the documentation of
     *             {@link Enchant#getIdentifier()} for more info.
     */
    public void registerEnchant(Enchant enchant) {
        validateEnchant(enchant);
        enchants.put(enchant.getIdentifier(), enchant);
    }

    private void validateEnchant(Enchant enchant) {
        Objects.requireNonNull(enchant, "Failed to validate enchant because it was null!");

        Objects.requireNonNull(enchant.getIdentifier(), "Failed to validate enchant because it's identifier was null!");
        Objects.requireNonNull(enchant.getDisplay(), "Failed to validate enchant because it's display name was null!");
        Objects.requireNonNull(enchant.getApplicableItems(), "Failed to validate the enchant because the list of applicable items was null!");

        Validate.isTrue(enchant.getIdentifier().length() > 0, "Failed to validate the enchant because it's identifier was empty!");
        Validate.isTrue(enchant.getDisplay().trim().length() > 0, "Failed to validate the enchant because it's display name was empty!");

        if (!IDENTIFIER_CHARS.matcher(enchant.getIdentifier()).matches()) {
            throw new IllegalArgumentException(
                    "Failed to validate the enchant because the enchant identifier contains illegal characters! Please see the documentation of Enchant#getIdentifier() for more information.");
        }

        Validate.isTrue(enchant.maxLevel() > 0, "Failed to validate the enchant because the max level was less than 1!");
        Validate.isTrue(enchant.getApplicableItems().size() > 0, "Failed to validate the enchant because the list of applicable items was empty!");
    }

    /**
     * @param identifier the {@link Enchant#getIdentifier() identifier} of the
     *            {@link Enchant enchant}.
     * @return
     */
    public Enchant getEnchant(String identifier) {
        return enchants.get(identifier.toUpperCase().replace(' ', '_'));
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
