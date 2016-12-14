package com.perceivedev.essentialenchants.enchants.pickaxes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;

import com.perceivedev.essentialenchants.ItemType;
import com.perceivedev.essentialenchants.enchant.Rarity;
import com.perceivedev.essentialenchants.enchant.types.Enchant;

public class EnchantObsidianDestroyer extends Enchant {

    private static final Map<ItemStack, ItemStack> smeltables = new HashMap<>();

    static {
        Bukkit.recipeIterator().forEachRemaining(r -> {
            if (!(r instanceof FurnaceRecipe)) {
                return;
            }
            FurnaceRecipe recipe = (FurnaceRecipe) r;
            if (!recipe.getInput().getType().isBlock()) {
                return;
            }
            smeltables.put(recipe.getInput(), recipe.getResult());
        });
    }

    public EnchantObsidianDestroyer() {
        super(BlockBreakEvent.class);

        registerEventHandler(BlockBreakEvent.class, e -> {
            BlockBreakEvent event = (BlockBreakEvent) e;
            if (event.isCancelled()) {
                return;
            }

            if (event.getBlock().getType() != Material.OBSIDIAN) {
                return;
            }

            @SuppressWarnings("deprecation")
            ItemStack tool = event.getPlayer().getItemInHand();
            if (!isEnchanted(tool)) {
                return;
            }

            event.setCancelled(true);
            event.getBlock().breakNaturally(tool);

        });
    }

    @Override
    public String getIdentifier() {
        return "OBSIDIAN_DESTROYER";
    }

    @Override
    public String getDisplay() {
        return "Obsidian Destroyer";
    }

    @Override
    public int maxLevel() {
        return 1;
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
