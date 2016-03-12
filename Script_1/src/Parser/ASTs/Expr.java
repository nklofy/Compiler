package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class Expr extends AST {
	Expr_Lmbd lmbd;
	Expr_Calc calc;
	String ret_val;//return value of calculation
	String ret_type;//return type of calculation
	String ref_type;//reference type for assignment
	
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
