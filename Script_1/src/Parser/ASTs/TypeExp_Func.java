package Parser.ASTs;

import Parser.*;
import Parser.TypeSys.*;

public class TypeExp_Func extends AST {
	//String ret_type;
	T_Function t_type;
	boolean isFixed;
	String rst_type;
	String ref_type;
	
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		this.rst_type="function";
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		
		return true;
	}
}
