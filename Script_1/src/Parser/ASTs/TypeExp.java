package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class TypeExp extends AST {
	T_Type t_type;//type_value
	TypeExp_Arr type_array;
	TypeExp_Bsc type_basic;
	TypeExp_Func type_func;
	TypeExp_Idn type_idn;
	TypeExp_Gnrc type_gnrc;
	String tmp_tpname;
	
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
		default:
			return false;
		}
		return true;
	}
}
