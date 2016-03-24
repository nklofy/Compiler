package Parser.TypeSys;

import java.util.*;

public class T_Class extends T_Type {
	LinkedList<R_Variable> fields=new LinkedList<R_Variable>();
	LinkedList<T_Function> methods=new LinkedList<T_Function>(); 
	LinkedList<T_Interface> impl_types=new LinkedList<T_Interface>();
	LinkedList<T_Class> extd_types=new LinkedList<T_Class>();
	HashMap<String,R_Function> name_methods=new HashMap<String,R_Function>();
	HashMap<String,R_Variable> name_fields=new HashMap<String,R_Variable>();
	
	
	public LinkedList<R_Variable> getFields() {
		return fields;
	}
	public void setFields(LinkedList<R_Variable> fields) {
		this.fields = fields;
		for(R_Variable r:this.fields){
			this.name_fields.put(r.name, r);
		}
	}
	public LinkedList<T_Function> getMethods() {
		return methods;
	}
	public void setMethods(LinkedList<T_Function> methods) {
		this.methods = methods;
		for(T_Function f:this.methods){
			if(!this.name_methods.containsKey(f.type_name)){
				this.name_methods.put(f.type_name, new R_Function());
			}
			this.name_methods.get(f.type_name).setTypeT(f);			
		}
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
	public boolean isImplOf(T_Type type1){
		return this.impl_types.contains(type1);
	}
	public boolean isSubOf(T_Type type1){
		return this.extd_types.contains(type1);
	}
	public HashMap<String, R_Function> getNameMethods() {
		return name_methods;
	}
	public void setNameMethods(HashMap<String, R_Function> name_methods) {
		this.name_methods = name_methods;
	}
	public HashMap<String, R_Variable> getNameFields() {
		return name_fields;
	}
	public void setNameFields(HashMap<String, R_Variable> name_fields) {
		this.name_fields = name_fields;
	}
}
