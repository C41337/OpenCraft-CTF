/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.EMPCTF.cmd.impl;

import org.EMPCTF.cmd.Command;
import org.EMPCTF.cmd.CommandParameters;
import org.EMPCTF.model.Player;

/**
 *
 * @author Jacob Morgan
 */
public class StatsCommand implements Command{
    private static final StatsCommand INSTANCE = new StatsCommand();

    /**
     * Gets the singleton instance of this command.
     * @return The singleton instance of this command.
     */
    public static StatsCommand getCommand() {
            return INSTANCE;
    }

    @Override
    public void execute(Player player, CommandParameters params) {
        player.getActionSender().sendChatMessage("- &eWins: "+player.getAttribute("wins")+" - "+"Games Played: "+player.getAttribute("games")+" ");
        player.getActionSender().sendChatMessage("- &eTags: "+player.getAttribute("tags")+" - "+"Captures: "+player.getAttribute("captures")+" ");
        player.getActionSender().sendChatMessage("- &eExplodes: "+player.getAttribute("explodes")+" - "+"Mines: "+player.getAttribute("mines")+" ");
        player.getActionSender().sendChatMessage("- &eRagequits: "+player.getAttribute("ragequits"));
    }
}
