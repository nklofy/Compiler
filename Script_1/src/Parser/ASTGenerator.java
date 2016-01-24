//this generator is for some possible add-in features
package Parser;
import java.util.*;
import Parser.ASTs.*;
import Parser.TypeSys.*;

public class ASTGenerator {
	public AST crtAST(String method, ParseState crt_state, LinkedList<Symbol> symbs){		
		AST ast=null;
		AST ast0,ast1,ast2,ast3,ast4,ast5,ast6,ast7,ast9,ast10;
	switch(method){
	//1. add link to up-ast, 2. if isMerged, skip, 3. create local sym-table, 4. update sym-table of up-ast
	//0	crtGoal 0
	case "crtGoal":
		ast=symbs.get(0).ast;
		break;
	//1	lnkStmtLst 1 0
	case "lnkStmtLst":
		ast1=symbs.get(1).ast;
		ast0=symbs.get(0).ast;
		AST_StmtList ast_t1=(AST_StmtList)ast1;
		ast_t1.addStmt(ast0);
		ast0.setLink(ast_t1);
		ast=ast_t1;
		break;
	//2	crtStmtLst 0
	case "crtStmtLst":
		ast0=symbs.get(0).ast;
		AST_StmtList ast_t2=new AST_StmtList();
		ast_t2.addStmt(ast0);
		ast=ast_t2;
		break;
	//3	crtStmtClsDef 0
	case "crtStmtClsDef":
		ast0=symbs.get(0).ast;
		AST_Stmt ast_t3=new AST_Stmt();
		ast_t3.setType(Stmt_DefCls.class.getName());
		ast_t3.setStmt(ast0);
		ast=ast_t3;
		break;
	//4	crtStmtIntfDef 0
	case "crtStmtIntfDef":
		ast0=symbs.get(0).ast;
		AST_Stmt ast_t4=new AST_Stmt();
		ast_t4.setType(Stmt_DefIntf.class.getName());
		ast_t4.setStmt(ast0);
		ast=ast_t4;
		break;
	//5	crtStmtFuncDef 0
	case "crtStmtFuncDef":
		ast0=symbs.get(0).ast;
		AST_Stmt ast_t5=new AST_Stmt();
		ast_t5.setType(Stmt_DefFunc.class.getName());
		ast_t5.setStmt(ast0);
		ast=ast_t5;
		break;
	//6	crtStmtIf 0
	case "crtStmtIf":
		ast0=symbs.get(0).ast;
		AST_Stmt ast_t6=new AST_Stmt();
		ast_t6.setType(Stmt_If.class.getName());
		ast_t6.setStmt(ast0);
		ast=ast_t6;
		break;	
	//7	crtStmtWhl 0
	case "crtStmtWhl":
		ast0=symbs.get(0).ast;
		AST_Stmt ast_t7=new AST_Stmt();
		ast_t7.setType(Stmt_Whl.class.getName());
		ast_t7.setStmt(ast0);
		ast=ast_t7;
		break;		
	//8	crtStmtSg 0
	case "crtStmtSg":
		ast0=symbs.get(0).ast;
		AST_Stmt ast_t8=new AST_Stmt();
		ast_t8.setType(Stmt_Sg.class.getName());
		ast_t8.setStmt(ast0);
		ast=ast_t8;
		break;	
	//9	crtSgVarAssign 0
	case "crtSgVarAssign":
		ast0=symbs.get(0).ast;
		Stmt_Sg ast_t9=new Stmt_Sg();
		ast_t9.setType(SgStmt_AsnVar.class.getName());
		ast_t9.setSg(ast0);
		ast=ast_t9;
		break;
	//10	crtSgVarDef 0
	case "crtSgVarDef":
		ast0=symbs.get(0).ast;
		Stmt_Sg ast_t10=new Stmt_Sg();
		ast_t10.setType(SgStmt_DefVar.class.getName());
		ast_t10.setSg(ast0);
		ast=ast_t10;
		break;
	//11	crtSgCalc 0
	case "crtSgCalc":
		ast0=symbs.get(0).ast;
		Stmt_Sg ast_t11=new Stmt_Sg();
		ast_t11.setType(Expr_Calc.class.getName());
		ast_t11.setSg(ast0);
		ast=ast_t11;
		break;
	//12	crtSgCtrF 0
	case "crtSgCtrF":		
		ast0=symbs.get(0).ast;
		Stmt_Sg ast_t12=new Stmt_Sg();
		ast_t12.setType(SgStmt_CtrFlw.class.getName());
		ast_t12.setSg(ast0);
		ast=ast_t12;
		break;
	//13	crtCFRetE 0
	case "crtCFRetE":
		ast0=symbs.get(0).ast;
		SgStmt_CtrFlw ast_t13=new SgStmt_CtrFlw();
		ast_t13.setRtExp((Expr_Calc)ast0);
		ast_t13.setCFT(SgStmt_CtrFlw.en_Ctrflw.t_returnExp);
		ast=ast_t13;
		break;
	//14	crtCFRet 0
	case "crtCFRet":
		SgStmt_CtrFlw ast_t14=new SgStmt_CtrFlw();
		ast_t14.setCFT(SgStmt_CtrFlw.en_Ctrflw.t_return);
		ast=ast_t14;
		break;		
	//15	crtCFCont 0
	case "crtCFCont":
		SgStmt_CtrFlw ast_t15=new SgStmt_CtrFlw();
		ast_t15.setCFT(SgStmt_CtrFlw.en_Ctrflw.t_continue);
		ast=ast_t15;
		break;	
	//16	crtCFBrk 0
	case "crtCFBrk":
		SgStmt_CtrFlw ast_t16=new SgStmt_CtrFlw();
		ast_t16.setCFT(SgStmt_CtrFlw.en_Ctrflw.t_break);
		ast=ast_t16;
		break;	
//		17	linkVarDef 2 0
	case "linkVarDef":
		ast0=symbs.get(0).ast;
		ast2=symbs.get(2).ast;
		SgStmt_DefVar ast_t17=new SgStmt_DefVar(); 
		ast_t17.setPredef((SgStmt_DefVar)ast2);
		ast_t17.setVar((ExprPri_Var)ast0);	//TODO	
		break;
//		18	linkVarDefAsg 4 2 0
	case "linkVarDefAsg":
		
		break;
//		19	crtVarDef 1 0
	case "crtVarDef":
		
		break;
//		20	crtVarDefAsg 3 2 0
	case "crtVarDefAsg":
		
		break;
//		21	crtVarAsg 2 0
//		22	crtLftFld 0
//		23	crtLftArr 0
//		24	crtTpBsc 0
//		25	crtTpArr 0
//		26	crtTpIdn 0
//		27	crtTpGnrcTp 0
//		28	crtTpFunc 0
//		29	crtBscTpInt 0
//		30	crtBscTpDb 0
//		31	crtBscTpBl 0
//		32	crtBscTpStr 0
//		33	crtBscTpChr 0
//		34	lnkArrTp 2
//		35	lnkIdnTpVar 2 0
//		36	crtIdnTpVar 0
//		37	crtGnrcTp 3 1
//		38	lnkGnrcArgsVar 2 0
//		39	lnkGnrcArgsExt 4 0
//		40	crtGnrcArgsVar 0
//		41	crtGnrcArgsExt 0
//		42	crtClassDef 8 6 5 4 3 1
//		43	crtIntfDef 6 4 3 1
//		44	crtScpInfLst 0
//		45	crtScpInfLstE 0
//		46	lnkScpInf 1 0
//		47	crtScpInf 0
//		48	crtScpStc 0
//		49	crtScpPbl 0
//		50	crtScpPrv 0
//		51	crtScpFnl 0
//		52	crtGnrcParLst 1
//		53	crtGnrcParLstE 0
//		54	lnkGnrcPar 2 0
//		55	lnkGnrcParExt 4 2 0
//		56	crtGnrcParVar 0
//		57	crtGnrcParExt 2 0
//		58	crtExtLst 0
//		59	crtExtLstE 0
//		60	lnkExtIdn 2 0
//		61	crtExtIdn 0
//		62	crtImpLst 0
//		63	crtImpLstE 0
//		64	lnkImpIdn 2 0
//		65	crtImpIdn 0
//		66	crtMmbDef 0
//		67	crtMmbDefE 0
//		68	lnkMmbFld 1 0
//		69	lnkMmbMthd 1 0
//		70	crtMmbFld 0
//		71	crtMemMthd 0
//		72	crtFldDef 2 1
//		73	crtMthdDef 1 0
//		74	crtFuncDef 7 6 4 1
//		75	crtParLst 0
//		76	crtParLstE 0
//		77	lnkParVar 3 1 0
//		78	crtParVar 1 0
//		79	crtIfIf 0
//		80	crtIfIfEls 2 0
//		81	crtIfLst 4 1
//		82	crtIfSg 3 1
//		83	crtElsIf 0
//		84	crtElsLst 1
//		85	crtElsSg 1
//		86	crtWhl 4 1
//		87	crtExpLmd 0
//		88	crtExpCalc 0
//		89	crtCalcCnd 0
//		90	crtCalcNewCls 0
//		91	crtCalcNewArr 0
//		92	crtNewClsIdn 3 1
//		93	crtNewClsGnrc 3 1
//		94	crtNewArrTp 1 0
//		95	crtNewArrLst 1
//		96	lnkArrLst 2 0
//		97	crtArrLst 0
//		98	crtArrCalc 0
//		99	crtArrLstArr 1
//		100	lnkDimLst 3 1
//		101	crtDimLst 1
//		102	crtCndBl 0
//		103	lnkBlOr 2 0
//		104	crtBlOr 0
//		105	lnkOrAnd 2 0
//		106	crtOrAnd 0
//		107	crtAndCmp 0
//		108	lnkCmpGT 2 0
//		109	lnkCmpGE 2 0
//		110	lnkCmpLT 2 0
//		111	lnkCmpLE 2 0
//		112	lnkCmpEQ 2 0
//		113	lnkCmpNE 2 0
//		114	crtCmpNAdd 0
//		115	crtCmpAdd 0
//		116	crtCmpTr 0
//		117	crtCmpFs 0
//		118	lnkAddAdd 2 0
//		119	lnkAddMns 2 0
//		120	crtAddPls 0
//		121	crtAddMns 0
//		122	crtAddMul 0
//		123	lnkMulMul 2 0
//		124	lnkMulSub 2 0
//		125	crtMulUnr 0
//		126	crtUnrInc 0
//		127	crtUnrDec 0
//		128	crtUnrIncP 0
//		129	crtUnrDecP 0
//		130	crtUnrN 0
//		131	crtUnrAcc 0
//		132	crtUnrCst 0
//		133	crtCst 2 0
//		134	crtAccFld 0
//		135	crtAccArr 0
//		136	crtAccApp 0
//		137	crtAccPri 0
//		138	lnkFldAccVar 2 0 
//		139	lnkFldAccCls 2 0
//		140	crtFldAccVar 0
//		141	crtFldAccThs 0
//		142	crtFldAccSpr 0
//		143	crtArrAcc 1 0
//		144	crtPriNum 0
//		145	crtPriStr 0
//		146	crtPriChr 0
//		147	crtPriCnd 1
//		148	crtAppAcc 5 3 1
//		149	crtAppApp 5 3 1
//		150	crtAppVar 3 1
//		151	crtArgLst 0
//		152	crtArgLstE 0
//		153	lnkArgLst 2 0
//		154	crtArgCalc 0
//		155	crtLbdExp 7 5 1


	case "":
		break;
	default:
		break;	
	}
	if(ast==null){
		System.out.println("error creating ast "+method+" state "+ crt_state.state_sn);
	}
	return ast;
	}	
	/*
	AST astStmtList(AST stmtlist, AST stmt){
		AST_StmtList ast=new AST_StmtList();
		ast.setList((AST_StmtList)stmtlist);
		ast.setStmt((AST_Stmt)stmt);
		return ast;
	}
	
	AST astStmt(Type_Stmt type,AST stmt){
		AST_Stmt ast=new AST_Stmt();
		//ast.setType(type);
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
	}*/
}
