package Parser.ASTs;

import java.util.*;

import Interpreter.Interpreter;
import Parser.*;

public class AST_StmtList extends AST {
	private ArrayList<AST> stmt_list=new ArrayList<AST>();
	public void addStmt(AST stmt){
		this.stmt_list.add(stmt);
		if(stmt.getMergedAsts()!=null){
			return;
		}
		this.upAll(stmt);
	}
	public boolean eval(Interpreter interpreter){return true;}
	public boolean genCode(CodeGenerator codegen){
		return true;
	}
	public boolean checkType(){
		return true;
	}
}
