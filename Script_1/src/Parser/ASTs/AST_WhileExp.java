package Parser.ASTs;

import Interpreter.Interpreter;
import Interpreter.RT_Frame;
import Parser.AST;

public class AST_WhileExp extends AST {
	private AST_BoolExp bool_exp;
	private AST_StmtList stmt_list;
	public boolean setWhileExp(AST_BoolExp bool_exp,AST_StmtList stmt_list){
		this.bool_exp=bool_exp;
		this.stmt_list=stmt_list;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		interpreter.interpret(this.bool_exp);
		if(bool_exp.base_type==null)
			return false;		
		else if(bool_exp.base_type!=Type_Base.t_bool)
			return false;
		boolean cond=this.bool_exp.bool_value; 
		while(cond){
			interpreter.interpret(this.stmt_list);
			interpreter.interpret(this.bool_exp);
			if(bool_exp.base_type==null)
				return false;		
			else if(bool_exp.base_type!=Type_Base.t_bool)
				return false;
			cond=this.bool_exp.bool_value; 
		}
		return true;
	}

}
