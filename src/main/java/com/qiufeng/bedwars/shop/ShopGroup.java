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

import java.util.List;

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
        ConfigurationSection shownAsSec = section.getConfigurationSection("shownas");
        ConfigurationSection itemsSec = section.getConfigurationSection("itemsSec");

        assert shownAsSec != null;
        shopGroup.shownAs = ItemFactory.getItemStack(shownAsSec);
        shopGroup.slot = shownAsSec.getInt("slot");

        assert itemsSec != null;
        for (String key : itemsSec.getKeys(false)) {
            ConfigurationSection itemSec = itemsSec.getConfigurationSection(key);
            assert itemSec != null;
            shopGroup.items.add(ShopItem.fromConfig(shopGroup, itemSec));
        }

        return shopGroup;
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
