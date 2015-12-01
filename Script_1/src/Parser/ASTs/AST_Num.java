package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_Num extends AST {
	private String type;
	private String buffer;
	public boolean setNum(String type, String buffer){
		this.type=type;
		this.buffer=buffer;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		// TODO Auto-generated method stub
		return false;
	}

}
