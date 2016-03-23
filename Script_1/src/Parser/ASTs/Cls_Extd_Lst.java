package Parser.ASTs;

import java.util.*;

import Parser.*;
import Parser.TypeSys.*;

public class Cls_Extd_Lst extends AST {
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
		if(isE){
			return true;
		}
		this.extd_types=new LinkedList<T_Class>();
		for(TypeExp_Idn t:exts){
			if(!t.checkType(codegen))return false;
			T_Type t1=codegen.getTypeSymTb(t.rst_type);
			if(!t1.isCls())return false;
			this.extd_types.add((T_Class)t1);			
		}
		return true;
	}
}
