
package org.EMPCTF.cmd.impl;

import org.EMPCTF.cmd.Command;
import org.EMPCTF.cmd.CommandParameters;
import org.EMPCTF.model.MapController;
import org.EMPCTF.model.Player;

/**
 *
 * @author qmarchi
 */
public class ReloadMapsCommand implements Command{

    private static final ReloadMapsCommand INSTANCE = new ReloadMapsCommand();

    /**
     * Gets the singleton instance of this command.
     * @return The singleton instance of this command.
     */
    public static ReloadMapsCommand getCommand() {
            return INSTANCE;
    }

    @Override
    public void execute(Player player, CommandParameters params) {
        if (player.isOp()){
            MapController.create();
        }
        else {
            player.wrapText("&cYou do not have permission to use that command.");
        }
    }

}
