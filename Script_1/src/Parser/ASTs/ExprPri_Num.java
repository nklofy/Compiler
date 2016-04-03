package Parser.ASTs;

import Parser.AST;
import Parser.CodeGenerator;

public class ExprPri_Num extends AST {

	public void setNum(String string, String value) {
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
