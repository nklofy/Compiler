//this generator is for some possible add-in features
package Parser;
import java.util.*;
import Parser.ASTs.*;
import Parser.ASTs.old.AST_AddExp;
import Parser.ASTs.old.AST_ApplyExp;
import Parser.ASTs.old.AST_ArgList;
import Parser.ASTs.old.AST_BoolExp;
import Parser.ASTs.old.AST_CalcExp;
import Parser.ASTs.old.AST_CmpExp;
import Parser.ASTs.old.AST_CtrFlw;
import Parser.ASTs.old.AST_ElseStmt;
import Parser.ASTs.old.AST_FuncDef;
import Parser.ASTs.old.AST_IfExp;
import Parser.ASTs.old.AST_IfStmt;
import Parser.ASTs.old.AST_MulExp;
import Parser.ASTs.old.AST_Num;
import Parser.ASTs.old.AST_ParList;
import Parser.ASTs.old.AST_PriExp;
import Parser.ASTs.old.AST_SgStmt;
import Parser.ASTs.old.AST_Stmt;
import Parser.ASTs.old.AST_StmtList;
import Parser.ASTs.old.AST_StrExp;
import Parser.ASTs.old.AST_TypeExp;
import Parser.ASTs.old.AST_Var;
import Parser.ASTs.old.AST_VarAssign;
import Parser.ASTs.old.AST_VarDef;
import Parser.ASTs.old.AST_WhileExp;
import Parser.TypeSys.old.Type_SgStmt;
import Parser.TypeSys.old.Type_Stmt;

public class ASTGenerator {
	AST crtAST(String method, ParseState crt_state){
		return null;
	}
	/*switch(method){
	
	case "crtGoal"://$0
			ast=ast_gen.astStmtList(symbol_stack.get(0).ast, null);
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
		case "crtSgVarAssign":// $0
			ast=ast_gen.astSgStmt(Type_SgStmt.VarAssign, symbol_stack.get(0).ast);
			break;
		case "crtSgCalcExp":// $0 
			ast=ast_gen.astSgStmt(Type_SgStmt.CalcExp, symbol_stack.get(0).ast);
			break;
		case "crtSgControlFlow":// $0 
			ast=ast_gen.astSgStmt(Type_SgStmt.CtrFlw, symbol_stack.get(0).ast);
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
		case "lnkVarDef":// $2 $0 
			ast=ast_gen.astVarDef(symbol_stack.get(2).ast, null, symbol_stack.get(0).ast, null);
			break;          
		case "lnkVarDefC"://  $4 $2 $0 
			ast=ast_gen.astVarDef(symbol_stack.get(4).ast, null, symbol_stack.get(2).ast, symbol_stack.get(0).ast);
			break;
		case "crtVarDef":// $1 $0 
			ast=ast_gen.astVarDef(null, symbol_stack.get(1).ast, symbol_stack.get(0).ast, null);
			break; 
		case "crtVarDefC":// $3 $2 $0
			ast=ast_gen.astVarDef(null,symbol_stack.get(3).ast, symbol_stack.get(2).ast, symbol_stack.get(0).ast);
			break; 
		case "crtVarAsgC":// $2 $0    
			ast=ast_gen.astVarAssign(symbol_stack.get(2).ast, symbol_stack.get(0).ast, symbol_stack.get(1).name);
			break;   
		case "crtVarAsgAdd":// $2 $0	
			ast=ast_gen.astVarAssign(symbol_stack.get(2).ast, symbol_stack.get(0).ast, symbol_stack.get(1).name);
			break;
		case "crtVarAsgSub":// $2 $0    
			ast=ast_gen.astVarAssign(symbol_stack.get(2).ast, symbol_stack.get(0).ast, symbol_stack.get(1).name);
			break;                  
		case "crtVarAsgMul":// $2 $0	
			ast=ast_gen.astVarAssign(symbol_stack.get(2).ast, symbol_stack.get(0).ast, symbol_stack.get(1).name);
			break;	
		case "crtVarAsgDiv":// $2 $0	
			ast=ast_gen.astVarAssign(symbol_stack.get(2).ast, symbol_stack.get(0).ast, symbol_stack.get(1).name);
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
		case "crtStrS":// $0      
			ast=ast_gen.astStrExp(symbol_stack.get(0).value, null);
			break;  
		case "crtStrC":// $0      
			ast=ast_gen.astStrExp(null, symbol_stack.get(0).value);
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
		case "crtCmpTrue":// $2 $0	
			ast=ast_gen.astCmpExp(null,  null,  null, null, 1);
			break;	
		case "crtCmpFalse":// $2 $0		
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
			ast=ast_gen.astPriExp(null, symbol_stack.get(0).ast, null, null);
			break;	
		case "crtPriExpAdd":// $1	
			ast=ast_gen.astPriExp(symbol_stack.get(1).ast, null, null, null);
			break;	
		case "crtPriExpApp":// $0	
			ast=ast_gen.astPriExp(null, null,  symbol_stack.get(0).ast, null);
			break;
		case "crtPriExpStr": //$0	
			ast=ast_gen.astPriExp(null, null, null, symbol_stack.get(0).ast);
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
		}*/
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
	
	AST astVarDef(AST var_def, AST type_exp, AST var, AST calc_exp){
		AST_VarDef ast=new AST_VarDef();
		ast.setVarDef((AST_VarDef)var_def);
		ast.setTypeExp((AST_TypeExp)type_exp);
		ast.setVar((AST_Var)var);
		ast.setCalcExp((AST_CalcExp)calc_exp);
		return ast;
	}
	
	AST astVarAssign(AST var, AST calc_exp, String opt){
		AST_VarAssign ast=new AST_VarAssign();
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
	AST astFuncDef(AST type_exp, AST name, AST par_list, AST stmt_list){
		AST_FuncDef ast=new AST_FuncDef();
		ast.setFuncDef((AST_TypeExp)type_exp, (AST_Var)name, (AST_ParList)par_list, (AST_StmtList)stmt_list);
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
	AST astCmpExp(AST bool_exp, AST add_exp1, String opt, AST add_exp2, int bl_value){
		AST_CmpExp ast=new AST_CmpExp();
		ast.setCmpExp((AST_BoolExp)bool_exp, (AST_AddExp)add_exp1, opt, (AST_AddExp)add_exp2, bl_value);
		return ast;
	}
	AST astStrExp(String str, String chr){
		AST_StrExp ast=new AST_StrExp();
		ast.setStr(str, chr);
		return ast;
	}
	AST astCalcExp(AST bool_exp, AST str_exp){
		AST_CalcExp ast=new AST_CalcExp();
		ast.setCalcExp((AST_BoolExp)bool_exp, (AST_StrExp)str_exp);
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
	AST astPriExp(AST add_exp, AST num, AST apply_exp, AST str_exp){
		AST_PriExp ast=new AST_PriExp();
		ast.setPriExp((AST_AddExp)add_exp, (AST_Num)num, (AST_ApplyExp)apply_exp, (AST_StrExp)str_exp);
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
		ast.setArgList((AST_ArgList)arg_list, (AST_CalcExp)var);
		return ast;
	}
}
