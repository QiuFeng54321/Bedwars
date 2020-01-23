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

import com.qiufeng.bedwars.map.BWMap;
import com.qiufeng.bedwars.map.BWTeam;
import org.bukkit.Bukkit;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Globals {
    public static Map<UUID, BWMap> playerStates;

    public static BWMap inGame(UUID uuid) {
        return playerStates.get(uuid);
    }

    /**
     * Let a player join the map
     *
     * @param uuid the player
     * @param map  the map to join
     * @return if success
     */
    public static boolean join(UUID uuid, BWMap map) {
        if (map.getStates().getGameStarted().getScore() == 1) {
            Objects.requireNonNull(Bukkit.getPlayer(uuid)).sendMessage("Game " + map.getName() + " is already started!");
            return false;
        }
        if (map.getStates().containsPlayer(uuid)) {
            Objects.requireNonNull(Bukkit.getPlayer(uuid)).sendMessage("You are already in this game!");
            return false;
        }
        tryKick(uuid);
        map.getStates().getNotGroupedPlayers().add(uuid);
        Objects.requireNonNull(Bukkit.getPlayer(uuid)).sendMessage("Successfully joined " + map.getName() + "!");
        return true;
    }

    /**
     * Let a player join a team
     *
     * @param uuid the player
     * @param team the team to join
     * @param map  the map to join
     * @return if success
     */
    public static boolean joinTeam(UUID uuid, BWTeam team, BWMap map) {
        if (map.getStates().getGameStarted().getScore() == 1) {
            Objects.requireNonNull(Bukkit.getPlayer(uuid)).sendMessage("Game is already started!");
            return false;
        }
        tryKick(uuid);
        map.getStates().getTeamListMap().get(team).add(uuid);
        return true;
    }

    public static void tryKick(UUID uuid) {
        BWMap map = inGame(uuid);
        Globals.playerStates.remove(uuid);
        if (map != null) map.getStates().kick(uuid);
    }
}