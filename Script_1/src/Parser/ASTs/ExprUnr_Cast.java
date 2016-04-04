package Parser.ASTs;

import Parser.*;

public class ExprUnr_Cast extends AST {
	TypeExp type_exp;
	ExprAccs accs;
	String rst_val;
	String rst_type;
	String ref_type;
	
	public boolean setCast(TypeExp type_exp,ExprAccs accs){
		this.type_exp=type_exp;
		this.accs=accs;
		return true;
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
