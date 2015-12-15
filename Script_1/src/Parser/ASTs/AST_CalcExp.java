package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;
import Parser.TypeSys.Data_Obj;
import Parser.TypeSys.Type_Base;
import Parser.TypeSys.Type_Obj;

public class AST_CalcExp extends AST {
	private AST_BoolExp bool_exp;
	private AST_StrExp str_exp;
	Type_Base base_type;
	long int_value;
	double double_value;
	boolean bool_value;
	char char_value;
	String string_value;
	Data_Obj data_obj;
	public boolean setCalcExp(AST_BoolExp bool_exp, AST_StrExp str_exp){
		this.bool_exp=bool_exp;
		this.str_exp=str_exp;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		if(this.bool_exp!=null){
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
		}else if(str_exp!=null){
			//TODO
		}
		return false;
	}
	public Data_Obj boxObj(){
		Data_Obj obj=new Data_Obj();
		obj.type_obj=new Type_Obj();
		obj.type_obj.type_base=this.base_type;
		switch(this.base_type){
		case t_int:
			obj.int_value=this.int_value;
			break;
		case t_double:
			obj.double_value=this.double_value;
			break;
		case t_bool:
			obj.bool_value=this.bool_value;
			break;
		case t_char:
			obj.char_value=this.char_value;
			break;
		case t_string:
			obj.string_value=this.string_value;
			break;
		default:
			break;	
		}
		return obj;
	}

}
