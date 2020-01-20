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

import com.qiufeng.bedwars.map.BWMap;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;

public class BWMapConfiguration {
    Map<String, BWMap> maps;
    BWConfiguration configuration;

    public static BWMapConfiguration load(BWConfiguration configuration, ConfigurationSection section) {
        BWMapConfiguration res = new BWMapConfiguration();
        res.setConfiguration(configuration);
        res.maps = new HashMap<>();
        for (String key : section.getKeys(false)) {
            ConfigurationSection mapSec = section.getConfigurationSection(key);
            assert mapSec != null;
            BWMap map = BWMap.fromConfig(res, key, mapSec);
            res.maps.put(key, map);
        }
        return res;
    }

    @Override
    public String toString() {
        return "BWMapConfiguration{" +
                "maps=" + maps +
                '}';
    }

    public Map<String, BWMap> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, BWMap> maps) {
        this.maps = maps;
    }

    public BWConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(BWConfiguration configuration) {
        this.configuration = configuration;
    }
}
