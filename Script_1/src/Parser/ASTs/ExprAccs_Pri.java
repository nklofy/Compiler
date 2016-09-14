package Parser.ASTs;

import Parser.*;

public class ExprAccs_Pri extends AST {
	ExprPri_Chr chr;
	ExprPri_Str str;
	ExprPri_Num num;
	ExprCalc_Cond cond;
	String rst_val;
	String ref_type;
	String rst_type;
	
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
		switch(this.getASTType()){
		case "ExprPri_Chr":
			this.chr.genCode(codegen);
			break;
		case "ExprPri_Str":
			this.str.genCode(codegen);
			break;
		case "ExprPri_Num":
			this.num.genCode(codegen);
			break;
		case "ExprCalc_Cond":
			this.cond.genCode(codegen);
		default:
			break;
		}
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		switch(this.getASTType()){
		case "ExprPri_Chr":
			this.chr.ref_type=this.ref_type;
			if(!this.chr.genSymTb(codegen))
				return false;
			this.rst_type=this.chr.rst_type;
			this.rst_val=this.chr.rst_val;
			break;
		case "ExprPri_Str":
			this.str.ref_type=this.ref_type;
			if(!this.str.genSymTb(codegen))
				return false;
			this.rst_type=this.str.rst_type;
			this.rst_val=this.str.rst_val;
			break;
		case "ExprPri_Num":
			this.num.ref_type=this.ref_type;
			if(!this.num.genSymTb(codegen))
				return false;
			this.rst_type=this.num.rst_type;
			this.rst_val=this.num.rst_val;
			break;
		case "ExprCalc_Cond":
			this.cond.ref_type=this.ref_type;
			if(!this.cond.genSymTb(codegen))
				return false;
			this.rst_type=this.cond.rst_type;
			this.rst_val=this.cond.rst_val;
		default:
			break;
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		switch(this.getASTType()){
		case "ExprPri_Chr":
			if(!this.chr.checkType(codegen))
				return false;
			break;
		case "ExprPri_Str":
			if(!this.str.checkType(codegen))
				return false;
			break;
		case "ExprPri_Num":
			if(!this.num.checkType(codegen))
				return false;
			break;
		case "ExprCalc_Cond":
			if(!this.cond.checkType(codegen))
				return false;
		default:
			break;
		}
		return true;
	}
}
