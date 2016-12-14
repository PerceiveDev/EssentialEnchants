package com.perceivedev.essentialenchants.enchant.types;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.perceivedev.essentialenchants.EssentialEnchants;
import com.perceivedev.essentialenchants.enchant.ItemSlot;
import com.perceivedev.essentialenchants.enchant.PlayerTicker;

/**
 * @author Rayzr
 *
 */
public abstract class SlotEnchant extends Enchant implements PlayerTicker {

    protected ItemSlot[] slots;

    @SuppressWarnings("unchecked")
    public SlotEnchant(ItemSlot... slots) {
        super(new Class[0]);
        this.slots = slots;
        EssentialEnchants.getInstance().getPlayerTicker().addTicker(this);
    }

    @Override
    public void tick(Player player) {
        for (ItemSlot slot : slots) {
            ItemStack item = slot.getItem(player);
            if (item == null || item.getType() == Material.AIR) {
                return;
            }
            int level = getEnchantLevel(item);
            if (level <= 0) {
                return;
            }
            apply(slot, player, level);
        }
    }

    protected abstract void apply(ItemSlot slot, Player player, int level);

}
