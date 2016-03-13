package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class TypeExp_Func extends AST {
	String ret_type;
	
	public boolean genCode(CodeGenerator codegen){
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		return true;
	}
}
