package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_AddExp extends AST {
	private AST_AddExp add_exp;
	private AST_MulExp mul_exp;
	private String opt;
	private AST_Var var;
	public boolean setAddExp(AST_AddExp add_exp, AST_MulExp mul_exp, String opt, AST_Var var){
		this.add_exp=add_exp;
		this.mul_exp=mul_exp;
		this.opt=opt;
		this.var=var;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		// TODO Auto-generated method stub
		return false;
	}

}
