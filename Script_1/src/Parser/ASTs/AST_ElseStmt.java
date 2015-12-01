package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_ElseStmt extends AST {
	private AST_IfExp if_exp;
	private AST_StmtList stmt_list;
	private AST_SgStmt sg_stmt;
	public boolean setElseStmt(AST_IfExp if_exp, AST_StmtList stmt_list, AST_SgStmt sg_stmt){
		this.if_exp=if_exp;
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
