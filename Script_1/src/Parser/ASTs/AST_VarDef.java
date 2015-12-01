package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_VarDef extends AST {
	private AST_TypeExp type_exp;
	private AST_VarDef var_def;
	private AST_Var var;
	public boolean setTypeExp(AST_TypeExp type_exp){
		this.type_exp=type_exp;
		return true;
	}
	public boolean setVarDef(AST_VarDef var_def){
		this.var_def=var_def;
		return true;
	}
	public boolean setVar(AST_Var var){
		this.var=var;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		// TODO Auto-generated method stub
		return false;
	}

}
