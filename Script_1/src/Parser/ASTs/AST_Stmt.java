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
		switch(this.getASTType()){
		case "Stmt_DefCls":
			if(this.stmt_cls.isMerged()){
				this.stmt_cls=(Stmt_DefCls)this.stmt_cls.getDeMrg(codegen);
				if(this.stmt_cls!=null)
					return true;
			}else
				return this.stmt_cls.checkType(codegen);
			break;
		case "Stmt_DefIntf":
			if(this.stmt_intf.isMerged()){
				this.stmt_intf=(Stmt_DefIntf)this.stmt_intf.getDeMrg(codegen);
				if(this.stmt_intf!=null)
					return true;
			}else
				return this.stmt_intf.checkType(codegen);
			break;
		case "Stmt_DefFunc":
			if(this.stmt_func.isMerged()){
				this.stmt_func=(Stmt_DefFunc)this.stmt_func.getDeMrg(codegen);
				if(this.stmt_func!=null)
					return true;
			}else
				return this.stmt_func.checkType(codegen);
			break;
		case "Stmt_If":
			if(this.stmt_if.isMerged()){
				this.stmt_if=(Stmt_If)this.stmt_if.getDeMrg(codegen);
				if(this.stmt_if!=null)
					return true;
			}else
				return this.stmt_if.checkType(codegen);
			break;
		case "Stmt_Whl":
			if(this.stmt_whl.isMerged()){
				this.stmt_whl=(Stmt_Whl)this.stmt_whl.getDeMrg(codegen);
				if(this.stmt_whl!=null)
					return true;
			}else
				return this.stmt_whl.checkType(codegen);
			break;
		case "Stmt_Sg":
			if(this.stmt_sg.isMerged()){
				this.stmt_sg=(Stmt_Sg)this.stmt_sg.getDeMrg(codegen);
				if(this.stmt_sg!=null)
					return true;
			}else
				return this.stmt_sg.checkType(codegen);
			break;
		default:
			return false;
		}
		return false;
	}
	
}
