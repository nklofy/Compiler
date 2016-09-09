package Parser.ASTs;

import Parser.*;

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
	public boolean genCode(CodeGenerator codegen){
		switch(this.getASTType()){
		case "ExprCalc_Cond":
			this.cond.genCode(codegen);
			this.rst_val=this.cond.rst_val;
			break;
		case "ExprCalc_NewCls":
			this.newCls.genCode(codegen);
			this.rst_val=this.newCls.rst_val;
			break;
		case "ExprCalc_NewArr":
			this.newArr.genCode(codegen);
			this.rst_val=this.newArr.rst_val;
			break;
		default:return false;
		}
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		switch(this.getASTType()){
		case "ExprCalc_Cond":
			if(!this.cond.genSymTb(codegen))
				return false;
			this.rst_type=this.cond.rst_type;
			this.cond.ref_type=this.ref_type;
			break;
		case "ExprCalc_NewCls":
			if(!this.newCls.genSymTb(codegen))
				return false;
			this.rst_type=this.newCls.rst_type;
			this.newCls.ref_type=this.ref_type;
			break;
		case "ExprCalc_NewArr":
			if(!this.newArr.genSymTb(codegen))
				return false;
			this.rst_type=this.newArr.rst_type;
			this.newArr.ref_type=this.ref_type;
			break;
		default:
			return false;
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		switch(this.getASTType()){
		case "ExprCalc_Cond":
			return this.cond.checkType(codegen);
		case "ExprCalc_NewCls":
			return this.newCls.checkType(codegen);
		case "ExprCalc_NewArr":
			return this.newArr.checkType(codegen);
		default:return false;
		}
	}
}
