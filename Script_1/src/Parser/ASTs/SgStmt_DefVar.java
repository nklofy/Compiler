package Parser.ASTs;

import Parser.*;
import Parser.IR.IRCode;
import Parser.TypeSys.*;

public class SgStmt_DefVar extends AST {
	SgStmt_DefVar pre_def;
	TypeExp type_exp;
	ExprPri_Var var;
	Expr expr;
	
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
		this.expr = expression;
	}
	public boolean genCode(CodeGenerator codegen){
		if(this.pre_def!=null){
			this.pre_def.genCode(codegen);			
		}
		if(this.type_exp!=null&&this.type_exp.tmp_type==null){
			this.type_exp.genCode(codegen);
		}
		if(this.var!=null){
			this.var.tmp_addr="$"+codegen.getTmpSn();
			this.var.tmp_type=this.type_exp.tmp_type;
		}
		if(this.expr!=null){
			this.expr.genCode(codegen);
			IRCode code=new IRCode("mov",this.var.tmp_addr,this.expr.tmp_rst,this.var.tmp_type,this.expr.tmp_type);
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
		if(this.expr!=null&&!this.expr.checkType(codegen))
			return false;
		if(!codegen.getRTType(this.type_exp.tmp_type).canAsn(codegen.getRTType(this.expr.tmp_type))){
			return false;
		}
		return true;
	}
}
