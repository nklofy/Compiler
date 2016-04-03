package Parser.ASTs;

import Parser.*;

public class ExprCalc_Unary extends AST {
	ExprAccs accs;
	String opt;
	ExprUnr_Cast cast;
	public boolean setUnary(ExprAccs accs,String opt){
		this.accs=accs;
		this.opt=opt;
		return true;
	}
	public boolean setCast(ExprUnr_Cast cast){
		this.cast=cast;
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
