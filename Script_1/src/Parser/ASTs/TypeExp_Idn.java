package Parser.ASTs;

import java.util.*;
import Parser.*;
import Parser.IR.*;
import Parser.TypeSys.*;

public class TypeExp_Idn extends AST {
	TypeExp_Idn type_idn;
	ExprPri_Var var;
	String rst_type;
	R_Package rst_pkg;
	T_Type t_type;
	public boolean setTypeIdn(TypeExp_Idn type_idn,ExprPri_Var var){
		this.var=var;
		this.type_idn=type_idn;
		return true;
	}
	public boolean genCode(CodeGenerator codegen){
		IRCode code=new IRCode("PkgType",this.rst_type,this.var.name,this.rst_pkg.getPkgName());
		codegen.addCode(code);
		codegen.incLineNo();
		return true;
	}
	/* (non-Javadoc)
	 * @see Parser.AST#genSymTb(Parser.CodeGenerator)
	 */
	public boolean genSymTb(CodeGenerator codegen){
		if(this.type_idn!=null){
			if(!this.type_idn.genSymTb(codegen))
				return false;
			if(this.type_idn.rst_pkg==null)
				return false;
			if(this.type_idn.rst_pkg.getSubPkgs().containsKey(this.var.name)){
				this.rst_pkg=this.type_idn.rst_pkg.getSubPkgs().get(var.name);
			}
			if(this.rst_pkg.getTypesInPkg().containsKey(var.name)){
				this.rst_type="@"+codegen.getTmpSn();
				codegen.putTypeInSymTb(this.rst_type, this.rst_pkg.getTypesInPkg().get(var.name));
				this.t_type=codegen.getTypeInSymTb(this.rst_type);
			}
			if(this.rst_pkg==null&&this.rst_type==null)
				return false;			
		}else{
			if(codegen.getPackage(var.name)!=null){
				this.rst_pkg=codegen.getPackage(var.name);				
			}else
				this.rst_type=var.name;
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		if(this.rst_type==null||codegen.getTypeInSymTb(this.rst_type)==null)
			return false;
		return true;
	}
}
