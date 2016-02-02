package Parser.ASTs;

import Parser.*;

public class ExprAccs_Arr extends AST {
	ExprAccs_Fld pre_fld;
	NewArr_DimLst dim_lst;
	public void setAccs(ExprAccs_Fld pre_fld,NewArr_DimLst dim_lst){
		this.pre_fld=pre_fld;
		this.dim_lst=dim_lst;
	}
}
