package Parser;

import java.util.*;

public class ParseState {//graph stack
	boolean fixed=true;	//fixed or ambiguous
	ParseState pre_state;
	LinkedList<ParseState> pre_states;	//multi path
	Symbol symbol;
	ArrayList<Symbol> symbols;		//corresponding to multi path
	int state_sn=-1;
	int out_count=0;
	int det_depth=0;
	boolean addLink(ParseState pre_state,Symbol symbol){		
		if(this.pre_state==null){
			if(this.fixed){
				this.pre_state=pre_state;
				this.symbol=symbol;
			}else{
				//TODO merge
				
				this.pre_states.add(pre_state);
				this.symbols.add(symbol);
			}			
		}else{
			if(this.fixed){
				//TODO merge
				
				this.fixed=false;
				this.pre_states=new LinkedList<ParseState>();
				this.symbols=new ArrayList<Symbol>();
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