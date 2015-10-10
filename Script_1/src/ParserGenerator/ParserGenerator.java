//Parser Generator

package ParserGenerator;

import java.util.*;
import java.io.*;

public class ParserGenerator {
	private ArrayList<ActionTable> gen_action_tables=new ArrayList<ActionTable>();
	private ArrayList<GrammarTable> gen_gr_tables=new ArrayList<GrammarTable>();
	private ArrayList<CC> gen_CCs=new ArrayList<CC>();
	private ArrayList<Grammar> gen_grammars=new ArrayList<Grammar>();
	private Map<String,Symbol> gen_symbols=new HashMap<String,Symbol>();
	private Set<Symbol> gen_tokens=new HashSet<Symbol>();
	private Set<Symbol> gen_NTs=new HashSet<Symbol>();
	private Map<Symbol,ArrayList<Kernel>> gen_kernels=new HashMap<Symbol,ArrayList<Kernel>>();
	static public void main(String[] args){
		System.out.println("start");
		ParserGenerator pg=new ParserGenerator();
		pg.input("grammar.txt");
		pg.generateGrammarTable();
		pg.getFirst();
		pg.getFollow();
		pg.getCCs();
		pg.generateActionTable();
		System.out.println("output");
		pg.outCC("out_cc.txt");
		pg.output("out_grammar.txt");
		System.out.println("end");
	}
		
	private boolean input(String filename) {
		Scanner in = null;
		String word;
		try {			
			in=new Scanner(new BufferedReader(new FileReader(filename)));
			if(in.hasNext())
				word=in.next();
			else return false;
			if(word.equals("//symbols")){
				word=in.next();
				while(!word.equals("//tokens")){
					Symbol symbol=new Symbol(word,false);
					gen_symbols.put(word,symbol);		//a table recording all symbols
					gen_NTs.add(symbol);				//non terminal
					word=in.next();
				}
			}else return false;
			if(word.equals("//tokens")){
				word=in.next();
				while(!word.equals("//grammars")){					
					Symbol symbol=new Symbol(word,true);
					gen_symbols.put(word,symbol);		//symbols table
					gen_tokens.add(symbol);				//token, terminal
					word=in.next();
				}
				gen_NTs.removeAll(gen_tokens);
			}else return false;
			if(word.equals("//grammars")){
				inputGrammars(in);		//record grammar in table
				return true;
			}else return false;					
		} catch (FileNotFoundException e) {
			e.printStackTrace();			
		}finally{
			in.close();
		}
		return false;
	}
	
	private boolean inputGrammars(Scanner in){
		String word=null;
		Symbol head=null;
		LinkedList<String> list=new LinkedList<String>();
		while(in.hasNext()){
			word=in.next();
			if(word.equals("//end")){
				gen_grammars.add(buildGrammar(list));
				in.close();
				return true;
			}
			if(word.equals("->")){
				head=gen_symbols.get(list.removeLast());				
				if(!list.isEmpty())
					gen_grammars.add(buildGrammar(list));
				list.addLast(head.name);
			}else								//if(word.equals("->"))
				list.addLast(word);
			
		}
		return false;
	}
	
	private Grammar buildGrammar(LinkedList<String> list){
		Grammar grammar = new Grammar();
		Production production = new Production();
		String word=null;
		Symbol head = gen_symbols.get(list.removeFirst());
		grammar.head=head;
		grammar.productions.add(production);
		while(!list.isEmpty()){
			word=list.removeFirst();
			if(word.equals("|")){
				production=new Production();
				grammar.productions.add(production);
				continue;
			}
			production.symbols.add(gen_symbols.get(word));						
		}
		head.head_grammar.addAll(grammar.productions);
		return grammar;
	}

	private void generateGrammarTable(){		//generate grammar-table from input grammars
		for(Grammar grr:gen_grammars){
			for(Production prd:grr.productions){
				GrammarTable grt=new GrammarTable();
				grt.head=grr.head.name;
				for(Symbol sym:prd.symbols){					
					grt.symbols.add(sym.name);
				}
				gen_gr_tables.add(grt);
				prd.index_gr_tb=gen_gr_tables.size()-1;
			}
		}
	}
	
	private boolean getFirst(){	//first token set of each symbol
		Symbol sym_e=gen_symbols.get("e");
		for(Symbol sym : gen_tokens){
			sym.First.add(sym);
		}
		int count=gen_grammars.size();
		boolean loop=true;
		while(loop){
			loop=false;
			for(int i=count-1;i>=0;i--){
				Grammar grammar=gen_grammars.get(i);
				Symbol head=grammar.head;
				Set<Symbol> first_set=new HashSet<Symbol>();
				for(Production production : grammar.productions){
					ArrayList<Symbol> symbols=production.symbols;
					int count1=symbols.size();
					for(int j=0;j<count1;j++){
						Symbol sym=symbols.get(j);				
						first_set.addAll(sym.First);	
						if(!sym.First.contains(sym_e)){
							break;
						}
						if(j==count1-1){
							first_set.add(sym_e);
						}
					}//for(int j=0;j<count1;j++)
				}//for(Production production : grammar.productions)
				if(!head.First.containsAll(first_set)){
					head.First.addAll(first_set);
					loop=true;
				}
			}//for(int i=count-1;i>=0;i--)
		}//while(loop)
		return true;
	}
	
	private boolean getFollow(){	//follow token set of each symbol, but not used currently.
		Symbol sym_e=gen_symbols.get("e");
		Symbol sym_eof=gen_symbols.get("eof");
		gen_grammars.get(0).head.Follow.add(sym_eof);
		int count=gen_grammars.size();
		boolean loop=true;
		while(loop){
			loop=false;
			for(int i=count-1;i>=0;i--){
				Grammar grammar=gen_grammars.get(i);
				Symbol head=grammar.head;
				for(Production production : grammar.productions){
					Set<Symbol> follow_set=new HashSet<Symbol>();
					follow_set.addAll(head.Follow);
					ArrayList<Symbol> symbols=production.symbols;
					int count1=symbols.size();
					for(int j=count1-1;j>=0;j--){
						Symbol sym=symbols.get(j);
						if(!sym.isFinal){
							if(!sym.Follow.containsAll(follow_set)){
								sym.Follow.addAll(follow_set);
								loop=true;
							}
							if(sym.First.contains(sym_e)){
								follow_set.addAll(sym.First);
								follow_set.remove(sym_e);
							}else{
								follow_set.clear();
								follow_set.addAll(sym.First);
							}	
						}//if(!sym.isFinal)
						else{
							follow_set.clear();
							follow_set.add(sym);
						}
					}//for(int j=count1-1;j>=0;j--)
				}
			}
		}//while(loop)
		return true;
	}
	
	private boolean getClosure(CC cc){ 		//items closure of the canonical collection
		if(cc.got_Closure)
			return false;
		cc.got_Closure=true;
		Item k_item=cc.kernel_item;
		if(k_item.symbols.size()==k_item.position){
			cc.is_reduce=true;
			cc.items.add(k_item);
			return false;
		}
		Symbol sym_e=gen_symbols.get("e");
		ArrayList<Item> cl_items=new ArrayList<Item>();		//items in closure 
		int index_cl=0;
		cl_items.add(k_item);
		boolean loop=true;
		while(loop){
			if(index_cl>=cl_items.size())
				break;
			k_item=cl_items.get(index_cl++);
			ArrayList<Symbol> symbols=k_item.symbols;
			int position=k_item.position;			
			Symbol sym_head=symbols.get(position);
			if(sym_head.isFinal){
				continue;
			}
			//loop=false;
			Set<Symbol> follow_set=new HashSet<Symbol>();
			int rest_count=symbols.size()-position-1;
			if(rest_count>0){
				int i=1;
				follow_set.addAll(symbols.get(position+i).First);
				while(follow_set.contains(sym_e)){
					follow_set.remove(sym_e);
					if(i==rest_count){
						follow_set.add(k_item.look_ahead);
						break;
					}
					i++;
					follow_set.addAll(symbols.get(position+i).First);				
				}//while(follow_set.contains(sym_e))
			}//if(rest_count>0)
			else if(rest_count==0){
				follow_set.add(k_item.look_ahead);
			}
			//look for a item with sym as head
			for(Production production:sym_head.head_grammar){
				for(Symbol sym:follow_set){
					Item item=new Item(sym_head,production.symbols,0,sym,production.index_gr_tb);				
					if(item.inItemList(cl_items)){
						continue;
					}
					else{
						cl_items.add(item);
						loop=true;
					}
					
				}
			}
		}
		cc.items.addAll(cl_items);
		return true;
	}
	
	private CC getGoto(Item item){	//TODO	
		Item kr_item=new Item(item);
		if(kr_item.position<kr_item.symbols.size()){
			kr_item.position++;
			ArrayList<Kernel> kernels=gen_kernels.get(kr_item.head);//find if kernel is already exist		
			if(kernels!=null){
				for(Kernel it:kernels){
					if(it.hasItem(kr_item)){	
						return it.cc_in;
					}
				}
			}
			CC cc =new CC();
			Kernel kernel=new Kernel();
			kernel.item_in=kr_item;
			if(!gen_kernels.containsKey(kr_item.head)){
				gen_kernels.put(kr_item.head, new ArrayList<Kernel>());
			}
			gen_kernels.get(kr_item.head).add(kernel);
			cc.kernel_item=kr_item;
			cc.index_gr=kr_item.index_gr_tb;
			kernel.cc_in=cc;
			return cc;
		}
		return null;
	}
	
	private boolean getCCs(){//from first cc spread all others and get their token-action map 
		Grammar grammar0=gen_grammars.get(0);
		CC cc0=new CC();
		cc0.kernel_item=new Item();
		cc0.kernel_item.head=grammar0.head;
		cc0.kernel_item.symbols=grammar0.productions.get(0).symbols;
		cc0.kernel_item.position=0;
		cc0.kernel_item.look_ahead=gen_symbols.get("eof");
		cc0.kernel_item.index_gr_tb=grammar0.productions.get(0).index_gr_tb;
		cc0.index_gr=cc0.kernel_item.index_gr_tb;
		getClosure(cc0);
		gen_CCs.add(cc0);
		int index_cc=0;
		boolean loop=true;
		while(loop){
			if(index_cc>=gen_CCs.size()){
				break;
			}
			CC cc=gen_CCs.get(index_cc++);
			if(cc.got_Goto){
				continue;
			}
			cc.got_Goto=true;
			if(cc.kernel_item.position==cc.kernel_item.symbols.size()){	
				cc.is_reduce=true;
				continue;
			}
			for(Item item:cc.items){	
				if(item.position==item.symbols.size()){
					continue;
				}
				CC new_cc=getGoto(item);
				Symbol sym=item.symbols.get(item.position);
				cc.goto_tb.put(sym, new_cc);
				getClosure(new_cc);
				if(!new_cc.in_table){
					gen_CCs.add(new_cc);
					new_cc.in_table=true;
					new_cc.index_cc=gen_CCs.size()-1;
				}
			}
		}
		return true;
	}
		
	private void generateActionTable(){		//action table and goto table
		for(CC cc:gen_CCs){
			ActionTable table=new ActionTable();
			gen_action_tables.add(table);
			for(Symbol token:gen_tokens){
				createActionTable(table,cc,token); //action table
			}
			for(Symbol sym:gen_NTs){
				createGotoTable(table,cc,sym);  //goto table
			}
			
		}
	}
	
	private void createActionTable(ActionTable table,CC cc,Symbol token){//action table
		String key=token.name;
		String value=" ";
		if(cc.goto_tb.containsKey(token)){			
				int obj_cc=cc.goto_tb.get(token).index_cc;
				value="s"+obj_cc;			
		}
		if(cc.is_reduce&&cc.kernel_item.look_ahead.equals(token)){
			int obj_gr=cc.index_gr;
			value="r"+obj_gr;
		}		
		table.action_t.put(key, value);
	}
	
	private void createGotoTable(ActionTable table,CC cc,Symbol sym){//goto table
		String key=sym.name;
		String value=" ";
		if(cc.goto_tb.containsKey(sym)){
			int obj_cc=cc.goto_tb.get(sym).index_cc;
			value="g"+obj_cc;	
		}		
		table.goto_t.put(key, value);
	}
	
	private void output(String filename){
		PrintWriter out=null;
		try {
			out=new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			String line="";
			out.println("//tokens");
			for(Symbol sym:gen_tokens){
				line=line+sym.name+" ";
			}
			out.println(line);
			line="";out.println();
			out.println("//symbols");
			for(Symbol sym:gen_NTs){
				line=line+sym.name+" ";
			}
			out.println(line);
			line="";
			out.println();
			out.println("//grammars");
			int i=0;
			for(GrammarTable table:gen_gr_tables){
				line="";
				for(String str:table.symbols){
					line=line+str+" ";
				}
				out.println(i+" "+table.head+" "+line);
				i++;
			}
			line="";
			out.println();
			out.println("//actions");
			i=0;
			for(ActionTable table:gen_action_tables){				
				for(Symbol token:gen_tokens){
					line=line+table.action_t.get(token.name)+" / ";
				}
				out.println(i+" "+line);
				line="";
				
				i++;
			}
			line="";
			out.println();
			out.println("//gotos");
			i=0;
			for(ActionTable table:gen_action_tables){				
				
				for(Symbol sym:gen_NTs){
					line=line+table.goto_t.get(sym.name)+" / ";	
				}
				out.println(i+" "+line);
				line="";
				i++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
	
	private void outCC(String filename){
		PrintWriter out=null;
		try {
			out=new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			String line="";
			out.println("//first set");out.println();
			//print all first set
			for(Symbol sym:gen_NTs){
				line=sym.name+" :";				
				out.println(line);
				line="";
				for(Symbol fs:sym.First){
					line=line+fs.name+" ";
				}
				out.println(line);
			}
			out.println("");out.println("//all CCs");out.println("");
			//print all CCs
			for(CC cc:gen_CCs){
				line=cc.kernel_item.head.name+" ->";
				out.println(line);
				for(Item item:cc.items){
					line="";
					for(Symbol sym:item.symbols){
						line=line+sym.name+" ";
					}
					line=line+String.valueOf(item.position)+" "+item.look_ahead.name;
					out.println(line);
				}
				out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}		
}

class Symbol{
	Symbol(){}
	Symbol(String name,boolean isFinal){
		this.name=name;
		this.isFinal=isFinal;
	}
	Set<Symbol> First=new HashSet<Symbol>();
	Set<Symbol> Follow=new HashSet<Symbol>();
	ArrayList<Production> head_grammar=new ArrayList<Production>();
	Boolean isFinal;
	String name;
}

class Grammar{
	Grammar(){}
	Grammar(Symbol head, ArrayList<Production> productions){}
	Symbol head;
	ArrayList<Production> productions=new ArrayList<Production>();
}

class Production{
	ArrayList<Symbol> symbols = new ArrayList<Symbol>();
	int index_gr_tb;
}

class Item{
	Item(){}
	Item(Symbol head,ArrayList<Symbol> symbols, int position, Symbol look_ahead, int index_gr_tb){
		this.head=head;
		this.symbols.addAll(symbols);
		this.position=position;
		this.look_ahead=look_ahead;
		this.index_gr_tb=index_gr_tb;
	}
	Item(Item old){
		this.head=old.head;
		this.symbols.addAll(old.symbols);
		this.position=old.position;
		this.look_ahead=old.look_ahead;
		this.index_gr_tb=old.index_gr_tb;
	}
	boolean eqItem(Item item){
		if(!this.head.equals(item.head))
			return false;
		if(this.position!=item.position)			
			return false;
		if(!this.look_ahead.equals(item.look_ahead))
			return false;
		if(this.symbols.size()!=item.symbols.size())
			return false;
		for(int i=0;i<this.symbols.size();i++){
			if(!this.symbols.get(i).equals(item.symbols.get(i)))
				return false;
		}
		return true;
	}
	boolean inItemList(List<Item> items){
		for(Item it:items){
			if(eqItem(it))
				return true; 
		}
		return false;
	}
	Symbol head;
	ArrayList<Symbol> symbols=new ArrayList<Symbol>();
	int position;
	Symbol look_ahead;
	int index_gr_tb;
}

class CC{//need to edit as look_ahead is wrong
	Item kernel_item;
	ArrayList<Item> items=new ArrayList<Item>();
	HashMap<Symbol,CC> goto_tb=new HashMap<Symbol,CC>();
	boolean got_Closure=false;
	boolean got_Goto=false;
	boolean in_table=false;
	boolean is_reduce=false;
	int index_cc=0;
	int index_gr=0;
}

class ActionTable{
	Map<String, String> action_t=new HashMap<String,String>();
	Map<String, String> goto_t=new HashMap<String,String>();
}

class GrammarTable{
	String head;
	ArrayList<String> symbols=new ArrayList<String>();
}

class Kernel{
	CC cc_in;
	Item item_in;
	boolean hasItem(Item item){
		return item_in.eqItem(item);
	}	
}