//type information about object
package Parser.TypeSys;

import java.util.*;

public class Type_Obj {	
	private String type_name;
	private Type_Base type_base;
	private Type_Func type_func;
	//class type
	private HashMap<String, Type_Obj> fields=new HashMap<String, Type_Obj>();
	private ArrayList<Type_Func> funcs=new ArrayList<Type_Func>();		//refactoring in future
	private HashMap<String,HashSet<Integer>> func_idx=new HashMap<String,HashSet<Integer>>();	//need new method to search or match correct function
	public String getTypeName() {
		return type_name;
	}
	public void setTypeName(String type_name) {
		this.type_name = type_name;
	}
	public Type_Base getTypeBase() {
		return type_base;
	}
	public void setTypeBase(Type_Base type_base) {
		this.type_base = type_base;
	}
	public Type_Func getTypeFunc() {
		return type_func;
	}
	public void setTypeFunc(Type_Func type_func) {
		this.type_func = type_func;
	}
	public HashMap<String, Type_Obj> getFields() {
		return fields;
	}
	public void setFields(HashMap<String, Type_Obj> fields) {
		this.fields = fields;
	}
	public ArrayList<Type_Func> getFuncs() {
		return funcs;
	}
	public void setFuncs(ArrayList<Type_Func> funcs) {
		this.funcs = funcs;
	}
	public HashMap<String, HashSet<Integer>> getFuncIdx() {
		return func_idx;
	}
	public void setFuncIdx(HashMap<String, HashSet<Integer>> func_idx) {
		this.func_idx = func_idx;
	}
	
}
