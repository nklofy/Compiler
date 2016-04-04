package Parser.ASTs;

import Parser.*;

public class ExprAccs extends AST {
	ExprAccs_Fld fld;
	ExprAccs_Pri pri;
	ExprAccs_Arr arr;
	ExprAccs_App app;
	String rst_val;
	String rst_type;
	String ref_type;
	
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
	
	public boolean genCode(CodeGenerator codegen){
		switch(this.getASTType()){
		case "ExprAccs_Fld":
			this.fld.genCode(codegen);
			break;
		case "ExprAccs_Pri":
			this.pri.genCode(codegen);
			break;
		case "ExprAccs_Arr":
			this.arr.genCode(codegen);
			break;
		case "ExprAccs_App":
			this.app.genCode(codegen);
			break;
			default:
				break;
		}
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		switch(this.getASTType()){
		case "ExprAccs_Fld":
			if(!this.fld.genSymTb(codegen))
				return false;
			
			break;
		case "ExprAccs_Pri":
			if(!this.pri.genSymTb(codegen))
				return false;
			this.rst_type=this.pri.rst_type;
			this.rst_val=this.pri.rst_val;
			this.pri.ref_type=this.ref_type;
			break;
		case "ExprAccs_Arr":
			if(!this.arr.genSymTb(codegen))
				return false;
			this.rst_type=this.arr.rst_type;
			this.rst_val=this.arr.rst_val;
			this.arr.ref_type=this.ref_type;
			break;
		case "ExprAccs_App":
			if(!this.app.genSymTb(codegen))
				return false;
			this.rst_type=this.app.rst_type;
			this.rst_val=this.app.rst_val;
			this.app.ref_type=this.ref_type;
			break;
		default:
			break;
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		switch(this.getASTType()){
		case "ExprAccs_Fld":
			if(!this.fld.checkType(codegen))
				return false;
			break;
		case "ExprAccs_Pri":
			if(!this.pri.checkType(codegen))
				return false;
			break;
		case "ExprAccs_Arr":
			if(!this.arr.checkType(codegen))
				return false;
			break;			
		case "ExprAccs_App":
			if(!this.app.checkType(codegen))
				return false;
			break;
			default:
				break;
		}
		return true;
	}
}
