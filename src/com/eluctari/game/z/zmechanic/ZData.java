/**
 * 
 */
package com.eluctari.game.z.zmechanic;

/**
 * See Z-Machine Standards Document 1.0, section x.x.x.
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 *
 */
public class ZData extends ParseElement {
	byte[] data;
	
	public ZData(ZToken t) {
		String s = t.getStringValue();
		if (s.equals(".byte")) {
			setByteSize(1);
		} else if (s.equals(".word")) {
			setByteSize(2);
		} else {
			throw new IllegalArgumentException(s);
		}
		data = new byte[getByteSize()];
	}
	
	public byte getData(int p) {
		return data[p];
	}
	
	public void setData(int p, byte d) {
		data[p] = d;
	}
	
	public String toString() {
		return "DATA(" + getByteSize() + " bytes at " + address.toString() + ")";
	}
}
