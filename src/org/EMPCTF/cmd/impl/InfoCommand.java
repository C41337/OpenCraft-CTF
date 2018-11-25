/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.EMPCTF.cmd.impl;

import org.EMPCTF.cmd.Command;
import org.EMPCTF.cmd.CommandParameters;
import org.EMPCTF.model.Player;

//Info command ©2013 MrBluePotato (Michael Cummings) <MrBluePotato@wondercraft.org>
public class InfoCommand implements Command{
    private static final InfoCommand INSTANCE = new InfoCommand();

    public static InfoCommand getCommand() {
            return INSTANCE;
    }
    
    @Override
    public void execute(Player player, CommandParameters params){
        player.wrapText("Server Info:");
        player.wrapText("Running &cEMPCTF &eVersion:&a1");
        player.wrapText("");
        
        
    }
    
}
