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
		int old_scp=codegen.getScope();
		this.setScope(codegen.addScope("function"));
		int in=codegen.getLineNo();
		ArrayList<IRCode> old_ir=codegen.getCodeList();
		codegen.setCodeList(new ArrayList<IRCode>());
		codegen.setLineNo(0);
		//this.type_exp.genCode(codegen);
		//IRCode code=new IRCode("defFunction",this.name,this.r_func.getFuncSig(),this.scope);
		//codegen.addCode(code);
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
		codegen.setScope(old_scp);
		codegen.popBlock4Sym();
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		this.r_func=new R_Function();
		if(codegen.isInGlobal()){
			codegen.addtFuncInFile(this.r_func);
			this.r_func.setScope("global");
		}
		codegen.pushBlock4Sym(this);
		int old_scp=codegen.getScope();
		this.setScope(codegen.addScope("function"));
		if(!this.type_exp.genSymTb(codegen))return false;
		this.t_type=new T_Function();
		this.r_func.setTypeT(this.t_type);
		this.r_func.setFuncName(this.name);
		//this.r_func.setScope(codegen.getScopeStr());
		if(!this.gnrc_pars.isE()){
			if(!this.gnrc_pars.genSymTb(codegen))
				return false;
			this.t_type.setGnrc(true);
			this.t_type.setGnrcPars(this.gnrc_pars.types_name);
			//for(String s:this.gnrc_pars.types_name){
			//	T_Type t=new T_Type();
			//	t.setDummy();t.setTypeName(s);
			//	codegen.putTypeInSymTb(s, t);
			//}
		}
		if(!this.pars.isE()){
			if(!this.pars.genSymTb(codegen))
				return false;
			//this.t_type.setParTypes(this.pars.pars_type);
			this.r_func.setParsName(this.pars.pars_name);
			for(int i=0;i<this.pars.pars_name.size();i++){
				String s=this.pars.pars_name.get(i);
				R_Variable r=new R_Variable();
				r.setDummy();
				//r.setVarType(this.pars.pars_type.get(i));
				codegen.putVarInSymTb(s, r);
			}
		}
		codegen.popBlock4Sym();		
		R_Function r=codegen.getFuncTopSymTb(this.name);
		if(r!=null&&r.isCntnNameType(this.r_func))
			throw new GenSymTblException("gensymtable error: define function"+this.name+":"+this.r_func.getFuncSig());
		codegen.putFuncInSymTb(this.name, this.r_func);
		codegen.pushBlock4Sym(this);
		if(!this.stmt_list.genSymTb(codegen)) return false;
		codegen.setScope(old_scp);
		codegen.popBlock4Sym();
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		codegen.pushBlock4Sym(this);
		int old_scp=codegen.getScope();
		this.setScope(codegen.addScope("function"));
		if(!this.type_exp.checkType(codegen))return false;
		codegen.ret_types.addFirst(codegen.getTypeInSymTb(this.type_exp.rst_type));
		codegen.putTypeInAllFileTb(this.type_exp.rst_type, codegen.getTypeInSymTb(this.type_exp.rst_type));
		this.t_type.setRetType(this.type_exp.rst_type);
		if(!this.gnrc_pars.isE()&&!this.gnrc_pars.checkType(codegen)){
			return false;
		}
		if(!this.pars.isE()){
			if(!this.pars.checkType(codegen))
				return false;
			this.t_type.setParTypes(this.pars.pars_type);
			for(int i=0;i<this.pars.pars_name.size();i++){
				String s=this.pars.pars_name.get(i);
				R_Variable r=codegen.getVarInSymTb(s);
				r.setVarType(this.pars.pars_type.get(i));
			}
		}
		this.r_func.setFuncSig(this.t_type.genFuncSig(codegen));
		if(!this.stmt_list.checkType(codegen)){
			return false;
		}
		/*
		R_Function f=codegen.getFuncTopSymTb(this.name);
		if(f.isMulti()){
			int c1=0;
			for(R_Function f1:f.getMulti().values()){
				if(f1.isEqNameType(this.r_func))
					c1++;
			}
			if(c1>1) throw new TypeCheckException("error type checking: function type repetitility "+this.name);
		}*/
		codegen.ret_types.remove();
		codegen.setScope(old_scp);
		codegen.popBlock4Sym();
		return true;
	}
}