/**
 * 
 */
package com.eluctari.game.z.zmechanic;

/**
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 *
 */
public class ZToken {
	ZTokenType zTokenType;
	String stringValue;
	int integerValue;
	
	public ZToken(ZTokenType ztt) {
		zTokenType = ztt;
	}
	
	public ZToken(ZTokenType ztt, String s) {
		this(ztt);
		assert(ztt.equals(ZTokenType.WORD) || ztt.equals(ZTokenType.STRING));
		stringValue = s;
	}
	
	public ZToken(ZTokenType ztt, int n) {
		this(ztt);
		assert(ztt.equals(ZTokenType.INTEGER));
		integerValue = n;
	}
	
	public ZTokenType getZTokenType() {
		return zTokenType;
	}
	
	public String getStringValue() {
		assert(zTokenType.equals(ZTokenType.STRING) || zTokenType.equals(ZTokenType.WORD));
		return stringValue;
	}
	
	public int getIntegerValue() {
		assert(zTokenType.equals(ZTokenType.INTEGER));
		return integerValue;
	}
	
	public byte getByteValue() {
		assert(zTokenType.equals(ZTokenType.INTEGER));
		assert(integerValue < 256);
		assert(integerValue >= 0);
		return (byte)integerValue;
	}
	
	@Override public String toString() {
		if (zTokenType.equals(ZTokenType.INTEGER)) {
			return new Integer(integerValue).toString();
		}
		else if (zTokenType.equals(ZTokenType.STRING) ||zTokenType.equals(ZTokenType.INTEGER) ) {
			return stringValue;
		}
		return zTokenType.toString();
	}
	
}
