package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class TypeExp_Gnrc extends AST {
	String type_name;//type_name
	T_Type t_type;//type_value
	TypeExp_Idn idn_type;
	Gnrc_Args args;
	public boolean setGnrcType(TypeExp_Idn idn_type,Gnrc_Args args){
		this.args=args;
		this.idn_type=idn_type;
		this.type_name=this.idn_type.type_name+"<"+this.args.type_name;
		return true;
	}
}
