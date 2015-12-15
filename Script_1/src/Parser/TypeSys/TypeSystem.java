package Parser.TypeSys;

import Interpreter.*;

public class TypeSystem {
	public static boolean initTypes(Interpreter interpreter){
		Type_Obj t_i=new Type_Obj();
		t_i.type_name="int";
		t_i.type_base=Type_Base.t_int;
		Type_Obj t_d=new Type_Obj();
		t_d.type_name="double";
		t_d.type_base=Type_Base.t_double;
		Type_Obj t_b=new Type_Obj();
		t_b.type_name="bool";
		t_b.type_base=Type_Base.t_bool;
		Type_Obj t_s=new Type_Obj();
		t_s.type_name="string";
		t_s.type_base=Type_Base.t_string;
		Type_Obj t_c=new Type_Obj();
		t_c.type_name="char";
		t_c.type_base=Type_Base.t_char;
		interpreter.getGlbEnv().addType("int", t_i);
		interpreter.getGlbEnv().addType("double", t_d);
		interpreter.getGlbEnv().addType("bool", t_b);
		interpreter.getGlbEnv().addType("string", t_s);
		interpreter.getGlbEnv().addType("char", t_c);
		return true;
	}
	public static boolean initFuncs(Interpreter interpreter){
		//TODO native fucntions
		return true;
	}
}
