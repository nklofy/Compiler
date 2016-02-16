package Parser.ASTs;

import Parser.*;

public class StmtIf_ElsBd extends AST {
	Stmt_If if_stmt;
	AST_StmtList stmt_list;
	Stmt_Sg sg_stmt;
	int lb_start;
	int lb_end;
	public boolean setElsBd(Stmt_If if_stmt,AST_StmtList stmt_list,Stmt_Sg sg_stmt){
		this.if_stmt=if_stmt;
		this.stmt_list=stmt_list;
		this.sg_stmt=sg_stmt;
		return true;
	}
	public boolean genCode(CodeGenerator codegen){
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		return true;
	}
}
