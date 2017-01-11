package Parser.ASTs;

import Parser.*;
import Parser.IR.*;
import Parser.TypeSys.*;

public class ExprPri_Chr extends AST {
	String rst_val;
	String rst_chr;
	String ref_type;
	String rst_type;
	
	public void setChr(String value) {
		this.rst_chr=value;
	}
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		IRCode code;
		if(this.ref_type.equals("char")){
			code=new IRCode("load_c", this.rst_val, this.rst_chr, null);
			codegen.addCode(code);
		}else if(this.ref_type.equals("string")){
			code=new IRCode("load_s", this.rst_val, this.rst_chr, null);
			codegen.addCode(code);			
		}
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		this.rst_val="%"+codegen.getTmpSn();
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		if(this.rst_chr.length()!=1)
			return false;
		if(!this.ref_type.equals("char")&&!this.ref_type.equals("string")){
			return false;
		}
		this.rst_type=this.ref_type;
		return true;
	}
}
