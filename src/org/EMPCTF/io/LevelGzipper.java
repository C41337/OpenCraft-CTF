package org.EMPCTF.io;

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

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPOutputStream;

import org.apache.mina.core.buffer.IoBuffer;
import org.EMPCTF.model.Level;
import org.EMPCTF.model.World;
import org.EMPCTF.net.MinecraftSession;

/**
 * A utility class for gzipping levels.
 * @author Graham Edgecombe
 */
public final class LevelGzipper {
	
	private static final LevelGzipper INSTANCE = new LevelGzipper();
	
	public static LevelGzipper getLevelGzipper() {
		return INSTANCE;
	}

	private ExecutorService service = Executors.newCachedThreadPool();

	private LevelGzipper() {
		/* empty */
	}
	

	public void gzipLevel(final MinecraftSession session) {
		final Level level = World.getWorld().getLevel();
		final int width = level.getWidth();
		final int height = level.getHeight();
		final int depth = level.getDepth();
		final byte[][][] blockData = World.getWorld().getLevel().getBlocks().clone();
		session.getActionSender().sendLevelInit();
		service.submit(new Runnable() {
			public void run() {
				try {
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					int size = width * height * depth;
					DataOutputStream os = new DataOutputStream(new GZIPOutputStream(out));
					os.writeInt(size);
                                        os.write(level.getBlocks1D());
					os.close();
					byte[] data = out.toByteArray();
					IoBuffer buf = IoBuffer.allocate(data.length);
					buf.put(data);
					buf.flip();
					while (buf.hasRemaining()) {
						int len = buf.remaining();
						if (len > 1024) {
							len = 1024;
						}
						byte[] chunk = new byte[len];
						buf.get(chunk);
						int percent = (int) ((double) buf.position() / (double) buf.limit() * 255D);
						session.getActionSender().sendLevelBlock(len, chunk, percent);
					}
					session.getActionSender().sendLevelFinish();
				} catch (IOException ex) {
					session.getActionSender().sendLoginFailure("Failed to gzip level. Please try again.");
				}
			}
		});
	}
	
}
