package com.qiufeng.bedwars.shop;

import com.qiufeng.bedwars.economy.Money;
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
     * Attempt to trade the item with player
     * @param player the player who wants to trade
     * @return if the trade succeeded.
     */
    boolean trade(Player player) {
        Money money = Money.getPlayerMoney(player);
        if(money.has(this.cost)) {
            this.cost.removeMoney(player);
            player.getInventory().addItem(this.gives);
            return true;
        } else {
            return false;
        }
    }
}
