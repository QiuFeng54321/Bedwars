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

package com.qiufeng.bedwars.scoreboard;

import com.qiufeng.bedwars.map.BWMap;
import com.qiufeng.bedwars.map.BWTeam;
import com.qiufeng.bedwars.util.ScoreboardUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.scoreboard.Score;

import java.util.*;

public class GameStates {
    BWMap map;
    Map<BWTeam, List<UUID>> teamListMap;
    List<UUID> notGroupedPlayers;
    int maxPlayers;
    Score gameStarted, emeraldGeneration, diamondGeneration;

    public static GameStates fromConfig(BWMap map, ConfigurationSection section) {
        GameStates gameStates = new GameStates();
        gameStates.setMap(map);
        gameStates.setTeamListMap(new HashMap<>());
        gameStates.setGameStarted(ScoreboardUtils.fromConfig(Objects.requireNonNull(section.getConfigurationSection("game-started"))));
        gameStates.setEmeraldGeneration(ScoreboardUtils.fromConfig(Objects.requireNonNull(section.getConfigurationSection("emerald-generation"))));
        gameStates.setDiamondGeneration(ScoreboardUtils.fromConfig(Objects.requireNonNull(section.getConfigurationSection("diamond-generation"))));
        gameStates.setMaxPlayers(gameStates.getMap().getTeams().size() * 4);
        gameStates.setNotGroupedPlayers(new ArrayList<>());
        for (var kv : gameStates.getMap().getTeams().entrySet()) {
            gameStates.teamListMap.put(kv.getValue(), new ArrayList<>());
        }
        return gameStates;
    }

    /**
     * Kick a player
     *
     * @param uuid the player
     */
    public void kick(UUID uuid) {
        for (var players : teamListMap.values()) {
            players.remove(uuid);
        }
        notGroupedPlayers.remove(uuid);
        Objects.requireNonNull(Bukkit.getPlayer(uuid)).sendMessage("Left the game.");
    }

    public boolean containsPlayer(UUID uuid) {
        for (List<UUID> players : teamListMap.values()) {
            if (players.contains(uuid)) return true;
        }
        return notGroupedPlayers.contains(uuid);
    }

    public List<UUID> getNotGroupedPlayers() {
        return notGroupedPlayers;
    }

    public void setNotGroupedPlayers(List<UUID> notGroupedPlayers) {
        this.notGroupedPlayers = notGroupedPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public BWMap getMap() {
        return map;
    }

    public void setMap(BWMap map) {
        this.map = map;
    }

    public Map<BWTeam, List<UUID>> getTeamListMap() {
        return teamListMap;
    }

    public void setTeamListMap(Map<BWTeam, List<UUID>> teamListMap) {
        this.teamListMap = teamListMap;
    }

    public Score getGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(Score gameStarted) {
        this.gameStarted = gameStarted;
    }

    public Score getEmeraldGeneration() {
        return emeraldGeneration;
    }

    public void setEmeraldGeneration(Score emeraldGeneration) {
        this.emeraldGeneration = emeraldGeneration;
    }

    public Score getDiamondGeneration() {
        return diamondGeneration;
    }

    public void setDiamondGeneration(Score diamondGeneration) {
        this.diamondGeneration = diamondGeneration;
    }

    @Override
    public String toString() {
        return "GameStates{" +
                "teamListMap=" + teamListMap +
                ", notGroupedPlayers=" + notGroupedPlayers +
                ", maxPlayers=" + maxPlayers +
                ", gameStarted=" + gameStarted +
                ", emeraldGeneration=" + emeraldGeneration +
                ", diamondGeneration=" + diamondGeneration +
                '}';
    }
}