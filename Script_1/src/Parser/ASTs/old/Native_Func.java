package Parser.ASTs.old;

import java.util.*;

import Interpreter.*;
import Parser.TypeSys.*;
import Parser.TypeSys.old.Data_Obj;
import Parser.TypeSys.old.Type_Obj;

public class Native_Func {		
	static Data_Obj runScan(Interpreter interpreter){
		Scanner scan=new Scanner(System.in);
		String s=scan.nextLine();
		scan.close();
		Data_Obj obj=new Data_Obj();
		obj.setTypeObj(interpreter.getGlbEnv().getType("string"));
		obj.setStringV(s);
		return obj;
	}
	static Data_Obj runPrint(Interpreter interpreter, Data_Obj arg){
		Data_Obj obj=new Data_Obj();
		obj.setTypeObj(interpreter.getGlbEnv().getType("bool"));
		Type_Obj t=arg.getTypeObj();
		String s;
		if(t.getTypeBase()!=null){
			switch(t.getTypeBase()){
			case t_int:
				s=String.valueOf(arg.getIntV());
				break;
			case t_double:
				s=String.valueOf(arg.getDoubleV());
				break;
			case t_string:
				s=arg.getStringV();
				break;
			case t_char:
				s=String.valueOf(arg.getCharV());
				break;
			case t_bool:
				s=String.valueOf(arg.getBoolV());
				break;
			default:
				s="unknown";
				break;
			}
		}else{			
			obj.setBoolV(false);
			return obj;
		}
		System.out.println(s);
		obj.setBoolV(true);
		return obj;
	}
}
