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
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		return true;
	}
}
