//a LexAnalyzer, build a table, by which can reading char and moving auto-machine's states to get tokens
package LexAnalyzer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;



public class LexAnalyzer {
	private ArrayList<RegexPattern> table_pt=new ArrayList<RegexPattern>();			
	private ArrayList<ReservedWord> table_res=new ArrayList<ReservedWord>();      	
	private ArrayList<ReservedWord> table_op=new ArrayList<ReservedWord>();
	private HashMap<Integer,HashMap<Character,Integer>> table_trans=new HashMap<Integer,HashMap<Character,Integer>>();
	private HashMap<DFA_State,HashSet<NFA_State>> table_d2n=new HashMap<DFA_State,HashSet<NFA_State>>();
	private HashMap<HashSet<NFA_State>,DFA_State> table_n2d=new HashMap<HashSet<NFA_State>,DFA_State>();
	private NFA_State nfa_start; 		//beginning of all nfa 
	private DFA_State all_start;
	private DFA_State dfa_start;		//beginning of all dfa and total analyzer
	private int dfa_sn=0;
	private HashSet<Integer> dfa_visited=new HashSet<Integer>();
	private ArrayList<Integer> dfa_ter=new ArrayList<Integer>();
	public static void main(String[] args) {
		LexAnalyzer lex_analyzer=new LexAnalyzer();
		lex_analyzer.input("tokens.txt");
		lex_analyzer.generateNFA();  	
		lex_analyzer.generateDFA();
		lex_analyzer.NFAtoDFA();
		lex_analyzer.combineDFA();
		lex_analyzer.getTokenTable();
		lex_analyzer.outputTable("out_lexAnalyzer.txt");
	}
	private boolean input(String filename){//read the file spec REs then 
		Scanner in = null;
		String word;
		try {			
			in=new Scanner(new BufferedReader(new FileReader(filename)));
			if(in.hasNext())
				word=in.next();
			else return false;
			if(word.equals("//tokens")){
				if(in.hasNext())
					word=in.next();
				while(!word.equals("//reserved")){					
					String token_name= word;
					if(in.hasNext())
						word=in.next();
					else return false;
					String token_RE=word;
					RegexPattern ptn=new RegexPattern(token_name,token_RE);		//to be modified
					table_pt.add(ptn);
					if(in.hasNext())
						word=in.next();
					else return false;
				}
				if(in.hasNext())
					word=in.next();
				while(!word.equals("//operator")){					
					String name= word;
					if(in.hasNext())
						word=in.next();
					else return false;
					String value=word;
					ReservedWord res=new ReservedWord(name,value);		
					table_res.add(res);
					if(in.hasNext())
						word=in.next();
					else return false;
				}
				if(in.hasNext())
					word=in.next();
				while(!word.equals("//end")){					
					String name= word;
					if(in.hasNext())
						word=in.next();
					else return false;
					String value=word;
					ReservedWord rsv=new ReservedWord(name,value);		
					table_res.add(rsv);
					table_op.add(rsv);
					if(in.hasNext())
						word=in.next();
					else return false;
				}
				System.out.println("finish reading");
			}
			else return false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();			
		}finally{
			in.close();
		}
		return true;		
	}
	
	private boolean generateNFA(){			//NFA for regex
		nfa_start=new NFA_State(0,true);
		nfa_start.e_edges=new HashSet<NFA_State>();
		RegexPaser paser=new RegexPaser();
		NFA_State nfa;
		for(RegexPattern pt:table_pt){
			if(pt.rule==null)		//guess it is not necessary
				continue;
			nfa=paser.parse(pt.rule);
			nfa_start.e_edges.add(nfa);
		}
		return true;
		
	}
	
	private boolean generateDFA(){ 		//DFA for reserved words
		dfa_start=new DFA_State(dfa_sn);		
		for(ReservedWord res:table_res){
			int index=0,length;
			char chr;
			String str;
			DFA_State dfa=new DFA_State(++dfa_sn);
			
			if(res.value.isEmpty())
				continue;
			str=res.value;
			length=str.length();
			chr=str.charAt(0);//first char
			if(!dfa_start.dfa_edges.containsKey(chr)){
				dfa_start.dfa_edges.put(chr, new DFA_State(++dfa_sn));				
			}
			dfa=dfa_start.dfa_edges.get(chr);			
			while(++index < length){
				chr=str.charAt(index);
				if(!dfa.dfa_edges.containsKey(chr)){
					dfa.dfa_edges.put(chr, new DFA_State(++dfa_sn));				
				}
				dfa=dfa.dfa_edges.get(chr);				
			}
			dfa.isFinal=true;			//last char
			dfa.value=str;
		}
		return true;		
	}
	
	private boolean NFAtoDFA(){ 		//convert NFA to DFA
		all_start=new DFA_State(++dfa_sn);
		HashSet<NFA_State> nfas=new HashSet<NFA_State>();
		nfas.add(nfa_start);
		spreadDFA(nfas,all_start);
		return true;
	}
	private boolean spreadDFA(HashSet<NFA_State> nfa_start,DFA_State start){
		
		HashSet<NFA_State> nfas=getEClosure(nfa_start);
		if(nfas.isEmpty())
			return false;
		for(NFA_State nfa:nfas){
			if(nfa.isFinal)
				start.isFinal=true;
			break;
		}
		start.dfa_edges=getEdges(nfas);
		if(start.dfa_edges.isEmpty())
			return false;
		for(Character chr:start.dfa_edges.keySet()){
			DFA_State dfa=start.dfa_edges.get(chr);
			HashSet<NFA_State> nfa_set=table_d2n.get(dfa);
			spreadDFA(nfa_set,start.dfa_edges.get(chr));
		}
		return true;
	}
	private HashSet<NFA_State> getEClosure(HashSet<NFA_State> nfa_set){
		
		HashSet<NFA_State> nfas=new HashSet<NFA_State>();
		HashSet<NFA_State> nfas_null=new HashSet<NFA_State>();
		if(nfa_set.isEmpty())
			return nfas;
		for(NFA_State nfa:nfa_set){
			for(NFA_State nfa_1:nfa.e_edges){
				if(nfa_1.nfa_edges.isEmpty() && !nfa_1.isFinal){
					nfas_null.add(nfa_1);
				}else{
					nfas.add(nfa_1); 
				}
			}
			nfa.e_edges.clear();
		}
		if(!nfas_null.isEmpty()){
			HashSet<NFA_State> nfas_1=getEClosure(nfas_null);
			if(!nfas_1.isEmpty())
				nfas.addAll(nfas_1);
		}
		return nfas;
	}
	
	private HashMap<Character, DFA_State> getEdges(HashSet<NFA_State> nfas){
		HashMap<Character, HashSet<NFA_State>> nfas_edges=new HashMap<Character, HashSet<NFA_State>>();
		HashMap<Character,DFA_State> dfa_edges=new HashMap<Character,DFA_State>();
		for(NFA_State nfa:nfas){		//generate map describing characters DFA of nfa_sets 
			for(Character chr:nfa.nfa_edges.keySet()){
				if(!nfas_edges.containsKey(chr)){
					nfas_edges.put(chr, new HashSet<NFA_State>());
				}
				nfas_edges.get(chr).addAll(nfa.nfa_edges.get(chr));
			}
			nfa.nfa_edges.clear();
		}
		for(Character chr:nfas_edges.keySet()){		//generate map describing characters DFA 
			HashSet<NFA_State> nfa_set=nfas_edges.get(chr);
			HashSet<NFA_State> nfa_in=NFAinTable(nfa_set);
			if(nfa_in==null){
				dfa_edges.put(chr, new DFA_State(++dfa_sn));			//generate a new way in characters DFA
				table_d2n.put(dfa_edges.get(chr),nfa_set);
				table_n2d.put(nfa_set, dfa_edges.get(chr));		//update table_d2n and table_n2d
			}else{
				dfa_edges.put(chr,table_n2d.get(nfa_in));		//update way in characters DFA
			}
		}
		return dfa_edges;
	}
	HashSet<NFA_State> NFAinTable(HashSet<NFA_State> nfds){			//will change to TreeSet to improve the efficiency
		for(HashSet<NFA_State> nfd_set:table_n2d.keySet()){
			if(nfds.containsAll(nfd_set) && nfd_set.containsAll(nfds))
				return nfd_set;
		}
		return null;
	}
	private boolean combineDFA(){			//combine dfa_start and all_start
		combine2DFA(all_start,dfa_start);
		return true;
	}
	private boolean combine2DFA(DFA_State dfa1, DFA_State dfa2){
		if(dfa1.isFinal){
			dfa1.dfa_edges=dfa2.dfa_edges;
			return false;
		}
		if(dfa2.isFinal){
			dfa1.isFinal=true;
			return false;
		}
		Set<Character> ways_1=dfa1.dfa_edges.keySet();
		Set<Character> ways_2=dfa2.dfa_edges.keySet();
		for(Character chr:ways_2){
			if(!ways_1.contains(chr)){
				dfa1.dfa_edges.put(chr,new DFA_State(++dfa_sn));
			}
			combine2DFA(dfa1.dfa_edges.get(chr),dfa2.dfa_edges.get(chr));
		}
		dfa2.dfa_edges.clear();
		return true;
		
	}
	private boolean getTokenTable(){		//generate lex-table
		DFA2Table(all_start);
		return true;
	}
	private boolean DFA2Table(DFA_State dfa_0){		
		if(dfa_0.isFinal){
			dfa_ter.add(dfa_0.sn);
			return true;
		}
		if(dfa_visited.contains(dfa_0.sn))
			return true;
		else
			dfa_visited.add(dfa_0.sn);
		table_trans.put(dfa_0.sn,new HashMap<Character,Integer>());
		for(Character chr:dfa_0.dfa_edges.keySet()){
			table_trans.get(dfa_0.sn).put(chr, dfa_0.dfa_edges.get(chr).sn);
		}
		for(Character chr:dfa_0.dfa_edges.keySet()){
			DFA2Table(dfa_0.dfa_edges.get(chr));
		}
		return false;
	}
	private boolean outputTable(String filename){
		PrintWriter out=null;
		try {
			out=new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			String line_1="",line_2="";
			out.println("//transfer table");
			int length=table_trans.size();
			for(Integer index:table_trans.keySet()){
				HashMap<Character,Integer> line_trans=table_trans.get(index);				
				line_1=String.valueOf(index)+" ";
				line_2=String.valueOf(index)+" ";
				for(Character chr:line_trans.keySet()){
					line_1=line_1+chr+"  ";
					line_2=line_2+line_trans.get(chr)+"  ";
				}
				out.println(line_1);
				out.println(line_2);
			}
			out.println();
			out.println("//terminal");
			length=dfa_ter.size();
			String line="";
			for(int index=0;index<length;index++){
				line=line+dfa_ter.get(index)+" ";
			}	
			out.println(line);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		return true;
	}
///////////////////////////////
//parse regex and get NFA
//////////////////////////////
}

class RegexPattern{
	String name;
	String rule;
	NFA_State nfa_head;
	RegexPattern(String name, String rule){
		this.name=name;
		this.rule=rule;
	}	
}
class ReservedWord{
	String name;
	String value;
	DFA_State dfa_head;
	ReservedWord(String name, String value){
		this.name=name;
		this.value=value;
	}
	DFA_State generateDFA(){
		return null; 						//to be modified
	}
}
class NFA_State{
	boolean isFinal=false;
	boolean isNull;
	int sn;
	String value;		//get value of token, at ending
	HashMap<Character,HashSet<NFA_State>> nfa_edges;
	HashSet<NFA_State> e_edges;			//e translate
	NFA_State(int sn,boolean isNull){
		this.sn=sn;
		this.isNull=isNull;
		this.nfa_edges=new HashMap<Character,HashSet<NFA_State>>();	
		this.e_edges=new HashSet<NFA_State>();
	}
	
}

class DFA_State{
	boolean isFinal=false;
	int sn;				//currently is not used
	String value;		//get value of token, at ending
	HashMap<Character,DFA_State> dfa_edges;
	DFA_State(int sn){
		this.sn=sn;
		this.dfa_edges=new HashMap<Character,DFA_State>();
	}
}
class RegexPaser{
	int sn=0;
	int index=0;
	int length=0;
	String rule;
	NFA_State parse(String rule){
		NFA_State nfa=new NFA_State(++sn,true);			
		length=rule.length();
		index=0;
		this.rule=rule;
		NFA_State nfa_end=parseSeq(nfa,nfa);
		nfa_end.isFinal=true;
		return nfa;
	}
	NFA_State parsePar(NFA_State pre_nfa, NFA_State crt_nfa){
		NFA_State tmp_nfa=parseSeq(pre_nfa,crt_nfa);
		char chr=rule.charAt(index);
		if(chr==')'){
			chr=rule.charAt(++index);
			switch(chr){
			case '*':
				crt_nfa.e_edges.add(tmp_nfa);
				tmp_nfa.e_edges.add(crt_nfa);
				crt_nfa=tmp_nfa;
				break;
			case '+':
				tmp_nfa.e_edges.add(crt_nfa);
				crt_nfa=tmp_nfa;
				break;
			case '?':
				crt_nfa.e_edges.add(tmp_nfa);
				crt_nfa=tmp_nfa;
				break;
			default:
				index--;
				crt_nfa=tmp_nfa;
			}
		}
		return crt_nfa;

	}
	
	NFA_State parseSqr(NFA_State pre_nfa, NFA_State crt_nfa) throws Exception{//TODO
		char chr=rule.charAt(index);
		if(chr==']'){
		/*	do{
				chr=rule.charAt(++index);
			}while(chr=='*'||chr=='+'||chr=='?');
			index--;
			return crt_nfa;*/
			throw new Exception("syntax error in []");
		}
		pre_nfa=crt_nfa;
		crt_nfa=new NFA_State(++sn,true);

		while(chr!=']'){
			if(chr=='(' || chr==')'||chr=='['){				
				throw new Exception("syntax error in []");
			}
			
			if(chr!='-'){
				if(!pre_nfa.nfa_edges.containsKey(chr)){
					pre_nfa.nfa_edges.put(chr, new HashSet<NFA_State>());
				}
				NFA_State new_nfa=new NFA_State(++sn,false);
				pre_nfa.nfa_edges.get(chr).add(new_nfa);
				new_nfa.e_edges.add(crt_nfa);
			}else{
				char chr_first=rule.charAt(index-1);
				char chr_last=rule.charAt(++index);
				for(char chr_tmp=chr_first;chr_tmp<=chr_last;chr_tmp++){
					if(!pre_nfa.nfa_edges.containsKey(chr_tmp)){
						pre_nfa.nfa_edges.put(chr_tmp, new HashSet<NFA_State>());
					}
					NFA_State new_nfa=new NFA_State(++sn,false);
					pre_nfa.nfa_edges.get(chr_tmp).add(new_nfa);			
					new_nfa.e_edges.add(crt_nfa);
				}
			}
			chr=rule.charAt(++index);
		}
		if(index<length-1)
		{
			chr=rule.charAt(++index);
			switch(chr){
			case '*':
				crt_nfa.e_edges.add(pre_nfa);
				pre_nfa.e_edges.add(crt_nfa);
				break;
			case '+':
				crt_nfa.e_edges.add(crt_nfa);
				break;
			case '?':
				crt_nfa.e_edges.add(pre_nfa);
				break;
			default:
				index--;
			}
		}
		return crt_nfa;
	}
	NFA_State parseDsj(NFA_State pre_nfa, NFA_State crt_nfa) throws Exception{		
		char chr=rule.charAt(index);
		switch(chr){
		case '|':
			throw new Exception ("syntax error in |");
		case '(':
			index++;
			NFA_State tmp_nfa=parsePar(pre_nfa,pre_nfa);
			tmp_nfa.e_edges.add(crt_nfa);			
			break;
		case '[':
			index++;
			tmp_nfa=parseSqr(pre_nfa,pre_nfa);
			tmp_nfa.e_edges.add(crt_nfa);			
			break;
		default:
			if(!pre_nfa.nfa_edges.containsKey(chr)){
				pre_nfa.nfa_edges.put(chr, new HashSet<NFA_State>());
			}
			tmp_nfa=new NFA_State(++sn,false);
			pre_nfa.nfa_edges.get(chr).add(tmp_nfa);
			tmp_nfa.e_edges.add(crt_nfa);
			break;
		}
		return crt_nfa;
	}
	NFA_State parseSeq(NFA_State pre_nfa, NFA_State crt_nfa){
		while(index < length){
			char chr=rule.charAt(index);
			switch(chr){
			case '(':
				if(index<length-1){
					index++;
					NFA_State tmp_nfa=parsePar(pre_nfa,crt_nfa);
					pre_nfa=crt_nfa;
					crt_nfa=tmp_nfa;
					index++;
				}
				break;
			case '[':
				if(index<length-1){
					try{
						index++;
						NFA_State tmp_nfa=parseSqr(pre_nfa,crt_nfa);
						pre_nfa=crt_nfa;
						crt_nfa=tmp_nfa;
						index++;
					}catch( Exception e){
						e.printStackTrace();
					}
				}
				break;
			case '|':
				if(index<length-1){
					try {
						index++;
						parseDsj(pre_nfa,crt_nfa);						
					} catch (Exception e) {
						e.printStackTrace();
					}
					index++;
				}
				break;
			case ')':
				return crt_nfa;
			case ']':
				return crt_nfa;
			default:
				if(!crt_nfa.nfa_edges.containsKey(chr)){
					crt_nfa.nfa_edges.put(chr, new HashSet<NFA_State>());
				}
				NFA_State new_nfa=new NFA_State(++sn,false);
				crt_nfa.nfa_edges.get(chr).add(new_nfa);
				pre_nfa=crt_nfa;
				crt_nfa=new NFA_State(++sn,true);
				new_nfa.e_edges.add(crt_nfa);
				index++;
			}
		}
		return crt_nfa;
	}
	NFA_State parseEsc( ){		//not used currently
		return null;
	}
	
} 