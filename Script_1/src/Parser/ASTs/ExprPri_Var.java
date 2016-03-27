package Parser.ASTs;

import Parser.AST;
import Parser.CodeGenerator;

public class ExprPri_Var extends AST {
	String name;
	String tmp_addr;
	String ref_type;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		this.tmp_addr="$"+this.name;
	}
	public boolean genCode(CodeGenerator codegen){
		
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		
		return true;
	}

}
