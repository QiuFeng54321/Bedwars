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
import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class EntityActivateMethod implements ActivateMethod<PlayerInteractEntityEvent> {
    BWPlugin plugin;
    Entity entity;

    public EntityActivateMethod(BWPlugin plugin, Entity entity) {
        this.plugin = plugin;
        this.entity = entity;
    }

    @Override
    public boolean needActivate(PlayerInteractEntityEvent event) {
        Entity ent = event.getRightClicked();
        return ent.equals(entity);
    }
}
