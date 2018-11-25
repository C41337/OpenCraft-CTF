/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.EMPCTF.cmd.impl;

import org.EMPCTF.Server;
import org.EMPCTF.cmd.Command;
import org.EMPCTF.cmd.CommandParameters;
import org.EMPCTF.model.MapController;
import org.EMPCTF.model.Player;

/**
 *
 * @author Jacob Morgan
 */
public class MapListCommand implements Command{
    private static final MapListCommand INSTANCE = new MapListCommand();

    /**
     * Gets the singleton instance of this command.
     * @return The singleton instance of this command.
     */
    public static MapListCommand getCommand() {
            return INSTANCE;
    }

    @Override
    public void execute(Player player, CommandParameters params) {
        Object[] names = MapController.levelNames.toArray();
        player.getActionSender().sendChatMessage("- &eAvailable maps:");
        String msg = "";
        for(Object map : names)
        {
            msg += ", "+map;
        }
        String[] lines = Server.wrapText(msg, 60);
        for(String l : lines)
        {
            player.getActionSender().sendChatMessage("- &a"+l);
        }
    }
}
