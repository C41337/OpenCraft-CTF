/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.EMPCTF.cmd.impl;

import org.EMPCTF.Server;
import org.EMPCTF.cmd.Command;
import org.EMPCTF.cmd.CommandParameters;
import org.EMPCTF.model.Player;
import org.EMPCTF.model.World;

/**
 *
 * @author Jacob Morgan
 */
public class PmCommand implements Command{
    private static final PmCommand INSTANCE = new PmCommand();

    /**
     * Gets the singleton instance of this command.
     * @return The singleton instance of this command.
     */
    public static PmCommand getCommand() {
            return INSTANCE;
    }

    @Override
    public void execute(Player player, CommandParameters params) {
        if(params.getArgumentCount() == 0)
        {
            player.getActionSender().sendChatMessage("- &e/pm [name] [message]");
        }
        String target = params.getStringArgument(0);
        for(Player p : World.getWorld().getPlayerList().getPlayers())
        {
            if(p.getName().equals(target))
            {
                String text = "";
                for(int i = 1; i < params.getArgumentCount(); i++)
                {
                    text += " "+params.getStringArgument(i);
                }
                if(!text.equals(""))
                {
                    p.getActionSender().sendChatMessage("&5(PM) "+player.parseName()+">&f"+text);
                    player.getActionSender().sendChatMessage("&5-->"+p.parseName()+">&f"+text);
                }
                else {
                    player.getActionSender().sendChatMessage("- &ePlease include a message.");
                }
                
            Server.log(player.getName()+"pmed"+player.parseName()+"the text"+text);
            }
        }
    }
}
