package Parser.ASTs;

import Parser.*;

public class Stmt_If extends AST {
	StmtIf_IfBd if_body;
	int lb_start;
	int lb_end;
	int lb_ifbd;
	int lb_elsbd;
	StmtIf_ElsBd else_body;
	public boolean setIfStmt(StmtIf_IfBd if_body,StmtIf_ElsBd else_body){
		this.if_body=if_body;
		this.else_body=else_body;
		return true;
	}
	public boolean genCode(CodeGenerator codegen){
		this.if_body.genCode(codegen);	
		this.lb_ifbd=this.if_body.lb_ifbd;
		if(this.else_body==null){
			this.lb_elsbd=this.if_body.lb_end+1;
		}
		else{
			this.else_body.genCode(codegen);
			this.lb_elsbd=this.else_body.lb_start;
			
		}
	
		for(int i:this.if_body.rps_if){
			codegen.replaceLb(i,2,lb_ifbd);
			codegen.replaceLb(i, 3, this.lb_elsbd);
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		if(this.else_body==null){
			return this.if_body.checkType(codegen);
		}
		else{ 
			return this.if_body.checkType(codegen)&&this.else_body.checkType(codegen);
		}
	}
}
