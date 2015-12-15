package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;
import Parser.TypeSys.Data_Obj;
import Parser.TypeSys.Type_Base;

public class AST_MulExp extends AST {
	private AST_MulExp mul_exp;
	private AST_PriExp pri_exp;
	private String opt;
	Type_Base base_type;
	long int_value;
	double double_value;
	boolean bool_value;
	char char_value;
	String string_value;
	Data_Obj data_obj;
	public boolean setMulExp(AST_MulExp mul_exp, String opt, AST_PriExp pri_exp){
		this.mul_exp=mul_exp;
		this.pri_exp=pri_exp;
		this.opt=opt;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		interpreter.interpret(pri_exp);
		if(mul_exp!=null){
			interpreter.interpret(mul_exp);
		}if(opt==null){
			if(pri_exp.base_type!=null){
				this.base_type=pri_exp.base_type;
				switch(pri_exp.base_type){
				case t_int:
					this.int_value=pri_exp.int_value;
					break;
				case t_double:
					this.double_value=pri_exp.double_value;
					break;
				case t_bool:
					this.bool_value=pri_exp.bool_value;
					break;
				case t_char:
					this.char_value=pri_exp.char_value;
					break;
				case t_string:
					this.string_value=pri_exp.string_value;
					break;
				default:
					break;	
				}
			}else if(pri_exp.data_obj!=null){
				this.data_obj=pri_exp.data_obj;
			}else
				return false;			
			return true;
		}
		else if(opt.equals("*")){
			if(mul_exp.base_type==Type_Base.t_int&&pri_exp.base_type==Type_Base.t_int){
				this.base_type=Type_Base.t_int;
				this.int_value=mul_exp.int_value*pri_exp.int_value;				
			}else if(mul_exp.base_type==Type_Base.t_int&&pri_exp.base_type==Type_Base.t_double){
				this.base_type=Type_Base.t_double;
				this.double_value=mul_exp.int_value*pri_exp.double_value;
			}else if(mul_exp.base_type==Type_Base.t_double&&pri_exp.base_type==Type_Base.t_int){
				this.base_type=Type_Base.t_double;
				this.double_value=mul_exp.double_value*pri_exp.int_value;
			}else if(mul_exp.base_type==Type_Base.t_double&&pri_exp.base_type==Type_Base.t_double){
				this.base_type=Type_Base.t_double;
				this.double_value=mul_exp.double_value*pri_exp.double_value;
			}
			return true;
		}else if(opt.equals("/")){
			this.base_type=Type_Base.t_double;
			if(mul_exp.base_type==Type_Base.t_int&&pri_exp.base_type==Type_Base.t_int){
				this.double_value=(double)mul_exp.int_value/(double)pri_exp.int_value;
			}else if(mul_exp.base_type==Type_Base.t_int&&pri_exp.base_type==Type_Base.t_double){
				this.double_value=(double)mul_exp.int_value/pri_exp.double_value;
			}else if(mul_exp.base_type==Type_Base.t_double&&pri_exp.base_type==Type_Base.t_int){
				this.double_value=mul_exp.double_value/(double)pri_exp.int_value;
			}else if(mul_exp.base_type==Type_Base.t_double&&pri_exp.base_type==Type_Base.t_double){
				this.double_value=mul_exp.double_value/pri_exp.double_value;
			}
			return true;
		}
		return false;
	}

}
