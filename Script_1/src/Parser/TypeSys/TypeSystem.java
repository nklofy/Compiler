package Parser.TypeSys;

import Interpreter.*;
import Parser.TypeSys.old.Type_Base;
import Parser.TypeSys.old.Type_Obj;

public class TypeSystem {
	public static boolean initTypes(Interpreter interpreter){
		Type_Obj t_i=new Type_Obj();
		t_i.setTypeName("int");
		t_i.setTypeBase(Type_Base.t_int);
		Type_Obj t_d=new Type_Obj();
		t_d.setTypeName("double");
		t_d.setTypeBase(Type_Base.t_double);
		Type_Obj t_b=new Type_Obj();
		t_b.setTypeName("bool");
		t_b.setTypeBase(Type_Base.t_bool);
		Type_Obj t_s=new Type_Obj();
		t_s.setTypeName("string");
		t_s.setTypeBase(Type_Base.t_string);
		Type_Obj t_c=new Type_Obj();
		t_c.setTypeName("char");
		t_c.setTypeBase(Type_Base.t_char);
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
