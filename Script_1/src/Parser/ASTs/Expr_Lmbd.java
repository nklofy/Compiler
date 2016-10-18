package Parser.ASTs;

import java.util.*;
import Parser.*;
import Parser.IR.*;
import Parser.TypeSys.*;

public class Expr_Lmbd extends AST {
	FuncDef_ParLst par_lst;
	AST_StmtList stmt_list;
	R_Function r_func;
	T_Function t_type;
	String name;
	String rst_val;//return value of calculation
	String rst_type;//return type of calculation
	String ref_type;//reference type for assignment
	
	public void setLmbd(FuncDef_ParLst par_lst,AST_StmtList stmt_list){
		this.par_lst=par_lst;
		this.stmt_list=stmt_list;
	}
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		codegen.pushBlock4Sym(this);
		int in=codegen.getLineNo();
		ArrayList<IRCode> old_ir=codegen.getCodeList();
		codegen.setCodeList(new ArrayList<IRCode>());
		codegen.setLineNo(-1);
		if(!this.stmt_list.genCode(codegen))
			return false;
		this.r_func.setFuncBody(codegen.getCodeList());
		codegen.setCodeList(old_ir);
		codegen.setLineNo(in);
		codegen.popBlock4Sym();
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		this.name="*"+codegen.getTmpSn();
		codegen.pushBlock4Sym(this);
		codegen.putFuncInSymTb(this.name, this.r_func);
		this.r_func=new R_Function();
		this.t_type=new T_Function();
		this.r_func.setTypeT(this.t_type);		
		if(!this.par_lst.isE()){
			if(!this.par_lst.genSymTb(codegen))
				return false;
			this.t_type.setParTypes(this.par_lst.pars_type);
			this.r_func.setParsName(this.par_lst.pars_name);
			for(int i=0;i<this.par_lst.pars_name.size();i++){
				String s=this.par_lst.pars_name.get(i);
				R_Variable r=new R_Variable();
				r.setDummy();
				r.setVarType(this.par_lst.pars_type.get(i));
				codegen.putVarInSymTb(s, r);
			}
		}	
		this.stmt_list.genSymTb(codegen);
		codegen.popBlock4Sym();
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		codegen.pushBlock4Sym(this);		
		if(!this.par_lst.isE()&&this.par_lst.checkType(codegen)){
			return false;
		}
		if(!this.stmt_list.checkType(codegen)){
			return false;
		}
		codegen.popBlock4Sym();
		return true;
	}
}
