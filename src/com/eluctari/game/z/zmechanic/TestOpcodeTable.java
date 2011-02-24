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
public class TestOpcodeTable {

	@Test public void test1() {
		OpcodeTable.INSTANCE.initialize();
	}
	
	@Test public void test2() {
		OpcodeTable.INSTANCE.get(Opcode.OperandType.OT_2OP, "add");
	}
	
	/**
	 * See Z-Machine Standards Document 1.0, page 79.
	 */	
	@Test public void test3() {
		Opcode o = OpcodeTable.INSTANCE.get(Opcode.OperandType.OT_2OP, "add");
		assertEquals(o.getByte(0), (byte)20);
	}
	
	@Test public void test4() {
		Opcode o = OpcodeTable.INSTANCE.get(Opcode.OperandType.OT_VAR, "buffer_mode");
		assertEquals(o.getByte(0), (byte)242);
	}
	
	@Test public void test5() {
		Opcode o = OpcodeTable.INSTANCE.get(Opcode.OperandType.OT_EXT, "art_shift");
		assertEquals(o.getByte(0), (byte)190);
		assertEquals(o.getByte(1), (byte)3);
	}
}
