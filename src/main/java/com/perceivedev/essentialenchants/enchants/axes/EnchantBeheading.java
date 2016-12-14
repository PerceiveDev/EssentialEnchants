package com.perceivedev.essentialenchants.enchants.axes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.perceivedev.essentialenchants.ItemType;
import com.perceivedev.essentialenchants.enchant.Rarity;
import com.perceivedev.essentialenchants.enchant.types.Enchant;
import com.perceivedev.essentialenchants.util.ItemFactory;

@SuppressWarnings("deprecation")
public class EnchantBeheading extends Enchant {

    private static final Map<Predicate<Entity>, Function<Entity, ItemStack>> entities = new HashMap<>();

    static {
        entities.put(e -> e.getType() == EntityType.SKELETON,
                e -> ItemFactory.builder(Material.SKULL_ITEM).setSkullType(SkullType.SKELETON).build());

        entities.put(e -> e.getType() == EntityType.ZOMBIE,
                e -> ItemFactory.builder(Material.SKULL_ITEM).setSkullType(SkullType.ZOMBIE).build());

        entities.put(e -> e.getType() == EntityType.SKELETON && ((Skeleton) e).getSkeletonType() == SkeletonType.WITHER,
                e -> ItemFactory.builder(Material.SKULL_ITEM).setSkullType(SkullType.ZOMBIE).build());

        entities.put(e -> e.getType() == EntityType.PLAYER,
                e -> ItemFactory.builder(Material.SKULL_ITEM).setSkullType(SkullType.PLAYER).setSkullOwner((Player) e).build());

        entities.put(e -> e.getType() == EntityType.CREEPER,
                e -> ItemFactory.builder(Material.SKULL_ITEM).setSkullType(SkullType.CREEPER).build());

        try {
            EntityType.ENDER_DRAGON.ordinal();
            entities.put(e -> e.getType() == EntityType.ENDER_DRAGON,
                    e -> ItemFactory.builder(Material.SKULL_ITEM).setSkullType(SkullType.DRAGON).build());
        } catch (EnumConstantNotPresentException e) {
            // Ignore, it's just an older version of MC
        }

    }

    public EnchantBeheading() {
        super(EntityDeathEvent.class);

        registerEventHandler(EntityDeathEvent.class, e -> {
            EntityDeathEvent event = (EntityDeathEvent) e;

            Player player = event.getEntity().getKiller();
            if (player == null) {
                return;
            }

            if (!isEnchanted(player.getInventory().getItemInMainHand())) {
                return;
            }

            if (!entities.keySet().contains(event.getEntityType())) {
                return;
            }

            if (Math.random() < 0.50) {
                return;
            }

            event.getDrops().add(entities.get(event.getEntityType()).apply(event.getEntity()));

        });
    }

    @Override
    public String getIdentifier() {
        return "VAMPIRIC";
    }

    @Override
    public String getDisplay() {
        return "Vampiric";
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
        return Arrays.asList(ItemType.SWORD);
    }

}
