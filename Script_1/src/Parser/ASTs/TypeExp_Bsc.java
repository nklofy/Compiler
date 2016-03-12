package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class TypeExp_Bsc extends AST {	
	String b_type;
	String ret_type;
	
	public void setTypeT(String b_type) {
		this.b_type =b_type;
	}
	public boolean genCode(CodeGenerator codegen){
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
	public boolean checkType(CodeGenerator codegen){
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
