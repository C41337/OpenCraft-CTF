/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.EMPCTF.cmd.impl;

import org.EMPCTF.Server;
import org.EMPCTF.cmd.Command;
import org.EMPCTF.cmd.CommandParameters;
import org.EMPCTF.game.impl.CTFGameMode;
import org.EMPCTF.model.Player;
import org.EMPCTF.model.Position;
import org.EMPCTF.model.Rotation;
import org.EMPCTF.model.World;

/**
 *
 * @author Jacob Morgan
 */
public class GrenadeCommand implements Command{
    private static final GrenadeCommand INSTANCE = new GrenadeCommand();
    Thread rocketThread;
    /**
     * Gets the singleton instance of this command.
     * @return The singleton instance of this command.
     */
    public static GrenadeCommand getCommand() {
            return INSTANCE;
    }

    void stop()
    {
        rocketThread.stop();
    }

    @Override
    public void execute(final Player player, CommandParameters params) {
        rocketThread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                Position pos = player.getPosition();
                Rotation r = player.getRotation();

                int heading = (int) (Server.getUnsigned(r.getRotation()) * ((float)360/256)) - 90;
                int pitch = 360 - (int) (Server.getUnsigned(r.getLook()) * ((float)360/256));

                double px = (pos.getX()-16) / 32;
                double py = (pos.getY()-16) / 32;
                double pz = ((pos.getZ()-16) / 32);

                double vx = Math.cos(Math.toRadians(heading)) * 2;
                double vz = Math.tan(Math.toRadians(pitch));
                double vy = Math.sin(Math.toRadians(heading)) * 2;
                double x = px;
                double y = py;
                double z = pz;
                double lastX = px;
                double lastY = py;
                double lastZ = pz;
                for(int i = 0; i < 256; i++)
                {
                    x += vx;
                    y += vy;
                    z += vz;
                    int bx = (int) Math.round(x);
                    int by = (int) Math.round(y);
                    int bz = (int) Math.round(z);
                    int block = World.getWorld().getLevel().getBlock(bx, by, bz);
                    if(block != 0 && block != 46)
                    {
                        World.getWorld().getLevel().setBlock((int) Math.round(lastX), (int) Math.round(lastY), (int) Math.round(lastZ), 0);
                        ((CTFGameMode)World.getWorld().getGameMode()).explodeTNT(player, World.getWorld().getLevel(), bx, by, bz, 2, true, false, false);
                        break;
                    }
                    else
                    {
                        World.getWorld().getLevel().setBlock((int) Math.round(lastX), (int) Math.round(lastY), (int) Math.round(lastZ), 0);
                        World.getWorld().getLevel().setBlock(bx, by, bz, 46);
                    }
                    lastX = x;
                    lastY = y;
                    lastZ = z;
                    i++;
                    if(vz > (double) -2) {
                        vz -= 0.15;
                    }
                    vx *= 0.9;
                    vy *= 0.9;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        });
        rocketThread.start();
    }
}
