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

import com.qiufeng.bedwars.BWPlugin;
import org.bukkit.configuration.Configuration;

import java.util.Objects;

public class BWConfiguration {
    BWPlugin plugin;
    BWMapConfiguration mapConfiguration;
    BWShopConfiguration shopConfiguration;

    public static BWConfiguration load(BWPlugin plugin, Configuration configuration) {
        BWConfiguration bwConfiguration = new BWConfiguration();
        bwConfiguration.setPlugin(plugin);
        bwConfiguration.setShopConfiguration(BWShopConfiguration.load(
                bwConfiguration,
                Objects.requireNonNull(configuration.getConfigurationSection("shops"))
        ));
        bwConfiguration.setMapConfiguration(BWMapConfiguration.load(
                bwConfiguration,
                Objects.requireNonNull(configuration.getConfigurationSection("maps"))
        ));
        return bwConfiguration;
    }

    @Override
    public String toString() {
        return "BWConfiguration{" +
                "mapConfiguration=" + mapConfiguration +
                ", shopConfiguration=" + shopConfiguration +
                '}';
    }

    public BWPlugin getPlugin() {
        return plugin;
    }

    public void setPlugin(BWPlugin plugin) {
        this.plugin = plugin;
    }

    public BWMapConfiguration getMapConfiguration() {
        return mapConfiguration;
    }

    public void setMapConfiguration(BWMapConfiguration mapConfiguration) {
        this.mapConfiguration = mapConfiguration;
    }

    public BWShopConfiguration getShopConfiguration() {
        return shopConfiguration;
    }

    public void setShopConfiguration(BWShopConfiguration shopConfiguration) {
        this.shopConfiguration = shopConfiguration;
    }
}
