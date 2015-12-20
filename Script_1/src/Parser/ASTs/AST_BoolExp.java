package Parser.ASTs;

import Interpreter.Interpreter;
import Parser.AST;
import Parser.TypeSys.Data_Obj;
import Parser.TypeSys.Type_Base;

public class AST_BoolExp extends AST {
	private AST_BoolExp bool_exp;
	private AST_CmpExp cmp_exp;
	private String opt;	
	Data_Obj data_obj;
	public boolean setBoolExp(AST_BoolExp bool_exp, String opt, AST_CmpExp cmp_exp){
		this.bool_exp=bool_exp;
		this.cmp_exp=cmp_exp;
		this.opt=opt;
		return true;
	}
	@Override
	public boolean eval(Interpreter interpreter) {
		if(this.cmp_exp!=null)
			interpreter.interpret(this.cmp_exp);
		if(this.opt==null){
			if(this.cmp_exp.data_obj!=null){
				this.data_obj=this.cmp_exp.data_obj;
			}else{
				System.out.println("error BoolExp eval CmpExp with null value");
				return false;
				}			
		}else if(this.bool_exp!=null){
			interpreter.interpret(this.bool_exp);
			this.data_obj=new Data_Obj();
			this.data_obj.setTypeObj(interpreter.getGlbEnv().getType("bool"));
			switch(this.opt){
			case "&&":
				this.data_obj.setBoolV(this.bool_exp.data_obj.getBoolV() && this.cmp_exp.data_obj.getBoolV());
				break;
			case "||":
				this.data_obj.setBoolV(this.bool_exp.data_obj.getBoolV() || this.cmp_exp.data_obj.getBoolV());
				break;
				default:
					break;
			}
		}
		else if(this.opt=="!"){
			if(this.cmp_exp.data_obj.getTypeObj().getTypeBase()!=Type_Base.t_bool){
				System.out.println("error BoolExp eval !CmpExp type");
				return false;
			}
			else{
				this.data_obj=new Data_Obj(this.cmp_exp.data_obj);
				this.data_obj.setBoolV(!this.cmp_exp.data_obj.getBoolV());
			}
		}else{
			System.out.println("error BoolExp eval null nodes");
			return false;
		}
		return true;
	}

}
