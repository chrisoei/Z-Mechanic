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
	private int releaseNumber;
	private int serialCode;
	private byte[] bytes;
	private int maxByte;
	
	public ZStoryFile() {
		maxByte = 0;
		bytes = new byte[getMaximumPermittedSize()];
	}
	
	public void putByte(int pos, byte data) {
		maxByte = ((pos > maxByte) ? pos : maxByte);
		bytes[pos] = data;
	}
	
	public void putWord(int bytePosition, short data) {
		putByte(bytePosition, (byte)(data >>> 8));
		putByte(bytePosition + 1, (byte)(data & 0xff));
	}
	
	public void putString(int bytePosition, String x) {
		byte[] b;
		try {
			b = x.getBytes("ASCII");
			for (byte q : b) {
				putByte(bytePosition++, q);
			}
		} catch (UnsupportedEncodingException e) {
			throw new AssertionError("This should never happen.");
		}
	}
	
	public void putBit(int bytePosition, int bitPosition, boolean val) {
		int bitMask = 1 << (bitPosition % 8);
		maxByte = ((bytePosition > maxByte) ? bytePosition : maxByte);
		if (val) {
			bytes[bytePosition] |= bitMask;
		} else {
			bytes[bytePosition] &= ~bitMask;
		}
	}
	
	public void setReleaseNumber(short x) {
		putWord(0x02, x);
	}
	
	public void setSerialCode(String x) {
		if (x.length() != 6) {
			throw new IllegalArgumentException("Serial code has incorrect length " + x.length());
		}
		putString(0x12, x);
	}
	
	// See specs page 61.
	public void setBaseOfHighMemory(Address x) {
		putWord(0x04, x.getByteAddress());
	}
	
	// See specs page 61.
	public void setInitialValueOfProgramCounter(Address x) {
		putWord(0x06, x.getByteAddress());
	}
	
	public void setDictionaryLocation(Address x) {
		putWord(0x08, x.getByteAddress());
	}
	
	public void setObjectTableLocation(Address x) {
		putWord(0x0a, x.getByteAddress());
	}
	
	public void setGlobalVariablesTable(Address x) {
		putWord(0x0c, x.getByteAddress());
	}
	
	public void setBaseOfStaticMemory(Address x) {
		putWord(0x0e, x.getByteAddress());
	}
	
	public void setAbbreviationsTable(Address x) {
		putWord(0x18, x.getByteAddress());
	}
	
	public void setFileLength() {
		putWord(0x1a, (maxByte > 0x1b) ? (short)maxByte : 0x1b);
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
