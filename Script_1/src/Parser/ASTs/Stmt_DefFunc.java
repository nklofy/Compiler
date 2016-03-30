package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class Stmt_DefFunc extends AST {
	//func_def generic_pars_list type_exp var ( par_list ) { stmt_list } 
	Gnrc_ParLst gnrc_pars;
	TypeExp type_exp;
	ExprPri_Var var;
	FuncDef_ParLst pars;
	AST_StmtList stmt_list;
	String name;
	T_Function t_type;//for type checking
	R_Function r_func;
	
	public void setFuncDef(Gnrc_ParLst gnrc_pars,TypeExp type_exp,ExprPri_Var var,FuncDef_ParLst pars,AST_StmtList stmt_list){
		this.gnrc_pars=gnrc_pars;
		this.type_exp=type_exp;
		this.var=var;
		this.name=var.name;
		this.pars=pars;
		this.stmt_list=stmt_list;
		R_Function r=new R_Function();
		r.setFuncDef(this);
	}
	public boolean genCode(CodeGenerator codegen){
		codegen.pushBlock4Sym(this);
		if(!this.stmt_list.genCode(codegen))
			return false;
		codegen.popBlock4Sym();
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		codegen.pushBlock4Sym(this);
		this.r_func=new R_Function();
		this.t_type=new T_Function();
		if(this.gnrc_pars.isE()){
			if(!this.gnrc_pars.genSymTb(codegen))
				return false;
			this.t_type.setGnrc(true);
			this.t_type.setGnrcPars(this.gnrc_pars.pars_name);
		}
		this.stmt_list.genCode(codegen);
		codegen.popBlock4Sym();
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		codegen.pushBlock4Sym(this);
		
		codegen.popBlock4Sym();
		return true;
	}
}