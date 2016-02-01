package Parser.ASTs;

import Parser.*;

public class Expr_Calc extends AST {
	//cond_exp new_class_exp new_array_exp
	ExprCalc_Cond cond;
	ExprCalc_NewCls newCls;
	ExprCalc_NewArr newArr;
	public boolean setCalc(AST ast){
		switch(this.getASTType()){
		case "ExprCalc_Cond":
			this.cond=(ExprCalc_Cond)ast;
			break;
		case "ExprCalc_NewCls":
			this.newCls=(ExprCalc_NewCls)ast;
			break;
		case "ExprCalc_NewArr":
			this.newArr=(ExprCalc_NewArr)ast;
			break;
			default:
				break;
		}
		return true;
	}
	
}
