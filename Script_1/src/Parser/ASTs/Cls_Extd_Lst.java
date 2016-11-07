package Parser.ASTs;

import java.util.*;

import Parser.*;
import Parser.TypeSys.*;

public class Cls_Extd_Lst extends AST {
	boolean isE=false;
	LinkedList<TypeExp_Idn> exts;
	LinkedList<String> extd_types;
	
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
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{		
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		if(isE){
			return true;
		}
		this.extd_types=new LinkedList<String>();
		HashSet<String> all_t=new HashSet<String>();
		for(TypeExp_Idn t:exts){
			if(!t.genSymTb(codegen))return false;
			if(all_t.contains(t.rst_type))
					throw new GenSymTblException("gensymtable error: "+t.rst_type);
			this.extd_types.add(t.rst_type);
			all_t.add(t.rst_type);
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		if(isE){
			return true;
		}
		
		return true;
	}
}
