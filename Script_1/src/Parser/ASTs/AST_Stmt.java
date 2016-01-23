package Parser.ASTs;

import Parser.AST;

public class AST_Stmt extends AST {
	Stmt_DefCls stmt_cls;
	Stmt_DefIntf stmt_intf;
	Stmt_DefFunc stmt_func;
	Stmt_If stmt_if;
	Stmt_Whl stmt_whl;
	Stmt_Sg stmt_sg;
	public AST_Stmt(){}
	public boolean setAST(AST ast){
		switch(this.getType()){
		case "Stmt_DefCls":
			this.stmt_cls=(Stmt_DefCls)ast;
			//symtb
			break;
		case "Stmt_DefIntf":
			this.stmt_intf=(Stmt_DefIntf)ast;
			//symtb
			break;
		case "Stmt_DefFunc":
			this.stmt_func=(Stmt_DefFunc)ast;
			//symtb
			break;
		case "Stmt_If":
			this.stmt_if=(Stmt_If)ast;
			break;
		case "Stmt_Whl":
			this.stmt_whl=(Stmt_Whl)ast;
			break;
		case "Stmt_Sg":
			this.stmt_sg=(Stmt_Sg)ast;
			//symtb
			break;
		default:
			return false;
		}
		return true;
	}
	
}
