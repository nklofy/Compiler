package Parser.ASTs;

import Parser.AST;

public class ExprCalc_Cond extends AST {
	ExprCalc_Bool bool_exp;

	public void setBoolExp(ExprCalc_Bool bool_exp) {
		this.bool_exp = bool_exp;
	}
}
