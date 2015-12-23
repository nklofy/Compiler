package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;
import Parser.TypeSys.Type_Base;

public class AST_StrExp extends AST {
	private String str;
	private String chr;
	Type_Base type;
	public boolean setStr(String str, String chr){
		this.str=str;
		this.chr=chr;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		// TODO Auto-generated method stub
		return false;
	}

}
