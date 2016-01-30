package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class TypeExp_Idn extends AST {
	T_Type t_type;//type_value
	TypeExp_Idn type_idn;
	ExprPri_Var var;
	public boolean setTypeIdn(TypeExp_Idn type_idn,ExprPri_Var var){
		this.var=var;
		this.type_idn=type_idn;
		return true;
	}
	
}
