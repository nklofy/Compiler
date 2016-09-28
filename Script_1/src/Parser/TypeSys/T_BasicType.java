package Parser.TypeSys;

import Parser.CodeGenerator;

public class T_BasicType extends T_Type {
	en_BType btype;
	{this.setKType(KType.t_bsc);}
	//public T_BasicType(){
	//	this.setKType(KType.t_bsc);
	//}
	public T_BasicType(String t){
		//this.setKType(KType.t_bsc);
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

	public boolean canCastFrom(CodeGenerator codegen,T_Type type2){
		if(!(type2 instanceof T_BasicType))
			return false;
		T_BasicType type3=(T_BasicType)type2;
		if(this.btype==type3.btype)
			return true;
		if(this.btype==en_BType.t_int){
			if(type3.btype==en_BType.t_int||type3.btype==en_BType.t_double||type3.btype==en_BType.t_string){
				return true;
			}
		}else if(this.btype==en_BType.t_double){
			if(type3.btype==en_BType.t_double||type3.btype==en_BType.t_int||type3.btype==en_BType.t_string){
				return true;
			}
		}else if(this.btype==en_BType.t_char){
			if(type3.btype==en_BType.t_char||type3.btype==en_BType.t_int||type3.btype==en_BType.t_string){
				return true;
			}
		}else if(this.btype==en_BType.t_bool){
			if(type3.btype==en_BType.t_bool||type3.btype==en_BType.t_int||type3.btype==en_BType.t_string){
				return true;
			}
		}
		return false;
	}
	public enum en_BType{t_int,t_double,t_string,t_char,t_bool;}
}
