package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_MulExp extends AST {
	private AST_MulExp mul_exp;
	private AST_PriExp pri_exp;
	private String opt;
	public boolean setMulExp(AST_MulExp mul_exp, String opt, AST_PriExp pri_exp){
		this.mul_exp=mul_exp;
		this.pri_exp=pri_exp;
		this.opt=opt;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		// TODO Auto-generated method stub
		return false;
	}

}
