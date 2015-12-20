package Parser.ASTs;

import java.util.*;

import Interpreter.*;
import Parser.*;
import Parser.TypeSys.*;
import Interpreter.RT_CtrFlow;
public class AST_ApplyExp extends AST {
	private AST_ApplyExp apply_exp;
	private AST_Var var;
	private AST_ArgList arg_list;	
	Data_Obj data_obj;
	Data_Obj in_obj;
	public boolean setApplyExp(AST_ApplyExp apply_exp, AST_Var var, AST_ArgList arg_list){
		this.apply_exp=apply_exp;
		this.var=var;
		this.arg_list=arg_list;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		Data_Obj obj=null;
		if(apply_exp!=null){//not used currently
			interpreter.interpret(apply_exp);
			this.in_obj=apply_exp.data_obj;
		}
		if(arg_list!=null){//function apply
			interpreter.interpret(var);
			interpreter.interpret(arg_list);
			if(var.data_obj==null){
				switch(var.name){
				case "println":
					Data_Obj o=arg_list.getArgs().get(0);
					this.data_obj=Native_Func.runPrint(interpreter, o);
					this.arg_list.arg_types.clear();
					this.arg_list.args.clear();
					break;
				case "scan":
					this.data_obj=Native_Func.runScan(interpreter);	
					break;
				default:
					if(this.in_obj==null){					//function()
						Data_Func f=var.getFunc(this.arg_list.arg_types);
						this.arg_list.arg_types.clear();
						this.data_obj=f.run(interpreter, arg_list.getArgs());		
					}else{										//obj.method()
						Data_Func f=this.in_obj.getFunc(var.name,arg_list.arg_types);
						this.arg_list.arg_types.clear();
						this.data_obj=f.run(interpreter, arg_list.getArgs());
					}
					break;
				}
			}else{
				System.out.println("undefined function "+var.name);
				return false;
			}
			return true;
		}
		if(var!=null){
			interpreter.interpret(var);
			if(this.in_obj==null){
				if(var.data_obj==null){
					System.out.println("error null var");
					return false;
				}else{// get var object
					this.data_obj=var.data_obj;					
				}				
				return true;
			}else{// in_obj exists
				this.data_obj=this.in_obj.getField(var.name);				
				return true;
			}
		}		
		return false;
	}
	
}
