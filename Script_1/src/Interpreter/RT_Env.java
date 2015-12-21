//types, functions store in environment  
package Interpreter;
import java.util.*;

import Parser.AST;
import Parser.ASTs.*;
import Parser.TypeSys.Data_Obj;
import Parser.TypeSys.Type_Func;
import Parser.TypeSys.Type_Obj;

public class RT_Env {
	RT_Frame crt_frm;
	HashMap<String,ArrayList<Type_Func>> funcs=new HashMap<String,ArrayList<Type_Func>>();
	HashMap<String,Data_Obj> objs=new HashMap<String,Data_Obj>();
	HashMap<String,Type_Obj> types=new HashMap<String,Type_Obj>();
	HashMap<AST, Data_Obj> tmp_values=new HashMap<AST, Data_Obj>();
	
	
	public RT_Frame getCrtFrm(){
		return this.crt_frm;
	}
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
		objs.put(name, obj);
		return true;
	}
	public boolean addFunc(String name, Type_Func func){
		if(!funcs.containsKey(name)){
			funcs.put(name, new ArrayList<Type_Func>());
		}
		funcs.get(name).add(func);
		return true;
	}
	public boolean addType(String name, Type_Obj type){
		this.types.put(name, type);
		return true;
	}
	public Data_Obj getTmpV(AST ast) {
		return tmp_values.get(ast);
	}
	public boolean addTmpV(AST ast, Data_Obj tmp_v) {
		this.tmp_values.put(ast, tmp_v);
		return true;
	}
}
