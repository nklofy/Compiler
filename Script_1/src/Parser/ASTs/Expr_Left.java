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
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		switch(this.getASTType()){
		case "ExprAccs_Fld":
			this.acces_field.genCode(codegen);
			this.rst_addr=this.acces_field.rst_val;
			break;
		case "ExprAccs_Arr":
			this.acces_array.genCode(codegen);
			this.rst_addr=this.acces_array.rst_val;
			break;
			default:
				return false;
		}
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		switch(this.getASTType()){
		case "ExprAccs_Fld":
			if(!this.acces_field.genSymTb(codegen))
				return false;
			break;
		case "ExprAccs_Arr":
			if(!this.acces_array.genSymTb(codegen))
				return false;
			break;
			default:
				return false;
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		switch(this.getASTType()){
		case "ExprAccs_Fld":
			if(!this.acces_field.checkType(codegen)) return false;
			this.ref_type=this.acces_field.rst_type;
			break;
		case "ExprAccs_Arr":
			if(!this.acces_array.checkType(codegen)) return false;
			this.ref_type=this.acces_array.rst_type;
			break;
		default:
			return false;
		}
		return true;
	}
}
