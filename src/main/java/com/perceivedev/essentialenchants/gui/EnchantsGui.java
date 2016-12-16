package com.perceivedev.essentialenchants.gui;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.perceivedev.essentialenchants.EssentialEnchants;
import com.perceivedev.essentialenchants.enchant.Rarity;
import com.perceivedev.essentialenchants.enchant.types.Enchant;
import com.perceivedev.essentialenchants.util.ItemFactory;
import com.perceivedev.essentialenchants.util.TextUtils;
import com.perceivedev.essentialenchants.util.gui.ClickEvent;
import com.perceivedev.essentialenchants.util.gui.Gui;
import com.perceivedev.essentialenchants.util.gui.Icon;

/**
 * @author Rayzr
 *
 */
public class EnchantsGui extends Gui {

    public EnchantsGui() {
        super(EssentialEnchants.getInstance().tr("gui.title"), 1);
    }

    @Override
    protected void init() {
        for (int i = 0; i < 9; i++) {
            Icon filler = new Icon(ItemFactory.builder(Material.STAINED_GLASS_PANE).setDurability(7), null);
            setIcon(i, 0, filler);
        }

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

    private void onClickUltra(ClickEvent e) {
        giveBook(e, Rarity.ULTRA, 37, "2.5k");
    }

    private void onClickEpic(ClickEvent e) {
        giveBook(e, Rarity.EPIC, 48, "5k");
    }

    private void onClickLegendary(ClickEvent e) {
        giveBook(e, Rarity.LEGENDARY, 63, "10k");
    }

    private void giveBook(ClickEvent e, Rarity targetRarity, int requiredXP, String prettyXP) {
        Player p = e.getPlayer();

        List<Enchant> valid = EssentialEnchants.getInstance().getEnchantManager().stream()
                .filter(ench -> ench.getRarity() == targetRarity).collect(Collectors.toList());
        Optional<Enchant> enchant = valid.size() < 1 ? Optional.empty() : Optional.of(valid.get((int) Math.floor(Math.random() * valid.size())));

        if (!enchant.isPresent()) {
            msg(p, "No enchants were found!");
            return;
        }

        ItemStack item = enchant.get().createBook(1 + (int) Math.round(Math.random() * (enchant.get().maxLevel() - 1)));
        if (!p.getInventory().containsAtLeast(item, 1) && p.getInventory().firstEmpty() < 0) {
            msg(p, "Your inventory is full!");
            return;
        }

        if (p.getLevel() < requiredXP) {
            msg(p, "You don't have enough experience! You need at least " + requiredXP + " levels, but you only have " + p.getLevel());
            return;
        }

        p.setLevel(p.getLevel() - requiredXP);
        p.getInventory().addItem(item);
    }

    private void msg(Player player, String msg) {
        player.sendMessage(TextUtils.colorize("&8[&aHydrusEnchants&8]&2 " + msg));
    }

}
