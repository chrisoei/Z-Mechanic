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
public class TestZMachine {
	@Test public void testBitOps1() {
		byte[] data = new byte[] { (byte)0x80, 0x00 };
		for (int i = 0; i < 16; i++) {
			assertEquals(i == 7, ZMachine.getBit(data, i));
		}
	}
	
	@Test public void testBitOps2() {
		byte[] data = new byte[] { (byte)0xff, (byte)0xfd };
		for (int i = 0; i < 16; i++) {
			assertEquals( i != 9, ZMachine.getBit(data, i));
		}
	}
	
	@Test public void testBitOps3() {
		byte[] data = new byte[5];
		Random r = new Random();
		for (int i = 0; i < 100 ; i++) {
			int p = r.nextInt(5 * 8);
			ZMachine.setBit(data, p, true);
			assertTrue(ZMachine.getBit(data, p));
			ZMachine.setBit(data, p, false);
			assertFalse(ZMachine.getBit(data, p));
		}
	}
	
	@Test public void testBitOps4() {
		assertTrue(ZMachine.getBit((byte)0x80, 7));
		assertFalse(ZMachine.getBit((byte)0x80, 0));
		assertTrue(ZMachine.getBit((byte)0x01, 0));
		assertFalse(ZMachine.getBit((byte)0x01, 7));
	}
	
	@Test public void testBitOps5() {
		byte x = 0;
		for (int i = 0; i < 8; i++) {
			x = ZMachine.setBit(x, i, true);
			assertTrue(ZMachine.getBit(x, i));
			x = ZMachine.setBit(x, i, false);
			assertFalse(ZMachine.getBit(x, i));
		}
	}
	
	@Test public void testBitOps6() {
		try {
			ZMachine.getBit((byte) 0x00, -1);
			fail();
		} catch (IllegalArgumentException e) {
			// Success
		}
	}
	
	@Test public void testBitOps7() {
		try {
			ZMachine.setBit((byte) 0x00, -1, true);
			fail();
		} catch (IllegalArgumentException e) {
			// Success
		}
	}
	
	@Test public void testBitOps8() {
		try {
			ZMachine.getBit((byte) 0x00, 8);
			fail();			
		} catch (IllegalArgumentException e) {
			// Success
			}
		}
	
	@Test public void testBitOps9() {
		try {
			ZMachine.setBit((byte) 0x00, 8, true);
			fail();
		} catch (IllegalArgumentException e) {
			// Success
		}
	}
}
