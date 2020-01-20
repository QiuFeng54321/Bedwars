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

package com.qiufeng.bedwars.config;

import com.qiufeng.bedwars.shop.Shop;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;

public class BWShopConfiguration {
    BWConfiguration configuration;
    Map<String, Shop> shops;

    public static BWShopConfiguration load(BWConfiguration configuration, ConfigurationSection section) {
        BWShopConfiguration res = new BWShopConfiguration();
        res.setConfiguration(configuration);
        res.shops = new HashMap<>();
        for (String key : section.getKeys(false)) {
            ConfigurationSection shopSec = section.getConfigurationSection(key);
            assert shopSec != null;
            res.getConfiguration().getPlugin().getLogger().info(shopSec.getValues(true).toString());
            Shop shop = Shop.fromConfig(res, key, shopSec);
            res.shops.put(key, shop);
        }
        return res;
    }

    @Override
    public String toString() {
        return "BWShopConfiguration{" +
                "shops=" + shops +
                '}';
    }

    public BWConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(BWConfiguration configuration) {
        this.configuration = configuration;
    }

    public Map<String, Shop> getShops() {
        return shops;
    }

    public void setShops(Map<String, Shop> shops) {
        this.shops = shops;
    }
}
