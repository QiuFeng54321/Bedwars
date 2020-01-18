package com.qiufeng.bedwars.config;

import com.qiufeng.bedwars.shop.Shop;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class BWShopConfiguration {
    List<Shop> shops;

    public static BWShopConfiguration load(ConfigurationSection section) {
        BWShopConfiguration res = new BWShopConfiguration();
        res.shops = new ArrayList<>();
        return res;
    }
}
