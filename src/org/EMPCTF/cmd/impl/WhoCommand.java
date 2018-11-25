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
 * @author qmarchi
 */
public class WhoCommand implements Command{
    private static final WhoCommand INSTANCE = new WhoCommand();
        public static WhoCommand getCommand() {
            return INSTANCE;
    }
    @Override
  public void execute(Player player, CommandParameters params) {
      player.wrapText("Press &8[TAB]&e For Player List!");
  }  
}
