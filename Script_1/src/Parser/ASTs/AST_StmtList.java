package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_StmtList extends AST {
	private AST_StmtList list;
	private AST_Stmt stmt;
	public boolean setList(AST_StmtList list){
		this.list=list;
		return true;
	}
	public boolean setStmt(AST_Stmt stmt){
		this.stmt=stmt;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		// TODO Auto-generated method stub
		list.eval(interpreter);
		stmt.eval(interpreter);
		return false;
	}

}
