package org.EMPCTF.persistence;

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

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import org.EMPCTF.model.Player;

/**
 * A persistence request which loads the specified player.
 * @author Graham Edgecombe
 *
 */
public class LoadPersistenceRequest extends PersistenceRequest {
	
	/**
	 * Creates the load request.
	 * @param player The player to load.
	 */
	public LoadPersistenceRequest(Player player) {
		super(player);
	}

	@SuppressWarnings("unchecked")
	public void perform(boolean createFile) throws IOException {
		final SavedGameManager mgr = SavedGameManager.getSavedGameManager();
		final Player player = getPlayer();
		final XStream xs = mgr.getXStream();
		final File file = new File(mgr.getPath(player));
		if(file.exists()) {
			try {
				Map<String, Object> attributes = (Map<String, Object>) xs.fromXML(new FileInputStream(file));
				for(Map.Entry<String, Object> entry : attributes.entrySet()) {
					player.setAttribute(entry.getKey(), entry.getValue());
				}
			} catch (RuntimeException ex) {
				throw new IOException(ex);
			}
                        if(player.getAttribute("storepoints") == null)
                            player.setAttribute("storepoints", 0);
                        if(player.getAttribute("ragequits") == null)
                            player.setAttribute("ragequits", 0);
		}
                else if(createFile)
                {
                    if(player.getAttribute("tags") == null)
                    {
                        player.setAttribute("tags", 0);
                        player.setAttribute("captures", 0);
                        player.setAttribute("explodes", 0);
                        player.setAttribute("mines", 0);
                        player.setAttribute("wins", 0);
                        player.setAttribute("games", 0);
                    }
                    if(player.getAttribute("storepoints") == null)
                        player.setAttribute("storepoints", 0);
                    if(player.getAttribute("ragequits") == null)
                        player.setAttribute("ragequits", 0);
                }
                else
                    throw new IOException();

	}
        public void perform() throws IOException
        {
            perform(true);
        }
	
}
