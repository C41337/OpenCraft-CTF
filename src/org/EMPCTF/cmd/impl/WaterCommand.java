/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.EMPCTF.cmd.impl;

import org.EMPCTF.cmd.Command;
import org.EMPCTF.cmd.CommandParameters;
import org.EMPCTF.model.Player;

/**
 *
 * @author Jacob Morgan
 */
public class WaterCommand implements Command{

    private static final WaterCommand INSTANCE = new WaterCommand();

    /**
     * Gets the singleton instance of this command.
     * @return The singleton instance of this command.
     */
    public static WaterCommand getCommand() {
            return INSTANCE;
    }

    @Override
    public void execute(Player player, CommandParameters params) {
        if(player.placeBlock == -1)
        {
            player.placeBlock = 9;
            player.getActionSender().sendChatMessage("&1Lava&e mode&f: On");
        }
        else
        {
            player.placeBlock = -1;
            player.getActionSender().sendChatMessage("&1Lava&e mode&f: Off");
        }
    }

}
