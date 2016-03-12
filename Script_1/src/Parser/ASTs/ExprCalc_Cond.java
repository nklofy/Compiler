package Parser.ASTs;

import Parser.AST;

public class ExprCalc_Cond extends AST {
	ExprCalc_Bool bool_exp;
	String ret_val;
	String ret_type;
	
	public void setBoolExp(ExprCalc_Bool bool_exp) {
		this.bool_exp = bool_exp;
	}
}
