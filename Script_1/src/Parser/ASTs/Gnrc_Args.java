package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class Gnrc_Args extends AST {	
	T_Type t_type;//type_value
	Gnrc_Args pre_args;
	ExprPri_Var var;
	TypeExp_Idn ext_idn_t;
	public boolean setGnrcArgs(Gnrc_Args pre_args,ExprPri_Var var,TypeExp_Idn idn_type){
		this.pre_args=pre_args;
		this.var=var;
		this.ext_idn_t=idn_type;		
		return true;
	}
}
