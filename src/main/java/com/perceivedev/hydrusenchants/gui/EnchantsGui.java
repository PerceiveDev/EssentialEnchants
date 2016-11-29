package com.perceivedev.hydrusenchants.gui;

import java.util.Optional;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.perceivedev.hydrusenchants.HydrusEnchants;
import com.perceivedev.hydrusenchants.enchant.Enchant;
import com.perceivedev.hydrusenchants.enchant.Rarity;
import com.perceivedev.hydrusenchants.util.ItemFactory;
import com.perceivedev.hydrusenchants.util.TextUtils;
import com.perceivedev.hydrusenchants.util.gui.ClickEvent;
import com.perceivedev.hydrusenchants.util.gui.Gui;
import com.perceivedev.hydrusenchants.util.gui.Icon;

/**
 * @author Rayzr
 *
 */
public class EnchantsGui extends Gui {

    /**
     * @param title
     * @param rows
     */
    public EnchantsGui() {
        super("&3Hydrus&9Enchants", 1);
    }

    @Override
    protected void init() {
        Icon ultra = new Icon(ItemFactory
                .builder(Material.STAINED_GLASS_PANE)
                .setDurability(5)
                .setName("&aUltra")
                .setLore("&7Cost: 2.5k XP"), this::onClickUltra);
        setIcon(2, 0, ultra);

        Icon epic = new Icon(ItemFactory
                .builder(Material.STAINED_GLASS_PANE)
                .setDurability(3)
                .setName("&bEpic")
                .setLore("&7Cost: 5k XP"), this::onClickEpic);
        setIcon(4, 0, epic);

        Icon legendary = new Icon(ItemFactory
                .builder(Material.STAINED_GLASS_PANE)
                .setDurability(1)
                .setName("&6Legendary")
                .setLore("&7Cost: 10k XP"), this::onClickLegendary);
        setIcon(6, 0, legendary);
    }

    private void giveBook(ClickEvent e, Rarity targetRarity, int requireXP, String requireXPPretty) {
        Player p = e.getPlayer();

        Optional<Enchant> enchant = HydrusEnchants.getInstance().getEnchantManager().stream()
                .filter(ench -> ench.getRarity() == targetRarity)
                .reduce((a, b) -> Math.random() > 0.5 ? a : b);

        if (!enchant.isPresent()) {
            msg(p, "No enchants were found!");
            return;
        }

        ItemStack item = enchant.get().createBook();
        if (!p.getInventory().containsAtLeast(item, 1) && p.getInventory().firstEmpty() < 0) {
            msg(p, "Your inventory is full!");
            return;
        }

        if (p.getTotalExperience() < requireXP) {
            msg(p, "You don't have enough experience! You need at least " + requireXPPretty + " XP");
            return;
        }

        p.setTotalExperience(p.getTotalExperience() - requireXP);
        p.setExp(0.0f);
        p.getInventory().addItem(item);
    }

    private void onClickUltra(ClickEvent e) {
        giveBook(e, Rarity.ULTRA, 2500, "2.5k");
    }

    private void onClickEpic(ClickEvent e) {
        giveBook(e, Rarity.EPIC, 5000, "5k");
    }

    private void onClickLegendary(ClickEvent e) {
        giveBook(e, Rarity.LEGENDARY, 10000, "10k");
    }

    private void msg(Player player, String msg) {
        player.sendMessage(TextUtils.colorize("&8[&aHydrusEnchants&8]&2 " + msg));
    }

}
