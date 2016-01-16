//this generator is for some possible add-in features
package Parser;
import java.util.*;
import Parser.ASTs.*;
import Parser.TypeSys.old.Type_SgStmt;
import Parser.TypeSys.old.Type_Stmt;

public class ASTGenerator {
	AST crtAST(String method, ParseState crt_state){			
	switch(method){
	//0	crtGoal 0
	//1	lnkStmtLst 1 0
	//2	crtStmtLst 0
	//3	crtStmtClsDef 0
	//4	crtStmtIntfDef 0
	//5	crtStmtFuncDef 0
	//6	crtStmtIf 0
	//7	crtStmtWhl 0
	//8	crtStmtSg 0
	//9	crtSgVarAssign 0
	//10	crtSgVarDef 0
	//11	crtSgCalc 0
	//12	crtSgCtrF 0
	//13	crtCtrFRet 0
	//14	crtCtrFCont 0
	//15	crtCtrFBrk 0
	//16	linkVarDef 2 0
	//17	linkVarDefAsg 4 2 0
	//18	crtVarDef 1 0
	//19	crtVarDefAsg 3 2 0
	//20	crtVarAsg 2 0
	//21	crtLftFld 0
	//22	crtLftArr 0
	//23	crtTpBsc 0
	//24	crtTpArr 0
	//25	crtTpIdn 0
	//26	crtTpGnrcTp 0
	//27	crtTpFunc 0
	//28	crtBscTpInt 0
	//29	crtBscTpDb 0
	//30	crtBscTpBl 0
	//31	crtBscTpStr 0
	//32	crtBscTpChr 0
	//33	lnkArrTp 2
	//34	lnkIdnTpVar 2 0
	//35	crtIdnTpVar 0
	//36	crtGnrcTp 3 1
	//37	lnkGnrcArgsVar 2 0
	//38	lnkGnrcArgsExt 4 0
	//39	crtGnrcArgsVar 0
	//40	crtGnrcArgsExt 0
	//41	crtClassDef 8 6 5 4 3 1
	//42	crtIntfDef 6 4 3 1
	//43	crtScpInfLst 0
	//44	crtScpInfLstE 0
	//45	lnkScpInf 1 0
	//46	crtScpInf 0
	//47	crtScpStc 0
	//48	crtScpPbl 0
	//49	crtScpPrv 0
	//50	crtScpFnl 0
	//51	crtGnrcParLst 1
	//52	crtGnrcParLstE 0
	//53	lnkGnrcPar 2 0
	//54	lnkGnrcParExt 4 2 0
	//55	crtGnrcParVar 0
	//56	crtGnrcParExt 2 0
	//57	crtExtLst 0
	//58	crtExtLstE 0
	//59	lnkExtIdn 2 0
	//60	crtExtIdn 0
	//61	crtImpLst 0
	//62	crtImpLstE 0
	//63	lnkImpIdn 2 0
	//64	crtImpIdn 0
	//65	crtMmbDef 0
	//66	crtMmbDefE 0
	//67	lnkMmbFld 1 0
	//68	lnkMmbMthd 1 0
	//69	crtMmbFld 0
	//70	crtMemMthd 0
	//71	crtFldDef 2 1
	//72	crtMthdDef 1 0
	//73	crtFuncDef 7 6 4 1
	//74	crtParLst 0
	//75	crtParLstE 0
	//76	lnkParVar 3 1 0
	//77	crtParVar 1 0
	//78	crtIfIf 0
	//79	crtIfIfEls 2 0
	//80	crtIfLst 4 1
	//81	crtIfSg 3 1
	//82	crtElsIf 0
	//83	crtElsLst 1
	//84	crtElsSg 1
	//85	crtWhl 4 1
	//86	crtExpLmd 0
	//87	crtExpCalc 0
	//88	crtCalcCnd 0
	//89	crtCalcNewCls 0
	//90	crtCalcNewArr 0
	//91	crtNewClsIdn 3 1
	//92	crtNewClsGnrc 3 1
	//93	crtNewArrTp 1 0
	//94	crtNewArrLst 1
	//95	lnkArrLst 2 0
	//96	crtArrLst 0
	//97	crtArrCalc 0
	//98	crtArrLstArr 1
	//99	lnkDimLst 3 1
	//100	crtDimLst 1
	//101	crtCndBl 0
	//102	lnkBlOr 2 0
	//103	crtBlOr 0
	//104	lnkOrAnd 2 0
	//105	crtOrAnd 0
	//106	crtAndCmp 0
	//107	lnkCmpGT 2 0
	//108	lnkCmpGE 2 0
	//109	lnkCmpLT 2 0
	//110	lnkCmpLE 2 0
	//111	lnkCmpEQ 2 0
	//112	lnkCmpNE 2 0
	//113	crtCmpNAdd 0
	//114	crtCmpAdd 0
	//115	crtCmpTr 0
	//116	crtCmpFs 0
	//117	lnkAddAdd 2 0
	//118	lnkAddMns 2 0
	//119	crtAddPls 0
	//120	crtAddMns 0
	//121	crtAddMul 0
	//122	lnkMulMul 2 0
	//123	lnkMulSub 2 0
	//124	crtMulUnr 0
	//125	crtUnrInc 0
	//126	crtUnrDec 0
	//127	crtUnrIncP 0
	//128	crtUnrDecP 0
	//129	crtUnrN 0
	//130	crtUnrAcc 0
	//131	crtUnrCst 0
	//132	crtCst 2 0
	//133	crtAccFld 0
	//134	crtAccArr 0
	//135	crtAccApp 0
	//136	crtAccPri 0
	//137	lnkFldAccVar 2 0 
	//138	lnkFldAccCls 2 0
	//139	crtFldAccVar 0
	//140	crtFldAccThs 0
	//141	crtFldAccSpr 0
	//142	crtArrAcc 1 0
	//143	crtPriNum 0
	//144	crtPriStr 0
	//145	crtPriChr 0
	//146	crtPriCnd 1
	//147	crtAppAcc 5 3 1
	//148	crtAppApp 5 3 1
	//149	crtAppVar 3 1
	//150	crtArgLst 0
	//151	crtArgLstE 0
	//152	lnkArgLst 2 0
	//153	crtArgCalc 0
	//154	crtLbdExp 7 5 1

	case "":
		break;
	default:
		break;	
	}
	return null;
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
