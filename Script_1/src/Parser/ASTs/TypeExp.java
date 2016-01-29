package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class TypeExp extends AST {
	String type_name;//type_name
	T_Type t_type;//type_value
	TypeExp_Arr type_array;
	TypeExp_Bsc type_basic;
	TypeExp_Func type_func;
	TypeExp_Idn type_idn;
	TypeExp_Gnrc type_gnrc;
	public boolean setTypeExp(AST ast){
		switch(this.getASTType()){
		case "TypeExp_Arr":
			this.type_array = (TypeExp_Arr)ast;
			this.type_name=this.type_array.type_name;
			break;
		case "TypeExp_Bsc":
			this.type_basic = (TypeExp_Bsc)ast;
			this.type_name=this.type_basic.type_name;
			break;			
		case "TypeExp_Func":
			this.type_func = (TypeExp_Func)ast;
			this.type_name=this.type_func.type_name;
			break;			
		case "TypeExp_Idn":
			this.type_idn = (TypeExp_Idn)ast;
			this.type_name=this.type_idn.type_name;
			break;
		case "TypeExp_Gnrc":
			this.type_gnrc = (TypeExp_Gnrc)ast;
			this.type_name=this.type_gnrc.type_name;
		default:
			return false;
		}
		return true;
	}
}
