package Parser.ASTs;

import Parser.*;
import Parser.IR.*;
import Parser.TypeSys.*;
import java.util.*;

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
		if(this.gnrc_args!=null){
			code=new IRCode("pushGArgs",this.ptr_func,this.gnrc_args.rst_val,null);
			codegen.addCode(code);
			codegen.incLineNo();
		}
		if(this.arg_lst!=null){
			code=new IRCode("pushFArgs",this.ptr_func,this.arg_lst.rst_val,null);
			codegen.addCode(code);
			codegen.incLineNo();
		}
		if(this.ptr_scp.equals("this")){
			code=new IRCode("pushThis",this.ptr_func,this.ptr_scp,null);
			codegen.addCode(code);
			codegen.incLineNo();
		}
		code=new IRCode("invoke",this.ptr_func,this.rst_val,null);
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
		R_Variable r=new R_Variable();	
		this.ptr_func="*"+codegen.getTmpSn();
		this.rst_val="%"+codegen.getTmpSn();	
		this.func_name=this.var.rst_val;
		//this.rst_type
		//R_Variable		
		//this.ptr_func
		//this.ptr_scp
		//this.func_sig
		if(this.pre_accs==null||this.pre_accs.rst_val.equals("this")){// f()/this.f()
			f=codegen.getFuncInSymTb(this.var.name);
			if(f==null)
				return false;
			if(f.isMethod()){				
				this.ptr_scp="this";
			}else if(this.pre_accs.rst_val.equals("this")){
				return false;
			}			
		}else{//if(this.pre_accs==null)
			if(this.pre_accs.rst_type.equals("class")){//A.class.f()
				T_Generic t=new T_Generic();
				t.setCoreType("class"); //t is class<A>
				LinkedList<String> l=new LinkedList<String>();
				l.add(this.pre_accs.rst_val);
				t.setGnrcPars(l);
				this.ptr_scp="<"+codegen.getTmpSn();
				codegen.putTypeInSymTb(this.ptr_scp, t);
				R_Variable r1=new R_Variable();
				r1.setVarName(this.ptr_scp);
				r1.setVarType("class");
				r1.setTmpAddr(this.ptr_scp);
				codegen.putVarInSymTb(this.ptr_scp, r1);
				T_Class t1=((T_Class)codegen.getTypeInSymTb("class"));
				
				f=.getMethods().get(this.func_name);				
			}else if(codegen.getVarInSymTb(this.pre_accs.rst_val)!=null){//a.f()
				this.ptr_scp=this.pre_accs.rst_val;
				T_Class t=(T_Class) codegen.getTypeInSymTb(codegen.getVarInSymTb(this.pre_accs.rst_val).getVarType());
				f=t.getMethods().get(this.func_name);
			}else if(codegen.getTypeInSymTb(this.pre_accs.rst_val)!=null){//A.f()
				this.ptr_scp=this.pre_accs.rst_val;
				T_Class t=(T_Class) codegen.getTypeInSymTb(this.ptr_scp);
				f=t.getMethods().get(this.func_name);
			}
			else
				return false;
		}
		if(!f.isMulti()){
			if(codegen.checkFuncEx(f, this.gnrc_args.types_name, this.arg_lst.arg_types)
					||codegen.checkFuncCs(f, this.gnrc_args.types_name, this.arg_lst.arg_types)){
				this.rst_type=f.getTypeT().getRetType();
				this.func_sig=f.getFuncSig();
			}
			else
				return false;
		}else{
			for(R_Function f1:f.getMulti().values()){
				if(codegen.checkFuncEx(f1, this.gnrc_args.types_name, this.arg_lst.arg_types)){
					this.rst_type=f1.getTypeT().getRetType();
					this.func_sig=f1.getFuncSig();
				}						
			}
			for(R_Function f1:f.getMulti().values()){
				if(codegen.checkFuncCs(f1, this.gnrc_args.types_name, this.arg_lst.arg_types)){
					this.rst_type=f1.getTypeT().getRetType();
					this.func_sig=f1.getFuncSig();
				}
			}
			if(this.rst_type==null)
				return false;
		}
		r=new R_Variable();
		r.setVarName(this.rst_val);
		r.setVarType(this.rst_type);
		r.setTmpAddr(this.rst_val);
		codegen.putVarInSymTb(this.rst_val, r);
		if(!codegen.canAsn(codegen.getTypeInSymTb(this.ref_type), codegen.getTypeInSymTb(this.rst_type)))
			return false;
		return true;		
	}
}
