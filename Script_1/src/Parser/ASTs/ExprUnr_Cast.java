package Parser.ASTs;

import Parser.*;

public class ExprUnr_Cast extends AST {
	TypeExp type_exp;
	ExprAccs accs;
	public boolean setCast(TypeExp type_exp,ExprAccs accs){
		this.type_exp=type_exp;
		this.accs=accs;
		return true;
	}
}
