package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class Expr_Left extends AST {
	ExprAccs_Fld acces_field;
	ExprAccs_Arr acces_array;
	String ret_addr;
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
	public boolean genCode(CodeGenerator codegen){
		switch(this.getASTType()){
		case "ExprAccs_Fld":
			this.acces_field.genCode(codegen);
			this.ret_addr=this.acces_field.ret_addr;
			break;
		case "ExprAccs_Arr":
			this.acces_array.genCode(codegen);
			this.ret_addr=this.acces_array.ret_addr;
			break;
			default:
				return false;
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		boolean b=false;
		switch(this.getASTType()){
		case "ExprAccs_Fld":
			b=this.acces_field.checkType(codegen);
			this.ref_type=this.acces_field.ref_type;
			break;
		case "ExprAccs_Arr":
			b=this.acces_array.checkType(codegen);
			this.ref_type=this.acces_array.ref_type;
			break;
		default:
			break;
		}
		return b;
	}
}
