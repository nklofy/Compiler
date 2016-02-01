package Parser.ASTs;

import Parser.AST;

public class FuncDef_Par extends AST {
	TypeExp type;
	ExprPri_Var var;
	public FuncDef_Par(TypeExp type,ExprPri_Var var){
		this.type=type;
		this.var=var;
	}
}
