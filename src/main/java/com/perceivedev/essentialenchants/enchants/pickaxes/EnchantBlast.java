package com.perceivedev.essentialenchants.enchants.pickaxes;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.perceivedev.essentialenchants.EssentialEnchants;
import com.perceivedev.essentialenchants.ItemType;
import com.perceivedev.essentialenchants.enchant.Rarity;
import com.perceivedev.essentialenchants.enchant.types.Enchant;
import com.perceivedev.essentialenchants.util.Utils;

public class EnchantBlast extends Enchant {

    public EnchantBlast() {
        super(BlockBreakEvent.class);

        registerEventHandler(BlockBreakEvent.class, e -> {
            BlockBreakEvent event = (BlockBreakEvent) e;
            if (event.isCancelled()) {
                return;
            }

            if (event.getPlayer().isSneaking()) {
                return;
            }

            @SuppressWarnings("deprecation")
            ItemStack tool = event.getPlayer().getInventory().getItemInHand();
            int level = getEnchantLevel(tool);
            if (level < 0) {
                return;
            }

            new BukkitRunnable() {
                public void run() {
                    breakLater(event, tool, level);
                }
            }.runTaskLater(EssentialEnchants.getInstance(), 1L);

        });
    }

    private void breakLater(BlockBreakEvent event, ItemStack tool, int level) {
        if (event.isCancelled())
            return;
        if (!event.getPlayer().isOnline())
            return;

        Vector dir = event.getPlayer().getEyeLocation().getDirection();
        int xmult = (int) Math.round(dir.getY() / 90) % 2;
        int zmult = (xmult + 1) % 2;

        Block base = event.getBlock();

        int radius = level == 3 || level == 2 ? 2 : 1;
        for (int h = -radius; h <= radius; h++) {
            for (int v = -radius; v <= radius; v++) {
                if (h == 0 && v == 0) {
                    continue;
                }
                Block next = base.getRelative(h * xmult, v, h * zmult);
                if (next == null) {
                    continue;
                }
                if (level < 3 && next.getType() == Material.OBSIDIAN) {
                    continue;
                }
                if (Utils.itemCausesDrops(next, tool)) {
                    next.breakNaturally();
                }
            }
        }
    }

    @Override
    public String getIdentifier() {
        return "BLAST";
    }

    @Override
    public String getDisplay() {
        return "Blast";
    }

    @Override
    public int maxLevel() {
        return 3;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.EPIC;
    }

    @Override
    public List<ItemType> getApplicableItems() {
        return Arrays.asList(ItemType.PICKAXE);
    }

}
