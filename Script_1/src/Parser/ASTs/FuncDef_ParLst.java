package Parser.ASTs;

import java.util.*;
import Parser.*;

public class FuncDef_ParLst extends AST {
	boolean isE=false;
	LinkedList<FuncDef_Par> pars;
	public void addPar(FuncDef_Par par){
		if(pars==null){
			this.pars=new LinkedList<FuncDef_Par>();			
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
	public boolean genSymTb(CodeGenerator codegen){
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		return true;
	}
}
