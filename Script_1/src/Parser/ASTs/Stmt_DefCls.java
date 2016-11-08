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
	//String scope="global";
	
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
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		codegen.pushBlock4Sym(this);
		int old_scp=codegen.getScope();
		this.setScope(codegen.addScope("class"));
		codegen.setThisCls(this.name);
		int in=codegen.getLineNo();
		ArrayList<IRCode> old_ir=codegen.getCodeList();
		codegen.setCodeList(new ArrayList<IRCode>());
		codegen.setLineNo(0);
		
		//this.t_type.set.setFuncBody(codegen.getCodeList());
		codegen.setCodeList(old_ir);
		codegen.setLineNo(in);
		//codegen.popBlock4Sym();

		if(!this.mbrdef_lst.isE()){
			for(MbrDef f:this.mbrdef_lst.mbrs){
				if(f.mthd!=null){				
					f.mthd.func_def.genCode(codegen);
				}
			}
		}
				
		codegen.setScope(old_scp);
		codegen.setThisCls(null);
		codegen.popBlock4Sym();
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		if(codegen.getTypeInSymTb(this.name)!=null)
				throw new GenSymTblException("gen sym table error: "+this.var.name);
		this.t_type=new T_Class();
		this.t_type.setTypeName(name);
		this.t_type.setKType(T_Type.KType.t_cls);
		codegen.putTypeInSymTb(this.name, this.t_type);
		codegen.addTypeInFile(this.t_type);
		codegen.pushBlock4Sym(this);	
		int old_scp=codegen.getScope();
		this.setScope(codegen.addScope("class"));
		codegen.setThisCls(this.name);
		if(this.gnrc_parlst!=null&&!this.gnrc_parlst.genSymTb(codegen))
			return false;
		if(!this.gnrc_parlst.isE()){
			this.t_type.setGnrc(true);
			this.t_type.setGnrcPars(this.gnrc_parlst.types_name);			
		}
		if(this.extd_lst!=null&&!this.extd_lst.genSymTb(codegen))
			return false;
		if(!this.extd_lst.isE()){
			this.t_type.setExtdTypes(this.extd_lst.extd_types);
		}
		if(this.impl_lst!=null&&!this.impl_lst.genSymTb(codegen))
			return false;
		if(!this.impl_lst.isE()){
				this.t_type.setImplTypes(this.impl_lst.extd_types);			
			}
		if(!this.mbrdef_lst.isE()){
			if(!this.mbrdef_lst.genSymTb(codegen))	return false;
			//if(!this.mbrdef_lst.isE()){
			HashMap<String,R_Function> ms=this.t_type.getMethods();
			HashMap<String,R_Variable> vs=this.t_type.getFields();
			for(R_Function f:this.mbrdef_lst.methods){

				if(ms.containsKey(f.getFuncName())){
					R_Function r=ms.get(f.getFuncName());
					if(r.isCntnNameType(f)){
						throw new GenSymTblException("symtable error: repetitive method"+f.getFuncName());
					}else{
						r.addFuncR(f);
					}					
				}else{
					ms.put(f.getFuncName(), f);
				}
			}			
			for(R_Variable v:this.mbrdef_lst.fields){
				if(vs.containsKey(v.getVarName())){
					throw new GenSymTblException("symtable error: repetitive field"+v.getVarName());
				}else{
					vs.put(v.getVarName(),v);
				}
			}
			//}	
		}
		//String s=this.name;
		//if(!this.gnrc_parlst.isE){
		//	s+="<"+this.gnrc_parlst.types_name.size()+">";
		//}

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

		if(this.gnrc_parlst!=null&&!this.gnrc_parlst.checkType(codegen))
			return false;
		if(this.extd_lst!=null&&!this.extd_lst.checkType(codegen))
			return false;
		if(!this.extd_lst.isE){
			for(String name:this.extd_lst.extd_types){
				T_Type t=codegen.getTypeInSymTb(name);
				if(t==null)
					throw new TypeCheckException("type check error: "+this.var.name);
				if(t.getKType()!=T_Type.KType.t_cls)
					throw new TypeCheckException("type check error: "+this.var.name);
			}
		}
		if(this.impl_lst!=null&&!this.impl_lst.checkType(codegen))
			return false;
		if(!this.impl_lst.isE){
			for(String name:this.impl_lst.extd_types){
				T_Type t=codegen.getTypeInSymTb(name);
				if(t==null)
					throw new TypeCheckException("type check error: "+this.var.name);
				if(t.getKType()!=T_Type.KType.t_intf)
					throw new TypeCheckException("type check error: "+this.var.name);
			}
		}
		
		if(!this.t_type.checkAllImpl(codegen))
			return false;
		if(!this.t_type.checkAllExtd(codegen))
			return false;
		
			
		
		
		if(this.mbrdef_lst!=null&&!this.mbrdef_lst.checkType(codegen)){
			
			return false;
		}
	//	if(!this.t_type.checkAllField(codegen))//skip these and spec them undefined actions
	//		throw new TypeCheckException("type check error: all fields "+this.name);
	//	if(!this.t_type.checkAllMthd(codegen))
	//		throw new TypeCheckException("type check error: all methods "+this.name);
		this.t_type.genTypeSig(codegen);
		codegen.setScope(old_scp);
		codegen.setThisCls(null);
		codegen.popBlock4Sym();
		return true;
	}
}
