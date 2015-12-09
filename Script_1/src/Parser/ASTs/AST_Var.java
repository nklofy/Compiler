package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_Var extends AST {
	String name;
	Type_Base type;
	long int_value;
	double double_value;
	boolean bool_value;
	char char_value;
	String string_value;
	Data_Func func;
	Data_Obj obj;
	//Object obj_value; 
	public boolean setVar(String name){
		this.name=name;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		return true;
	}

}
