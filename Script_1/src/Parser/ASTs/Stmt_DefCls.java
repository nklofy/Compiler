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
		R_Type r=new R_Type();
		//r.setTypeDef(this);
		this.addTypeUp(this.var.name);
		this.putTypeTb(this.var.name, r);
		return true;
	}
	public boolean genCode(CodeGenerator codegen){
		return true;
	}
	public boolean checkType(){
		return true;
	}
}
