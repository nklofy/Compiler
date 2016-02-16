package Parser.ASTs;

import java.util.*;

import Parser.*;
import Parser.IR.*;

public class StmtIf_IfBd extends AST {
	ExprCalc_Bool bool_exp;
	AST_StmtList stmt_list;
	Stmt_Sg sg_stmt;
	
	int lb_start;
	int lb_end;
	int lb_ifbd;
	LinkedList<Integer> rps_if;
	
	public boolean setIfBd(ExprCalc_Bool bool_exp,AST_StmtList stmt_list,Stmt_Sg sg_stmt){
		this.bool_exp=bool_exp;
		this.stmt_list=stmt_list;
		this.sg_stmt=sg_stmt;
		return true;
	}
	public boolean genCode(CodeGenerator codegen){
		this.bool_exp.genCode(codegen);
		IRCode code=new IRCode("if","%"+String.valueOf(codegen.getTmpSn()),null,null);
		codegen.addCode(code);
		codegen.incLineNo();
		this.rps_if.add(codegen.getLineNo());
		this.lb_ifbd=codegen.getLineNo()+1;
		if(this.sg_stmt!=null){
			this.sg_stmt.genCode(codegen);
		}else if(this.stmt_list!=null){
			this.stmt_list.genCode(codegen);
		}
		this.lb_end=codegen.getLineNo();
		
		
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		if(!this.bool_exp.checkType(codegen)){
			return false;
		}
		if(this.sg_stmt!=null){
			return this.sg_stmt.checkType(codegen);
		}
		else if(this.stmt_list!=null){
			return this.stmt_list.checkType(codegen);
		}
		return false;
	}
}
