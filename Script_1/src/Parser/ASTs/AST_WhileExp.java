package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_WhileExp extends AST {
	private AST_BoolExp bool_exp;
	private AST_StmtList stmt_list;
	public boolean setWhileExp(AST_BoolExp bool_exp,AST_StmtList stmt_list){
		this.bool_exp=bool_exp;
		this.stmt_list=stmt_list;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		// TODO Auto-generated method stub
		return false;
	}

}
