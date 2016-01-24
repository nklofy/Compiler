package Parser.ASTs;

import java.util.*;
import Parser.*;

public class AST_StmtList extends AST {
	private ArrayList<AST> stmt_list=new ArrayList<AST>();
	public void addStmt(AST stmt){
		this.stmt_list.add(stmt);
		if(stmt.getMergedAsts()!=null){
			return;
		}
		for(String s:stmt.getVarUp()){
			if(this.getVarTb().keySet().contains(s)){
				System.out.println("error existing symbol name: "+ s);
			}else{
				this.putVarTb(s, stmt.getVarTb().get(s));
			}
		}
		for(String s:stmt.getTypeUp()){
			if(this.getTypeTb().keySet().contains(s)){
				System.out.println("error existing symbol name: "+ s);
			}else{
				this.putTypeTb(s, stmt.getTypeTb().get(s));
			}
		}
		for(String s:stmt.getFuncUp()){
			if(this.getFuncTb().keySet().contains(s)){
				System.out.println("error existing symbol name: "+ s);
			}else{
				this.putFuncTb(s, stmt.getFuncTb().get(s));
			}
		}
	}
	public boolean genCode(CodeGenerator codegen){
		for(AST stmt:stmt_list){
			stmt.genCode(codegen);
		}
		return true;
	}
}
