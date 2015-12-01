package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_CmpExp extends AST {
	private AST_BoolExp bool_exp;
	private AST_AddExp add_exp1;
	private AST_AddExp add_exp2;
	private String opt;
	public boolean setCmpExp(AST_BoolExp bool_exp, AST_AddExp add_exp1, String opt, AST_AddExp add_exp2){
		this.bool_exp=bool_exp;
		this.add_exp1=add_exp1;
		this.add_exp2=add_exp2;
		this.opt=opt;		
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		// TODO Auto-generated method stub
		return false;
	}

}
