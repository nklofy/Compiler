package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class TypeExp_Arr extends AST {
	String type_name;//type_name
	T_Type t_type;//type_value
	TypeExp type_pre;
	public TypeExp getPreType() {
		return type_pre;
	}
	public void setPreType(TypeExp type_pre) {
		this.type_pre = type_pre;
		this.type_name=this.type_pre.type_name+"]";
	}
	public String getTypeName() {
		return type_name;
	}
	public void setTypeName(String type_name) {
		this.type_name = type_name;
	}
	
	
	
}
