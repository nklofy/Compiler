package Parser.ASTs;

import Parser.AST;
import Parser.CodeGenerator;

public class FuncDef_Par extends AST {
	TypeExp type;
	ExprPri_Var var;
	public FuncDef_Par(TypeExp type,ExprPri_Var var){
		this.type=type;
		this.var=var;
	}
	public boolean genCode(CodeGenerator codegen){
		this.type.genCode(codegen);
		this.var.genCode(codegen);
		return true;
	}
	public boolean genSymTb(CodeGenerator codegen){
		if(!this.type.genSymTb(codegen))
			return false;
		if(!this.var.genSymTb(codegen))
			return false;
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		if(this.type.checkType(codegen))
			return false;
		if(this.var.checkType(codegen))
			return false;
		return true;
	}
}
