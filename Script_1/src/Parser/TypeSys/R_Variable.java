package Parser.TypeSys;

import Parser.AST;
import Parser.TypeSys.*;

public class R_Variable {
	private boolean isFinal;
	private boolean isStatic;
	private boolean isDummy;
	private boolean isField;
	private String var_type;
	private String rst_val;
	private String name;

	public String getVarName() {
		return name;
	}
	public void setVarName(String name) {
		this.name = name;
	}
	public String getRstVal() {
		return rst_val;
	}
	public void setRstVal(String tmp_addr) {
		this.rst_val = tmp_addr;
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
	public boolean isField() {
		return isField;
	}
	public void setField(boolean isField) {
		this.isField = isField;
	}
	public String getVarType() {
		return var_type;
	}
	public void setVarType(String type_def) {
		this.var_type = type_def;
	}
}
