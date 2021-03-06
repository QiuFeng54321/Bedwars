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

package com.qiufeng.bedwars.shop;

import com.qiufeng.bedwars.config.BWShopConfiguration;
import com.qiufeng.bedwars.shop.activate.ActivateMethod;
import com.qiufeng.bedwars.shop.activate.ItemActivateMethod;
import com.qiufeng.bedwars.util.ItemFactory;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shop {
    String name;
    List<ActivateMethod<?>> activateMethod;
    Map<String, ShopGroup> groups;
    BWShopConfiguration parent;

    public static Shop fromConfig(BWShopConfiguration parent, String name, ConfigurationSection section) {
        Shop shop = new Shop();
        shop.setParent(parent);
        shop.setName(name);
        shop.activateMethod = new ArrayList<>();
        shop.groups = new HashMap<>();
        shop.getParent().getConfiguration().getPlugin().getLogger().info(section.getValues(true).toString());
        List<Map<?, ?>> activateMethodsSec = section.getMapList("actmethods");
        ConfigurationSection groupsSec = section.getConfigurationSection("groups");
        assert groupsSec != null;
        shop.getParent().getConfiguration().getPlugin().getLogger().info(groupsSec.toString());
        for (Map<?, ?> activateMethodSec : activateMethodsSec) {
            assert activateMethodSec != null;
            Map<?, ?> dataSec = (Map<?, ?>) activateMethodSec.get("data");
            String type = (String) activateMethodSec.get("type");
            assert type != null;
            if (type.equals("item")) {
                assert dataSec != null;
                ItemActivateMethod method = new ItemActivateMethod(parent.getConfiguration().getPlugin(), ItemFactory.getItemStack((Map<String, Object>) dataSec));
                shop.activateMethod.add(method);
            }
        }
        for (String key : groupsSec.getKeys(false)) {
            ConfigurationSection groupSec = groupsSec.getConfigurationSection(key);
            assert groupSec != null;
            ShopGroup shopGroup = ShopGroup.fromConfig(shop, key, groupSec);
            shop.groups.put(key, shopGroup);
        }
        return shop;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "name='" + name + '\'' +
                ", activateMethod=" + activateMethod +
                ", groups=" + groups +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ActivateMethod<?>> getActivateMethod() {
        return activateMethod;
    }

    public void setActivateMethod(List<ActivateMethod<?>> activateMethod) {
        this.activateMethod = activateMethod;
    }

    public Map<String, ShopGroup> getGroups() {
        return groups;
    }

    public void setGroups(Map<String, ShopGroup> groups) {
        this.groups = groups;
    }

    public BWShopConfiguration getParent() {
        return parent;
    }

    public void setParent(BWShopConfiguration parent) {
        this.parent = parent;
    }
}
