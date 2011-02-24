/**
 * 
 */
package com.eluctari.game.z.zmechanic;

/**
 * An instruction represents a line of assembler code that contains an opcode
 * and operands. See Z-Machine Standards Document 1.0, section 4.1.
 * 
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 * 
 */
public class Instruction {
	public enum Form {
		LONGFORM, SHORTFORM, EXTENDEDFORM, VARIABLEFORM
	}

	Address start;
	/**
	 * See Z-Machine Standards Document 1.0, section 4.1.
	 */
	Opcode opcode;
	Operand[] operands;
	byte[] storeVariable;
	byte[] branchOffset;
	String textToPrint;

	/**
	 * See Z-Machine Standards Document 1.0, section 4.3.
	 * @return
	 */
	public Form getForm() {
		switch (opcode.getByte(0) >> 6) {
		case 3:
			return Form.VARIABLEFORM;
		case 2:
			return Form.SHORTFORM;
		default:
			if ((opcode.getByte(0) == 0xbe)
					&& (ZMachine.INSTANCE.getVersion() >= 5)) {
				return Form.EXTENDEDFORM;
			}
			return Form.LONGFORM;
		}
	}
}
