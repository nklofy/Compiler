package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_AddExp extends AST {
	private AST_AddExp add_exp;
	private AST_MulExp mul_exp;
	private String opt;
	private AST_Var var;
	Type_Base base_type;
	long int_value;
	double double_value;
	boolean bool_value;
	char char_value;
	String string_value;
	Data_Obj data_obj;
	public boolean setAddExp(AST_AddExp add_exp, AST_MulExp mul_exp, String opt, AST_Var var){
		this.add_exp=add_exp;
		this.mul_exp=mul_exp;
		this.opt=opt;
		this.var=var;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		interpreter.interpret(mul_exp);
		if(this.add_exp!=null)
			interpreter.interpret(add_exp);
		if(this.var!=null)
			interpreter.interpret(var);
		if(opt==null){
			if(mul_exp.base_type!=null){
				this.base_type=mul_exp.base_type;
				switch(mul_exp.base_type){
				case t_int:
					this.int_value=mul_exp.int_value;
					break;
				case t_double:
					this.double_value=mul_exp.double_value;
					break;
				case t_bool:
					this.bool_value=mul_exp.bool_value;
					break;
				case t_char:
					this.char_value=mul_exp.char_value;
					break;
				case t_string:
					this.string_value=mul_exp.string_value;
					break;
				default:
					break;	
				}
			}else if(mul_exp.data_obj!=null){
				this.data_obj=mul_exp.data_obj;
			}else
				return false;	
			return true;
		}else if(opt.equals("-")){
			if(this.add_exp==null){
				this.base_type=mul_exp.base_type;
				if(mul_exp.base_type==Type_Base.t_int)
					this.int_value=-mul_exp.int_value;
				if(mul_exp.base_type==Type_Base.t_double)
					this.double_value=-mul_exp.double_value;
			}else{
				if(add_exp.base_type==Type_Base.t_int&&mul_exp.base_type==Type_Base.t_int){
					this.base_type=Type_Base.t_int;
					this.int_value=add_exp.int_value-mul_exp.int_value;
				}
				else if(add_exp.base_type==Type_Base.t_double&&mul_exp.base_type==Type_Base.t_double){
					this.base_type=Type_Base.t_double;
					this.double_value=add_exp.double_value-mul_exp.double_value;
				}
				else if(add_exp.base_type==Type_Base.t_double&&mul_exp.base_type==Type_Base.t_int){
					this.base_type=Type_Base.t_double;
					this.double_value=add_exp.double_value-(double)mul_exp.int_value;
				}
				else if(add_exp.base_type==Type_Base.t_int&&mul_exp.base_type==Type_Base.t_double){
					this.base_type=Type_Base.t_double;
					this.double_value=(double)add_exp.int_value-mul_exp.double_value;
				}
			}
			return true;
		}else if(opt.equals("+")){
			if(add_exp.base_type==Type_Base.t_int&&mul_exp.base_type==Type_Base.t_int){
				this.base_type=Type_Base.t_int;
				this.int_value=add_exp.int_value+mul_exp.int_value;
			}
			else if(add_exp.base_type==Type_Base.t_double&&mul_exp.base_type==Type_Base.t_double){
				this.base_type=Type_Base.t_double;
				this.double_value=add_exp.double_value+mul_exp.double_value;
			}
			else if(add_exp.base_type==Type_Base.t_double&&mul_exp.base_type==Type_Base.t_int){
				this.base_type=Type_Base.t_double;
				this.double_value=add_exp.double_value+(double)mul_exp.int_value;
			}
			else if(add_exp.base_type==Type_Base.t_int&&mul_exp.base_type==Type_Base.t_double){
				this.base_type=Type_Base.t_double;
				this.double_value=(double)add_exp.int_value+mul_exp.double_value;
			}
			
		}else if(opt.equals("++")){
			if(var.data_obj.type_obj.type_base==Type_Base.t_int){
				this.base_type=Type_Base.t_int;
				this.int_value=++var.data_obj.int_value;
				return true;
			}else{
				return false;				
			}
		}else if(opt.equals("--")){
			if(var.data_obj.type_obj.type_base==Type_Base.t_int){
				this.base_type=Type_Base.t_int;
				this.int_value=--var.data_obj.int_value;
				return true;
			}else{
				return false;				
			}			
		}else if(opt.equals("++T")){
			if(var.data_obj.type_obj.type_base==Type_Base.t_int){
				this.base_type=Type_Base.t_int;
				this.int_value=var.data_obj.int_value++;
				return true;
			}else{
				return false;				
			}			
		}else if(opt.equals("--T")){
			if(var.data_obj.type_obj.type_base==Type_Base.t_int){
				this.base_type=Type_Base.t_int;
				this.int_value=var.data_obj.int_value--;
				return true;
			}else{
				return false;				
			}
		}
		return false;
	}
}
