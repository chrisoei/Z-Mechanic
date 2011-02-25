/**
 * 
 */
package com.eluctari.game.z.zmechanic;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

/**
 * See Z-Machine Standards Document 1.0, section x.x.x.
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 *
 */
public class TestBitOps {
	@Test public void testBitOps1() {
		byte[] data = new byte[] { (byte)0x80, 0x00 };
		for (int i = 0; i < 16; i++) {
			assertEquals(i == 15, BitOps.getBit(data, i));
		}
	}
	
	@Test public void testBitOps2() {
		byte[] data = new byte[] { (byte)0xff, (byte)0xfd };
		for (int i = 0; i < 16; i++) {
			assertEquals( i != 1, BitOps.getBit(data, i));
		}
	}
	
	@Test public void testBitOps3() {
		byte[] data = new byte[5];
		Random r = new Random();
		for (int i = 0; i < 100 ; i++) {
			int p = r.nextInt(5 * 8);
			BitOps.setBit(data, p, true);
			assertTrue(BitOps.getBit(data, p));
			BitOps.setBit(data, p, false);
			assertFalse(BitOps.getBit(data, p));
		}
	}
	
	@Test public void testBitOps4() {
		assertTrue(BitOps.getBit((byte)0x80, 7));
		assertFalse(BitOps.getBit((byte)0x80, 0));
		assertTrue(BitOps.getBit((byte)0x01, 0));
		assertFalse(BitOps.getBit((byte)0x01, 7));
	}
	
	@Test public void testBitOps5() {
		byte x = 0;
		for (int i = 0; i < 8; i++) {
			x = BitOps.setBit(x, i, true);
			assertTrue(BitOps.getBit(x, i));
			x = BitOps.setBit(x, i, false);
			assertFalse(BitOps.getBit(x, i));
		}
	}
	
	@Test public void testBitOps6() {
		try {
			BitOps.getBit((byte) 0x00, -1);
			fail();
		} catch (IllegalArgumentException e) {
			// Success
		}
	}
	
	@Test public void testBitOps7() {
		try {
			BitOps.setBit((byte) 0x00, -1, true);
			fail();
		} catch (IllegalArgumentException e) {
			// Success
		}
	}
	
	@Test public void testBitOps8() {
		try {
			BitOps.getBit((byte) 0x00, 8);
			fail();			
		} catch (IllegalArgumentException e) {
			// Success
			}
		}
	
	@Test public void testBitOps9() {
		try {
			BitOps.setBit((byte) 0x00, 8, true);
			fail();
		} catch (IllegalArgumentException e) {
			// Success
		}
	}
	
	@Test public void testBitOps10() {
		for (int i = 0; i< 16; i++)
			assertEquals(i == 15, BitOps.getBit((byte)0x80, (byte)0x00, i));
	}
	
	@Test public void testBitOps11() {
		for (int i = 0; i< 16; i++)
			assertEquals(i == 0, BitOps.getBit((byte)0x00, (byte)0x01, i));
	}
	
	@Test public void testBitOps12() {
		try {
			BitOps.getBit((byte) 0x00, (byte) 0x00, -1);
			fail();
		} catch (IllegalArgumentException e) {
			// Success
		}
	}
	
	@Test public void testBitOps13() {
		try {
			BitOps.getBit((byte) 0x00, (byte) 0x00, 16);
			fail();
		} catch (IllegalArgumentException e) {
			// Success
		}
	}
}
