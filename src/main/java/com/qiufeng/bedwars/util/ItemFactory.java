/*
 * Copyright (C) 2020 Qiufeng54321
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.qiufeng.bedwars.util;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ItemFactory {
    public static String DEFAULT_ITEM_NAME = "DEFAULT";

    public static ItemStack getItemStack(ConfigurationSection section) {
        Material material = Material.getMaterial(Objects.requireNonNull(section.getString("type")));
        assert material != null;
        int amount = section.getInt("amount", 1);
        List<String> lore = section.getStringList("lore");
        String name = section.getString("name", DEFAULT_ITEM_NAME);
        return getItemStack(material, amount, name, lore);
    }

    public static ItemStack getItemStack(Map<String, Object> section) {
        Material material = Material.getMaterial((String) Objects.requireNonNull(section.get("type")));
        assert material != null;
        int amount = (int) section.getOrDefault("amount", 1);
        List<String> lore = (List<String>) section.getOrDefault("lore", new ArrayList<String>());
        String name = (String) section.getOrDefault("name", DEFAULT_ITEM_NAME);
        return getItemStack(material, amount, name, lore);
    }

    public static ItemStack getItemStack(Material material, int amount, String name, List<String> lore) {
        ItemStack itemStack = new ItemStack(material, amount);
        var meta = itemStack.getItemMeta();
        assert meta != null;
        if (!name.equals("DEFAULT")) meta.setDisplayName(name);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
