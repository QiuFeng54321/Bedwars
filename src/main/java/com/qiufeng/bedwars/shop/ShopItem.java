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

package com.qiufeng.bedwars.shop;

import com.qiufeng.bedwars.economy.Money;
import com.qiufeng.bedwars.util.ItemFactory;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ShopItem {
    private ShopGroup group;
    int slot;
    ItemStack shownAs;
    ItemStack gives;
    Money cost;

    public ShopItem(ShopGroup group, int slot, ItemStack shownAs, ItemStack gives, Money cost) {
        this.group = group;
        this.slot = slot;
        this.shownAs = shownAs;
        this.gives = gives;
        this.cost = cost;
    }

    /**
     * Gets a shop item from given configuration section
     *
     * @param group   the shop group the item belongs to
     * @param section the configuration section
     * @return shop item
     */
    public static ShopItem fromConfig(ShopGroup group, ConfigurationSection section) {
        ConfigurationSection shownAsSec = section.getConfigurationSection("shownas");
        ConfigurationSection givesSec = section.getConfigurationSection("gives");
        ConfigurationSection costSec = section.getConfigurationSection("cost");
        assert shownAsSec != null;
        ItemStack shownAs = ItemFactory.getItemStack(shownAsSec);
        int slot = shownAsSec.getInt("slot");
        assert givesSec != null;
        ItemStack gives = ItemFactory.getItemStack(givesSec);
        assert costSec != null;
        Money cost = Money.getMoney(costSec);
        return new ShopItem(group, slot, shownAs, gives, cost);
    }

    /**
     * Attempt to trade the item with player
     *
     * @param player the player who wants to trade
     * @return if the trade succeeded.
     */
    boolean trade(Player player) {
        Money money = Money.getPlayerMoney(player);
        if (money.has(this.cost)) {
            this.cost.removeMoney(player);
            player.getInventory().addItem(this.gives);
            return true;
        } else {
            return false;
        }
    }
}
