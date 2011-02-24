/**
 * 
 */
package com.eluctari.game.z.zmechanic;

/**
 * See Z-Machine Standards Document 1.0.
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 *
 */
public enum ZMachine {
	INSTANCE;
	
	private int version;
	private long routineOffset = 0;
	private long stringOffset = 0;
	
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int v) {
		version = v;
	}
	
	public long getRoutineOffset() {
		return routineOffset;
	}
	
	public void setRoutineOffset(long x) {
		routineOffset = x;
	}
	
	public long getStringOffset() {
		return stringOffset;
	}
	
	public void setStringOffset(long x) {
		stringOffset = x;
	}
}
