package Parser.TypeSys;

import Parser.CodeGenerator;

public class T_BasicType extends T_Type {
	en_BType btype;
	public T_BasicType(){
		
	}
	public T_BasicType(String t){
		switch(t){
		case "int":
			this.btype=en_BType.t_int;
			break;
		case "double":
			this.btype=en_BType.t_double;
			break;
		case "char":
			this.btype=en_BType.t_char;
			break;
		case "string":
			this.btype=en_BType.t_string;
			break;
		case "bool":
			this.btype=en_BType.t_bool;
			break;
			default:break;//modify in future
		}
		setTypeB(this.btype);
	}
	public en_BType getTypeB() {
		return btype;
	}
	public void setTypeB(en_BType btype) {
		this.btype = btype;
		switch(btype){
		case t_int:
			this.setTypeSig("int");
			break;
		case t_double:
			this.setTypeSig("double");
			break;
		case t_char:
			this.setTypeSig("char");
			break;
		case t_string:
			this.setTypeSig("string");
			break;
		case t_bool:
			this.setTypeSig("bool");
			break;
		}
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
