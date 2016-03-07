package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class Expr_Left extends AST {
	ExprAccs_Fld acces_field;
	ExprAccs_Arr acces_array;
	String tmp_addr;
	String ref_type;
	
	public boolean setAccs(AST ast){
		switch(this.getASTType()){
		case "ExprAccs_Fld":
			this.acces_field =(ExprAccs_Fld)ast;
			break;
		case "ExprAccs_Arr":
			this.acces_array = (ExprAccs_Arr)ast;
			break;
			default:
				return false;
		}
		return true;
	}
}
