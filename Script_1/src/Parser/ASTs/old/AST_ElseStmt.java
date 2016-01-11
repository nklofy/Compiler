package Parser.ASTs.old;

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
		if(this.if_exp!=null){
			interpreter.interpret(this.if_exp);
		}else if(this.stmt_list!=null){
			interpreter.interpret(this.stmt_list);
		}else if(this.sg_stmt!=null){
			interpreter.interpret(this.sg_stmt);
		}
		return true;
	}

}
