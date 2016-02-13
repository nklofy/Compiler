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
	public void setFuncDef(Gnrc_ParLst gnrc_pars,TypeExp type_exp,ExprPri_Var var,FuncDef_ParLst pars,AST_StmtList stmt_list){
		this.gnrc_pars=gnrc_pars;
		this.type_exp=type_exp;
		this.var=var;
		this.pars=pars;
		this.stmt_list=stmt_list;
		R_Function r=new R_Function();
		r.setFuncDef(this);
		this.addFuncUp(this.var.name);
		this.putFuncTb(this.var.name, r);
		this.upAll(stmt_list);
	}
	public boolean genCode(CodeGenerator codegen){
		return true;
	}
	public boolean checkType(){
		return true;
	}
}