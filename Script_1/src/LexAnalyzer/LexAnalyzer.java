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
	private DFA_State all_start;		//beginning of total 
	private DFA_State dfa_start;		//beginning of all dfa 
	private HashSet<Integer> dfa_visited=new HashSet<Integer>();
	private HashMap<Integer,String> dfa_opt=new HashMap<Integer,String>();
	private HashMap<Integer,String> dfa_reg=new HashMap<Integer,String>();
	private HashMap<Integer,String> dfa_res=new HashMap<Integer,String>();
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
	boolean input(String filename){//read the file spec REs then 
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
	
	boolean generateNFA(){			//NFA for regex
		nfa_start=new NFA_State();
		nfa_start.e_edges=new HashSet<NFA_State>();
		RegexPaser paser=new RegexPaser();
		NFA_State nfa;
		for(RegexPattern pt:table_pt){
			if(pt.rule==null)		//guess it is not necessary
				continue;
			nfa=paser.parse(nfa_start,pt.rule);
			nfa.value=pt.name;
			nfa.isFinal=true;
		}
		return true;		
	}
	
	boolean generateDFA(){ 		//DFA for reserved words
		dfa_start=new DFA_State();		
		for(ReservedWord res:table_res){
			int index=0,length;
			char chr;
			String str;
			DFA_State dfa=new DFA_State();
			
			if(res.value.isEmpty())
				continue;
			str=res.value;
			length=str.length();
			chr=str.charAt(0);//first char
			if(!dfa_start.dfa_edges.containsKey(chr)){
				dfa_start.dfa_edges.put(chr, new DFA_State());				
			}
			dfa=dfa_start.dfa_edges.get(chr);			
			while(++index < length){
				chr=str.charAt(index);
				if(!dfa.dfa_edges.containsKey(chr)){
					dfa.dfa_edges.put(chr, new DFA_State());				
				}
				dfa=dfa.dfa_edges.get(chr);				
			}
			dfa.isFinal=true;			//last char
			dfa.value=str;			
			
			if(table_op.contains(res)){
				dfa.type=TokenType.t_opt;
			}else
				dfa.type=TokenType.t_res;
		}
		
		return true;		
	}
	
	boolean NFAtoDFA(){ 		//convert NFA to DFA
		all_start=new DFA_State();
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
			if(nfa.isFinal){
				start.isFinal=true;			//mark final DFA 
				start.value=nfa.value;
				start.type=TokenType.t_reg;
				break;
			}
		}
		if(!start.dfa_edges.isEmpty()){		//existed dfa, already done getEdges()
			return false;
		}
		start.dfa_edges=getEdges(nfas);
		if(start.dfa_edges.isEmpty()){
			return false;
		}
		for(Character chr:start.dfa_edges.keySet()){
			DFA_State dfa=start.dfa_edges.get(chr);
			HashSet<NFA_State> nfa_set=table_d2n.get(dfa);
			spreadDFA(nfa_set,start.dfa_edges.get(chr));
		}
		return true;
	}
	private HashSet<NFA_State> getEClosure(HashSet<NFA_State> nfa_set){		
		HashSet<NFA_State> nfas=new HashSet<NFA_State>();
		addEClosure(nfas,nfa_set);
		return nfas;
	}
	private boolean addEClosure(HashSet<NFA_State> nfas, HashSet<NFA_State> nfa_set){
		HashSet<NFA_State> nfas_null=new HashSet<NFA_State>();
		if(nfa_set.isEmpty())
			return false;
		for(NFA_State tmp_nfa:nfa_set){			//has nfa_edge, then add to closure, or search e-edges recursively 
			nfas.add(tmp_nfa);
			for(NFA_State nfa_1:tmp_nfa.e_edges){
				if(!nfa_1.e_edges.isEmpty() && !nfas.contains(nfa_1)){
					nfas_null.add(nfa_1);
				}
				nfas.add(nfa_1);
			}
		}
		if(!nfas_null.isEmpty()){
			addEClosure(nfas,nfas_null);
		}
		return true;
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
		}		
		for(Character chr:nfas_edges.keySet()){		//generate map describing characters DFA 
			HashSet<NFA_State> nfa_set=nfas_edges.get(chr);
			HashSet<NFA_State> nfa_in=NFAinTable(nfa_set);
			if(nfa_in==null){
				dfa_edges.put(chr, new DFA_State());			//generate a new way in characters DFA
				table_d2n.put(dfa_edges.get(chr),nfa_set);
				table_n2d.put(nfa_set, dfa_edges.get(chr));		//update table_d2n and table_n2d
			}else{
				dfa_edges.put(chr,table_n2d.get(nfa_in));		//update way in characters DFA
			}
			/*for(NFA_State nfa:nfa_set){			//move to another place
				if(nfa.isFinal)
					dfa_edges.get(chr).isFinal=true;
				if(nfa.value!=null){
					dfa_edges.get(chr).value="regex-"+nfa.value;//TODO
				}
			}*/
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
	boolean combineDFA(){			//combine dfa_start and all_start
		combine2DFA(all_start,dfa_start);
		return true;
	}
	private boolean combine2DFA(DFA_State dfa1, DFA_State dfa2){ 		//add 2 to 1
		if(dfa2.isFinal){
			dfa1.isFinal=true;
			dfa1.value=dfa2.value;
			dfa1.type=dfa2.type;
			//dfa_ter.put(dfa1.sn,dfa1.value);
		}
		if(dfa2.dfa_edges.isEmpty())
			return false;
		Set<Character> ways_1=dfa1.dfa_edges.keySet();
		Set<Character> ways_2=dfa2.dfa_edges.keySet();
		List<DFA_State> dfa_list_1=new LinkedList<DFA_State>();
		List<DFA_State> dfa_list_2=new LinkedList<DFA_State>();
		for(Character chr:ways_2){
			if(!ways_1.contains(chr)){
				dfa1.dfa_edges.put(chr,new DFA_State());				
			}
			dfa_list_1.add(0,dfa1.dfa_edges.get(chr));
			dfa_list_2.add(0,dfa2.dfa_edges.get(chr));
		}
		dfa2.dfa_edges.clear();
		while(!dfa_list_1.isEmpty())
		{
			DFA_State dfa_tmp_1=dfa_list_1.remove(0);
			DFA_State dfa_tmp_2=dfa_list_2.remove(0);
			combine2DFA(dfa_tmp_1,dfa_tmp_2);
		}
		return true;
		
	}
	boolean getTokenTable(){		//generate lex-table
		DFA2Table(all_start);
		return true;
	}
	private boolean DFA2Table(DFA_State dfa_0){	
		if(dfa_0.isFinal){							//add terminal states
			if(dfa_0.type==TokenType.t_opt){
				dfa_opt.put(dfa_0.sn,dfa_0.value);
			}else if(dfa_0.type==TokenType.t_res){
				dfa_res.put(dfa_0.sn,dfa_0.value);
			}else if(dfa_0.type==TokenType.t_reg){
				dfa_reg.put(dfa_0.sn,dfa_0.value);
			}
		}
		if(dfa_visited.contains(dfa_0.sn))
			return false;
		else
			dfa_visited.add(dfa_0.sn);
		table_trans.put(dfa_0.sn,new HashMap<Character,Integer>());
		for(Character chr:dfa_0.dfa_edges.keySet()){
			table_trans.get(dfa_0.sn).put(chr, dfa_0.dfa_edges.get(chr).sn);
		}
		for(Character chr:dfa_0.dfa_edges.keySet()){
			DFA2Table(dfa_0.dfa_edges.get(chr));
		}
		return true;
	}
	boolean outputTable(String filename){
		PrintWriter out=null;
		try {
			out=new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			String line_1="",line_2="";
			out.println("//transfer table");
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
			out.println("//regex");
			String line="";
			for(Integer i:dfa_reg.keySet()){
				line=i+" "+dfa_reg.get(i);
				out.println(line);
			}
			out.println();
			out.println("//res");
			line="";
			for(Integer i:dfa_res.keySet()){
				line=i+" "+dfa_res.get(i);
				out.println(line);
			}
			out.println();
			out.println("//opt");
			line="";
			for(Integer i:dfa_opt.keySet()){
				line=i+" "+dfa_opt.get(i);
				out.println(line);
			}
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
	static int snS=0;
	boolean isFinal=false;		//terminal state
	int sn;
	String value;				//get value of token, at ending
	HashMap<Character,HashSet<NFA_State>> nfa_edges;
	HashSet<NFA_State> e_edges;			//e transfer
	NFA_State(){
		snS++;
		this.sn=snS;
		this.nfa_edges=new HashMap<Character,HashSet<NFA_State>>();	
		this.e_edges=new HashSet<NFA_State>();
	}
}
enum TokenType{
	t_res,t_opt,t_reg
};
class DFA_State{
	static int snS=0;
	boolean isFinal=false;
	TokenType type;
	int sn;				//currently is not used
	String value;		//get value of token, at ending
	HashMap<Character,DFA_State> dfa_edges;
	DFA_State(){
		snS++;
		this.sn=snS;
		this.dfa_edges=new HashMap<Character,DFA_State>();
	}
}
class RegexPaser{
	int index=0;
	int length=0;
	String rule;
	NFA_State parse(NFA_State pre_nfa, String rule){
		NFA_State crt_nfa=new NFA_State();
		length=rule.length();
		index=0;
		this.rule=rule;
		pre_nfa.e_edges.add(crt_nfa);
		crt_nfa=parseSeq(pre_nfa,crt_nfa);
		return crt_nfa;
	}
	NFA_State parseSeq(NFA_State pre_nfa, NFA_State crt_nfa){
		while(index < length){
			char chr=rule.charAt(index);
			switch(chr){
			case '(':
				if(++index<length){
					try{
						NFA_State tmp_nfa=parsePar(pre_nfa,crt_nfa);
						pre_nfa=crt_nfa;
						crt_nfa=tmp_nfa;
						index++;
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				break;
			case '[':
				if(++index<length){
					try{
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
				if(++index<length){
					try {
						parseDsj(pre_nfa,crt_nfa);						
					} catch (Exception e) {
						e.printStackTrace();
					}
					index++;
				}
				break;
			case '*':
				crt_nfa.e_edges.add(pre_nfa);
				pre_nfa.e_edges.add(crt_nfa);
				index++;
				break;
			case '+':
				crt_nfa.e_edges.add(pre_nfa);
				index++;
				break;
			case '?':
				pre_nfa.e_edges.add(crt_nfa);
				index++;
				break;
			case ')':
				return crt_nfa;
			case ']':
				return crt_nfa;
			default:
				if(!crt_nfa.nfa_edges.containsKey(chr)){
					crt_nfa.nfa_edges.put(chr, new HashSet<NFA_State>());
				}
				NFA_State tmp_nfa=new NFA_State();		//new e transfer state
				crt_nfa.nfa_edges.get(chr).add(tmp_nfa);
				pre_nfa=crt_nfa;
				crt_nfa=tmp_nfa;
				index++;
				break;
			}
		}
		crt_nfa.isFinal=true;
		return crt_nfa;
	}
	NFA_State parsePar(NFA_State pre_nfa, NFA_State crt_nfa) throws Exception{
		NFA_State tmp_nfa=parseSeq(pre_nfa,crt_nfa);
		char chr=rule.charAt(index);
		if(chr==')'){
			if(index==length-1){				
				return tmp_nfa;
			}
			chr=rule.charAt(++index);
			switch(chr){
			case '*':
				crt_nfa.e_edges.add(tmp_nfa);
				tmp_nfa.e_edges.add(crt_nfa);
				break;
			case '+':
				tmp_nfa.e_edges.add(crt_nfa);
				break;
			case '?':
				crt_nfa.e_edges.add(tmp_nfa);
				break;
			default:
				index--;
			}
		}else{
			throw new Exception("syntax error in ()");
		}
		return tmp_nfa;

	}
	
	NFA_State parseSqr(NFA_State pre_nfa, NFA_State crt_nfa) throws Exception{
		char chr=rule.charAt(index);
		pre_nfa=crt_nfa;
		crt_nfa=new NFA_State();
		while(chr!=']'){
			if(chr=='(' || chr==')'||chr=='['){				
				throw new Exception("syntax error in []");
			}
			if(chr!='-'){
				if(!pre_nfa.nfa_edges.containsKey(chr)){
					pre_nfa.nfa_edges.put(chr, new HashSet<NFA_State>());
				}
				NFA_State tmp_nfa=new NFA_State();
				pre_nfa.nfa_edges.get(chr).add(tmp_nfa);
				tmp_nfa.e_edges.add(crt_nfa);
			}else{
				char chr_first=rule.charAt(index-1);
				char chr_last=rule.charAt(++index);
				for(char chr_tmp=++chr_first;chr_tmp<=chr_last;chr_tmp++){
					if(!pre_nfa.nfa_edges.containsKey(chr_tmp)){
						pre_nfa.nfa_edges.put(chr_tmp, new HashSet<NFA_State>());
					}
					NFA_State new_nfa=new NFA_State();
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
				crt_nfa.e_edges.add(pre_nfa);
				break;
			case '?':
				pre_nfa.e_edges.add(crt_nfa);
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
			tmp_nfa=new NFA_State();
			pre_nfa.nfa_edges.get(chr).add(tmp_nfa);
			tmp_nfa.e_edges.add(crt_nfa);
			break;
		}
		return crt_nfa;
	}

	NFA_State parseEsc( ){		//not used currently
		return null;
	}
	
} 