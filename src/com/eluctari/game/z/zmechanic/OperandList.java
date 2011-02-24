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
public class OperandList {
	public enum OperandType {
		LARGE, // 2 bytes, big endian
		SMALL, // 1 byte
		VARIABLE, 	// $00 = top of stack
					// $01 to $0f = local variables
					// $10 to $ff = global variables
		OMITTED
	}
	List<OperandType> list;
	
}
