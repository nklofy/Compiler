//this generator is for some possible add-in features
package Parser;
import java.util.*;
import Parser.ASTs.*;
import Parser.TypeSys.*;

public class ASTGenerator {
	TypeChecker type_sys = new TypeChecker();
	public TypeChecker getTypeSys() {
		return type_sys;
	}
	public AST crtAST(String method, ParseState crt_state, List<Symbol> symbs){		
		AST ast=null;
		AST ast0,ast1,ast2,ast3,ast4,ast5,ast6,ast7,ast8,ast9,ast10;
	switch(method){
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
		//ast0.setLink(ast_t1);
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
		ast_t3.setASTType(Stmt_DefCls.class.getName());
		ast_t3.setStmt(ast0);
		ast=ast_t3;
		break;
//4	crtStmtIntfDef 0
	case "crtStmtIntfDef":
		ast0=symbs.get(0).ast;
		AST_Stmt ast_t4=new AST_Stmt();
		ast_t4.setASTType(Stmt_DefIntf.class.getName());
		ast_t4.setStmt(ast0);
		ast=ast_t4;
		break;
//5	crtStmtFuncDef 0
	case "crtStmtFuncDef":
		ast0=symbs.get(0).ast;
		AST_Stmt ast_t5=new AST_Stmt();
		ast_t5.setASTType(Stmt_DefFunc.class.getName());
		ast_t5.setStmt(ast0);
		ast=ast_t5;
		break;
//6	crtStmtIf 0
	case "crtStmtIf":
		ast0=symbs.get(0).ast;
		AST_Stmt ast_t6=new AST_Stmt();
		ast_t6.setASTType(Stmt_If.class.getName());
		ast_t6.setStmt(ast0);
		ast=ast_t6;
		break;	
//7	crtStmtWhl 0
	case "crtStmtWhl":
		ast0=symbs.get(0).ast;
		AST_Stmt ast_t7=new AST_Stmt();
		ast_t7.setASTType(Stmt_Whl.class.getName());
		ast_t7.setStmt(ast0);
		ast=ast_t7;
		break;		
//8	crtStmtSg 0
	case "crtStmtSg":
		ast0=symbs.get(1).ast;
		AST_Stmt ast_t8=new AST_Stmt();
		ast_t8.setASTType(Stmt_Sg.class.getName());
		ast_t8.setStmt(ast0);
		ast=ast_t8;
		break;	
//9	crtSgVarAssign 0
	case "crtSgVarAssign":
		ast0=symbs.get(0).ast;
		Stmt_Sg ast_t9=new Stmt_Sg();
		ast_t9.setASTType(SgStmt_AsgnVar.class.getName());
		ast_t9.setSg(ast0);
		ast=ast_t9;
		break;
//10	crtSgVarDef 0
	case "crtSgVarDef":
		ast0=symbs.get(0).ast;
		Stmt_Sg ast_t10=new Stmt_Sg();
		ast_t10.setASTType(SgStmt_DefVar.class.getName());
		ast_t10.setSg(ast0);
		ast=ast_t10;
		break;
//11	crtSgCalc 0
	case "crtSgCalc":
		ast0=symbs.get(0).ast;
		Stmt_Sg ast_t11=new Stmt_Sg();
		ast_t11.setASTType(Expr_Calc.class.getName());
		ast_t11.setSg(ast0);
		ast=ast_t11;
		break;
//12	crtSgCtrF 0
	case "crtSgCtrF":		
		ast0=symbs.get(0).ast;
		Stmt_Sg ast_t12=new Stmt_Sg();
		ast_t12.setASTType(SgStmt_CtrFlw.class.getName());
		ast_t12.setSg(ast0);
		ast=ast_t12;
		break;
//13	crtCFRetE 0
	case "crtCFRetE":
		ast0=symbs.get(0).ast;
		SgStmt_CtrFlw ast_t13=new SgStmt_CtrFlw();
		ast_t13.setRtExp((Expr)ast0);
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
		ast_t17.setVar((ExprPri_Var)ast0);
		ast=ast_t17;
		break;
//		18	linkVarDefAsg 4 2 0
	case "linkVarDefAsg":
		ast0=symbs.get(0).ast;
		ast2=symbs.get(2).ast;
		ast4=symbs.get(4).ast;
		SgStmt_DefVar ast_t18=new SgStmt_DefVar(); 
		ast_t18.setPredef((SgStmt_DefVar)ast4);
		ast_t18.setVar((ExprPri_Var)ast2);		
		ast_t18.setExpr((Expr)ast0);
		ast=ast_t18;
		break;
//		19	crtVarDef 1 0
	case "crtVarDef":
		ast0=symbs.get(0).ast;
		ast1=symbs.get(1).ast;
		SgStmt_DefVar ast_t19=new SgStmt_DefVar();
		ast_t19.setTypeExp((TypeExp)ast1);
		ast_t19.setVar((ExprPri_Var)ast0);
		ast=ast_t19;
		break;
//		20	crtVarDefAsg 3 2 0
	case "crtVarDefAsg":
		ast0=symbs.get(0).ast;
		ast2=symbs.get(2).ast;
		ast3=symbs.get(3).ast;
		SgStmt_DefVar ast_t20=new SgStmt_DefVar(); 
		ast_t20.setTypeExp((TypeExp)ast3);
		ast_t20.setVar((ExprPri_Var)ast2);		
		ast_t20.setExpr((Expr)ast0);
		ast=ast_t20;
		break;
//		21	crtVarAsg 2 0
	case "crtVarAsg":
		ast0=symbs.get(0).ast;
		ast2=symbs.get(2).ast;
		SgStmt_AsgnVar ast_t21=new SgStmt_AsgnVar();
		ast_t21.setLeft((Expr_Left)ast2);
		ast_t21.setExpr((Expr)ast0);
		ast=ast_t21;
		break;
//		22	crtLftFld 0
	case "crtLftFld":
		ast0=symbs.get(0).ast;
		Expr_Left ast_t22=new Expr_Left();
		ast_t22.setASTType(ExprAccs_Fld.class.getName());
		ast_t22.setAccs(ast0);
		ast=ast_t22;
		break;
//		23	crtLftArr 0
	case "crtLftArr":
		ast0=symbs.get(0).ast;
		Expr_Left ast_t23=new Expr_Left();
		ast_t23.setASTType(ExprAccs_Arr.class.getName());
		ast_t23.setAccs(ast0);
		ast=ast_t23;
		break;
//		24	crtTpBsc 0
	case "crtTpBsc":
		ast0=symbs.get(0).ast;
		TypeExp ast_t24=new TypeExp();
		ast_t24.setASTType(TypeExp_Bsc.class.getName());
		ast_t24.setTypeExp(ast0);
		ast=ast_t24;
		break;
//		25	crtTpArr 0
	case "crtTpArr":
		ast0=symbs.get(0).ast;
		TypeExp ast_t25=new TypeExp();
		ast_t25.setASTType(TypeExp_Arr.class.getName());
		ast_t25.setTypeExp(ast0);
		ast=ast_t25;
		break;
//		26	crtTpIdn 0
	case "crtTpIdn":
		ast0=symbs.get(0).ast;
		TypeExp ast_t26=new TypeExp();
		ast_t26.setASTType(TypeExp_Idn.class.getName());
		ast_t26.setTypeExp(ast0);
		ast=ast_t26;
		break;
//		27	crtTpGnrcTp 0
	case "crtTpGnrcTp":
		ast0=symbs.get(0).ast;
		TypeExp ast_t27=new TypeExp();
		ast_t27.setASTType(TypeExp_Gnrc.class.getName());
		ast_t27.setTypeExp(ast0);
		ast=ast_t27;
		break;
//		28	crtTpFunc 0
	case "crtTpFunc":
		TypeExp ast_t28=new TypeExp();
		ast_t28.setASTType(TypeExp_Func.class.getName());
		ast_t28.setTypeExp(new TypeExp_Func());
		ast=ast_t28;
		break;
//		29	crtBscTpInt 0
	case "crtBscTpInt":
		TypeExp_Bsc ast_t29 = new TypeExp_Bsc();
		ast_t29.setTypeT("int");
		ast=ast_t29;
		break;
//		30	crtBscTpDb 0
	case "crtBscTpDb":
		TypeExp_Bsc ast_t30 = new TypeExp_Bsc();
		ast_t30.setTypeT("double");
		ast=ast_t30;
		break;
//		31	crtBscTpBl 0
	case "crtBscTpBl":
		TypeExp_Bsc ast_t31 = new TypeExp_Bsc();
		ast_t31.setTypeT("bool");
		ast=ast_t31;
		break;
//		32	crtBscTpStr 0
	case "crtBscTpStr":
		TypeExp_Bsc ast_t32 = new TypeExp_Bsc();
		ast_t32.setTypeT("string");
		ast=ast_t32;
		break;
//		33	crtBscTpChr 0
	case "crtBscTpChr":
		TypeExp_Bsc ast_t33 = new TypeExp_Bsc();
		ast_t33.setTypeT("char");
		ast=ast_t33;
		break;
//		34	lnkArrTp 2
	case "lnkArrTp":
		ast2=symbs.get(2).ast;
		TypeExp_Arr ast_t34 = new TypeExp_Arr();
		ast_t34.setPreType((TypeExp)ast2);
		ast=ast_t34;
		break;
//		35	lnkIdnTpVar 2 0
	case "lnkIdnTpVar":
		ast0=symbs.get(0).ast;
		ast2=symbs.get(2).ast;
		TypeExp_Idn ast_t35 = new TypeExp_Idn();
		ast_t35.setTypeIdn((TypeExp_Idn)ast2, (ExprPri_Var)ast0);
		ast=ast_t35;
		break;
//		36	crtIdnTpVar 0
	case "crtIdnTpVar":
		ast0=symbs.get(0).ast;
		TypeExp_Idn ast_t36 = new TypeExp_Idn();
		ast_t36.setTypeIdn(null, (ExprPri_Var)ast0);
		ast=ast_t36;
		break;
//		37	crtGnrcTp 3 1
	case "crtGnrcTp":
		ast1=symbs.get(1).ast;
		ast3=symbs.get(3).ast;
		TypeExp_Gnrc ast_t37=new TypeExp_Gnrc();
		ast_t37.setGnrcType((TypeExp_Idn)ast3, (Gnrc_ArgLst)ast1);
		ast=ast_t37;
		break;
//		38	lnkGnrcArgsVar 2 0
	case "lnkGnrcArgsVar":
		ast0=symbs.get(0).ast;
		ast2=symbs.get(2).ast;
		Gnrc_ArgLst ast_t38 = new Gnrc_ArgLst();
		ast_t38.setGnrcArgs((Gnrc_ArgLst)ast2, (TypeExp)ast0, null);
		ast=ast_t38;
		break;		
//		39	lnkGnrcArgsExt 4 0
	case "lnkGnrcArgsExt":
		ast0=symbs.get(0).ast;
		ast4=symbs.get(4).ast;
		Gnrc_ArgLst ast_t39 = new Gnrc_ArgLst();
		ast_t39.setGnrcArgs((Gnrc_ArgLst)ast4, null, (TypeExp_Idn)ast0);
		ast=ast_t39;
		break;		
//		40	crtGnrcArgsVar 0
	case "crtGnrcArgsVar":
		ast0=symbs.get(0).ast;
		Gnrc_ArgLst ast_t40= new Gnrc_ArgLst();
		ast_t40.setGnrcArgs(null, (TypeExp)ast0, null);
		ast=ast_t40;
		break;
//		41	crtGnrcArgsExt 0
	case "crtGnrcArgsExt":
		ast0=symbs.get(0).ast;
		Gnrc_ArgLst ast_t41= new Gnrc_ArgLst();
		ast_t41.setGnrcArgs(null, null, (TypeExp_Idn)ast0);
		ast=ast_t41;
		break;
//		42	crtClassDef 8 6 5 4 3 1
	case "crtClassDef":
		ast8=symbs.get(8).ast;
		ast6=symbs.get(6).ast;
		ast5=symbs.get(5).ast;
		ast4=symbs.get(4).ast;
		ast3=symbs.get(3).ast;
		ast1=symbs.get(1).ast;
		Stmt_DefCls ast_t42 = new Stmt_DefCls();
		ast_t42.setClsDef((Scp_InfoLst)ast8,(ExprPri_Var) ast6,(Gnrc_ParLst)ast5,
				(Cls_Extd_Lst)ast4,(Cls_Impl_Lst)ast3,(MbrDef_Lst)ast1);
		ast=ast_t42;
		break;
//		43	crtIntfDef 7 5 4 3 1
	case "crtIntfDef":
		ast7=symbs.get(7).ast;
		ast5=symbs.get(5).ast;
		ast4=symbs.get(4).ast;
		ast3=symbs.get(3).ast;
		ast1=symbs.get(1).ast;
		Stmt_DefIntf ast_t43= new Stmt_DefIntf();
		ast_t43.setIntfDef((Scp_InfoLst)ast7,(ExprPri_Var)ast5,(Gnrc_ParLst)ast4,
				(Cls_Extd_Lst)ast3,(MbrDef_Lst)ast1);
		ast=ast_t43;
		break;
//		44	crtScpInfLst 0
	case "crtScpInfLst":
		ast0=symbs.get(0).ast;
		ast=ast0;
		break;
//		45	crtScpInfLstE 0
	case "crtScpInfLstE":
		Scp_InfoLst ast_t45=new Scp_InfoLst();
		ast_t45.setE();
		ast=ast_t45;
		break;
//		46	lnkScpInf 1 0
	case "lnkScpInf":
		ast0=symbs.get(0).ast;
		ast1=symbs.get(1).ast;
		Scp_InfoLst ast_t46=(Scp_InfoLst)ast1;
		ast_t46.addScp((Scp_Info)ast0);
		ast=ast_t46;
		break;
//		47	crtScpInf 0
	case "crtScpInf":
		ast0 = symbs.get(0).ast;
		Scp_InfoLst ast_t47= new Scp_InfoLst();
		ast_t47.addScp((Scp_Info)ast0);
		ast=ast_t47;
		break;
//		48	crtScpStc 0
	case "crtScpStc":
		Scp_Info ast_t48 = new Scp_Info();
		ast_t48.setScp(Scp_Info.en_Scp.t_static);
		ast=ast_t48;
		break;
//		49	crtScpPbl 0
	case "crtScpPbl":
		Scp_Info ast_t49 = new Scp_Info();
		ast_t49.setScp(Scp_Info.en_Scp.t_public);
		ast=ast_t49;
		break;
//		50	crtScpPrv 0
	case "crtScpPrv":
		Scp_Info ast_t50 = new Scp_Info();
		ast_t50.setScp(Scp_Info.en_Scp.t_private);
		ast=ast_t50;
		break;		
//		51	crtScpFnl 0
	case "crtScpFnl":
		Scp_Info ast_t51 = new Scp_Info();
		ast_t51.setScp(Scp_Info.en_Scp.t_final);
		ast=ast_t51;
		break;
//		52	crtGnrcParLst 1
	case "crtGnrcParLst":
		ast1=symbs.get(1).ast;
		ast=ast1;
		break;
//		53	crtGnrcParLstE 0
	case "crtGnrcParLstE":
		Gnrc_ParLst ast_t53= new Gnrc_ParLst();
		ast_t53.setE();
		ast=ast_t53;
		break;
//		54	lnkGnrcPar 2 0
	case "lnkGnrcPar":
		ast0=symbs.get(0).ast;
		ast2=symbs.get(2).ast;
		Gnrc_ParLst ast_t54=(Gnrc_ParLst)ast2;
		ast_t54.addPar(new Gnrc_Par((ExprPri_Var)ast0));
		ast=ast_t54;
		break;
//		55	lnkGnrcParExt 4 2 0
	case "lnkGnrcParExt":
		ast0=symbs.get(0).ast;
		ast2=symbs.get(2).ast;
		ast4=symbs.get(4).ast;
		Gnrc_ParLst ast_t55=(Gnrc_ParLst)ast4;
		ast_t55.addPar(new Gnrc_Par((ExprPri_Var)ast2,(TypeExp_Idn)ast0));
		ast=ast_t55;
		break;
//		56	crtGnrcParVar 0
	case "crtGnrcParVar":
		ast0=symbs.get(0).ast;
		Gnrc_ParLst ast_t56=new Gnrc_ParLst();
		ast_t56.addPar(new Gnrc_Par((ExprPri_Var)ast0));
		ast=ast_t56;
		break;
//		57	crtGnrcParExt 2 0
	case "crtGnrcParExt":
		ast0=symbs.get(0).ast;
		ast2=symbs.get(2).ast;
		Gnrc_ParLst ast_t57=new Gnrc_ParLst();
		ast_t57.addPar(new Gnrc_Par((ExprPri_Var)ast0,(TypeExp_Idn)ast2));
		ast=ast_t57;
		break;
//		58	crtExtLst 0
	case"crtExtLst":
		ast0=symbs.get(0).ast;
		ast=ast0;
		break;
//		59	crtExtLstE 0
	case "crtExtLstE":
		Cls_Extd_Lst ast_t59=new Cls_Extd_Lst();
		ast_t59.setE();
		ast=ast_t59;
		break;
//		60	lnkExtIdn 2 0
	case "lnkExtIdn":
		ast0=symbs.get(0).ast;
		ast2=symbs.get(2).ast;
		Cls_Extd_Lst ast_t60=(Cls_Extd_Lst)ast2;
		ast_t60.addExtd((TypeExp_Idn)ast0);
		ast=ast_t60;
		break;
//		61	crtExtIdn 0
	case "crtExtIdn":
		ast0=symbs.get(0).ast;
		Cls_Extd_Lst ast_t61=new Cls_Extd_Lst();
		ast_t61.addExtd((TypeExp_Idn)ast0);
		ast=ast_t61;
		break;
//		62	crtImpLst 0
	case "crtImpLst":
		ast0=symbs.get(0).ast;
		ast=ast0;
		break;
//		63	crtImpLstE 0
	case "crtImpLstE":
		Cls_Impl_Lst ast_t63=new Cls_Impl_Lst();
		ast=ast_t63;
		break;
//		64	lnkImpIdn 2 0
	case "lnkImpIdn":
		ast0=symbs.get(0).ast;
		ast2=symbs.get(2).ast;
		Cls_Impl_Lst ast_t64=(Cls_Impl_Lst)ast2;
		ast_t64.addImp((TypeExp_Idn)ast0);
		ast=ast_t64;
		break;
//		65	crtImpIdn 0
	case "crtImpIdn":
		ast0=symbs.get(0).ast;
		Cls_Impl_Lst ast_t65=new Cls_Impl_Lst();
		ast_t65.addImp((TypeExp_Idn)ast0);
		ast=ast_t65;
		break;
//		66	crtMmbDef 0
	case "crtMmbDef":
		ast0=symbs.get(0).ast;
		ast=ast0;
		break;
//		67	crtMmbDefE 0
	case "crtMmbDefE":
		MbrDef_Lst ast_t67=new MbrDef_Lst();
		ast_t67.setE();
		ast=ast_t67;
		break;
//		68	lnkMmbFld 1 0
	case "lnkMmbFld":
		ast0=symbs.get(0).ast;
		ast1=symbs.get(1).ast;
		MbrDef_Lst ast_t68=(MbrDef_Lst)ast1;
		ast_t68.addMbr(new MbrDef((MbrDef_Fld)ast0));
		ast=ast_t68;
		break;
//		69	lnkMmbMthd 1 0
	case "lnkMmbMthd":
		ast0=symbs.get(0).ast;
		ast1=symbs.get(1).ast;
		MbrDef_Lst ast_t69=(MbrDef_Lst)ast1;
		ast_t69.addMbr(new MbrDef((MbrDef_Mthd)ast0));
		ast=ast_t69;
		break;
//		70	crtMmbFld 0
	case "crtMmbFld":
		ast0=symbs.get(0).ast;
		MbrDef_Lst ast_t70=new MbrDef_Lst();
		MbrDef ast_t70_1=new MbrDef();
		ast_t70_1.setASTType(MbrDef_Fld.class.getName());
		ast_t70_1.setMbr(ast0);
		ast_t70.addMbr(ast_t70_1);
		ast=ast_t70;
		break;
//		71	crtMemMthd 0
	case "crtMemMthd":
		ast0=symbs.get(0).ast;
		MbrDef_Lst ast_t71=new MbrDef_Lst();
		MbrDef ast_t71_1=new MbrDef();
		ast_t71_1.setASTType(MbrDef_Mthd.class.getName());
		ast_t71_1.setMbr(ast0);
		ast_t71.addMbr(ast_t71_1);
		ast=ast_t71;
		break;
//		72	crtFldDef 2 1
	case "crtFldDef":
		ast2=symbs.get(2).ast;
		ast1=symbs.get(1).ast;
		MbrDef_Fld ast_t72=new MbrDef_Fld();
		ast_t72.setFld((Scp_InfoLst)ast2, (SgStmt_DefVar) ast1);
		ast=ast_t72;
		break;
//		73	crtMthdDef 1 0
	case "crtMthdDef":
		ast0=symbs.get(0).ast;
		ast1=symbs.get(1).ast;
		MbrDef_Mthd ast_t73=new MbrDef_Mthd();
		ast_t73.setMthd((Scp_InfoLst)ast1, (Stmt_DefFunc) ast0);
		ast=ast_t73;
		break;
//		74	crtFuncDef 7 6 4 1
	case "crtFuncDef":
		ast1=symbs.get(1).ast;
		ast4=symbs.get(4).ast;
		ast6=symbs.get(6).ast;
		ast7=symbs.get(7).ast;
		ast8=symbs.get(8).ast;
		Stmt_DefFunc ast_t74=new Stmt_DefFunc();
		ast_t74.setFuncDef((Gnrc_ParLst) ast8, (TypeExp) ast7, (ExprPri_Var) ast6, (FuncDef_ParLst) ast4, (AST_StmtList) ast1);
		ast=ast_t74;
		break;
//		75	crtParLst 0
	case "crtParLst":
		ast0=symbs.get(0).ast;
		ast=ast0;
		break;
//		76	crtParLstE 0
	case "crtParLstE":
		FuncDef_ParLst ast_t76=new FuncDef_ParLst();
		ast_t76.setE();
		ast=ast_t76;
		break;
//		77	lnkParVar 3 1 0
	case "lnkParVar":
		ast0=symbs.get(0).ast;
		ast1=symbs.get(1).ast;
		ast3=symbs.get(3).ast;
		FuncDef_ParLst ast_t77=(FuncDef_ParLst)ast3;
		ast_t77.addPar(new FuncDef_Par((TypeExp) ast1,(ExprPri_Var) ast0));
		ast=ast_t77;
		break;
//		78	crtParVar 1 0
	case "crtParVar":
		ast0=symbs.get(0).ast;
		ast1=symbs.get(1).ast;
		FuncDef_ParLst ast_t78 =new FuncDef_ParLst();
		ast_t78.addPar(new FuncDef_Par((TypeExp) ast1,(ExprPri_Var) ast0));
		ast=ast_t78;
		break;
//		79	crtIfIf 0
	case "crtIfIf":
		ast0=symbs.get(0).ast;
		Stmt_If ast_t79=new Stmt_If();
		ast_t79.setIfStmt((StmtIf_IfBd)ast0, null);
		ast=ast_t79;
		break;
//		80	crtIfIfEls 2 0
	case "crtIfIfEls":
		ast0=symbs.get(0).ast;
		ast2=symbs.get(2).ast;
		Stmt_If ast_t80=new Stmt_If();
		ast_t80.setIfStmt((StmtIf_IfBd)ast2, (StmtIf_ElsBd)ast0);
		ast=ast_t80;
		break;
//		81	crtIfLst 4 1
	case "crtIfLst":
		ast1=symbs.get(1).ast;
		ast4=symbs.get(4).ast;
		StmtIf_IfBd ast_t81=new StmtIf_IfBd();
		ast_t81.setIfBd((ExprCalc_Bool)ast4, (AST_StmtList)ast1, null);
		ast=ast_t81;
		break;
//		82	crtIfSg 3 1
	case "crtIfSg":
		ast1=symbs.get(1).ast;
		ast3=symbs.get(3).ast;
		StmtIf_IfBd ast_t82=new StmtIf_IfBd();
		ast_t82.setIfBd((ExprCalc_Bool)ast3, null, (Stmt_Sg)ast1);
		ast=ast_t82;
		break;
//		83	crtElsIf 0
	case "crtElsIf":
		ast0=symbs.get(0).ast;
		StmtIf_ElsBd ast_t83=new StmtIf_ElsBd();
		ast_t83.setElsBd((Stmt_If)ast0, null, null);
		ast=ast_t83;
		break;		
//		84	crtElsLst 1
	case "crtElsLst":
		ast1=symbs.get(1).ast;
		StmtIf_ElsBd ast_t84=new StmtIf_ElsBd();
		ast_t84.setElsBd(null, (AST_StmtList)ast1, null);
		ast=ast_t84;
		break;
//		85	crtElsSg 1
	case "crtElsSg":
		ast1=symbs.get(1).ast;
		StmtIf_ElsBd ast_t85=new StmtIf_ElsBd();
		ast_t85.setElsBd(null, null, (Stmt_Sg)ast1);
		ast=ast_t85;
		break;
//		86	crtWhl 4 1
	case "crtWhl":
		ast4=symbs.get(4).ast;
		ast1=symbs.get(1).ast;
		Stmt_Whl ast_t86=new Stmt_Whl();
		ast_t86.setwhl((ExprCalc_Bool)ast4, (AST_StmtList)ast1);
		ast=ast_t86;
		break;
//		87	crtExpLmd 0
	case "crtExpLmd":
		ast0=symbs.get(0).ast;
		Expr ast_t87=new Expr();
		ast_t87.setASTType(Expr_Lmbd.class.getName());
		ast_t87.setExpr(ast0);
		ast=ast_t87;
		break;
//		88	crtExpCalc 0
	case "crtExpCalc":
		ast0=symbs.get(0).ast;
		Expr ast_t88=new Expr();
		ast_t88.setASTType(Expr_Calc.class.getName());
		ast_t88.setExpr(ast0);
		ast=ast_t88;
		break;
//		89	crtCalcCnd 0
	case "crtCalcCnd":
		ast0=symbs.get(0).ast;
		Expr_Calc ast_t89=new Expr_Calc();
		ast_t89.setASTType(ExprCalc_Cond.class.getName());
		ast_t89.setCalc((ExprCalc_Cond)ast0);
		ast=ast_t89;
		break;
//		90	crtCalcNewCls 0
	case "crtCalcNewCls":
		ast0=symbs.get(0).ast;
		Expr_Calc ast_t90=new Expr_Calc();
		ast_t90.setASTType(ExprCalc_NewCls.class.getName());
		ast_t90.setCalc((ExprCalc_NewCls)ast0);
		ast=ast_t90;
		break;
//		91	crtCalcNewArr 0
	case "crtCalcNewArr":
		ast0=symbs.get(0).ast;
		Expr_Calc ast_t91=new Expr_Calc();
		ast_t91.setASTType(ExprCalc_NewArr.class.getName());
		ast_t91.setCalc((ExprCalc_NewArr)ast0);
		ast=ast_t91;
		break;
//		92	crtNewClsIdn 3 1
	case "crtNewClsIdn":
		ast1=symbs.get(1).ast;
		ast3=symbs.get(3).ast;
		ExprCalc_NewCls ast_t92=new ExprCalc_NewCls();
		ast_t92.setNewCls((TypeExp_Idn)ast3, null, (FuncApp_ArgLst)ast1);
		ast=ast_t92;
		break;
//		93	crtNewClsGnrc 3 1
	case "crtNewClsGnrc":
		ast1=symbs.get(1).ast;
		ast3=symbs.get(3).ast;
		ExprCalc_NewCls ast_t93=new ExprCalc_NewCls();
		ast_t93.setNewCls(null, (TypeExp_Gnrc)ast3, (FuncApp_ArgLst)ast1);
		ast=ast_t93;
		break;
//		94	crtNewArrTp 1 0
	case "crtNewArrTp":
		ast0=symbs.get(0).ast;
		ast1=symbs.get(1).ast;
		ExprCalc_NewArr ast_t94=new ExprCalc_NewArr();
		ast_t94.setNewArr((TypeExp)ast1, (NewArr_DimLst)ast0, null);
		ast=ast_t94;
		break;
//		95	crtNewArrLst 1
	case "crtNewArrLst":
		ast1=symbs.get(1).ast;
		ExprCalc_NewArr ast_t95=new ExprCalc_NewArr();
		ast_t95.setNewArr(null, null, (NewArr_InitLst)ast1);
		ast=ast_t95;
		break;
//		96	lnkArrLst 2 0
	case "lnkArrLst":
		ast0=symbs.get(0).ast;
		ast2=symbs.get(2).ast;
		NewArr_InitLst ast_t96=(NewArr_InitLst)ast2;
		ast_t96.addInit((NewArr_Init)ast0);
		ast=ast_t96;
		break;
//		97	crtArrLst 0
	case "crtArrLst":
		ast0=symbs.get(0).ast;
		NewArr_InitLst ast_t97=new NewArr_InitLst();
		ast_t97.addInit((NewArr_Init)ast0);
		ast=ast_t97;
		break;
//		98	crtArrCalc 0
	case "crtArrCalc":
		ast0=symbs.get(0).ast;
		NewArr_Init ast_t98=new NewArr_Init();
		ast_t98.setCalc((Expr_Calc)ast0);
		ast=ast_t98;
		break;
//		99	crtArrLstArr 1
	case "crtArrLstArr":
		ast0=symbs.get(0).ast;
		NewArr_Init ast_t99=new NewArr_Init();
		ast_t99.setLst((NewArr_InitLst)ast0);
		ast=ast_t99;
		break;
//		100	lnkDimLst 3 1
	case "lnkDimLst":
		ast1=symbs.get(1).ast;
		ast3=symbs.get(3).ast;
		NewArr_DimLst ast_t100=(NewArr_DimLst)ast3;
		ast_t100.addDim((ExprCalc_Add)ast1);
		ast=ast_t100;
		break;
//		101	crtDimLst 1
	case "crtDimLst":
		ast1=symbs.get(1).ast;
		NewArr_DimLst ast_t101=new NewArr_DimLst();
		ast_t101.addDim((ExprCalc_Add)ast1);
		ast=ast_t101;
		break;
//		102	crtCndBl 0
	case "crtCndBl":
		ast0=symbs.get(0).ast;
		ExprCalc_Cond ast_t102=new ExprCalc_Cond();
		ast_t102.setBoolExp((ExprCalc_Bool)ast0);
		ast=ast_t102;
		break;
//		103	lnkBlOr 2 0
	case "lnkBlOr":
		ast0=symbs.get(0).ast;
		ast2=symbs.get(2).ast;
		ExprCalc_Bool ast_t103=new ExprCalc_Bool();
		ast_t103.setBiBool((ExprCalc_Bool)ast2, "||", (ExprCalc_Bool)ast0);
		ast=ast_t103;
		break;
//		104	crtBlOr 0
	case "crtBlOr":
		ast0=symbs.get(0).ast;
		ast=ast0;
		break;
//		105	lnkOrAnd 2 0
	case "lnkOrAnd":
		ast0=symbs.get(0).ast;
		ast2=symbs.get(2).ast;
		ExprCalc_Bool ast_t105=new ExprCalc_Bool();
		ast_t105.setBiBool((ExprCalc_Bool)ast2, "&&", (ExprCalc_Bool)ast0);
		ast=ast_t105;
		break;
//		106	crtOrAnd 0
	case "crtOrAnd":
		ast0=symbs.get(0).ast;
		ast=ast0;
		break;
//		107	crtAndCmp 0
	case "crtAndCmp":
		ast0=symbs.get(0).ast;
		ast=ast0;
		break;
//		108	lnkCmpGT 2 0
	case "lnkCmpGT":
		ast0=symbs.get(0).ast;
		ast2=symbs.get(2).ast;
		ExprCalc_Bool ast_t108=new ExprCalc_Bool();
		ast_t108.setBiCmp((ExprCalc_Add)ast2, ">", (ExprCalc_Add)ast0);
		ast=ast_t108;
		break;
//		109	lnkCmpGE 2 0
	case "lnkCmpGE":
		ast0=symbs.get(0).ast;
		ast2=symbs.get(2).ast;
		ExprCalc_Bool ast_t109=new ExprCalc_Bool();
		ast_t109.setBiCmp((ExprCalc_Add)ast2, ">=", (ExprCalc_Add)ast0);
		ast=ast_t109;
		break;	
//		110	lnkCmpLT 2 0
	case "lnkCmpLT":
		ast0=symbs.get(0).ast;
		ast2=symbs.get(2).ast;
		ExprCalc_Bool ast_t110=new ExprCalc_Bool();
		ast_t110.setBiCmp((ExprCalc_Add)ast2, "<", (ExprCalc_Add)ast0);
		ast=ast_t110;
		break;		
//		111	lnkCmpLE 2 0
	case "lnkCmpLE":
		ast0=symbs.get(0).ast;
		ast2=symbs.get(2).ast;
		ExprCalc_Bool ast_t111=new ExprCalc_Bool();
		ast_t111.setBiCmp((ExprCalc_Add)ast2, "<=", (ExprCalc_Add)ast0);
		ast=ast_t111;
		break;
//		112	lnkCmpEQ 2 0
	case "lnkCmpEQ":
		ast0=symbs.get(0).ast;
		ast2=symbs.get(2).ast;
		ExprCalc_Bool ast_t112=new ExprCalc_Bool();
		ast_t112.setBiCmp((ExprCalc_Add)ast2, "==", (ExprCalc_Add)ast0);
		ast=ast_t112;
		break;
//		113	lnkCmpNE 2 0
	case "lnkCmpNE":
		ast0=symbs.get(0).ast;
		ast2=symbs.get(2).ast;
		ExprCalc_Bool ast_t113=new ExprCalc_Bool();
		ast_t113.setBiCmp((ExprCalc_Add)ast2, "!=", (ExprCalc_Add)ast0);
		ast=ast_t113;
		break;
//		114	crtCmpNAdd 0
	case "crtCmpNAdd":
		ast0=symbs.get(0).ast;
		ExprCalc_Bool ast_t114=new ExprCalc_Bool();
		ast_t114.setUnAdd((ExprCalc_Add)ast0,"!");
		ast=ast_t114;
		break;
//		115	crtCmpAdd 0
	case "crtCmpAdd":
		ast0=symbs.get(0).ast;
		ExprCalc_Bool ast_t115=new ExprCalc_Bool();
		ast_t115.setAdd((ExprCalc_Add)ast0);
		ast=ast_t115;
		break;
//		116	crtCmpTr 0
	case "crtCmpTr":
		ExprCalc_Bool ast_t116=new ExprCalc_Bool();
		ast_t116.setTrue();
		ast=ast_t116;
		break;
//		117	crtCmpFs 0
	case "crtCmpFs":
		ExprCalc_Bool ast_t117=new ExprCalc_Bool();
		ast_t117.setFalse();
		ast=ast_t117;
		break;
//		118	lnkAddAdd 2 0
	case "lnkAddAdd":
		ast0=symbs.get(0).ast;
		ast2=symbs.get(2).ast;
		ExprCalc_Add ast_t118=new ExprCalc_Add();
		ast_t118.setBiAdd((ExprCalc_Add)ast2, "+", (ExprCalc_Add)ast0);
		ast=ast_t118;
		break;
//		119	lnkAddMns 2 0
	case "lnkAddMns":
		ast0=symbs.get(0).ast;
		ast2=symbs.get(2).ast;
		ExprCalc_Add ast_t119=new ExprCalc_Add();
		ast_t119.setBiAdd((ExprCalc_Add)ast2, "-", (ExprCalc_Add)ast0);
		ast=ast_t119;
		break;
//		120	crtAddPls 0
	case "crtAddPls":
		ast0=symbs.get(0).ast;
		ExprCalc_Add ast_t120=new ExprCalc_Add();
		ast_t120.setUnAdd((ExprCalc_Add)ast0, "+");
		ast=ast_t120;
		break;
//		121	crtAddMns 0
	case "crtAddMns":
		ast0=symbs.get(0).ast;
		ExprCalc_Add ast_t121=new ExprCalc_Add();
		ast_t121.setUnAdd((ExprCalc_Add)ast0, "-");
		ast=ast_t121;
		break;
//		122	crtAddMul 0
	case "crtAddMul":
		ast0=symbs.get(0).ast;
		ast=ast0;
		break;
//		123	lnkMulMul 2 0
	case "lnkMulMul":
		ast0=symbs.get(0).ast;
		ast2=symbs.get(2).ast;
		ExprCalc_Add ast_t123=new ExprCalc_Add();
		ast_t123.setBiMul((ExprCalc_Add) ast2, "*", (ExprAccs)ast0);
		ast=ast_t123;
		break;
//		124	lnkMulSub 2 0
	case "lnkMulSub":
		ast0=symbs.get(0).ast;
		ast2=symbs.get(2).ast;
		ExprCalc_Add ast_t124=new ExprCalc_Add();
		ast_t124.setBiMul((ExprCalc_Add) ast2, "/", (ExprAccs)ast0);
		ast=ast_t124;
		break;
//		125	crtMulUnr 0
	case "crtMulUnr":
		ast0=symbs.get(0).ast;
		ExprCalc_Add ast_t125=new ExprCalc_Add();
		ast_t125.setUnary((ExprCalc_Unary) ast0);
		ast=ast_t125;
		break;
//		126	crtUnrInc 0
	case "crtUnrInc":
		ast0=symbs.get(0).ast;
		ExprCalc_Unary ast_t126=new ExprCalc_Unary();
		ast_t126.setUnary((ExprAccs) ast0, "++");
		ast=ast_t126;
		break;
//		127	crtUnrDec 0
	case "crtUnrDec":
		ast0=symbs.get(0).ast;
		ExprCalc_Unary ast_t127=new ExprCalc_Unary();
		ast_t127.setUnary((ExprAccs) ast0, "--");
		ast=ast_t127;
		break;
//		128	crtUnrIncP 0
	case "crtUnrIncP":
		ast0=symbs.get(1).ast;
		ExprCalc_Unary ast_t128=new ExprCalc_Unary();
		ast_t128.setUnary((ExprAccs) ast0, "++p");
		ast=ast_t128;
		break;
//		129	crtUnrDecP 0
	case "crtUnrDecP":
		ast0=symbs.get(1).ast;
		ExprCalc_Unary ast_t129=new ExprCalc_Unary();
		ast_t129.setUnary((ExprAccs) ast0, "--p");
		ast=ast_t129;
		break;
//		130	crtUnrAcc 0
	case "crtUnrAcc":
		ast0=symbs.get(0).ast;
		ExprCalc_Unary ast_t130=new ExprCalc_Unary();
		ast_t130.setUnary((ExprAccs) ast0, null);
		ast=ast_t130;
		break;
//		131	crtUnrCst 0
	case "crtUnrCst":
		ast0=symbs.get(0).ast;
		ExprCalc_Unary ast_t131=new ExprCalc_Unary();
		ast_t131.setCast((ExprUnr_Cast) ast0);
		ast=ast_t131;
		break;
//		132	crtCst 2 0
	case "crtCst":
		ast0=symbs.get(0).ast;
		ast2=symbs.get(2).ast;
		ExprUnr_Cast ast_t132=new ExprUnr_Cast();
		ast_t132.setCast((TypeExp) ast2, (ExprAccs)ast0);
		ast=ast_t132;
		break;
//		133	crtAccFld 0
	case "crtAccFld":
		ast0=symbs.get(0).ast;
		ExprAccs ast_t133=new ExprAccs();
		ast_t133.setASTType(ExprAccs_Fld.class.getName());
		ast_t133.setAccs(ast0);
		ast=ast_t133;
		break;
//		134	crtAccArr 0
	case "crtAccArr":
		ast0=symbs.get(0).ast;
		ExprAccs ast_t134=new ExprAccs();
		ast_t134.setASTType(ExprAccs_Arr.class.getName());
		ast_t134.setAccs(ast0);
		ast=ast_t134;
		break;
//		135	crtAccApp 0
	case "crtAccApp":
		ast0=symbs.get(0).ast;
		ExprAccs ast_t135=new ExprAccs();
		ast_t135.setASTType(ExprAccs_App.class.getName());
		ast_t135.setAccs(ast0);
		ast=ast_t135;
		break;
//		136	crtAccPri 0
	case "crtAccPri":
		ast0=symbs.get(0).ast;
		ExprAccs ast_t136=new ExprAccs();
		ast_t136.setASTType(ExprAccs_Pri.class.getName());
		ast_t136.setAccs(ast0);
		ast=ast_t136;
		break;
//		137	lnkFldAccVar 2 0 
	case "lnkFldAccVar":
		ast0=symbs.get(0).ast;
		ast2=symbs.get(2).ast;
		ExprAccs_Fld ast_t137=new ExprAccs_Fld();
		ast_t137.setAccs((ExprAccs_Fld)ast2, (ExprPri_Var)ast0, null);
		ast=ast_t137;
		break;
//		138	lnkFldAccCls 2 
	case "lnkFldAccCls":
		ast2=symbs.get(2).ast;
		ExprAccs_Fld ast_t138=new ExprAccs_Fld();
		ast_t138.setAccs((ExprAccs_Fld)ast2, null, "class");
		ast=ast_t138;
		break;
//		139	crtFldAccVar 0
	case "crtFldAccVar":
		ast0=symbs.get(0).ast;
		ExprAccs_Fld ast_t139=new ExprAccs_Fld();
		ast_t139.setAccs(null, (ExprPri_Var)ast0, null);
		ast=ast_t139;
		break;
//		140	crtFldAccThs 0
	case "crtFldAccThs":
		ast0=symbs.get(0).ast;
		ExprAccs_Fld ast_t140=new ExprAccs_Fld();
		ast_t140.setAccs(null, null, "this");
		ast=ast_t140;
		break;
//		141	crtFldAccSpr 0
	case "crtFldAccSpr":
		ast0=symbs.get(0).ast;
		ExprAccs_Fld ast_t141=new ExprAccs_Fld();
		ast_t141.setAccs(null, null, "super");
		ast=ast_t141;
		break;
//		142	crtArrAcc 1 0
	case "crtArrAcc":
		ast0=symbs.get(0).ast;
		ast1=symbs.get(1).ast;
		ExprAccs_Arr ast_t142=new ExprAccs_Arr();
		ast_t142.setAccs((ExprAccs_Fld)ast1, (NewArr_DimLst)ast0);
		ast=ast_t142;
		break;
//		143	crtPriNum 0
	case "crtPriNum":
		ast0=symbs.get(0).ast;
		ExprAccs_Pri ast_t143=new ExprAccs_Pri();
		ast_t143.setASTType(ExprPri_Num.class.getName());
		ast_t143.setPri(ast0);
		ast=ast_t143;
		break;
//		144	crtPriStr 0
	case "crtPriStr":
		ast0=symbs.get(0).ast;
		ExprAccs_Pri ast_t144=new ExprAccs_Pri();
		ast_t144.setASTType(ExprPri_Str.class.getName());
		ast_t144.setPri(ast0);
		ast=ast_t144;
		break;
//		145	crtPriChr 0
	case "crtPriChr":
		ast0=symbs.get(0).ast;
		ExprAccs_Pri ast_t145=new ExprAccs_Pri();
		ast_t145.setASTType(ExprPri_Chr.class.getName());
		ast_t145.setPri(ast0);
		ast=ast_t145;
		break;
//		146	crtPriCnd 1
	case "crtPriCnd":
		ast1=symbs.get(1).ast;
		ExprAccs_Pri ast_t146=new ExprAccs_Pri();
		ast_t146.setASTType(ExprCalc_Cond.class.getName());
		ast_t146.setPri(ast1);
		ast=ast_t146;
		break;
//		147	crtAppAcc 5 3 1
	case "crtAppAcc":
		ast5=symbs.get(5).ast;
		ast3=symbs.get(3).ast;
		ast1=symbs.get(1).ast;
		ExprAccs_App ast_t147=new ExprAccs_App();
		ast_t147.lnkApp((ExprAccs)ast5,null,(ExprPri_Var)ast3,(FuncApp_ArgLst)ast1);
		ast=ast_t147;
		break;
//		148	crtAppApp 5 3 1
	case "lnkAppGnrc":
		ast8=symbs.get(8).ast;
		ast5=symbs.get(5).ast;
		ast3=symbs.get(3).ast;
		ast1=symbs.get(1).ast;
		ExprAccs_App ast_t148=new ExprAccs_App();
		ast_t148.lnkApp((ExprAccs)ast8,(Gnrc_ArgLst) ast5,(ExprPri_Var)ast3,(FuncApp_ArgLst)ast1);
		ast=ast_t148;		
		break;
//		149	crtAppVar 3 1	
	case "crtAppGnrc":
		ast5=symbs.get(5).ast;
		ast3=symbs.get(3).ast;
		ast1=symbs.get(1).ast;
		ExprAccs_App ast_t149=new ExprAccs_App();
		ast_t149.setApp((Gnrc_ArgLst)ast5,(ExprPri_Var)ast3,(FuncApp_ArgLst)ast1);
		ast=ast_t149;
		break;
//		150	crtAppVar 3 1
	case "crtAppVar":
		ast3=symbs.get(3).ast;
		ast1=symbs.get(1).ast;
		ExprAccs_App ast_t150=new ExprAccs_App();
		ast_t150.setApp(null,(ExprPri_Var)ast3,(FuncApp_ArgLst)ast1);
		ast=ast_t150;
		break;
//		151	crtArgLst 0
	case "crtArgLst":
		ast0=symbs.get(0).ast;
		ast=ast0;
		break;
//		152	crtArgLstE 0
	case "crtArgLstE":
		FuncApp_ArgLst ast_t152=new FuncApp_ArgLst();
		ast_t152.setE();
		ast=ast_t152;
		break;
//		153	lnkArgLst 2 0
	case "lnkArgLst":
		ast0=symbs.get(0).ast;
		ast2=symbs.get(2).ast;
		FuncApp_ArgLst ast_t153=(FuncApp_ArgLst)ast2;
		ast_t153.addArg((Expr)ast0);
		ast=ast_t153;
		break;
//		154	crtArgCalc 0
	case "crtArgExp":
		ast0=symbs.get(0).ast;
		FuncApp_ArgLst ast_t154=new FuncApp_ArgLst();
		ast_t154.addArg((Expr)ast0);
		ast=ast_t154;
		break;
//		155	crtLbdExp 7 5 1
	case "crtLbdExp":
		ast1=symbs.get(1).ast;
		ast5=symbs.get(5).ast;
		Expr_Lmbd ast_t155=new Expr_Lmbd();
		ast_t155.setLmbd((FuncDef_ParLst)ast5, (AST_StmtList)ast1);
		ast=ast_t155;
		break;
	
	default:
		System.out.println("error no method");
		break;	
	}
	if(ast==null){
		System.out.println("error creating ast "+method+" state "+ crt_state.state_sn);
	}
	return ast;
	}	
	
}
