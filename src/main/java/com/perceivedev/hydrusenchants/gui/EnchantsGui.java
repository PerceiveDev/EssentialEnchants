package com.perceivedev.hydrusenchants.gui;

import org.bukkit.DyeColor;
import org.bukkit.Material;

import com.perceivedev.hydrusenchants.util.ItemFactory;
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
                .setColor(DyeColor.LIME)
                .setName("&aUltra")
                .setLore("&7Cost: 2.5k XP"), this::onClickUltra);
        setIcon(2, 0, ultra);

        Icon epic = new Icon(ItemFactory
                .builder(Material.STAINED_GLASS_PANE)
                .setColor(DyeColor.LIME)
                .setName("&bEpic")
                .setLore("&7Cost: 5k XP"), this::onClickEpic);
        setIcon(4, 0, epic);

        Icon legendary = new Icon(ItemFactory
                .builder(Material.STAINED_GLASS_PANE)
                .setColor(DyeColor.LIME)
                .setName("&6Legendary")
                .setLore("&7Cost: 10k XP"), this::onClickLegendary);
        setIcon(6, 0, legendary);
    }

    private void onClickUltra(ClickEvent e) {

    }

    private void onClickEpic(ClickEvent e) {

    }

    private void onClickLegendary(ClickEvent e) {

    }

}
