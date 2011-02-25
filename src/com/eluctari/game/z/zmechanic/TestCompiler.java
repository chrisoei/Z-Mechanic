/**
 * 
 */
package com.eluctari.game.z.zmechanic;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.*;

/**
 * See Z-Machine Standards Document 1.0, section x.x.x.
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 *
 */
public class TestCompiler {
	@Test public void testOne() {
		Compiler c = new Compiler();
		try {
			c.compile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
}
