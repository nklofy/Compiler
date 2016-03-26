package Parser.TypeSys;

import java.util.*;
import Parser.*;
import Parser.IR.*;

public class T_Function extends T_Type {
	private T_Type ret_type;
	private LinkedList<T_Type> par_types;
	

	public T_Type getRetType() {
		return ret_type;
	}
	public void setRetType(T_Type ret_type) {
		this.ret_type = ret_type;
	}
	public LinkedList<T_Type> getParTypes() {
		return par_types;
	}
	public void setParTypes(LinkedList<T_Type> par_types) {
		this.par_types = par_types;
	}
	
	public void genTypeCode() {
		String s=this.ret_type.getTypeCode()+"(";
		for(T_Type t:this.par_types){
			s=s+t.getTypeCode()+",";
		}
		s=s+")";
		if(this.isGnrc()){
			s=s+"<"+this.getGnrcPars().size()+">";
		}
		this.setTypeCode(s);
	}
}
