/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.EMPCTF.cmd.impl;

import org.EMPCTF.cmd.Command;
import org.EMPCTF.cmd.CommandParameters;
import org.EMPCTF.game.impl.CTFGameMode;
import org.EMPCTF.model.MapController;
import org.EMPCTF.model.Player;
import org.EMPCTF.model.World;

/**
 *
 * @author Jacob Morgan
 */
public class VoteCommand implements Command{
    private static final VoteCommand INSTANCE = new VoteCommand();

    /**
     * Gets the singleton instance of this command.
     * @return The singleton instance of this command.
     */
    public static VoteCommand getCommand() {
            return INSTANCE;
    }

    @Override
    public void execute(Player player, CommandParameters params) {
        if(params.getArgumentCount() == 1)
        {
            if(player.hasVoted) {
                player.getActionSender().sendChatMessage("- &eYou have already voted!");
            }
            else if(((CTFGameMode)World.getWorld().getGameMode()).voting == false) {
                player.getActionSender().sendChatMessage("- &eVoting is not currently open!");
            }
            else
            {
                boolean r = MapController.addVote(params.getStringArgument(0));
                if(r == false) {
                    player.getActionSender().sendChatMessage("- &eUnknown map!");
                }
                else {
                    player.hasVoted = true;
                }
            }
        }
    }
}
