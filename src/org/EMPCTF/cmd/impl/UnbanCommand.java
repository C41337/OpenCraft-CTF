/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.EMPCTF.cmd.impl;

import org.EMPCTF.Server;
import org.EMPCTF.cmd.Command;
import org.EMPCTF.cmd.CommandParameters;
import org.EMPCTF.model.Player;
import org.EMPCTF.persistence.LoadPersistenceRequest;
import org.EMPCTF.persistence.SavePersistenceRequest;

/**
 *
 * @author Jacob
 */
public class UnbanCommand implements Command{
    
        private static final UnbanCommand INSTANCE = new UnbanCommand();

	/**
	 * Gets the singleton instance of this command.
	 * @return The singleton instance of this command.
	 */
	public static UnbanCommand getCommand() {
		return INSTANCE;
	}


	@Override
	public void execute(Player player, CommandParameters params) {
            if (player.isOp() || ("MrBluePotato".equals(player.getName())))
            {
                   Player p = new Player(null, params.getStringArgument(0));
                    try
                    {
                        Server.log(player.getName()+" unbanned "+p.getName());
                        new LoadPersistenceRequest(p).perform();
                        p.setAttribute("banned", "false");
                        new SavePersistenceRequest(p).perform();
                        player.getActionSender().sendChatMessage(p.getName()+" was unbanned.");
                    }
                    catch(Exception e)
                    {
                    }
            }
            else {
                player.getActionSender().sendChatMessage("&cYou do not have permission to use that command.");
            }
	}
}
