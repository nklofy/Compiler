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
	public boolean genCode(CodeGenerator codegen){
		//TODO
		this.if_body.genCode(codegen);		
		if(this.else_body!=null){
			this.else_body.genCode(codegen);		
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		if(!this.if_body.isMerged()){
			if(this.else_body==null){
				return this.if_body.checkType(codegen);
			}
			else if(!this.else_body.isMerged()){
				return this.if_body.checkType(codegen)&&this.else_body.checkType(codegen);
			}
		}
		if(this.if_body.isMerged()){
			this.if_body=(StmtIf_IfBd)this.if_body.getDeMrg(codegen);
			if(this.if_body==null)
				return false;
		}
		if(this.else_body.isMerged()){
			this.else_body=(StmtIf_ElsBd)this.else_body.getDeMrg(codegen);
			if(this.else_body==null)
				return false;
		}
		return true;
	}
}
