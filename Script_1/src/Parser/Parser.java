//public API: input(String) parse()
//before running, you'd better to check rules matching syntax rules 
package Parser;

import java.io.*;
import java.util.*;

import LexAnalyzer.*;
import Parser.AST.*;
import Parser.ASTs.*;

public class Parser {
	private Tokenizer tokenizer=new Tokenizer();
	private ASTGenerator ast_gen=new ASTGenerator();
	private ArrayList<String> symbol_table=new ArrayList<String>();
	private ArrayList<String> token_table=new ArrayList<String>();
	private ArrayList<Grammar> grammar_table=new ArrayList<Grammar>();
	private HashMap<String,Integer> symbol_sn=new HashMap<String,Integer>();
	private HashMap<String,Integer> token_sn=new HashMap<String,Integer>();
	private HashMap<Integer,String> token_n2s=new HashMap<Integer,String>();
	private ArrayList<ASTRule> astRule_list=new ArrayList<ASTRule>();
	private ArrayList<ArrayList<Integer>> shift_table=new ArrayList<ArrayList<Integer>>();
	private ArrayList<ArrayList<Integer>> goto_table=new ArrayList<ArrayList<Integer>>();
	private ArrayList<ArrayList<Integer>> reduce_table=new ArrayList<ArrayList<Integer>>();
	private ArrayList<ArrayList<ArrayList<Integer>>> reduces_table=new ArrayList<ArrayList<ArrayList<Integer>>>();	
	private AST ast_tree;
	private HashMap<Integer,ParseState> states_active=new HashMap<Integer,ParseState>();
	//private HashMap<Integer,ParseState> states_shift=new HashMap<Integer,ParseState>();
	//private HashMap<Integer,ParseState> states_reduce=new HashMap<Integer,ParseState>();
	private LinkedList<ParseState> states_rlst=new LinkedList<ParseState>();	//TODO change to treeset?
	private LinkedList<ParseState> states_elst=new LinkedList<ParseState>();
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
				ArrayList<Integer> reduce=new ArrayList<Integer>();
				ArrayList<ArrayList<Integer>> reduces=new ArrayList<ArrayList<Integer>>();
				shift_table.add(shift);		//add all shift action
				reduce_table.add(reduce);
				reduces_table.add(reduces);	//add all reduce action
				for(int i=1;i<actions.length;i++){
					if(!actions[i].equals("")){	
						String str_tmp=actions[i];
						ArrayList<Integer> rs=new ArrayList<Integer>();
						reduces.add(rs);
						if(str_tmp.equals("/")){
							shift.add(-1);		//"-1" means no action		
							reduce.add(-1);
							continue;
						}else{							
							int j=0,jp=0;
							if(str_tmp.charAt(0)=='s'){		//shift, maybe reduce
								while(j<str_tmp.length()&&str_tmp.charAt(j)!='r'){
									j++;
								}
								shift.add(Integer.parseInt(str_tmp.substring(1, j)));
								if(j==str_tmp.length()){
									reduce.add(-1);		//-1 means no reduce
									continue;
								}else if(str_tmp.charAt(j)!='r'){
									System.out.println("error action table at "+ shift_table.size());
								}
							}else if(str_tmp.charAt(0)=='r'){ 			//no shift, only reduce
								shift.add(-1);
							}else{
								System.out.println("unkown action "+str_tmp);
							}
							jp=j;
							while(j<str_tmp.length()){
								j++;
								if(j==str_tmp.length()){
									if(rs.isEmpty()){//only one reduce
										int r=Integer.parseInt(str_tmp.substring(jp+1, j));
										reduce.add(r);
									}else{
										int r=Integer.parseInt(str_tmp.substring(jp+1, j));
										rs.add(r);
										reduce.add(-2); //-2 means r-r conflict	
									}
								}else if(str_tmp.charAt(j)=='r'){
									int r=Integer.parseInt(str_tmp.substring(jp+1, j));
									rs.add(r);
									jp=j;
								}
							}	//while(j<str_tmp.length())
						}
					}	//if(!actions[i].equals(""))
				}	//for(int i=1;i<actions.length;i++)
				if(in.hasNext())
					word=in.nextLine();
				else
					return false;
			}	//while(!word.equals("//gotos") && !word.equals(""))
			while(word.equals("") || word.equals("//gotos")){
				if(in.hasNext())
					word=in.nextLine();
				else
					return false;
			}	//while(word.equals("") || word.equals("//gotos"))
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
			}	//while(!word.equals("//end") && !word.equals(""))
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
			token_n2s.put(i, token_table.get(i));
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
				ASTRule rule=new ASTRule();		
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
		states_active.put(0, state_start);
		String token_name="";
		Symbol smb=new Symbol();
		while(true){	
			smb=new Symbol();
			smb.line=token.getLine();
			smb.type=token.getType();
			switch(smb.type){
			case "int":
				token_name="number";smb.name=token_name;
				smb.value=token.getNumValue();
				ExprPri_Num tn_ast=new ExprPri_Num();
				tn_ast.setNum("int", smb.value);
				smb.ast=tn_ast;
				break;
			case "double":
				token_name="number";smb.name=token_name;
				smb.value=token.getNumValue();
				ExprPri_Num td_ast=new ExprPri_Num();
				td_ast.setNum("double", smb.value);
				smb.ast=td_ast;
				break;
			case "var":
				token_name="var";smb.name=token_name;
				smb.value=token.getIdnName();
				ExprPri_Var tv_ast=new ExprPri_Var();
				tv_ast.setName(smb.value);
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
				ExprPri_Str ts_ast=new ExprPri_Str();
				ts_ast.setStr(smb.value);
				smb.ast=ts_ast;
				break;
			case "char":
				token_name="chr";smb.name=token_name;
				smb.value=token.getChrValue();
				ExprPri_Chr tc_ast=new ExprPri_Chr();
				tc_ast.setChr(smb.value);
				smb.ast=tc_ast;
				break;
			default:
				System.out.println("error wrong token type");
				return false;
			}
			doAllReduce(token_name, smb);	
			if(token_name.equals("eof")&&states_active.containsKey(0)  ){//end of parsing
				System.out.println("eof, "+"finished parsing");
				//ast_tree=states_act.get(0).symbol.ast;
				return true;	
			}
			if(states_active.isEmpty()){				
				System.out.println("error no state in active");
				break;
			}
			token=tokenizer.getToken();
		}
		return false;
	}
	
	private boolean doAllReduce(String token_name, Symbol smb){	
		System.out.println("get token "+token_name+" at line "+smb.line);
		states_rlst.clear();
		states_elst.clear();
		states_rlst.addAll(states_active.values());
		states_active.clear();
		int crt_token_sn=token_sn.get(token_name);
		while(!states_rlst.isEmpty()){
			ParseState pst=states_rlst.removeFirst();
			if(pst.state_sn==0&&token_name.equals("eof")){
				states_active.put(0,pst);
				return true;
			}
			doEShift(pst,crt_token_sn, smb);		//if possible, e-shift then add to rlist for reduce
			int r_grm=reduce_table.get(pst.state_sn).get(crt_token_sn);
			if(r_grm==-1){	//no reduce then shift
				doShift(pst,crt_token_sn,smb);
			}else if(r_grm==-2){	//r-r conflict
				ArrayList<Integer> r_grms=reduces_table.get(pst.state_sn).get(crt_token_sn);
				for(int r:r_grms){
					doReduce(pst,r,crt_token_sn,smb);
				}
			}else{		//reduce
				doReduce(pst,r_grm,crt_token_sn,smb);
			}
		}
		return true;
	}
	
	private boolean doEShift(ParseState pst, int crt_token_sn, Symbol smb){
		int shift_state=shift_table.get(pst.state_sn).get(sym_e);
		if(shift_state==-1){
			return false;
		}else{
			System.out.println("e-Shift "+token_n2s.get(crt_token_sn)+" "+pst.state_sn+" goto "+shift_state);
			int r_grm=reduce_table.get(shift_state).get(crt_token_sn);
			if(r_grm==-1){
				return false;
			}else if(r_grm==-2){
				ArrayList<Integer> r_grms=reduces_table.get(shift_state).get(crt_token_sn);
				for(int r:r_grms){
					doEReduce(pst,r,crt_token_sn,smb);
				}
			}else{
					doEReduce(pst,r_grm,crt_token_sn,smb);				
			}			
		}
		return true;
	}
	
	private boolean doEReduce(ParseState pst, int r_grm, int crt_token_sn, Symbol smb){
		String reduce_head=grammar_table.get(r_grm).head;
		int nss=goto_table.get(pst.state_sn).get(symbol_sn.get(reduce_head));
		if(nss==-1){
			return false;
		}
		Symbol smb_e=new Symbol(); //ast = null
		ParseState pst_a;
		if(states_active.containsKey(nss)){		//goto state in active
			pst_a=states_active.get(nss);			
			smb_e.name=reduce_head;
			pst_a.addLink(pst,smb_e);
			pst_a.det_depth=0;			//refresh count and depth;
			pst_a.out_count++;
			System.out.println("e-reduce and merge "+reduce_head+" "+pst.state_sn+" goto "+nss);
		}else{								//goto a new state and add it to active
			pst_a=new ParseState();
			pst_a.state_sn=nss;
			smb_e.name=reduce_head;
			pst_a.addLink(pst,smb_e);
			pst_a.det_depth=pst.det_depth+1;
			pst_a.out_count++;
			states_rlst.add(pst_a);		//add to rlist for another reduce			
			System.out.println("e-reduce "+reduce_head+" "+pst.state_sn+" goto "+nss);
			doShift(pst_a,crt_token_sn,smb);
		}
		return true;
	}
	private boolean doShift(ParseState pst, int crt_token_sn, Symbol smb){
		int crt_s=pst.state_sn;
		int shift_s=shift_table.get(crt_s).get(crt_token_sn);
		if(shift_s==-1){
			return false;
		}
		ParseState new_pst;
		if(states_active.containsKey(shift_s)){		//merge to existing state in active
			new_pst=states_active.get(shift_s);
			new_pst.addLink(pst, smb);
			new_pst.det_depth=0;			//refresh count and depth;
			new_pst.out_count++;
			System.out.println("shift and merge "+token_n2s.get(crt_token_sn)+" "+pst.state_sn+" goto "+new_pst.state_sn);
		}else{					//new state insert in active
			new_pst=new ParseState();
			new_pst.state_sn=shift_s;
			new_pst.addLink(pst, smb);
			new_pst.det_depth=pst.det_depth+1;
			new_pst.out_count++;
			states_active.put(shift_s, new_pst);
		}
		System.out.println("shift "+token_n2s.get(crt_token_sn)+" "+pst.state_sn+" goto "+new_pst.state_sn);
		return true;
	}
	
	private boolean doReduce(ParseState pst_crt, int reduce_grammar, int crt_token_sn, Symbol smb){
		String reduce_head=grammar_table.get(reduce_grammar).head;					
		int ct=grammar_table.get(reduce_grammar).symbol_count;	
		ParseState pst_pre=pst_crt;
		if(ct<=pst_crt.det_depth){
			ASTRule rule=astRule_list.get(reduce_grammar);
			String method=rule.method;
			Symbol new_smb=new Symbol();
			new_smb.name=reduce_head;
			new_smb.path_start=pst_pre;
			for(int i=0;i<ct;i++){							//fast LR
				pst_pre=pst_pre.pre_state;					//move states_act;
			}
			new_smb.path_end=pst_pre;
			new_smb.path_count=ct;
			new_smb.ast=ast_gen.crtAST(method,pst_crt);		//build new ast TODO
			//System.out.println("create ast: "+ ast.getClass().getName());
			int nss=goto_table.get(pst_pre.state_sn).get(symbol_sn.get(reduce_head));
			if(nss==-1){
				return false;		//no reduce, maybe another shift
			}
			ParseState nps=null;
			if(states_active.containsKey(nss)){
				nps=states_active.get(nss);
				nps.addLink(pst_pre, new_smb);
				nps.det_depth=0;							//refresh count and depth;
				nps.out_count++;				
				System.out.println("reduce and merge "+reduce_head+" "+pst_pre.state_sn+" goto "+nss);
			}else{
				nps=new ParseState();
				nps.state_sn=nss;
				nps.addLink(pst_pre, new_smb);
				nps.det_depth=pst_pre.det_depth+1;
				nps.out_count++;
				System.out.println("reduce "+reduce_head+" "+pst_pre.state_sn+" goto "+nss);				
				doShift(nps,crt_token_sn,smb);				
			}
			states_rlst.add(nps);
			return true;
		}else{
			return doGLReduce(pst_crt,reduce_grammar,crt_token_sn,smb);
		}	
	}
	private boolean doGLReduce(ParseState pst_crt, int reduce_grammar, int crt_token_sn, Symbol smb){//need to refactor?
		String reduce_head=grammar_table.get(reduce_grammar).head;		
		ASTRule rule=astRule_list.get(reduce_grammar);
		String method=rule.method;		
		Grammar grm=grammar_table.get(reduce_grammar);
		int ct=grm.symbol_count;	
		ParseState pst_pre=pst_crt;		
		HashSet<ParseState> states_bfw=new HashSet<ParseState>();	//set of back forward state
		HashSet<ParseState> states_bfwl=new HashSet<ParseState>();	//tmp list for back forward state
		for(int i=pst_crt.det_depth;i>0;i--){			
			pst_pre=pst_pre.pre_state;						//fast move
		}
		states_bfw.add(pst_pre);
		for(int i=ct-pst_crt.det_depth+1;i>=2;i--){
			states_bfwl.clear();
			for(ParseState pst_b:states_bfw){
				if(pst_b.fixed){
					if(pst_b.symbol.name.equals(grm.symbols.get(i))){
						states_bfwl.add(pst_b.pre_state);
					}else{
						continue;
					}					
				}else{
					HashSet<String> sbs=new HashSet<String>();
					for(Symbol s:pst_b.symbols){
						sbs.add(s.name);
					}
					if(sbs.contains(grm.symbols.get(i))){
						states_bfwl.addAll(pst_b.pre_states);
					}else{
						continue;
					}					
				}
			}
			states_bfw.clear();
			states_bfw.addAll(states_bfwl);
		}
		for(ParseState pre_mr:states_bfw){
			int nss=goto_table.get(pre_mr.state_sn).get(symbol_sn.get(reduce_head));
			if(nss==-1){
				continue;		//no reduce
			}
			Symbol new_smb=new Symbol();
			new_smb.name=reduce_head;
			new_smb.path_start=pst_pre;
			new_smb.path_count=ct;
			new_smb.path_end=pre_mr;
			new_smb.ast=ast_gen.crtAST(method,pst_crt);		//build new ast
			//System.out.println("create ast: "+ ast.getClass().getName());
			ParseState nps=null;
			if(states_active.containsKey(nss)){
				nps=states_active.get(nss);
				nps.addLink(pre_mr, new_smb);
				nps.det_depth=0;							//refresh count and depth;
				nps.out_count++;					
				System.out.println("g-reduce and merge "+reduce_head+" "+pst_pre.state_sn+" goto "+nss);
			}else{
				nps=new ParseState();
				nps.state_sn=nss;
				nps.addLink(pre_mr, new_smb);
				nps.det_depth=pre_mr.det_depth+1;
				nps.out_count++;
				System.out.println("g-reduce "+reduce_head+" "+pst_pre.state_sn+" goto "+nss);
				doShift(nps,crt_token_sn,smb);
			}			
			states_rlst.add(nps);
		}
		return true;
	}
	private boolean doAllShift(int crt_token_sn, Symbol smb){
		return true;
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
