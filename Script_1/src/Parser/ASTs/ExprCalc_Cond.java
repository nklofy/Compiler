package Parser.ASTs;

import Parser.AST;
import Parser.CodeGenerator;

public class ExprCalc_Cond extends AST {
	ExprCalc_Bool bool_exp;
	String rst_val;
	String rst_type;
	String ref_type;
	
	public void setBoolExp(ExprCalc_Bool bool_exp) {
		this.bool_exp = bool_exp;
	}
	
	public boolean genCode(CodeGenerator codegen){
		this.bool_exp.genCode(codegen);
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		if(!this.bool_exp.genSymTb(codegen))
			return false;
		this.bool_exp.ref_type=this.ref_type;
		this.rst_type=this.bool_exp.rst_type;
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		if(!this.bool_exp.checkType(codegen))
			return false;
		this.rst_val=this.bool_exp.rst_val;
		return true;
	}
}
