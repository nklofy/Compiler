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
		//this.upAll(ast);
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
	public boolean upSymTbl(CodeGenerator codegen){
		switch(this.getASTType()){
		case "Stmt_DefCls":
			boolean b=this.stmt_cls.upSymTbl(codegen);
			codegen.addTypeSymTb(this.stmt_cls.name,this.stmt_cls.t_type);
			return b;
		case "Stmt_DefIntf":
			b=this.stmt_intf.upSymTbl(codegen);
			codegen.addTypeSymTb(this.stmt_intf.name, this.stmt_intf.t_type);
			return b;
		case "Stmt_DefFunc":
			b=this.stmt_func.upSymTbl(codegen);
			codegen.addFuncSymTb(this.stmt_func.name, this.stmt_func.t_type);
			return b;
		case "Stmt_If":
			return this.stmt_if.upSymTbl(codegen);			
		case "Stmt_Whl":
			return this.stmt_whl.upSymTbl(codegen);
		case "Stmt_Sg":
			return this.stmt_sg.upSymTbl(codegen);
		default:
			return false;
		}
	}
	public boolean checkType(CodeGenerator codegen){		
		boolean rst=true;
		switch(this.getASTType()){
		case "Stmt_DefCls":
			return this.stmt_cls.checkType(codegen);
		case "Stmt_DefIntf":
			return this.stmt_intf.checkType(codegen);
		case "Stmt_DefFunc":
			return this.stmt_func.checkType(codegen);
		case "Stmt_If":
			return this.stmt_if.checkType(codegen);
		case "Stmt_Whl":
			return this.stmt_whl.checkType(codegen);
		case "Stmt_Sg":
			return this.stmt_sg.checkType(codegen);
		default:
			return false;
		}
	}
	
}
