package Parser.TypeSys;

import Parser.*;
import Parser.TypeSys.*;

public class R_Type extends R_Record {
	//expression of types TODO	
	//inferencing methods TODO
	T_Type type_value;
	boolean isStatic;
	AST type_exp;
	public AST getTypeExp() {
		return type_exp;
	}
	public void setTypeExp(AST type_def) {
		this.type_exp = type_def;
	}
	public boolean isStatic() {
		return isStatic;
	}
	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}
	public T_Type getTypeValue() {
		return type_value;
	}
	public void setTypeValue(T_Type type_value) {
		this.type_value = type_value;
	}
	
}
