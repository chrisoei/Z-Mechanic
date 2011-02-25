/**
 * 
 */
package com.eluctari.game.z.zmechanic;

/**
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 *
 */

import java.util.*;

public class Label extends ParseElement {
	
	static enum labelMap {
		INSTANCE;		
		Map<Label, Address> addressMap = new HashMap<Label, Address>();
	}
	
	String labelText;
	
	public Label(ZToken t) {
		String s = t.getStringValue();
		labelText = s.substring(0, s.length() - 1);
	}
	
	public String toString() {
		return "Label(" + labelText + ")";
	}
}
