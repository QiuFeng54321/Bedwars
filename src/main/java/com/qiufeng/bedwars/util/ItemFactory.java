package com.qiufeng.bedwars.util;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Objects;

public class ItemFactory {
    public static ItemStack getItemStack(ConfigurationSection section) {
        Material material = Material.getMaterial(Objects.requireNonNull(section.getString("type")));
        assert material != null;
        int amount = section.getInt("amount", 1);
        List<String> lore = section.getStringList("lore");
        String name = section.getString("name", material.getKey().getKey());
        return getItemStack(material, amount, name, lore);
    }
    public static ItemStack getItemStack(Material material, int amount, String name, List<String> lore) {
        ItemStack itemStack = new ItemStack(material, amount);
        var meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
