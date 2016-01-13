package Parser.ASTs.old;

import Interpreter.old.Interpreter;
import Parser.AST;
import Parser.TypeSys.old.Data_Obj;
import Parser.TypeSys.old.Type_Base;

public class AST_CmpExp extends AST {
	private AST_BoolExp bool_exp;
	private AST_AddExp add_exp1;
	private AST_AddExp add_exp2;
	private String opt;
	int bl_value;		//directly set this exp true or false	
	Data_Obj data_obj;
	public boolean setCmpExp(AST_BoolExp bool_exp, AST_AddExp add_exp1, String opt, AST_AddExp add_exp2, int bl_value){
		this.bool_exp=bool_exp;
		this.add_exp1=add_exp1;
		this.add_exp2=add_exp2;
		this.opt=opt;	
		this.bl_value=bl_value;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		Data_Obj obj_bl=null;
		Data_Obj obj_a1=null;
		Data_Obj obj_a2=null;
		if(this.bl_value==1){
			this.data_obj=new Data_Obj();
			this.data_obj.setTypeObj(interpreter.getGlbEnv().getType("bool"));
			this.data_obj.setBoolV(true);
		}else if(this.bl_value==-1){
			this.data_obj=new Data_Obj();
			this.data_obj.setTypeObj(interpreter.getGlbEnv().getType("bool"));
			this.data_obj.setBoolV(false);
		}else if(this.bool_exp!=null){
			interpreter.interpret(this.bool_exp);
			obj_bl=new Data_Obj(this.bool_exp.data_obj);
			if(this.bool_exp.data_obj!=null){
				this.data_obj=obj_bl;
			}else{
				System.out.println("error CmpExp eval BoolExp null value");
				return false;
			}
		}else if(this.opt!=null && this.add_exp1!=null && this.add_exp2!=null){
			interpreter.interpret(this.add_exp1);
			obj_a1=new Data_Obj(this.add_exp1.data_obj);
			interpreter.interpret(this.add_exp2);
			obj_a2=new Data_Obj(this.add_exp2.data_obj);
			this.data_obj=new Data_Obj();
			this.data_obj.setTypeObj(interpreter.getGlbEnv().getType("bool"));
			switch(this.opt){
			case "<":				
				this.data_obj.setBoolV((obj_a1.getIntV()+obj_a1.getDoubleV())<(obj_a2.getIntV()+obj_a2.getDoubleV()));
				break;
			case ">":
				this.data_obj.setBoolV((obj_a1.getIntV()+obj_a1.getDoubleV())>(obj_a2.getIntV()+obj_a2.getDoubleV()));
				break;
			case "<=":
				this.data_obj.setBoolV((obj_a1.getIntV()+obj_a1.getDoubleV())<=(obj_a2.getIntV()+obj_a2.getDoubleV()));
				break;
			case ">=":
				this.data_obj.setBoolV((obj_a1.getIntV()+obj_a1.getDoubleV())>=(obj_a2.getIntV()+obj_a2.getDoubleV()));
				break;
			case "==":
				this.data_obj.setBoolV((obj_a1.getIntV()+obj_a1.getDoubleV())==(obj_a2.getIntV()+obj_a2.getDoubleV()));
				break;
			case "!=":
				this.data_obj.setBoolV((obj_a1.getIntV()+obj_a1.getDoubleV())!=(obj_a2.getIntV()+obj_a2.getDoubleV()));
				break;
			default:
				break;
			}
		}else if(this.add_exp1!=null && this.add_exp2 == null){
			interpreter.interpret(this.add_exp1);
			obj_a1=new Data_Obj(this.add_exp1.data_obj);
			if(this.add_exp1.data_obj!=null){
				this.data_obj=obj_a1;
			}else{
				System.out.println("error CmpExp eval AddExp1 null value");
				return false;
			}
		}else{
			System.out.println("error CmpExp null nodes");
			return false;
		}
		return true;
	}

}
