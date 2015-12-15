package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;
import Parser.TypeSys.Data_Obj;
import Parser.TypeSys.Type_Base;

public class AST_CmpExp extends AST {
	private AST_BoolExp bool_exp;
	private AST_AddExp add_exp1;
	private AST_AddExp add_exp2;
	private String opt;
	int bl_value;		//corresponding to directly set this exp true or false
	Type_Base base_type;
	long int_value;
	double double_value;
	boolean bool_value;
	char char_value;
	String string_value;
	Data_Obj data_obj;
	public boolean setCmpExp(AST_BoolExp bool_exp, AST_AddExp add_exp1, String opt, AST_AddExp add_exp2, int bl_value){
		this.bool_exp=bool_exp;
		this.add_exp1=add_exp1;
		this.add_exp2=add_exp2;
		this.opt=opt;	
		this.bl_value=bl_value;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		if(this.bl_value==1){
			this.base_type=Type_Base.t_bool;
			this.bool_value=true;
		}else if(this.bl_value==-1){
			this.base_type=Type_Base.t_bool;
			this.bool_value=false;
		}else if(this.bool_exp!=null){
			interpreter.interpret(this.bool_exp);
			if(this.bool_exp.base_type!=null){
				this.base_type=this.bool_exp.base_type;
				switch(bool_exp.base_type){
				case t_int:
					this.int_value=bool_exp.int_value;
					break;
				case t_double:
					this.double_value=bool_exp.double_value;
					break;
				case t_bool:
					this.bool_value=bool_exp.bool_value;
					break;
				case t_char:
					this.char_value=bool_exp.char_value;
					break;
				case t_string:
					this.string_value=bool_exp.string_value;
					break;
				default:
					break;	
				}
			}else if(this.bool_exp.data_obj!=null){
				this.data_obj=new Data_Obj(this.bool_exp.data_obj);
			}else
				return false;
		}else if(this.opt!=null && this.add_exp1!=null && this.add_exp2!=null){
			interpreter.interpret(this.add_exp1);
			interpreter.interpret(this.add_exp2);
			this.base_type=Type_Base.t_bool;
			switch(this.opt){
			case "<":				
				this.bool_value=(add_exp1.int_value+add_exp1.double_value)<(add_exp2.int_value+add_exp2.double_value);
				break;
			case ">":
				this.bool_value=(add_exp1.int_value+add_exp1.double_value)>(add_exp2.int_value+add_exp2.double_value);
				break;
			case "<=":
				this.bool_value=(add_exp1.int_value+add_exp1.double_value)<=(add_exp2.int_value+add_exp2.double_value);
				break;
			case ">=":
				this.bool_value=(add_exp1.int_value+add_exp1.double_value)>=(add_exp2.int_value+add_exp2.double_value);
				break;
			case "==":
				this.bool_value=(add_exp1.int_value+add_exp1.double_value)==(add_exp2.int_value+add_exp2.double_value);
				break;
			case "!=":
				this.bool_value=(add_exp1.int_value+add_exp1.double_value)!=(add_exp2.int_value+add_exp2.double_value);
				break;
			default:
				break;
			}
		}else if(this.add_exp1!=null && this.add_exp2 == null){
			interpreter.interpret(this.add_exp1);
			if(add_exp1.base_type!=null){
				this.base_type=add_exp1.base_type;
				switch(add_exp1.base_type){
				case t_int:
					this.int_value=add_exp1.int_value;
					break;
				case t_double:
					this.double_value=add_exp1.double_value;
					break;
				case t_bool:
					this.bool_value=add_exp1.bool_value;
					break;
				case t_char:
					this.char_value=add_exp1.char_value;
					break;
				case t_string:
					this.string_value=add_exp1.string_value;
					break;
				default:
					break;	
				}
			}else if(add_exp1.data_obj!=null){
				this.data_obj=new Data_Obj(add_exp1.data_obj);
			}else
				return false;
		}else
			return false;
		return true;
	}

}
