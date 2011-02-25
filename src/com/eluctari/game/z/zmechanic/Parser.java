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
	List<ParseElement> syntaxTree;
	int position;
	
	public Parser(Lexer lex) {
		lexer = lex;
		position = 0;
	}
	
	public void parse() {
		syntaxTree = new LinkedList<ParseElement>();
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
					List<ZToken> args = new LinkedList<ZToken>();
					for (ZToken arg = lexer.next(); arg.getZTokenType() != ZTokenType.EOLN ;) {
						args.add(arg);
					}
					Instruction.Factory.INSTANCE.setForm(Instruction.Form.F_LONG);
					switch(args.size()) {
					case 0:
						Instruction.Factory.INSTANCE.setOperandCount(Instruction.OperandCount.OC_0OP);
						break;
					case 1:
						Instruction.Factory.INSTANCE.setOperandCount(Instruction.OperandCount.OC_1OP);
						break;
					case 2:
						Instruction.Factory.INSTANCE.setOperandCount(Instruction.OperandCount.OC_2OP);
						break;
					}
					int n = 0;
					for (ZToken t2 : args) {
						Operand o = new Operand(t2);
						Instruction.Factory.INSTANCE.setOperand(n++, o);
					}
					syntaxTree.add(Instruction.Factory.INSTANCE.newInstance());
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
