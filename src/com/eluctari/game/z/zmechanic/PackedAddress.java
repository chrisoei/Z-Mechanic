/**
 * 
 */
package com.eluctari.game.z.zmechanic;

/**
 * See Z-Machine Standards Document 1.0, section 1.2.3.
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 *
 */
public class PackedAddress extends Address {
	public PackedAddress(long x) {
		int v = ZMachine.INSTANCE.getVersion();
		switch(v) {
		case 1:
		case 2:
		case 3:
			setByteAddress(2 * x);
			return;
		case 4:
		case 5:
			setByteAddress(4 * x);
			return;
		case 8:
			setByteAddress(8 * x);
			return;
		case 6:
		case 7:
			throw new AssertionError("V6 and V7 packed addresses depend on string vs. routine.");
		default:
			throw new UnknownZMachineVersionException(v);
		}
	}
	
	public PackedAddress() {
		
	}
	
	public static PackedAddress newRoutineAddress(long x) {
		PackedAddress a = new PackedAddress();
		int v = ZMachine.INSTANCE.getVersion();

		switch(v) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 8:
			a = new PackedAddress(x);
			break;
		case 6:
		case 7:
			a.setByteAddress(4L * x + 8L * ZMachine.INSTANCE.getRoutineOffset());
			break;
		default:
			throw new UnknownZMachineVersionException(v);
		}
		return a;
	}
	
	public static PackedAddress newStringAddress(long x) {
		PackedAddress a = new PackedAddress();
		int v = ZMachine.INSTANCE.getVersion();
		switch(v) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 8:
			a = new PackedAddress(x);
			break;
		case 6:
		case 7:
			a.setByteAddress(4L * x + 8L * ZMachine.INSTANCE.getRoutineOffset());
			break;
		default:
			throw new UnknownZMachineVersionException(v);
		}
		return a;
	}
}
