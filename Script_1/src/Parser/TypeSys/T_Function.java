package Parser.TypeSys;

import java.util.*;
import Parser.*;
import Parser.IR.*;

public class T_Function extends T_Type {
	private String ret_type;
	private LinkedList<String> par_types;
	private String f_t_Code;//for function's type checking
	
	public String getRetType() {
		return ret_type;
	}
	public void setRetType(String ret_type) {
		this.ret_type = ret_type;
	}
	public LinkedList<String> getParTypes() {
		return par_types;
	}
	public void setParTypes(LinkedList<String> par_types) {
		this.par_types = par_types;
	}
	public String getFTCode() {
		return f_t_Code;
	}
	public void setFTCode(String f_t_Code) {
		this.f_t_Code = f_t_Code;
	}
	
	public void genFTCode(CodeGenerator codegen) {
		String s="";
		if(this.isGnrc()){
			s="<"+this.getGnrcPars().size()+">";
		}
		s=codegen.getTypeInSymTb(this.ret_type).getTypeCode()+"(";
		for(String name:this.par_types){
			T_Type t= codegen.getTypeInSymTb(name);
			s=s+t.getTypeCode()+",";
		}
		s=s+")";
		
		this.setFTCode(s);
	}
}
