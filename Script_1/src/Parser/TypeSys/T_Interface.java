package Parser.TypeSys;

import java.util.*;

public class T_Interface extends T_Type {
	LinkedList<T_Interface> extd_types;
	private HashMap<String,R_Function> methods; 
	
	public boolean isSuperOf(T_Type type1){
		return true;
	}
	public boolean isSubOf(T_Type type1){
		return true;
	}
	public HashMap<String,R_Function> getMethods() {
		return methods;
	}
	public void setMethods(HashMap<String,R_Function> methods) {
		this.methods = methods;
	}
}
