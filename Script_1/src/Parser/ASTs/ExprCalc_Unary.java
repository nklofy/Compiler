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
	
}
