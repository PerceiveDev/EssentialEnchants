package com.perceivedev.hydrusenchants.event;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.perceivedev.hydrusenchants.HydrusEnchants;
import com.perceivedev.hydrusenchants.enchant.Enchants;
import com.perceivedev.hydrusenchants.enchant.types.Enchant;
import com.perceivedev.hydrusenchants.util.TextUtils;

/**
 * @author Rayzr
 *
 */
public class InventoryListener implements Listener {

    private HydrusEnchants plugin;

    public InventoryListener(HydrusEnchants plugin) {
        this.plugin = plugin;
    }

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
        if (data.indexOf(";") < 0) {
            plugin.getLogger().warning("Invalid book! No level is specified!");
            return;
        }

        ItemStack item = e.getCurrentItem();

        Enchant enchant = HydrusEnchants.getInstance().getEnchantManager().getEnchant(data.split(";")[0]);
        if (enchant == null || !enchant.isApplicableTo(item)) {
            return;
        }

        int enchantLevel = Integer.parseInt(data.split(";")[1]);
        int current = -1;
        Enchants enchants = Enchants.load(item.getItemMeta().getLore());

        if ((current = enchants.get(enchant)) > -1) {
            if (current + enchantLevel <= enchant.maxLevel()) {
                enchants.set(enchant, current + enchantLevel);
            } else {
                e.getWhoClicked().sendMessage(plugin.tr("enchant.failed.toHigh",
                        TextUtils.numeral(current),
                        TextUtils.numeral(enchantLevel),
                        TextUtils.numeral(current + enchantLevel),
                        TextUtils.numeral(enchant.maxLevel())));
                return;
            }
        } else {
            enchants.set(enchant, enchantLevel);
        }

        e.setCancelled(true);

        item = enchants.apply(item);

        e.setCursor(null);
        e.setCurrentItem(item);
    }

}
