
package org.EMPCTF.model.impl;

import org.EMPCTF.model.Player;
import org.EMPCTF.model.StoreItem;

public class BigTNTItem extends StoreItem{
    public BigTNTItem(String n, int p)
    {
        super(n, p);
        description = "Get 6 TNTs with a 7x7x7 explosion";
    }
    public StoreItem getCopy()
    {
        return new BigTNTItem(name, price);
    }
    @Override
    public void activate(Player player)
    {
        player.tntRadius = 3;
        player.bigTNTRemaining = 6;
    }
}
