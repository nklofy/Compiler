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
		interpreter.crt_frm=new RT_Frame();
		interpreter.env_global.crt_frm=interpreter.crt_frm;
		interpreter.crt_frm.crt_env=interpreter.env_global;
		interpreter.interpret((AST_StmtList) ast_tree);//TODO
		parser.output("out_parser.txt");
		
	}	
	private RT_Env env_global; 	//runtime, refs of global vars, global functions	
	private RT_Static env_static;
	private RT_Frame crt_frm;
	public RT_Env getGlbEnv(){
		return this.env_global;
	}
	public RT_Static getStcEnv(){
		return this.env_static;
	}
	public RT_Frame getCrtFrm(){
		return this.crt_frm;
	}
	public boolean setCrtFrm(RT_Frame crt_frame){
		this.crt_frm=crt_frame;
		return true;
	}
	public boolean getVar(AST_Var var){//refresh var's value in table
		return(var.eval(this));
	}
	
	public boolean interpret(AST_StmtList stmt_list){
		return(stmt_list.eval(this));
	}

	public boolean interpret(AST_Stmt stmt){
		return(stmt.eval(this));
	}

	public boolean interpret(AST_SgStmt sg_stmt){
		return(sg_stmt.eval(this));
	}

	public boolean interpret(AST_CtrFlw ctr_flw){
		ctr_flw.eval(this);
		switch(ctr_flw.getFlwType()){
		case "return":
			12
			break;
		case "break":
			
			break;
		case "continue":
			
			break;
		default:
			break;
		}
		return false;
	}

	public boolean interpret(AST_VarDef var_def){
		return(var_def.eval(this));
		
	}

	public boolean interpret(AST_VarAssign var_assign){
		return(var_assign.eval(this));
	}

	public boolean interpret(AST_TypeExp type_exp){
		return(type_exp.eval(this));
	}

	public boolean interpret(AST_FuncDef func_def){		
		return(func_def.eval(this));
	}

	public boolean interpret(AST_ParList par_list){
		return(par_list.eval(this));
	}

	public boolean interpret(AST_IfExp if_exp){
		return(if_exp.eval(this));
	}

	public boolean interpret(AST_IfStmt if_stmt){
		return(if_stmt.eval(this));
	}

	public boolean interpret(AST_ElseStmt else_stmt){		
		return(else_stmt.eval(this));
	}

	public boolean interpret(AST_WhileExp while_exp){
		return(while_exp.eval(this));
	}

	public boolean interpret(AST_CalcExp calc_exp){
		return(calc_exp.eval(this));
	}

	public boolean interpret(AST_StrExp str_exp){
		return(str_exp.eval(this));
	}

	public boolean interpret(AST_BoolExp bool_exp){
		return(bool_exp.eval(this));
	}

	public boolean interpret(AST_CmpExp cmp_exp){
		return(cmp_exp.eval(this));
	}

	public boolean interpret(AST_AddExp add_exp){
		return(add_exp.eval(this));
	}

	public boolean interpret(AST_MulExp mul_exp){
		return(mul_exp.eval(this));
	}

	public boolean interpret(AST_PriExp pri_exp){
		return(pri_exp.eval(this));
	}

	public boolean interpret(AST_ApplyExp apply_exp){
		return(apply_exp.eval(this));
	}

	public boolean interpret(AST_ArgList arg_list){
		return(arg_list.eval(this));
	}

	public boolean interpret(AST_Var var){
		return(var.eval(this));
	}

	public boolean interpret(AST_Num num){
		return(num.eval(this));
	}
}
