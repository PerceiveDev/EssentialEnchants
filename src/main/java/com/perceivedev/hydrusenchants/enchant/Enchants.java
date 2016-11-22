package com.perceivedev.hydrusenchants.enchant;

import java.util.List;
import java.util.Objects;

/**
 * @author Rayzr
 *
 */
public class Enchants {

    private List<String> lore;

    public Enchants(List<String> lore) {
        this.lore = lore;
        loadEnchants();
    }

    public void loadEnchants() {
        // TODO: Load all enchants from lore
    }

    public static Enchants load(List<String> lore) {
        Objects.requireNonNull(lore);
        return new Enchants(lore);
    }

}
