package Parser.ASTs;

import java.util.ArrayList;

import Parser.*;
import Parser.IR.IRCode;
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
		int in=codegen.getLineNo();
		ArrayList<IRCode> old_ir=codegen.getCodeList();
		codegen.setCodeList(new ArrayList<IRCode>());
		codegen.setLineNo(-1);
		
		if(this.gnrc_pars!=null){
			IRCode code=new IRCode("pushGPars",this.ptr_func,this.gnrc_pars.rst_val,null);
			codegen.addCode(code);
			codegen.incLineNo();
		}
		if(this.arg_lst!=null){
			IRCode code=new IRCode("pushFPars",this.ptr_func,this.pars.rst_val,null);
			codegen.addCode(code);
			codegen.incLineNo();
		}
		
		if(!this.stmt_list.genCode(codegen))
			return false;
		this.r_func.setFuncBody(codegen.getCodeList());
		codegen.setCodeList(old_ir);
		codegen.setLineNo(in);
		codegen.popBlock4Sym();
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		codegen.pushBlock4Sym(this);
		this.r_func=new R_Function();
		this.t_type=new T_Function();
		this.r_func.setTypeT(this.t_type);
		if(!this.gnrc_pars.isE()){
			if(!this.gnrc_pars.genSymTb(codegen))
				return false;
			this.t_type.setGnrc(true);
			this.t_type.setGnrcPars(this.gnrc_pars.types_name);
			for(String s:this.gnrc_pars.types_name){
				T_Type t=new T_Type();
				t.setDummy();t.setTypeName(s);
				codegen.putTypeInSymTb(s, t);
			}
		}
		if(!this.pars.isE()){
			if(!this.pars.genSymTb(codegen))
				return false;
			this.t_type.setParTypes(this.pars.pars_type);
			this.r_func.setParsName(this.pars.pars_name);
			for(int i=0;i<this.pars.pars_name.size();i++){
				String s=this.pars.pars_name.get(i);
				R_Variable r=new R_Variable();
				r.setDummy();
				r.setVarType(this.pars.pars_type.get(i));
				codegen.putVarInSymTb(s, r);
			}
		}		
		codegen.putFuncInSymTb(this.name, this.r_func);
		this.stmt_list.genSymTb(codegen);
		codegen.popBlock4Sym();
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		codegen.pushBlock4Sym(this);
		if(!this.gnrc_pars.isE()&&!this.gnrc_pars.checkType(codegen)){
			return false;
		}
		if(!this.pars.isE()&&this.pars.checkType(codegen)){
			return false;
		}
		if(!this.stmt_list.checkType(codegen)){
			return false;
		}
		codegen.popBlock4Sym();
		return true;
	}
}