/**
 * 
 */
package com.eluctari.game.z.zmechanic;

/**
 * See Z-Machine Standards Document 1.0, section x.x.x.
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 *
 */

import java.io.*;
public class Compiler {

	ZStoryFile storyFile;
	
	public void compile() throws IOException {
		ZMachine.INSTANCE.setVersion(5);
		storyFile = new ZStoryFile();
		storyFile.putByte(0, (byte)ZMachine.INSTANCE.getVersion());
		byte flags1 = 0; // See p61
		flags1 = BitOps.setBit(flags1, 0, false); // no colors available
		flags1 = BitOps.setBit(flags1, 1, false); // picture display not available
		flags1 = BitOps.setBit(flags1, 2, false); // boldface not available
		flags1 = BitOps.setBit(flags1, 3, false); // italics not available
		flags1 = BitOps.setBit(flags1, 4, true); // fixed-space font available
		flags1 = BitOps.setBit(flags1, 5, false); // sound effects not available
		flags1 = BitOps.setBit(flags1, 6, false); // timed keyboard not available
		storyFile.putByte(1, flags1);
		
		storyFile.setSerialCode("110224");
		storyFile.setReleaseNumber((short)7);
		storyFile.write(new File("/home/software/compiled.z5"));
	}
}
