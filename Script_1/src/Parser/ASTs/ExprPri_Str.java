package Parser.ASTs;

import Parser.AST;
import Parser.CodeGenerator;

public class ExprPri_Str extends AST {
	String rst_val;
	String ref_type;
	String rst_type;
	
	public void setStr(String value) {
		this.rst_val=value;		
	}
	public boolean genCode(CodeGenerator codegen){
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		if(!this.ref_type.equals("string")){
			return false;
		}
		return true;
	}
}
