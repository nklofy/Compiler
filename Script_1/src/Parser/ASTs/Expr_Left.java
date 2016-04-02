package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class Expr_Left extends AST {
	ExprAccs_Fld acces_field;
	ExprAccs_Arr acces_array;
	String rst_addr;
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
			this.rst_addr=this.acces_field.rst_addr;
			break;
		case "ExprAccs_Arr":
			this.acces_array.genCode(codegen);
			this.rst_addr=this.acces_array.rst_addr;
			break;
			default:
				return false;
		}
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		switch(this.getASTType()){
		case "ExprAccs_Fld":
			if(!this.acces_field.genSymTb(codegen))
				return false;
			this.ref_type=this.acces_field.ref_type;
			break;
		case "ExprAccs_Arr":
			if(!this.acces_array.genSymTb(codegen))
				return false;
			this.ref_type=this.acces_array.ref_type;
			break;
			default:
				return false;
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		switch(this.getASTType()){
		case "ExprAccs_Fld":
			return this.acces_field.checkType(codegen);
		case "ExprAccs_Arr":
			return this.acces_array.checkType(codegen);
		default:
			return false;
		}
	}
}
