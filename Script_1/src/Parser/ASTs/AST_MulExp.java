package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_MulExp extends AST {
	private AST_MulExp mul_exp;
	private AST_PriExp pri_exp;
	private String opt;
	long int_value;
	double double_value;
	Type_Base type;
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
			this.type=pri_exp.type;
			if(pri_exp.type==Type_Base.t_int){
				this.int_value=pri_exp.int_value;
			}else if(pri_exp.type==Type_Base.t_double){
				this.double_value=pri_exp.double_value;
			}
			return true;
		}
		else if(opt.equals("*")){
			if(mul_exp.type==Type_Base.t_int&&pri_exp.type==Type_Base.t_int){
				this.type=Type_Base.t_int;
				this.int_value=mul_exp.int_value*pri_exp.int_value;				
			}else if(mul_exp.type==Type_Base.t_int&&pri_exp.type==Type_Base.t_double){
				this.type=Type_Base.t_double;
				this.double_value=mul_exp.int_value*pri_exp.double_value;
			}else if(mul_exp.type==Type_Base.t_double&&pri_exp.type==Type_Base.t_int){
				this.type=Type_Base.t_double;
				this.double_value=mul_exp.double_value*pri_exp.int_value;
			}else if(mul_exp.type==Type_Base.t_double&&pri_exp.type==Type_Base.t_double){
				this.type=Type_Base.t_double;
				this.double_value=mul_exp.double_value*pri_exp.double_value;
			}
			return true;
		}else if(opt.equals("/")){
			this.type=Type_Base.t_double;
			if(mul_exp.type==Type_Base.t_int&&pri_exp.type==Type_Base.t_int){
				this.double_value=(double)mul_exp.int_value/(double)pri_exp.int_value;
			}else if(mul_exp.type==Type_Base.t_int&&pri_exp.type==Type_Base.t_double){
				this.double_value=(double)mul_exp.int_value/pri_exp.double_value;
			}else if(mul_exp.type==Type_Base.t_double&&pri_exp.type==Type_Base.t_int){
				this.double_value=mul_exp.double_value/(double)pri_exp.int_value;
			}else if(mul_exp.type==Type_Base.t_double&&pri_exp.type==Type_Base.t_double){
				this.double_value=mul_exp.double_value/pri_exp.double_value;
			}
			return true;
		}
		return false;
	}

}
