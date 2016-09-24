package Parser.ASTs;

import java.util.*;

import Parser.*;
import Parser.TypeSys.*;

public class MbrDef_Lst extends AST {
	boolean isE=false;
	LinkedList<MbrDef> mbrs;
	LinkedList<R_Variable> fields;
	LinkedList<R_Function> methods; 
	
	public void addMbr(MbrDef par){
		if(this.mbrs==null){
			this.mbrs=new LinkedList<MbrDef>();
			this.fields=new LinkedList<R_Variable>();
			this.methods=new LinkedList<R_Function>();
		}
		this.mbrs.add(par);
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
		for(MbrDef mbr:mbrs){
			if(!mbr.genSymTb(codegen))
				return false;
			if(mbr.getASTType().equals("MbrDef_Fld")){
				this.fields.addAll(mbr.r_vars);
			}else if(mbr.getASTType().equals("MbrDef_Mthd")){
				this.methods.add(mbr.r_func);
			}else
				return false;
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		
		return true;
	}
}
