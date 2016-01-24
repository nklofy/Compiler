package Parser.TypeSys;

import Parser.TypeSys.*;

public class R_Function extends R_Record {
	T_Function type;
	boolean isStatic;
	boolean isAbstract;
	
	public boolean isStatic() {
		return isStatic;
	}
	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}
	
	public T_Function getType() {
		return type;
	}
	public void setType(T_Function type) {
		this.type = type;
	}
}
