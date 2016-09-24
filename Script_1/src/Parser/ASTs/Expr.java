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
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
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
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		switch(this.getASTType()){
		case "Expr_Lmbd":
			this.lmbd.ref_type=this.ref_type;
			if(!this.lmbd.genSymTb(codegen))
				return false;
			this.rst_type=this.lmbd.rst_type;
			this.rst_val=this.lmbd.rst_val;
			break;
		case "Expr_Calc":
			this.calc.ref_type=this.ref_type;
			if(!this.calc.genSymTb(codegen))
				return false;
			this.rst_type=this.calc.rst_type;
			this.rst_val=this.calc.rst_val;
		default:
			return false;
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		switch(this.getASTType()){
		case "Expr_Lmbd":
			if(!this.lmbd.checkType(codegen))
				return false;
			break;
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
