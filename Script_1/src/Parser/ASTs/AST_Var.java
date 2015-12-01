package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_Var extends AST {
	private String name;
	public boolean setVar(String name){
		this.name=name;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		// TODO Auto-generated method stub
		return false;
	}

}
