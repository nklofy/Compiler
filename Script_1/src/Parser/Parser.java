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
	private ArrayList<String> symbol_table=new ArrayList<String>();
	private ArrayList<String> token_table=new ArrayList<String>();
	private ArrayList<Grammar> grammar_table=new ArrayList<Grammar>();
	private HashMap<String,Integer> symbol_sn=new HashMap<String,Integer>();
	private HashMap<String,Integer> token_sn=new HashMap<String,Integer>();
	private ArrayList<AstRule> astRule_list=new ArrayList<AstRule>();
	private ArrayList<ArrayList<Integer>> shift_table=new ArrayList<ArrayList<Integer>>();	
	private ArrayList<ArrayList<int[]>> reduce_table=new ArrayList<ArrayList<int[]>>();
	private ArrayList<ArrayList<Integer>> goto_table=new ArrayList<ArrayList<Integer>>();
	private AST ast_tree;
	private HashMap<Integer,ParseState> states_act=new HashMap<Integer,ParseState>();
	private HashMap<Integer,ParseState> states_amb=new HashMap<Integer,ParseState>();
	private int sym_e;
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
				ArrayList<int[]> reduce=new ArrayList<int[]>();
				shift_table.add(shift);		//add all shift action
				reduce_table.add(reduce);	//add all reduce action
				for(int i=1;i<actions.length;i++){
					if(!actions[i].equals("")){	
						String str_tmp=actions[i];
						if(str_tmp.equals("/")){
							shift.add(-1);		//"-1" means illegal action		
							reduce.add(new int[]{-1});
						}else{
							int j=0,jp=0,k=0;
							if(str_tmp.charAt(0)=='s'){		//shift, maybe reduce
								while(j<str_tmp.length()&&str_tmp.charAt(j)!='r'){
									j++;
								}
								shift.add(Integer.parseInt(str_tmp.substring(1, j)));
								reduce.add(new int[]{-1});								
							}else{ 			//no shift, but reduce
								shift.add(-1);	
								reduce.add(new int[]{-1});
							}
							if(j<str_tmp.length()&&str_tmp.charAt(j)=='r'){
								jp=j;
								int[] intl=reduce.get(reduce.size()-1);
								while(j<str_tmp.length()){
									j++;
									if(str_tmp.charAt(j)=='r'||j==str_tmp.length()){
										intl[k++]=Integer.parseInt(str_tmp.substring(jp+1, j));
										jp=j;
									}	
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
							i++;
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
		//int j=0;
		for(int i=0;i<symbol_table.size();i++){
			symbol_sn.put(symbol_table.get(i),i);
			//symbol_sn_stk.put(symbol_table.get(j),j);
			//j++;
		}
		for(int i=0;i<token_table.size();i++){
			token_sn.put(token_table.get(i),i);
			//symbol_sn_stk.put(token_table.get(j),j);
			//j++;
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
	
	public boolean parse(){//TODO
		ParseState state_start=new ParseState();
		sym_e=symbol_sn.get("e");
		Token token=tokenizer.getToken();
		int crt_state=0;
		//boolean gotNewToken=true;
		//ASTGenerator ast_gen=new ASTGenerator();
		state_start.state=crt_state;
		Symbol symbol=new Symbol();
		symbol.name="Goal";
		state_start.symbol=symbol;
		
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
			int crt_token_sn=token_sn.get(token_name);
			doReduce(crt_token_sn);
			doShift(crt_token_sn);
				
			
			if(true){
				break;
			}
			
			/*int shift_state=shift_table.get(crt_state).get(crt_token_sn);
			int[] reduce_grammars=reduce_table.get(crt_state).get(crt_token_sn);//TODO
			int reduce_grammar=0;
			if(shift_state!=-1){//in shift table
				crt_state=shift_state;//shift
				
				state_stack.addFirst(crt_state);
				symbol_stack.addFirst(smb);
				System.out.println("s "+crt_state+" "+token_name);
				token=tokenizer.getToken();
				gotNewToken=true;
				continue;
				
			}else if(reduce_grammar!=0){//in reduce table
				gotNewToken=false;
				String reduce_head=grammar_table.get(reduce_grammar).head;
				Symbol reduce_smb=new Symbol();
				reduce_smb.name=reduce_head;
				AstRule rule=astRule_list.get(reduce_grammar);
				String method=rule.method;
				AST ast = null;
				ast=ast_gen.crtAST(method,state_act);
				
				reduce_smb.ast=ast;
				//System.out.println("create ast: "+ ast.getClass().getName());
				int ct=grammar_table.get(reduce_grammar).symbol_count;
				for(int i=0;i<ct;i++){
					state_stack.remove();	
					symbol_stack.remove();						
				}	
				crt_state=state_stack.peek();
				crt_state=goto_table.get(crt_state).get(symbol_sn.get(reduce_head));
				state_stack.addFirst(crt_state);
				symbol_stack.addFirst(reduce_smb);
				System.out.println("r "+reduce_grammar+" "+reduce_smb.name+" g "+crt_state+" "+token_name);
				if(reduce_grammar==0 && token_name.equals("eof")){
					System.out.println("eof, "+"finished parsing");
					ast_tree=ast;
					return true;				
				}
			}else{
				System.out.println("error in line "+token.getLine()+", state "+crt_state+" "+symbol_stack.get(0).name+", token "+token_name);
				return false;
			}	*/		
		}
		return false;
	}
	
	private boolean doShift(int crt_token_sn){
		for(ParseState pst:states_act.values()){
			int crt_state=pst.state;
			int shift_state=shift_table.get(crt_state).get(crt_token_sn);
			if(shift_state==-1){	
				shift_state=shift_table.get(crt_state).get(sym_e);
				if(shift_state==-1){
					states_act.remove(pst.state);
					continue;
				}else{
					doSignleReduce(crt_token_sn);
				}
			}
			ParseState new_pst=states_act.get(shift_state);
			if(new_pst==null){
				new_pst=new ParseState();
				init; count; depth;
				states_act.remove(pst.state);
				states_act.put(shift_state, new_pst);
			}else{
				new_pst.pre_states.add(pst);
			}
			
		}
		return true;
	}
	private boolean doReduce(int crt_token_sn){
		for(ParseState pst:states_act.values()){
			int[] reduce_grammars=reduce_table.get(pst.state).get(crt_token_sn);
			for(int i:reduce_grammars){
				doSingleReduce();
			}
		}
		return true;
	}
	
	private boolean doSingleReduce(int i){
		move states_act;
		refresh count and depth;
		build new ast;
		add to amb;
		return;
	}
	
	public boolean output(String filename){
		PrintWriter out=null;
		String line="";
		try {
			out=new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			
		} catch (Exception e) {			
			e.printStackTrace();
		}finally{
			out.close();
		}
		return false;
	}
}