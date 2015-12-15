//public API: input(String) parse()
//before running, you'd better to check rules matching syntax rules 
package Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

import LexAnalyzer.Token;
import LexAnalyzer.Tokenizer;
import Parser.AST.*;
import Parser.ASTs.*;
import Parser.TypeSys.Type_SgStmt;
import Parser.TypeSys.Type_Stmt;

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
		ASTGenerator ast_gen=new ASTGenerator();
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
				Symbol reduce_smb=new Symbol();
				reduce_smb.name=reduce_head;
				AstRule rule=astRule_list.get(reduce_grammar);
				String method=rule.method;
				AST ast = null;
				switch(method){
				
				case "crtGoal"://$0
					
					break;
				case "lnkStmtLst"://$1 $0	
					ast=ast_gen.astStmtList(symbol_stack.get(1).ast, symbol_stack.get(0).ast);
					break;				 
				case "crtStmtLst":// $0 
					ast=ast_gen.astStmtList(null, symbol_stack.get(0).ast);
					break;               
				case "crtStmtVarDef":// $1 
					ast=ast_gen.astStmt(Type_Stmt.VarDef, symbol_stack.get(1).ast);
					break;
				case "crtStmtFuncDef":// $0 
					ast=ast_gen.astStmt(Type_Stmt.FuncDef, symbol_stack.get(0).ast);
					break;
				case "crtStmtIfExp":// $0
					ast=ast_gen.astStmt(Type_Stmt.IfExp, symbol_stack.get(0).ast);
					break;
				case "crtStmtWhlExp":// $0
					ast=ast_gen.astStmt(Type_Stmt.WhileExp, symbol_stack.get(0).ast);
					break;
				case "crtStmtSgStmt":// $1
					ast=ast_gen.astStmt(Type_Stmt.SgStmt, symbol_stack.get(1).ast);
					break; 
				case "crtSgControlFlow":// $0 
					ast=ast_gen.astSgStmt(Type_SgStmt.CtrFlw, symbol_stack.get(0).ast);
					break;
				case "crtSgVarAssign":// $0
					ast=ast_gen.astSgStmt(Type_SgStmt.VarAssign, symbol_stack.get(0).ast);
					break;
				case "crtSgCalcExp":// $0 
					ast=ast_gen.astSgStmt(Type_SgStmt.CalcExp, symbol_stack.get(0).ast);
					break;
				case "crtCtrFlwRtn":// $0 
					ast=ast_gen.astCtrFlw("return", symbol_stack.get(0).ast);
					break;
				case "crtCtrFlwCont":// $0 
					ast=ast_gen.astCtrFlw("continue", null);
					break;
				case "crtCtrFlwBrk":// $0 
					ast=ast_gen.astCtrFlw("break",null);
					break;
				case "lnkVarDef":// $1 $0 
					ast=ast_gen.astVarDef(symbol_stack.get(2).ast, null, symbol_stack.get(0).ast);
					break;            
				case "crtVarDef":// $1 $0 
					ast=ast_gen.astVarDef(null, symbol_stack.get(1).ast, symbol_stack.get(0).ast);
					break;
				case "lnkVarAsg":// $4 $2 $0	 
					ast=ast_gen.astVarAssign(symbol_stack.get(4).ast, null, symbol_stack.get(2).ast, symbol_stack.get(0).ast, symbol_stack.get(1).name);
					break; 
				case "crtVarAsgT":// $3 $2 $0   
					ast=ast_gen.astVarAssign(null,symbol_stack.get(3).ast, symbol_stack.get(2).ast, symbol_stack.get(0).ast, symbol_stack.get(1).name);
					break; 
				case "crtVarAsgC":// $2 $0    
					ast=ast_gen.astVarAssign(null,null, symbol_stack.get(2).ast, symbol_stack.get(0).ast, symbol_stack.get(1).name);
					break;   
				case "crtVarAsgAdd":// $2 $0	
					ast=ast_gen.astVarAssign(null,null, symbol_stack.get(2).ast, symbol_stack.get(0).ast, symbol_stack.get(1).name);
					break;
				case "crtVarAsgSub":// $2 $0    
					ast=ast_gen.astVarAssign(null,null, symbol_stack.get(2).ast, symbol_stack.get(0).ast, symbol_stack.get(1).name);
					break;                  
				case "crtVarAsgMul":// $2 $0	
					ast=ast_gen.astVarAssign(null,null, symbol_stack.get(2).ast, symbol_stack.get(0).ast, symbol_stack.get(1).name);
					break;	
				case "crtVarAsgDiv":// $2 $0	
					ast=ast_gen.astVarAssign(null,null, symbol_stack.get(2).ast, symbol_stack.get(0).ast, symbol_stack.get(1).name);
					break;		
				case "crtTpExpInt":// $0   
					ast=ast_gen.astTypeExp("int");
					break;      
				case "crtTpExpDb":// $0    
					ast=ast_gen.astTypeExp("double");
					break;   
				case "crtTpExpBl":// $0	
					ast=ast_gen.astTypeExp("bool");
					break;	
				case "crtTpExpStr":// $0     
					ast=ast_gen.astTypeExp("string");
					break; 
				case "crtTpExpChr":// $0
					ast=ast_gen.astTypeExp("char");
					break;
				case "crtTpExpVar":// $0      
					ast=ast_gen.astTypeExp("var");
					break;
				case "crtFncDef":// $6 $4 $1    
					ast=ast_gen.astFuncDef(symbol_stack.get(7).ast, symbol_stack.get(6).ast, symbol_stack.get(4).ast, symbol_stack.get(1).ast);
					break;  
				case "lnkParLst":// $2 $0     
					ast=ast_gen.astParList(symbol_stack.get(3).ast, symbol_stack.get(1).ast, symbol_stack.get(0).ast);
					break;  
				case "crtParLst":// $1 $0     
					ast=ast_gen.astParList(null, symbol_stack.get(1).ast, symbol_stack.get(0).ast);
					break;  
				case "crtParLstE"://   
					ast=ast_gen.astParList(null, null, null);
					break;        
				case "crtIfExpIf":// $0    
					ast=ast_gen.astIfExp(symbol_stack.get(0).ast, null);
					break;    
				case "crtIfExpEls":// $2 $0  
					ast=ast_gen.astIfExp(symbol_stack.get(2).ast, symbol_stack.get(0).ast);
					break;  
				case "crtIfStmtL":// $4 $1    
					ast=ast_gen.astIfStmt(symbol_stack.get(4).ast, symbol_stack.get(1).ast, null);
					break;   
				case "crtIfStmtS":// $4 $1     
					ast=ast_gen.astIfStmt(symbol_stack.get(3).ast, null, symbol_stack.get(1).ast);
					break;     
				case "crtElsStmtI":// $0    
					ast=ast_gen.astElseStmt(symbol_stack.get(0).ast, null, null);
					break;     
				case "crtElsStmtL":// $1   
					ast=ast_gen.astElseStmt(null, symbol_stack.get(1).ast, null);
					break;   
				case "crtElsStmtS":// $1    
					ast=ast_gen.astElseStmt(null, null, symbol_stack.get(1).ast);
					break;   
				case "crtWhlExp":// $4 $1    
					ast=ast_gen.astWhileExp(symbol_stack.get(4).ast, symbol_stack.get(1).ast);
					break;    
				case "crtCalcExpBl":// $0   
					ast=ast_gen.astCalcExp(symbol_stack.get(0).ast, null);
					break;
				case "crtCalcExpStr":// $0    
					ast=ast_gen.astCalcExp(null, symbol_stack.get(0).ast);
					break;
				case "crtStrSA":// $2 $0     
					ast=ast_gen.astStrExp(symbol_stack.get(0).ast);
					break;
				case "crtStrAS":// $2 $0   
					ast=ast_gen.astStrExp(symbol_stack.get(0).ast);
					break;
				case "crtStrS":// $0      
					ast=ast_gen.astStrExp(symbol_stack.get(0).ast);
					break;  
				case "crtStrC":// $0      
					ast=ast_gen.astStrExp(symbol_stack.get(0).ast);
					break;  
				case "crtBlExpAnd":// $2 $0       
					ast=ast_gen.astBoolExp(symbol_stack.get(2).ast, "&&", symbol_stack.get(0).ast);
					break;    
				case "crtBlExpOr":// $2 $0	
					ast=ast_gen.astBoolExp(symbol_stack.get(2).ast, "||", symbol_stack.get(0).ast);
					break;	 
				case "crtBlExpN":// $0		
					ast=ast_gen.astBoolExp(null, "!", symbol_stack.get(0).ast);
					break;		 
				case "crtBlExpCmp":// $0		
					ast=ast_gen.astBoolExp(null, null, symbol_stack.get(0).ast);
					break;		 
				case "crtCmpExpBl":// $1		
					ast=ast_gen.astCmpExp(symbol_stack.get(1).ast, null,  null,  null, 0);
					break;		 
				case "crtCmpExpL":// $2 $0		
					ast=ast_gen.astCmpExp(null, symbol_stack.get(2).ast,  ">",  symbol_stack.get(0).ast, 0);
					break;		 
				case "crtCmpExpLE":// $2 $0		
					ast=ast_gen.astCmpExp(null, symbol_stack.get(2).ast,  ">=",  symbol_stack.get(0).ast, 0);
					break;	 
				case "crtCmpExpS":// $2 $0	
					ast=ast_gen.astCmpExp(null, symbol_stack.get(2).ast,  "<",  symbol_stack.get(0).ast, 0);
					break;	 
				case "crtCmpExpSE":// $2 $0	
					ast=ast_gen.astCmpExp(null, symbol_stack.get(2).ast,  "<=",  symbol_stack.get(0).ast, 0);
					break;		 
				case "crtCmpExpE":// $2 $0		
					ast=ast_gen.astCmpExp(null, symbol_stack.get(2).ast,  "==",  symbol_stack.get(0).ast, 0);
					break;	 
				case "crtCmpExpN":// $2 $0		
					ast=ast_gen.astCmpExp(null, symbol_stack.get(2).ast,  "!=",  symbol_stack.get(0).ast, 0);
					break;	 
				case "crtCmpAdd":// $0		
					ast=ast_gen.astCmpExp(null, symbol_stack.get(0).ast, null, null, 0);
					break;
				case "crtCmpTrue":// $2 $0	//TODO	
					ast=ast_gen.astCmpExp(null,  null,  null, null, 1);
					break;	
				case "crtCmpFalse":// $2 $0		//TODO
					ast=ast_gen.astCmpExp(null,  null,  null, null, -1);
					break;	
				case "crtAddExpAdd":// $2 $0		
					ast=ast_gen.astAddExp(symbol_stack.get(2).ast, symbol_stack.get(0).ast, "+", null);
					break; 
				case "crtAddExpSub":// $2 $0		
					ast=ast_gen.astAddExp(symbol_stack.get(2).ast, symbol_stack.get(0).ast, "-", null);
					break;	 
				case "crtAddExpMns":// $0	
					ast=ast_gen.astAddExp(null, symbol_stack.get(0).ast, "-", null);
					break;		 
				case "crtAddExpMul":// $0	
					ast=ast_gen.astAddExp(null, symbol_stack.get(0).ast, null, null);
					break;		 
				case "crtAddExpInc":// $0	
					ast=ast_gen.astAddExp(null, null, "++", symbol_stack.get(0).ast);
					break;		 
				case "crtAddExpDec":// $0	
					ast=ast_gen.astAddExp(null, null, "--", symbol_stack.get(0).ast);
					break;		 
				case "crtAddExpIncT":// $1		
					ast=ast_gen.astAddExp(null, null, "++T", symbol_stack.get(1).ast);
					break;	 
				case "crtAddExpDecT":// $1	
					ast=ast_gen.astAddExp(null, null, "--T", symbol_stack.get(1).ast);
					break;	 
				case "crtMulExpMul":// $2 $0		
					ast=ast_gen.astMulExp( symbol_stack.get(2).ast, "*",  symbol_stack.get(0).ast);
					break;
				case "crtMulExpDiv":// $2 $0		
					ast=ast_gen.astMulExp( symbol_stack.get(2).ast, "/",  symbol_stack.get(0).ast);break;
				case "crtMulExpPri":// $0		
					ast=ast_gen.astMulExp( null, null,  symbol_stack.get(0).ast);
					break;
				case "crtPriExpNum":// $0	
					ast=ast_gen.astPriExp(null, symbol_stack.get(0).ast, null);
					break;	
				case "crtPriExpAdd":// $1	
					ast=ast_gen.astPriExp(symbol_stack.get(0).ast, null, null);
					break;	
				case "crtPriExpApp":// $0	
					ast=ast_gen.astPriExp(null, null,  symbol_stack.get(0).ast);
					break;
				case "lnkAppExp":// $5 $3 $1		
					ast=ast_gen.astApplyExp(symbol_stack.get(5).ast, symbol_stack.get(3).ast, symbol_stack.get(1).ast);
					break;
				case "crtAppExpP":// $2 $0	
					ast=ast_gen.astApplyExp(symbol_stack.get(2).ast, symbol_stack.get(0).ast, null);
					break;
				case "crtAppExpF":// $3 $1	
					ast=ast_gen.astApplyExp(null, symbol_stack.get(3).ast, symbol_stack.get(1).ast);
					break;		
				case "crtAppExpVar":// $0	
					ast=ast_gen.astApplyExp(null, symbol_stack.get(0).ast, null);
					break;
				case "lnkArgLst":// $2 $0	
					ast=ast_gen.astArgList(symbol_stack.get(2).ast, symbol_stack.get(0).ast);
					break;
				case "crtArgCalc":// $0	
					ast=ast_gen.astArgList(null, symbol_stack.get(0).ast);
					break;
				case "crtArgLstE"://
					ast=ast_gen.astArgList(null, null);
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
	
	public boolean output(String filename){
		
		return false;
	}
}