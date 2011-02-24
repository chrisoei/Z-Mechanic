/**
 * 
 */
package com.eluctari.game.z.zmechanic;

import java.util.*;

/**
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 *
 */
public class Parser {
	Lexer lexer;
	List<ZElement> syntaxTree;
	int position;
	
	public Parser(Lexer lex) {
		lexer = lex;
		position = 0;
	}
	
	public void parse() {
		syntaxTree = new LinkedList<ZElement>();
		position = 0;
		while(lexer.hasNext()) {
			ZToken tok = lexer.next();
			switch(tok.getZTokenType()) {
			case EOLN:
				position = 0;
				break;
			case WORD:
				String s = tok.getStringValue();
				if (s.endsWith(":")) {
					syntaxTree.add(new Label(tok));
				} else if (s.startsWith("::")) {
					syntaxTree.add(new Section(tok));
				} else if (s.startsWith("@")) {
					throw new UnsupportedOperationException("No @ commands implemented yet.");
				} else if (s.equals(".byte")) {
					ZData d = new ZData(tok);
					d.setData(0, lexer.next().getByteValue());
					syntaxTree.add(d);
				} else if (s.equals(".word")) {
					ZData d = new ZData(tok);
					d.setData(0, lexer.next().getByteValue());
					d.setData(1, lexer.next().getByteValue());
					syntaxTree.add(d);
				} else if (s.equals(".fstr")) {
					
				} else if (s.equals(".end")) {
					
				}
				break;
			default:
			}
		}
	}
}
