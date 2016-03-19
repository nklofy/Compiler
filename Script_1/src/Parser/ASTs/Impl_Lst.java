package Parser.ASTs;

import java.util.*;

import Parser.*;
import Parser.TypeSys.T_Interface;

public class Impl_Lst extends AST {
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
		
		return true;
	}
}
