package Parser.ASTs;

import Parser.*;

public class ExprAccs extends AST {
	ExprAccs_Fld fld;
	ExprAccs_Pri pri;
	ExprAccs_Arr arr;
	ExprAccs_App app;
	public boolean setAccs(AST ast){
		switch(this.getASTType()){
		case "ExprAccs_Fld":
			this.fld=(ExprAccs_Fld)ast;
			break;
		case "ExprAccs_Pri":
			this.pri=(ExprAccs_Pri)ast;
			break;
		case "ExprAccs_Arr":
			this.arr=(ExprAccs_Arr)ast;
			break;
		case "ExprAccs_App":
			this.app=(ExprAccs_App)ast;
			break;
			default:
				break;
		}
		return true;
	}
}
