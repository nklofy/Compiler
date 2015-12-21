package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;
import Parser.TypeSys.Data_Obj;
import Parser.TypeSys.Type_Obj;

public class AST_VarDef extends AST {
	private AST_TypeExp type_exp;
	private AST_VarDef var_def;
	private AST_Var var;
	private AST_CalcExp calc_exp;
	
	Type_Obj type;
	public boolean setTypeExp(AST_TypeExp type_exp){
		this.type_exp=type_exp;
		return true;
	}
	public boolean setVarDef(AST_VarDef var_def){
		this.var_def=var_def;
		return true;
	}
	public boolean setVar(AST_Var var){
		this.var=var;
		return true;
	}
	public boolean setCalcExp(AST_CalcExp calc_exp) {
		this.calc_exp = calc_exp;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		if(this.var_def!=null){
			interpreter.interpret(this.var_def);
			this.type=var_def.type;
		}
		if(this.type_exp!=null){
			interpreter.interpret(this.type_exp);
			this.type=this.type_exp.obj_type;
		}
		if(this.var!=null){
			interpreter.interpret(this.var);
			if(this.calc_exp!=null){
				interpreter.interpret(this.calc_exp);
				this.var.data_obj=new Data_Obj(this.calc_exp.data_obj);				
				this.var.data_obj.setInit(true);
				this.var.data_obj.setTypeObj(this.type);
				interpreter.getCrtFrm().getCrtEnv().addObj(var.name, this.var.data_obj);
			}else{
				Data_Obj obj=new Data_Obj();
				interpreter.getCrtFrm().getCrtEnv().addObj(var.name, obj);
			}
		}else{
			System.out.println("error def var");
			return false;
		}
		return true;
	}

}
