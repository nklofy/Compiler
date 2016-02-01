package Parser.ASTs;

import Parser.*;

public class Expr extends AST {
	Expr_Lmbd lmbd;
	Expr_Calc calc;
	public boolean setExpr(AST ast){
		switch(this.getASTType()){
		case "Expr_Lmbd":
			this.lmbd=(Expr_Lmbd)ast;
			break;
		case "Expr_Calc":
			this.calc=(Expr_Calc)ast;
			break;
			default:
				break;
		}
		return true;
	}
}
