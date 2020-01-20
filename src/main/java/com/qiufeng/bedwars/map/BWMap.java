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
import com.qiufeng.bedwars.scoreboard.GameStates;
import com.qiufeng.bedwars.shop.Shop;
import com.qiufeng.bedwars.util.Position;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BWMap {
    BWMapConfiguration mapConfiguration;
    String name;
    World world;
    Shop shop;
    Location lobby;
    GameStates states;
    Map<String, BWTeam> teams;

    public static BWMap fromConfig(BWMapConfiguration configuration, String name, ConfigurationSection section) {
        BWMap map = new BWMap();
        var teamsSec = section.getConfigurationSection("teams");
        map.setMapConfiguration(configuration);
        map.setName(name);
        map.setWorld(Bukkit.getWorld(Objects.requireNonNull(section.getString("world"))));
        map.setLobby(Position.getLocation(map.getWorld(), section.getIntegerList("lobby")));
        map.setShop(configuration.getConfiguration().getShopConfiguration().getShops().get(
                section.getString("useshop")
        ));
        map.setTeams(new HashMap<>());
        map.setStates(GameStates.fromConfig(map, Objects.requireNonNull(section.getConfigurationSection("states"))));
        assert teamsSec != null;
        for (String key : teamsSec.getKeys(false)) {
            ConfigurationSection teamSec = teamsSec.getConfigurationSection(key);
            assert teamSec != null;
            map.teams.put(key, BWTeam.fromConfig(map, key, teamSec));
        }
        return map;
    }

    @Override
    public String toString() {
        return "BWMap{" +
                "name='" + name + '\'' +
                ", world=" + world +
                ", shop=" + shop +
                ", lobby=" + lobby +
                ", states=" + states +
                ", teams=" + teams +
                '}';
    }

    public GameStates getStates() {
        return states;
    }

    public void setStates(GameStates states) {
        this.states = states;
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

    public Location getLobby() {
        return lobby;
    }

    public void setLobby(Location lobby) {
        this.lobby = lobby;
    }

    public Map<String, BWTeam> getTeams() {
        return teams;
    }

    public void setTeams(Map<String, BWTeam> teams) {
        this.teams = teams;
    }
}
