//public API: input(String) parse()
//before running, you'd better to check rules matching syntax rules 
package Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.*;
import java.util.*;

import LexAnalyzer.Tokenizer;
import LexAnalyzer.Token;

public class Parser {
	Tokenizer tokenizer=new Tokenizer();
	ArrayList<String> symbol_table=new ArrayList<String>();
	ArrayList<String> token_table=new ArrayList<String>();
	ArrayList<Grammar> grammar_table=new ArrayList<Grammar>();
	HashMap<String,Integer> symbol_sn=new HashMap<String,Integer>();
	HashMap<String,Integer> token_sn=new HashMap<String,Integer>();
	//HashMap<String,Integer> symbol_sn_stk=new HashMap<String,Integer>();
	ArrayList<AstRule> astRule_list=new ArrayList<>();
	ArrayList<ArrayList<Integer>> shift_table=new ArrayList<ArrayList<Integer>>();	
	ArrayList<ArrayList<Integer>> reduce_table=new ArrayList<ArrayList<Integer>>();
	ArrayList<ArrayList<Integer>> goto_table=new ArrayList<ArrayList<Integer>>();
	LinkedList<Symbol> symbol_stack=new LinkedList<Symbol>();
	LinkedList<Integer> state_stack=new LinkedList<Integer>();
	public static void main(String[] args) {
		Parser parser=new Parser();
		parser.analyzeGrm("out_grammar.txt"); 	System.out.println("analyzeGrm out_grammar.txt");
		parser.analyzeAST("grammar_AST.txt");	System.out.println("analyzeAST grammar_AST.txt");
		parser.analyzeLex("out_lexAnalyzer.txt");	System.out.println("analyzeLex out_lexAnalyzer.txt");
		parser.input("script_test1.txt");	
		parser.parse();
		parser.output("out_parser.txt");
		
		//tokenizer.getToken();
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
				shift_table.add(shift);		//add all shift action
				reduce_table.add(reduce);	//add all reduce action
				for(int i=1;i<actions.length;i++){
					if(!actions[i].equals("")){	
						String str_tmp=actions[i];
						if(str_tmp.equals("/")){
							shift.add(-1);		//"-1" means illegal action
							reduce.add(-1);
						}else{
							i++;
							if(str_tmp.charAt(0)=='s'){
								shift.add(Integer.parseInt(str_tmp.substring(1)));//shift
								reduce.add(-1);
							}else if(str_tmp.charAt(0)=='r'){
								reduce.add(Integer.parseInt(str_tmp.substring(1)));//reduce
								shift.add(-1);
							}else return false;
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
		int j=0;
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
				rule.symbol_count=grammar_table.get(astRule_list.size()-1).symbol_count;
				for(int i=2;i<str_list.size();i++){
					rule.parameters.add(Integer.parseInt(str_list.get(i).substring(1)));
				}
				//System.out.println(rule.method+" "+rule.symbol_count+" "+rule.parameters.get(0));
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
		Token token=tokenizer.getToken();
		int crt_state=0;
		int crt_grammar=0;
		state_stack.addFirst(0);
		Symbol symbol=new Symbol();
		symbol.name="Goal";
		symbol_stack.addFirst(symbol);
		while(true){			
			String token_name="";			
			switch(token.getType()){
			case "int":
				token_name="number";
				break;
			case "double":
				token_name="number";
				break;
			case "idn":
				token_name=token.getIdnName();
				break;
			case "res":
				token_name=token.getResName();
				break;
			case "opt":				
				token_name=token.getOptName();
				break;
			default:
				return false;
			}
			
				int crt_token_sn=token_sn.get(token_name);
				int shift_state=shift_table.get(crt_state).get(crt_token_sn);//TODO
				int reduce_grammar=reduce_table.get(crt_state).get(crt_token_sn);
				
				if(shift_state!=-1){//in shift table
					crt_state=shift_state;//shift
					Symbol smb=new Symbol();
					smb.name=token_name;
					state_stack.addFirst(crt_state);
					symbol_stack.addFirst(smb);
					System.out.println("s "+crt_state+" "+token_name);
					token=tokenizer.getToken();
					continue;

				}else if(reduce_grammar!=-1){//in reduce table
					crt_grammar=reduce_grammar;
					String sym=grammar_table.get(crt_grammar).head;
					for(int i=0;i<grammar_table.get(crt_grammar).symbol_count;i++){
						state_stack.remove();
						symbol_stack.remove();
					}
					Symbol smb=new Symbol();
					smb.name=sym;
					crt_state=state_stack.peek();
					crt_state=goto_table.get(crt_state).get(symbol_sn.get(sym));
					state_stack.addFirst(crt_state);
					symbol_stack.addFirst(smb);
					System.out.println("r "+crt_grammar+" g "+crt_state+" "+token_name);
					if(crt_grammar==0 && token_name.equals("eof")){//TODO
						System.out.println("eof");
						return true;				
					}
				}else{
					System.out.println("error parser state "+token_name+crt_state);
					return false;
				}
			
		}
	}
	
	
	public boolean output(String filename){
		
		return false;
	}
}