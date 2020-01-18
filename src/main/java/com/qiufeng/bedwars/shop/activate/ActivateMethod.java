package com.qiufeng.bedwars.shop.activate;

import org.bukkit.event.Event;

public interface ActivateMethod {
    default boolean needActivate(Event event) {
        return false;
    }
}
