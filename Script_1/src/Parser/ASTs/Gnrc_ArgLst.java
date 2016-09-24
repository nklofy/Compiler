package Parser.ASTs;

import java.util.*;

import Parser.*;
import Parser.TypeSys.*;

public class Gnrc_ArgLst extends AST {
	Gnrc_ArgLst pre_args;
	TypeExp var;
	TypeExp_Idn ext_idn_t;
	LinkedList<String>types_name;
	String rst_val;
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
/*	public String getArgsSig() {
		return rst_val;
	}
	public void setArgsSig(String arg_types) {
		this.rst_val = arg_types;
	}
*/
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
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
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		if(!this.pre_args.checkType(codegen))
			return false;
		if(!this.var.checkType(codegen))
			return false;
		String s="";
		int c=this.types_name.size();
		for(int i=0;i<c;i++){
			String s1=this.types_name.remove();
			if(codegen.getTypeInSymTb(s1)==null)
				return false;
			s+=s1+",";
		}
		this.rst_val=s;
		return true;
	}
}
