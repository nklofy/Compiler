package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;
import Parser.CodeGenerator;

public class AST_Stmt extends AST {
	Stmt_DefCls stmt_cls;
	Stmt_DefIntf stmt_intf;
	Stmt_DefFunc stmt_func;
	Stmt_If stmt_if;
	Stmt_Whl stmt_whl;
	Stmt_Sg stmt_sg;
	public boolean setStmt(AST ast){
		switch(this.getASTType()){
		case "Stmt_DefCls":
			this.stmt_cls=(Stmt_DefCls)ast;
			break;
		case "Stmt_DefIntf":
			this.stmt_intf=(Stmt_DefIntf)ast;
			break;
		case "Stmt_DefFunc":
			this.stmt_func=(Stmt_DefFunc)ast;					
			break;
		case "Stmt_If":
			this.stmt_if=(Stmt_If)ast;
			break;
		case "Stmt_Whl":
			this.stmt_whl=(Stmt_Whl)ast;
			break;
		case "Stmt_Sg":
			this.stmt_sg=(Stmt_Sg)ast;
			this.upAll(ast);
			break;
		default:
			return false;
		}
		this.upAll(ast);
		return true;
	}
	public boolean eval(Interpreter interpreter){return true;}
	public boolean genCode(CodeGenerator codegen){
		return true;
	}
	public boolean checkType(){
		return true;
	}
}
