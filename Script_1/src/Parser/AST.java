package Parser;
import java.util.*;

import Interpreter.*;
import Parser.TypeSys.*;

public class AST {
	String type;
	AST ast_deMrg;	//de-merge, choose correct one
	HashSet<AST> merged_asts;
	HashMap<String,R_Record> symtbl_up;		//symbols trans up
	HashMap<String,R_Record> symtbl_down;	//symbols trans down
	AST visit_scope;		//a link for visiting global or extern symbols
	public String getAST_Type() {
		return type;
	}
	public void setAST_Type(String type) {
		this.type = type;
	}
	//public boolean eval(Interpreter interpreter){return true;}
	public boolean genCode(CodeGenerator codegen){
		return true;
	}
}