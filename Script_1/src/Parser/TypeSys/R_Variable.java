package Parser.TypeSys;

import Parser.AST;
import Parser.TypeSys.*;

public class R_Variable {
	boolean isFinal;
	boolean isStatic;
	boolean isInit;
	String var_type;
	String name;

	public String getVarName() {
		return name;
	}
	public void setVarName(String name) {
		this.name = name;
	}
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
	public String getVarType() {
		return var_type;
	}
	public void setVarType(String type_def) {
		this.var_type = type_def;
	}
}
