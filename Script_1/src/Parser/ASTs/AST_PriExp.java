package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_PriExp extends AST {
	private AST_AddExp add_exp;
	private AST_Num num;
	private AST_Var var;
	private AST_ApplyExp apply_exp;
	public boolean setPriExp(AST_AddExp add_exp, AST_Num num, AST_Var var, AST_ApplyExp apply_exp){
		this.add_exp=add_exp;
		this.num=num;
		this.var=var;
		this.apply_exp=apply_exp;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		// TODO Auto-generated method stub
		return false;
	}

}
