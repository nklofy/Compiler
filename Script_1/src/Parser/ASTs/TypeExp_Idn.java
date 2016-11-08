package Parser.ASTs;

import java.util.*;
import Parser.*;
import Parser.IR.*;
import Parser.TypeSys.*;

public class TypeExp_Idn extends AST {
	TypeExp_Idn type_idn;
	ExprPri_Var var;
	String rst_type;
	String rst_pkg;
	T_Type t_type;
	
	public boolean setTypeIdn(TypeExp_Idn type_idn,ExprPri_Var var){
		this.var=var;
		this.type_idn=type_idn;
		return true;
	}
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		if(this.type_idn!=null){
			//IRCode code=new IRCode("PkgType",this.rst_type,this.var.name,this.rst_pkg);
			//codegen.addCode(code);
		}
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		if(this.type_idn!=null){
			if(!this.type_idn.genSymTb(codegen))
				return false;
			if(this.type_idn.rst_pkg==null)
					throw new GenSymTblException("gensymtable error: "+this.type_idn.rst_type+" "+this.var.name);	
			this.rst_pkg=this.type_idn.rst_pkg+"."+this.var.name;
		}else{
			if(!this.var.genSymTb(codegen))return false;
			this.rst_type=var.name;
			this.rst_pkg=var.name;
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		if(this.type_idn==null){
			if(this.rst_type==null||codegen.getTypeInSymTb(this.rst_type)==null)
				return false;
			this.t_type=codegen.getTypeInSymTb(this.rst_type);
			if(this.t_type==null)
				return false;
		}else{
			this.rst_type=this.rst_pkg;
			//check if package exists TODO
			this.t_type=codegen.getTypeInSymTb(this.rst_type);
			if(this.t_type==null)
				return false;
			codegen.putTypeInSymTb(this.rst_type, this.t_type);
		}
		
		return true;
	}
}
