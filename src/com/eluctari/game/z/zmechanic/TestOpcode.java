/**
 * 
 */
package com.eluctari.game.z.zmechanic;

import static org.junit.Assert.*;

import org.junit.*;

/**
 * See Z-Machine Standards Document 1.0, section x.x.x.
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 *
 */
public class TestOpcode {

	@Test public void testZero() {
		assertTrue(Opcode.OperandType.get("0OP") == Opcode.OperandType.OT_0OP);
	}
	
	@Test public void testOne() {
		assertTrue(Opcode.OperandType.get("1OP") == Opcode.OperandType.OT_1OP);
	}
	
	@Test public void testTwo() {
		assertTrue(Opcode.OperandType.get("2OP") == Opcode.OperandType.OT_2OP);
	}
	
	@Test public void testVar() {
		assertTrue(Opcode.OperandType.get("VAR") == Opcode.OperandType.OT_VAR);
	}
	
	@Test public void testExt() {
		assertTrue(Opcode.OperandType.get("EXT") == Opcode.OperandType.OT_EXT);
	}
	
	@Test public void testIllegalType() {
		try {
			Opcode.OperandType.get("FOO");
		} catch(IllegalArgumentException e) {
			return; //success!
		}
		fail(); // Should have thrown exception.
	}
	
	@Test public void testTypeToString() {
		for (String x : new String[] { "0OP", "1OP", "2OP", "VAR", "EXT"}) {
			assertEquals(Opcode.OperandType.get(x).toString(), x);
		}
	}
}
