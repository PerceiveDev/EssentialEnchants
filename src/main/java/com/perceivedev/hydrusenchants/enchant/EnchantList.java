package com.perceivedev.hydrusenchants.enchant;

import com.perceivedev.hydrusenchants.enchant.types.Enchant;
import com.perceivedev.hydrusenchants.enchants.bow.EnchantLightning;
import com.perceivedev.hydrusenchants.enchants.chestplate.EnchantBounce;
import com.perceivedev.hydrusenchants.enchants.chestplate.EnchantRevival;
import com.perceivedev.hydrusenchants.enchants.chestplate.EnchantTank;
import com.perceivedev.hydrusenchants.enchants.helmet.EnchantBurn;
import com.perceivedev.hydrusenchants.enchants.helmet.EnchantConsumed;
import com.perceivedev.hydrusenchants.enchants.helmet.EnchantFitness;
import com.perceivedev.hydrusenchants.enchants.helmet.EnchantGlow;
import com.perceivedev.hydrusenchants.enchants.helmet.EnchantRage;
import com.perceivedev.hydrusenchants.enchants.leggings.EnchantInvisibility;

/**
 * @author Rayzr
 *
 */
public class EnchantList {

    // Universal Enchants
    public static final Enchant RAGE = new EnchantRage();

    // Helmet Enchants
    public static final Enchant FITNESS = new EnchantFitness();
    public static final Enchant GLOW = new EnchantGlow();
    public static final Enchant BURN = new EnchantBurn();
    public static final Enchant CONSUMED = new EnchantConsumed();

    // Chestplate Enchants
    public static final Enchant REVIVAL = new EnchantRevival();
    public static final Enchant BOUNCE = new EnchantBounce();
    
    // Leggings Enchants
    public static final Enchant INVISIBILITY = new EnchantInvisibility();

    // Chestplate & Leggings Enchants
    public static final Enchant TANK = new EnchantTank();

    // Bow Enchants
    public static final Enchant LIGHTNING = new EnchantLightning();

    public static String load() {
        return EnchantList.class.getSimpleName();
    }

}
