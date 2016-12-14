package com.perceivedev.essentialenchants.util;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang.Validate;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.SkullType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.Colorable;

/**
 * @author Rayzr
 *
 */
public class ItemFactory {

    private ItemStack item;

    private ItemFactory(ItemStack base) {
        this.item = base.clone();
    }

    /**
     * Creates an {@link ItemFactory item factory} from the {@code base}
     * {@link ItemStack item}
     * 
     * @param base the base item to use
     * @return a new {@link ItemFactory item factory}
     * @throws NullPointerException if {@code base == null}
     */
    public static ItemFactory builder(ItemStack base) {
        Objects.requireNonNull(base, "base can not be null");
        return new ItemFactory(base);
    }

    /**
     * Creates an {@link ItemFactory item factory} from the {@code base}
     * {@link Material material}
     * 
     * @param base the base material to use
     * @return a new {@link ItemFactory item factory}
     * @throws NullPointerException if {@code base == null}
     */
    public static ItemFactory builder(Material base) {
        return builder(base == null ? null : new ItemStack(base));
    }

    /**
     * Sets the name of the item (applies color codes)
     * 
     * @param name the name to set
     * @return this {@link ItemFactory}
     */
    public ItemFactory setName(String name) {
        ItemMeta meta = getMeta();
        meta.setDisplayName(TextUtils.colorize(name));
        setMeta(meta);
        return this;
    }

    /**
     * Sets the lore of the item (applies color codes)
     * 
     * @param lore the lore to set
     * @return this {@link ItemFactory}
     */
    public ItemFactory setLore(List<String> lore) {
        if (lore == null) {
            return this;
        }
        ItemMeta meta = getMeta();
        lore = lore.stream().map(TextUtils::colorize).collect(Collectors.toList());
        meta.setLore(lore);
        setMeta(meta);
        return this;
    }

    /**
     * Sets the lore of the item (applies color codes)
     * 
     * @param lore the lore to set
     * @return this {@link ItemFactory}
     */
    public ItemFactory setLore(String... lore) {
        return setLore(Arrays.asList(lore));
    }

    /**
     * Adds lore to the item (applies color codes)
     * 
     * @param lore the lore to add
     * @return this {@link ItemFactory}
     */
    public ItemFactory addLore(List<String> lore) {
        if (lore == null) {
            return this;
        }
        lore = lore.stream().map(TextUtils::colorize).collect(Collectors.toList());

        ItemMeta meta = getMeta();
        if (meta.hasLore()) {
            List<String> newLore = meta.getLore();
            newLore.addAll(lore);
            meta.setLore(newLore);
        } else {
            meta.setLore(lore);
        }
        setMeta(meta);
        return this;
    }

    /**
     * Adds lore to the item (applies color codes)
     * 
     * @param lore the lore to add
     * @return this {@link ItemFactory}
     */
    public ItemFactory addLore(String... lore) {
        return addLore(Arrays.asList(lore));
    }

    /**
     * Sets the amount of the item
     * 
     * @param amount the amount to set
     * @return this {@link ItemFactory}
     */
    public ItemFactory setAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    /**
     * Sets the durability of the item
     * 
     * @param durability the durability to set
     * @return this {@link ItemFactory}
     */
    public ItemFactory setDurability(int durability) {
        Validate.isTrue(durability >= Short.MIN_VALUE, "durability is out the range of -32,767 to 32,767");
        Validate.isTrue(durability <= Short.MAX_VALUE, "durability is out the range of -32,767 to 32,767");
        item.setDurability((short) durability);
        return this;
    }

    /**
     * Sets the color of an item
     * 
     * @param color the color to set
     * @return this {@link ItemFactory}
     * @throws IllegalArgumentException if the item is not colorable
     */
    public ItemFactory setColor(DyeColor color) {
        Validate.isTrue(item.getItemMeta() instanceof Colorable, "item is not colorable!");
        ((Colorable) item.getItemMeta()).setColor(color);
        return this;
    }

    /**
     * Gets the {@link ItemMeta} from the item
     * 
     * @return the {@link ItemMeta}
     */
    public ItemMeta getMeta() {
        return item.getItemMeta();
    }

    /**
     * Sets the {@link ItemMeta} on the item
     * 
     * @param meta the new {@link ItemMeta} to use
     * @return this {@link ItemFactory}
     */
    public ItemFactory setMeta(ItemMeta meta) {
        item.setItemMeta(meta);
        return this;
    }

    /**
     * Sets the {@link SkullType} of this item
     * 
     * @param type the {@link SkullType} to set for this item
     * @return this {@link ItemFactory}
     * @throws IllegalArgumentException if the item is not a
     *             {@link Material#SKULL_ITEM skull item}
     */
    public ItemFactory setSkullType(SkullType type) {
        Validate.isTrue(getMeta() instanceof SkullMeta, "item is not a skull!");
        setDurability(type.ordinal());
        return this;
    }

    /**
     * Sets the owner of this item (only works on player heads, see
     * {@link #setSkullType(SkullType)})
     * 
     * @param owner the name of the owner
     * @return this {@link ItemFactory}
     * @throws IllegalArgumentException if this is not a player head
     */
    public ItemFactory setSkullOwner(String owner) {
        Validate.isTrue(getMeta() instanceof SkullMeta && item.getDurability() == SkullType.PLAYER.ordinal(), "item is not a player head!");
        SkullMeta meta = (SkullMeta) getMeta();
        meta.setOwner(owner);
        setMeta(meta);
        return this;
    }

    /**
     * Sets the owner of this item (only works on player heads, see
     * {@link #setSkullType(SkullType)})
     * <br>
     * <br>
     * This calls {@link #setSkullOwner(OfflinePlayer)} with
     * {@code owner.getName()} as the parameter
     * 
     * @param owner the owner of this item
     * @return this {@link ItemFactory}
     * @throws IllegalArgumentException if this is not a player head
     */
    public ItemFactory setSkullOwner(OfflinePlayer owner) {
        return setSkullOwner(owner.getName());
    }

    /**
     * Builds the item (modifying item does not modify this factory)
     * 
     * @return the item
     */
    public ItemStack build() {
        return item.clone();
    }

}
