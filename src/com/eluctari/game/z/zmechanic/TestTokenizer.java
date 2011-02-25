/**
 * 
 */
package com.eluctari.game.z.zmechanic;

import static org.junit.Assert.*;

import java.io.*;

import org.junit.Test;


/**
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 *
 */
public class TestTokenizer {
	@Test public void testToken1() {
		try {
			for (Lexer t = new Lexer(new File("/home/software/Projects/Z-Mechanic/beer.zap"));
					t.hasNext(); ) {
				ZToken x = t.next();
				switch(x.getZTokenType()) {
					case EOLN:
						//System.out.println("EOLN");
						break;
					
					case INTEGER:
						//System.out.println("Number: " + x.getIntegerValue());
						break;
						
					case COMMA:
						//System.out.println("Comma: ");
						break;
						
					case WORD:
						//System.out.println("Word: " + x.getStringValue());
						break;
						
					case EOF:
						//System.out.println("EOF");
						break;
		
					case GREATERTHAN:
						//System.out.println(">");
						
					default:
//						System.out.println("Unknown: " + t + " = "+ x.getIntegerValue() + "/" + x.getStringValue());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
}
