package Parser.ASTs;

import Parser.*;

public class Stmt_If extends AST {
	StmtIf_IfBd if_body;
	StmtIf_ElsBd else_body;
	public boolean setIfStmt(StmtIf_IfBd if_body,StmtIf_ElsBd else_body){
		this.if_body=if_body;
		this.else_body=else_body;
		return true;
	}
}
