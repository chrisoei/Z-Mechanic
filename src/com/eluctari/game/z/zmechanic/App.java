/**
 * 
 */
package com.eluctari.game.z.zmechanic;

import java.io.*;

/**
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 *
 */
public class App {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader(args[0])));
		st.eolIsSignificant(true);
		st.quoteChar('"');
		st.lowerCaseMode(true);
		st.commentChar(';');
	}

}
