package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class TypeExp extends AST {
	//T_Type t_type;//type_value
	TypeExp_Arr type_array;
	TypeExp_Bsc type_basic;
	TypeExp_Func type_func;
	TypeExp_Idn type_idn;
	TypeExp_Gnrc type_gnrc;
	String ret_type;
	
	public boolean setTypeExp(AST ast){
		switch(this.getASTType()){
		case "TypeExp_Arr":
			this.type_array = (TypeExp_Arr)ast;
			break;
		case "TypeExp_Bsc":
			this.type_basic = (TypeExp_Bsc)ast;
			break;			
		case "TypeExp_Func":
			this.type_func = (TypeExp_Func)ast;
			break;			
		case "TypeExp_Idn":
			this.type_idn = (TypeExp_Idn)ast;
			break;
		case "TypeExp_Gnrc":
			this.type_gnrc = (TypeExp_Gnrc)ast;
			break;
		default:
			return false;
		}
		return true;
	}
	public boolean genCode(CodeGenerator codegen){
		switch(this.getASTType()){
		case "TypeExp_Arr":
			this.type_array.genCode(codegen);
			break;
		case "TypeExp_Bsc":
			this.type_basic.genCode(codegen);			
			break;			
		case "TypeExp_Func":
			this.type_func.genCode(codegen);
			break;			
		case "TypeExp_Idn":
			this.type_idn.genCode(codegen);
			break;
		case "TypeExp_Gnrc":
			this.type_gnrc.genCode(codegen);
			break;
		default:
			return false;
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		boolean b=false;
		switch(this.getASTType()){
		case "TypeExp_Arr":
			b=this.type_array.checkType(codegen);
			this.ret_type=this.type_array.ret_type;
			break;
		case "TypeExp_Bsc":
			b=this.type_basic.checkType(codegen);
			this.ret_type=this.type_basic.ret_type;
			break;			
		case "TypeExp_Func":
			b=this.type_func.checkType(codegen);
			this.ret_type=this.type_func.ret_type;
			break;			
		case "TypeExp_Idn":
			b=this.type_idn.checkType(codegen);
			this.ret_type=this.type_idn.ret_type;
			break;
		case "TypeExp_Gnrc":
			b=this.type_gnrc.checkType(codegen);
			this.ret_type=this.type_gnrc.ret_type;
			break;
		default:
			return false;
		}
		return b;
	}
}
