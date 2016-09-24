package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class TypeExp_Bsc extends AST {	
	String b_type;
	String rst_type;
	T_Type t_type;
	
	public void setTypeT(String b_type) {
		this.b_type =b_type;
		this.rst_type=this.b_type;
	}
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{		
		this.t_type=codegen.getTypeInSymTb(this.b_type);
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		switch(this.b_type){
		case "int":			
			break;
		case "double":			
			break;
		case "bool":			
			break;
		case "string":			
			break;
		case "char":			
			break;
		default:
			return false;		
		}
		return true;
	}
}
