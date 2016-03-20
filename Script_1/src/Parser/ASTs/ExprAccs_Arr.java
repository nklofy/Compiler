package Parser.ASTs;

import Parser.*;

public class ExprAccs_Arr extends AST {
	ExprAccs_Fld pre_fld;
	NewArr_DimLst dim_lst;
	String rst_addr;
	String ref_type;
	
	public void setAccs(ExprAccs_Fld pre_fld,NewArr_DimLst dim_lst){
		this.pre_fld=pre_fld;
		this.dim_lst=dim_lst;
	}
	public boolean genCode(CodeGenerator codegen){
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		return true;
	}
	
}
