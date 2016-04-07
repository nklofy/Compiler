package Parser.ASTs;

import java.util.*;

import Parser.*;
import Parser.TypeSys.*;

public class Gnrc_ArgLst extends AST {
	Gnrc_ArgLst pre_args;
	TypeExp var;
	TypeExp_Idn ext_idn_t;
	LinkedList<String>types_name;
	String args_sig;
	int size;
	
	public boolean setGnrcArgs(Gnrc_ArgLst pre_args,TypeExp var,TypeExp_Idn idn_type){
		this.pre_args=pre_args;
		this.var=var;
		this.ext_idn_t=idn_type;		
		return true;
	}
	
	public LinkedList<String> getTypesName() {
		return types_name;
	}
	public void setTypesName(LinkedList<String> gnrc_args) {
		this.types_name = gnrc_args;
	}
	public String getArgsSig() {
		return args_sig;
	}
	public void setArgsSig(String arg_types) {
		this.args_sig = arg_types;
	}

	public boolean genCode(CodeGenerator codegen){
		
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		if(!this.pre_args.genSymTb(codegen))return false;
		if(this.pre_args!=null){
			this.types_name=this.pre_args.types_name;
			this.types_name.add(var.rst_type);
		}else{
			this.types_name=new LinkedList<String>();
			this.types_name.add(var.rst_type);
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		return this.pre_args.checkType(codegen)&&this.var.checkType(codegen);//in future, add constraints on var's types
	}
}
