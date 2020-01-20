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

package com.qiufeng.bedwars;

import com.qiufeng.bedwars.config.BWConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class BWPlugin extends JavaPlugin {
    BWConfiguration configuration;

    public BWConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(BWConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();
        configuration = BWConfiguration.load(this, getConfig());
        getLogger().info(configuration.toString());
        getLogger().info("Bedwars enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Bedwars disabled!");
    }
}
