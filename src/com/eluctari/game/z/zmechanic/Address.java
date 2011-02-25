/**
 * 
 */
package com.eluctari.game.z.zmechanic;

import java.io.StringWriter;

/**
 * This is an immutable (and therefore threadsafe) class.
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 *
 */
public final class Address {

	private final int bytePosition; // Remember, Java integers are 32-bits whereas Z-machines are restricted to 16.
	
	public Address(short x) {
		bytePosition = 0xFFFF & (int)x; // get rid of sign
	}
	
	public Address(int x) {
		assert((x >= 0) && (x < 65536));
		bytePosition = x;
	}
	
	public Address nextByte() {
		return new Address(bytePosition + 1);
	}
	
	public Address nextWord() {
		return new Address(bytePosition + 2);
	}
	
	/**
	 * 
	 * @return a 32-bit signed integer
	 */
	public int getByteAddress() {
		return bytePosition;
	}
	
	public int getWordAddress() {
		return (bytePosition / 2);
	}
	
	public int getPackedRoutineAddress() {
		switch(ZMachine.INSTANCE.getVersion()) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 8:
			return (bytePosition / 8);
		case 6:
		case 7:
			throw new UnsupportedOperationException(); // FIXME for version 6 and 7
		default:
			throw new UnknownZMachineVersionException(ZMachine.INSTANCE.getVersion());
		}
	}
	
	public int getPackedStringAddress() {
		switch(ZMachine.INSTANCE.getVersion()) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 8:
			return (bytePosition / 8);
		case 6:
		case 7:
			throw new UnsupportedOperationException(); // FIXME for version 6 and 7
		default:
			throw new UnknownZMachineVersionException(ZMachine.INSTANCE.getVersion());
		}
	}
	
	public String toString() {
		return String.format("%04x", getByteAddress());
	}
}
