package Parser.ASTs;

import java.util.*;
import Parser.*;
import Parser.IR.*;
import Parser.TypeSys.*;

public class Expr_Lmbd extends AST {
	FuncDef_ParLst pars;
	AST_StmtList stmt_list;
	R_Function r_func;
	T_Function t_type;
	String name;
	String rst_val;//return value of calculation
	String rst_type;//return type of calculation
	String ref_type;//reference type for assignment
	
	public void setLmbd(FuncDef_ParLst par_lst,AST_StmtList stmt_list){
		this.pars=par_lst;
		this.stmt_list=stmt_list;
	}
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		codegen.pushBlock4Sym(this);
		int old_scp=codegen.getScope();
		this.setScope(codegen.addScope("lambda"));
		IRCode code=new IRCode("defLambdaExp",this.rst_val,this.t_type.genFuncSig(codegen),null);
		codegen.addCode(code);
		if(!this.pars.isE){
			this.pars.genCode(codegen);
		}
		if(!this.stmt_list.genCode(codegen))
			return false;
		codegen.setScope(old_scp);
		codegen.popBlock4Sym();
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		this.name="$"+codegen.getTmpSn();
		this.rst_val=this.name;
		this.rst_type="function";
		this.r_func=new R_Function();
		codegen.putFuncInSymTb(this.name, this.r_func);
	/*	if(codegen.isInScope("global")){
			codegen.addtFuncInFile(this.r_func);
			this.r_func.setScope("global");
		}*/
		codegen.pushBlock4Sym(this);
		int old_scp=codegen.getScope();
		this.setScope(codegen.addScope("lambda"));
		this.t_type=new T_Function();
		this.r_func.setTypeT(this.t_type);
		this.r_func.setFuncName(this.name);
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
		if(!this.stmt_list.genSymTb(codegen)) return false;
		codegen.setScope(old_scp);
		codegen.popBlock4Sym();
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		codegen.pushBlock4Sym(this);
		int old_scp=codegen.getScope();
		this.setScope(codegen.addScope("lambda"));
		if(!this.pars.isE()&&this.pars.checkType(codegen)){
			return false;
		}
		if(!this.stmt_list.checkType(codegen)){
			return false;
		}
		this.t_type.setRetType(codegen.ret_types.remove().getTypeSig());
		this.r_func.setFuncSig(this.t_type.genFuncSig(codegen));
		codegen.setScope(old_scp);
		codegen.popBlock4Sym();
		return true;
	}
}
