package Parser.TypeSys;

import Parser.TypeSys.*;

public class R_Type extends R_Record {
	//expression of types 
	//inference methods
	T_Type type_value;
	String type_name;
	boolean isStatic;
	
	public boolean isStatic() {
		return isStatic;
	}
	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}
	
}
