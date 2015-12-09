package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_AddExp extends AST {
	private AST_AddExp add_exp;
	private AST_MulExp mul_exp;
	private String opt;
	private AST_Var var;
	long int_value;
	double double_value;
	Type_Base type;
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
			this.type=mul_exp.type;
			if(mul_exp.type==Type_Base.t_int)
				this.int_value=mul_exp.int_value;
			else if(mul_exp.type==Type_Base.t_double)
				this.double_value=mul_exp.double_value;
			return true;
		}else if(opt.equals("-")){
			if(this.add_exp==null){
				this.type=mul_exp.type;
				if(mul_exp.type==Type_Base.t_int)
					this.int_value=-mul_exp.int_value;
				if(mul_exp.type==Type_Base.t_double)
					this.double_value=-mul_exp.double_value;
			}else{
				if(add_exp.type==Type_Base.t_int&&mul_exp.type==Type_Base.t_int){
					this.type=Type_Base.t_int;
					this.int_value=add_exp.int_value-mul_exp.int_value;
				}
				else if(add_exp.type==Type_Base.t_double&&mul_exp.type==Type_Base.t_double){
					this.type=Type_Base.t_double;
					this.double_value=add_exp.double_value-mul_exp.double_value;
				}
				else if(add_exp.type==Type_Base.t_double&&mul_exp.type==Type_Base.t_int){
					this.type=Type_Base.t_double;
					this.double_value=add_exp.double_value-(double)mul_exp.int_value;
				}
				else if(add_exp.type==Type_Base.t_int&&mul_exp.type==Type_Base.t_double){
					this.type=Type_Base.t_double;
					this.double_value=(double)add_exp.int_value-mul_exp.double_value;
				}
			}
			return true;
		}else if(opt.equals("+")){
			if(add_exp.type==Type_Base.t_int&&mul_exp.type==Type_Base.t_int){
				this.type=Type_Base.t_int;
				this.int_value=add_exp.int_value+mul_exp.int_value;
			}
			else if(add_exp.type==Type_Base.t_double&&mul_exp.type==Type_Base.t_double){
				this.type=Type_Base.t_double;
				this.double_value=add_exp.double_value+mul_exp.double_value;
			}
			else if(add_exp.type==Type_Base.t_double&&mul_exp.type==Type_Base.t_int){
				this.type=Type_Base.t_double;
				this.double_value=add_exp.double_value+(double)mul_exp.int_value;
			}
			else if(add_exp.type==Type_Base.t_int&&mul_exp.type==Type_Base.t_double){
				this.type=Type_Base.t_double;
				this.double_value=(double)add_exp.int_value+mul_exp.double_value;
			}
			
		}else if(opt.equals("++")){
			if(var.type==Type_Base.t_int){
				this.type=var.type;
				this.int_value=++var.int_value;
				return true;
			}else{
				return false;				
			}
		}else if(opt.equals("--")){
			if(var.type==Type_Base.t_int){
				this.type=var.type;
				this.int_value=--var.int_value;
				return true;
			}else{
				return false;				
			}			
		}else if(opt.equals("++T")){
			if(var.type==Type_Base.t_int){
				this.type=var.type;
				this.int_value=var.int_value++;
				return true;
			}else{
				return false;				
			}			
		}else if(opt.equals("--T")){
			if(var.type==Type_Base.t_int){
				this.type=var.type;
				this.int_value=var.int_value--;
				return true;
			}else{
				return false;				
			}
		}
		return false;
	}
}
