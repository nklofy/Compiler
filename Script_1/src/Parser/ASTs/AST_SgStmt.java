package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_SgStmt extends AST {
	private Type_SgStmt type;
	private AST_CtrFlw ctr_flw;
	private AST_CalcExp calc_exp;
	private AST_VarAssign var_assign;
	
	public boolean setType(Type_SgStmt type){
		this.type=type;
		return true;
	}
	public boolean setCtrFlw(AST_CtrFlw ctr_flw){
		this.ctr_flw=ctr_flw;
		return true;
	}
	public boolean setCalcExp(AST_CalcExp exp){
		this.calc_exp=exp;
		return true;
	}
	public boolean setVarAssign(AST_VarAssign exp){
		this.var_assign=exp;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		// TODO Auto-generated method stub
		return false;
	}

}
