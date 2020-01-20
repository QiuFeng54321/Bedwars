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

package com.qiufeng.bedwars.util;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.scoreboard.Score;

import java.util.Objects;

public class ScoreboardUtils {
    public static Score fromConfig(ConfigurationSection section) {
        String objective = section.getString("objective");
        String player = section.getString("player");
        assert objective != null;
        assert player != null;
        return Objects.requireNonNull(
                Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard().getObjective(objective)
        ).getScore(player);
    }
}
