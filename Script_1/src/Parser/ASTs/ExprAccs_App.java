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
	String rst_val;//returned value
	String ref_type;
	String rst_type;
	//String ptr_func;//pointer to function
	String ptr_scp;//search scope for function
	String func_name;
	String func_sig;
	boolean inGType=false;
	boolean isMethod=false;
	boolean isFuncObj=false;
	String func_obj;
	
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
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		if(this.pre_accs!=null)
			this.pre_accs.genCode(codegen);
		IRCode code=null;		
		if(this.gnrc_args!=null){
			this.gnrc_args.genCode(codegen);
		}
		if(this.arg_lst!=null){
			this.arg_lst.genCode(codegen);
		}
		if(isFuncObj){
			code=new IRCode("getFuncObj",this.func_name, this.func_obj,this.ptr_scp);
			codegen.addCode(code);
		}else{
			if(this.ptr_scp=="global"){
				code=new IRCode("getFunc",this.func_name, this.func_sig,this.ptr_scp);
				codegen.addCode(code);
			}else{
				code=new IRCode("getMethod",this.func_name, this.func_sig,this.ptr_scp);
				codegen.addCode(code);
			}			
		}
		/*if(this.ptr_scp.equals("this")){
			code=new IRCode("pushThis",null,null,null);
			codegen.addCode(code);
		}*/
		if(this.gnrc_args!=null){
			for(String g_a :this.gnrc_args.types_name){
				code=new IRCode("pushTypeArg", g_a,null,null);
				codegen.addCode(code);
			}
		}
		if(!this.arg_lst.isE()){
			for(Expr_Calc exp :this.arg_lst.args){
				code=new IRCode("pushFuncArg", exp.rst_val,null,null);
				codegen.addCode(code);
			}
		}
		code=new IRCode("invoke",this.rst_val,null,null);
		codegen.addCode(code);
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		if(this.pre_accs!=null){
			if(!this.pre_accs.genSymTb(codegen))return false;
			if(this.pre_accs.rst_val.equals("this"))
				this.ptr_scp="this";
			this.ptr_scp=this.pre_accs.rst_val;
		}
		if(this.gnrc_args!=null&&!this.gnrc_args.genSymTb(codegen))
			return false;
		if(this.arg_lst!=null&&!this.arg_lst.genSymTb(codegen))
			return false;
		this.rst_val="%"+codegen.getTmpSn();
		this.func_name=this.var.rst_val;
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		if(this.pre_accs!=null&&!this.pre_accs.checkType(codegen))
			return false;
		if(this.gnrc_args!=null&&!this.gnrc_args.checkType(codegen))
			return false;
		if(this.arg_lst!=null&&!this.arg_lst.checkType(codegen))
			return false;
		R_Function f=null;
		R_Variable r=new R_Variable();
		if(this.pre_accs==null){// f()
			f=codegen.getFuncInSymTb(this.var.name);
			if(f==null){
				R_Variable rf=codegen.getVarInSymTb(this.var.name);
				if(rf!=null&&rf.getVarType().equals("function")){
					this.isFuncObj=true;
				}else
					throw new TypeCheckException("Type Check Error: no function "+this.var.name);

			}				
			if(codegen.isInGlobal())
				this.ptr_scp="global";
			else if(codegen.isInScope("class")){
				this.ptr_scp="this";				
			}else
				throw new TypeCheckException("Type Check Error: scope of func app "+this.var.name);
		}else{			//xx.f() 
			if(this.ptr_scp.equals("this")){//this.f()
				if(!codegen.isInScope("class"))
					throw new TypeCheckException("Type Check Error: not in class scope"+this.var.name);
				T_Type t=null;
				if(codegen.getThisCls()!=null){
					t=codegen.getTypeInSymTb(codegen.getThisCls());
				}/*
				else if(codegen.getThisObj()!=null){
					
				}
				T_Type t=codegen.getTypeInSymTb(codegen.getVarInSymTb(codegen.getThisObj()).getVarType());*/
				if(t.getKType()==T_Type.KType.t_cls){
					f=((T_Class)t).getMethods().get(this.func_name);
					if(f==null){
						R_Variable rf=((T_Class)t).getFields().get(this.func_name);
						if(rf!=null&&rf.getVarType().equals("function")){
							this.isFuncObj=true;
						}else
							throw new TypeCheckException("Type Check Error: no function "+this.var.name);
					}				
				}else if(t.getKType()==T_Type.KType.t_intf){
					f=((T_Interface)t).getMethods().get(this.func_name);
					if(f==null){
						throw new TypeCheckException("Type Check Error: no function "+this.var.name);
					}
				}else if(t.getKType()==T_Type.KType.t_gnrc){
					T_Type t1=codegen.getTypeInSymTb(((T_Generic)t).getCoreType());
					f=((T_Class)t1).getMethods().get(this.var.name);
					if(f==null){
						R_Variable rf=((T_Class)t1).getFields().get(this.func_name);
						if(rf!=null&&rf.getVarType().equals("function")){
							this.isFuncObj=true;
						}else
							throw new TypeCheckException("Type Check Error: no function "+this.var.name);
					}
					codegen.gnrc_arg.addFirst(((T_Generic)t).getTypeArgTb());
					this.inGType=true;
				}else
					throw new TypeCheckException("Type Check Error:  "+this.var.name);
			}
			else if(this.pre_accs.rst_type.equals("class")){//A.class.f()
				T_Class t1=((T_Class)codegen.getTypeInSymTb("class"));				
				f=t1.getMethods().get(this.func_name);
				codegen.gnrc_arg.addFirst(new HashMap<String,String>());
				codegen.gnrc_arg.getFirst().put("T", this.ptr_scp);
				this.inGType=true;
			}else if(codegen.getVarInSymTb(this.pre_accs.rst_val)!=null		//a.f()
					||codegen.getTypeInSymTb(this.pre_accs.rst_val)!=null){	//A.f()
				this.ptr_scp=this.pre_accs.rst_val;
				T_Type t=null;
				if(codegen.getVarInSymTb(this.pre_accs.rst_val)!=null)
					t=codegen.getTypeInSymTb(codegen.getVarInSymTb(this.pre_accs.rst_val).getVarType());
				else
					t=codegen.getTypeInSymTb(this.pre_accs.rst_val);
				if(t.getKType()==T_Type.KType.t_cls){
					f=((T_Class)t).getMethods().get(this.func_name);
					if(f==null){
						R_Variable rf=((T_Class)t).getFields().get(this.func_name);
						if(rf!=null&&rf.getVarType().equals("function")){
							this.isFuncObj=true;
						}else
							throw new TypeCheckException("Type Check Error: no function "+this.var.name);
					}
				}else if(t.getKType()==T_Type.KType.t_intf){
					f=((T_Interface)t).getMethods().get(this.func_name);
					if(f==null){
						throw new TypeCheckException("Type Check Error: no function "+this.var.name);
					}
				}else if(t.getKType()==T_Type.KType.t_gnrc){
					T_Type t1=codegen.getTypeInSymTb(((T_Generic)t).getCoreType());
					f=((T_Class)t1).getMethods().get(this.var.name);
					if(f==null){
						R_Variable rf=((T_Class)t1).getFields().get(this.func_name);
						if(rf!=null&&rf.getVarType().equals("function")){
							this.isFuncObj=true;
						}else
							throw new TypeCheckException("Type Check Error: no function "+this.var.name);
					}
					codegen.gnrc_arg.addFirst(((T_Generic)t).getTypeArgTb());
					this.inGType=true;
				}else
					throw new TypeCheckException("Type Check Error:  "+this.var.name);
			}else

				throw new TypeCheckException("Type Check Error:  "+this.var.name);
		}
		T_Function t_f=null;
		LinkedList<String> args1=(this.gnrc_args==null?null:this.gnrc_args.getTypesName());
		LinkedList<String> args2=(this.arg_lst==null?null:this.arg_lst.arg_types);
		if(!f.isMulti()){
			t_f=(T_Function)f.getTypeT();
			if(t_f.isGnrc()){
				LinkedList<String> gps=t_f.getGnrcPars();
				HashMap<String,String>g_types=new HashMap<String,String>();
				if(gps.size()!=args1.size())throw new TypeCheckException("type error: func app "+this.func_name);
				Iterator<String> it1=gps.iterator(),it2=args1.iterator();
				while(it1.hasNext()){
					g_types.put(it1.next(), it2.next());
				}
				codegen.gnrc_arg.addFirst(g_types);
			}
			if(f.isEqArgTypes(codegen, args1, args2)
					||f.isCtArgTypes(codegen, args1, args2)){
				this.rst_type=t_f.getRetType();
				this.func_sig=f.getFuncSig();
			}
			else
				throw new TypeCheckException("Type Check Error:  "+this.var.name);
		}else{
			for(R_Function f1:f.getMulti().values()){
				t_f=(T_Function)f1.getTypeT();
				if(t_f.isGnrc()){
					LinkedList<String> gps=t_f.getGnrcPars();
					HashMap<String,String>g_types=new HashMap<String,String>();
					if(gps.size()!=args1.size())throw new TypeCheckException("type error: func app "+this.func_name);
					Iterator<String> it1=gps.iterator(),it2=args1.iterator();
					while(it1.hasNext()){
						g_types.put(it1.next(), it2.next());
					}
					codegen.gnrc_arg.addFirst(g_types);
					
				}
				if(f1.isEqArgTypes(codegen, args1, args2)
						||f1.isCtArgTypes(codegen, args1, args2)){
					this.rst_type=t_f.getRetType();
					this.func_sig=f1.getFuncSig();
				}						
			}
			if(this.rst_type==null)
				throw new TypeCheckException("Type Check Error:  "+this.var.name);
		}
		r=new R_Variable();
		r.setVarName(this.rst_val);
		if(this.inGType&&codegen.FindGnrcArgTb(this.rst_type)!=null){
			this.rst_type=codegen.FindGnrcArgTb(this.rst_type);			
		}
		
		//TODO T_Generic t=(T_Generic)codegen.getTypeInSymTb(this.rst_type);
	
		r.setVarType(this.rst_type);
		r.setRstVal(this.rst_val);
		codegen.putVarInSymTb(this.rst_val, r);
		if(this.ref_type!=null&&!codegen.getTypeInSymTb(this.ref_type).canAsnFrom(codegen, codegen.getTypeInSymTb(this.rst_type)))
			throw new TypeCheckException("Type Check Error:  "+this.var.name);
		if(t_f.isGnrc())
			codegen.gnrc_arg.remove();
		if(this.inGType)
			codegen.gnrc_arg.remove();
		return true;		
	}
}
