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
		// See p61
		storyFile.putBit(1, 0, false); // no colors available
		storyFile.putBit(1, 1, false); // picture display not available
		storyFile.putBit(1, 2, false); // boldface not available
		storyFile.putBit(1, 3, false); // italics not available
		storyFile.putBit(1, 4, true); // fixed-space font available
		storyFile.putBit(1, 5, false); // sound effects not available
		storyFile.putBit(1, 6, false); // timed keyboard not available
		
		storyFile.setSerialCode("110224");
		storyFile.setReleaseNumber((short)7);
		storyFile.setAbbreviationsTable(new Address((short)123));
		storyFile.setBaseOfHighMemory(new Address((short)1234));
		storyFile.setBaseOfStaticMemory(new Address((short)126));
		storyFile.setInitialValueOfProgramCounter(new Address((short)12345));
		storyFile.putByte(12345, (byte)1);
		storyFile.setFileLength();
		storyFile.write(new File("/home/software/compiled.z5"));
	}
}
