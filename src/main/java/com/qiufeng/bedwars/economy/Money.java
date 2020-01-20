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

package com.qiufeng.bedwars.economy;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class Money {
    int iron = 0, gold = 0, emerald = 0, diamond = 0;

    public Money(int iron, int gold, int emerald, int diamond) {
        this.iron = iron;
        this.gold = gold;
        this.emerald = emerald;
        this.diamond = diamond;
    }

    public Money() {
    }

    /**
     * Counts how much money a player has<br>
     *
     * @param player the player whom counts money on
     * @return how much money the player has
     */
    public static Money getPlayerMoney(Player player) {
        Money money = new Money();
        for (var item : player.getInventory().getStorageContents()) {
            if (item.getType() == Material.IRON_INGOT) money.iron += item.getAmount();
            else if (item.getType() == Material.GOLD_INGOT) money.gold += item.getAmount();
            else if (item.getType() == Material.EMERALD) money.emerald += item.getAmount();
            else if (item.getType() == Material.DIAMOND) money.diamond += item.getAmount();
        }
        return money;
    }

    /**
     * Tells if money is enough
     *
     * @param least the least money it should have
     * @return if the money is enough
     */
    public boolean has(Money least) {
        return iron >= least.iron &&
                gold >= least.gold &&
                emerald >= least.emerald &&
                diamond >= least.diamond;
    }

    /**
     * Removes money from self and return
     * @param toRemove money to remove
     * @return money after removal
     */
    public Money removeMoney(Money toRemove) {
        return new Money(
                iron - toRemove.iron,
                gold - toRemove.gold,
                emerald - toRemove.emerald,
                diamond - toRemove.diamond);
    }

    /**
     * Gets money from a config section
     *
     * @param section the section
     * @return money got
     */
    public static Money getMoney(ConfigurationSection section) {
        return new Money(
                section.getInt("iron", 0),
                section.getInt("gold", 0),
                section.getInt("emerald", 0),
                section.getInt("diamond", 0)
        );
    }

    /**
     * Gets money from a config section
     *
     * @param section the section
     * @return money got
     */
    public static Money getMoney(Map<String, Integer> section) {
        return new Money(
                section.getOrDefault("iron", 0),
                section.getOrDefault("gold", 0),
                section.getOrDefault("emerald", 0),
                section.getOrDefault("diamond", 0)
        );
    }

    /**
     * Removes player money
     *
     * @param player the player to be removed money
     */
    public void removeMoney(Player player) {
        player.getInventory().remove(new ItemStack(Material.IRON_INGOT, iron));
        player.getInventory().remove(new ItemStack(Material.GOLD_INGOT, gold));
        player.getInventory().remove(new ItemStack(Material.EMERALD, emerald));
        player.getInventory().remove(new ItemStack(Material.DIAMOND, diamond));
    }

    @Override
    public String toString() {
        return "Money{" +
                "iron=" + iron +
                ", gold=" + gold +
                ", emerald=" + emerald +
                ", diamond=" + diamond +
                '}';
    }

    /**
     * Simply clones the money
     *
     * @return cloned Money object
     */
    protected Money cloneMoney() {
        return new Money(iron, gold, emerald, diamond);
    }
}
