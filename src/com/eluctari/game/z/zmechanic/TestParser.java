/**
 * 
 */
package com.eluctari.game.z.zmechanic;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.*;


/**
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 *
 */
public class TestParser {

	@Test public void testOne() {
		try {
			Parser p = 
				new Parser(new Lexer(new File("/home/software/Projects/Z-Mechanic/beer.zap")));
			p.parse();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
}
