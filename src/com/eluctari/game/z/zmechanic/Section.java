/**
 * 
 */
package com.eluctari.game.z.zmechanic;

import java.util.*;
/**
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 *
 */
public class Section extends ParseElement {
	static Map<String,SectionType> sectionMap = new HashMap();

	SectionType sectionType;
	
	public enum SectionType {
		GLOBAL("global"), OBJECT("object"), IMPURE("impure"), VOCAB("vocab"), WORDS("words"), ENDLOD("endlod"), START("start");
		private String representation;
		private SectionType(String x) {
//			System.out.println("adding section:"+x);
			representation = x;
			sectionMap.put(x, this);
		}
		public String toString() {
			return representation;
		}
	}
	
	static {
		SectionType dummy = SectionType.GLOBAL;
	}
	
	public Section(ZToken t) {
		String s = t.getStringValue();
		s = s.substring(0, s.length()-2);
		if (!sectionMap.containsKey(s)) {
			throw new IllegalArgumentException("Illegal section: \"" + s + "\".");
		} else {
			sectionType = sectionMap.get(s);
		}
	}
	
	public String toString() {
		return "SECTION(" + sectionType.toString() + " = " + address + ")";
	}
}
