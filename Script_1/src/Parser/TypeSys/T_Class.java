package Parser.TypeSys;

import java.util.*;
import Parser.*;

public class T_Class extends T_Type {
	private LinkedList<String> impl_types=new LinkedList<String>();
	private LinkedList<String> extd_types=new LinkedList<String>();
	private HashMap<String,R_Function> methods=new HashMap<String,R_Function>();
	private HashMap<String,R_Variable> fields=new HashMap<String,R_Variable>();
	HashSet<String> all_impl=new HashSet<String>();	
	HashSet<String> all_extd=new HashSet<String>();
	private String scope="global";
	
	
	{
		this.setKType(KType.t_cls);
	}
	//public T_Class(){
	//	this.setKType(KType.t_cls);
	//}
	public HashMap<String,R_Variable> getFields() {
		return fields;
	}
	public void setFields(HashMap<String,R_Variable> fields) {
		this.fields = fields;
	}
	public HashMap<String,R_Function> getMethods() {
		return methods;
	}
	public void setMethods(HashMap<String,R_Function> methods) {
		this.methods = methods;
	}
	public LinkedList<String> getImplTypes() {
		return impl_types;
	}
	public void setImplTypes(LinkedList<String> impl_types) {
		this.impl_types = impl_types;
	}
	public LinkedList<String> getExtdTypes() {
		return extd_types;
	}
	public void setExtdTypes(LinkedList<String> extd_types) {
		this.extd_types = extd_types;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public HashSet<String> getAllImpl() {
		return all_impl;
	}
	public HashSet<String> getAllExtd() {
		return all_extd;
	}
	public void genTypeSig(CodeGenerator codegen) {
		String s="";
		if(this.isGnrc()){
			s+="<";
			for(String g:this.getGnrcPars()){
				s+=codegen.getTypeInSymTb(g).getTypeSig()+",";
			}
			s=s.substring(0,s.length()-1)+">";
		}
		s+=this.getTypeName();
		this.setTypeSig(s);
	}
	
	
	public boolean canCastFrom(CodeGenerator codegen,T_Type type2){
		if(type2==this) return true;
		else if(this.all_extd.contains(type2.getTypeName())||this.all_impl.contains(type2.getTypeName()))
			return true;
		else if(type2.getKType()==T_Type.KType.t_cls){
			if(((T_Class)type2).all_extd.contains(this.getTypeName())
					||((T_Class)type2).all_impl.contains(this.getTypeName()))return true;			
		}else if(type2.getKType()==T_Type.KType.t_intf){
			if(((T_Interface)type2).all_extd.contains(this.getTypeName()))return true;
		}
		return false;
	}
	//for type checking
	public boolean checkAllImpl(CodeGenerator codegen){//no ring inheriting in impl types 
		for(String s:this.impl_types){
			T_Interface t= (T_Interface) codegen.getTypeInSymTb(s);
			if(this.all_impl.contains(t.getTypeName()))return false;
			this.all_impl.add(t.getTypeName());
			for(String name:t.getAllExtd()){
				if(this.all_impl.contains(name))return false;
				this.all_impl.add(name);
			}		
		}
		for(String s:this.extd_types){
			T_Class t= (T_Class) codegen.getTypeInSymTb(s);
			for(String name:t.getAllImpl()){
				if(this.all_impl.contains(name))return false;
				this.all_impl.add(name);
			}
		}
		return true;
	}
	public boolean checkAllExtd(CodeGenerator codegen){//no ring inheriting in extd types
		for(String s:this.extd_types){
			T_Class t=(T_Class) codegen.getTypeInSymTb(s);
			this.all_extd.add(t.getTypeName());
			for(String name:t.getAllExtd()){
				if(this.all_extd.contains(name))return false;
				this.all_extd.add(name);
			}
		}
		return true;
	}
	public R_Variable getField(CodeGenerator codegen, String name){
		if(this.fields.containsKey(name))return this.fields.get(name);
		for(String s:this.all_extd){
			T_Class t=(T_Class) codegen.getTypeInSymTb(s);
			if(t.fields.containsKey(name))return t.fields.get(name);
		}
		return null;
	}	
	public R_Function getMethod(CodeGenerator codegen, String name){
		if(this.methods.containsKey(name))return this.methods.get(name);
		for(String s:this.all_extd){
			T_Class t=(T_Class) codegen.getTypeInSymTb(s);
			if(this.methods.containsKey(name))return this.methods.get(name);			
		}
		return null;
	}
	
/*	public boolean checkAllMthd(CodeGenerator codegen){//skip these and spec them undefined actions
		HashMap<String,R_Function> methods=new HashMap<String,R_Function>();
		methods.putAll(this.methods);
		for(String s:this.impl_types){//make sure all interface are implemented
			T_Interface t=(T_Interface) codegen.getTypeInSymTb(s);
			for(String name:t.getMethods().keySet()){
				if(!methods.containsKey(name)) return false;
				R_Function f1=methods.get(name);
				R_Function f2=t.getMethods().get(name);
				if(!f1.isMulti()&&!f2.isMulti()&&!f1.isEqNameType(f2)){
					return false;
				}else if(!f1.isMulti()&&f2.isMulti()){
					if(!f2.isCntnNameType(f1))return false;
				}else if(f1.isMulti()&&!f2.isMulti()){
					if(!f1.isCntnNameType(f2))return false;
				}
			}
		}
		for(String s:this.extd_types){//gen all methods in this type, including super classes's
			T_Class t= (T_Class) codegen.getTypeInSymTb(s);
			for(String name:t.methods.keySet()){
				R_Function r=t.methods.get(name);
				if(!methods.containsKey(name)){
					methods.put(name, r);
				}else{
					R_Function r1=methods.get(name);
					if(r.isMulti()){
						for(String ts:r.getMulti().keySet()){
							if(!r1.isCntnNameType(r.getMulti().get(ts))){
								r1.addFuncR(r.getMulti().get(ts));
							}
							else
								return false;
						}
					}else{
						//if(!r1.isCntnNameType(r))
							r1.addFuncR(r);
						//else
						//	return false;
					}
				}
			}
		}
		return true;
	}
	public boolean checkAllField(CodeGenerator codegen){//gen all fields in this type, including super classes's
		HashMap<String,R_Variable> fields=new HashMap<String,R_Variable>();
		fields.putAll(this.fields);
		for(String s:this.extd_types){
			T_Class t = (T_Class) codegen.getTypeInSymTb(s);
			for(String name:t.fields.keySet()){
				if(!fields.containsKey(name)){
					fields.put(name, t.fields.get(name));
				}else
					return false;
			}
		}
		return true;
	}*/
}