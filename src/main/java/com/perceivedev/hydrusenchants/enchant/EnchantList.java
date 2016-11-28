package com.perceivedev.hydrusenchants.enchant;

import java.lang.reflect.Field;

import com.perceivedev.hydrusenchants.enchants.EnchantLightning;

/**
 * @author Rayzr
 *
 */
public class EnchantList {

    public static final Enchant LIGHTNING = new EnchantLightning();

    public static void printEnchants() {
        for (Field field : EnchantList.class.getFields()) {
            if (!field.getType().isAssignableFrom(Enchant.class)) {
                continue;
            }
            try {
                System.out.println("- " + ((Enchant) field.get(null)).getDisplay());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
