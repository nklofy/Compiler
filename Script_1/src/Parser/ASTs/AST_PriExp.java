package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_PriExp extends AST {
	private AST_AddExp add_exp;
	private AST_Num num;
	private AST_ApplyExp apply_exp;
	long int_value;
	double double_value;
	Type_Base type;
	public boolean setPriExp(AST_AddExp add_exp, AST_Num num, AST_ApplyExp apply_exp){
		this.add_exp=add_exp;
		this.num=num;
		this.apply_exp=apply_exp;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		if(num!=null){
			interpreter.interpret(num);
			this.type=num.type;
			if(num.type==Type_Base.t_int){
				this.int_value=num.int_value;
			}else if(num.type==Type_Base.t_double){
				this.double_value=num.double_value;
			}
			return true;
		}else if(add_exp!=null){
			interpreter.interpret(add_exp);
			this.type=add_exp.type;
			if(add_exp.type==Type_Base.t_int){
				this.int_value=add_exp.int_value;
			}else if(add_exp.type==Type_Base.t_double){
				this.double_value=add_exp.double_value;
			}
			return true;
		}else if(apply_exp!=null){
			interpreter.interpret(apply_exp);
			this.type=apply_exp.type;
			if(apply_exp.type==Type_Base.t_int){
				this.int_value=apply_exp.int_value;
			}else if(apply_exp.type==Type_Base.t_double){
				this.double_value=apply_exp.double_value;
			}else{
				return false;
			}
			return true;
		}
		return false;
	}
}
