package com.perceivedev.essentialenchants.enchants.leggings;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.perceivedev.essentialenchants.ItemType;
import com.perceivedev.essentialenchants.enchant.Rarity;
import com.perceivedev.essentialenchants.enchant.types.Enchant;

/**
 * @author Rayzr
 *
 */
public class EnchantInvisibility extends Enchant {

    public EnchantInvisibility() {
        super(EntityDamageByEntityEvent.class);

        registerEventHandler(EntityDamageByEntityEvent.class, e -> {
            EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e;
            if (!(event.getEntity() instanceof Player && event.getDamager() instanceof Player)) {
                return;
            }
            Player player = (Player) event.getEntity();
            int level = getEnchantLevel(player.getInventory().getChestplate());
            if (level < 0) {
                return;
            }
            Player damager = (Player) event.getDamager();

            switch (level) {
                case 1:
                    if (Math.random() < 0.45) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 15 * 20, 0));
                        damager.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 6 * 20, 0));
                    }
                    break;
                case 2:
                    if (Math.random() < 0.50) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 30 * 20, 0));
                        damager.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 18 * 20, 0));
                    }
                    break;
                case 3:
                    if (Math.random() < 0.60) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 60 * 20, 0));
                        damager.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 29 * 20, 0));
                    }
                    break;
                default:
                    break;
            }
        });
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.perceivedev.hydrusenchants.enchant.types.Enchant#getIdentifier()
     */
    @Override
    public String getIdentifier() {
        return "INVISIBILITY";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.perceivedev.hydrusenchants.enchant.types.Enchant#getDisplay()
     */
    @Override
    public String getDisplay() {
        return "Invisibility";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.perceivedev.hydrusenchants.enchant.types.Enchant#maxLevel()
     */
    @Override
    public int maxLevel() {
        return 3;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.perceivedev.hydrusenchants.enchant.types.Enchant#getRarity()
     */
    @Override
    public Rarity getRarity() {
        return Rarity.ULTRA;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.perceivedev.hydrusenchants.enchant.types.Enchant#getApplicableItems()
     */
    @Override
    public List<ItemType> getApplicableItems() {
        return Arrays.asList(ItemType.LEGGINGS);
    }

}
