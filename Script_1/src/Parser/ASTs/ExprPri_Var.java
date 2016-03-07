package Parser.ASTs;

import Parser.AST;

public class ExprPri_Var extends AST {
	String name;
	String tmp_addr;
	String ref_type;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		this.tmp_addr="$"+this.name;
	}
	

}
