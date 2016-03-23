package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class Stmt_DefIntf extends AST {
	//scope_info_list interface var generic_pars_list extends_list { member_def_list } 
	Scp_InfoLst scp_infolst;
	ExprPri_Var var;
	Gnrc_ParLst gnrc_parlst;
	Cls_Extd_Lst extd_lst;
	MbrDef_Lst mbrdef_lst;
	String name;
	String rst_type;//for IR code
	T_Class t_type;//for type checking
	
	public boolean setIntfDef(Scp_InfoLst scp_infolst,ExprPri_Var var,Gnrc_ParLst gnrc_parlst,
			Cls_Extd_Lst extd_lst,MbrDef_Lst mbrdef_lst){
		this.scp_infolst=scp_infolst;
		this.var=var;
		this.gnrc_parlst=gnrc_parlst;
		this.extd_lst=extd_lst;
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
	public boolean checkType(){
		return true;
	}
}
