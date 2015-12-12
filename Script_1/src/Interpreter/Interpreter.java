package Interpreter;
import Parser.*;
import Parser.ASTs.*;

public class Interpreter {

	public static void main(String[] args) {
		System.out.println("hello");
		Parser parser=new Parser();
		parser.analyzeGrm("out_grammar.txt"); 	System.out.println("analyzeGrm out_grammar.txt");
		parser.analyzeAST("grammar_AST.txt");	System.out.println("analyzeAST grammar_AST.txt");
		parser.analyzeLex("out_lexAnalyzer.txt");	System.out.println("analyzeLex out_lexAnalyzer.txt");
		parser.input("script_test1.txt");	
		parser.parse();
		AST ast_tree=parser.getAST();
		Interpreter interpreter=new Interpreter();
		interpreter.env_global=new RT_Env();
		interpreter.env_global.crt_frm=new RT_Frame();
		interpreter.env_global.crt_frm.crt_env=interpreter.env_global;
		interpreter.interpret((AST_StmtList) ast_tree);//TODO
		parser.output("out_parser.txt");
		
	}	
	private RT_Env env_global; 	//runtime, refs of global vars, global functions	
	private RT_Static env_static;
	private RT_Frame crt_frame;
	public RT_Env getGlbEnv(){
		return this.env_global;
	}
	public RT_Static getStcEnv(){
		return this.env_static;
	}
	public RT_Frame getCrtFrm(){
		return this.crt_frame;
	}
	public boolean setCrtFrm(RT_Frame crt_frame){
		this.crt_frame=crt_frame;
		return true;
	}
	public boolean getVar(AST_Var var){//refresh var's value in table
		return true;
	}
	
	public boolean interpret(AST_StmtList stmt_list){
		return false;
	}

	public boolean interpret(AST_Stmt stmt){
		return false;
	}

	public boolean interpret(AST_SgStmt sg_stmt){
		return false;
	}

	public boolean interpret(AST_CtrFlw ctr_flw){
		return false;
	}

	public boolean interpret(AST_VarDef var_def){
		return false;
	}

	public boolean interpret(AST_VarAssign var_assign){
		return false;
	}

	public boolean interpret(AST_TypeExp type_exp){
		return false;
	}

	public boolean interpret(AST_FuncDef func_def){
		return false;
	}

	public boolean interpret(AST_ParList par_list){
		return false;
	}

	public boolean interpret(AST_IfExp if_exp){
		return false;
	}

	public boolean interpret(AST_IfStmt if_stmt){
		return false;
	}

	public boolean interpret(AST_ElseStmt else_stmt){
		return false;
	}

	public boolean interpret(AST_WhileExp while_exp){
		return false;
	}

	public boolean interpret(AST_CalcExp calc_exp){
		return false;
	}

	public boolean interpret(AST_StrExp str_exp){
		return false;
	}

	public boolean interpret(AST_BoolExp bool_exp){
		return false;
	}

	public boolean interpret(AST_CmpExp cmp_exp){
		return false;
	}

	public boolean interpret(AST_AddExp add_exp){
		return false;
	}

	public boolean interpret(AST_MulExp mul_exp){
		return false;
	}

	public boolean interpret(AST_PriExp pri_exp){
		return false;
	}

	public boolean interpret(AST_ApplyExp apply_exp){
		return false;
	}

	public boolean interpret(AST_ArgList arg_list){
		return false;
	}

	public boolean interpret(AST_Var var){
		return false;
	}

	public boolean interpret(AST_Num num){
		return false;
	}
}
