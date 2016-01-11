package Parser.ASTs.old;

import Interpreter.Interpreter;
import Parser.AST;
import Parser.TypeSys.old.Data_Obj;
import Parser.TypeSys.old.Type_Base;

public class AST_PriExp extends AST {
	private AST_AddExp add_exp;
	private AST_Num num;
	private AST_ApplyExp apply_exp;
	private AST_StrExp str_exp;
	Type_Base base_type;
	long int_value;
	double double_value;
	boolean bool_value;
	char char_value;
	String string_value;
	Data_Obj data_obj;
	public boolean setPriExp(AST_AddExp add_exp, AST_Num num, AST_ApplyExp apply_exp, AST_StrExp str_exp){
		this.add_exp=add_exp;
		this.num=num;
		this.apply_exp=apply_exp;
		this.str_exp=str_exp;
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
			this.data_obj=boxObj(interpreter);
			return true;
		}else if(add_exp!=null){
			interpreter.interpret(add_exp);
			if(add_exp.data_obj!=null){
				this.data_obj=new Data_Obj(add_exp.data_obj);
			}else{
				System.out.println("error PriExp eval AddExp with null value");
				return false;
			}
			return true;
		}else if(apply_exp!=null){
			interpreter.interpret(apply_exp);
			if(apply_exp.data_obj!=null){
				this.data_obj=new Data_Obj(apply_exp.data_obj);				
			}else{
				System.out.println("error PriExp eval AppExp with null value");
				return false;
			}	
			return true;
		}else if(this.str_exp!=null){
			interpreter.interpret(str_exp);
			this.base_type=str_exp.type;
			switch(this.base_type){
			case t_string:
				this.string_value=this.str_exp.str_value;
				break;
			case t_char:
				this.char_value=this.str_exp.chr_value;
				break;
			default:
				this.string_value="";
			}
			this.data_obj=boxObj(interpreter);
			return true;
		}
		return false;	
	}
	private Data_Obj boxObj(Interpreter interpreter){
		Data_Obj obj=new Data_Obj();
		switch(this.base_type){
		case t_int:
			obj.setTypeObj(interpreter.getGlbEnv().getType("int"));
			obj.setIntV(this.int_value);
			break;
		case t_double:
			obj.setTypeObj(interpreter.getGlbEnv().getType("double"));
			obj.setDoubleV(this.double_value);
			break;
		case t_bool:
			obj.setTypeObj(interpreter.getGlbEnv().getType("bool"));
			obj.setBoolV(this.bool_value);
			break;
		case t_char:
			obj.setTypeObj(interpreter.getGlbEnv().getType("char"));
			obj.setCharV(this.char_value);
			break;
		case t_string:
			obj.setTypeObj(interpreter.getGlbEnv().getType("string"));
			obj.setStringV(this.string_value);
			break;
		default:
			break;	
		}
		return obj;
	}
}
