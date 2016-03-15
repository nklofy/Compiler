package Parser.ASTs;

import java.util.*;

import Parser.*;
import Parser.IR.IRCode;
import Parser.TypeSys.*;

public class TypeExp_Gnrc extends AST {
	TypeExp_Idn idn_type;
	Gnrc_ArgLst args;
	String ret_type;
	
	public boolean setGnrcType(TypeExp_Idn idn_type,Gnrc_ArgLst args){
		this.args=args;
		this.idn_type=idn_type;	
		return true;
	}
	public boolean genCode(CodeGenerator codegen){		
		IRCode code=new IRCode("GnrcType",this.ret_type,this.idn_type.ret_type,this.args.ret_args);
		codegen.addCode(code);
		codegen.incLineNo();
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		if(!this.idn_type.checkType(codegen))
			return false;
		if(!this.args.checkType(codegen))
			return false;
		T_Type t=codegen.getRTType(this.idn_type.ret_type);//class or interface or function?
		if(!t.isGnrc)
			return false;		
		if(t.Pars_Gnrc.size()!=this.args.gnrc_args.size())
			return false;
		this.ret_type="<"+codegen.getTmpSn();
		LinkedList<String> pars=t.Pars_Gnrc;
		LinkedList<T_Type> args=this.args.gnrc_args;
		T_Generic t1=new T_Generic();
		t1.core_type=t;
		for(int i=0;i<pars.size();i++){
			t1.type_args.put(pars.get(i), args.get(i));
		}
		codegen.addRTType(this.ret_type, t1);
		this.args.ret_args=this.args.gnrc_args.remove().type_name;
		for(int i=1;i<this.args.gnrc_args.size();i++){
			this.args.ret_args+=","+this.args.gnrc_args.remove();
		}
		return true;
	}
}
