package com.perceivedev.hydrusenchants.event;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.perceivedev.hydrusenchants.HydrusEnchants;
import com.perceivedev.hydrusenchants.enchant.Enchant;
import com.perceivedev.hydrusenchants.enchant.Enchants;
import com.perceivedev.hydrusenchants.util.TextUtils;

/**
 * @author Rayzr
 *
 */
public class InventoryListener implements Listener {

    @EventHandler(priority = EventPriority.LOW)
    public void onClick(InventoryClickEvent e) {
        if (e.getCursor() == null || e.getCursor().getType() != Material.ENCHANTED_BOOK) {
            return;
        }

        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) {
            return;
        }

        ItemStack book = e.getCursor();
        if (!book.hasItemMeta() || !book.getItemMeta().hasLore()) {
            return;
        }

        List<String> lore = book.getItemMeta().getLore();
        if (lore.size() < 1) {
            return;
        }

        String data = TextUtils.unhideText(lore.get(0));
        if (!data.startsWith(Enchant.ENCHANT_BOOK_IDENTIFIER)) {
            return;
        }
        data = data.substring(Enchant.ENCHANT_BOOK_IDENTIFIER.length());

        ItemStack item = e.getCurrentItem();

        Enchant enchant = HydrusEnchants.getInstance().getEnchantManager().getEnchant(data);
        if (enchant == null || !enchant.isApplicableTo(item)) {
            return;
        }

        Enchants enchants = Enchants.load(item.getItemMeta().getLore());
        if (enchants.get(enchant) > -1) {
            return;
        }

        e.setCancelled(true);

        enchants.set(enchant, enchant.maxLevel());
        enchants.apply(item);

        e.setCursor(null);
        e.setCurrentItem(item);
    }

}
