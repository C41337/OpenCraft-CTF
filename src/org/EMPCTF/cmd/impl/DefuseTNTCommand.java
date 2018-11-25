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
public class DefuseTNTCommand implements Command{
    private static final DefuseTNTCommand INSTANCE = new DefuseTNTCommand();

    /**
     * Gets the singleton instance of this command.
     * @return The singleton instance of this command.
     */
    public static DefuseTNTCommand getCommand() {
            return INSTANCE;
    }

    @Override
    public void execute(Player player, CommandParameters params) {
        player.hasTNT = false;
        World.getWorld().getLevel().setBlock(player.tntX, player.tntY, player.tntZ, 0);
        player.tntX = 0;
        player.tntY = 0;
        player.tntZ = 0;
        player.getActionSender().sendChatMessage("- &eTNT is no longer active!");
    }
}
