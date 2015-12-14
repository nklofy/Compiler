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
		interpreter.ctr_flow=new RT_CtrFlow();
		//Type_Func f_prn=new Type_Func();
		//interpreter.env_global.addFunc("print", f_prn);
		//Type_Func f_gets=new Type_Func();
		//interpreter.env_global.addFunc("gets", f_gets);
		interpreter.interpret((AST_StmtList) ast_tree);//TODO
		parser.output("out_parser.txt");
		
	}	
	private RT_Env env_global; 	//runtime, refs of global vars, global functions	
	private RT_Static env_static;
	private RT_Frame crt_frm;
	private RT_CtrFlow ctr_flow;
	public RT_Env getGlbEnv(){
		return this.env_global;
	}
	public RT_Static getStcEnv(){
		return this.env_static;
	}
	public RT_Frame getCrtFrm(){
		return this.crt_frm;
	}
	public RT_CtrFlow getCtrFlow(){
		return this.ctr_flow;
	}
	public boolean setCrtFrm(RT_Frame crt_frame){
		this.crt_frm=crt_frame;
		return true;
	}
	
	//eval ASTs
	
	
	public boolean interpret(AST_StmtList stmt_list){
		if(this.ctr_flow.getFlow()==RT_CtrFlow.Flow_State.s_go)
			return(stmt_list.eval(this));
		return false;
	}

	public boolean interpret(AST_Stmt stmt){
		if(this.ctr_flow.getFlow()==RT_CtrFlow.Flow_State.s_go)
			return(stmt.eval(this));
		return false;
	}

	public boolean interpret(AST_SgStmt sg_stmt){
		if(this.ctr_flow.getFlow()==RT_CtrFlow.Flow_State.s_go)
			return(sg_stmt.eval(this));
		return false;
	}

	public boolean interpret(AST_CtrFlw ctr_flw){
		if(this.ctr_flow.getFlow()==RT_CtrFlow.Flow_State.s_go)
			return(ctr_flw.eval(this));
		return false;
	}

	public boolean interpret(AST_VarDef var_def){
		if(this.ctr_flow.getFlow()==RT_CtrFlow.Flow_State.s_go)
			return(var_def.eval(this));
		return false;
		
	}

	public boolean interpret(AST_VarAssign var_assign){
		if(this.ctr_flow.getFlow()==RT_CtrFlow.Flow_State.s_go)
			return(var_assign.eval(this));
		return false;
	}

	public boolean interpret(AST_TypeExp type_exp){
		if(this.ctr_flow.getFlow()==RT_CtrFlow.Flow_State.s_go)
			return(type_exp.eval(this));
		return false;
	}

	public boolean interpret(AST_FuncDef func_def){		
		if(this.ctr_flow.getFlow()==RT_CtrFlow.Flow_State.s_go)
			return(func_def.eval(this));
		return false;
	}

	public boolean interpret(AST_ParList par_list){
		if(this.ctr_flow.getFlow()==RT_CtrFlow.Flow_State.s_go)
			return(par_list.eval(this));
		return false;
	}

	public boolean interpret(AST_IfExp if_exp){
		if(this.ctr_flow.getFlow()==RT_CtrFlow.Flow_State.s_go)
			return(if_exp.eval(this));
		return false;
	}

	public boolean interpret(AST_IfStmt if_stmt){
		if(this.ctr_flow.getFlow()==RT_CtrFlow.Flow_State.s_go)
			return(if_stmt.eval(this));
		return false;
	}

	public boolean interpret(AST_ElseStmt else_stmt){
		if(this.ctr_flow.getFlow()==RT_CtrFlow.Flow_State.s_go)
			return(else_stmt.eval(this));
		return false;
	}

	public boolean interpret(AST_WhileExp while_exp){
		if(this.ctr_flow.getFlow()==RT_CtrFlow.Flow_State.s_go)
			return(while_exp.eval(this));
		return false;
	}

	public boolean interpret(AST_CalcExp calc_exp){
		if(this.ctr_flow.getFlow()==RT_CtrFlow.Flow_State.s_go)
			return(calc_exp.eval(this));
		return false;
	}

	public boolean interpret(AST_StrExp str_exp){
		if(this.ctr_flow.getFlow()==RT_CtrFlow.Flow_State.s_go)
			return(str_exp.eval(this));
		return false;
	}

	public boolean interpret(AST_BoolExp bool_exp){
		if(this.ctr_flow.getFlow()==RT_CtrFlow.Flow_State.s_go)
			return(bool_exp.eval(this));
		return false;
	}

	public boolean interpret(AST_CmpExp cmp_exp){
		if(this.ctr_flow.getFlow()==RT_CtrFlow.Flow_State.s_go)
			return(cmp_exp.eval(this));
		return false;
	}

	public boolean interpret(AST_AddExp add_exp){
		if(this.ctr_flow.getFlow()==RT_CtrFlow.Flow_State.s_go)
			return(add_exp.eval(this));
		return false;
	}

	public boolean interpret(AST_MulExp mul_exp){
		if(this.ctr_flow.getFlow()==RT_CtrFlow.Flow_State.s_go)
			return(mul_exp.eval(this));
		return false;
	}

	public boolean interpret(AST_PriExp pri_exp){
		if(this.ctr_flow.getFlow()==RT_CtrFlow.Flow_State.s_go)
			return(pri_exp.eval(this));
		return false;
	}

	public boolean interpret(AST_ApplyExp apply_exp){
		if(this.ctr_flow.getFlow()==RT_CtrFlow.Flow_State.s_go)
			return(apply_exp.eval(this));
		return false;
	}

	public boolean interpret(AST_ArgList arg_list){
		if(this.ctr_flow.getFlow()==RT_CtrFlow.Flow_State.s_go)
			return(arg_list.eval(this));
		return false;
	}

	public boolean interpret(AST_Var var){
		if(this.ctr_flow.getFlow()==RT_CtrFlow.Flow_State.s_go)
			return(var.eval(this));
		return false;
	}

	public boolean interpret(AST_Num num){
		if(this.ctr_flow.getFlow()==RT_CtrFlow.Flow_State.s_go)
			return(num.eval(this));
		return false;
	}
}
