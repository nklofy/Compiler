package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class Expr extends AST {
	Expr_Lmbd lmbd;
	Expr_Calc calc;
	String rst_val;//return value of calculation
	String rst_type;//return type of calculation
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
		return this.rst_val;
	}
	public boolean genCode(CodeGenerator codegen){
		switch(this.getASTType()){
		case "Expr_Lmbd":
			this.lmbd.genCode(codegen);
			break;
		case "Expr_Calc":
			this.calc.genCode(codegen);
			break;
		default:
			return false;
		}
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		switch(this.getASTType()){
		case "Expr_Lmbd":
			if(!this.lmbd.genSymTb(codegen))
				return false;
			break;
		case "Expr_Calc":
			if(!this.calc.genSymTb(codegen))
;				return false;
		default:
			return false;
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		switch(this.getASTType()){
		case "Expr_Lmbd":
			if(!this.lmbd.checkType(codegen))
				return false;
		case "Expr_Calc":
			if(!this.calc.checkType(codegen))
				return false;
			break;
		default:
			return false;
		}
		return true;
	}
}
