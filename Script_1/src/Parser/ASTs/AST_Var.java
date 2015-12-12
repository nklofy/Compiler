package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;

public class AST_Var extends AST {
	String name;
	Data_Obj data_obj;
	//Object obj_value; 
	public boolean setVar(String name){
		this.name=name;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		Data_Obj obj=interpreter.getCrtFrm().getCrtEnv().getObj(this.name);
		if(obj==null){
			obj=interpreter.getGlbEnv().getObj(this.name);
		}
		if(obj==null){
			obj=interpreter.getStcEnv().getObj(this.name);
		}			
		this.data_obj=new Data_Obj(obj);
		return true;
	}

}
