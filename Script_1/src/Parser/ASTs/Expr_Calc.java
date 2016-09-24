package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.GenCodeException;
import Parser.TypeSys.GenSymTblException;
import Parser.TypeSys.TypeCheckException;

public class Expr_Calc extends AST {
	//cond_exp new_class_exp new_array_exp
	ExprCalc_Cond cond;
	ExprCalc_NewCls newCls;
	ExprCalc_NewArr newArr;
	String rst_type;
	String rst_val;
	String ref_type;
	
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
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		switch(this.getASTType()){
		case "ExprCalc_Cond":
			this.cond.genCode(codegen);
			break;
		case "ExprCalc_NewCls":
			this.newCls.genCode(codegen);
			break;
		case "ExprCalc_NewArr":
			this.newArr.genCode(codegen);
			break;
		default:return false;
		}
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		switch(this.getASTType()){
		case "ExprCalc_Cond":
			this.cond.ref_type=this.ref_type;
			if(!this.cond.genSymTb(codegen))
				return false;
			this.rst_type=this.cond.rst_type;
			this.rst_val=this.cond.rst_val;
			break;
		case "ExprCalc_NewCls":
			this.newCls.ref_type=this.ref_type;
			if(!this.newCls.genSymTb(codegen))
				return false;
			this.rst_type=this.newCls.rst_type;
			this.rst_val=this.newCls.rst_val;
			break;
		case "ExprCalc_NewArr":
			this.newArr.ref_type=this.ref_type;
			if(!this.newArr.genSymTb(codegen))
				return false;
			this.rst_type=this.newArr.rst_type;
			this.rst_val=this.newArr.rst_val;
			break;
		default:
			return false;
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		switch(this.getASTType()){
		case "ExprCalc_Cond":
			if(!this.cond.checkType(codegen))
				return false;
			break;
		case "ExprCalc_NewCls":
			if(!this.newCls.checkType(codegen))
				return false;
			break;
		case "ExprCalc_NewArr":
			if(!this.newArr.checkType(codegen))
				return false;
			break;
		default:return false;
		}
		return true;
	}
}
