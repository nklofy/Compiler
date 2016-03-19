package Parser.TypeSys;

import java.util.*;

public class T_Class extends T_Type {
	HashMap<String,T_Type> fields;
	HashMap<String,R_Function> methods; 
	LinkedList<T_Interface> impl_types;
	LinkedList<T_Class> extd_types;
	
	public HashMap<String, T_Type> getFields() {
		return fields;
	}
	public void setFields(HashMap<String, T_Type> fields) {
		this.fields = fields;
	}
	public HashMap<String, R_Function> getMethods() {
		return methods;
	}
	public void setMethods(HashMap<String, R_Function> methods) {
		this.methods = methods;
	}
	public LinkedList<T_Interface> getImplTypes() {
		return impl_types;
	}
	public void setImplTypes(LinkedList<T_Interface> impl_types) {
		this.impl_types = impl_types;
	}
	public LinkedList<T_Class> getExtdTypes() {
		return extd_types;
	}
	public void setExtdTypes(LinkedList<T_Class> extd_types) {
		this.extd_types = extd_types;
	}
}
