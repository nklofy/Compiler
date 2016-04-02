package Parser.ASTs;

import java.util.*;
import Parser.*;
import Parser.TypeSys.*;

public class Gnrc_ParLst extends AST {
	boolean isE=false;
	LinkedList<Gnrc_Par> g_pars;
	LinkedList<String> types_name;
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
	public boolean genCode(CodeGenerator codegen){
		
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		if(isE)
			return true;
		this.types_name=new LinkedList<String>();
		HashSet<String> all_ns=new HashSet<String>();
		for(Gnrc_Par p:g_pars){
			if(!p.genSymTb(codegen))
				return false;
			this.types_name.add(p.var.name);
			if(all_ns.contains(p.var.name))
				return false;
			all_ns.add(p.var.name);
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		
		return true;
	}
}
