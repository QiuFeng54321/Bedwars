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

import com.qiufeng.bedwars.util.ItemFactory;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShopGroup {
    String name;
    private Shop shop;
    ItemStack shownAs;
    int slot;
    List<ShopItem> items;

    /**
     * Gets a shop group from a configuration section
     *
     * @param shop    parent shop
     * @param section the configuration section
     * @return shop group
     */
    public static ShopGroup fromConfig(Shop shop, String name, ConfigurationSection section) {
        ShopGroup shopGroup = new ShopGroup();
        shopGroup.name = name;
        shopGroup.shop = shop;
        shopGroup.setItems(new ArrayList<>());
        ConfigurationSection shownAsSec = section.getConfigurationSection("shownas");
        List<Map<?, ?>> itemsSec = section.getMapList("items");

        shopGroup.getShop().getParent().getConfiguration().getPlugin().getLogger().info(itemsSec.toString());

        assert shownAsSec != null;
        shopGroup.shownAs = ItemFactory.getItemStack(shownAsSec);
        shopGroup.slot = shownAsSec.getInt("slot");

        for (Map<?, ?> itemSec : itemsSec) {
            assert itemSec != null;
            shopGroup.items.add(ShopItem.fromConfig(shopGroup, (Map<String, Object>) itemSec));
        }

        return shopGroup;
    }

    @Override
    public String toString() {
        return "ShopGroup{" +
                "name='" + name + '\'' +
                ", shownAs=" + shownAs +
                ", slot=" + slot +
                ", items=" + items +
                '}';
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemStack getShownAs() {
        return shownAs;
    }

    public void setShownAs(ItemStack shownAs) {
        this.shownAs = shownAs;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public List<ShopItem> getItems() {
        return items;
    }

    public void setItems(List<ShopItem> items) {
        this.items = items;
    }
}
