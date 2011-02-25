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
	private boolean isSet;

	/**
	 * The Z-Machine version number defaults to 8. Once this
	 * property is either read or set, it can never change.
	 */
	public int getVersion() {
		isSet = true;
		return version;
	}
	
	/**
	 * The Z-Machine version number defaults to 8. Once this
	 * property is either read or set, it can never change.
	 * @param v set the version to this integer
	 */
	public void setVersion(int v) {
		if (isSet && (version != v)) {
			throw new IllegalStateException("Cannot change version " + version + " Z-machine to version " + v);
		}
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
