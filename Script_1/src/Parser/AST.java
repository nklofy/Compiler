package Parser;
import java.util.*;

import Interpreter.*;
import Parser.TypeSys.Data_Obj;

class AstRule{		//currently just method name is used
	AstRule(){}
	AstRule(String method){		
		this.method=method;
	}
	String method;
	int symbol_count;
	ArrayList<Integer> parameters=new ArrayList<Integer>();
}

class Symbol{		//symbol in graph stack	
	AST ast;
	LinkedList<AST> asts;
	String type;
	String name;
	String value;
	public String getValue() {
		return value;
	}	
}

class ParseState{		//graph stack
	ParseState pre_state;
	LinkedList<ParseState> pre_states;
	Symbol symbol;
	int state_sn=-1;
	int out_count=0;
	int det_depth=0;
}

class Grammar{		//grammar production
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