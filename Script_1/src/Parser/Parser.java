//public API: input(String) parse()
//before running, you'd better to check rules matching syntax rules 
package Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

import LexAnalyzer.Token;
import LexAnalyzer.Tokenizer;
import Parser.AST.*;

public class Parser {
	private Tokenizer tokenizer=new Tokenizer();
	private List<String> symbol_table=new ArrayList<String>();
	private List<String> token_table=new ArrayList<String>();
	private List<Grammar> grammar_table=new ArrayList<Grammar>();
	private Map<String,Integer> symbol_sn=new HashMap<String,Integer>();
	private Map<String,Integer> token_sn=new HashMap<String,Integer>();
	private List<AstRule> astRule_list=new ArrayList<AstRule>();
	private List<ArrayList<Integer>> shift_table=new ArrayList<ArrayList<Integer>>();	
	private List<ArrayList<Integer>> reduce_table=new ArrayList<ArrayList<Integer>>();
	private List<ArrayList<Integer>> goto_table=new ArrayList<ArrayList<Integer>>();
	private LinkedList<Symbol> symbol_stack=new LinkedList<Symbol>();
	private LinkedList<Integer> state_stack=new LinkedList<Integer>();
	private AST ast_tree;
	public AST getAST(){
		return ast_tree;
	}
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
		boolean gotNewToken=true;
		state_stack.addFirst(0);
		Symbol symbol=new Symbol();
		symbol.name="Goal";
		symbol_stack.addFirst(symbol);

		while(true){
			String token_name="";
			Symbol smb=new Symbol();
			if(gotNewToken){
				smb.type=token.getType();
				switch(smb.type){
				case "int":
					token_name="number";smb.name=token_name;
					smb.num_value=token.getNumValue();
					break;
				case "double":
					token_name="number";smb.name=token_name;
					smb.num_value=token.getNumValue();
					break;
				case "idn":
					token_name="var";smb.name=token.getIdnName();
					break;
				case "res":
					token_name=token.getResName();smb.name=token_name;
					break;
				case "opt":
					token_name=token.getOptName();smb.name=token_name;
					break;
				default:
					return false;
				}
			}			
			int crt_token_sn=token_sn.get(token_name);
			int shift_state=shift_table.get(crt_state).get(crt_token_sn);
			int reduce_grammar=reduce_table.get(crt_state).get(crt_token_sn);

			if(shift_state!=-1){//in shift table
				crt_state=shift_state;//shift

				state_stack.addFirst(crt_state);
				symbol_stack.addFirst(smb);
				//System.out.println("s "+crt_state+" "+token_name);
				token=tokenizer.getToken();
				gotNewToken=true;
				continue;
				
			}else if(reduce_grammar!=-1){//in reduce table
				gotNewToken=false;
				String reduce_head=grammar_table.get(reduce_grammar).head;
				//TODO create new AST
				Symbol reduce_smb=new Symbol();
				reduce_smb.name=reduce_head;
				//create AST as needed
				AstRule rule=astRule_list.get(reduce_grammar);
				String method=rule.method;
				AST ast = null;
				switch(method){
				
				case "crtGoal"://$0
					break;
				case "lnkStmtLst"://$1 $0	
					break;				 
				case "crtStmtLst":// $0 
					break;               
				case "crtStmtVarDef":// $1 
					break;
				case "crtStmtFuncDef":// $0 
					break;
				case "crtStmtIfExp":// $0
					break;
				case "crtStmtWhlExp":// $0
					break;
				case "crtStmtSgStmt":// $1 
					break; 
				case "crtSgControlFlow":// $1 
					break;
				case "crtSgVarAssign":// $0
					break;
				case "crtSgCalcExp":// $0 
					break;
				case "crtCtrFlwCont":// $0 
					break;
				case "crtCtrFlwRtn":// $0 
					break;
				case "crtCtrFlwBrk":// $0 
					break;
				case "lnkVarDef":// $1 $0   
					break;            
				case "crtVarDef":// $1 $0 
					break;
				case "lnkVarAsg":// $4 $2 $0	 
					break; 
				case "crtVarAsgT":// $3 $2 $0   
					break; 
				case "crtVarAsgC":// $2 $0    
					break;   
				case "crtVarAsgAdd":// $2 $0		
					break;
				case "crtVarAsgSub":// $2 $0    
					break;                  
				case "crtVarAsgMul":// $2 $0		
					break;	
				case "crtVarAsgDiv":// $2 $0	
					break;		
				case "crtTpExpInt":// $0   
					break;      
				case "crtTpExpDb":// $0    
					break;   
				case "crtTpExpBl":// $0	
					break;	
				case "crtTpExpStr":// $0     
					break; 
				case "crtTpExpVar":// $0      
					break;
				case "crtFncDef":// $6 $4 $1    
					break;  
				case "lnkParLst":// $2 $0     
					break;  
				case "crtParLst":// $1 $0     
					break;  
				case "crtParLstE"://   
					break;        
				case "crtIfExpIf":// $0    
					break;    
				case "crtIfExpEls":// $2 $0  
					break;  
				case "crtIfStmtL":// $4 $1    
					break;   
				case "crtIfStmtS":// $4 $1     
					break;     
				case "crtElsStmtI":// $0    
					break;     
				case "crtElsStmtL":// $1    
					break;   
				case "crtElsStmtS":// $1    
					break;   
				case "crtWhlExp":// $4 $1    
					break;        
				case "crtCalcExpAdd":// $0    
					break;     
				case "crtCalcExpBl":// $0    
					break;
				case "crtCalcExpStr":// $0    
					break;
				case "crtStrSA":// $2 $0      
					break;
				case "crtStrAS":// $2 $0      
					break;
				case "crtStrS":// $0      
					break;  
				case "crtBlExpAnd":// $2 $0       
					break;    
				case "crtBlExpOr":// $2 $0		
					break;	 
				case "crtBlExpN":// $0		
					break;		 
				case "crtBlExpCmp":// $0		
					break;		 
				case "crtCmpExpBl":// $1		
					break;		 
				case "crtCmpExpL":// $2 $0		
					break;		 
				case "crtCmpExpLE":// $2 $0		
					break;	 
				case "crtCmpExpS":// $2 $0	
					break;	 
				case "crtCmpExpSE":// $2 $0	
					break;		 
				case "crtCmpExpE":// $2 $0		
					break;	 
				case "crtCmpExpN":// $2 $0		
					break;	 
				case "crtAddExpAdd":// $2 $0		
					break; 
				case "crtAddExpSub":// $2 $0		
					break;	 
				case "crtAddExpMns":// $0	
					break;		 
				case "crtAddExpMul":// $0	
					break;		 
				case "crtAddExpInc":// $0	
					break;		 
				case "crtAddExpDec":// $0	
					break;		 
				case "crtAddExpIncT":// $1		
					break;	 
				case "crtAddExpDecT":// $1	
					break;	 
				case "crtMulExpMul":// $2 $0		
					break;
				case "crtMulExpDiv":// $2 $0		
					break;
				case "crtMulExpPri":// $0		
					break;
				case "crtPriExpNum":// $0	
					break;	
				case "crtPriExpAdd":// $1	
					break;	
				case "crtPriExpApp":// $0	
					break;	
				case "crtPriExpVar":// $0	
					break;
				case "lnkAppExp":// $5 $3 $1		
					break;
				case "crtAppExpP":// $2 $0	
					break;
				case "crtAppExpF":// $3 $1	
					break;		
				case "crtAppExpVar":// $0	
					break;
				case "lnkArgLst":// $2 $0	
					break;
				case "crtArgLst":// $0	
					break;
				case "crtArgLstE"://
					break;
				default:
					break;
				}
				reduce_smb.ast=ast;
				System.out.println("create ast: "+ ast.getClass().getName());
				int ct=grammar_table.get(reduce_grammar).symbol_count;
				for(int i=0;i<ct;i++){
					state_stack.remove();	
					symbol_stack.remove();						
				}	
				crt_state=state_stack.peek();
				crt_state=goto_table.get(crt_state).get(symbol_sn.get(reduce_head));
				state_stack.addFirst(crt_state);
				symbol_stack.addFirst(reduce_smb);
				System.out.println("r "+reduce_grammar+" g "+crt_state+" "+token_name);
				if(reduce_grammar==0 && token_name.equals("eof")){//TODO
					System.out.println("eof");
					ast_tree=ast;
					return true;				
				}
			}else{
				//System.out.println("error parser state "+token_name+crt_state);
				return false;
			}			
		}
	}
	/*case "astGoal":
					ast=crtStmtList((StmtListAST)symbol_stack.get(0).ast,null);
					break;
				case "astLinkStmtList":
					ast=crtStmtList((StmtListAST)symbol_stack.get(1).ast,(StmtAST)symbol_stack.get(0).ast);
					break;
				case "astListStmt":
					ast=crtStmtList(null,(StmtAST)symbol_stack.get(0).ast);
					break;
				case "astStmtExp":
					ast=crtStmtExp((ExpAST)symbol_stack.get(1).ast);
					break;
				case "astLinkExpAdd":
					ast=crtExpAdd((ExpAST)symbol_stack.get(2).ast,(AddAST)symbol_stack.get(0).ast, "+");
					break;
				case "astLinkExpSub":
					ast=crtExpAdd((ExpAST)symbol_stack.get(2).ast,(AddAST)symbol_stack.get(0).ast, "-");
					break;
				case "astExpSub":
					ast=crtExpAdd(null,(AddAST)symbol_stack.get(0).ast, "-");
					break;
				case "astExpAdd":
					ast=crtExpAdd(null,(AddAST)symbol_stack.get(0).ast, null);
					break;
				case "astLinkAddMul":
					ast=crtAddMul((AddAST)symbol_stack.get(2).ast,(MulAST)symbol_stack.get(0).ast,"*");
					break;
				case "astLinkAddDiv":
					ast=crtAddMul((AddAST)symbol_stack.get(02).ast,(MulAST)symbol_stack.get(0).ast,"/");
					break;
				case "astAddMul":
					ast=crtAddMul(null,(MulAST)symbol_stack.get(0).ast,null);
					break;
				case "astMulPri":
					ast=crtMulPri((PriAST)symbol_stack.get(0).ast);
					break;
				case "astPriNum":
					ast=crtPriNum((NumAST)symbol_stack.get(0).ast);
					break;
				case "astPriExp":
					ast=crtPriExp((ExpAST)symbol_stack.get(1).ast);
					break;
				case "astNumExp":						
					ast=crtNumExp(symbol_stack.get(0).type,symbol_stack.get(0).getNumValue());
					break;

				default:
					break;
	AST crtStmtList(StmtListAST list,StmtAST stmt){	
		StmtListAST ast=new StmtListAST();
		ast.stmt_list=list;
		ast.stmt=stmt;
		return ast;
	}
	AST crtStmtExp(ExpAST exp){	
		StmtAST ast=new StmtAST();
		ast.exp=exp;
		return ast;
	}
	AST crtExpAdd(ExpAST exp,AddAST add,String opt){	
		ExpAST ast=new ExpAST();
		ast.exp=exp;
		ast.add_exp=add;
		ast.opt=opt;
		return ast;
	}
	AST crtAddMul(AddAST add,MulAST mul,String opt){
		AddAST ast=new AddAST();
		ast.add_exp=add;
		ast.mul_exp=mul;
		ast.opt=opt;
		return ast;
	}
	AST crtMulPri(PriAST pri){	//11 mul_exp pri_exp //11 astMulPri $1
		MulAST ast=new MulAST();
		ast.pri_exp=pri;
		return ast;
	}
	AST crtPriNum(NumAST num){	//12 pri_exp num_exp //12 astPriNum $1
		PriAST ast=new PriAST();
		ast.num_exp=num;
		return ast;
	}
	AST crtPriExp(ExpAST exp){	//13 pri_exp ( exp ) //13 astPriExp $2
		PriAST ast=new PriAST();
		ast.exp=exp;
		return ast;
	}
	AST crtNumExp(String type,String buffer){	//14 num_exp number //14 astNumExp $1
		NumAST ast=new NumAST();
		ast.num_type=type;
		ast.buffer=buffer;
		return ast;
	}*/

	public boolean output(String filename){
		
		return false;
	}
}