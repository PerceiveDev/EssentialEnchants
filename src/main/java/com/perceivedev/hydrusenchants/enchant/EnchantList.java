package com.perceivedev.hydrusenchants.enchant;

import com.perceivedev.hydrusenchants.enchant.types.Enchant;
import com.perceivedev.hydrusenchants.enchants.EnchantBurn;
import com.perceivedev.hydrusenchants.enchants.EnchantConsumed;
import com.perceivedev.hydrusenchants.enchants.EnchantFitness;
import com.perceivedev.hydrusenchants.enchants.EnchantGlow;
import com.perceivedev.hydrusenchants.enchants.EnchantLightning;
import com.perceivedev.hydrusenchants.enchants.EnchantRage;

/**
 * @author Rayzr
 *
 */
public class EnchantList {

    public static final Enchant LIGHTNING = new EnchantLightning();
    public static final Enchant FITNESS   = new EnchantFitness();
    public static final Enchant GLOW      = new EnchantGlow();
    public static final Enchant BURN      = new EnchantBurn();
    public static final Enchant CONSUMED  = new EnchantConsumed();
    public static final Enchant RAGE      = new EnchantRage();

    public static String load() {
        return EnchantList.class.getSimpleName();
    }

}
