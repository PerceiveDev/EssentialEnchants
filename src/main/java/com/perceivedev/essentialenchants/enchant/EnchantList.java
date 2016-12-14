package com.perceivedev.essentialenchants.enchant;

import com.perceivedev.essentialenchants.enchant.types.Enchant;
import com.perceivedev.essentialenchants.enchants.axes.EnchantBeheading;
import com.perceivedev.essentialenchants.enchants.axes.EnchantQuickened;
import com.perceivedev.essentialenchants.enchants.boots.EnchantDash;
import com.perceivedev.essentialenchants.enchants.boots.EnchantFeatherWeight;
import com.perceivedev.essentialenchants.enchants.boots.EnchantSpring;
import com.perceivedev.essentialenchants.enchants.bow.EnchantFireball;
import com.perceivedev.essentialenchants.enchants.bow.EnchantLightning;
import com.perceivedev.essentialenchants.enchants.chestplate.EnchantBounce;
import com.perceivedev.essentialenchants.enchants.chestplate.EnchantRevival;
import com.perceivedev.essentialenchants.enchants.chestplate.EnchantTank;
import com.perceivedev.essentialenchants.enchants.helmet.EnchantBurn;
import com.perceivedev.essentialenchants.enchants.helmet.EnchantConsumed;
import com.perceivedev.essentialenchants.enchants.helmet.EnchantFitness;
import com.perceivedev.essentialenchants.enchants.helmet.EnchantGlow;
import com.perceivedev.essentialenchants.enchants.helmet.EnchantRage;
import com.perceivedev.essentialenchants.enchants.leggings.EnchantDrained;
import com.perceivedev.essentialenchants.enchants.leggings.EnchantInvisibility;
import com.perceivedev.essentialenchants.enchants.leggings.EnchantRefuel;
import com.perceivedev.essentialenchants.enchants.leggings.EnchantVenom;
import com.perceivedev.essentialenchants.enchants.pickaxes.EnchantBlast;
import com.perceivedev.essentialenchants.enchants.pickaxes.EnchantObsidianDestroyer;
import com.perceivedev.essentialenchants.enchants.pickaxes.EnchantSmelter;
import com.perceivedev.essentialenchants.enchants.swords.EnchantBackstab;
import com.perceivedev.essentialenchants.enchants.swords.EnchantBurst;
import com.perceivedev.essentialenchants.enchants.swords.EnchantShocking;
import com.perceivedev.essentialenchants.enchants.swords.EnchantShockwave;
import com.perceivedev.essentialenchants.enchants.swords.EnchantVampiric;
import com.perceivedev.essentialenchants.enchants.swords.EnchantWilted;

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

    // Axe, Pickaxe & Sword enchants
    public static final Enchant QUICKENED = new EnchantQuickened();

    // Pickaxe enchants
    public static final Enchant SMELTER = new EnchantSmelter();
    public static final Enchant BLAST = new EnchantBlast();
    public static final Enchant OBSIDIAN_DESTROYER = new EnchantObsidianDestroyer();

    public static String load() {
        return EnchantList.class.getSimpleName();
    }

}
