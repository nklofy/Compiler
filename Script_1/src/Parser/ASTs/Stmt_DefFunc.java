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
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		codegen.pushBlock4Sym(this);
		int in=codegen.getLineNo();
		ArrayList<IRCode> old_ir=codegen.getCodeList();
		codegen.setCodeList(new ArrayList<IRCode>());
		codegen.setLineNo(-1);
		this.type_exp.genCode(codegen);
		IRCode code=new IRCode("defFunction",this.name,this.type_exp.rst_type,this.r_func.getFuncSig());
		codegen.addCode(code);
		if(!this.gnrc_pars.isE){
			//code=new IRCode("defGPars",this.gnrc_pars.rst_val,null,null);
			//codegen.addCode(code);
			this.gnrc_pars.genCode(codegen);
		}
		if(!this.pars.isE){
			//code=new IRCode("pushFPars",this.pars.rst_val,null,null);
			//codegen.addCode(code);
			this.pars.genCode(codegen);
		}
		
		if(!this.stmt_list.genCode(codegen))
			return false;
		this.r_func.setFuncBody(codegen.getCodeList());
		codegen.setCodeList(old_ir);
		codegen.setLineNo(in);
		codegen.popBlock4Sym();
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		this.r_func=new R_Function();
		codegen.putFuncInSymTb(this.name, this.r_func);
		codegen.pushBlock4Sym(this);
		if(!this.type_exp.genSymTb(codegen))return false;
		this.t_type=new T_Function();
		this.r_func.setTypeT(this.t_type);
		this.r_func.setFuncName(this.name);
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
		codegen.addtFuncInFile(this.r_func);
		this.stmt_list.genSymTb(codegen);
		codegen.popBlock4Sym();
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		codegen.pushBlock4Sym(this);
		if(!this.type_exp.checkType(codegen))return false;
		this.t_type.setRetType(this.type_exp.rst_type);
		codegen.ret_types.addFirst(this.type_exp.rst_type);
		if(!this.gnrc_pars.isE()&&!this.gnrc_pars.checkType(codegen)){
			return false;
		}
		if(!this.pars.isE()&&this.pars.checkType(codegen)){
			return false;
		}
		this.r_func.setFuncSig(this.t_type.genFuncSig(codegen));
		if(!this.stmt_list.checkType(codegen)){
			return false;
		}
		codegen.popBlock4Sym();
		return true;
	}
}