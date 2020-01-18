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

import com.qiufeng.bedwars.util.Position;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

public class BWTeam {
    BWMap map;
    String name;
    Location spawn;
    Location bed_foot, bed_head;

    public static BWTeam fromConfig(BWMap map, String name, ConfigurationSection section) {
        BWTeam team = new BWTeam();
        team.setMap(map);
        team.setName(name);
        team.setSpawn(Position.getLocation(map.world, section.getIntegerList("spawn")));
        team.setBed_foot(Position.getLocation(map.world, section.getIntegerList("bed.foot")));
        team.setBed_head(Position.getLocation(map.world, section.getIntegerList("bed.head")));
        return team;
    }

    public BWMap getMap() {
        return map;
    }

    public void setMap(BWMap map) {
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getSpawn() {
        return spawn;
    }

    public void setSpawn(Location spawn) {
        this.spawn = spawn;
    }

    public Location getBed_foot() {
        return bed_foot;
    }

    public void setBed_foot(Location bed_foot) {
        this.bed_foot = bed_foot;
    }

    public Location getBed_head() {
        return bed_head;
    }

    public void setBed_head(Location bed_head) {
        this.bed_head = bed_head;
    }
}
