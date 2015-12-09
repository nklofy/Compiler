package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_TypeExp extends AST {
	private boolean isBaseType=true;
	private boolean isVarType=false;
	private boolean isExpType=false;
	private Type_Base base_type;
	private String var_type;
	public boolean setType(String type){
		switch(type){
		case "int":
			this.base_type=Type_Base.t_int;
			break;
		case "double":
			this.base_type=Type_Base.t_double;
			break;
		case "bool":
			this.base_type=Type_Base.t_bool;
			break;
		case "string":
			this.base_type=Type_Base.t_string;
			break;
		case "char":
			this.base_type=Type_Base.t_char;
			break;
		default:
			//TODO, here is tobe changed after add class or type expression
			break;
		}
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		// TODO Auto-generated method stub
		return false;
	}

}
