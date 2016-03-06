package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class Expr extends AST {
	Expr_Lmbd lmbd;
	Expr_Calc calc;
	String tmp_rst;
	String tmp_type;
	
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
	public String getRst(){
		return null;
	}
	public boolean genCode(CodeGenerator codegen){
		return true;
	}
	public boolean checkType(){
		switch(this.getASTType()){
		case "Expr_Lmbd":
			
			break;
		case "Expr_Calc":
			
			break;
		default:
			break;
		}
		return true;
	}
}
