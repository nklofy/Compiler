package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class TypeExp_Arr extends AST {
	T_Type t_type;//type_value
	TypeExp type_pre;
	String ret_type;
	
	public TypeExp getPreType() {
		return type_pre;
	}
	public void setPreType(TypeExp type_pre) {
		this.type_pre = type_pre;
	}
	public boolean genCode(CodeGenerator codegen){
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		return true;
	}
	
	
	
}
