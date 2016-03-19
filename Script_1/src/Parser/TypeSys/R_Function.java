package Parser.TypeSys;

import java.util.*;
import Parser.*;
import Parser.TypeSys.*;

public class R_Function {
	T_Function type;
	AST func_def;
	boolean isMulti=false;
	LinkedList<R_Function> multimorphism;//other data struct maybe?
	T_Type ret_type;
	ArrayList<T_Type> par_types;
	
	public boolean isMulti() {
		return isMulti;
	}
	public void setMulti() {
		this.isMulti = true;
		this.multimorphism=new LinkedList<R_Function>();
	}
	public void addMulti(R_Function r){
		if(r.isMulti){
			for(R_Function f:r.multimorphism){
				this.multimorphism.add(f);
			}
		}else{
			this.multimorphism.add(r);
		}
	}
	public AST getFuncDef() {
		return func_def;
	}
	public void setFuncDef(AST func_def) {
		this.func_def = func_def;
	}
	public T_Function getTypeT() {
		return type;
	}
	public void setTypeT(T_Function type) {
		this.type = type;
	}
}
