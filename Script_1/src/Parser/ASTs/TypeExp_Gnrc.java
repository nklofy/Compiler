package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class TypeExp_Gnrc extends AST {
	T_Type t_type;//type_value
	TypeExp_Idn idn_type;
	Gnrc_ArgLst args;
	public boolean setGnrcType(TypeExp_Idn idn_type,Gnrc_ArgLst args){
		this.args=args;
		this.idn_type=idn_type;	
		return true;
	}
}
