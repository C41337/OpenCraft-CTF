/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.EMPCTF.task.impl;

import org.EMPCTF.game.impl.CTFGameMode;
import org.EMPCTF.model.DropItem;
import org.EMPCTF.model.World;


public class ItemDropTask implements Runnable{
    private void dropItem()
    {
        int points;
        double r = Math.random();
        if(r < 0.01)
            points = 250;
        else if(r < 0.1)
            points = 100;
        else if(r < 0.4)
            points = 75;
        else if(r < 0.7)
            points = 50;
        else
            points = 15;
        if(((CTFGameMode)World.getWorld().getGameMode()).ready)
            new DropItem(points);
    }
    public void run()
    {
        while(true)
        {
            try {
                Thread.sleep(310 * 1000);
                dropItem();
            } catch (InterruptedException ex) {
            }
        }
    }
}
