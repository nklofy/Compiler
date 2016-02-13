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
		if(this.bool_exp.isMerged()){
			this.bool_exp=(ExprCalc_Bool)this.bool_exp.getDeMrg(codegen);
			if(this.bool_exp==null)return false;
		}
		if(this.stmt_list.isMerged()){
			this.stmt_list=(AST_StmtList)this.stmt_list.getDeMrg(codegen);
			if(this.stmt_list==null)return false;
		}
		//return 
		return true;
	}
}
