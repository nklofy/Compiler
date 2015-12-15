//a function's properties
package Parser.TypeSys;

import java.util.ArrayList;
import Interpreter.*;
import Parser.ASTs.AST_StmtList;

public class Data_Func {
	public boolean isMethod;		//is obj's method
	public Type_Obj class_type;		//obj as method caller
	public Type_Func type_func;
	public ArrayList<String> par_list;
	public AST_StmtList stmt_list;
	public Data_Obj run(Interpreter interpreter, ArrayList<Data_Obj> arg_list){
		RT_Frame crt_frm=interpreter.getCrtFrm();
		RT_Frame new_frm=new RT_Frame();
		interpreter.setCrtFrm(new_frm);
		new_frm.setRtnFrm(crt_frm);
		RT_Env new_env=new RT_Env();
		new_frm.setCrtEnv(new_env);
		for(int i=0;i<par_list.size();i++){
			new_frm.getArgs().add(arg_list.get(i));
			new_env.addObj(par_list.get(i), arg_list.get(i));
		}
		interpreter.interpret(stmt_list);
		return new_frm.getRtnObj();
	}
}
