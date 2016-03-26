package Parser.ASTs;

import java.util.*;
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
		this.name=this.var.name;
		this.gnrc_parlst=gnrc_parlst;
		this.extd_lst=extd_lst;
		this.impl_lst=impl_lst;
		this.mbrdef_lst=mbrdef_lst;
		return true;
	}
	public boolean genCode(CodeGenerator codegen){
		
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		if(codegen.getTypeInSymTb(this.name)!=null)
			return false;
		this.t_type=new T_Class();
		codegen.putTypeInSymTb(this.name, this.t_type);
		codegen.pushBlock4Sym(this);		
		if(this.gnrc_parlst!=null&&!this.gnrc_parlst.genSymTb(codegen))
			return false;
		if(this.extd_lst!=null&&!this.extd_lst.genSymTb(codegen))
			return false;
		if(this.impl_lst!=null&&!this.impl_lst.genSymTb(codegen))
			return false;
		if(this.mbrdef_lst!=null&&!this.mbrdef_lst.genSymTb(codegen))
			return false;
		codegen.addTypeInFile(this.t_type);
		if(this.gnrc_parlst!=null){
			this.t_type.setKType(T_Type.KType.t_gnrc);
			this.t_type.setGnrcPars(this.gnrc_parlst.pars_name);			
		}
		if(this.extd_lst!=null){
			this.t_type.setExtdTypes(this.extd_lst.extd_types);
		}
		if(this.impl_lst!=null){
			this.t_type.setImplTypes(this.impl_lst.impl_types);			
		}
		if(this.mbrdef_lst!=null){
			this.t_type.setMethods(this.mbrdef_lst.methods);
			this.t_type.setFields(this.mbrdef_lst.fields);
		}
		codegen.popBlock4Sym();
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		codegen.pushBlock4Sym(this);
		//check if implementing all the implements
		//check if there is name error or conflict
		
		if(this.gnrc_parlst!=null&&!this.gnrc_parlst.checkType(codegen))
			return false;
		if(this.extd_lst!=null&&!this.extd_lst.checkType(codegen))
			return false;
		if(this.impl_lst!=null&&!this.impl_lst.checkType(codegen))
			return false;
		if(this.mbrdef_lst!=null&&!this.mbrdef_lst.checkType(codegen))
			return false;
		LinkedList<T_Interface> impls=this.impl_lst.impl_types;1234
		for(T_Interface i:this.impl_lst.impl_types){
			for(T_Function f:i.getMethods()){
				if(!this.mbrdef_lst.methods.contains(f)){
					return false;
				}
			}
		}
		codegen.popBlock4Sym();
		return true;
	}
}
