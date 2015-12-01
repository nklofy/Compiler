package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_ApplyExp extends AST {
	private AST_ApplyExp apply_exp;
	private AST_Var var;
	private AST_ArgList arg_list;
	public boolean setApplyExp(AST_ApplyExp apply_exp, AST_Var var, AST_ArgList arg_list){
		this.apply_exp=apply_exp;
		this.var=var;
		this.arg_list=arg_list;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		// TODO Auto-generated method stub
		return false;
	}

}
