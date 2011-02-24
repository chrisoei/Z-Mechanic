/**
 * 
 */
package com.eluctari.game.z.zmechanic;

/**
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 *
 */
public class Operand {

	/**
	 * See Z-Machine Standards Document 1.0, section 4.2.
	 * @author Christopher Oei http://www.linkedin.com/in/eluctari
	 *
	 */
	public enum OperandType {
		LARGE_CONSTANT(0, 0, 2, "Large constant"),
		SMALL_CONSTANT(0, 1, 1, "Small constant"),
		VARIABLE(1, 0, 1, "Variable"),
		OMITTED(1, 1, 0, "Omitted altogether");

		String description;
		int nBytes;
		
		private OperandType(int msb, int lsb, int bytes, String s) {
			nBytes = bytes;
			description = s;
		}
	}
	
	public Operand(int msb, int lsb) {
		
	}
}
