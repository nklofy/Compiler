package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_IfExp extends AST {
	private AST_IfStmt if_stmt;
	private AST_ElseStmt else_stmt;
	public boolean setIfExp(AST_IfStmt if_stmt, AST_ElseStmt else_stmt){
		this.if_stmt=if_stmt;
		this.else_stmt=else_stmt;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		// TODO Auto-generated method stub
		return false;
	}

}
