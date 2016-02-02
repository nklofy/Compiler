package Parser.ASTs;

import Parser.*;

public class ExprAccs_Fld extends AST {
	ExprAccs_Fld pre_fld;
	ExprPri_Var var;
	String sign;
	
	public void setAccs(ExprAccs_Fld pre_fld,ExprPri_Var var,String sign){
		this.pre_fld=pre_fld;
		this.var=var;
		this.sign=sign;
	}
	
}
