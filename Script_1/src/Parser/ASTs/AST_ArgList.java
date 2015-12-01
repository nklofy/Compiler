package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_ArgList extends AST {
	private AST_ArgList arg_list;
	private AST_Var var;
	public boolean setArgList(AST_ArgList arg_list, AST_Var var){
		this.arg_list=arg_list;
		this.var=var;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		// TODO Auto-generated method stub
		return false;
	}

}
