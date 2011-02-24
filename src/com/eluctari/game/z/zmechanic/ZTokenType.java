/**
 * 
 */
package com.eluctari.game.z.zmechanic;

/**
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 *
 */
public enum ZTokenType {
	EOF("<EOF>"), EOLN("<EOLN>"), 
	COMMA(","), SLASH("/"), GREATERTHAN(">"), SINGLEQUOTE("'"),
	WORD, INTEGER, STRING,
	UNKNOWN;
	
	private String representation;
	
	private ZTokenType() {
		representation = null;
	}
	
	private ZTokenType(String r) {
		representation = r;
	}
}
