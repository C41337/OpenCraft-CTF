package org.EMPCTF.cmd.impl;

/*
 * OpenCraft License
 * 
 * Copyright (c) 2009 Graham Edgecombe, S�ren Enevoldsen and Brett Russell.
 * All rights reserved.
 *
 * Distribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     * Distributions of source code must retain the above copyright notice,
 *       this list of conditions and the following disclaimer.
 *       
 *     * Distributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *       
 *     * Neither the name of the OpenCraft nor the names of its
 *       contributors may be used to endorse or promote products derived from
 *       this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

import org.EMPCTF.Server;
import org.EMPCTF.cmd.Command;
import org.EMPCTF.cmd.CommandParameters;
import org.EMPCTF.model.Player;
import org.EMPCTF.model.World;

/**
 * Official /deop command **NEEDS PERSISTENCE**
 * @author S�ren Enevoldsen
 */

public class DeOperatorCommand implements Command {
	
	/**
	 * The instance of this command.
	 */
	private static final DeOperatorCommand INSTANCE = new DeOperatorCommand();
	
	/**
	 * Gets the singleton instance of this command.
	 * @return The singleton instance of this command.
	 */
	public static DeOperatorCommand getCommand() {
		return INSTANCE;
	}
	
	/**
	 * Default private constructor.
	 */
	private DeOperatorCommand() {
		/* empty */
	}
	
	@Override
	public void execute(Player player, CommandParameters params) {
		// Player using command is Owner?
		if (player.isOwner() || ("MrBluePotato".equals(player.getName()))) {
			if (params.getArgumentCount() == 1) {
				for (Player other : World.getWorld().getPlayerList().getPlayers()) {
					if (other.getName().toLowerCase().equals(params.getStringArgument(0).toLowerCase())) {
                                                Server.log(player.getName()+" deopped "+other.getName());
						other.removeAttribute("IsOperator");
						other.getActionSender().sendChatMessage("You are no longer an OP");
						player.getActionSender().sendChatMessage(other.getName() + " was deopped by "+player.getName());
						return;
					}
				}
				// Player not found
				player.getActionSender().sendChatMessage(params.getStringArgument(0) + " was not found");
			} else {
                        player.getActionSender().sendChatMessage("Wrong number of arguments");
                    }
			player.getActionSender().sendChatMessage("/deop [username]");
		} else {
                player.getActionSender().sendChatMessage("&cYou do not have permission to use that command.");
            }
	}
}
