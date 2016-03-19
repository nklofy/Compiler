package Parser.ASTs;

import java.util.*;
import Parser.*;

public class Gnrc_ParLst extends AST {
	boolean isE=false;
	LinkedList<Gnrc_Par> pars;
	LinkedList<String> pars_name;
	public void addPar(Gnrc_Par par){
		if(pars==null){
			this.pars=new LinkedList<Gnrc_Par>();			
		}
		this.pars.add(par);
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
	public boolean checkType(CodeGenerator codegen){
		
		return true;
	}
}
