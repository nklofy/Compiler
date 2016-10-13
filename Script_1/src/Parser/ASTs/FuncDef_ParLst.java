package Parser.ASTs;

import java.util.*;
import Parser.*;
import Parser.TypeSys.GenCodeException;
import Parser.TypeSys.GenSymTblException;
import Parser.TypeSys.TypeCheckException;

public class FuncDef_ParLst extends AST {
	boolean isE=false;
	LinkedList<FuncDef_Par> pars;
	LinkedList<String> pars_type;
	LinkedList<String> pars_name;
	//String rst_val;
	
	
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
	//public String getVal() {
	//	return rst_val;
	//}
	//public void setVal(String val) {
	//	this.rst_val = val;
	//}
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		if(isE)
			return true;
		for(FuncDef_Par par:pars){
			par.genCode(codegen);
			//this.rst_val+=par.var.rst_val+",";
		}
		//this.rst_val=this.rst_val.substring(0, rst_val.length()-1);
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		if(isE)
			return true;
		HashSet<String> names=new HashSet<String>();
		this.pars_type=new LinkedList<String>();
		this.pars_name=new LinkedList<String>();
		for(FuncDef_Par par:pars){
			if(!par.genSymTb(codegen))
				return false;
			if(names.contains(par.var.name))
				return false;
			names.add(par.var.name);
			this.pars_name.add(par.var.name);
			this.pars_type.add(par.type.rst_type);
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		if(isE)
			return true;
		for(FuncDef_Par par:pars){
			if(!par.checkType(codegen))
				return false;
		}
		return true;
	}
}
