package com.perceivedev.essentialenchants.util;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.inventory.ItemStack;

import com.perceivedev.essentialenchants.mininbt.ItemNBTUtil;
import com.perceivedev.essentialenchants.mininbt.NBTWrappers.INBTBase;
import com.perceivedev.essentialenchants.mininbt.NBTWrappers.NBTTagCompound;
import com.perceivedev.essentialenchants.mininbt.NBTWrappers.NBTTagList;

/**
 * @author Rayzr
 *
 */
public class Attributes {

    public static ItemStack addAttribute(ItemStack itemStack, String attribute, int operation, double amount, AttributeSlot slot) {
        NBTTagCompound tag = ItemNBTUtil.getTag(itemStack);
        NBTTagList attributeModifiers = tag.hasKey("AttributeModifiers") ? (NBTTagList) tag.get("AttributeModifiers") : new NBTTagList();

        UUID randomID = UUID.randomUUID();

        NBTTagCompound attr = new NBTTagCompound();
        attr.setInt("Operation", operation);
        attr.setDouble("Amount", amount);
        attr.setString("Name", attribute);
        attr.setInt("UUIDMost", (int) randomID.getMostSignificantBits());
        attr.setInt("UUIDLeast", (int) randomID.getLeastSignificantBits());
        attr.setString("AttributeName", attribute);
        attr.setString("Slot", slot.getInternal());

        attributeModifiers.add(attr);

        tag.set("AttributeModifiers", attributeModifiers);

        return ItemNBTUtil.setNBTTag(tag, itemStack);
    }

    public static ItemStack removeAttribute(ItemStack itemStack, String attribute, AttributeSlot slot) {
        NBTTagCompound tag = ItemNBTUtil.getTag(itemStack);
        NBTTagList attributeModifiers = tag.hasKey("AttributeModifiers") ? (NBTTagList) tag.get("AttributeModifiers") : new NBTTagList();

        List<INBTBase> tagsToRemove = attributeModifiers.getRawList().stream()
                .filter(nbt -> nbt instanceof NBTTagCompound)
                .map(nbt -> (NBTTagCompound) nbt)
                .filter(nbt -> attribute.equals(nbt.getString("Name")))
                .filter(nbt -> slot.getInternal().equals(nbt.getString("Slot")))
                .collect(Collectors.toList());

        attributeModifiers.getRawList().removeAll(tagsToRemove);

        tag.set("AttributeModifiers", attributeModifiers);

        return ItemNBTUtil.setNBTTag(tag, itemStack);
    }

}
