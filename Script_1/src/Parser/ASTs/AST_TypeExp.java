package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_TypeExp extends AST {
	private Type_Base base_type;
	private String type_name;
	Type_Obj obj_type;
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
			this.type_name=type;
			break;
		}
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		if(this.base_type!=null){
			this.obj_type.type_base=this.base_type;
		}else{
			// TODO deal with class type or self-defined type
			this.obj_type=interpreter.getCrtFrm().getCrtEnv().getType(this.type_name);
			if(this.obj_type==null)
				interpreter.getGlbEnv().getType(this.type_name);
			if(this.obj_type==null)
				interpreter.getStcEnv().getType(this.type_name);
		}
		return true;
	}

}
