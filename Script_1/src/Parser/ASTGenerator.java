//this generator is for some possible add-in features
package Parser;
import java.util.*;
import Parser.ASTs.*;
import Parser.TypeSys.*;

public class ASTGenerator {
	TypeSystem type_sys = new TypeSystem();
	public TypeSystem getTypeSys() {
		return type_sys;
	}
	public AST crtAST(String method, ParseState crt_state, LinkedList<Symbol> symbs){		
		AST ast=null;
		AST ast0,ast1,ast2,ast3,ast4,ast5,ast6,ast7,ast8,ast9,ast10;
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
		ast0=symbs.get(0).ast;
		AST_Stmt ast_t8=new AST_Stmt();
		ast_t8.setASTType(Stmt_Sg.class.getName());
		ast_t8.setStmt(ast0);
		ast=ast_t8;
		break;	
	//9	crtSgVarAssign 0
	case "crtSgVarAssign":
		ast0=symbs.get(0).ast;
		Stmt_Sg ast_t9=new Stmt_Sg();
		ast_t9.setASTType(SgStmt_AsnVar.class.getName());
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
		SgStmt_AsnVar ast_t21=new SgStmt_AsnVar();
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
		ast0=symbs.get(0).ast;
		TypeExp ast_t28=new TypeExp();
		ast_t28.setASTType(TypeExp_Func.class.getName());
		ast_t28.setTypeExp(ast0);
		ast=ast_t28;
		break;
//		29	crtBscTpInt 0
	case "crtBscTpInt":
		TypeExp_Bsc ast_t29 = new TypeExp_Bsc();
		ast_t29.setTypeT(type_sys.getBType("int"));
		ast=ast_t29;
		break;
//		30	crtBscTpDb 0
	case "crtBscTpDb":
		TypeExp_Bsc ast_t30 = new TypeExp_Bsc();
		ast_t30.setTypeT(type_sys.getBType("double"));
		ast=ast_t30;
		break;
//		31	crtBscTpBl 0
	case "crtBscTpBl":
		TypeExp_Bsc ast_t31 = new TypeExp_Bsc();
		ast_t31.setTypeT(type_sys.getBType("bool"));
		ast=ast_t31;
		break;
//		32	crtBscTpStr 0
	case "crtBscTpStr":
		TypeExp_Bsc ast_t32 = new TypeExp_Bsc();
		ast_t32.setTypeT(type_sys.getBType("string"));
		ast=ast_t32;
		break;
//		33	crtBscTpChr 0
	case "crtBscTpChr":
		TypeExp_Bsc ast_t33 = new TypeExp_Bsc();
		ast_t33.setTypeT(type_sys.getBType("char"));
		ast=ast_t33;
		break;
//		34	lnkArrTp 2
	case "lnkArrTp":
		ast2=symbs.get(2).ast;
		TypeExp_Arr ast_t34 = new TypeExp_Arr();
		ast_t34.setPreType((TypeExp)ast2);
		ast=ast_t34;
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
		ast_t38.setGnrcArgs((Gnrc_ArgLst)ast2, (ExprPri_Var)ast0, null);
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
		ast_t40.setGnrcArgs(null, (ExprPri_Var)ast0, null);
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
				(Extd_Lst)ast4,(Impl_Lst)ast3,(MbrDef_Lst)ast1);
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
				(Extd_Lst)ast3,(MbrDef_Lst)ast1);
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
		break;
//		47	crtScpInf 0
	case "crtScpInf":
		ast0 = symbs.get(0).ast;
		Scp_InfoLst ast_t47= new Scp_InfoLst();
		ast_t47.addScp((Scp_Info)ast0);
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
		Extd_Lst ast_t59=new Extd_Lst();
		ast=ast_t59;
//		60	lnkExtIdn 2 0
	case "lnkExtIdn":
		ast0=symbs.get(0).ast;
		ast2=symbs.get(2).ast;
		Extd_Lst ast_t60=(Extd_Lst)ast2;
		ast_t60.addExtd((TypeExp_Idn)ast0);
		ast=ast_t60;
		break;
//		61	crtExtIdn 0
	case "crtExtIdn":
		ast0=symbs.get(0).ast;
		Extd_Lst ast_t61=new Extd_Lst();
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
		Impl_Lst ast_t63=new Impl_Lst();
		ast=ast_t63;
		break;
//		64	lnkImpIdn 2 0
	case "lnkImpIdn":
		ast0=symbs.get(0).ast;
		ast2=symbs.get(2).ast;
		Impl_Lst ast_t64=(Impl_Lst)ast2;
		ast_t64.addImp((TypeExp_Idn)ast0);
		ast=ast_t64;
		break;
//		65	crtImpIdn 0
	case "crtImpIdn":
		ast0=symbs.get(0).ast;
		Impl_Lst ast_t65=new Impl_Lst();
		ast_t65.addImp((TypeExp_Idn)ast0);
		ast=ast_t65;
		break;
//		66	crtMmbDef 0
	case "crtMmbDef":
		ast0=symbs.get(0).ast;
		MbrDef_Lst ast_t66=(MbrDef_Lst)ast0;
		ast=ast_t66;
		break;
//		67	crtMmbDefE 0
	case "crtMmbDefE":
		MbrDef_Lst ast_t67=new MbrDef_Lst();
		ast=ast_t67;
		break;
//		68	lnkMmbFld 1 0
	case "lnkMmbFld":
		ast0=symbs.get(0).ast;
		ast1=symbs.get(1).ast;
		MbrDef_Lst ast_t68=(MbrDef_Lst)ast1;
		ast_t68.addMbr((MbrDef) ast0);
		ast=ast_t68;
		break;
//		69	lnkMmbMthd 1 0
	case "lnkMmbMthd":
		ast0=symbs.get(0).ast;
		ast1=symbs.get(1).ast;
		MbrDef_Lst ast_t69=(MbrDef_Lst)ast1;
		ast_t69.addMbr((MbrDef) ast0);
		ast=ast_t69;
		break;
//		70	crtMmbFld 0
	case "crtMmbFld":
		ast0=symbs.get(0).ast;
		MbrDef ast_t70=new MbrDef();
		ast_t70.setASTType(MbrDef_Fld.class.getName());
		ast_t70.setMbr(ast0);
		ast=ast_t70;
		break;
//		71	crtMemMthd 0
	case "crtMemMthd":
		ast0=symbs.get(0).ast;
		MbrDef ast_t71=new MbrDef();
		ast_t71.setASTType(MbrDef_Mthd.class.getName());
		ast_t71.setMbr(ast0);
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
		break;
//		75	crtParLst 0
	case "crtParLst":
		ast0=symbs.get(0).ast;
		ast=ast0;
		break;
//		76	crtParLstE 0
	case "crtParLstE":
		FuncDef_ParLst ast_t76=new FuncDef_ParLst();
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
		ast_t81.setIfBd((ExprCalc_Unary)ast4, (AST_StmtList)ast1, null);
		ast=ast_t81;
		break;
//		82	crtIfSg 3 1
	case "crtIfSg":
		ast1=symbs.get(1).ast;
		ast3=symbs.get(3).ast;
		StmtIf_IfBd ast_t82=new StmtIf_IfBd();
		ast_t82.setIfBd((ExprCalc_Unary)ast3, null, (Stmt_Sg)ast1);
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
		ast_t86.setwhl((ExprCalc_Unary)ast4, (AST_StmtList)ast1);
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
		ast_t92.setNewCls((TypeExp_Idn)ast3, null, (ExprAppl_ArgLst)ast1);
		ast=ast_t92;
		break;
//		93	crtNewClsGnrc 3 1
	case "crtNewClsGnrc":
		ast1=symbs.get(1).ast;
		ast3=symbs.get(3).ast;
		ExprCalc_NewCls ast_t93=new ExprCalc_NewCls();
		ast_t93.setNewCls(null, (TypeExp_Gnrc)ast3, (ExprAppl_ArgLst)ast1);
		ast=ast_t93;
		break;
//		94	crtNewArrTp 1 0
	case "crtNewArrTp":
		break;
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
