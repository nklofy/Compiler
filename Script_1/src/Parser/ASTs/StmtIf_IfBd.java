package Parser.ASTs;

import Parser.*;

public class StmtIf_IfBd extends AST {
	ExprCalc_Bool bool_exp;
	AST_StmtList stmt_list;
	Stmt_Sg sg_stmt;
	public boolean setIfBd(ExprCalc_Bool bool_exp,AST_StmtList stmt_list,Stmt_Sg sg_stmt){
		this.bool_exp=bool_exp;
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
