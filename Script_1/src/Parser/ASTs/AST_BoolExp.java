package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_BoolExp extends AST {
	private AST_BoolExp bool_exp;
	private AST_CmpExp cmp_exp;
	private String opt;
	Type_Base base_type;
	long int_value;
	double double_value;
	boolean bool_value;
	char char_value;
	String string_value;
	Data_Obj data_obj;
	public boolean setBoolExp(AST_BoolExp bool_exp, String opt, AST_CmpExp cmp_exp){
		this.bool_exp=bool_exp;
		this.cmp_exp=cmp_exp;
		this.opt=opt;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		if(this.cmp_exp!=null)
			interpreter.interpret(this.cmp_exp);
		if(this.opt==null){
			if(this.cmp_exp.base_type!=null){
				this.base_type=this.cmp_exp.base_type;
				switch(cmp_exp.base_type){
				case t_int:
					this.int_value=cmp_exp.int_value;
					break;
				case t_double:
					this.double_value=cmp_exp.double_value;
					break;
				case t_bool:
					this.bool_value=cmp_exp.bool_value;
					break;
				case t_char:
					this.char_value=cmp_exp.char_value;
					break;
				case t_string:
					this.string_value=cmp_exp.string_value;
					break;
				default:
					break;	
				}
			}else if(this.cmp_exp.data_obj!=null){
				this.data_obj=new Data_Obj(this.cmp_exp.data_obj);
			}else
				return false;
		}else if(this.bool_exp!=null){
			interpreter.interpret(this.bool_exp);
			this.base_type=Type_Base.t_bool;
			switch(this.opt){
			case "&&":
				this.bool_value=this.bool_exp.bool_value && this.cmp_exp.bool_value;
				break;
			case "||":
				this.bool_value=this.bool_exp.bool_value || this.cmp_exp.bool_value;
				break;
				default:
					break;
			}
		}
		else if(this.opt=="!"){
			if(this.cmp_exp.base_type==Type_Base.t_bool){
				this.base_type=Type_Base.t_bool;
				this.bool_value=!this.cmp_exp.bool_value;
			}
			else
				return false;
		}else
			return false;
		return true;
	}

}
