package Parser.ASTs;

import Parser.AST;

public class Gnrc_Par extends AST {
	ExprPri_Var var;
	TypeExp_Idn idn;
	public Gnrc_Par(ExprPri_Var var){
		this.var=var;
	}
	public Gnrc_Par(ExprPri_Var var,TypeExp_Idn idn){
		this.var=var;
		this.idn=idn;
	}
}
