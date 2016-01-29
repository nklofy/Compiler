package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class TypeExp_Bsc extends AST {
	String type_name;//type_name
	T_Type t_type;//type_value

/*	public T_Type getTypeB() {
		return t_type;
	}
	public void setTypeB(T_Type t_type) {
		this.t_type = t_type;
	}*/
	public void setTypeB(String s) {
		this.type_name=s;
	}
	public String getTypeName() {
		return type_name;
	}

	public void setTypeName(String type_name) {
		this.type_name = type_name;
	}
}
