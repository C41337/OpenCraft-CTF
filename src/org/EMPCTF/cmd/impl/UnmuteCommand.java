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
public class UnmuteCommand implements Command {
    
    private static final UnmuteCommand INSTANCE = new UnmuteCommand();

    /**
     * Gets the singleton instance of this command.
     * @return The singleton instance of this command.
     */
    public static UnmuteCommand getCommand() {
            return INSTANCE;
    }

    @Override
    public void execute(Player player, CommandParameters params) {
       
        if (player.isOwner() == true || player.isVIP() == true || player.isOp())
        {
            for (Player other : World.getWorld().getPlayerList().getPlayers()) {
                if (other.getName().toLowerCase().equals(params.getStringArgument(0).toLowerCase()) ) {
                        other.muted = false;
                        Server.log(player.getName()+" unmuted "+other.getName());
                        World.getWorld().broadcast(other.parseName()+" &ewas unmuted by "+(player.getName()));
                        other.getActionSender().sendChatMessage("&cYou were unmuted by "+(player.getName()));
                    }
                else {
                    player.wrapText("&cMake sure you enter the command correctly. Ex: &a/mute PlayerName");
                }
                }}
        else
        {
            player.getActionSender().sendChatMessage("&cYou do not have permission to use that command.");
        }
      
    }

}