package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;
import Parser.TypeSys.Type_Base;
import Parser.TypeSys.Type_Obj;

public class AST_TypeExp extends AST {
	private String type_name;
	Type_Obj obj_type;
	public boolean setType(String type){
		this.type_name=type;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		this.obj_type=interpreter.getCrtFrm().getCrtEnv().getType(this.type_name);
		if(this.obj_type==null){
			this.obj_type=interpreter.getGlbEnv().getType(this.type_name);
		}
		if(this.obj_type==null){
			this.obj_type=interpreter.getStcEnv().getType(this.type_name);
		}
		return true;
	}

}
