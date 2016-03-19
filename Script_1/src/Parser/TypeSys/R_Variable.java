package Parser.TypeSys;

import Parser.AST;
import Parser.TypeSys.*;

public class R_Variable {
	boolean isFinal;
	boolean isStatic;
	boolean isInit;
	T_Type type_def;

	public boolean isStatic() {
		return isStatic;
	}
	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}	
	public boolean isFinal() {
		return isFinal;
	}
	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}
	public T_Type getTypeDef() {
		return type_def;
	}
	public void setTypeDef(T_Type type_def) {
		this.type_def = type_def;
	}
}
