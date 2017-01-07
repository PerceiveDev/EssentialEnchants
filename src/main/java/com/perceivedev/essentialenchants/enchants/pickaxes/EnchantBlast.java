package com.perceivedev.essentialenchants.enchants.pickaxes;

import java.util.Arrays;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
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

    @SuppressWarnings("deprecation")
    private void breakLater(BlockBreakEvent event, ItemStack tool, int level) {
        if (event.isCancelled())
            return;
        Player player = event.getPlayer();
        if (!player.isOnline())
            return;

        Vector dir = event.getPlayer().getEyeLocation().getDirection();

        int ymult = Math.abs(dir.getY()) > 0.9 ? 0 : 1;
        int xmult = ymult == 0 ? 1 : (int) Math.round(dir.getX() + 1) % 2;
        int zmult = ymult == 0 ? 1 : (xmult + 1) % 2;

        int radius = level == 3 || level == 2 ? 2 : 1;

        Block base = event.getBlock();

        miner: {
            for (int x = xmult == 0 ? radius : -radius; x <= radius; x++) {
                for (int y = ymult == 0 ? radius : -radius; y <= radius; y++) {
                    for (int z = zmult == 0 ? radius : -radius; z <= radius; z++) {
                        if (x * xmult == 0 && y * ymult == 0 && z * zmult == 0) {
                            continue;
                        }
                        Block next = base.getRelative(x * xmult, y * ymult, z * zmult);
                        if (next == null) {
                            continue;
                        }
                        if (level < 3 && next.getType() == Material.OBSIDIAN) {
                            continue;
                        }
                        if (Utils.itemCausesDrops(next, tool)) {
                            next.breakNaturally(tool);

                            if (player.getGameMode() != GameMode.CREATIVE) {
                                tool = Utils.damage(tool);
                                if (tool == null) {
                                    break miner;
                                }
                            }
                        }
                    }
                }
            }
        }

        player.getInventory().setItemInHand(tool);
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
