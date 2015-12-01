package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_IfStmt extends AST {
	private AST_BoolExp bool_exp;
	private AST_StmtList stmt_list;
	private AST_SgStmt sg_stmt;
	public boolean setIfStmt(AST_BoolExp bool_exp, AST_StmtList stmt_list, AST_SgStmt sg_stmt){
		this.bool_exp=bool_exp;
		this.stmt_list=stmt_list;
		this.sg_stmt=sg_stmt;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		// TODO Auto-generated method stub
		return false;
	}

}
