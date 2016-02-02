package Parser.ASTs;

import Parser.*;

public class NewArr_Init extends AST {
	Expr_Calc calc;
	NewArr_InitLst lst;
	public void setCalc(Expr_Calc calc) {
		this.calc = calc;
	}
	public void setLst(NewArr_InitLst lst) {
		this.lst = lst;
	}
	
}
