package Parser.ASTs.old;

import Interpreter.Interpreter;
import Parser.AST;
import Parser.TypeSys.old.Type_Base;

import java.lang.*;

public class AST_StrExp extends AST {
	private String str;
	private String chr;
	Type_Base type;
	String str_value;
	char chr_value;
	public boolean setStr(String str, String chr){
		this.str=str;
		this.chr=chr;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		// TODO need to add some checks
		if(this.str!=null){
			this.type=Type_Base.t_string;
			this.str_value=str.substring(1,str.length()-1);
			return true;
		}else if(this.chr!=null){
			this.type=Type_Base.t_char;
			this.chr_value=chr.charAt(1);
			return true;
		}
		return false;
	}

}
