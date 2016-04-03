package Parser.ASTs;

import Parser.*;

public class ExprAccs_Pri extends AST {
	ExprPri_Chr chr;
	ExprPri_Str str;
	ExprPri_Num num;
	ExprCalc_Cond cond;
	public void setPri(AST ast){
		switch(this.getASTType()){
		case "ExprPri_Chr":
			this.chr=(ExprPri_Chr)ast;
			break;
		case "ExprPri_Str":
			this.str=(ExprPri_Str)ast;
			break;
		case "ExprPri_Num":
			this.num=(ExprPri_Num)ast;
			break;
		case "ExprCalc_Cond":
			this.cond=(ExprCalc_Cond)ast;
			default:
				break;
		}
	}
	
	public boolean genCode(CodeGenerator codegen){
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		//new type, new var, new function, put in table
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		return true;
	}
}
