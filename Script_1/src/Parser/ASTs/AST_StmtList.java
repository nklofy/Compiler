package Parser.ASTs;

import java.util.*;
import Parser.*;

public class AST_StmtList extends AST {
	private ArrayList<AST> stmt_list=new ArrayList<AST>();
	public void addStmt(AST stmt){
		this.stmt_list.add(stmt);
		//symtable
	}
	public boolean genCode(CodeGenerator codegen){
		for(AST stmt:stmt_list){
			stmt.genCode(codegen);
		}
		return true;
	}
}
