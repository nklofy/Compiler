package Parser.ASTs;

import Parser.*;
import Parser.IR.*;
import Parser.TypeSys.*;

public class ExprPri_Str extends AST {
	String str;
	String rst_val;
	String ref_type;
	String rst_type;
	
	public void setStr(String value) {
		this.str=value;		
	}
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		IRCode code=new IRCode("load_s",this.rst_val,null,null);
		codegen.addCode(code);
		code=new IRCode(this.str,null,null,null);
		codegen.addCode(code);
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		this.rst_type="string";
		this.rst_val="%"+codegen.getTmpSn();
		R_Variable r=new R_Variable();
		r.setVarName(this.rst_val);
		r.setTmpAddr(this.rst_val);
		r.setVarType(this.rst_type);
		codegen.putVarInSymTb(this.rst_val, r);
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		if(!this.ref_type.equals("string")){
			return false;
		}
		return true;
	}
}
