package Parser.ASTs;

import java.util.*;

import Parser.*;
import Parser.TypeSys.T_Class;

public class Extd_Lst extends AST {
	boolean isE=false;
	LinkedList<TypeExp_Idn> exts;
	LinkedList<T_Class> extd_types;
	
	public void addExtd(TypeExp_Idn par){
		if(this.exts==null){
			this.exts=new LinkedList<TypeExp_Idn>();			
		}
		this.exts.add(par);
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
