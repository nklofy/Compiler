package Parser.ASTs;

import java.util.HashMap;

import Parser.*;
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
		this.gnrc_parlst=gnrc_parlst;
		this.extd_lst=extd_lst;
		this.impl_lst=impl_lst;
		this.mbrdef_lst=mbrdef_lst;
		T_Type r=new T_Type();
		//r.setTypeDef(this);
		//this.addTypeUp(this.var.name);
		this.putTypeTb(this.var.name, r);
		return true;
	}
	public boolean genCode(CodeGenerator codegen){
		
		return true;
	}
	public boolean upSymTbl(CodeGenerator codegen){
		this.name=this.var.name;
		
		
		
		
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		if(!this.var.checkType(codegen))
			return false;
		if(this.gnrc_parlst!=null&&!this.gnrc_parlst.checkType(codegen))
			return false;
		if(this.extd_lst!=null&&!this.extd_lst.checkType(codegen))
			return false;
		if(this.impl_lst!=null&&!this.impl_lst.checkType(codegen))
			return false;
		if(this.mbrdef_lst!=null&&!this.mbrdef_lst.checkType(codegen))
			return false;
		T_Class cls_type=new T_Class();
		codegen.addTypeInFile(cls_type);
		if(this.gnrc_parlst!=null){
			cls_type.setGnrc(true);
			cls_type.setGnrcPars(this.gnrc_parlst.pars_name);			
		}
		if(this.extd_lst!=null){
			cls_type.setExtdTypes(this.extd_lst.extd_types);
		}
		if(this.impl_lst!=null){
			cls_type.setImplTypes(this.impl_lst.impl_types);			
		}
		if(this.mbrdef_lst!=null){
			cls_type.setMethods(this.mbrdef_lst.methods);
			cls_type.setFields(this.mbrdef_lst.fields);
		}
		for(T_Interface i:this.impl_lst.impl_types){
			HashMap<String, R_Function> fs=i.getMethods();
			for(String s:fs.keySet()){
				R_Function f=this.mbrdef_lst.methods.get(s);
				if(f==null)
					return false;
				if(!fs.get(s).isMulti() && !f.isMulti()){
					if(!fs.get(s).getTypeT().isEqType(f.getTypeT()))
						return false;
				}else if(fs.get(s).isMulti()&&f.isMulti()){
					boolean hadFdAll=false;
					for(R_Function r:fs.get(s).getMulti()){
						hadFdAll=false;
						for(R_Function r1:f.getMulti()){
							if(r.getTypeT().isEqType(r1.getTypeT())) hadFdAll=true;
						}
						if(!hadFdAll) return false;
					}
				}else{
					return false;
				}
			}
		}
		return true;
	}
}
