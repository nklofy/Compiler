package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class TypeExp_Idn extends AST {
	String type_name;//type_name
	T_Type t_type;//type_value
	TypeExp_Idn type_idn;
	ExprPri_Var var;
	public boolean setTypeIdn(TypeExp_Idn type_idn,ExprPri_Var var){
		this.var=var;
		if(type_idn==null){
			this.type_name=var.name;
		}else{
			this.type_idn=type_idn;
			this.type_name=type_idn.type_name+"."+var.name;
		}			
		return true;
	}
	
}
