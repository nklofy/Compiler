package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class Gnrc_Args extends AST {
	String type_name;//type_name
	T_Type t_type;//type_value
	Gnrc_Args pre_args;
	ExprPri_Var var;
	TypeExp_Idn idn_type;
	public boolean setGnrcArgs(Gnrc_Args pre_args,ExprPri_Var var,TypeExp_Idn idn_type){
		this.pre_args=pre_args;
		this.var=var;
		this.idn_type=idn_type;
		this.type_name="";
		if(this.pre_args!=null){
			this.type_name=this.pre_args.type_name+"<";
		}
		if(this.idn_type!=null){
			this.type_name=this.type_name+"<?"+this.idn_type.type_name;
		}
		if(this.var!=null){
			this.type_name=this.type_name+this.var.name;
		}
		return true;
	}
}
