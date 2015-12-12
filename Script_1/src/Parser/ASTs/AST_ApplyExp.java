package Parser.ASTs;

import java.util.*;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_ApplyExp extends AST {
	private AST_ApplyExp apply_exp;
	private AST_Var var;
	private AST_ArgList arg_list;
	Type_Base base_type;
	long int_value;
	double double_value;
	boolean bool_value;
	char char_value;
	String string_value;
	Data_Obj data_obj;
	public boolean setApplyExp(AST_ApplyExp apply_exp, AST_Var var, AST_ArgList arg_list){
		this.apply_exp=apply_exp;
		this.var=var;
		this.arg_list=arg_list;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		Data_Obj obj;
		if(apply_exp!=null){//not used currently
			interpreter.interpret(apply_exp);
			obj=apply_exp.data_obj;
		}
		if(arg_list!=null){//function apply
			interpreter.interpret(var);
			interpreter.interpret(arg_list);
			if(var.data_obj.type_obj.type_func!=null){		//var is func
				if(this.data_obj==null){
					this.data_obj=var.data_obj.type_obj.type_func.data_func.run(interpreter, arg_list.getArgs());
				}else{
					this.data_obj=this.data_obj.getFunc(var.name,arg_list.arg_types).run(interpreter, arg_list.getArgs());
				}
			}
			return true;
		}
		if(var!=null){
			interpreter.interpret(var);
			if(this.data_obj==null){					
				if(var.data_obj.type_obj.type_base!=null){ 	//get var base type
					this.base_type=var.data_obj.type_obj.type_base;	
					switch(this.base_type){
					case t_int:
						this.int_value=var.data_obj.int_value;
						break;
					case t_double:
						this.double_value=var.data_obj.double_value;
						break;
					case t_bool:
						this.bool_value=var.data_obj.bool_value;
						break;
					case t_char:
						this.char_value=var.data_obj.char_value;
						break;
					case t_string:
						this.string_value=var.data_obj.string_value;
						break;
					default:
						break;				
					}
				}else{// get var object
					this.data_obj=new Data_Obj(var.data_obj);
				}
				return true;
			}else{// data_obj exists
				this.data_obj=this.data_obj.getField(var.name);
			}
			return true;
		}		
		return false;
	}
}
