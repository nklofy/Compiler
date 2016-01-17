package Parser.ASTs;

import Parser.AST;

public class AST_Stmt extends AST {
	Type type;
	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	public enum Type{cls_def,itf_def,func_def,if_stmt,while_stmt,sg_stmt;}
}
