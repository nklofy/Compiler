package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class Expr_Lmbd extends AST {
	FuncDef_ParLst par_lst;
	AST_StmtList stmt_list;
	R_Function func;
	public void setLmbd(FuncDef_ParLst par_lst,AST_StmtList stmt_list){
		this.par_lst=par_lst;
		this.stmt_list=stmt_list;
	}
	public boolean genCode(CodeGenerator codegen){
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		//new type, new var, new function, put in table
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		return true;
	}
}
