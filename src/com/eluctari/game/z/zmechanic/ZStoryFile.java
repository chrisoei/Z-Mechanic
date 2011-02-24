/**
 * 
 */
package com.eluctari.game.z.zmechanic;

/**
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 * 
 */
public class ZStoryFile {
	private static int KILOBYTE = 1024;
	private int releaseNumber;
	private int serialCode;

	
	public long getMaximumPermittedSize() {
		switch (ZMachine.INSTANCE.getVersion()) {
		case 1:
		case 2:
		case 3:
			return 128 * KILOBYTE;
		case 4:
		case 5:
			return 256 * KILOBYTE;
		case 6:
		case 8:
			return 512 * KILOBYTE;
		case 7:
			return 320 * KILOBYTE;
		default:
			throw new AssertionError("Unknown Z Machine version");
		}
	}
	
}
