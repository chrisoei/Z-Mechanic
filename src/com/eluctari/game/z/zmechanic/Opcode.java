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
	
	public enum OperandType {
		OT_0OP("0OP"), // these opcodes take 0 operands
		OT_1OP("1OP"), // these opcodes take 1 operand
		OT_2OP("2OP"), // these opcodes take 2 operands
		OT_VAR("VAR"), // these opcodes take a variable number of operands
		OT_EXT("EXT"); // these are extended opcodes

		private String representation;
		private static Map<String, OperandType> operandTypeMap;
		
		static {
			operandTypeMap = new HashMap<String, OperandType>();
			for (OperandType ot : OperandType.values()) {
				operandTypeMap.put(ot.representation, ot);
			}
		}

		public static OperandType get(String s) {
			if (!operandTypeMap.containsKey(s)) {
				throw new IllegalArgumentException("No such operand type: " + s);
			}
			return operandTypeMap.get(s);
		}
		
		private OperandType(String s) {
			representation = s;
		}
		
		public String toString() {
			return representation;
		}
	}
	
	private OperandType operandType;
	private String name;
	
	public Opcode(OperandType ot, String o, int c) {
		operandType = ot;
		name = o;
		bytes = new byte[1];
		bytes[0] = (byte) c;
	}
	
	public Opcode(OperandType ot, String o, int c1, int c2) {
		operandType = ot;
		name = o;
		bytes = new byte[2];
		bytes[0] = (byte) c1;
		bytes[1] = (byte) c2;
	}
	
	public byte getByte(int n) {
		return bytes[n];
	}
	
	public String toString() {
		String s = operandType.toString() + ":" +
			instructionNumber +
			(branch ? "B" : "") +
			(store ? "S" : "") +
			(text ? "T" : "") +
			(indirect ? "I" : "") +
			(flag2 ? "F" + flagnn : "");
		return s;
	}
}
