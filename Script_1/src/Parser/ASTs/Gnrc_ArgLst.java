package Parser.ASTs;

import java.util.*;

import Parser.*;
import Parser.TypeSys.*;

public class Gnrc_ArgLst extends AST {
	Gnrc_ArgLst pre_args;
	TypeExp var;
	TypeExp_Idn ext_idn_t;
	LinkedList<String>gnrc_args;
	String arg_types;
	int size;
	
	public boolean setGnrcArgs(Gnrc_ArgLst pre_args,TypeExp var,TypeExp_Idn idn_type){
		this.pre_args=pre_args;
		this.var=var;
		this.ext_idn_t=idn_type;		
		return true;
	}
	
	public LinkedList<String> getGnrcArgs() {
		return gnrc_args;
	}
	public void setGnrcArgs(LinkedList<String> gnrc_args) {
		this.gnrc_args = gnrc_args;
	}
	public String getArgTypes() {
		return arg_types;
	}
	public void setArgTypes(String arg_types) {
		this.arg_types = arg_types;
	}

	public boolean genCode(CodeGenerator codegen){
		
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		if(!this.pre_args.genSymTb(codegen))return false;
		if(this.pre_args!=null){
			this.gnrc_args=this.pre_args.gnrc_args;
			this.gnrc_args.add(var.rst_type);
		}else{
			this.gnrc_args=new LinkedList<String>();
			this.gnrc_args.add(var.rst_type);
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		return this.pre_args.checkType(codegen)&&this.var.checkType(codegen);//in future, add constraints on var's types
	}
}
