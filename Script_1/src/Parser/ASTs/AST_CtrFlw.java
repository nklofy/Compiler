package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_CtrFlw extends AST {
	private String type;
	private AST_CalcExp calc_exp;
	public boolean setType(String type){
		this.type=type;
		return true;
	}
	public boolean setCalcExp(AST_CalcExp exp){
		this.calc_exp=exp;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		// TODO Auto-generated method stub
		return false;
	}

}
