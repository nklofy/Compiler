package Parser.TypeSys;

import Interpreter.*;

public class TypeSystem {//defining, producing, managing all kinds of Types
	public TypeSystem(){
		this.initTypes();
		this.initFuncs();
	}
	T_BasicType t_int=new T_BasicType();
	T_BasicType t_double=new T_BasicType();
	T_BasicType t_bool=new T_BasicType();
	T_BasicType t_string=new T_BasicType();
	T_BasicType t_char=new T_BasicType();
	public boolean initTypes(){
		this.t_int.setTypeB(T_BasicType.en_BType.t_int);
		this.t_double.setTypeB(T_BasicType.en_BType.t_double);
		this.t_bool.setTypeB(T_BasicType.en_BType.t_bool);
		this.t_string.setTypeB(T_BasicType.en_BType.t_string);
		this.t_char.setTypeB(T_BasicType.en_BType.t_char);
		return true;
	}
	public T_Type getBType(String s){
		switch(s){
		case "int":
			return this.t_int;
		case "double":
			return this.t_double;
		case "bool":
			return this.t_bool;
		case "string":
			return this.t_string;
		case "char":
			return this.t_char;
		default:
			return null;
		}
	}
	public boolean initFuncs(){
		
		return true;
	}
}
