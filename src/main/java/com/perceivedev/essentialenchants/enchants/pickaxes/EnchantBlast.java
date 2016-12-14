package com.perceivedev.essentialenchants.enchants.pickaxes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;

import com.perceivedev.essentialenchants.ItemType;
import com.perceivedev.essentialenchants.enchant.Rarity;
import com.perceivedev.essentialenchants.enchant.types.Enchant;

public class EnchantBlast extends Enchant {

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

    public EnchantBlast() {
        super(BlockBreakEvent.class);

        registerEventHandler(BlockBreakEvent.class, e -> {
            BlockBreakEvent event = (BlockBreakEvent) e;
            if (event.isCancelled()) {
                return;
            }

            ItemStack tool = event.getPlayer().getInventory().getItemInMainHand();
            int level = getEnchantLevel(tool);
            if (level < 0) {
                return;
            }

            // int size = level == 3 || level == 2 ? 5 : 3;

        });
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
