/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.EMPCTF.task.impl;

import org.EMPCTF.game.impl.CTFGameMode;
import org.EMPCTF.model.Player;
import org.EMPCTF.model.World;
import org.EMPCTF.task.ScheduledTask;

/**
 *
 * @author Jacob Morgan
 */
public class CTFProcessTask extends ScheduledTask{

    private static final long DELAY = 500;
    private static CTFGameMode ctf = (CTFGameMode)World.getWorld().getGameMode();
    private static World world = World.getWorld();

    public CTFProcessTask()
    {
        super(DELAY);
    }

    public void execute() {
        for (Player player : world.getPlayerList().getPlayers())
        {
            ctf.processPlayerMove(player);
        }
    }

}
