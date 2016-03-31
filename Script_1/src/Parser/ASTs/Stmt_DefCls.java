package Parser.ASTs;

import java.util.*;
import Parser.*;
import Parser.IR.*;
import Parser.TypeSys.*;

public class Stmt_DefCls extends AST {
	 //scope_info_list class var generic_pars_list extends_list implements_list { member_def_list }
	Scp_InfoLst scp_infolst;
	ExprPri_Var var;
	Gnrc_ParLst gnrc_parlst;
	Cls_Extd_Lst extd_lst;
	Cls_Impl_Lst impl_lst;
	MbrDef_Lst mbrdef_lst;
	String name;
	String rst_type;//for IR code
	T_Class t_type;//for type checking
	
	public boolean setClsDef(Scp_InfoLst scp_infolst,ExprPri_Var var,Gnrc_ParLst gnrc_parlst,
			Cls_Extd_Lst extd_lst,Cls_Impl_Lst impl_lst,MbrDef_Lst mbrdef_lst){
		this.scp_infolst=scp_infolst;
		this.var=var;
		this.name=this.var.name;
		this.gnrc_parlst=gnrc_parlst;
		this.extd_lst=extd_lst;
		this.impl_lst=impl_lst;
		this.mbrdef_lst=mbrdef_lst;
		return true;
	}
	public boolean genCode(CodeGenerator codegen){
		codegen.pushBlock4Sym(this);		
		for(MbrDef f:this.mbrdef_lst.mbrs){
			if(f.mthd!=null){				
				f.mthd.func_def.genCode(codegen);
			}
		}			
		codegen.popBlock4Sym();
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		if(codegen.getTypeInSymTb(this.name)!=null)
			return false;
		this.t_type=new T_Class();
		this.t_type.setKType(T_Type.KType.t_cls);
		codegen.putTypeInSymTb(this.name, this.t_type);
		codegen.pushBlock4Sym(this);		
		if(!this.gnrc_parlst.genSymTb(codegen))
			return false;
		if(!this.extd_lst.genSymTb(codegen))
			return false;
		if(!this.impl_lst.genSymTb(codegen))
			return false;
		if(!this.mbrdef_lst.genSymTb(codegen))
			return false;
		codegen.addTypeInFile(this.t_type);
		if(!this.gnrc_parlst.isE()){
			this.t_type.setGnrc(true);
			this.t_type.setGnrcPars(this.gnrc_parlst.pars_name);			
		}
		if(!this.extd_lst.isE()){
			this.t_type.setExtdTypes(this.extd_lst.extd_types);
		}
		if(!this.impl_lst.isE()){
			this.t_type.setImplTypes(this.impl_lst.extd_types);			
		}
		if(!this.mbrdef_lst.isE()){
			for(R_Function f:this.mbrdef_lst.methods){
				HashMap<String,R_Function> ms=this.t_type.getMethods();
				if(ms.containsKey(f.getFuncName())){
					R_Function r=ms.get(f.getFuncName());
					if(r.isCntnNameType(f)){
						return false;
					}else{
						r.addFuncR(f);
					}					
				}else{
					ms.put(f.getFuncName(), f);
				}
			}
			for(R_Variable f:this.mbrdef_lst.fields){
				for(R_Variable v:this.mbrdef_lst.fields){
					HashMap<String,R_Variable> vs=this.t_type.getFields();
					if(vs.containsKey(v.getVarName())){
						return false;
					}else{
						vs.put(v.getVarName(),v);
					}
				}
			}
		}
		codegen.popBlock4Sym();
		String s=this.name;
		if(this.gnrc_parlst!=null){
			s+="<"+this.gnrc_parlst.pars_name.size()+">";
		}
		this.t_type.setTypeCode(s);
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		codegen.pushBlock4Sym(this);
		if(!this.gnrc_parlst.checkType(codegen))
			return false;
		if(!this.extd_lst.checkType(codegen))
			return false;
		for(String name:this.extd_lst.extd_types){
			T_Type t=codegen.getTypeInSymTb(name);
			if(t==null)
				return false;
			if(t.getKType()!=T_Type.KType.t_cls)
				return false;
		}
		if(!this.impl_lst.checkType(codegen))
			return false;
		for(String name:this.impl_lst.extd_types){
			T_Type t=codegen.getTypeInSymTb(name);
			if(t==null)
				return false;
			if(t.getKType()!=T_Type.KType.t_intf)
				return false;
		}
		if(!this.mbrdef_lst.checkType(codegen))
			return false;
		if(!this.t_type.checkAllImpl(codegen))
			return false;
		if(!this.t_type.checkAllExtd(codegen))
			return false;
		if(!this.t_type.checkAllField(codegen))
			return false;
		if(!this.t_type.checkAllMthd(codegen))
			return false;
		codegen.popBlock4Sym();
		return true;
	}
}
