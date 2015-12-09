package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_CalcExp extends AST {
	private AST_AddExp add_exp;
	private AST_BoolExp bool_exp;
	private AST_StrExp str_exp;
	Type_Base type;
	Data_Func data_func;	//function var 
	Data_Obj data_obj;		//value of add_exp
	boolean bool_value;		//value of bool_exp
	String string_value;	//value of string_exp
	char char_value;	//value of char_exp
	public boolean setCalcExp(AST_AddExp add_exp, AST_BoolExp bool_exp, AST_StrExp str_exp){
		this.add_exp=add_exp;
		this.bool_exp=bool_exp;
		this.str_exp=str_exp;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		// TODO Auto-generated method stub
		return false;
	}

}
