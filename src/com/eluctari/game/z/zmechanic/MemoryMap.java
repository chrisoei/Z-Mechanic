/**
 * 
 */
package com.eluctari.game.z.zmechanic;

/**
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 *
 */
public class MemoryMap {
	Address dynamicMemory = new Address(0);
	Address staticMemory; // written in word at $0e
	Address highMemory; // starts at word at $04
}
