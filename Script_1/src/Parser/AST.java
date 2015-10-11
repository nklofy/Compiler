package Parser;

import java.util.*;

class AST {	
	int int_value;
	double double_value;
	AST left;
	AST right;
	Node node;
	boolean eval(){
		return false;
	}
}

class Node{
	String type;
	String name;
}

class AstRule{
	AstRule(){}
	AstRule(String method){		
		this.method=method;
	}
	String method;
	ArrayList<String> parameters=new ArrayList<String>();
}

class Symbol{		//why use new symbol class instead of parsergenerator's symbol? 
	AST ast;		//because here is more simple without many thing unnecessary
	String type;
	String name;
}

class Grammar{
	String head;
	ArrayList<String> symbols=new ArrayList<String>();
}