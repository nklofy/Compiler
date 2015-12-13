package Parser.ASTs;

import java.util.ArrayList;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_ArgList extends AST {
	private AST_ArgList arg_list;
	private AST_CalcExp calc_exp;
	ArrayList<String> arg_types=new ArrayList<String>();
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
		}
		if(this.calc_exp!=null){
			interpreter.interpret(this.calc_exp);
			this.args.add(calc_exp.data_obj);
		}
		return false;
	}
	ArrayList<Data_Obj> getArgs(){
		return this.args;
	}
}
