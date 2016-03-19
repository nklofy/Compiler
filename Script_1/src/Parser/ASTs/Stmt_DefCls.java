package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class Stmt_DefCls extends AST {
	 //scope_info_list class var generic_pars_list extends_list implements_list { member_def_list }
	Scp_InfoLst scp_infolst;
	ExprPri_Var var;
	Gnrc_ParLst gnrc_parlst;
	Extd_Lst extd_lst;
	Impl_Lst impl_lst;
	MbrDef_Lst mbrdef_lst;
	public boolean setClsDef(Scp_InfoLst scp_infolst,ExprPri_Var var,Gnrc_ParLst gnrc_parlst,
			Extd_Lst extd_lst,Impl_Lst impl_lst,MbrDef_Lst mbrdef_lst){
		this.scp_infolst=scp_infolst;
		this.var=var;
		this.gnrc_parlst=gnrc_parlst;
		this.extd_lst=extd_lst;
		this.impl_lst=impl_lst;
		this.mbrdef_lst=mbrdef_lst;
		T_Type r=new T_Type();
		//r.setTypeDef(this);
		this.addTypeUp(this.var.name);
		this.putTypeTb(this.var.name, r);
		return true;
	}
	public boolean genCode(CodeGenerator codegen){
		
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
		return true;
	}
}
