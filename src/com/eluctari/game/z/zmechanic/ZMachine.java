/**
 * 
 */
package com.eluctari.game.z.zmechanic;

/**
 * See Z-Machine Standards Document 1.0.
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 *
 */
public enum ZMachine {
	INSTANCE;
	
	private int version;
	private long routineOffset = 0;
	private long stringOffset = 0;
	private boolean isSet;

	/**
	 * Get the value of a bit from a byte array. The bit order conventions are described in
	 * Z-Machine Standards Document 1.0, page 5.
	 * @param data	a byte array of data
	 * @param pos	the bit position, where 0 is the least significant bit of the byte with the lowest address
	 * @return		a boolean representation of the bit at pos
	 */
	public static boolean getBit(byte[] data, int pos) {
		return (data[pos / 8] >>> (pos % 8) & 0x0001) == 1;
	}
	
	/**
	 * Get the value of a bit from a single byte. The bit order conventions are described in
	 * Z-Machine Standards Document 1.0, page 5. 
	 * @param data	a byte array of data
	 * @param pos	The bit position, where 0 is the least significant bit of the byte.
	 * 				The "topmost" bit refers to bit 7.
	 * @return		a boolean representation of the bit at pos
	 */
	public static boolean getBit(byte data, int pos) {
		if ((pos < 0) || (pos > 7)) {
			throw new IllegalArgumentException("Unable to get bit " + pos);
		}
		return ((data >>> pos ) & 0x0001) == 1;
	}
	
	/**
	 * Set the value of a bit from a byte array. The conventions match 
	 * @param data	a byte array of data
	 * @param pos	the bit position, where 0 is the least significant bit of the byte with the lowest address
	 * @param val	a boolean representation of the bit at pos
	 */
	public static void setBit(byte[] data, int pos, boolean val) {
		int bitMask = 1 << (pos % 8);
		if (val) {
			data[pos / 8] |= bitMask;
		} else {
			data[pos / 8] &= ~bitMask;
		}
	}
	
	/**
	 * Set the value of a bit of a single byte. The conventions match 
	 * @param data	a byte array of data
	 * @param pos	The bit position, where 0 is the least significant bit of the byte.
	 * 				The "topmost" bit refers to bit 7.
	 * @param val	a boolean representation of the bit at pos
	 * @return		the new value of the byte
	 */
	public static byte setBit(byte data, int pos, boolean val) {
		if ((pos < 0) || (pos > 7)) {
			throw new IllegalArgumentException("Unable to set bit " + pos);
		}
		int bitMask = 1 << (pos % 8);
		return val ? (data |= bitMask) : (data &= ~bitMask);
	}
	
	private ZMachine() {
		isSet = false;
		routineOffset = 0;
		stringOffset = 0;
		version = 8; // default 
	}

	/**
	 * The Z-Machine version number defaults to 8. Once this
	 * property is either read or set, it can never change.
	 */
	public int getVersion() {
		isSet = true;
		return version;
	}
	
	/**
	 * The Z-Machine version number defaults to 8. Once this
	 * property is either read or set, it can never change.
	 * @param v set the version to this integer
	 */
	public void setVersion(int v) {
		if (isSet && (version != v)) {
			throw new IllegalStateException("Cannot change version " + version + " Z-machine to version " + v);
		}
		version = v;
	}
	
	public long getRoutineOffset() {
		return routineOffset;
	}
	
	public void setRoutineOffset(long x) {
		routineOffset = x;
	}
	
	public long getStringOffset() {
		return stringOffset;
	}
	
	public void setStringOffset(long x) {
		stringOffset = x;
	}
}
