/**
 * 
 */
package com.eluctari.game.z.zmechanic;

/**
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 *
 */
public class Address {

	private int bytePosition; // Remember, Java integers are 32-bits whereas Z-machines are restricted to 16.
	boolean isSet;
	
	public Address() {
		bytePosition = -1;
		isSet = false;
	}
	
	public Address(short x) {
		setByteAddress(x);
	}
	
	public void setByteAddress(short x) {
		bytePosition = x;
		isSet = true;
	}
	
	public short getByteAddress() {
		assert(isSet);
		return (short)bytePosition;
	}
	
	public short getWordAddress() {
		assert(isSet);
		return (short)(bytePosition / 2);
	}
	
	public short getPackedRoutineAddress() {
		assert(isSet);
		switch(ZMachine.INSTANCE.getVersion()) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 8:
			return (short)(bytePosition / 8);
		case 6:
		case 7:
			throw new UnsupportedOperationException(); // FIXME for version 6 and 7
		default:
			throw new UnknownZMachineVersionException(ZMachine.INSTANCE.getVersion());
		}
	}
	
	public short getPackedStringAddress() {
		assert(isSet);
		switch(ZMachine.INSTANCE.getVersion()) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 8:
			return (short)(bytePosition / 8);
		case 6:
		case 7:
			throw new UnsupportedOperationException(); // FIXME for version 6 and 7
		default:
			throw new UnknownZMachineVersionException(ZMachine.INSTANCE.getVersion());
		}
	}
}
