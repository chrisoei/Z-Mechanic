/**
 * 
 */
package com.eluctari.game.z.zmechanic;

import java.util.*;
/**
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 *
 */
public class Section extends ZElement {
	static Map<String,SectionType> sectionMap = new HashMap();

	SectionType sectionType;
	
	public enum SectionType {
		GLOBAL("global"), OBJECT("object"), IMPURE("impure"), VOCAB("vocab"), WORDS("words"), ENDLOD("endlod"), START("start");
		private SectionType(String x) {
			sectionMap.put(x, this);
		}
	}
	
	public Section(ZToken t) {
		String s = t.getStringValue();
		s = s.substring(2,s.length()-2);
		if (!sectionMap.containsKey(s)) {
			throw new AssertionError("Illegal section" + s);
		} else {
			sectionType = sectionMap.get(s);
		}
	}
	
}
