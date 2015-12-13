//static objs and functions
package Interpreter;

import java.util.ArrayList;
import java.util.HashMap;

import Parser.ASTs.Data_Func;
import Parser.ASTs.Data_Obj;
import Parser.ASTs.Type_Func;
import Parser.ASTs.Type_Obj;

public class RT_Static {
	HashMap<String,ArrayList<Type_Func>> funcs=new HashMap<String,ArrayList<Type_Func>>();
	HashMap<String,Data_Obj> objs=new HashMap<String,Data_Obj>();
	HashMap<String,Type_Obj> types=new HashMap<String,Type_Obj>();
	
	public ArrayList<Type_Func> getFunc(String name){
		return funcs.get(name);
	}
	public Data_Obj getObj(String name){
		return objs.get(name);
	}
	public Type_Obj getType(String name){
		return types.get(name);
	}
	
	public boolean addObj(String name, Data_Obj obj){
		//TODO check unique firstly
		objs.put(name, obj);
		return true;
	}
	public boolean addFunc(String name, Type_Func func){
		//TODO check unique firstly
		if(funcs.get(name).isEmpty()){
			funcs.put(name, new ArrayList<Type_Func>());
		}
		funcs.get(name).add(func);
		return true;
	}
	public boolean addType(String name, Type_Obj type){
		this.types.put(name, type);
		return true;
	}
}
