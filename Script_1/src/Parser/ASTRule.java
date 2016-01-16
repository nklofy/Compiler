package Parser;

import java.util.ArrayList;

public class ASTRule {
	ASTRule(){}
	ASTRule(String method){		
		this.method=method;
	}
	String method;
	int symbol_count;
	ArrayList<Integer> parameters=new ArrayList<Integer>();
}