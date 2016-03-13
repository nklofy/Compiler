package Parser.ASTs;

import java.util.LinkedList;

import Parser.*;
import Parser.TypeSys.*;

public class TypeExp_Idn extends AST {
	TypeExp_Idn type_idn;
	ExprPri_Var var;
	String ret_type;
	R_Package pck;
	
	public boolean setTypeIdn(TypeExp_Idn type_idn,ExprPri_Var var){
		this.var=var;
		this.type_idn=type_idn;
		return true;
	}
	public boolean genCode(CodeGenerator codegen){
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		boolean b1=true,b2=true;
		if(this.type_idn!=null){
			if(!this.type_idn.checkType(codegen))
				return false;
			if(this.pck.type_inPkg.containsKey(var.name)){
				this.ret_type="@"+codegen.getTmpSn();
				codegen.addRTType(this.ret_type, this.pck.type_inPkg.get(var.name).getTypeValue());
			}else{
				this.pck=this.type_idn.pck.sub_pkgs.get(var.name);
				if(this.pck==null)
					return false;
			}
		}else{
			if(codegen.getRTType(var.name)!=null){
				this.ret_type=var.name;
			}else{
				this.pck=codegen.getPackage(var.name);
				if(this.pck==null){
					return false;
				}
			}
		}		
		return true;
	}
}
