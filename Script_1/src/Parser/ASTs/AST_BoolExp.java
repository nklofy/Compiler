package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_BoolExp extends AST {
	private AST_BoolExp bool_exp;
	private AST_CmpExp cmp_exp;
	private String opt;
	public boolean setBoolExp(AST_BoolExp bool_exp, String opt, AST_CmpExp cmp_exp){
		this.bool_exp=bool_exp;
		this.cmp_exp=cmp_exp;
		this.opt=opt;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		// TODO Auto-generated method stub
		return false;
	}

}
