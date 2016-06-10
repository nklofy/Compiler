package Parser.TypeSys;

import Parser.CodeGenerator;

public class T_BasicType extends T_Type {
	en_BType btype;
	public en_BType getTypeB() {
		return btype;
	}
	public void setTypeB(en_BType btype) {
		this.btype = btype;
	}
	public boolean isEqType(T_BasicType t){
		if(this.btype==t.btype)
			return true;
		else
			return false;
	}
	public boolean canOpt(CodeGenerator codegen, String opt, int position){//0 means return position, -1 means left, 1 means right
		switch(opt){
			case "+":
				if(this.btype==en_BType.t_int||this.btype==en_BType.t_double||
						this.btype==en_BType.t_char||this.btype==en_BType.t_string)
					return true;
				break;
			case "-":
				if(this.btype==en_BType.t_int||this.btype==en_BType.t_double)
					return true;
				break;
			case "*":
				if(this.btype==en_BType.t_int||this.btype==en_BType.t_double)
					return true;
				break;
			case "/":
				if(this.btype==en_BType.t_int||this.btype==en_BType.t_double)
					return true;
				break;
			case "++":
				if(this.btype==en_BType.t_int)
					return true;
				break;
			case "--":
				if(this.btype==en_BType.t_int)
					return true;
				break;
			default:
				break;
		}
		return false;
	}

	public boolean canCast(CodeGenerator codegen,T_BasicType type2){
		if(this.btype==en_BType.t_int){
			if(type2.btype==en_BType.t_double||type2.btype==en_BType.t_string){
				return true;
			}
		}else if(this.btype==en_BType.t_double){
			if(type2.btype==en_BType.t_int||type2.btype==en_BType.t_string){
				return true;
			}
		}else if(this.btype==en_BType.t_char){
			if(type2.btype==en_BType.t_int||type2.btype==en_BType.t_string){
				return true;
			}
		}else if(this.btype==en_BType.t_bool){
			if(type2.btype==en_BType.t_int||type2.btype==en_BType.t_string){
				return true;
			}
		}
		return false;
	}
	public enum en_BType{t_int,t_double,t_string,t_char,t_bool;}
}
