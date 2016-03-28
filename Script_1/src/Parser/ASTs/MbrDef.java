package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class MbrDef extends AST {
	MbrDef_Fld fld;
	MbrDef_Mthd mthd;
	R_Variable r_var;
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
	public boolean genCode(CodeGenerator codegen){
		
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		switch(this.getASTType()){
		case "MbrDef_Fld":
			if(!this.fld.genSymTb(codegen))
				return false;
			this.r_var=new R_Variable();
			this.r_var
			break;
		case "MbrDef_Mthd":
			if(!this.mthd.genSymTb(codegen))
				return false;
			this.r_func=new R_Function();
			
			break;
		default:return false;
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		switch(this.getASTType()){
		case "MbrDef_Fld":
			return this.fld.checkType(codegen);
			
		case "MbrDef_Mthd":
			return this.mthd.checkType(codegen);
			
		default:return false;
		}
	}
}
