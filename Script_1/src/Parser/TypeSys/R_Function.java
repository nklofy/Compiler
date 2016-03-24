package Parser.TypeSys;

import java.util.*;
import Parser.*;
import Parser.TypeSys.*;

public class R_Function {
	T_Function t_type;
	AST func_def;
	boolean isMulti=false;
	LinkedList<T_Function> multimorphism;//other data struct maybe?	
	T_Type ret_type;
	ArrayList<T_Type> par_types;
	
	public boolean isMulti() {
		return isMulti;
	}
	public void setMulti() {
		this.isMulti = true;
		this.multimorphism=new LinkedList<T_Function>();
	}
	public void addMulti(R_Function r){
		if(!this.isMulti)
			this.setMulti();
		if(r.isMulti){
			for(T_Function f:r.multimorphism){
				this.multimorphism.add(f);
			}
		}else{
			this.multimorphism.add(r.t_type);
		}
	}
	public LinkedList<T_Function> getMulti() {
		return multimorphism;
	}
	public void setMulti(LinkedList<T_Function> multimorphism) {
		this.multimorphism = multimorphism;
	}
	public AST getFuncDef() {
		return func_def;
	}
	public void setFuncDef(AST func_def) {
		this.func_def = func_def;
	}
	public T_Function getTypeT() {
		return t_type;
	}
	public void setTypeT(T_Function type) {
		if(this.t_type==null){
			this.t_type = type;
			return;
		}
		if(!this.isMulti){
			this.setMulti();
		}
		this.multimorphism.add(type);
	}
}
