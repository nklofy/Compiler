package Parser;
import java.util.*;

import Interpreter.*;

public class AST {
	private String type;
	//private HashSet<AST> merged_asts;
	public String getAST_Type() {
		return type;
	}
	public void setAST_Type(String type) {
		this.type = type;
	}
/*	public void addMgd(AST ast){
		if(this.merged_asts==null){
			this.merged_asts=new HashSet<AST>();
		}
		this.merged_asts.add(ast);
	}
	public HashSet<AST> getAllMgd(){
		return this.merged_asts;
	}*/
	//public boolean eval(Interpreter interpreter){return true;}
	public boolean genCode(CodeGenerator codegen){
		return true;
	}
}