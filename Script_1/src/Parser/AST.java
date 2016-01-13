package Parser;
import java.util.*;

import Interpreter.*;
import Interpreter.old.Interpreter;
import Parser.TypeSys.old.Data_Obj;

class AstRule{		//currently just method name is used
	AstRule(){}
	AstRule(String method){		
		this.method=method;
	}
	String method;
	int symbol_count;
	ArrayList<Integer> parameters=new ArrayList<Integer>();
}

class Symbol{		//symbol in graph stack correspond to reduce path or merged paths
	AST ast;
	int line;
	ParseState path_start;	//start of reduce
	ParseState path_end;	//end of reduce
	int path_count; 	//edges in reduce path
	String type;
	String name;
	String value;
	///public String getValue() {
	//	return value;
	//}
}

class ParseState{		//graph stack
	boolean fixed=true;	//fixed or ambiguous
	ParseState pre_state;
	LinkedList<ParseState> pre_states;	//multi path
	Symbol symbol;
	LinkedList<Symbol> symbols;		//corresponding to multi path
	int state_sn=-1;
	int out_count=0;
	int det_depth=0;
	boolean addLink(ParseState pre_state,Symbol symbol){
		if(this.pre_state==null){
			if(this.fixed){
				this.pre_state=pre_state;
				this.symbol=symbol;
			}else{
				this.pre_states.add(pre_state);
				this.symbols.add(symbol);
			}			
		}else{
			if(this.fixed){
				this.fixed=false;
				this.pre_states=new LinkedList<ParseState>();
				this.symbols=new LinkedList<Symbol>();
				this.pre_states.add(this.pre_state);
				this.symbols.add(this.symbol);
				this.pre_states.add(pre_state);
				this.symbols.add(symbol);
				this.pre_state=null;
				this.symbol=null;
			}else{
				return false;
			}
		}
		return true;
	}
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