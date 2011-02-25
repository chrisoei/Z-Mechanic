/**
 * 
 */
package com.eluctari.game.z.zmechanic;

/**
 * See Z-Machine Standards Document 1.0, section x.x.x.
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 *
 */
public class ParseElement {
	int byteSize;
	Address address;
	
	public ParseElement() {
		
	}
	
	public ParseElement(int n) {
		byteSize = n;
	}
	
	public int getByteSize() {
		return byteSize;
	}
	
	public void setByteSize(int n) {
		byteSize = n;
	}
	
	public void setAddress(Address a) {
		address = a;
	}
	
	public Address getAddress() {
		return address;
	}
}
