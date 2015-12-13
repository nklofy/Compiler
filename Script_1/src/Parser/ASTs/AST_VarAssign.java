package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_VarAssign extends AST {
	private AST_VarAssign var_assign;
	private AST_TypeExp type_exp;
	private AST_Var var;
	private AST_CalcExp calc_exp;
	private String opt;
	Type_Obj type;
	public boolean setVarAssign(AST_VarAssign var_assign){
		this.var_assign=var_assign;
		return true;
	}
	public boolean setTypeExp(AST_TypeExp type_exp){
		this.type_exp=type_exp;
		return true;
	}
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
		if(this.var_assign!=null){
			interpreter.interpret(this.var_assign);
			this.type=this.var_assign.type;
		}
		if(this.type_exp!=null){
			interpreter.interpret(this.type_exp);
			this.type=this.type_exp.obj_type;
			interpreter.interpret(this.var);
			interpreter.interpret(this.calc_exp);
			if(this.var.data_obj!=null){
				return false;
			}else{
				if(this.calc_exp.data_obj==null){
					var.data_obj=this.calc_exp.boxObj();
				}else{
					var.data_obj=this.calc_exp.data_obj;
				}				
				var.data_obj.type_ref=this.type;
				var.data_obj.hasInit=true;
				interpreter.getCrtFrm().getCrtEnv().addObj(var.name, var.data_obj);
			}
		}else{
			interpreter.interpret(this.var);
			interpreter.interpret(this.calc_exp);
			if(this.var.data_obj!=null&&this.type!=null){
				return false;
			}
			if(this.opt.equals("=")){
				if(this.calc_exp.data_obj==null){
					var.data_obj=this.calc_exp.boxObj();
				}else{
					var.data_obj=this.calc_exp.data_obj;
				}		
				if(this.type!=null)
					var.data_obj.type_ref=this.type;
				var.data_obj.hasInit=true;
				interpreter.getCrtFrm().getCrtEnv().addObj(var.name, var.data_obj);
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
		}
		return true;
	}

}
