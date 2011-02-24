/**
 * 
 */
package com.eluctari.game.z.zmechanic;

import java.util.*;

/**
 * See Z-Machine Standards Document 1.0, Section 14 (page 70).
 * @author Christopher Oei http://www.linkedin.com/in/eluctari
 *
 */
public enum OpcodeTable {

	INSTANCE;
	
	private int nOpcodes; // total # of opcodes
	
	private Map<Opcode.OperandType,Map<String,Opcode>> table;
	
	private void addEntry(boolean st, boolean br, String op, Integer code, String description) {
		String[] z = op.split(":");
		if (z.length != 2) {
			throw new IllegalArgumentException("Bad length: " + z.length);
		}
		Opcode.OperandType ot = Opcode.OperandType.get(z[0]);
		int c = Integer.parseInt(z[1]);
		if ( (c < 0) || (c > 255)) {
			throw new IllegalArgumentException("Bad byte code: " + c);
		}
		String name = new StringTokenizer(description).nextToken();
		Opcode opcode;
		if (ot == Opcode.OperandType.OT_EXT) {
			opcode = new Opcode(ot, name, 190, c); // See p75
		} else {
			opcode = new Opcode(ot, name, c);
		}
		table.get(ot).put(name, opcode);
		nOpcodes++;
	}
	
	private OpcodeTable() {
		
	}
	
	public Opcode get(Opcode.OperandType ot, String s) {
		return table.get(ot).get(s);
	}
	
	public void initialize() {
		nOpcodes = 0;
		table = new HashMap<Opcode.OperandType, Map<String,Opcode>>();
		for (Opcode.OperandType ot : Opcode.OperandType.values()) {
			table.put(ot, new HashMap<String,Opcode>());
		}
		generate0OP();
		generate1OP();
		generate2OP();
		generateVAR();
		generateEXT();
	}
	
	/**
	 * See Z-Machine Standards Document 1.0, page 70.
	 */
	public void generate2OP() {
		addEntry(false, true, "2OP:1", 0x01, "je a b ?(label)");
		addEntry(false, true, "2OP:2", 0x02, "jl a b ?(label)");
		addEntry(false, true, "2OP:3", 0x03, "jg a b ?(label)");
		addEntry(false, true, "2OP:4", 0x04, "dec_chk (variable) value ?(label)");
		addEntry(false, true, "2OP:5", 0x05, "inc_chk (variable) value ?(label)");
		addEntry(false, true, "2OP:6", 0x06, "jin obj1 obj2 ?(label)");
		addEntry(true, false, "2OP:8", 0x08, "or a b -> (result)");
		addEntry(true, false, "2OP:9", 0x09, "and a b -> (result)");
		addEntry(false, true, "2OP:10", 0x0a, "test_attr object attribute ?(label)");
		addEntry(false, false, "2OP:11", 0x0b, "set_attr object attribute");
		addEntry(false, false, "2OP:12", 0x0c, "clear_attr object attribute");
		addEntry(false, false, "2OP:13", 0x0d, "store (variable) value");
		addEntry(false, false, "2OP:14", 0x0e, "insert_obj object destination");
		addEntry(true, false, "2OP:15", 0x0f, "loadw array word-index -> (result)");
		addEntry(true, false, "2OP:16", 0x10, "loadb array byte-index -> (result)");
		addEntry(true, false, "2OP:17", 0x11, "get_prop object property -> (result)");
		addEntry(true, false, "2OP:18", 0x12, "get_prop_addr object property -> (result");
		addEntry(true, false, "2OP:19", 0x13, "get_next_prop object property -> (result)");
		addEntry(true, false, "2OP:20", 0x14, "add a b -> (result)");
		addEntry(true, false, "2OP:21", 0x15, "sub a b -> (result)");
		addEntry(true, false, "2OP:22", 0x16, "mul a b -> (result)");
		addEntry(true, false, "2OP:23", 0x17, "div a b -> (result)");
		addEntry(true, false, "2OP:24", 0x18, "mod a b -> (result)");
		addEntry(true, false, "2OP:25", 0x19, "call_2s routine arg1 -> (result)");
		addEntry(false, false, "2OP:26", 0x1a, "call_2n routine arg1");
		addEntry(false, false, "2OP:27", 0x1b, "set_colour foreground background");
		addEntry(false, false, "2OP:28", 0x1c, "throw value stack-frame");
	}
	
	/**
	 * See Z-Machine Standards Document 1.0, page 71.
	 */
	public void generate1OP() {
		addEntry(false, true, "1OP:128", 0x00, "jz a ?(label)");
		addEntry(true, true, "1OP:129", 0x01, "get_sibling object -> (result) ?(label)");
		addEntry(true, true, "1OP:130", 0x02, "get_child object -> (result) ?(label)");
		addEntry(true, false, "1OP:131", 0x03, "get_parent object -> (result)");
		addEntry(true, false, "1OP:132", 0x04, "get_prop_len property-address -> (result)");
		addEntry(false, false, "1OP:133", 0x05, "inc (variable)");
		addEntry(false, false, "1OP:134", 0x06, "dec (variable)");
		addEntry(false, false, "1OP:135", 0x07, "print_addr byte-address-of-string");
		addEntry(true, false, "1OP:136", 0x08, "call_1s routine -> (result)");
		addEntry(false, false, "1OP:137", 0x09, "remove_obj object");
		addEntry(false, false, "1OP:138", 0x0a, "print_obj object");
		addEntry(false, false, "1OP:139", 0x0b, "ret value");
		addEntry(false, false, "1OP:140", 0x0c, "jump ?(label)");
		addEntry(false, false, "1OP:141", 0x0d, "print_paddr packed-address-of-string");
		addEntry(true, false, "1OP:142", 0x0e, "load (variable) -> (result)");
		addEntry(true, false, "1OP:143", 0x0f, "not value -> (result)");
	}
	
	/**
	 * See Z-Machine Standards Document 1.0, Page 72.
	 */
	public void generate0OP() {
		addEntry(false, false, "0OP:176", 0x00, "rtrue");
		addEntry(false, false, "0OP:177", 0x01, "rfalse");
		addEntry(false, false, "0OP:178", 0x02, "print (literal-string)");
		addEntry(false, false, "0OP:179", 0x03, "print_ret (literal-string)");
		addEntry(false, false, "0OP:180", 0x04, "nop");
		addEntry(false, true, "0OP:181", 0x05, "save ?(label)");
		addEntry(false, true, "0OP:182", 0x06, "restore ?(label)");
		addEntry(false, false, "0OP:183", 0x07, "restart");
		addEntry(false, false, "0OP:184", 0x08, "ret_popped");
		addEntry(false, false, "0OP:185", 0x09, "pop");
		addEntry(false, false, "0OP:186", 0x0a, "quit");
		addEntry(false, false, "0OP:187", 0x0b, "new_line");
		addEntry(false, false, "0OP:188", 0x0c, "show_status");
		addEntry(false, true, "0OP:189", 0x0d, "verify ?(label)");
		addEntry(false, false, "0OP:190", 0x0e, "[first byte of extended opcode]");
		addEntry(false, true, "0OP:191", 0x0f, "piracy ?(label)");
	}
	
	public void generateVAR() {
		addEntry(true, false, "VAR:224", 0x00, "call routine ...0 to 3 args... -> (result");
		addEntry(false, false, "VAR:225", 0x01, "storew array word-index value");
		addEntry(false, false, "VAR:226", 0x02, "storeb array byte-index value");
		addEntry(false, false, "VAR:227", 0x03, "put_prop object property value");
		addEntry(false, false, "VAR:228", 0x04, "sread text parse");
		addEntry(false, false, "VAR:229", 0x05, "print_char output-character-code");
		addEntry(false, false, "VAR:230", 0x06, "print_num value");
		addEntry(true, false, "VAR:231", 0x07, "random range -> (result)");
		addEntry(false, false, "VAR:232", 0x08, "push value");
		addEntry(false, false, "VAR:233", 0x09, "pull (variable)");
		addEntry(false, false, "VAR:234", 0x0a, "split_window lines");
		addEntry(false, false, "VAR:235", 0x0b, "set_window window");
		addEntry(true, false, "VAR:236", 0x0c, "call_vs2 routine ...0 to 7 args... -> (result)");
		addEntry(false, false, "VAR:237", 0x0d, "erase_window window");
		addEntry(false, false, "VAR:238", 0x0e, "erase_line value");
		addEntry(false, false, "VAR:239", 0x0f, "set_cursor line column");
		addEntry(false, false, "VAR:240", 0x10, "get_cursor array");
		addEntry(false, false, "VAR:241", 0x11, "set_text_style style");
		addEntry(false, false, "VAR:242", 0x12, "buffer_mode flag");
		addEntry(false, false, "VAR:243", 0x13, "output_stream number");
		addEntry(false, false, "VAR:244", 0x14, "input_stream number");
		addEntry(false, false, "VAR:245", 0x15, "sound_effect number effect volume routine");
		addEntry(true, false, "VAR:246", 0x16, "read_char 1 time routine -> (result)");
		addEntry(true, true, "VAR:247", 0x17, "scan_table x table len form -> (result)");
		addEntry(true, false, "VAR:248", 0x18, "not value -> (result)");
		addEntry(false, false, "VAR:249", 0x19, "call_vn routine ...up to 3 args...");
		addEntry(false, false, "VAR:250", 0x1a, "call_vn2 routine ...up to 7 args...");
		addEntry(false, false, "VAR:251", 0x1b, "tokenise text parse dictionary flag");
		addEntry(false, false, "VAR:252", 0x1c, "encode_text zscii-text length from coded-text");
		addEntry(false, false, "VAR:253", 0x1d, "copy_table first second size");
		addEntry(false, false, "VAR:254", 0x1e, "print_table zscii-text width height skip");
		addEntry(false, true, "VAR:255", 0x1f, "check_arg_count argument-number");
	}
	
	public void generateEXT() {
		addEntry(true, false, "EXT:0", 0x00, "save table bytes name -> (result)");
		addEntry(true, false, "EXT:1", 0x01, "restore table bytes name -> (result)");
		addEntry(true, false, "EXT:2", 0x02, "log_shift number places -> (result)");
		addEntry(true, false, "EXT:3", 0x03, "art_shift number places -> (result)");
		addEntry(true, false, "EXT:4", 0x04, "set_font font -> (result)");
		addEntry(false, false, "EXT:5", 0x05, "draw_picture picture-number y x");
		addEntry(false, true, "EXT:6", 0x06, "picture_data picture-number array ?(label)");
		addEntry(false, false, "EXT:7", 0x07, "erase_picture picture-number y x");
		addEntry(false, false, "EXT:8", 0x08, "set_margins left right window");
		addEntry(true, false, "EXT:9", 0x09, "save_undo -> (result)");
		addEntry(true, false, "EXT:10", 0x0a, "restore_undo -> (result)");
		addEntry(false, false, "EXT:11", 0x0b, "print_unicode char-number");
		addEntry(false, false, "EXT:12", 0x0c, "check_unicode char-number -> (result)");
		// Skip over 13-15
		addEntry(false, false, "EXT:16", 0x10, "move_window window y x");
		addEntry(false, false, "EXT:17", 0x11, "window_size window y x");
		addEntry(false, false, "EXT:18", 0x12, "window_style window flags operation");
		addEntry(true, false, "EXT:19", 0x13, "get_wind_prop window property-number -> (result)");
		addEntry(false, false, "EXT:20", 0x14, "scroll_window window pixels");
		addEntry(false, false, "EXT:21", 0x15, "pop_stack items stack");
		addEntry(false, false, "EXT:22", 0x16, "read_mouse array");
		addEntry(false, false, "EXT:23", 0x17, "mouse_window window");
		addEntry(false, true, "EXT:24", 0x18, "push_stack value stack ?(label)");
		addEntry(false, false, "EXT:25", 0x19, "put_wind_prop window property-number value");
		addEntry(false, false, "EXT:26", 0x1a, "print_form formatted-table");
		addEntry(false, true, "EXT:27", 0x1b, "make_menu number table ?(label)");
		addEntry(false, false, "EXT:28", 0x1c, "picture_table table");

	}
}
