/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.EMPCTF.cmd.impl;

import org.EMPCTF.cmd.Command;
import org.EMPCTF.cmd.CommandParameters;
import org.EMPCTF.game.impl.CTFGameMode;
import org.EMPCTF.model.Player;
import org.EMPCTF.model.Position;
import org.EMPCTF.model.World;

/**
 * 
 * @author Jacob Morgan
 * @author Quinton Marchi 
 */
public class CreeperCommand implements Command{
    private static final CreeperCommand INSTANCE = new CreeperCommand();

    /**
     * Gets the singleton instance of this command.
     * @return The singleton instance of this command.
     */
    public static CreeperCommand getCommand() {
            return INSTANCE;
    }
/* Let's get the player */
    @Override
    public void execute(Player player, CommandParameters params) {
/* And now the Position */
        
        Position pos = player.getPosition();
        int px = (pos.getX()-16) / 32;
        int py = (pos.getY()-16) / 32;
        int pz = ((pos.getZ()-16) / 32);
/* Make the Creeper sound */
        World.getWorld().broadcast("- &eSSsSSssSSsSsS");
/*If the player is Operator or VIP ignore the text */
        if ((player.isOp()) || player.isVIP());
        /* If it is a standard user then say who it is. */
        else {World.getWorld().broadcast(player.getNameChar()+player.getName()+" Exploded!");}    
/* random stuff */
        ((CTFGameMode)World.getWorld().getGameMode()).explodeTNT(player, World.getWorld().getLevel(), px, py, pz, 4, true, true, false);

    }
}
