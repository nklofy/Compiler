package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;
import Parser.TypeSys.Data_Obj;
import Parser.TypeSys.Type_Base;

public class AST_AddExp extends AST {
	private AST_AddExp add_exp;
	private AST_MulExp mul_exp;
	private String opt;
	private AST_Var var;	
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
		if(this.mul_exp!=null)
			interpreter.interpret(mul_exp);
		if(this.add_exp!=null)
			interpreter.interpret(add_exp);
		if(this.var!=null)
			interpreter.interpret(var);
		if(opt==null){
			if(mul_exp.data_obj!=null){
				this.data_obj=mul_exp.data_obj;
			}else{
				System.out.println("error AddExp eval MulExp with null value");
				return false;
				}			
			return true;
		}else if(opt.equals("-")){
			if(this.add_exp==null){
				this.data_obj=new Data_Obj(this.mul_exp.data_obj);
				if(this.data_obj.getTypeObj().getTypeBase()==null){
					System.out.println("error MddExp eval -MulExp type");
					return false;
				}
				switch(this.data_obj.getTypeObj().getTypeBase()){
				case t_int:
					this.data_obj.setIntV(-this.data_obj.getIntV());
					break;
				case t_double:
					this.data_obj.setDoubleV(-this.data_obj.getDoubleV());
					break;
				default:
					break;
				}
			}else{
				this.data_obj=new Data_Obj();
				if(add_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_int&&mul_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_int){
					this.data_obj.setTypeObj(interpreter.getGlbEnv().getType("int"));
					this.data_obj.setIntV(add_exp.data_obj.getIntV()-mul_exp.data_obj.getIntV());
				}
				else if(add_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_double&&mul_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_double){
					this.data_obj.setTypeObj(interpreter.getGlbEnv().getType("double"));
					this.data_obj.setDoubleV(add_exp.data_obj.getDoubleV()-mul_exp.data_obj.getDoubleV());
				}
				else if(add_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_double&&mul_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_int){
					this.data_obj.setTypeObj(interpreter.getGlbEnv().getType("double"));
					this.data_obj.setDoubleV(add_exp.data_obj.getDoubleV()-mul_exp.data_obj.getIntV());
				}
				else if(add_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_int&&mul_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_double){
					this.data_obj.setTypeObj(interpreter.getGlbEnv().getType("double"));
					this.data_obj.setDoubleV(add_exp.data_obj.getIntV()-mul_exp.data_obj.getDoubleV());
				}
			}
			return true;
		}else if(opt.equals("+")){
			this.data_obj=new Data_Obj();
			if(add_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_int&&mul_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_int){
				this.data_obj.setTypeObj(interpreter.getGlbEnv().getType("int"));
				this.data_obj.setIntV(add_exp.data_obj.getIntV()+mul_exp.data_obj.getIntV());
			}
			else if(add_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_double&&mul_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_double){
				this.data_obj.setTypeObj(interpreter.getGlbEnv().getType("double"));
				this.data_obj.setDoubleV(add_exp.data_obj.getDoubleV()+mul_exp.data_obj.getDoubleV());
			}
			else if(add_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_double&&mul_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_int){
				this.data_obj.setTypeObj(interpreter.getGlbEnv().getType("double"));
				this.data_obj.setDoubleV(add_exp.data_obj.getDoubleV()+mul_exp.data_obj.getIntV());
			}
			else if(add_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_int&&mul_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_double){
				this.data_obj.setTypeObj(interpreter.getGlbEnv().getType("double"));
				this.data_obj.setDoubleV(add_exp.data_obj.getIntV()+mul_exp.data_obj.getDoubleV());
			}
			
		}else if(opt.equals("++")){
			if(var.data_obj.getTypeObj().getTypeBase()!=Type_Base.t_int){
				System.out.println("error AddExp eval Var type");
				return false;
			}else{
				this.data_obj=new Data_Obj(var.data_obj);
				this.data_obj.setIntV(var.data_obj.getIntV()+1);
				var.data_obj.setIntV(this.data_obj.getIntV());
				return true;
			}
		}else if(opt.equals("--")){
			if(var.data_obj.getTypeObj().getTypeBase()!=Type_Base.t_int){
				System.out.println("error AddExp eval Var type");
				return false;
			}else{
				this.data_obj=new Data_Obj(var.data_obj);
				this.data_obj.setIntV(var.data_obj.getIntV()-1);
				var.data_obj.setIntV(this.data_obj.getIntV());
				return true;
			}
		}else if(opt.equals("++T")){
			if(var.data_obj.getTypeObj().getTypeBase()!=Type_Base.t_int){
				System.out.println("error AddExp eval Var type");
				return false;
			}else{
				this.data_obj=new Data_Obj(var.data_obj);
				this.data_obj.setIntV(var.data_obj.getIntV()+1);
				return true;
			}
		}else if(opt.equals("--T")){
			if(var.data_obj.getTypeObj().getTypeBase()!=Type_Base.t_int){
				System.out.println("error AddExp eval Var type");
				return false;
			}else{
				this.data_obj=new Data_Obj(var.data_obj);
				this.data_obj.setIntV(var.data_obj.getIntV()-1);
				var.data_obj.setIntV(this.data_obj.getIntV());
				return true;
			}
		}
		return false;
	}
}
