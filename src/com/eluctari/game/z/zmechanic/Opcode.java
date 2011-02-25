/**
 * 
 */
package com.eluctari.game.z.zmechanic;

import java.util.*;

/**
 * See Z-Machine Standards Document 1.0, section x.x.x.
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 *
 */
public class Opcode {
	byte[] bytes;
	int nOperands;
	boolean store;
	boolean branch;
	boolean text;
	boolean indirect;
	boolean flag2; // See p77
	int flagnn; // See p77
	int instructionNumber;
	
	
	private Instruction.OperandCount operandCount;
	private String name;
	
	public Opcode(Instruction.OperandCount oc, String o, int c) {
		operandCount = oc;
		name = o;
		bytes = new byte[1];
		bytes[0] = (byte) c;
	}
	
	public Opcode(Instruction.OperandCount oc, String o, int c1, int c2) {
		operandCount = oc;
		name = o;
		bytes = new byte[2];
		bytes[0] = (byte) c1;
		bytes[1] = (byte) c2;
	}
	
	public byte getByte(int n) {
		return bytes[n];
	}
	
	public String toString() {
		String s = operandCount.toString() + ":" +
			instructionNumber +
			(branch ? "B" : "") +
			(store ? "S" : "") +
			(text ? "T" : "") +
			(indirect ? "I" : "") +
			(flag2 ? "F" + flagnn : "");
		return s;
	}
}
