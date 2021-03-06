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
public class RagequitCommand implements Command{
    private static final RagequitCommand INSTANCE = new RagequitCommand();

    /**
     * Gets the singleton instance of this command.
     * @return The singleton instance of this command.
     */
    public static RagequitCommand getCommand() {
            return INSTANCE;
    }

    @Override
    public void execute(Player player, CommandParameters params) {
        player.getActionSender().sendLoginFailure("You ragequit from the server.");
        player.getSession().close();
        World.getWorld().broadcast("- &7"+player.getName()+" &cragequit &efrom the server");
        player.setAttribute("ragequits", (Integer) player.getAttribute("ragequits") + 1);
    }
}
