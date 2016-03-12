package Parser.ASTs;

import Parser.*;

public class ExprAccs_Fld extends AST {
	ExprAccs_Fld pre_fld;
	ExprPri_Var var;
	String sign;
	String ret_addr;
	String ref_type;
	
	public void setAccs(ExprAccs_Fld pre_fld,ExprPri_Var var,String sign){
		this.pre_fld=pre_fld;
		this.var=var;
		this.sign=sign;
	}
	public boolean genCode(CodeGenerator codegen){
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		return true;
	}
}
