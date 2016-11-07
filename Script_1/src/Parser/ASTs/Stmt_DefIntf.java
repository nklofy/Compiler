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
		return true;
	}
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		//codegen.pushBlock4Sym(this);
		
		//codegen.popBlock4Sym();
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		if(codegen.getTypeInSymTb(this.name)!=null)
				throw new GenSymTblException("gensymtable error: existing type "+this.name);
		this.t_type=new T_Interface();
		this.t_type.setTypeName(this.name);
		codegen.putTypeInSymTb(this.name, this.t_type);
		codegen.addTypeInFile(this.t_type);
		codegen.pushBlock4Sym(this);
		int old_scp=codegen.getScope();
		this.setScope(codegen.addScope("class"));
		codegen.setThisCls(this.name);
		if(!this.gnrc_parlst.genSymTb(codegen))
			return false;
		if(!this.extd_lst.genSymTb(codegen))
			return false;		
		if(!this.mbrdef_lst.genSymTb(codegen))
			return false;
		if(!this.gnrc_parlst.isE()){
			this.t_type.setKType(T_Type.KType.t_gnrc);
			this.t_type.setGnrcPars(this.gnrc_parlst.types_name);			
		}
		if(!this.extd_lst.isE()){
			this.t_type.setExtdTypes(this.extd_lst.extd_types);
		}
		if(!this.mbrdef_lst.isE()){
			for(R_Function f:this.mbrdef_lst.methods){
				HashMap<String,R_Function> ms=this.t_type.getMethods();
				if(ms.containsKey(f.getFuncName())){
					R_Function r=ms.get(f.getFuncName());
					if(r.isCntnNameType(f)){
						throw new GenSymTblException("type error: "+this.name+" method "+f.getFuncName());
					}else{
						r.addFuncR(f);
					}					
				}else{
					ms.put(f.getFuncName(), f);
				}
				f.setDummy(true);
			}
			if(!this.mbrdef_lst.fields.isEmpty()){
				throw new GenSymTblException("type error: "+this.name+" fields");
			}
		}
		this.t_type.genTypeSig(codegen);
		codegen.setThisCls(null);
		codegen.setScope(old_scp);
		codegen.popBlock4Sym();
		
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		codegen.pushBlock4Sym(this);
		int old_scp=codegen.getScope();
		this.setScope(codegen.addScope("class"));
		codegen.setThisCls(this.name);
		
		if(!this.gnrc_parlst.checkType(codegen))
			return false;
		if(!this.extd_lst.checkType(codegen))
			return false;
		if(!this.extd_lst.isE()){
		for(String name:this.extd_lst.extd_types){
			T_Type t=codegen.getTypeInSymTb(name);
			if(t==null)
				return false;
			if(t.getKType()!=T_Type.KType.t_intf)
				return false;
		}
	}
		if(!this.mbrdef_lst.checkType(codegen))
			return false;
		//if(!this.t_type.checkAllExtd(codegen))
		//	return false;
		//if(!this.t_type.checkAllMthd(codegen))
		//	return false;
		codegen.setScope(old_scp);
		codegen.setThisCls(null);
		codegen.popBlock4Sym();
		return true;
	}
}
