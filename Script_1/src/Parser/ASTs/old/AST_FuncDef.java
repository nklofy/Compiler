package Parser.ASTs.old;

import Interpreter.*;
import Interpreter.old.Interpreter;
import Interpreter.old.RT_Env;
import Interpreter.old.RT_Static;
import Parser.*;
import Parser.TypeSys.*;
import Parser.TypeSys.old.Data_Func;
import Parser.TypeSys.old.Data_Obj;
import Parser.TypeSys.old.Type_Func;

public class AST_FuncDef extends AST {
	private AST_TypeExp type_exp;
	private AST_Var var;
	private AST_ParList par_list;
	private AST_StmtList stmt_list;
	String name;
	public boolean setFuncDef(AST_TypeExp type_exp, AST_Var name, AST_ParList par_list, AST_StmtList stmt_list){
		this.type_exp=type_exp;
		this.var=name;
		this.par_list=par_list;
		this.stmt_list=stmt_list;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		interpreter.interpret(type_exp);
		interpreter.interpret(var);
		this.name=var.name;
		interpreter.interpret(par_list);
		Data_Func func_data=new Data_Func();
		Type_Func func_type=new Type_Func();
		func_data.setTypeFunc(func_type);
		func_type.setDataFunc(func_data);
		func_type.setFuncName(this.name);
		func_type.getParTypes().addAll(this.par_list.par_types);
		func_data.getParList().addAll(this.par_list.par_names);
		func_type.setReturnType(type_exp.obj_type);
		func_data.setStmtList(this.stmt_list);
		RT_Env crt_env=interpreter.getCrtFrm().getCrtEnv();
		RT_Static crt_stc=interpreter.getStcEnv();//TODO if this func is static
		crt_env.addFunc(this.name, func_type);
		return true;
	}
}
