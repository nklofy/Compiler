package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.GenCodeException;
import Parser.TypeSys.GenSymTblException;
import Parser.TypeSys.TypeCheckException;

public class StmtIf_ElsBd extends AST {
	Stmt_If if_stmt;
	AST_StmtList stmt_list;
	Stmt_Sg sg_stmt;
	
	public boolean setElsBd(Stmt_If if_stmt,AST_StmtList stmt_list,Stmt_Sg sg_stmt){
		this.if_stmt=if_stmt;
		this.stmt_list=stmt_list;
		this.sg_stmt=sg_stmt;
		return true;
	}
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		if(this.if_stmt!=null){
			this.if_stmt.genCode(codegen);
		}
		if(this.stmt_list!=null){
			this.stmt_list.genCode(codegen);
		}
		if(this.sg_stmt!=null){
			this.sg_stmt.genCode(codegen);
		}
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		if(this.if_stmt!=null)
			return this.if_stmt.checkType(codegen);
		if(this.stmt_list!=null)
			return this.stmt_list.checkType(codegen);
		if(this.sg_stmt!=null)
			return this.sg_stmt.checkType(codegen);
		return false;
	}
}
