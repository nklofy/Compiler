package Parser.ASTs;

import java.util.*;

import Parser.*;
import Parser.TypeSys.*;

public class MbrDef extends AST {
	MbrDef_Fld fld;
	MbrDef_Mthd mthd;
	LinkedList<R_Variable> r_vars;
	R_Function r_func;
	
	public MbrDef(){}
	public MbrDef(MbrDef_Fld fld){
		this.fld=fld;
		this.setASTType(MbrDef_Fld.class.getName());
	}
	public MbrDef(MbrDef_Mthd mthd){
		this.mthd=mthd;
		this.setASTType(MbrDef_Mthd.class.getName());
	}
	public boolean setMbr(AST ast){
		switch(this.getASTType()){
		case "MbrDef_Fld":
			this.fld=(MbrDef_Fld)ast;			
			break;
		case "MbrDef_Mthd":
			this.mthd=(MbrDef_Mthd)ast;
			break;
		}
		return true;
	}
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		switch(this.getASTType()){
		case "MbrDef_Fld":
			if(!this.fld.genCode(codegen))
				return false;
			break;
		case "MbrDef_Mthd":
			this.mthd.setScope(this.scope);
			if(!this.mthd.genCode(codegen))
				return false;
			break;
		default:return false;
		}
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		switch(this.getASTType()){
		case "MbrDef_Fld":
			if(!this.fld.genSymTb(codegen))
				return false;
			break;
		case "MbrDef_Mthd":
			this.mthd.setScope(this.scope);
			if(!this.mthd.genSymTb(codegen))
				return false;
			break;
		default:return false;
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		switch(this.getASTType()){
		case "MbrDef_Fld":
			if(!this.fld.checkType(codegen)) return false;
			this.r_vars=this.fld.var_def.r_vars;
			break;
		case "MbrDef_Mthd":
			if(!this.mthd.checkType(codegen)) return false;	
			this.r_func=this.mthd.func_def.r_func;	
			break;
		default:return false;
		}
		return true;
	}
}
