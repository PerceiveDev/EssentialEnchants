package com.perceivedev.essentialenchants.enchant.types;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.perceivedev.essentialenchants.enchant.ItemSlot;

/**
 * @author Rayzr
 *
 */
public abstract class PotionEnchant extends SlotEnchant {

    protected Map<ItemSlot, PotionEnchantData> effects = new HashMap<>();

    public PotionEnchant(PotionEnchantData... effects) {
        super(Arrays.stream(effects).map(effect -> effect.getSlot()).collect(Collectors.toList()).toArray(new ItemSlot[0]));
        for (PotionEnchantData effect : effects) {
            this.effects.put(effect.getSlot(), effect);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.perceivedev.hydrusenchants.enchant.types.SlotEnchant#apply(com.
     * perceivedev.hydrusenchants.enchant.ItemSlot, org.bukkit.entity.Player,
     * int)
     */
    @Override
    protected final void apply(ItemSlot slot, Player player, int level) {
        PotionEnchantData data = effects.get(slot);
        if (data == null) {
            return;
        }
        int amplifier = data.getAmplifierCalculator().apply(level);
        PotionEffect effect = player.getPotionEffect(data.getType());
        if (effect != null && effect.getAmplifier() <= amplifier) {
            player.removePotionEffect(data.getType());
        }
        player.addPotionEffect(new PotionEffect(data.getType(), 31, amplifier));
    }

    public static class PotionEnchantData {

        private ItemSlot slot;
        private PotionEffectType type;
        private Function<Integer, Integer> amplifierCalculator;

        public PotionEnchantData(ItemSlot slot, PotionEffectType type, Function<Integer, Integer> amplifierCalculator) {
            this.slot = slot;
            this.type = type;
            this.amplifierCalculator = amplifierCalculator;
        }

        /**
         * @return the item slot
         */
        public ItemSlot getSlot() {
            return slot;
        }

        /**
         * @return the type
         */
        public PotionEffectType getType() {
            return type;
        }

        /**
         * @return the amplifierCalculator
         */
        public Function<Integer, Integer> getAmplifierCalculator() {
            return amplifierCalculator;
        }

    }

}
