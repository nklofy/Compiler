package Parser.ASTs;

import java.util.*;
import Parser.*;
import Parser.IR.*;
import Parser.TypeSys.*;

public class TypeExp_Idn extends AST {
	TypeExp_Idn type_idn;
	ExprPri_Var var;
	String ret_type;
	R_Package r_pkg;
	
	public boolean setTypeIdn(TypeExp_Idn type_idn,ExprPri_Var var){
		this.var=var;
		this.type_idn=type_idn;
		return true;
	}
	public boolean genCode(CodeGenerator codegen){
		IRCode code=new IRCode("PkgType",this.ret_type,this.var.name,this.r_pkg.getPkgName());
		codegen.addCode(code);
		codegen.incLineNo();
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		if(this.type_idn!=null){
			if(!this.type_idn.checkType(codegen))
				return false;
			if(this.r_pkg.getTypeInPkg().containsKey(var.name)){
				this.ret_type="@"+codegen.getTmpSn();
				codegen.addRTType(this.ret_type, this.r_pkg.getTypeInPkg().get(var.name));
			}else{
				this.r_pkg=this.type_idn.r_pkg.getSubPkgs().get(var.name);
				if(this.r_pkg==null)
					return false;
			}
		}else{
			if(codegen.getRTType(var.name)!=null){
				this.ret_type=var.name;
			}else{
				this.r_pkg=codegen.getPackage(var.name);
				if(this.r_pkg==null){
					return false;
				}
			}
		}		
		return true;
	}
}
