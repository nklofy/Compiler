package Parser.TypeSys;

import java.util.*;
import Parser.*;
import Parser.TypeSys.*;

public class R_Function extends R_Record {
	T_Function type;
	boolean isStatic;
	boolean isAbstract;
	AST func_def;
	boolean isMulti=false;
	public boolean isMulti() {
		return isMulti;
	}
	public void setMulti() {
		this.isMulti = true;
		this.multimorphism=new LinkedList<R_Function>();
	}
	LinkedList<R_Function> multimorphism;
	public void addMulti(R_Function r){
		this.multimorphism.add(r);
	}
	public AST getFuncDef() {
		return func_def;
	}
	public void setFuncDef(AST func_def) {
		this.func_def = func_def;
	}
	public boolean isStatic() {
		return isStatic;
	}
	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}
	
	public T_Function getType() {
		return type;
	}
	public void setType(T_Function type) {
		this.type = type;
	}
}
