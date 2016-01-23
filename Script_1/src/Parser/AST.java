package Parser;
import java.util.*;

import Interpreter.*;
import Parser.TypeSys.*;

public class AST {
	String type;
	AST ast_deMrg;	//de-merge, choose correct one
	HashSet<AST> merged_asts;	
	SymTable sym_table;
	AST visit_link;		//a link for visiting global or extern symbols
	public String getType() {
		return type;
	}
	public void setType(String type) {
		String[] ss=type.split("\\.");
		this.type = ss[ss.length-1];
	}
	public AST getDeMrg() {
		//de-merge and return 
		return ast_deMrg;
	}

	public AST getLink() {
		return visit_link;
	}
	public void setLink(AST visit_scope) {
		this.visit_link = visit_scope;
	}	

/*	public boolean loadSymTb(AST ast){
		if(ast.merged_asts!=null){
			return false;
		}
		return false;
	}*/
	public HashSet<AST> getMergedAsts() {
		return merged_asts;
	}
	public void setMergedAsts(HashSet<AST> merged_asts) {
		this.merged_asts = merged_asts;
	}
	//public boolean eval(Interpreter interpreter){return true;}
	public boolean genCode(CodeGenerator codegen){
		return true;
	}
	public boolean checkType(SymTable sym_table){
		return true;
	}
}