/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.EMPCTF.cmd.impl;

import org.EMPCTF.Server;
import org.EMPCTF.cmd.Command;
import org.EMPCTF.cmd.CommandParameters;
import org.EMPCTF.model.Player;

/**
 *
 * @author Jacob Morgan
 */
public class RulesCommand implements Command{
    private static final RulesCommand INSTANCE = new RulesCommand();

    /**
     * Gets the singleton instance of this command.
     * @return The singleton instance of this command.
     */
    public static RulesCommand getCommand() {
            return INSTANCE;
    }

    @Override
    public void execute(Player player, CommandParameters params) {
        int i = 1;
        for(String l : Server.rulesText)
        {
            player.wrapText(i+". "+l);
            i++;
        }
    }
}
