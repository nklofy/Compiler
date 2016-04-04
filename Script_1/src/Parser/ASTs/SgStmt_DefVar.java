package Parser.ASTs;

import java.util.*;

import Parser.*;
import Parser.IR.*;
import Parser.TypeSys.*;

public class SgStmt_DefVar extends AST {
	SgStmt_DefVar pre_def;
	TypeExp type_exp;
	ExprPri_Var var;
	Expr expr;
	LinkedList<R_Variable> r_vars;
	
	public void setPredef(SgStmt_DefVar var_def) {
		this.pre_def = var_def;
		this.type_exp=var_def.type_exp;
	}
	public void setTypeExp(TypeExp type_exp) {
		this.type_exp = type_exp;
	}
	public void setVar(ExprPri_Var var) {
		this.var = var;		
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
			IRCode code=new IRCode("mov",this.var.ref_type,this.var.rst_val,this.expr.rst_val);
			codegen.addCode(code);
			codegen.incLineNo();
		}
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		if(this.pre_def!=null){
			if(!this.pre_def.genSymTb(codegen))
			return false;
			this.r_vars=this.pre_def.r_vars;
		}
		else{
			if(!this.type_exp.genSymTb(codegen))
				return false;
			this.r_vars=new LinkedList<R_Variable>();
		}
		if(codegen.getVarInSymTb(this.var.name)!=null)
			return false;
		R_Variable r=new R_Variable();
		r.setVarType(this.type_exp.rst_type);
		r.setVarName(this.var.name);
		r.setTmpAddr(this.var.rst_val);
		codegen.putVarInSymTb(this.var.name, r);
		this.r_vars.add(r);
		this.var.ref_type=this.type_exp.rst_type;
		if(this.expr!=null)
			this.expr.ref_type=this.var.ref_type;
		return true;
	}
	public boolean checkType(CodeGenerator codegen){	//set var's type and expr's asgn_type 
		if(this.pre_def!=null){
			if(!this.pre_def.checkType(codegen))
				return false;
		}
		else{
			if(!this.type_exp.checkType(codegen))
				return false;
		}
		if(this.expr!=null){
			if(!this.expr.checkType(codegen))
				return false;
		}
		return true;
	}
}
