package Parser.ASTs;

import Parser.AST;
import Parser.CodeGenerator;

public class ExprPri_Str extends AST {
	String rst_val;
	String ref_type;
	String rst_type;
	
	public void setStr(String value) {
		// TODO Auto-generated method stub
		
	}
	public boolean genCode(CodeGenerator codegen){
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		//new type, new var, new function, put in table
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		return true;
	}
}
