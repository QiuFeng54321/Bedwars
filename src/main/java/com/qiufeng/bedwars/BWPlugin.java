package com.qiufeng.bedwars;

import org.bukkit.plugin.java.JavaPlugin;

public class BWPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Bedwars enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Bedwars disabled!");
    }
}
