package Parser.ASTs;

import java.util.*;
import Parser.*;
import Parser.TypeSys.*;

public class Gnrc_ParLst extends AST {
	boolean isE=false;
	LinkedList<Gnrc_Par> g_pars;
	LinkedList<String> types_name;
	//String rst_val;
	
	public void addPar(Gnrc_Par par){
		if(g_pars==null){
			this.g_pars=new LinkedList<Gnrc_Par>();			
		}
		this.g_pars.add(par);
	}
	public boolean isE() {
		return isE;
	}
	public void setE() {
		this.isE = true;
	}
	//public String getVal() {
	//	return rst_val;
	//}
	//public void setVal(String val) {
	//	this.rst_val = val;
	//}
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		//gen rst_val
		if(isE)
			return true;
		for(Gnrc_Par par:this.g_pars){
			par.genCode(codegen);
			//this.rst_val+=par.rst_val+",";
		}
		//this.rst_val=this.rst_val.substring(0,this.rst_val.length()-1);
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		if(isE)
			return true;
		this.types_name=new LinkedList<String>();
		HashSet<String> all_ns=new HashSet<String>();
		for(Gnrc_Par p:g_pars){
			if(!p.genSymTb(codegen))
				return false;
			this.types_name.add(p.var.name);
			if(codegen.getTypeInSymTb(p.var.name)!=null)
				throw new GenSymTblException("gensymtable error: gnrc par type "+p.var.name);
			if(all_ns.contains(p.var.name))
				throw new GenSymTblException("gensymtable error: gnrc par type "+p.var.name);
			T_Type t=new T_Type();
			t.setTypeName(p.var.name);
			t.setDummy();
			codegen.putTypeInSymTb(p.var.name, t);
			all_ns.add(p.var.name);
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		
		return true;
	}
}
