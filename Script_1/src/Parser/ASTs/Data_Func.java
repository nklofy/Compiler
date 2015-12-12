//a function's properties
package Parser.ASTs;

import java.util.ArrayList;
import Interpreter.*;

public class Data_Func {
	boolean isMethod;		//is obj's method
	Data_Obj data_obj;		//obj as method caller
	Type_Func type_func;
	ArrayList<String> par_list=new ArrayList<String>();
	AST_StmtList stmt_list;
	Data_Obj run(Interpreter interpreter, ArrayList<Data_Obj> arg_list){
		//new frame
		//arg_table
		//eval stmtlist
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
