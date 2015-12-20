package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;
import Parser.TypeSys.Data_Obj;
import Parser.TypeSys.Type_Base;

public class AST_MulExp extends AST {
	private AST_MulExp mul_exp;
	private AST_PriExp pri_exp;
	private String opt;
	
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
			if(pri_exp.data_obj!=null){
				this.data_obj=pri_exp.data_obj;
			}else{
				System.out.println("error MulExp eval PriExp with null value");
				return false;
			}
			return true;
		}
		else if(opt.equals("*")){
			this.data_obj=new Data_Obj();
			if(mul_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_int&&pri_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_int){
				this.data_obj.setTypeObj(interpreter.getGlbEnv().getType("int"));
				this.data_obj.setIntV(mul_exp.data_obj.getIntV()*pri_exp.data_obj.getIntV());				
			}else if(mul_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_int&&pri_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_double){
				this.data_obj.setTypeObj(interpreter.getGlbEnv().getType("double"));
				this.data_obj.setDoubleV(mul_exp.data_obj.getIntV()*pri_exp.data_obj.getDoubleV());
			}else if(mul_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_double&&pri_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_int){
				this.data_obj.setTypeObj(interpreter.getGlbEnv().getType("double"));
				this.data_obj.setDoubleV(mul_exp.data_obj.getDoubleV()*pri_exp.data_obj.getIntV());
			}else if(mul_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_double&&pri_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_double){
				this.data_obj.setTypeObj(interpreter.getGlbEnv().getType("double"));
				this.data_obj.setDoubleV(mul_exp.data_obj.getDoubleV()*pri_exp.data_obj.getDoubleV());
			}
			return true;
		}else if(opt.equals("/")){
			this.data_obj=new Data_Obj();
			this.data_obj.setTypeObj(interpreter.getGlbEnv().getType("double"));
			if(mul_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_int&&pri_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_int){
				this.data_obj.setDoubleV((double)mul_exp.data_obj.getIntV()/(double)pri_exp.data_obj.getIntV());
			}else if(mul_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_int&&pri_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_double){
				this.data_obj.setDoubleV((double)mul_exp.data_obj.getIntV()/pri_exp.data_obj.getDoubleV());
			}else if(mul_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_double&&pri_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_int){
				this.data_obj.setDoubleV(mul_exp.data_obj.getDoubleV()/(double)pri_exp.data_obj.getIntV());
			}else if(mul_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_double&&pri_exp.data_obj.getTypeObj().getTypeBase()==Type_Base.t_double){
				this.data_obj.setDoubleV(mul_exp.data_obj.getDoubleV()/pri_exp.data_obj.getDoubleV());
			}
			return true;
		}
		return false;
	}
}
