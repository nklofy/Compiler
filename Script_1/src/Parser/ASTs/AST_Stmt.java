package Parser.ASTs;

import Interpreter.*;
import Parser.*;

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
			break;
		default:
			return false;
		}
		this.upAll(ast);
		return true;
	}
	
	public boolean genCode(CodeGenerator codegen){
		switch(this.getASTType()){
		case "Stmt_DefCls":
			this.stmt_cls.genCode(codegen);
			break;
		case "Stmt_DefIntf":
			this.stmt_intf.genCode(codegen);
			break;
		case "Stmt_DefFunc":
			this.stmt_func.genCode(codegen);					
			break;
		case "Stmt_If":
			this.stmt_if.genCode(codegen);
			break;
		case "Stmt_Whl":
			this.stmt_whl.genCode(codegen);
			break;
		case "Stmt_Sg":
			this.stmt_sg.genCode(codegen);
			break;
		default:
			return false;
		}
		return true;
	}
	public boolean checkType(CodeGenerator codegen){		
		boolean rst=true;
		switch(this.getASTType()){
		case "Stmt_DefCls":
			rst= this.stmt_cls.checkType(codegen);
			break;
		case "Stmt_DefIntf":
			rst= this.stmt_intf.checkType(codegen);
			break;
		case "Stmt_DefFunc":
			rst= this.stmt_func.checkType(codegen);
			break;
		case "Stmt_If":
			rst= this.stmt_if.checkType(codegen);
			break;
		case "Stmt_Whl":
			rst= this.stmt_whl.checkType(codegen);
			break;
		case "Stmt_Sg":
			rst= this.stmt_sg.checkType(codegen);
			break;
		default:
			return false;
		}
		return rst;
	}
	
}
