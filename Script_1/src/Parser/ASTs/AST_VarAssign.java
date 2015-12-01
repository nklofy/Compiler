package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_VarAssign extends AST {
	private AST_VarAssign var_assign;
	private AST_TypeExp type_exp;
	private AST_Var var;
	private AST_CalcExp calc_exp;
	private String opt;
	public boolean setVarAssign(AST_VarAssign var_assign){
		this.var_assign=var_assign;
		return true;
	}
	public boolean setTypeExp(AST_TypeExp type_exp){
		this.type_exp=type_exp;
		return true;
	}
	public boolean setVar(AST_Var var){
		this.var=var;
		return true;
	}
	public boolean setOpt(String opt){
		this.opt=opt;
		return true;
	}
	public boolean setCalcExp(AST_CalcExp calc_exp){
		this.calc_exp=calc_exp;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		// TODO Auto-generated method stub
		return false;
	}

}
