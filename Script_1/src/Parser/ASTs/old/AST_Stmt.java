package Parser.ASTs.old;

import Interpreter.Interpreter;
import Parser.AST;
import Parser.TypeSys.old.Type_Stmt;

public class AST_Stmt extends AST {
	private Type_Stmt stmt_type;
	private AST_SgStmt sg_stmt;
	private AST_VarDef var_def;
	private AST_IfExp if_exp;
	private AST_WhileExp while_exp;
	private AST_FuncDef func_def;
	private AST_CtrFlw ctr_flw;
	public boolean setType(Type_Stmt type){
		this.stmt_type=type;
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
		switch(this.stmt_type){
		case SgStmt:
			interpreter.interpret(this.sg_stmt);
			break;
		case VarDef:
			interpreter.interpret(this.var_def);
			break;
		case IfExp:
			interpreter.interpret(this.if_exp);
			break;
		case WhileExp:
			interpreter.interpret(this.while_exp);
			break;
		case FuncDef:
			interpreter.interpret(this.func_def);
			break;
		default:
			break;
		}
		return false;
	}	
}
