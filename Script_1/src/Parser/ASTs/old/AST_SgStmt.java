package Parser.ASTs.old;

import Interpreter.Interpreter;
import Parser.AST;
import Parser.TypeSys.old.Type_SgStmt;

public class AST_SgStmt extends AST {
	private Type_SgStmt sg_type;
	private AST_CtrFlw ctr_flw;
	private AST_CalcExp calc_exp;
	private AST_VarAssign var_assign;
	
	public boolean setType(Type_SgStmt type){
		this.sg_type=type;
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
		switch(this.sg_type){
		case CtrFlw:
			interpreter.interpret(this.ctr_flw);
			break;
		case VarAssign:
			interpreter.interpret(this.var_assign);
			break;
		case CalcExp:
			interpreter.interpret(this.calc_exp);
			break;
		}
		return false;
	}

}
