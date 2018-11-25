/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.EMPCTF.model.impl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.EMPCTF.cmd.Command;
import org.EMPCTF.model.Player;
import org.EMPCTF.model.StoreItem;

/**
 *
 * @author Jacob Morgan
 */
public class SimpleItem extends StoreItem{
    private Command command;
    public SimpleItem(String name, int price, String description, Command command)
    {
        super(name, price);
        this.command = command;
        this.description = description;
    }
    @Override
    public StoreItem getCopy() {
        return new SimpleItem(name, price, description, command);
    }

    @Override
    public void activate(Player p) {
        try {
            command.execute(p, null);
        } catch (IOException ex) {
            Logger.getLogger(SimpleItem.class.getName()).log(Level.WARNING, null, ex);
        }
    }

}
