package Parser.ASTs;

import Parser.*;

public class Expr_Calc extends AST {
	//cond_exp new_class_exp new_array_exp
	ExprCalc_Cond cond;
	ExprCalc_NewCls newCls;
	ExprCalc_NewArr newArr;
	String rst_type;
	String rst_val;
	
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
			this.rst_val=this.cond.ret_val;
			break;
		case "ExprCalc_NewCls":
			this.newCls.genCode(codegen);
			this.rst_val=this.newCls.ret_val;
			break;
		case "ExprCalc_NewArr":
			this.newArr.genCode(codegen);
			this.rst_val=this.newArr.ret_val;
			break;
		default:return false;
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		switch(this.getASTType()){
		case "ExprCalc_Cond":
			boolean b=this.cond.checkType(codegen);
			this.rst_type=this.cond.ret_type;
			return b;
		case "ExprCalc_NewCls":
			b=this.newCls.checkType(codegen);
			this.rst_type=this.newCls.ret_type;
			return b;
		case "ExprCalc_NewArr":
			b=this.newArr.checkType(codegen);
			this.rst_type=this.newArr.ret_type;
			return b;
		default:break;
		}
		return false;
	}
}
