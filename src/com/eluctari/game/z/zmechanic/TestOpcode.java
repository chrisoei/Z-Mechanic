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
		assertTrue(Instruction.OperandCount.get("0OP") == Instruction.OperandCount.OC_0OP);
	}
	
	@Test public void testOne() {
		assertTrue(Instruction.OperandCount.get("1OP") == Instruction.OperandCount.OC_1OP);
	}
	
	@Test public void testTwo() {
		assertTrue(Instruction.OperandCount.get("2OP") == Instruction.OperandCount.OC_2OP);
	}
	
	@Test public void testVar() {
		assertTrue(Instruction.OperandCount.get("VAR") == Instruction.OperandCount.OC_VAR);
	}
	
	@Test public void testExt() {
		assertTrue(Instruction.OperandCount.get("EXT") == Instruction.OperandCount.OC_EXT);
	}
	
	@Test public void testIllegalType() {
		try {
			Instruction.OperandCount.get("FOO");
		} catch(IllegalArgumentException e) {
			return; //success!
		}
		fail(); // Should have thrown exception.
	}
	
	@Test public void testTypeToString() {
		for (String x : new String[] { "0OP", "1OP", "2OP", "VAR", "EXT"}) {
			assertEquals(Instruction.OperandCount.get(x).toString(), x);
		}
	}
}
