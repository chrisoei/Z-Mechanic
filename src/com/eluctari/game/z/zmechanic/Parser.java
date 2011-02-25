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
	Address currentAddress;
	
	public Parser(Lexer lex) {
		lexer = lex;
		position = 0;
		currentAddress = new Address(0);
	}
	
	public void parse() {
		syntaxTree = new LinkedList<ParseElement>();
		position = 0;
		while(lexer.hasNext()) {
			ZToken tok = lexer.next();
			switch(tok.getZTokenType()) {
			case EOF:
				return;
			case EOLN:
				position = 0;
				break;
			case WORD:
				String s = tok.getStringValue();
				if (s.endsWith("::")) {
					Section sec = new Section(tok);
					sec.setAddress(currentAddress);
					syntaxTree.add(sec);
				} else if (s.endsWith(":")) {
					Label l = new Label(tok);
					l.setAddress(currentAddress);
					syntaxTree.add(l);
				} else if (s.equals(".byte")) {
					ZData d = new ZData(tok);
					d.setData(0, lexer.next().getByteValue());
					d.setAddress(currentAddress);
					syntaxTree.add(d);
					currentAddress = currentAddress.nextByte();
				} else if (s.equals(".word")) {
					ZData d = new ZData(tok);
					d.setData(0, lexer.next().getByteValue());
					d.setData(1, lexer.next().getByteValue());
					d.setAddress(currentAddress);
					syntaxTree.add(d);
					currentAddress = currentAddress.nextWord();
				} else if (s.equals(".fstr")) {
					
				} else if (s.equals(".end")) {
					return;
				} else {
					List<ZToken> args = new LinkedList<ZToken>();
					while (lexer.hasNext() ) {
						ZToken arg = lexer.next();
						if (arg.getZTokenType() == ZTokenType.EOLN)
							break;
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
					System.out.println("Looking for instruction " + s);
					Instruction.Factory.INSTANCE.setOpcode(OpcodeTable.INSTANCE.get(Instruction.OperandCount.OC_1OP, s).bytes[0]);//FIXME
					int n = 0;
					for (ZToken t2 : args) {
						Operand o = new Operand(t2);
						Instruction.Factory.INSTANCE.setOperand(n++, o);
					}
					Instruction addme = Instruction.Factory.INSTANCE.newInstance();
					addme.setAddress(currentAddress);
					syntaxTree.add(addme);
					//currentAddress = currentAddress.increment(addme.getByteSize());
				}
				break; // end case WORD
			default:
				System.out.println("Hit default!!!");
			}
		}
	}
	
	public void show() {
		for (ParseElement e : syntaxTree) {
			System.out.println(e);
		}
	}
}
