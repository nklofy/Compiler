package Parser.ASTs;

import Parser.AST;
import Parser.CodeGenerator;
import Parser.TypeSys.GenCodeException;
import Parser.TypeSys.GenSymTblException;
import Parser.TypeSys.TypeCheckException;

public class ExprCalc_Cond extends AST {
	ExprCalc_Bool bool_exp;
	String rst_val;
	String rst_type;
	String ref_type;
	
	public void setBoolExp(ExprCalc_Bool bool_exp) {
		this.bool_exp = bool_exp;
	}
	
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		this.bool_exp.genCode(codegen);
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		
		if(!this.bool_exp.genSymTb(codegen))
			return false;
		this.rst_val=this.bool_exp.rst_val;
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		this.bool_exp.ref_type=this.ref_type;
		if(!this.bool_exp.checkType(codegen))
			return false;
		this.rst_type=this.bool_exp.rst_type;
		return true;
	}
}
