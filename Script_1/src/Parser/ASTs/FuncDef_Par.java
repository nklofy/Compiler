package Parser.ASTs;

import Parser.*;
import Parser.IR.*;
import Parser.TypeSys.*;

public class FuncDef_Par extends AST {
	TypeExp type;
	ExprPri_Var var;
	public FuncDef_Par(TypeExp type,ExprPri_Var var){
		this.type=type;
		this.var=var;
	}
	public boolean genCode(CodeGenerator codegen)throws GenCodeException{
		IRCode code=new IRCode("defFuncPar", this.type.rst_type, this.var.rst_val, null);
		codegen.addCode(code);
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen)throws GenSymTblException{
		if(!this.type.genSymTb(codegen))
			return false;
		if(!this.var.genSymTb(codegen))
			return false;
		return true;
	}
	public boolean checkType(CodeGenerator codegen)throws TypeCheckException{
		if(this.type.checkType(codegen))
			return false;
		if(this.var.checkType(codegen))
			return false;
		return true;
	}
}
