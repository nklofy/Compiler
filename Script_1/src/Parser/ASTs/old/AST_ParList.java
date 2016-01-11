package Parser.ASTs.old;

import java.util.ArrayList;

import Interpreter.Interpreter;
import Parser.AST;
import Parser.TypeSys.old.Type_Obj;

public class AST_ParList extends AST {
	private AST_TypeExp type_exp;
	private AST_Var var;
	private AST_ParList par_list;
	ArrayList<Type_Obj> par_types=new ArrayList<Type_Obj>();
	ArrayList<String> par_names=new ArrayList<String>();
	public boolean setParList(AST_ParList par_list, AST_TypeExp type_exp, AST_Var var){
		this.par_list=par_list;
		this.type_exp=type_exp;
		this.var=var;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		if(this.par_list!=null){
			interpreter.interpret(this.par_list);
			this.par_names.addAll(this.par_list.par_names);
			this.par_types.addAll(this.par_list.par_types);
			this.par_list.par_names.clear();
			this.par_list.par_types.clear();
		}
		if(this.var!=null && this.type_exp!=null){
			interpreter.interpret(this.var);
			interpreter.interpret(this.type_exp);
			this.par_names.add(this.var.name);
			this.par_types.add(this.type_exp.obj_type);
		}else
			return false;
		return true;
	}

}
