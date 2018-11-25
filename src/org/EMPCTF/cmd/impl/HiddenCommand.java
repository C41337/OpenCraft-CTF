/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.EMPCTF.cmd.impl;

import org.EMPCTF.cmd.Command;
import org.EMPCTF.cmd.CommandParameters;
import org.EMPCTF.model.Player;
import org.EMPCTF.model.World;

/**
 *
 * @author Jacob Morgan
 */
public class HiddenCommand implements Command{
    private static final HiddenCommand INSTANCE = new HiddenCommand();

    /**
     * Gets the singleton instance of this command.
     * @return The singleton instance of this command.
     */
    public static HiddenCommand getCommand() {
            return INSTANCE;
    }

    @Override
    public void execute(Player player, CommandParameters params) {
		if (player.isOp()) {
                    String message = "- &eHidden players: ";
                    for(Player p : World.getWorld().getPlayerList().getPlayers())
                    {
                        if(!p.isVisible) {
                            message += p.parseName()+",";
                        }
                    }
                    player.getActionSender().sendChatMessage(message);
		} else {
            player.getActionSender().sendChatMessage("&cYou do not have permission to use that command.");
        }
    }
}
