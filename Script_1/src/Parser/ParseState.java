package Parser;

import java.util.*;

public class ParseState {//graph stack
	boolean isFixed=true;	//fixed or ambiguous
	ParseState pre_state;
	ArrayList<ParseState> pre_states;	//multi path
	Symbol symbol;
	ArrayList<Symbol> symbols;		//corresponding to multi path
	int state_sn=-1;
	int out_count=0;
	int det_depth=0;
	boolean addLink(ParseState pre_state,Symbol symbol){
		if(this.isFixed ){
			if(this.pre_state==null){
				this.pre_state=pre_state;
				this.symbol=symbol;
			}else{
				if(this.symbol.name.equals(symbol.name) && this.pre_state==pre_state){//merge
					this.symbol.merge(symbol);
					return false;
				}
				this.isFixed=false;
				this.pre_states=new ArrayList<ParseState>();
				this.symbols=new ArrayList<Symbol>();
				this.pre_states.add(this.pre_state);
				this.symbols.add(this.symbol);
				this.pre_states.add(pre_state);
				this.symbols.add(symbol);
			}			
		}else{
			for(int i=0;i<this.pre_states.size();i++){
				if(this.pre_states.get(i)==pre_state && this.symbols.get(i).name.equals(symbol.name)){//merge
					this.symbols.get(i).merge(symbol);
					return false;
				}
			}
			this.pre_states.add(pre_state);
			this.symbols.add(symbol);
		}
		return true;
	}
}