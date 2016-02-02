package Parser.ASTs;

import Parser.*;

public class Expr_Lmbd extends AST {
	FuncDef_ParLst par_lst;
	AST_StmtList stmt_list;
	public void setLmbd(FuncDef_ParLst par_lst,AST_StmtList stmt_list){
		this.par_lst=par_lst;
		this.stmt_list=stmt_list;
	}
}
