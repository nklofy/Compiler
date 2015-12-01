package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_FuncDef extends AST {
	private AST_Var name;
	private AST_ParList par_list;
	private AST_StmtList stmt_list;
	public boolean setFuncDef(AST_Var name, AST_ParList par_list, AST_StmtList stmt_list){
		this.name=name;
		this.par_list=par_list;
		this.stmt_list=stmt_list;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		// TODO Auto-generated method stub
		return false;
	}

}
