package Parser.ASTs.old;

import Interpreter.Interpreter;
import Parser.AST;
import Parser.TypeSys.old.Type_Base;

public class AST_Num extends AST {
	private String bf_type;
	private String buffer;
	Type_Base type;
	long int_value;
	double double_value;
	public boolean setNum(String type, String buffer){
		this.bf_type=type;
		this.buffer=buffer;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		if(bf_type.equals("int")){
			type=Type_Base.t_int;
			int_value=Long.parseLong(buffer);
		}else if(bf_type.equals("double")){
			type=Type_Base.t_double;
			double_value=Double.parseDouble(buffer);
		}
		return true;
	}

}
