package Parser;

import java.util.*;

class AST {
	String num_type;
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
	int symbol_count;
	ArrayList<Integer> parameters=new ArrayList<Integer>();
}

class Symbol{		//why use new symbol class instead of parsergenerator's symbol? 
	AST ast;		//because here is more simple without many thing unnecessary
	String type;
	String name;
	int index;
	static int sn=0;//will change to object factory
	Symbol(){
		index=sn++;
	}
}

class Grammar{
	String head;
	int symbol_count;
	ArrayList<String> symbols=new ArrayList<String>();
}