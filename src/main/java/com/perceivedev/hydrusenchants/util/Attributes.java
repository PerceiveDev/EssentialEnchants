package com.perceivedev.hydrusenchants.util;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.inventory.ItemStack;

import com.perceivedev.hydrusenchants.mininbt.ItemNBTUtil;
import com.perceivedev.hydrusenchants.mininbt.NBTWrappers.INBTBase;
import com.perceivedev.hydrusenchants.mininbt.NBTWrappers.NBTTagCompound;
import com.perceivedev.hydrusenchants.mininbt.NBTWrappers.NBTTagList;
import com.perceivedev.hydrusenchants.mininbt.NBTWrappers.NBTTagString;

/**
 * @author Rayzr
 *
 */
public class Attributes {

    public static ItemStack addAttribute(ItemStack itemStack, String attribute, int operation, double amount) {
        NBTTagCompound tag = ItemNBTUtil.getTag(itemStack);
        NBTTagList attributeModifiers = tag.hasKey("AttributeModifiers") ? (NBTTagList) tag.get("AttributeModifiers") : new NBTTagList();

        UUID randomID = UUID.randomUUID();

        NBTTagCompound attr = new NBTTagCompound();
        attr.setInt("Operation", operation);
        attr.setDouble("Amount", amount);
        attr.setString("Name", attribute);
        attr.setInt("UUIDMost", (int) randomID.getMostSignificantBits());
        attr.setInt("UUIDLeast", (int) randomID.getLeastSignificantBits());
        attr.setString("AttributeName", "generic.attackDamage");

        attributeModifiers.add(attr);

        tag.set("AttributeModifiers", attributeModifiers);

        return ItemNBTUtil.setNBTTag(tag, itemStack);
    }

    public static ItemStack removeAttribute(ItemStack itemStack, String attribute) {
        NBTTagCompound tag = ItemNBTUtil.getTag(itemStack);
        NBTTagList attributeModifiers = tag.hasKey("AttributeModifiers") ? (NBTTagList) tag.get("AttributeModifiers") : new NBTTagList();

        List<INBTBase> tagsToRemove = attributeModifiers.getRawList().stream()
                .filter(nbt -> nbt instanceof NBTTagCompound)
                .map(nbt -> (NBTTagCompound) nbt)
                .filter(nbt -> nbt.hasKeyOfType("Name", NBTTagString.class) && nbt.getString("Name").equals(attribute))
                .collect(Collectors.toList());

        attributeModifiers.getRawList().removeAll(tagsToRemove);

        tag.set("AttributeModifiers", attributeModifiers);

        return ItemNBTUtil.setNBTTag(tag, itemStack);
    }

}
