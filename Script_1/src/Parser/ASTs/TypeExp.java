package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class TypeExp extends AST {
	T_Type t_type;
	TypeExp_Arr type_array;
	TypeExp_Bsc type_basic;
	TypeExp_Func type_func;
	TypeExp_Idn type_idn;
	TypeExp_Gnrc type_gnrc;
	public boolean setTypeExp(AST ast){
		switch(this.getASTType()){
		case "TypeExp_Arr":
			this.type_array = (TypeExp_Arr)ast;
			this.t_type=this.type_array.t_type;
			break;
		case "TypeExp_Bsc":
			this.type_basic = (TypeExp_Bsc)ast;
			this.t_type=this.type_basic.t_type;
			break;			
		case "TypeExp_Func":
			this.type_func = (TypeExp_Func)ast;
			this.t_type=this.type_func.t_type;
			break;			
		case "TypeExp_Idn":
			this.type_idn = (TypeExp_Idn)ast;
			this.t_type=this.type_idn.t_type;
			break;
		case "TypeExp_Gnrc":
			this.type_gnrc = (TypeExp_Gnrc)ast;
			this.t_type=this.type_gnrc.t_type;
		default:
			return false;
		}
		return true;
	}
	public T_Type getTypeT() {
		return t_type;
	}
}
