package Parser.ASTs.old;

import java.util.ArrayList;

import Interpreter.old.Interpreter;
import Parser.AST;
import Parser.TypeSys.old.Data_Obj;
import Parser.TypeSys.old.Type_Obj;

public class AST_ArgList extends AST {
	private AST_ArgList arg_list;
	private AST_CalcExp calc_exp;
	ArrayList<Type_Obj> arg_types=new ArrayList<Type_Obj>();
	ArrayList<Data_Obj> args=new ArrayList<Data_Obj>();
	public boolean setArgList(AST_ArgList arg_list, AST_CalcExp calc_exp){
		this.arg_list=arg_list;
		this.calc_exp=calc_exp;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		if(this.arg_list!=null){
			interpreter.interpret(this.arg_list);
			this.args.addAll(arg_list.getArgs());
			this.arg_types.addAll(arg_list.getTypes());
			arg_list.args.clear();
			arg_list.arg_types.clear();
		}
		if(this.calc_exp!=null){
			interpreter.interpret(this.calc_exp);
			this.args.add(calc_exp.data_obj);
			this.arg_types.add(calc_exp.data_obj.getTypeObj());
		}
		return false;
	}
	ArrayList<Data_Obj> getArgs(){
		return this.args;
	}
	ArrayList<Type_Obj> getTypes(){
		return this.arg_types;
	}
}
