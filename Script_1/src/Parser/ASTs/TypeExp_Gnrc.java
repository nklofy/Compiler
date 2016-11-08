package Parser.ASTs;

import java.util.*;

import Parser.*;
import Parser.IR.IRCode;
import Parser.TypeSys.*;

public class TypeExp_Gnrc extends AST {
	TypeExp_Idn idn_type;
	Gnrc_ArgLst args;
	String rst_type;
	//T_Type t_type;
	
	public boolean setGnrcType(TypeExp_Idn idn_type,Gnrc_ArgLst args){
		this.args=args;
		this.idn_type=idn_type;	
		return true;
	}
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{		
		//IRCode code=new IRCode("GnrcType",this.rst_type,this.idn_type.rst_type,this.args.rst_val);
		//codegen.addCode(code);
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		if(!this.idn_type.genSymTb(codegen))
			return false;
		if(!this.args.genSymTb(codegen))
			return false;	
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{		
		if(!this.idn_type.checkType(codegen))
			return false;
		if(!this.args.checkType(codegen))
			return false;
		T_Type t=codegen.getTypeInSymTb(this.idn_type.rst_type);//TODO
		if(!t.isGnrc())
			throw new TypeCheckException("type error: not gnrc "+this.idn_type.rst_type);
		if(t.getGnrcPars().size()!=this.args.types_name.size())
				throw new TypeCheckException("type error: gnrc par size "+this.idn_type.rst_type);
		LinkedList<String> pars=t.getGnrcPars();
		LinkedList<String> args=this.args.types_name;
		T_Generic t1=new T_Generic();
		t1.setCoreType(this.idn_type.rst_type);
		for(int i=0;i<pars.size();i++){
			t1.getTypeArgTb().put(pars.get(i), args.get(i));
		}
		t1.genTypeSig(codegen);
		this.rst_type=t1.getTypeSig();
		codegen.putTypeInSymTb(this.rst_type, t1);			
		return true;
	}
}
