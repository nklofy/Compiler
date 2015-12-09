package Parser.ASTs;

import java.util.*;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_ApplyExp extends AST {
	private AST_ApplyExp apply_exp;
	private AST_Var var;
	private AST_ArgList arg_list;
	Type_Base type;
	long int_value;
	double double_value;
	boolean bool_value;
	char char_value;
	String string_value;
	Data_Obj obj;
	Data_Func func;
	public boolean setApplyExp(AST_ApplyExp apply_exp, AST_Var var, AST_ArgList arg_list){
		this.apply_exp=apply_exp;
		this.var=var;
		this.arg_list=arg_list;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		Data_Obj obj;
		if(apply_exp!=null){
			interpreter.interpret(apply_exp);
			obj=apply_exp.obj;
		}
		if(arg_list!=null){
			interpreter.interpret(var);
			interpreter.interpret(arg_list);
			if(var.func!=null){
				if(this.obj==null){
					this.obj=var.func.run(arg_list.getArgs());
				}else{
					this.obj=this.obj.getFunc(var.name,arg_list.arg_types).run(arg_list.getArgs());
				}
			}
			return true;
		}
		if(var!=null){
			interpreter.interpret(var);
			this.type=var.type;
			switch(this.type){
			case t_int:
				this.int_value=var.int_value;
				break;
			case t_double:
				this.double_value=var.double_value;
				break;
			case t_bool:
				this.bool_value=var.bool_value;
				break;
			case t_char:
				this.char_value=var.char_value;
				break;
			case t_string:
				this.string_value=var.string_value;
				break;
				default:
					break;				
			}
			if(this.obj!=null){
				this.obj=this.obj.getField(var.name);
			}
			return true;
		}		
		return false;
	}

}
