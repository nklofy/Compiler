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
	private boolean isMethod;
	LinkedList<String> pars_name;
	ArrayList<IRCode> func_body;
	AST func_def;
	String scope;
	
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
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
	public boolean isMethod() {
		return isMethod;
	}
	public void setMethod(boolean isField) {
		this.isMethod = isField;
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
	public boolean isEqArgTypes(CodeGenerator codegen, LinkedList<String>gnrc_args, LinkedList<String>args){
		T_Function t=this.t_type;
		boolean eqA=false;
		boolean eqG=false;
		if(args==null){
			if(t.getParTypes()==null)
				eqA=true;
		}else{
			LinkedList<String> pars=t.getParTypes();
			Iterator<String> it1=pars.iterator();
			Iterator<String> it2=args.iterator();
			while(it1.hasNext()&&it2.hasNext()){
				String s1=it1.next();
				String s2=it2.next();
				T_Type t1,t2;
				t1=codegen.getTypeInSymTb(codegen.FindGnrcArgTb(s1));
				t2=codegen.getTypeInSymTb(codegen.FindGnrcArgTb(s2));
				if(t1==null)t1=codegen.getTypeInSymTb(s1);
				if(t2==null)t2=codegen.getTypeInSymTb(s2);
				if(!t1.isEqType(t2))
					return false;
			}
			eqA=true;
		}		
		if(gnrc_args==null){
			if(!t.isGnrc())
				eqG=true;
		}else{
			LinkedList<String> gpars=t.getGnrcPars();
			if(gpars.size()==gnrc_args.size())
				eqG=true;
		}
		return eqA&&eqG;
	}
	public boolean isCtArgTypes(CodeGenerator codegen, LinkedList<String>gnrc_args, LinkedList<String>args){
		T_Function t=this.t_type;
		boolean eqA=false;
		boolean eqG=false;
		if(args==null){
			if(t.getParTypes()==null)
				eqA=true;
		}else{
			LinkedList<String> pars=t.getParTypes();
			Iterator<String> it1=pars.iterator();
			Iterator<String> it2=args.iterator();
			while(it1.hasNext()&&it2.hasNext()){
				String s1=it1.next();
				String s2=it2.next();
				T_Type t1,t2;
				t1=codegen.getTypeInSymTb(codegen.FindGnrcArgTb(s1));
				t2=codegen.getTypeInSymTb(codegen.FindGnrcArgTb(s2));
				if(t1==null)t1=codegen.getTypeInSymTb(s1);
				if(t2==null)t2=codegen.getTypeInSymTb(s2);
				if(!t2.canCastFrom(codegen,t1))
					return false;
			}
			eqA=true;
		}		
		if(gnrc_args==null){
			if(!t.isGnrc())
				eqG=true;
		}else{
			LinkedList<String> gpars=t.getGnrcPars();
			if(gpars.size()==gnrc_args.size())
				eqG=true;
		}
		return eqA&&eqG;
	}
}
