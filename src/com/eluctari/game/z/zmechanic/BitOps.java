/**
 * 
 */
package com.eluctari.game.z.zmechanic;

/**
 * Bit operations using Z-Machine conventions.
 * See Z-Machine Standards Document 1.0.
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 *
 */
public class BitOps {
	/**
	 * Get the value of a bit from a byte array. The bit order conventions are described in
	 * Z-Machine Standards Document 1.0, page 5. The byte order conventions are described
	 * in section 4.2.1 on page 26.
	 * @param data	a byte array of data
	 * @param pos	The bit position, where 0 is the least significant bit of the least significant byte.
	 * 				Since the Z-Machine is big-endian, this is the byte with the biggest address. The
	 * 				"topmost" bit refers to bit 7 of the first (most significant) byte.
	 * @return		a boolean representation of the bit at pos
	 */
	public static boolean getBit(byte[] data, int pos) {
		return (data[data.length - 1 - pos / 8] >>> (pos % 8) & 0x0001) == 1;
	}

	/**
	 * Get the value of a bit from a two-byte word. The bit order conventions are described in
	 * Z-Machine Standards Document 1.0, page 5. The byte order conventions are described
	 * in section 4.2.1 on page 26.
	 * @param b0	a byte of data
	 * @param b1	a byte of data. address(b1) = address(b0) + 1.
	 * @param pos	The bit position, where 0 is the least significant bit of the least significant byte.
	 * 				Since the Z-Machine is big-endian, this is the byte with the biggest address. The
	 * 				"topmost" bit refers to bit 7 of the first (most significant) byte.
	 * @return		a boolean representation of the bit at pos
	 */
	public static boolean getBit(byte b0, byte b1, int pos) {
		if ((pos < 0) || (pos > 15)) {
			throw new IllegalArgumentException("Unable to get bit " + pos);
		}
		if (pos < 8) {
			return ((b1 >>> pos ) & 0x0001) == 1;
		} else {
			return ((b0 >>> (pos % 8) ) & 0x0001) == 1;
		}
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
	 * Set the value of a bit from a byte array. The bit order conventions are described in
	 * Z-Machine Standards Document 1.0, page 5. The byte order conventions are described
	 * in section 4.2.1 on page 26.
	 * @param data	a byte array of data
	 * @param pos	The bit position, where 0 is the least significant bit of the least significant byte.
	 * 				Since the Z-Machine is big-endian, this is the byte with the biggest address. The
	 * 				"topmost" bit refers to bit 7 of the first (most significant) byte.
	 * @param val	a boolean representation of the bit at pos
	 */
	public static void setBit(byte[] data, int pos, boolean val) {
		int bitMask = 1 << (pos % 8);
		if (val) {
			data[data.length - 1 - pos / 8] |= bitMask;
		} else {
			data[data.length - 1 - pos / 8] &= ~bitMask;
		}
	}
	
	/**
	 * Set the value of a bit of a single byte. The bit order conventions are described in
	 * Z-Machine Standards Document 1.0, page 5. 
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
}
