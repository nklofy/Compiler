package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_IfStmt extends AST {
	private AST_BoolExp bool_exp;
	private AST_StmtList stmt_list;
	private AST_SgStmt sg_stmt;
	boolean cond_value;
	public boolean setIfStmt(AST_BoolExp bool_exp, AST_StmtList stmt_list, AST_SgStmt sg_stmt){
		this.bool_exp=bool_exp;
		this.stmt_list=stmt_list;
		this.sg_stmt=sg_stmt;
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
		if(cond){
			this.cond_value=true;
			if(this.stmt_list!=null){
				interpreter.interpret(this.stmt_list);
			}else if(this.sg_stmt!=null){
				interpreter.interpret(this.sg_stmt);
			}
		}else{
			this.cond_value=false;			
		}
		return false;
	}

}
