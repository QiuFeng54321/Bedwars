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

package com.qiufeng.bedwars.shop.activate;

import com.qiufeng.bedwars.BWPlugin;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class ItemActivateMethod implements ActivateMethod<PlayerInteractEvent> {
    BWPlugin plugin;
    ItemStack itemStack;

    public ItemActivateMethod(BWPlugin plugin, ItemStack itemStack) {
        this.plugin = plugin;
        this.itemStack = itemStack;
    }

    @Override
    public boolean needActivate(PlayerInteractEvent event) {
        if (Objects.equals(event.getItem(), itemStack)) {
            //TODO only return true when in game
            return event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK;
        }
        return false;
    }

    @Override
    public String toString() {
        return "ItemActivateMethod{" +
                "itemStack=" + itemStack +
                '}';
    }
}
