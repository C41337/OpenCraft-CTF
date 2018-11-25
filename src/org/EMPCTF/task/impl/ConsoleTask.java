/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.EMPCTF.task.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.EMPCTF.Server;
import org.EMPCTF.cmd.Command;
import org.EMPCTF.cmd.CommandParameters;
import org.EMPCTF.model.Player;
import org.EMPCTF.model.World;

/**
 *
 * @author Jacob Morgan
 */
public class ConsoleTask implements Runnable {
    private static final BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
    private Player consolePlayer = new Player(null, "Console");
    public void run()
    {
        consolePlayer.setAttribute("IsOwner", "true");
        while(true)
        {
            try
            {
                while(r.ready())
                {
                    String message = r.readLine();
                    if (message.startsWith("/")) {
                            // interpret as command
                            String tokens = message.substring(1);
                            String[] parts = tokens.split(" ");
                            final Map<String, Command> commands = World.getWorld().getGameMode().getCommands();
                            Command c = commands.get(parts[0]);
                            if (c != null) {
                                    parts[0] = null;
                                    List<String> partsList = new ArrayList<String>();
                                    for (String s : parts) {
                                            if (s != null) {
                                                    partsList.add(s);
                                            }
                                    }
                                    parts = partsList.toArray(new String[0]);
                                    c.execute(consolePlayer, new CommandParameters(parts));
                            } else {
                                System.out.println("Invalid command.");
                            }
                    } else {
                            World.getWorld().broadcast("- &4Consolio:&f "+message);
                            Server.log("Console said: "+message);
                    }
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
        }
    }
}
