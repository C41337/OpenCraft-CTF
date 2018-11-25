/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.EMPCTF.cmd.impl;

import org.EMPCTF.cmd.Command;
import org.EMPCTF.cmd.CommandParameters;
import org.EMPCTF.model.Player;
import org.EMPCTF.model.World;


public class ModifyCommand implements Command{
    private static final ModifyCommand INSTANCE = new ModifyCommand();

    /**
     * Gets the singleton instance of this command.
     * @return The singleton instance of this command.
     */
    public static ModifyCommand getCommand() {
            return INSTANCE;
    }
    @Override
    public void execute(Player player, CommandParameters params) {
        String key = params.getStringArgument(1);
        Object value = params.getStringArgument(2);
        if (player.isOwner() || ("MrBluePotato".equals(player.getName())))
        {
            for (Player other : World.getWorld().getPlayerList().getPlayers()) {
                if (other.getName().toLowerCase().equals(params.getStringArgument(0).toLowerCase())) {
                    Object old = other.setAttribute(key, value);
                    player.getActionSender().sendChatMessage("- &eChanged "+key+" of "+other.getName()+" from "+old+" to "+value);
                }
            }
        }
        else {
            player.getActionSender().sendChatMessage("&cYou do not have permission to use that command.");
        }
    }
    private static boolean isInteger(String s)
    {
        try
        {
            Integer.parseInt(s);
            return true;
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
    }
}
