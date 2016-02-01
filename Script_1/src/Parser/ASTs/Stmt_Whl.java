package Parser.ASTs;

import Parser.*;

public class Stmt_Whl extends AST {
	ExprCalc_Unary bool_exp;
	AST_StmtList stmt_list;
	public boolean setwhl(ExprCalc_Unary bool_exp,AST_StmtList stmt_list){
		this.bool_exp=bool_exp;
		this.stmt_list=stmt_list;
		return true;
	}
}
