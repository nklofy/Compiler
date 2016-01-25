package Parser.TypeSys;

import Parser.TypeSys.*;

public class R_Variable extends R_Record{
	boolean isFinal;
	T_Type t_type;
	boolean isStatic;
	
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
	public T_Type getTypeT() {
		return t_type;
	}
	public void setTypeT(T_Type type) {
		this.t_type = type;
	}	
}
