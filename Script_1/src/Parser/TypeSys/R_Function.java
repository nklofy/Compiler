package Parser.TypeSys;

import java.util.*;
import Parser.*;
import Parser.IR.*;
import Parser.TypeSys.*;

public class R_Function {
	private T_Function t_type;
	private String func_sig;
	private boolean isMulti=false;	//polymorphic
	private HashMap<String, R_Function> multi_func;//will change?			
	private String func_name;
	private boolean isDummy;
	private boolean isField;
	LinkedList<String> pars_name;
	ArrayList<IRCode> func_body;
	AST func_def;
	
	
	public String getFuncName() {
		return func_name;
	}
	public void setFuncName(String func_name) {
		this.func_name = func_name;
	}
	public ArrayList<IRCode> getFuncBody() {
		return func_body;
	}
	public void setFuncBody(ArrayList<IRCode> func_body) {
		this.func_body = func_body;
	}
	public boolean isDummy() {
		return isDummy;
	}
	public void setDummy(boolean is_dummy) {
		this.isDummy = is_dummy;
	}
	public boolean isField() {
		return isField;
	}
	public void setField(boolean isField) {
		this.isField = isField;
	}
	public LinkedList<String> getParsName() {
		return pars_name;
	}
	public void setParsName(LinkedList<String> pars_name) {
		this.pars_name = pars_name;
	}
	public boolean isMulti() {
		return isMulti;
	}
	public void setMulti() {
		this.isMulti = true;
		this.multi_func=new HashMap<String, R_Function>();
	}
	public boolean addFuncR(R_Function r){
		if(!this.isMulti)
			this.setMulti();
		if(r.isMulti){
			for(R_Function f:r.multi_func.values()){
				if(this.multi_func.containsKey(f.func_sig)){
					return false;
				}
				this.multi_func.put(f.func_sig,f);
			}
		}else{
			if(this.multi_func.containsKey(r.func_sig))
				return false;
			this.multi_func.put(r.func_sig,r);
		}
		return true;
	}
	public HashMap<String, R_Function> getMulti() {
		return multi_func;
	}
	public void setMulti(HashMap<String, R_Function> multimorphism) {
		this.multi_func = multimorphism;
	}	
	public T_Function getTypeT() {
		return t_type;
	}
	public void setTypeT(T_Function type) {
		this.t_type = type;		
	}
	public String getFuncSig() {
		return this.func_sig;
	}
	public void setFuncSig(String type_code) {
		this.func_sig = type_code;
	}
	public AST getFuncDef() {
		return func_def;
	}
	public void setFuncDef(AST func_def) {
		this.func_def = func_def;
	}
	
	public boolean isEqNameType(R_Function r){//functions' name and type are the same
		if(!this.isMulti&&!r.isMulti){
			if(!this.func_name.equals(r.func_name))
				return false;
			if(!this.func_sig.equals(r.func_sig))
				return false;
		}else if(this.isMulti&&r.isMulti){
			for(String s:this.multi_func.keySet()){
				if(!this.multi_func.get(s).isEqNameType(r.multi_func.get(s)))
					return false;
			}
		}else{
			return false;
		}
		return true;
	}
	public boolean isCntnNameType(R_Function r){//the same name and type with one of multi-func
		if(!this.func_name.equals(r.func_name))
			return false;
		if(this.isMulti){
			for(String s:this.multi_func.keySet()){
				if(s.equals(r.func_sig))
					return true;
			}
		}else{
			return this.func_sig.equals(r.func_sig);
		}
		return false;
	}
}
