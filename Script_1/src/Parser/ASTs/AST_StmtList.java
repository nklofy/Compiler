package Parser.ASTs;

import java.util.*;

import Interpreter.Interpreter;
import Parser.*;

public class AST_StmtList extends AST {
	private ArrayList<AST> stmt_list=new ArrayList<AST>();
	int lb_start;
	int lb_end;
	public void addStmt(AST stmt){
		this.stmt_list.add(stmt);
		this.upAll(stmt);
	}
	public boolean genCode(CodeGenerator codegen){
		for(AST stmt:this.stmt_list){
			stmt.genCode(codegen);
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		boolean rst=true;
		for(int i=0;i<this.stmt_list.size();i++){
			AST stmt=this.stmt_list.get(i);
			
			if(!stmt.checkType(codegen)){
				//TODO try recover
				rst=false;
				continue;
			}
		}
		return rst;
	}
}
