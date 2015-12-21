package Parser;
import java.util.*;

import Interpreter.*;
import Parser.TypeSys.Data_Obj;

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
	AST ast;		//because it is simpler without many unnecessary things 
	String type;
	String name;
	String num_value;
	public String getNumValue() {
		return num_value;
	}
	
}

class Grammar{
	String head;
	int symbol_count;
	ArrayList<String> symbols=new ArrayList<String>();
}

public abstract class AST {
	public abstract boolean eval(Interpreter interpreter);
	public Data_Obj getV(Interpreter interpreter){
		Data_Obj obj=interpreter.getCrtFrm().getCrtEnv().getTmpV(this);
		return obj;
	}
}