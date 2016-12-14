package com.perceivedev.essentialenchants.enchants.pickaxes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;

import com.perceivedev.essentialenchants.ItemType;
import com.perceivedev.essentialenchants.enchant.Rarity;
import com.perceivedev.essentialenchants.enchant.types.Enchant;

public class EnchantSmelter extends Enchant {

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

    public EnchantSmelter() {
        super(BlockBreakEvent.class);

        registerEventHandler(BlockBreakEvent.class, e -> {
            BlockBreakEvent event = (BlockBreakEvent) e;
            if (event.isCancelled()) {
                return;
            }

            ItemStack tool = event.getPlayer().getInventory().getItemInMainHand();
            if (!isEnchanted(tool)) {
                return;
            }

            Block block = event.getBlock();
            Collection<ItemStack> items = block.getDrops(tool);

            List<ItemStack> filtered = new ArrayList<ItemStack>();
            boolean foundMatch = false;

            for (ItemStack item : items) {
                if (smeltables.containsKey(item)) {
                    foundMatch = true;
                    filtered.add(smeltables.get(item));
                } else {
                    filtered.add(item);
                }
            }

            if (!foundMatch) {
                return;
            }

            event.setCancelled(true);
            block.setType(Material.AIR);

            World world = block.getWorld();
            for (ItemStack item : filtered) {
                world.dropItemNaturally(block.getLocation(), item);
            }
        });
    }

    @Override
    public String getIdentifier() {
        return "SMELTER";
    }

    @Override
    public String getDisplay() {
        return "Smelter";
    }

    @Override
    public int maxLevel() {
        return 1;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.ULTRA;
    }

    @Override
    public List<ItemType> getApplicableItems() {
        return Arrays.asList(ItemType.PICKAXE);
    }

}
