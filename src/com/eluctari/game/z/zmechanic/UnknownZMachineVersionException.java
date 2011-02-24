/**
 * 
 */
package com.eluctari.game.z.zmechanic;

/**
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 *
 */
public class UnknownZMachineVersionException extends IllegalArgumentException {
	int version;
	
	public UnknownZMachineVersionException(int x) {
		version = x;
	}
}
