package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class TypeExp_Func extends AST {
	//String ret_type;
	//T_Type t_type;
	
	public boolean genCode(CodeGenerator codegen){
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		return true;
	}
}
