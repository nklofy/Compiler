package Parser.ASTs;

import Parser.AST;
import Parser.CodeGenerator;
import Parser.TypeSys.GenCodeException;
import Parser.TypeSys.GenSymTblException;
import Parser.TypeSys.TypeCheckException;

public class ExprPri_Chr extends AST {
	String rst_val;
	String ref_type;
	String rst_type;
	
	public void setChr(String value) {
		this.rst_val=value;
	}
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		if(this.rst_val.length()!=1)
			return false;
		if(!this.ref_type.equals("char")&&!this.ref_type.equals("string")){
			return false;
		}
		this.rst_type=this.ref_type;
		return true;
	}
}
