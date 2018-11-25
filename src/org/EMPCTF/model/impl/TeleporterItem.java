/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.EMPCTF.model.impl;

import org.EMPCTF.model.BuildMode;
import org.EMPCTF.model.Player;
import org.EMPCTF.model.StoreItem;

/**
 *
 * @author Jacob
 */
public class TeleporterItem extends StoreItem{
    public TeleporterItem(String n, int p)
    {
        super(n, p);
    }
    
    @Override
    public StoreItem getCopy() {
        return new TeleporterItem(name, price);
    }

    @Override
    public void activate(Player p) {
        p.buildMode = BuildMode.TELE_ENTRANCE;
    }
    
}
