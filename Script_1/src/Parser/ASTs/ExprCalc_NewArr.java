package Parser.ASTs;

import Parser.*;

public class ExprCalc_NewArr extends AST {
	TypeExp type_exp;
	NewArr_DimLst dim_lst;
	NewArr_InitLst init_lst;
	String ret_val;
	String ret_type;
	
	public boolean setNewArr(TypeExp type_exp,NewArr_DimLst dim_lst,NewArr_InitLst init_lst){
		this.type_exp=type_exp;
		this.dim_lst=dim_lst;
		this.init_lst=init_lst;
		return true;
	}
}
