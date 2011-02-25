/**
 * 
 */
package com.eluctari.game.z.zmechanic;

import java.io.*;
import java.util.Iterator;

/**
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 *
 */
public class Lexer implements Iterator<ZToken> {

	private StreamTokenizer streamTokenizer;
	private boolean more;
	private Reader reader;
	
	public Lexer(File f) throws FileNotFoundException {
		this(new BufferedReader(new FileReader(f)));
	}
	
	public Lexer(Reader r) {
		reader = r;
		streamTokenizer = new StreamTokenizer(reader);
		streamTokenizer.eolIsSignificant(true);
		streamTokenizer.quoteChar('"');
		streamTokenizer.lowerCaseMode(true);
		streamTokenizer.commentChar(';');
		streamTokenizer.ordinaryChar('.'); // must do this before calling wordChars for period
		streamTokenizer.wordChars('.','.');
		streamTokenizer.wordChars('_', '_');
		streamTokenizer.wordChars(':',':');
		streamTokenizer.wordChars('?','?');
		more = true;
	}
	
	@Override
	public ZToken next() {
		int x = StreamTokenizer.TT_EOF;
		try {
			x = streamTokenizer.nextToken();
		} catch (IOException e) {
			more = false;
			return new ZToken(ZTokenType.EOF);
		}
		switch(x) {
			case StreamTokenizer.TT_EOF:
				more = false;
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return new ZToken(ZTokenType.EOF);
			case StreamTokenizer.TT_EOL:
				return new ZToken(ZTokenType.EOLN);
			case ',':
				return new ZToken(ZTokenType.COMMA);
			case StreamTokenizer.TT_NUMBER:
				return new ZToken(ZTokenType.INTEGER, (int)streamTokenizer.nval);
			case StreamTokenizer.TT_WORD:
				return new ZToken(ZTokenType.WORD, streamTokenizer.sval);
			case '"':
				return new ZToken(ZTokenType.STRING, streamTokenizer.sval);
			case '/':
				return new ZToken(ZTokenType.SLASH);
			case '>':
				return new ZToken(ZTokenType.GREATERTHAN);
			case '\'':
				return new ZToken(ZTokenType.SINGLEQUOTE);
			default:
				throw new AssertionError("Unknown token code " + x + "(" + this + ")");
			
		}
	}

	@Override
	public boolean hasNext() {
		return more;
	}

	@Override
	public void remove() {
		throw new AssertionError("This should never happen.");		
	}
}
