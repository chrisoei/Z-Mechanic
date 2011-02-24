/**
 * 
 */
package com.eluctari.game.z.zmechanic;

/**
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 *
 */
public class Address {

	private long bytePosition;
	boolean isSet;
	
	public Address() {
		bytePosition = 0;
		isSet = false;
	}
	
	public Address(long x) {
		setByteAddress(x);
	}
	
	public void setByteAddress(long x) {
		bytePosition = x;
		isSet = true;
	}
	
}
