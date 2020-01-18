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

package com.qiufeng.bedwars.map;

import com.qiufeng.bedwars.config.BWMapConfiguration;
import com.qiufeng.bedwars.shop.Shop;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Map;
import java.util.Objects;

public class BWMap {
    BWMapConfiguration mapConfiguration;
    String name;
    World world;
    Shop shop;
    Map<String, BWTeam> teams;

    public static BWMap fromConfig(BWMapConfiguration configuration, String name, ConfigurationSection section) {
        BWMap map = new BWMap();
        map.setMapConfiguration(configuration);
        map.setName(name);
        map.setWorld(Bukkit.getWorld(Objects.requireNonNull(section.getString("world"))));
        map.setShop(configuration.getConfiguration().getShopConfiguration().getShops().get(
                section.getString("useshop")
        ));
        return map;
    }

    public BWMapConfiguration getMapConfiguration() {
        return mapConfiguration;
    }

    public void setMapConfiguration(BWMapConfiguration mapConfiguration) {
        this.mapConfiguration = mapConfiguration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Map<String, BWTeam> getTeams() {
        return teams;
    }

    public void setTeams(Map<String, BWTeam> teams) {
        this.teams = teams;
    }
}
