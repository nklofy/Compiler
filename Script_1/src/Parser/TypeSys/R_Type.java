package Parser.TypeSys;

import Parser.*;
import Parser.TypeSys.*;

public class R_Type extends R_Record {
	//expression of types TODO	
	//inferencing methods TODO
	T_Type type_value;
	boolean isStatic;
	AST type_def;
	public AST getTypeDef() {
		return type_def;
	}
	public void setTypeDef(AST type_def) {
		this.type_def = type_def;
	}
	public boolean isStatic() {
		return isStatic;
	}
	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}
	
}
