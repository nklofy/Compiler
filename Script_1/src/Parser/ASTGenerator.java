//this generator is for some possible add-in features
package Parser;
import java.util.*;
import Parser.ASTs.*;

public class ASTGenerator {
	AST astStmtList(AST stmtlist, AST stmt){
		AST_StmtList ast=new AST_StmtList();
		ast.setList((AST_StmtList)stmtlist);
		ast.setStmt((AST_Stmt)stmt);
		return ast;
	}
	
	AST astStmt(Type_Stmt type,AST stmt){
		AST_Stmt ast=new AST_Stmt();
		ast.setType(type);
		switch(type){
		case SgStmt:
			ast.setSgStmt((AST_SgStmt)stmt);
			break;
		case VarDef:
			ast.setVarDef((AST_VarDef)stmt);
			break;
		case IfExp:
			ast.setIfExp((AST_IfExp)stmt);
			break;
		case WhileExp:
			ast.setWhileExp((AST_WhileExp)stmt);
			break;
		case FuncDef:
			ast.setFuncDef((AST_FuncDef)stmt);
			break;
		case CtrFlw:
			ast.setCtrFlw((AST_CtrFlw)stmt);
			break;
		default:
			break;
		}
		return ast;
	}
	
	AST astSgStmt(Type_SgStmt type,AST stmt){
		AST_SgStmt ast=new AST_SgStmt();
		ast.setType(type);
		switch(type){	
		case CtrFlw:
			ast.setCtrFlw((AST_CtrFlw)stmt);
			break;
		case CalcExp:
			ast.setCalcExp((AST_CalcExp)stmt);
			break;
		case VarAssign:
			ast.setVarAssign((AST_VarAssign)stmt);
			break;
		default:
			break;
		}
		
		return ast;
	}
	
	AST astCtrFlw(String type,AST stmt){
		AST_CtrFlw ast=new AST_CtrFlw();
		ast.setType(type);
		switch(type){	
		case "return":
			ast.setCalcExp((AST_CalcExp)stmt);
			break;
		case "break":
			break;
		case "continue":
			break;
		default:
			break;
		}
		return ast;
	}
	
	AST astVarDef(AST var_def, AST type_exp, AST var){
		AST_VarDef ast=new AST_VarDef();
		ast.setVarDef((AST_VarDef)var_def);
		ast.setTypeExp((AST_TypeExp)type_exp);
		ast.setVar((AST_Var)var);
		return ast;
	}
	
	AST astVarAssign(AST var_assign,AST type_exp, AST var, AST calc_exp, String opt){
		AST_VarAssign ast=new AST_VarAssign();
		ast.setVarAssign((AST_VarAssign)var_assign);
		ast.setTypeExp((AST_TypeExp)type_exp);
		ast.setVar((AST_Var)var);
		ast.setCalcExp((AST_CalcExp)calc_exp);
		ast.setOpt(opt);
		return ast;
	}
	AST astTypeExp(String type){
		AST_TypeExp ast=new AST_TypeExp();
		ast.setType(type);
		return ast;
	}
	AST astFuncDef(AST name, AST par_list, AST stmt_list){
		AST_FuncDef ast=new AST_FuncDef();
		ast.setFuncDef((AST_Var)name, (AST_ParList)par_list, (AST_StmtList)stmt_list);
		return ast;
	}
	AST astParList(AST par_list, AST type_exp, AST var){
		AST_ParList ast=new AST_ParList();
		ast.setParList((AST_ParList)par_list, (AST_TypeExp)type_exp, (AST_Var)var);
		return ast;
	}
	AST astIfExp(AST if_stmt, AST else_stmt){
		AST_IfExp ast=new AST_IfExp();
		ast.setIfExp((AST_IfStmt)if_stmt, (AST_ElseStmt)else_stmt);
		return ast;
	}
	AST astWhileExp(AST bool_exp, AST stmt_list){
		AST_WhileExp ast=new AST_WhileExp();
		ast.setWhileExp((AST_BoolExp)bool_exp, (AST_StmtList)stmt_list);
		return ast;
	}
	AST astIfStmt(AST bool_exp, AST stmt_list, AST sg_stmt){
		AST_IfStmt ast=new AST_IfStmt();
		ast.setIfStmt((AST_BoolExp)bool_exp, (AST_StmtList)stmt_list, (AST_SgStmt)sg_stmt);
		return ast;
	}
	AST astElseStmt(AST if_exp, AST stmt_list, AST sg_stmt){
		AST_ElseStmt ast=new AST_ElseStmt();
		ast.setElseStmt((AST_IfExp)if_exp, (AST_StmtList)stmt_list, (AST_SgStmt)sg_stmt);
		return ast;
	}
	AST astBoolExp(AST bool_exp, String opt, AST cmp_exp){
		AST_BoolExp ast=new AST_BoolExp();
		ast.setBoolExp((AST_BoolExp)bool_exp, opt, (AST_CmpExp)cmp_exp);
		return ast;
	}
	AST astCmpExp(AST bool_exp, AST add_exp1, String opt, AST add_exp2){
		AST_CmpExp ast=new AST_CmpExp();
		ast.setCmpExp((AST_BoolExp)bool_exp, (AST_AddExp)add_exp1, opt, (AST_AddExp)add_exp2);
		return ast;
	}
	AST astStrExp(AST str){
		AST_StrExp ast=new AST_StrExp();
		return ast;
	}
	AST astCalcExp(AST add_exp, AST bool_exp, AST str_exp){
		AST_CalcExp ast=new AST_CalcExp();
		ast.setCalcExp((AST_AddExp)add_exp, (AST_BoolExp)bool_exp, (AST_StrExp)str_exp);
		return ast;
	}
	AST astAddExp(AST add_exp, AST mul_exp, String opt, AST var){
		AST_AddExp ast=new AST_AddExp();
		ast.setAddExp((AST_AddExp)add_exp, (AST_MulExp)mul_exp, opt, (AST_Var)var);
		return ast;
	}
	AST astMulExp(AST mul_exp, String opt, AST pri_exp){
		AST_MulExp ast=new AST_MulExp();
		ast.setMulExp((AST_MulExp)mul_exp, opt, (AST_PriExp)pri_exp);
		return ast;
	}
	AST astPriExp(AST add_exp, AST num, AST var, AST apply_exp){
		AST_PriExp ast=new AST_PriExp();
		ast.setPriExp((AST_AddExp)add_exp, (AST_Num)num, (AST_Var)var, (AST_ApplyExp)apply_exp);
		return ast;
	}
	AST astNum(String type, String buffer){
		AST_Num ast=new AST_Num();
		ast.setNum(type, buffer);
		return ast;
	}
	AST astVar(String name){
		AST_Var ast=new AST_Var();
		ast.setVar(name);
		return ast;
	}
	AST astApplyExp(AST apply_exp, AST var, AST arg_list){
		AST_ApplyExp ast=new AST_ApplyExp();
		ast.setApplyExp((AST_ApplyExp)apply_exp, (AST_Var)var, (AST_ArgList)arg_list);
		return ast;
	}
	AST astArgList(AST arg_list, AST var){
		AST_ArgList ast=new AST_ArgList();
		return ast;
	}
}
