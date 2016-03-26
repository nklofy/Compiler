package Parser.TypeSys;


import java.util.*;
import Interpreter.*;

public class TypeSystem {//defining, producing, managing all kinds of Types, decide types' initiating order
	public TypeSystem(){
		this.initTypes();
		this.initFuncs();
	}
	T_BasicType t_int=new T_BasicType();
	T_BasicType t_double=new T_BasicType();
	T_BasicType t_bool=new T_BasicType();
	T_BasicType t_string=new T_BasicType();
	T_BasicType t_char=new T_BasicType();
	public boolean initTypes(){
		this.t_int.setTypeB(T_BasicType.en_BType.t_int);
		this.t_double.setTypeB(T_BasicType.en_BType.t_double);
		this.t_bool.setTypeB(T_BasicType.en_BType.t_bool);
		this.t_string.setTypeB(T_BasicType.en_BType.t_string);
		this.t_char.setTypeB(T_BasicType.en_BType.t_char);
		return true;
	}
	public T_Type getBType(String s){
		switch(s){
		case "int":
			return this.t_int;
		case "double":
			return this.t_double;
		case "bool":
			return this.t_bool;
		case "string":
			return this.t_string;
		case "char":
			return this.t_char;
		default:
			return null;
		}
	}
	public boolean initFuncs(){
		
		return true;
	}
	/*
	public boolean canAsn(T_Type type1){
		
		return true;
	}
	public boolean canTrans(T_Type type1){
		return true;
	}*/
	/*
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
	}*/
}
