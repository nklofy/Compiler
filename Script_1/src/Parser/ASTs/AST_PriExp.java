package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_PriExp extends AST {
	private AST_AddExp add_exp;
	private AST_Num num;
	private AST_ApplyExp apply_exp;
	Type_Base base_type;
	long int_value;
	double double_value;
	boolean bool_value;
	char char_value;
	String string_value;
	Data_Obj data_obj;
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
			this.base_type=num.type;
			if(num.type==Type_Base.t_int){
				this.int_value=num.int_value;
			}else if(num.type==Type_Base.t_double){
				this.double_value=num.double_value;
			}
			return true;
		}else if(add_exp!=null){
			interpreter.interpret(add_exp);
			if(add_exp.base_type!=null){
				this.base_type=add_exp.base_type;
				switch(add_exp.base_type){
				case t_int:
					this.int_value=add_exp.int_value;
					break;
				case t_double:
					this.double_value=add_exp.double_value;
					break;
				case t_bool:
					this.bool_value=add_exp.bool_value;
					break;
				case t_char:
					this.char_value=add_exp.char_value;
					break;
				case t_string:
					this.string_value=add_exp.string_value;
					break;
				default:
					break;	
				}
			}else if(add_exp.data_obj!=null){
				this.data_obj=new Data_Obj(add_exp.data_obj);
			}else
				return false;
			return true;
		}else if(apply_exp!=null){
			interpreter.interpret(apply_exp);
			if(apply_exp.base_type!=null){
				this.base_type=apply_exp.base_type;
				switch(apply_exp.base_type){
				case t_int:
					this.int_value=apply_exp.int_value;
					break;
				case t_double:
					this.double_value=apply_exp.double_value;
					break;
				case t_bool:
					this.bool_value=apply_exp.bool_value;
					break;
				case t_char:
					this.char_value=apply_exp.char_value;
					break;
				case t_string:
					this.string_value=apply_exp.string_value;
					break;
				default:
					break;	
				} 
			}else if(apply_exp.data_obj!=null){
				this.data_obj=new Data_Obj(apply_exp.data_obj);
			}	
			return true;
		}
		return false;
	}
}
