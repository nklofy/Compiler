//public API: input(String) parse()
//before running, you'd better to check rules matching syntax rules 
package Parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

import LexAnalyzer.Token;
import LexAnalyzer.Tokenizer;
import Parser.AST.*;
import Parser.ASTs.*;
import Parser.TypeSys.Type_SgStmt;
import Parser.TypeSys.Type_Stmt;

public class Parser {
	private Tokenizer tokenizer=new Tokenizer();
	private ASTGenerator ast_gen=new ASTGenerator();
	private ArrayList<String> symbol_table=new ArrayList<String>();
	private ArrayList<String> token_table=new ArrayList<String>();
	private ArrayList<Grammar> grammar_table=new ArrayList<Grammar>();
	private HashMap<String,Integer> symbol_sn=new HashMap<String,Integer>();
	private HashMap<String,Integer> token_sn=new HashMap<String,Integer>();
	private ArrayList<AstRule> astRule_list=new ArrayList<AstRule>();
	private ArrayList<ArrayList<Integer>> shift_table=new ArrayList<ArrayList<Integer>>();	
	private ArrayList<ArrayList<LinkedList<Integer>>> reduce_table=new ArrayList<ArrayList<LinkedList<Integer>>>();
	private ArrayList<ArrayList<Integer>> goto_table=new ArrayList<ArrayList<Integer>>();
	private AST ast_tree;
	private HashMap<Integer,ParseState> states_act=new HashMap<Integer,ParseState>();
	private HashMap<Integer,ParseState> states_amb=new HashMap<Integer,ParseState>();
	private int sym_e;
	private ArrayList<String> parse_log=new ArrayList<String>();
	public AST getAST(){
		return ast_tree;
	}
	public static void main(String[] args) {
		Parser parser=new Parser();
		parser.analyzeGrm("out_grammar.txt"); 	System.out.println("analyzeGrm out_grammar.txt");
		parser.analyzeAST("grammar_AST.txt");	System.out.println("analyzeAST grammar_AST.txt");
		parser.analyzeLex("out_lexAnalyzer.txt");	System.out.println("analyzeLex out_lexAnalyzer.txt");
		parser.input("script_test1.txt");	
		parser.parse();							System.out.println("finish parsing");
		parser.output("out_parser.txt");		
		
	}
	public boolean analyzeGrm(String filename){ 	//read and analyze the grammar table and action table
		Scanner in = null;
		String word;
		try {
			in=new Scanner(new BufferedReader(new FileReader(filename)));
			if(in.hasNext()){
				word=in.nextLine();
				if(!word.equals("//tokens"))
					return false;
			}
			else
				return false;			
			if(in.hasNext())
				word=in.nextLine();
			else
				return false;
			while(!word.equals("//symbols") && !word.equals("")){	
				String tokens[]=word.split(" ");				
				for(int i=0;i<tokens.length;i++){
					if(!tokens[i].equals("")){						
						token_table.add(tokens[i]);			//add all tokens
						//System.out.println(tokens[i]);
					}					
				}
				if(in.hasNext())
					word=in.nextLine();
				else
					return false;
			}
			while(word.equals("") || word.equals("//symbols")){
				if(in.hasNext())
					word=in.nextLine();
				else
					return false;
			}
			while(!word.equals("//grammars") && !word.equals("")){
				String tokens[]=word.split(" ");
				for(int i=0;i<tokens.length;i++){
					if(!tokens[i].equals("")){						
						symbol_table.add(tokens[i]);		//add all symbols
						//System.out.println(tokens[i]);
					}					
				}
				if(in.hasNext())
					word=in.nextLine();
				else
					return false;
			}
			while(word.equals("") || word.equals("//grammars")){
				if(in.hasNext())
					word=in.nextLine();
				else
					return false;
			}
			while(!word.equals("//actions") && !word.equals("")){
				String syms[]=word.split(" ");
				Grammar grm=new Grammar();
				grammar_table.add(grm);
				for(int i=0;i<syms.length;i++){
					if(!syms[i].equals("")){
						grm.symbols.add(syms[i]);//add all grammars, like "1" "head" "production"
					}
				}
				grm.head=grm.symbols.get(1);
				grm.symbol_count=grm.symbols.size()-2;
				if(in.hasNext())
					word=in.nextLine();
				else
					return false;
			}
			while(word.equals("") || word.equals("//actions")){
				if(in.hasNext())
					word=in.nextLine();
				else
					return false;
			}
			while(!word.equals("//gotos") && !word.equals("")){	
				String actions[]=word.split(" ");
				ArrayList<Integer> shift=new ArrayList<Integer>();
				ArrayList<LinkedList<Integer>> reduce=new ArrayList<LinkedList<Integer>>();
				shift_table.add(shift);		//add all shift action
				reduce_table.add(reduce);	//add all reduce action
				for(int i=1;i<actions.length;i++){
					if(!actions[i].equals("")){	
						String str_tmp=actions[i];
						if(str_tmp.equals("/")){
							shift.add(-1);		//"-1" means no action		
							reduce.add(new LinkedList<Integer>());
							reduce.get(reduce.size()-1).add(-1);
						}else{
							int j=0,jp=0;
							if(str_tmp.charAt(0)=='s'){		//shift, maybe reduce
								while(j<str_tmp.length()&&str_tmp.charAt(j)!='r'){
									j++;
								}
								shift.add(Integer.parseInt(str_tmp.substring(1, j)));
								reduce.add(new LinkedList<Integer>());
								reduce.get(reduce.size()-1).add(-1);							
							}else if(str_tmp.charAt(0)=='r'){ 			//no shift, but reduce
								shift.add(-1);	
								reduce.add(new LinkedList<Integer>());
							}else{
								System.out.println("unkown action "+str_tmp);
							}
							if(j<str_tmp.length()&&str_tmp.charAt(j)=='r'){
								jp=j++;
								LinkedList<Integer> intl=reduce.get(reduce.size()-1);								
								while(j<=str_tmp.length()){
									if(j==str_tmp.length()||str_tmp.charAt(j)=='r'){
										String s=str_tmp.substring(jp+1, j);
										//System.out.println(s);
										intl.add(Integer.parseInt(s));
										jp=j;
									}
									j++;
								}
								
							}
						}
					}
				}
				if(in.hasNext())
					word=in.nextLine();
				else
					return false;
			}
			while(word.equals("") || word.equals("//gotos")){
				if(in.hasNext())
					word=in.nextLine();
				else
					return false;
			}
			while(!word.equals("//end") && !word.equals("")){	
				String words[]=word.split(" ");
				ArrayList<Integer> gt=new ArrayList<Integer>();
				goto_table.add(gt);			//add all goto movement
				for(int i=1;i<words.length;i++){
					if(!words[i].equals("")){	
						String str_tmp=words[i];
						if(str_tmp.equals("/")){
							gt.add(-1);		//"-1" means illegal action
						}else{
							if(str_tmp.charAt(0)=='g'){
								gt.add(Integer.parseInt(str_tmp.substring(1)));//goto
							}else return false;
						}
					}
				}
				if(in.hasNext())
					word=in.nextLine();
				else
					return false;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally{
			in.close();
		}
		
		for(int i=0;i<symbol_table.size();i++){
			symbol_sn.put(symbol_table.get(i),i);
		}
		for(int i=0;i<token_table.size();i++){
			token_sn.put(token_table.get(i),i);	
		}
		
		return true;		
	}
	
	public boolean analyzeAST(String filename){		//read and analyze rules for creating ast
		Scanner in = null;
		String word;
		try {
			in=new Scanner(new BufferedReader(new FileReader(filename)));
			if(in.hasNext()){
				word=in.nextLine();
				if(!word.equals("//grammars"))
					return false;
			}
			else
				return false;			
			if(in.hasNext())
				word=in.nextLine();
			else
				return false;
			while(!word.equals("//AST") && !word.equals("")){					
				if(in.hasNext())
					word=in.nextLine();
				else
					return false;
			}
			while(word.equals("") || word.equals("//AST")){
				if(in.hasNext())
					word=in.nextLine();
				else
					return false;
			}
			while(!word.equals("//end") && !word.equals("")){
				String words[]=word.split(" ");
				AstRule rule=new AstRule();		
				astRule_list.add(rule);			//add all rules
				ArrayList<String> str_list=new ArrayList<String>();
				for(int i=0;i<words.length;i++){
					if(!words[i].equals("")){
						str_list.add(words[i]);//format like "1" "method" "parameters"
					}					
				}//rule.parameters
				rule.method=str_list.get(1);
/*				rule.symbol_count=grammar_table.get(astRule_list.size()-1).symbol_count;
				for(int i=2;i<str_list.size();i++){
					rule.parameters.add(Integer.parseInt(str_list.get(i).substring(1)));
				}
				System.out.println(rule.method+" "+rule.symbol_count+" "+rule.parameters.get(0));*/
				if(in.hasNext())
					word=in.nextLine();
				else
					return false;
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally{
			in.close();
		}
		return true;
	}

	public boolean analyzeLex(String filename){
		tokenizer.analyze(filename);
		return true;
	}
	
	public boolean input(String filename){		
		tokenizer.setScanFile(filename);
		return true;
	}
	
	public boolean parse(){
		ParseState state_start=new ParseState();
		sym_e=token_sn.get("e");
		Token token=tokenizer.getToken();
		int crt_state=0;
		state_start.state_sn=crt_state;
		Symbol symbol=new Symbol();
		symbol.name="Goal";
		state_start.symbol=symbol;
		states_act.put(0, state_start);
		String token_name="";
		Symbol smb=new Symbol();
		while(true){	
			smb=new Symbol();
			smb.type=token.getType();
			switch(smb.type){
			case "int":
				token_name="number";smb.name=token_name;
				smb.value=token.getNumValue();
				AST_Num tn_ast=new AST_Num();
				tn_ast.setNum("int", smb.value);
				smb.ast=tn_ast;
				break;
			case "double":
				token_name="number";smb.name=token_name;
				smb.value=token.getNumValue();
				AST_Num td_ast=new AST_Num();
				td_ast.setNum("double", smb.value);
				smb.ast=td_ast;
				break;
			case "idn":
				token_name="var";smb.name=token.getIdnName();
				AST_Var tv_ast=new AST_Var();
				tv_ast.setVar(smb.name);
				smb.ast=tv_ast;
				break;
			case "res":
				token_name=token.getResName();smb.name=token_name;
				break;
			case "opt":
				token_name=token.getOptName();smb.name=token_name;
				break;
			case "note":
				token=tokenizer.getToken();
				continue;
			case "string":
				token_name="str";smb.name=token_name;
				smb.value=token.getStrValue();
				break;
			case "char":
				token_name="chr";smb.name=token_name;
				smb.value=token.getChrValue();
				break;
			default:
				return false;
			}

			doAllReduce(token_name);
							

			if(token_name.equals("eof")  ){//end of parsing
				if(states_act.size()==1 && states_act.containsKey(1)){
					System.out.println("eof, "+"finished parsing");
					parse_log.add("eof, "+"finished parsing");
					//ast_tree=states_act.get(0).symbol.ast;
					return true;	
				}
			}
			doAllShift(token_name,smb);
			if(states_act.isEmpty()){
				break;
			}
			token=tokenizer.getToken();
		}
		return false;
	}
	private boolean doAllShift(String token_name,Symbol smb){
		int crt_token_sn=token_sn.get(token_name);
		ArrayList<ParseState> states_e=new ArrayList<ParseState>();
		ArrayList<ParseState> states_e_rm=new ArrayList<ParseState>();
		ArrayList<ParseState> states_e_add=new ArrayList<ParseState>();
		ArrayList<ParseState> states_rm=new ArrayList<ParseState>();
		ArrayList<ParseState> states_add=new ArrayList<ParseState>();
		int shift_state;
		for(ParseState pst:states_act.values()){//check for e shift
			int crt_state=pst.state_sn;
			shift_state=shift_table.get(crt_state).get(crt_token_sn);
			if(shift_state==-1){	
				shift_state=shift_table.get(crt_state).get(sym_e);
				if(shift_state==-1){
					states_rm.add(pst);
					parse_log.add("wrong branch when shift "+crt_state+" "+token_name);
					System.out.println("wrong branch when shift "+crt_state+" "+token_name);
					continue;
				}else{
					states_e.add(pst);
					continue;
				}
			}
			
		}
		for(ParseState pst_rm:states_rm){
			states_act.remove(pst_rm.state_sn);
		}
		states_rm.clear();
		if(!states_e.isEmpty()){
			for(ParseState pst_e:states_e){
				int shift_e=shift_table.get(pst_e.state_sn).get(sym_e);
				ParseState pst_ep=pst_e.pre_state;
				ParseState new_pst=new ParseState();
				new_pst.pre_state=pst_ep;
				new_pst.state_sn=shift_e;
				states_e_rm.add(pst_e);
				states_e_add.add(new_pst);
			}
			for(ParseState pst_rm:states_e_rm){
				states_act.remove(pst_rm.state_sn);
			}
			for(ParseState pst_add:states_e_add){
				states_act.put(pst_add.state_sn,pst_add);
			}
			doAllReduce(token_name);
		}		
		
		for(ParseState pst:states_act.values()){//do shift
			int crt_state=pst.state_sn;
			shift_state=shift_table.get(crt_state).get(crt_token_sn);
			ParseState new_pst=new ParseState();
			new_pst.state_sn=shift_state;
			new_pst.symbol=smb;
			new_pst.det_depth=pst.det_depth+1;
			new_pst.out_count++;
			new_pst.pre_state=pst;
			states_rm.add(pst);
			states_add.add(new_pst);
			parse_log.add("shift "+pst.state_sn+" "+token_name+" goto "+new_pst.state_sn);
			System.out.println("shift "+pst.state_sn+" "+token_name+" goto "+new_pst.state_sn);
		}
		for(ParseState pst_rm:states_rm){
			states_act.remove(pst_rm.state_sn);
		}
		for(ParseState pst_add:states_add){
			states_act.put(pst_add.state_sn, pst_add);
		}		
		return true;
	}
	private boolean doAllReduce(String token_name){
		int crt_token_sn=token_sn.get(token_name);
		LinkedList<ParseState> states_rlst=new LinkedList<ParseState>();
		LinkedList<ParseState> states_rm=new LinkedList<ParseState>();
		LinkedList<ParseState> states_add=new LinkedList<ParseState>();		
		for(ParseState pst:states_act.values()){
			states_rlst.add(pst);
		}
		while(true){
			if(states_rlst.isEmpty()){
				break;
			}
			ParseState pst=states_rlst.removeFirst();
			LinkedList<Integer> r_grms=reduce_table.get(pst.state_sn).get(crt_token_sn);
			if(r_grms.get(0)==-1){
				continue;
			}
			for(int r:r_grms){
				ParseState nps=doReduce(pst,r);
				if(nps==null){
					continue;
				}
				pst.out_count--;
				if(pst.out_count==0){
					states_act.remove(pst.state_sn);
				}
				states_act.put(nps.state_sn,nps);
				if(nps.state_sn==1){
					return true;
				}
				states_rlst.add(nps);
			}
		}		
		return true;
	}
	
	private ParseState doReduce(ParseState crt_state, int reduce_grammar){
		String reduce_head=grammar_table.get(reduce_grammar).head;		
		AstRule rule=astRule_list.get(reduce_grammar);
		String method=rule.method;
		AST ast =ast_gen.crtAST(method,crt_state);		//build new ast;
		//parse_log.add("create ast: "+ ast.getClass().getName());
		//System.out.println("create ast: "+ ast.getClass().getName());
		int ct=grammar_table.get(reduce_grammar).symbol_count;	
		ParseState pre_state=crt_state;
		if(ct<=crt_state.det_depth){//fast LR
			for(int i=0;i<ct;i++){							//move states_act;
				pre_state=pre_state.pre_state;			
			}
		}else{
			
		}
		
		int nss=goto_table.get(pre_state.state_sn).get(symbol_sn.get(reduce_head));
		ParseState nps=null;
		if(states_act.containsKey(nss)){
			nps=states_act.get(nss);
			nps.pre_states.add(pre_state);
			nps.symbol.asts.add(ast);
			nps.det_depth=0;							//refresh count and depth;
			nps.out_count++;
		}else if(nss!=-1){
			Symbol smb=new Symbol();
			smb.name=reduce_head;
			smb.ast=ast;
			nps=new ParseState();
			nps.state_sn=nss;
			nps.pre_state=pre_state;
			nps.symbol=smb;
			nps.det_depth=pre_state.det_depth+1;
			nps.out_count++;
		}else{
			return null;
		}
		parse_log.add("reduce "+reduce_grammar+" "+reduce_head+" goto "+nss);
		System.out.println("reduce "+reduce_grammar+" "+reduce_head+" goto "+nss);
		return nps;
	}
	
	public boolean output(String filename){
		PrintWriter out=null;
		try {
			out=new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			for(String s:parse_log){
				out.println(s);
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}finally{
			out.close();
		}
		return false;
	}
}