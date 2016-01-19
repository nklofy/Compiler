package Parser;

import java.util.*;


public class Path{
	ParseState path_start;	//start of reduce
	ParseState path_end;	//end of reduce, begin of goto, 1 more than grammar symbs count
	int path_count; 	//depth in reduce path
	Path pre_path;
	Symbol crt_symbol=null;
	public LinkedList<Symbol> getPath(){			
		LinkedList<Symbol> symbs=new LinkedList<Symbol>();
		symbs.add(crt_symbol);
		Path pre_t=pre_path;
		while(pre_t!=null){
			symbs.add(pre_t.crt_symbol);
			pre_t=pre_t.pre_path;
		}
		return symbs;						
	}
	public Path addSymbol(Symbol symb){
		if(this.crt_symbol==null){
			this.crt_symbol=symb;
			return this;
		}
		Path path_t=new Path();
		path_t.pre_path=this;
		path_t.crt_symbol=symb;
		path_t.path_start=this.path_start;
		return path_t;
	}
}

