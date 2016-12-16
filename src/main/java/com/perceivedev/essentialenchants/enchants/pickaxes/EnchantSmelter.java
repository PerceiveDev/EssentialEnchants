package com.perceivedev.essentialenchants.enchants.pickaxes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.perceivedev.essentialenchants.util.Utils;

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
            ItemStack input = Utils.fixDurability(recipe.getInput());
            ItemStack result = Utils.fixDurability(recipe.getResult());
            smeltables.put(input, result);
        });
    }

    private static Optional<ItemStack> findSmeltResult(ItemStack input) {
        return smeltables.entrySet().stream().filter(k -> input.isSimilar(k.getKey()))
                .map(k -> k.getValue()).findFirst();
    }

    public EnchantSmelter() {
        super(BlockBreakEvent.class);

        registerEventHandler(BlockBreakEvent.class, e -> {
            BlockBreakEvent event = (BlockBreakEvent) e;
            if (event.isCancelled()) {
                return;
            }

            @SuppressWarnings("deprecation")
            ItemStack tool = event.getPlayer().getInventory().getItemInHand();
            if (!isEnchanted(tool)) {
                return;
            }

            Block block = event.getBlock();
            Collection<ItemStack> items = block.getDrops(tool);
            System.out.println("Items:\n- " + items.stream().map(i -> i.getType().name()).collect(Collectors.joining("\n- ")));

            List<ItemStack> filtered = new ArrayList<ItemStack>();
            boolean foundMatch = false;

            for (ItemStack item : items) {
                Optional<ItemStack> result = findSmeltResult(item);
                if (result.isPresent()) {
                    foundMatch = true;
                    System.out.println("Adding: " + result.get().getType().name());
                    filtered.add(result.get());
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
