package Parser.ASTs;

import Parser.*;

public class Stmt_Whl extends AST {
	ExprCalc_Bool bool_exp;
	AST_StmtList stmt_list;
	public boolean setwhl(ExprCalc_Bool bool_exp,AST_StmtList stmt_list){
		this.bool_exp=bool_exp;
		this.stmt_list=stmt_list;
		return true;
	}
	public boolean genCode(CodeGenerator codegen){
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		return this.bool_exp.checkType(codegen)&&this.stmt_list.checkType(codegen);
	}
}
