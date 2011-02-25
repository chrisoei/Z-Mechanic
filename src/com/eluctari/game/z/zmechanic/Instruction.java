/**
 * 
 */
package com.eluctari.game.z.zmechanic;

import java.util.HashMap;
import java.util.Map;

/**
 * An instruction represents a line of assembler code that contains an opcode
 * and operands. See Z-Machine Standards Document 1.0, section 4.1.
 * 
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 * 
 */
public class Instruction extends ParseElement {

	public enum Form {
		F_LONG(false, false, "long form"), 
		F_SHORT(true, false, "short form"), 
		F_EXTENDED(false, true, "extended form"), // FIXME
		F_VARIABLE(true, true, "variable form");

		boolean mostSignificantBit;

		/**
		 * @return the mostSignificantBit
		 */
		public boolean getMostSignificantBit() {
			return mostSignificantBit;
		}

		/**
		 * @return the leastSignificantBit
		 */
		public boolean getLeastSignificantBit() {
			return leastSignificantBit;
		}

		boolean leastSignificantBit;
		private String representation;

		private Form(boolean msb, boolean lsb, String s) {
			mostSignificantBit = msb;
			leastSignificantBit = lsb;
			representation = s;
		}
	}

	public enum OperandCount {
		OC_0OP("0OP"), // these opcodes take 0 operands
		OC_1OP("1OP"), // these opcodes take 1 operand
		OC_2OP("2OP"), // these opcodes take 2 operands
		OC_VAR("VAR"), // these opcodes take a variable number of operands
		OC_EXT("EXT"); // these are extended opcodes

		private String representation;
		private static Map<String, OperandCount> operandCountMap;

		static {
			operandCountMap = new HashMap<String, OperandCount>();
			for (OperandCount ot : OperandCount.values()) {
				operandCountMap.put(ot.representation, ot);
			}
		}

		public static OperandCount get(String s) {
			if (!operandCountMap.containsKey(s)) {
				throw new IllegalArgumentException("No such operand type: " + s);
			}
			return operandCountMap.get(s);
		}

		private OperandCount(String s) {
			representation = s;
		}

		public String toString() {
			return representation;
		}
	}

	public enum OperandType {
		LARGE_CONSTANT(false, false, 2, "Large constant"), // 2 bytes, big endian
		SMALL_CONSTANT(false, true, 1, "Small constant"), // 1 byte
		VARIABLE(true, false, 1, "Variable"), 	// $00 = top of stack
												// $01 to $0f = local variables
												// $10 to $ff = global variable
		OMITTED(true, true, 0, "Omitted altogether");

		String description;
		int nBytes;
		boolean mostSignificantBit;
		boolean leastSignificantBit;

		private OperandType(boolean msb, boolean lsb, int bytes, String s) {
			mostSignificantBit = msb;
			leastSignificantBit = lsb;
			nBytes = bytes;
			description = s;
		}

	}

	public enum Factory {
		INSTANCE;

		private Instruction instruction = new Instruction();
		
		public Instruction newInstance() {
			Instruction i = instruction;
			instruction = new Instruction();
			return i;
		}
		
		public void setOpcode(byte o) {
			instruction.opcode = o;
		}

		public void setForm(Instruction.Form f) {
			instruction.form = f;
		}

		public void setOperandCount(Instruction.OperandCount oc) {
			instruction.operandCount = oc;
			switch(oc) {
			case OC_0OP:
				instruction.operandTypes = null;
				instruction.operands = null;
				break;
			case OC_1OP:
				instruction.operandTypes = new Instruction.OperandType[1];
				instruction.operands = new Operand[1];
				break;
			case OC_2OP:
				instruction.operandTypes = new Instruction.OperandType[2];
				instruction.operands = new Operand[2];
			}
		}

		public void setOperandType(int n, Instruction.OperandType ot) {
			instruction.operandTypes[n] = ot;
		}
		
		public void setOperand(int n, Operand o) {
			instruction.operands[n] = o;
		}

		private void adjustOpcode() {
			instruction.opcode = BitOps.setBit(
					BitOps.setBit(instruction.opcode, 7,
							instruction.form.getMostSignificantBit()), 6,
					instruction.form.getLeastSignificantBit()); // See section
																// 4.3 of the
																// specification

			switch (instruction.form) {
			case F_SHORT:
				switch (instruction.getOperandCount()) {
				case OC_0OP: // See section 4.3.1 on page 27 of the
								// specification.
					instruction.opcode = BitOps
							.setBit(BitOps.setBit(instruction.opcode, 4, true),
									5, true);
					break;
				case OC_1OP:
					instruction.opcode = BitOps.setBit(
							BitOps.setBit(instruction.opcode, 4, false), 5,
							false); // extra degree of freedom
					break;
				}
				break;
			case F_VARIABLE:
				instruction.opcode = BitOps.setBit(
						BitOps.setBit(instruction.opcode, 7, true), 6, true); 
							// See section 4.3 of the specification
				switch (instruction.getOperandCount()) {
				case OC_VAR:
					instruction.opcode = BitOps.setBit(instruction.opcode, 5,
							true);
					break;
				case OC_2OP:
					instruction.opcode = BitOps.setBit(instruction.opcode, 5,
							false);
					break;
				}
				break;
			case F_EXTENDED:
				throw new UnsupportedOperationException();
			case F_LONG:
				instruction.opcode = BitOps.setBit(
						BitOps.setBit(instruction.opcode, 7, false), 6, false); // extra degree of freedom
				setOperandCount(Instruction.OperandCount.OC_2OP);
				break;
			}
		}
	}

	Address start;
	Form form;
	OperandCount operandCount;
	OperandType[] operandTypes;
	/**
	 * See Z-Machine Standards Document 1.0, section 4.1.
	 */
	byte opcode;
	Operand[] operands;
	byte[] storeVariable;
	byte[] branchOffset;
	String textToPrint;

	private Instruction() {
	}

	public OperandCount getOperandCount() {
		return operandCount;
	}

	public String toString() {
		return String.format("INSTRUCTION(%x at ", opcode) + address + ")";
	}
}
