package Parser.ASTs;

import java.util.*;

import Parser.*;
import Parser.TypeSys.*;

public class Gnrc_ArgLst extends AST {	
	Gnrc_ArgLst pre_args;
	TypeExp_Idn var;
	TypeExp_Idn ext_idn_t;
	LinkedList<T_Type>gnrc_args;
	String ret_args;
	
	public boolean setGnrcArgs(Gnrc_ArgLst pre_args,TypeExp_Idn var,TypeExp_Idn idn_type){
		this.pre_args=pre_args;
		this.var=var;
		this.ext_idn_t=idn_type;		
		return true;
	}
	public boolean genCode(CodeGenerator codegen){
		
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		if(!this.pre_args.checkType(codegen))return false;
		if(this.pre_args!=null){
			this.gnrc_args=this.pre_args.gnrc_args;
			T_Type t=codegen.getTypeSymTb(var.rst_type);
			if(t==null)return false;
			this.gnrc_args.add(t);
		}else{
			this.gnrc_args=new LinkedList<T_Type>();
			T_Type t=codegen.getTypeSymTb(var.rst_type);
			if(t==null)return false;
			this.gnrc_args.add(t);
		}
		return true;
	}
}
