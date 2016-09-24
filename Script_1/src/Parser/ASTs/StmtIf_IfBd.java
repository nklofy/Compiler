package Parser.ASTs;

import java.util.*;

import Parser.*;
import Parser.IR.*;
import Parser.TypeSys.GenCodeException;
import Parser.TypeSys.GenSymTblException;
import Parser.TypeSys.TypeCheckException;

public class StmtIf_IfBd extends AST {
	ExprCalc_Bool bool_exp;
	AST_StmtList stmt_list;
	Stmt_Sg sg_stmt;	
	
	public boolean setIfBd(ExprCalc_Bool bool_exp,AST_StmtList stmt_list,Stmt_Sg sg_stmt){
		this.bool_exp=bool_exp;
		this.stmt_list=stmt_list;
		this.sg_stmt=sg_stmt;
		return true;
	}
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		codegen.incLineNo();
		this.bool_exp.genCode(codegen);
		IRCode code=new IRCode("if",this.bool_exp.getVal(),null,null);
		codegen.incLineNo();
		codegen.addCode(code);
		int ln_ifbd=codegen.getLineNo()+1;
		codegen.mp_label2line.put(codegen.labels_ifbd.peek(), ln_ifbd);
		codegen.getRpsLst(codegen.labels_ifbd.peek()).add(code);
		if(this.sg_stmt!=null){
			this.sg_stmt.genCode(codegen);
		}else if(this.stmt_list!=null){
			this.stmt_list.genCode(codegen);
		}	
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
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
