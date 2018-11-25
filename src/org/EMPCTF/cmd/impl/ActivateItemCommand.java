/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.EMPCTF.cmd.impl;

import org.EMPCTF.Server;
import org.EMPCTF.cmd.Command;
import org.EMPCTF.cmd.CommandParameters;
import org.EMPCTF.model.Player;
import org.EMPCTF.model.StoreItem;

/**
 *
 * @author Jacob Morgan
 */
public class ActivateItemCommand implements Command{
        private StoreItem item = null;
        public ActivateItemCommand(StoreItem item)
        {
            super();
            this.item = item;
        }

	@Override
	public void execute(Player player, CommandParameters params) {
            if(player.team == -1) {
                player.getActionSender().sendChatMessage("- &eYou need to join a team to do that!");
            }
            else
            {
                if(!(item.name.equals("Brush") && player.brush == true))
                {
                    boolean r = Server.getStore().buy(player, item.name);
                    if(r) {
                        item.activate(player);
                    }
                }
                else {
                    item.activate(player);
                }
            }
        }
}
