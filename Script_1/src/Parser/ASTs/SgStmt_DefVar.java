package Parser.ASTs;

import Parser.*;
import Parser.IR.IRCode;
import Parser.TypeSys.*;

public class SgStmt_DefVar extends AST {
	SgStmt_DefVar pre_def;
	TypeExp type_exp;
	ExprPri_Var var;
	Expr expression;
	
	public void setPredef(SgStmt_DefVar var_def) {
		this.pre_def = var_def;
		this.type_exp=var_def.type_exp;
		this.setVarTb(var_def.getVarTb());
		this.setVarUp(var_def.getVarUp());
	}
	public void setTypeExp(TypeExp type_exp) {
		this.type_exp = type_exp;
	}
	public void setVar(ExprPri_Var var) {
		this.var = var;
		R_Variable r=new R_Variable();
		this.addVarUp(this.var.name);
		this.putVarTb(this.var.name, r);
	}
	public void setExpr(Expr expression) {
		this.expression = expression;
	}
	public boolean genCode(CodeGenerator codegen){
		if(this.pre_def!=null){
			this.pre_def.genCode(codegen);			
		}
		if(this.type_exp!=null&&this.type_exp.tmp_tpname==null){
			this.type_exp.genCode(codegen);
		}
		if(this.var!=null){
			IRCode code=new IRCode("new",this.type_exp.tmp_tpname,this.var.tmp_addr,null);
			codegen.addCode(code);
			codegen.incLineNo();
		}
		if(this.expression!=null){
			this.expression.genCode(codegen);
			IRCode code=new IRCode("store",this.expression.tmp_rst,this.var.tmp_addr,null);
			codegen.addCode(code);
			codegen.incLineNo();
		}
		
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		if(this.pre_def!=null&&!this.pre_def.checkType(codegen))
			return false;
		if(this.type_exp!=null&&!this.type_exp.checkType(codegen))
			return false;
		if(this.var!=null&&!this.var.checkType(codegen))
			return false;
		if(this.expression!=null&&!this.expression.checkType(codegen))
			return false;
		return true;
	}
}
