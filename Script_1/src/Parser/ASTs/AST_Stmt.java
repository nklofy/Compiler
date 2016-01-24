package Parser.ASTs;

import Parser.AST;

public class AST_Stmt extends AST {
	Stmt_DefCls stmt_cls;
	Stmt_DefIntf stmt_intf;
	Stmt_DefFunc stmt_func;
	Stmt_If stmt_if;
	Stmt_Whl stmt_whl;
	Stmt_Sg stmt_sg;
	public boolean setStmt(AST ast){
		switch(this.getType()){
		case "Stmt_DefCls":
			this.stmt_cls=(Stmt_DefCls)ast;
			if(ast.getMergedAsts()!=null){
				break;
			}
			for(String s:ast.getTypeUp()){
				if(this.getTypeTb()!=null && this.getTypeTb().keySet().contains(s)){
					System.out.println("error existing symbol name: "+ s);
				}else{
					this.putTypeTb(s, ast.getTypeTb().get(s));
					this.addTypeUp(s);
				}
			}
			break;
		case "Stmt_DefIntf":
			this.stmt_intf=(Stmt_DefIntf)ast;
			if(ast.getMergedAsts()!=null){
				break;
			}
			for(String s:ast.getTypeUp()){
				if(this.getTypeTb()!=null && this.getTypeTb().keySet().contains(s)){
					System.out.println("error existing symbol name: "+ s);
				}else{
					this.putTypeTb(s, ast.getTypeTb().get(s));
					this.addTypeUp(s);
				}
			}
			break;
		case "Stmt_DefFunc":
			this.stmt_func=(Stmt_DefFunc)ast;
			if(ast.getMergedAsts()!=null){
				break;
			}
			for(String s:ast.getFuncUp()){
				if(this.getFuncTb()!=null && this.getFuncTb().keySet().contains(s)){
					System.out.println("error existing symbol name: "+ s);
				}else{
					this.putFuncTb(s, ast.getFuncTb().get(s));
					this.addFuncUp(s);
				}
			}
			break;
		case "Stmt_If":
			this.stmt_if=(Stmt_If)ast;
			break;
		case "Stmt_Whl":
			this.stmt_whl=(Stmt_Whl)ast;
			break;
		case "Stmt_Sg":
			this.stmt_sg=(Stmt_Sg)ast;
			if(ast.getMergedAsts()!=null){
				break;
			}
			for(String s:ast.getVarUp()){
				if(this.getVarTb()!=null && this.getVarTb().keySet().contains(s)){
					System.out.println("error existing symbol name: "+ s);
				}else{
					this.putVarTb(s, ast.getVarTb().get(s));
					this.addVarUp(s);
				}
			}
			break;
		default:
			return false;
		}
		return true;
	}
	
}
