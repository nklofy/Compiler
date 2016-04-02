package Parser.TypeSys;

import Parser.AST;
import Parser.TypeSys.*;

public class R_Variable {
	boolean isFinal;
	boolean isStatic;
	boolean isDummy;
	String var_type;
	String tmp_addr;
	String name;

	public String getVarName() {
		return name;
	}
	public void setVarName(String name) {
		this.name = name;
	}
	public String getTmpAddr() {
		return tmp_addr;
	}
	public void setTmpAddr(String tmp_addr) {
		this.tmp_addr = tmp_addr;
	}
	public boolean isDummy() {
		return isDummy;
	}
	public void setDummy() {
		this.isDummy = true;
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
