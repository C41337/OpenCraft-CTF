/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.EMPCTF.cmd.impl;

import org.EMPCTF.cmd.CommandParameters;
import org.EMPCTF.model.Player;

/**
 *
 * @author Jacob Morgan
 */
public class SpecCommand {

    private static final SpecCommand INSTANCE = new SpecCommand();

    /**
     * Gets the singleton instance of this command.
     * @return The singleton instance of this command.
     */
    public static SpecCommand getCommand() {
            return INSTANCE;
    }

    
    public void execute(Player player, CommandParameters params) {
        player.joinTeam("&5Spec");
    }

}
