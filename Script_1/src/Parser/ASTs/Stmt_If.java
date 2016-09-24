package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.GenCodeException;
import Parser.TypeSys.GenSymTblException;
import Parser.TypeSys.TypeCheckException;

public class Stmt_If extends AST {
	StmtIf_IfBd if_body;
	StmtIf_ElsBd else_body;
		
	public boolean setIfStmt(StmtIf_IfBd if_body,StmtIf_ElsBd else_body){
		this.if_body=if_body;
		this.else_body=else_body;
		return true;
	}
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		String lb_ifbd=":"+String.valueOf(codegen.getTmpSn());
		codegen.labels_ifbd.addFirst(lb_ifbd);
		String lb_elsbd=":"+String.valueOf(codegen.getTmpSn());
		codegen.labels_elsbd.addFirst(lb_elsbd);
		this.if_body.genCode(codegen);
		int ln_elsbd=codegen.getLineNo()+1;
		codegen.mp_label2line.put(lb_elsbd, ln_elsbd);
		if(this.else_body!=null){
			this.else_body.genCode(codegen);		
		}
		codegen.replaceLb(lb_ifbd);
		codegen.labels_ifbd.remove();
		codegen.labels_elsbd.remove();
		codegen.mp_label2line.remove(lb_ifbd);
		codegen.mp_label2line.remove(lb_elsbd);
		codegen.rps_code_list.remove(lb_ifbd);
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		if(this.else_body==null){
			return this.if_body.checkType(codegen);
		}
		else{ 
			return this.if_body.checkType(codegen)&&this.else_body.checkType(codegen);
		}
	}
}
