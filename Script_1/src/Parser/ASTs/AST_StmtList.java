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
	public boolean genCode(CodeGenerator codegen){
		for(AST stmt:this.stmt_list){
			stmt.genCode(codegen);
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen){
		if(this.isMerged()){
			AST_StmtList ast1=(AST_StmtList)this.getDeMrg(codegen);
			this.stmt_list=ast1.stmt_list;
			return true;
		}
		for(int i=0;i<this.stmt_list.size();i++){
			AST stmt=this.stmt_list.get(i);
			if(stmt.isMerged()){
				AST_Stmt ast1=(AST_Stmt)this.getDeMrg(codegen);
				if(ast1==null)
					return false;
				this.stmt_list.set(i,ast1);
				continue;
			}
			if(!stmt.checkType(codegen)){
				return false;
			}
		}
		return true;
	}
}
