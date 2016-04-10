package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class ExprPri_Var extends AST {
	String name;
	String rst_val;
	String ref_type;
	String rst_type;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		this.rst_val=name;
	}
	public boolean genCode(CodeGenerator codegen){
		
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		R_Variable r=codegen.getVarInSymTb(name);
		if(r!=null){
			this.rst_type=r.getVarType();
			if(!codegen.canAsn(codegen.getTypeInSymTb(this.ref_type), codegen.getTypeInSymTb(this.rst_type)))
				return false;
		}
		return true;
	}

}
