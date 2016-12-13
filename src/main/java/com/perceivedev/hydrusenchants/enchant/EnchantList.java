package com.perceivedev.hydrusenchants.enchant;

import com.perceivedev.hydrusenchants.enchant.types.Enchant;
import com.perceivedev.hydrusenchants.enchants.axes.EnchantBeheading;
import com.perceivedev.hydrusenchants.enchants.axes.EnchantQuickened;
import com.perceivedev.hydrusenchants.enchants.boots.EnchantDash;
import com.perceivedev.hydrusenchants.enchants.boots.EnchantFeatherWeight;
import com.perceivedev.hydrusenchants.enchants.boots.EnchantSpring;
import com.perceivedev.hydrusenchants.enchants.bow.EnchantFireball;
import com.perceivedev.hydrusenchants.enchants.bow.EnchantLightning;
import com.perceivedev.hydrusenchants.enchants.chestplate.EnchantBounce;
import com.perceivedev.hydrusenchants.enchants.chestplate.EnchantRevival;
import com.perceivedev.hydrusenchants.enchants.chestplate.EnchantTank;
import com.perceivedev.hydrusenchants.enchants.helmet.EnchantBurn;
import com.perceivedev.hydrusenchants.enchants.helmet.EnchantConsumed;
import com.perceivedev.hydrusenchants.enchants.helmet.EnchantFitness;
import com.perceivedev.hydrusenchants.enchants.helmet.EnchantGlow;
import com.perceivedev.hydrusenchants.enchants.helmet.EnchantRage;
import com.perceivedev.hydrusenchants.enchants.leggings.EnchantDrained;
import com.perceivedev.hydrusenchants.enchants.leggings.EnchantInvisibility;
import com.perceivedev.hydrusenchants.enchants.leggings.EnchantRefuel;
import com.perceivedev.hydrusenchants.enchants.leggings.EnchantVenom;
import com.perceivedev.hydrusenchants.enchants.swords.EnchantBackstab;
import com.perceivedev.hydrusenchants.enchants.swords.EnchantBurst;
import com.perceivedev.hydrusenchants.enchants.swords.EnchantShocking;
import com.perceivedev.hydrusenchants.enchants.swords.EnchantShockwave;
import com.perceivedev.hydrusenchants.enchants.swords.EnchantVampiric;
import com.perceivedev.hydrusenchants.enchants.swords.EnchantWilted;

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
    
    // Chestplate & Leggings Enchants
    public static final Enchant TANK = new EnchantTank();

    // Leggings Enchants
    public static final Enchant INVISIBILITY = new EnchantInvisibility();
    public static final Enchant REFUEL = new EnchantRefuel();
    public static final Enchant VENOM = new EnchantVenom();
    public static final Enchant DRAINED = new EnchantDrained();

    // Boots Enchants
    public static final Enchant DASH = new EnchantDash();
    public static final Enchant SPRING = new EnchantSpring();
    public static final Enchant FEATHER_WEIGHT = new EnchantFeatherWeight();

    // Bow Enchants
    public static final Enchant LIGHTNING = new EnchantLightning();
    public static final Enchant FIREBALL = new EnchantFireball();

    // Sword Enchants
    public static final Enchant BACKSTAB = new EnchantBackstab();
    public static final Enchant SHOCKWAVE = new EnchantShockwave();
    public static final Enchant BURST = new EnchantBurst();
    public static final Enchant SHOCKING = new EnchantShocking();
    public static final Enchant WILTED = new EnchantWilted();
    public static final Enchant VAMPIRIC = new EnchantVampiric();

    // Axe Enchants
    public static final Enchant BEHEADING = new EnchantBeheading();

    // Axe & Pickaxe enchants
    public static final Enchant QUICKENED = new EnchantQuickened();
    
    // Pickaxe enchants
    
    
    public static String load() {
        return EnchantList.class.getSimpleName();
    }

}
