//public API: input(String) parse()
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
	HashMap<String,Integer> sym_sn=new HashMap<String,Integer>();
	HashMap<String,Integer> token_sn=new HashMap<String,Integer>();
	ArrayList<AstRule> astRule_list=new ArrayList<>();
	ArrayList<ArrayList<Integer>> shift_table=new ArrayList<ArrayList<Integer>>();
	ArrayList<ArrayList<Integer>> reduce_table=new ArrayList<ArrayList<Integer>>();
	ArrayList<ArrayList<Integer>> goto_table=new ArrayList<ArrayList<Integer>>();
	public static void main(String[] args) {
		Parser parser=new Parser();
		parser.analyzeGrm("out_grammar.txt");
		parser.analyzeAST("grammar_AST.txt");
		parser.analyzeLex("out_lexAnalyzer.txt");
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
				for(int i=1;i<tokens.length;i++){
					if(!tokens[i].equals("")){						
						token_table.add(tokens[i]);			//add all tokens
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
				for(int i=1;i<tokens.length;i++){
					if(!tokens[i].equals("")){						
						symbol_table.add(tokens[i]);		//add all symbols
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
							}else if(str_tmp.charAt(0)=='r'){
								reduce.add(Integer.parseInt(str_tmp.substring(1)));//reduce
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
		return true;		
	}
	
	public boolean analyzeAST(String filename){		//read and analyze
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
				for(int i=0;i<words.length;i++){
					if(!words[i].equals("")){
						rule.parameters.add(words[i]);//format like "1" "method" "parameters"
					}					
				}
				rule.method=rule.parameters.get(1);
				//System.out.println(rule.method);
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
	
	public boolean parse(){//TODO//////////////////////////////////
		Token token=tokenizer.getToken();
		while(true){
			if(token.getType().equals("res")&&token.getResName().equals("eof")){//TODO
				System.out.println("eof");
				break;
			}
			token=tokenizer.getToken();
		}
		return false;
	}
	
	private AST create(AST left, AST right, Node node){
		
		
		return null;
		
	}
	
	public boolean output(String filename){
		
		return false;
	}
}