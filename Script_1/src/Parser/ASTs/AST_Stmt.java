package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_Stmt extends AST {
	private Type_Stmt type;
	private AST_SgStmt sg_stmt;
	private AST_VarDef var_def;
	private AST_IfExp if_exp;
	private AST_WhileExp while_exp;
	private AST_FuncDef func_def;
	private AST_CtrFlw ctr_flw;
	public boolean setType(Type_Stmt type){
		this.type=type;
		return true;
	}
	public boolean setSgStmt(AST_SgStmt sg_stmt){
		this.sg_stmt=sg_stmt;
		return true;
	}
	public boolean setVarDef(AST_VarDef var_def){
		this.var_def=var_def;
		return true;
	}
	public boolean setIfExp(AST_IfExp if_exp){
		this.if_exp=if_exp;
		return true;
	}
	public boolean setWhileExp(AST_WhileExp while_exp){
		this.while_exp=while_exp;
		return true;
	}
	public boolean setFuncDef(AST_FuncDef func_def){
		this.func_def=func_def;
		return true;
	}public boolean setCtrFlw(AST_CtrFlw ctr_flw){
		this.ctr_flw=ctr_flw;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		// TODO Auto-generated method stub
		return false;
	}	
}
