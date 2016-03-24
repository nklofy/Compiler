package Parser.TypeSys;

import java.util.*;

public class T_Class extends T_Type {
	LinkedList<R_Variable> fields=new LinkedList<R_Variable>();
	LinkedList<T_Function> methods=new LinkedList<T_Function>(); 
	LinkedList<T_Interface> impl_types=new LinkedList<T_Interface>();
	LinkedList<T_Class> extd_types=new LinkedList<T_Class>();
	HashMap<String,R_Function> name_methods=new HashMap<String,R_Function>();
	HashMap<String,R_Variable> name_fields=new HashMap<String,R_Variable>();
	LinkedList<T_Interface> all_impl=new LinkedList<T_Interface>();
	LinkedList<T_Class> all_extd=new LinkedList<T_Class>();
	HashSet<String> all_mthd_name=new HashSet<String>();
	
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
	//for type checking
	public boolean isImplOf(T_Type type1){
		if(this.impl_types.contains(type1)) return true;
		for(T_Interface t:this.impl_types){
			if(!t.extd_types.isEmpty()){
				for(T_Interface t1:t.extd_types)
				if(t1.isSubOf(t1)) return true;
			}
		}
		return false;
	}
	public boolean isSubOf(T_Type type1){
		if(this.extd_types.contains(type1)) return true;
		for(T_Class t:this.extd_types){
			if(!t.extd_types.isEmpty()){
				for(T_Class t1:t.extd_types)
				if(t1.isSubOf(type1)) return true;
			}
		}
		return false;
	}
	public LinkedList<T_Class> getAllExtd(){
		return this.all_extd;
	}
	public boolean setAllExtd(){		//check no ring or diamond in inheriting
		for(T_Class c:this.extd_types){
			if(this.all_extd.contains(c)) return false;
			this.all_extd.add(c);
			if(!c.extd_types.isEmpty()){
				for(T_Class c1:c.getAllExtd()){
					if(this.all_extd.contains(c1)) return false;
					this.all_extd.add(c1);
				}				
			}
		}
		return true;
	}
	public LinkedList<T_Interface> getAllImpl(){		
		return this.all_impl;
	}
	public boolean setAllImpl(){		//check no ring or diamond in inheriting
		for(T_Interface i:this.impl_types){
			if(this.all_impl.contains(i)) return false;
			this.all_impl.add(i);
			if(!i.extd_types.isEmpty()){
				for(T_Interface i1:i.getAllExtd()){
					if(this.all_impl.contains(i1)) return false;
					this.all_impl.add(i1);	
				}
			}
		}
		return true;
	}
	public void setAllMthdName(){
		for(T_Class c:this.extd_types){
			this.all_mthd_name.addAll(c.all_mthd_name);
		}
		for(T_Function f:this.methods){
			this.all_mthd_name.add(f.getTypeName());
		}
	}
	private boolean findMthd(T_Function f){
		boolean inImpl=false;
		for(T_Function f1:this.methods){
			if(f1.isEqFunc(f)){
				inImpl=true;
				break;
			}
		}
		return inImpl;
	}	
	public boolean checkMthdImpl(){	//check if all methods are implemented in class
		LinkedList<T_Function> all_m_i=new LinkedList<T_Function>();
		for(T_Interface intf:this.getAllImpl()){
			LinkedList<T_Function> fs=intf.getMethods();
			all_m_i.addAll(fs);
		}		
		boolean found=false;		
		for(T_Function f:all_m_i){
			found=false;
			if(findMthd(f)){
				found=true;
				continue;
			}else return false;
		}
		return true;
	}
	public boolean checkSuperCls(){//check if there are fields/methods name conflicts in super classes
		HashSet<String> fld_names=new HashSet<String>();
		for(T_Class c1:this.getAllExtd()){
			for(R_Variable r:c1.fields){
				if(fld_names.contains(r.name)){
					return false;
				}
				fld_names.add(r.name);
			}			
		}
		HashSet<String> all_name=new HashSet<String>();
		for(T_Class c:this.extd_types){
			for(String name:c.all_mthd_name){
				if(all_name.contains(name))
					return false;
				all_name.add(name);
			}
		}
		return true;
	}
}