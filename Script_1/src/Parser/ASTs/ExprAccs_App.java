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
	String ptr_func;//pointer to function
	String ptr_scp;//search scope for function
	String func_name;
	String func_sig;
	
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
		IRCode code=null;
		if(this.ptr_scp!=null){
			code=new IRCode("getFunc",this.ptr_func,this.ptr_scp,this.func_name+":"+this.func_sig);
			codegen.addCode(code);
			codegen.incLineNo();
		}
		//getMethod TODO
		code=new IRCode("invoke",this.rst_val,this.ptr_func,String.valueOf(this.gnrc_args.size+this.arg_lst.size));
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
		R_Function f=null;
		if(this.pre_accs==null){
			f=codegen.getFuncInSymTb(this.var.name);
			if(f.isMethod()){
				this.ptr_func="*"+f.getFuncName();
				this.ptr_scp="this";
			}
			if(codegen.checkFuncEx(f, this.gnrc_args.types_name,this.arg_lst.arg_types)){
								
				return true;
			}				
		}else{
			if(this.pre_accs.rst_type.equals("class")){
				
			}else if(this.pre_accs.rst_val.equals("this")){
				
			}else if(codegen.getVarInSymTb(this.pre_accs.rst_val)!=null){
				
			}else
				return false;
			
		}
		
		if(f.isMulti()){
			for(R_Function f1:f.getMulti().values()){
				if(codegen.checkFuncEx(f1, this.gnrc_args.gnrc_args, this.arg_lst.arg_types))
					return true;	
			}
			for(R_Function f1:f.getMulti().values()){
				if(codegen.checkFuncCs(f1, this.gnrc_args.gnrc_args, this.arg_lst.arg_types))
					return true;	
			}
		}else{
			if(codegen.checkFuncEx(f, this.gnrc_args.gnrc_args, this.arg_lst.arg_types))
				this.rst_val=f.getFuncName();
		}
		return true;
	}
}
