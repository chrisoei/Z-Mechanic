/**
 * 
 */
package com.eluctari.game.z.zmechanic;

import java.io.*;

/**
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 * 
 */
public class ZStoryFile {
	private static int KILOBYTE = 1024;
	
	private static Address MACHINE_VERSION = new Address(0x00);
	private static Address MAIN_ADDRESS = new Address(0x06);
	private static Address DICTIONARY_ADDRESS = new Address(0x08);
	private static Address OBJECT_TABLE_ADDRESS = new Address(0x0a);
	private static Address GLOBAL_VARIABLES_TABLE_ADDRESS = new Address(0x0c);
	private static Address BASE_OF_STATIC_MEMORY = new Address(0x0e);
	private static Address ABBREVIATIONS_TABLE_ADDRESS = new Address(0x18);
	private static Address FILE_LENGTH = new Address(0x1a);
	private static Address CHECKSUM = new Address(0x1c);

	
	private int releaseNumber;
	private int serialCode;
	private byte[] bytes;
	private int maxByte;
	
	public ZStoryFile() {
		maxByte = 0;
		bytes = new byte[getMaximumPermittedSize()];
	}
	
	public byte getByte(Address x) {
		return bytes[x.getByteAddress()];
	}
	
	public void putByte(Address x, byte data) {
		int pos = x.getByteAddress();
		maxByte = ((pos > maxByte) ? pos : maxByte);
		bytes[pos] = data;
	}
	
	public short getWord(Address x) {
		int y = x.getByteAddress();
		return (short)(((int)bytes[y] << 8) + (int)bytes[y + 1]);
	}
	
	public void putWord(Address x, short data) {
		putByte(x, (byte)(data >>> 8));
		putByte(x.nextByte(), (byte)(data & 0xff));
	}
	
	public void putByteAddress(Address x, Address data) {
		int y = data.getByteAddress();
		putByte(x, (byte) (y >>> 8));
		putByte(x.nextByte(), (byte)(y & 0xff));
	}
	
	public void putString(Address a, String x) {
		byte[] b;
		try {
			b = x.getBytes("ASCII");
			for (byte q : b) {
				putByte(a, q);
				a = a.nextByte();
			}
		} catch (UnsupportedEncodingException e) {
			throw new AssertionError("This should never happen.");
		}
	}
	
	public void putBit(Address a, int bitPosition, boolean val) {
		int bytePosition = a.getByteAddress();
		int bitMask = 1 << (bitPosition % 8);
		maxByte = ((bytePosition > maxByte) ? bytePosition : maxByte);
		if (val) {
			bytes[bytePosition] |= bitMask;
		} else {
			bytes[bytePosition] &= ~bitMask;
		}
	}
	
	public void setZMachineVersion() {
		putByte(MACHINE_VERSION, (byte)ZMachine.INSTANCE.getVersion());
	}
	
	public short getReleaseNumber() {
		return getWord(new Address(0x02));
	}
	
	public void setReleaseNumber(short x) {
		putWord(new Address(0x02), x);
	}
	
	public void setSerialCode(String x) {
		if (x.length() != 6) {
			throw new IllegalArgumentException("Serial code has incorrect length " + x.length());
		}
		putString(new Address(0x12), x);
	}
	
	/**
	 * Also known as the size of resident memory.
	 * See Z-Machine Standards Document 1.0, page 61.
	 * @param x
	 */
	public void setBaseOfHighMemory(Address x) {
		putByteAddress(new Address(0x04), x);
	}
	
	// See specs page 61.
	public void setInitialValueOfProgramCounter(Address x) {
		putByteAddress(MAIN_ADDRESS, x);
	}
	
	public void setDictionaryLocation(Address x) {
		putByteAddress(DICTIONARY_ADDRESS, x);
	}
	
	public void setObjectTableLocation(Address x) {
		putByteAddress(OBJECT_TABLE_ADDRESS, x);
	}
	
	public void setGlobalVariablesTable(Address x) {
		putByteAddress(GLOBAL_VARIABLES_TABLE_ADDRESS, x);
	}
	
	/**
	 * Also known as: the size of dynamic memory.
	 * See Z-Machine Standards Document 1.0, section 1.1, page 12.
	 * @param x
	 */
	public void setBaseOfStaticMemory(Address x) {
		assert(x.getByteAddress() >= 64);
		putByteAddress(BASE_OF_STATIC_MEMORY, x);
	}
	
	public void setAbbreviationsTable(Address x) {
		putByteAddress(ABBREVIATIONS_TABLE_ADDRESS, x);
	}
	
	public void setFileLength() {
		int a = FILE_LENGTH.nextByte().getByteAddress();
		putByteAddress(FILE_LENGTH, (maxByte > a) ? new Address(maxByte) : FILE_LENGTH.nextByte());
	}
	
	public void write(File f) throws IOException {
		OutputStream o = new BufferedOutputStream(new FileOutputStream(f));
		o.write(bytes, 0, maxByte + 1);
		o.close();
	}
	
	public int getMaximumPermittedSize() {
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
