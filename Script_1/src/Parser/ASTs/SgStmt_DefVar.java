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
	public boolean genCode(CodeGenerator codegen){ //if only def var without assignment, just register on RT
		if(this.pre_def!=null){
			this.pre_def.genCode(codegen);			
		}
		if(this.var!=null&&this.expr!=null){
			this.expr.genCode(codegen);
			this.var.tmp_addr="$"+codegen.getTmpSn();
			IRCode code=new IRCode("cpy",this.var.ref_type,this.var.tmp_addr,this.expr.ret_val);
			codegen.addCode(code);
			codegen.incLineNo();
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen){	//set var's type and expr's asgn_type 
		if(this.pre_def!=null&&!this.pre_def.checkType(codegen))
			return false;
		else{
			if(!this.type_exp.checkType(codegen))
				return false;
		}
		this.var.ref_type=this.type_exp.ret_type;
		this.getVarTb().get(this.var.name).setTypeDef(codegen.getRTType(this.var.ref_type));
		if(this.expr!=null){
			this.expr.ref_type=this.var.ref_type;
			if(!this.expr.checkType(codegen))
				return false;
		}
		return true;
	}
}
