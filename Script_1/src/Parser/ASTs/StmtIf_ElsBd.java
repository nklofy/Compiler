package Parser.ASTs;

import Parser.*;
import Parser.IR.*;
import Parser.TypeSys.*;

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
		String lb_ifend=":"+String.valueOf(codegen.getTmpSn());
		IRCode code=new IRCode("goto",lb_ifend,null,null);
		codegen.getRpsLst(lb_ifend).add(code);
		codegen.addCode(code);
		if(this.if_stmt!=null){
			this.if_stmt.genCode(codegen);
		}
		if(this.stmt_list!=null){
			this.stmt_list.genCode(codegen);
		}
		if(this.sg_stmt!=null){
			this.sg_stmt.genCode(codegen);
		}
		int ln_ifend=codegen.getLineNo()+1;
		codegen.mp_label2line.put(lb_ifend, ln_ifend);
		codegen.replaceLb(lb_ifend);
		codegen.mp_label2line.remove(lb_ifend);
		codegen.rps_code_list.remove(lb_ifend);
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		if(this.if_stmt!=null)
			return this.if_stmt.genSymTb(codegen);
		if(this.stmt_list!=null)
			return this.stmt_list.genSymTb(codegen);
		if(this.sg_stmt!=null)
			return this.sg_stmt.genSymTb(codegen);
		return false;
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
