package Parser.ASTs;

import java.util.HashMap;

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
	T_Interface t_type;//for type checking
	
	public boolean setIntfDef(Scp_InfoLst scp_infolst,ExprPri_Var var,Gnrc_ParLst gnrc_parlst,
			Cls_Extd_Lst extd_lst,MbrDef_Lst mbrdef_lst){
		this.scp_infolst=scp_infolst;
		this.var=var;
		this.name=this.var.name;
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
	public boolean genSymTb(CodeGenerator codegen){
		if(codegen.getTypeInSymTb(this.name)!=null)
			return false;
		this.t_type=new T_Interface();
		codegen.putTypeInSymTb(this.name, this.t_type);
		codegen.pushBlock4Sym(this);
		if(this.gnrc_parlst!=null&&!this.gnrc_parlst.genSymTb(codegen))
			return false;
		if(this.extd_lst!=null&&!this.extd_lst.genSymTb(codegen))
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
		if(this.mbrdef_lst!=null){
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
			if(this.mbrdef_lst.fields!=null){
				return false;
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
		if(this.gnrc_parlst!=null&&!this.gnrc_parlst.checkType(codegen))
			return false;
		if(this.extd_lst!=null&&!this.extd_lst.checkType(codegen))
			return false;
		for(String name:this.extd_lst.extd_types){
			T_Type t=codegen.getTypeInSymTb(name);
			if(t==null)
				return false;
			if(t.getKType()!=T_Type.KType.t_intf)
				return false;
		}
		if(this.mbrdef_lst!=null&&!this.mbrdef_lst.checkType(codegen))
			return false;
		if(!this.t_type.checkAllExtd(codegen))
			return false;
		if(!this.t_type.checkAllMthd(codegen))
			return false;
		codegen.popBlock4Sym();
		return true;
	}
}
