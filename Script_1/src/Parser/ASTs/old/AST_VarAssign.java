package Parser.ASTs.old;

import Interpreter.*;
import Parser.*;
import Parser.TypeSys.*;
import Parser.TypeSys.old.Data_Obj;

public class AST_VarAssign extends AST {
	private AST_Var var;
	private AST_CalcExp calc_exp;
	private String opt;
	
	public boolean setVar(AST_Var var){
		this.var=var;
		return true;
	}
	public boolean setOpt(String opt){
		this.opt=opt;
		return true;
	}
	public boolean setCalcExp(AST_CalcExp calc_exp){
		this.calc_exp=calc_exp;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {	
		interpreter.interpret(this.var);
		String name_v=var.name;
		interpreter.interpret(this.calc_exp);
		if(this.opt.equals("=")){
			if(this.calc_exp.data_obj==null){
				System.out.println("error VarAssign eval CalcExp null value");
				return false;
			}else{
				this.var.data_obj=new Data_Obj(this.calc_exp.data_obj);
			}
			//this.var.data_obj.setInit(true);
			interpreter.getCrtFrm().getCrtEnv().addObj(name_v, var.data_obj);
		}else{
			switch(this.opt){//TODO
			case "+=":
				break;
			case "-=":
				break;
			case "*=":
				break;
			case "/=":
				break;
			default:
				return false;
			}
		}
		return true;
	}

}
