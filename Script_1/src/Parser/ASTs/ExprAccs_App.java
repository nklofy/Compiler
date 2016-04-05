package Parser.ASTs;

import Parser.*;
import Parser.IR.*;
import Parser.TypeSys.*;

public class ExprAccs_App extends AST {
	ExprAccs pre_accs;
	Gnrc_ArgLst gnrc_args;
	ExprPri_Var var;
	FuncApp_ArgLst arg_lst;
	String rst_val;
	String ref_type;
	String rst_type;
	
	public void lnkApp(ExprAccs pre_accs,Gnrc_ArgLst g_lst,ExprPri_Var var,FuncApp_ArgLst arg_lst){
		this.pre_accs=pre_accs;
		this.gnrc_args=g_lst;
		this.var=var;
		this.arg_lst=arg_lst;
	}
	public void setApp(Gnrc_ArgLst g_lst, ExprPri_Var var, FuncApp_ArgLst arg_lst){
		this.var=var;
		this.arg_lst=arg_lst;
		this.gnrc_args=g_lst;
	}
	public boolean genCode(CodeGenerator codegen){
		if(this.pre_accs!=null)
			this.pre_accs.genCode(codegen);
		if(this.gnrc_args!=null)
			this.gnrc_args.genCode(codegen);
		if(this.arg_lst!=null)
			this.arg_lst.genCode(codegen);
		IRCode code=new IRCode("call",this.rst_val,this.var.name,String.valueOf(this.arg_lst.size));
		codegen.addCode(code);
		codegen.incLineNo();
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		if(this.pre_accs!=null&&!this.pre_accs.genCode(codegen))
			return false;
		if(this.gnrc_args!=null&&!this.gnrc_args.genSymTb(codegen))
			return false;
		if(this.arg_lst!=null&&this.arg_lst.genSymTb(codegen))
			return false;
		this.rst_val="%"+codegen.getTmpSn();
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		if(this.pre_accs!=null&&!this.pre_accs.checkType(codegen))
			return false;
		if(this.gnrc_args!=null&&!this.gnrc_args.checkType(codegen))
			return false;
		if(this.arg_lst!=null&&!this.arg_lst.checkType(codegen))
			return false;
		R_Function f=codegen.getFuncInSymTb(this.var.name);
		if(f.isMulti()){
			if(f.getMulti().keySet().contains(o)){
				
			}else{
				for(R_Function f1:f.getMulti().values()){
					
				}
			}			
		}else{
			
		}
		return true;
	}
}
