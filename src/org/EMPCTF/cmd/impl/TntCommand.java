/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.EMPCTF.cmd.impl;

import org.EMPCTF.cmd.Command;
import org.EMPCTF.cmd.CommandParameters;
import org.EMPCTF.game.impl.CTFGameMode;
import org.EMPCTF.model.Player;
import org.EMPCTF.model.World;

/**
 *
 * @author Jacob Morgan
 */
public class TntCommand implements Command {
    private static final TntCommand INSTANCE = new TntCommand();

    /**
     * Gets the singleton instance of this command.
     * @return The singleton instance of this command.
     */
    public static TntCommand getCommand() {
            return INSTANCE;
    }

    @Override
    public void execute(Player player, CommandParameters params) {
            if(player.hasTNT)
            {
                ((CTFGameMode)World.getWorld().getGameMode()).explodeTNT(player, World.getWorld().getLevel(), player.tntX, player.tntY, player.tntZ, 2);
                player.hasTNT = false;
                player.tntX = 0;
                player.tntY = 0;
                player.tntZ = 0;
            }
            else {
            player.getActionSender().sendChatMessage("- &eYou haven't placed any TNT.");
        }
    }
}
