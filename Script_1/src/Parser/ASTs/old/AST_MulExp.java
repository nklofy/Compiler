package Parser.ASTs.old;

import Interpreter.Interpreter;
import Parser.AST;
import Parser.TypeSys.old.Data_Obj;
import Parser.TypeSys.old.Type_Base;

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
		Data_Obj obj_mul=null;
		Data_Obj obj_pri=null;
		if(mul_exp!=null){
			interpreter.interpret(mul_exp);
			obj_mul=new Data_Obj(this.mul_exp.data_obj);
		}if(pri_exp!=null){
			interpreter.interpret(pri_exp);
			obj_pri=new Data_Obj(this.pri_exp.data_obj);
		}
		if(opt==null){
			if(pri_exp.data_obj!=null){
				this.data_obj=obj_pri;
			}else{
				System.out.println("error MulExp eval PriExp with null value");
				return false;
			}
			return true;
		}
		else if(opt.equals("*")){
			this.data_obj=new Data_Obj();
			if(obj_mul.getTypeObj().getTypeBase()==Type_Base.t_int&&obj_pri.getTypeObj().getTypeBase()==Type_Base.t_int){
				this.data_obj.setTypeObj(interpreter.getGlbEnv().getType("int"));
				this.data_obj.setIntV(obj_mul.getIntV()*obj_pri.getIntV());				
			}else if(obj_mul.getTypeObj().getTypeBase()==Type_Base.t_int&&obj_pri.getTypeObj().getTypeBase()==Type_Base.t_double){
				this.data_obj.setTypeObj(interpreter.getGlbEnv().getType("double"));
				this.data_obj.setDoubleV(obj_mul.getIntV()*obj_pri.getDoubleV());
			}else if(obj_mul.getTypeObj().getTypeBase()==Type_Base.t_double&&obj_pri.getTypeObj().getTypeBase()==Type_Base.t_int){
				this.data_obj.setTypeObj(interpreter.getGlbEnv().getType("double"));
				this.data_obj.setDoubleV(obj_mul.getDoubleV()*obj_pri.getIntV());
			}else if(obj_mul.getTypeObj().getTypeBase()==Type_Base.t_double&&obj_pri.getTypeObj().getTypeBase()==Type_Base.t_double){
				this.data_obj.setTypeObj(interpreter.getGlbEnv().getType("double"));
				this.data_obj.setDoubleV(obj_mul.getDoubleV()*obj_pri.getDoubleV());
			}
			return true;
		}else if(opt.equals("/")){
			this.data_obj=new Data_Obj();
			this.data_obj.setTypeObj(interpreter.getGlbEnv().getType("double"));
			if(obj_mul.getTypeObj().getTypeBase()==Type_Base.t_int&&obj_pri.getTypeObj().getTypeBase()==Type_Base.t_int){
				this.data_obj.setDoubleV((double)obj_mul.getIntV()/(double)obj_pri.getIntV());
			}else if(obj_mul.getTypeObj().getTypeBase()==Type_Base.t_int&&obj_pri.getTypeObj().getTypeBase()==Type_Base.t_double){
				this.data_obj.setDoubleV((double)obj_mul.getIntV()/obj_pri.getDoubleV());
			}else if(obj_mul.getTypeObj().getTypeBase()==Type_Base.t_double&&obj_pri.getTypeObj().getTypeBase()==Type_Base.t_int){
				this.data_obj.setDoubleV(obj_mul.getDoubleV()/(double)obj_pri.getIntV());
			}else if(obj_mul.getTypeObj().getTypeBase()==Type_Base.t_double&&obj_pri.getTypeObj().getTypeBase()==Type_Base.t_double){
				this.data_obj.setDoubleV(obj_mul.getDoubleV()/obj_pri.getDoubleV());
			}
			return true;
		}
		return false;
	}
}
