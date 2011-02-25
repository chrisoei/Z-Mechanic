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
		storyFile.setZMachineVersion();
		// See p61
		Address flag1 = new Address(1);
		storyFile.putBit(flag1, 0, false); // no colors available
		storyFile.putBit(flag1, 1, false); // picture display not available
		storyFile.putBit(flag1, 2, false); // boldface not available
		storyFile.putBit(flag1, 3, false); // italics not available
		storyFile.putBit(flag1, 4, true); // fixed-space font available
		storyFile.putBit(flag1, 5, false); // sound effects not available
		storyFile.putBit(flag1, 6, false); // timed keyboard not available
		
		storyFile.setSerialCode("110224");
		storyFile.setReleaseNumber((short)7);
		assert((short)7 == storyFile.getReleaseNumber());
		storyFile.setAbbreviationsTable(new Address((short)0x007b));
		storyFile.setBaseOfHighMemory(new Address((short)0x04d2));
		storyFile.setBaseOfStaticMemory(new Address((short)0x7e));
		storyFile.setDictionaryLocation(new Address((short)0x50));
		storyFile.setGlobalVariablesTable(new Address((short)0x65));
		storyFile.setObjectTableLocation(new Address((short)0x60));
		storyFile.setInitialValueOfProgramCounter(new Address((short)0x3039));
		storyFile.putByte(new Address(0xc0e5), (byte)1);
		
		storyFile.setFileLength();
		storyFile.write(new File("/home/software/compiled.z5"));
	}
}
