package com.qiufeng.bedwars.shop.activate;

import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class ItemActivateMethod implements ActivateMethod {
    @Override
    public boolean needActivate(Event event) {
        if(event instanceof PlayerInteractEntityEvent) {

        }
        return false;
    }
}
