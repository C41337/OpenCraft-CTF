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
public class MeCommand implements Command{

    private static final MeCommand INSTANCE = new MeCommand();

    /**
     * Gets the singleton instance of this command.
     * @return The singleton instance of this command.
     */
    public static MeCommand getCommand() {
            return INSTANCE;
    }

    @Override
    public void execute(Player player, CommandParameters params) {
        String text = "";
        if (!player.muted){
        for(int i = 0; i < params.getArgumentCount(); i++)
        {
            text += " "+params.getStringArgument(i);
        }
        if(params.getArgumentCount() > 0) {
                World.getWorld().broadcast("&5*"+player.getName()+"&5"+text);
            }
        } 
        else {
            player.getActionSender().sendChatMessage("&cYou cannot use this command white you are muted.");
        }
    }

}
