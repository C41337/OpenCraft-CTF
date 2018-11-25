/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.EMPCTF.cmd.impl;

import org.EMPCTF.Server;
import org.EMPCTF.cmd.Command;
import org.EMPCTF.cmd.CommandParameters;
import org.EMPCTF.game.impl.CTFGameMode;
import org.EMPCTF.model.Level;
import org.EMPCTF.model.MapController;
import org.EMPCTF.model.Player;
import org.EMPCTF.model.World;

/**
 *
 * @author Jacob Morgan
 */
public class NewGameCommand implements Command{
    private static final NewGameCommand INSTANCE = new NewGameCommand();

    /**
     * Gets the singleton instance of this command.
     * @return The singleton instance of this command.
     */
    public static NewGameCommand getCommand() {
            return INSTANCE;
    }
    @Override
    public void execute(Player player, CommandParameters params) {
        if ((player.isOp()) || player.isVIP())
        {
            Server.log(player.getName()+" used /newgame");
            String mapName;
            try {
            mapName = params.getStringArgument(0);
            }
            catch(Exception ex)
            {
                mapName = null;
            }
            Level newMap;
            if(mapName == null) {
                newMap = MapController.randomLevel();
            }
            else {
                newMap = MapController.getLevel(mapName);
            }
            ((CTFGameMode)World.getWorld().getGameMode()).startGame(newMap);
        }
        else {
            player.getActionSender().sendChatMessage("&cYou do not have permission to use that command.");
        }
    }
}
