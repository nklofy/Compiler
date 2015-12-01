package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_ParList extends AST {
	private AST_TypeExp type_exp;
	private AST_Var var;
	private AST_ParList par_list;
	public boolean setParList(AST_ParList par_list, AST_TypeExp type_exp, AST_Var var){
		this.par_list=par_list;
		this.type_exp=type_exp;
		this.var=var;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		// TODO Auto-generated method stub
		return false;
	}

}
