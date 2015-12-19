//a function's properties
package Parser.TypeSys;

import java.util.ArrayList;
import Interpreter.*;
import Parser.ASTs.AST_StmtList;

public class Data_Func {
	private boolean isMethod;		//is obj's method
	private Type_Obj class_type;		//obj as method caller
	private Type_Func type_func;	
	private ArrayList<String> par_list=new ArrayList<String>();
	private AST_StmtList stmt_list;
	public Data_Obj run(Interpreter interpreter, ArrayList<Data_Obj> arg_list){
		RT_Frame crt_frm=interpreter.getCrtFrm();
		RT_Frame new_frm=new RT_Frame();
		interpreter.setCrtFrm(new_frm);
		new_frm.setRtnFrm(crt_frm);
		RT_Env new_env=new RT_Env();
		new_frm.setCrtEnv(new_env);
		for(int i=0;i<par_list.size();i++){
			//new_frm.getArgs().add(arg_list.get(i));
			new_env.addObj(par_list.get(i), arg_list.get(i));
		}
		interpreter.interpret(stmt_list);
		return new_frm.getRtnObj();
	}
	public boolean isMethod() {
		return isMethod;
	}
	public void setMethod(boolean isMethod) {
		this.isMethod = isMethod;
	}
	public Type_Obj getClassType() {
		return class_type;
	}
	public void setClassType(Type_Obj class_type) {
		this.class_type = class_type;
	}
	public Type_Func getTypeFunc() {
		return type_func;
	}
	public void setTypeFunc(Type_Func type_func) {
		this.type_func = type_func;
	}
	public ArrayList<String> getParList() {
		return par_list;
	}
	public void setParList(ArrayList<String> par_list) {
		this.par_list = par_list;
	}
	public AST_StmtList getStmtList() {
		return stmt_list;
	}
	public void setStmtList(AST_StmtList stmt_list) {
		this.stmt_list = stmt_list;
	}
}
