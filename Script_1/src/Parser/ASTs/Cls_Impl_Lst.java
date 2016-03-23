package Parser.ASTs;

import java.util.*;

import Parser.*;
import Parser.TypeSys.R_Function;
import Parser.TypeSys.T_Class;
import Parser.TypeSys.T_Interface;
import Parser.TypeSys.T_Type;

public class Cls_Impl_Lst extends AST {
	boolean isE=false;
	LinkedList<TypeExp_Idn> imps;
	LinkedList<T_Interface> impl_types;
	
	public void addImp(TypeExp_Idn par){
		if(this.imps==null){
			this.imps=new LinkedList<TypeExp_Idn>();			
		}
		this.imps.add(par);
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
	public boolean checkType(CodeGenerator codegen){
		if(isE){
			return true;
		}
		this.impl_types=new LinkedList<T_Interface>();
		for(TypeExp_Idn t:imps){
			if(!t.checkType(codegen))return false;
			T_Type t1=codegen.getTypeInSymTb(t.rst_type);
			if(!t1.isIntf())return false;
			this.impl_types.add((T_Interface)t1);			
		}
		return true;
	}
}
