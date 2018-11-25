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
public class BanCommand implements Command{

	private static final BanCommand INSTANCE = new BanCommand();

	/**
	 * Gets the singleton instance of this command.
	 * @return The singleton instance of this command.
	 */
	public static BanCommand getCommand() {
		return INSTANCE;
	}


	@Override
	public void execute(Player player, CommandParameters params) {
            if (player.isOp() || ("MrBluePotato".equals(player.getName())))
            {
                    for (Player other : World.getWorld().getPlayerList().getPlayers()) {
                            if (other.getName().toLowerCase().equals(params.getStringArgument(0).toLowerCase())) {
                                    Server.log(player.getName()+" banned "+other.getName());
                                    other.setAttribute("banned", "true");
                                    other.getActionSender().sendLoginFailure("You have been banned! Appeal at www.is.gd/wcbanappeal");
                                    other.getSession().close();
                                    World.getWorld().broadcast(other.parseName()+" was banned by "+(player.getName()));
                                    return;
                            }
                    }
            }
            else {
                player.getActionSender().sendChatMessage("&cYou do not have permission to use that command.");
            }
	}
}
